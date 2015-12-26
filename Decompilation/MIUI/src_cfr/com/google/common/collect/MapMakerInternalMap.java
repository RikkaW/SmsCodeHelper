/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ObjectInputStream
 *  java.io.ObjectOutputStream
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.ref.ReferenceQueue
 *  java.util.AbstractCollection
 *  java.util.AbstractMap
 *  java.util.AbstractQueue
 *  java.util.Map$Entry
 *  java.util.concurrent.ConcurrentLinkedQueue
 *  java.util.concurrent.ConcurrentMap
 *  java.util.concurrent.TimeUnit
 *  java.util.concurrent.atomic.AtomicInteger
 *  java.util.concurrent.atomic.AtomicReferenceArray
 *  java.util.concurrent.locks.ReentrantLock
 *  java.util.logging.Level
 *  java.util.logging.Logger
 */
package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Equivalences;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import com.google.common.collect.AbstractLinkedIterator;
import com.google.common.collect.AbstractMapEntry;
import com.google.common.collect.ForwardingConcurrentMap;
import com.google.common.collect.GenericMapMaker;
import com.google.common.collect.Iterators;
import com.google.common.collect.MapMaker;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

class MapMakerInternalMap<K, V>
extends AbstractMap<K, V>
implements ConcurrentMap<K, V>,
Serializable {
    static final Queue<? extends Object> DISCARDING_QUEUE;
    static final ValueReference<Object, Object> UNSET;
    private static final Logger logger;
    private static final long serialVersionUID = 5;
    final int concurrencyLevel;
    final transient EntryFactory entryFactory;
    Set<Map.Entry<K, V>> entrySet;
    final long expireAfterAccessNanos;
    final long expireAfterWriteNanos;
    final Equivalence<Object> keyEquivalence;
    Set<K> keySet;
    final Strength keyStrength;
    final int maximumSize;
    final MapMaker.RemovalListener<K, V> removalListener;
    final Queue<MapMaker.RemovalNotification<K, V>> removalNotificationQueue;
    final transient int segmentMask;
    final transient int segmentShift;
    final transient Segment<K, V>[] segments;
    final Ticker ticker;
    final Equivalence<Object> valueEquivalence;
    final Strength valueStrength;
    Collection<V> values;

    static {
        logger = Logger.getLogger((String)MapMakerInternalMap.class.getName());
        UNSET = new ValueReference<Object, Object>(){

            @Override
            public void clear(ValueReference<Object, Object> valueReference) {
            }

            @Override
            public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> referenceQueue, ReferenceEntry<Object, Object> referenceEntry) {
                return this;
            }

            @Override
            public Object get() {
                return null;
            }

            @Override
            public ReferenceEntry<Object, Object> getEntry() {
                return null;
            }

            @Override
            public boolean isComputingReference() {
                return false;
            }

            @Override
            public Object waitForValue() {
                return null;
            }
        };
        DISCARDING_QUEUE = new AbstractQueue<Object>(){

            public Iterator<Object> iterator() {
                return Iterators.emptyIterator();
            }

            public boolean offer(Object object) {
                return true;
            }

            public Object peek() {
                return null;
            }

            public Object poll() {
                return null;
            }

            public int size() {
                return 0;
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    MapMakerInternalMap(MapMaker mapMaker) {
        int n;
        int n2;
        this.concurrencyLevel = Math.min((int)mapMaker.getConcurrencyLevel(), (int)65536);
        this.keyStrength = mapMaker.getKeyStrength();
        this.valueStrength = mapMaker.getValueStrength();
        this.keyEquivalence = mapMaker.getKeyEquivalence();
        this.valueEquivalence = mapMaker.getValueEquivalence();
        this.maximumSize = mapMaker.maximumSize;
        this.expireAfterAccessNanos = mapMaker.getExpireAfterAccessNanos();
        this.expireAfterWriteNanos = mapMaker.getExpireAfterWriteNanos();
        this.entryFactory = EntryFactory.getFactory(this.keyStrength, this.expires(), this.evictsBySize());
        this.ticker = mapMaker.getTicker();
        this.removalListener = mapMaker.getRemovalListener();
        ConcurrentLinkedQueue concurrentLinkedQueue = this.removalListener == GenericMapMaker.NullListener.INSTANCE ? MapMakerInternalMap.discardingQueue() : new ConcurrentLinkedQueue();
        this.removalNotificationQueue = concurrentLinkedQueue;
        int n3 = n = Math.min((int)mapMaker.getInitialCapacity(), (int)1073741824);
        if (this.evictsBySize()) {
            n3 = Math.min((int)n, (int)this.maximumSize);
        }
        int n4 = 0;
        for (n = 1; !(n >= this.concurrencyLevel || this.evictsBySize() && n * 2 > this.maximumSize); ++n4, n <<= 1) {
        }
        this.segmentShift = 32 - n4;
        this.segmentMask = n - 1;
        this.segments = this.newSegmentArray(n);
        n4 = n2 = n3 / n;
        if (n2 * n < n3) {
            n4 = n2 + 1;
        }
        for (n3 = 1; n3 < n4; n3 <<= 1) {
        }
        if (this.evictsBySize()) {
            n2 = this.maximumSize / n + 1;
            int n5 = this.maximumSize;
            n4 = 0;
            while (n4 < this.segments.length) {
                int n6 = n2;
                if (n4 == n5 % n) {
                    n6 = n2 - 1;
                }
                this.segments[n4] = this.createSegment(n3, n6);
                ++n4;
                n2 = n6;
            }
            return;
        }
        n = 0;
        while (n < this.segments.length) {
            this.segments[n] = this.createSegment(n3, -1);
            ++n;
        }
    }

    static <K, V> void connectEvictables(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextEvictable(referenceEntry2);
        referenceEntry2.setPreviousEvictable(referenceEntry);
    }

    static <K, V> void connectExpirables(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextExpirable(referenceEntry2);
        referenceEntry2.setPreviousExpirable(referenceEntry);
    }

    static <E> Queue<E> discardingQueue() {
        return DISCARDING_QUEUE;
    }

    static <K, V> ReferenceEntry<K, V> nullEntry() {
        return NullEntry.INSTANCE;
    }

    static <K, V> void nullifyEvictable(ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry<K, V> referenceEntry2 = MapMakerInternalMap.nullEntry();
        referenceEntry.setNextEvictable(referenceEntry2);
        referenceEntry.setPreviousEvictable(referenceEntry2);
    }

    static <K, V> void nullifyExpirable(ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry<K, V> referenceEntry2 = MapMakerInternalMap.nullEntry();
        referenceEntry.setNextExpirable(referenceEntry2);
        referenceEntry.setPreviousExpirable(referenceEntry2);
    }

    static int rehash(int n) {
        n += n << 15 ^ -12931;
        n ^= n >>> 10;
        n += n << 3;
        n ^= n >>> 6;
        n += (n << 2) + (n << 14);
        return n >>> 16 ^ n;
    }

    static <K, V> ValueReference<K, V> unset() {
        return UNSET;
    }

    public void clear() {
        Segment<K, V>[] arrsegment = this.segments;
        int n = arrsegment.length;
        for (int i = 0; i < n; ++i) {
            arrsegment[i].clear();
        }
    }

    public boolean containsKey(Object object) {
        if (object == null) {
            return false;
        }
        int n = this.hash(object);
        return this.segmentFor(n).containsKey(object, n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean containsValue(Object object) {
        if (object == null) {
            return false;
        }
        Segment<K, V>[] arrsegment = this.segments;
        long l = -1;
        int n = 0;
        while (n < 3) {
            long l2 = 0;
            for (Segment segment : arrsegment) {
                int n2 = segment.count;
                AtomicReferenceArray atomicReferenceArray = segment.table;
                for (n2 = 0; n2 < atomicReferenceArray.length(); ++n2) {
                    for (ReferenceEntry referenceEntry = (ReferenceEntry)atomicReferenceArray.get((int)n2); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                        V v = segment.getLiveValue(referenceEntry);
                        if (v == null || !this.valueEquivalence.equivalent(object, v)) continue;
                        return true;
                    }
                }
                l2 += (long)segment.modCount;
            }
            if (l2 == l) {
                return false;
            }
            ++n;
            l = l2;
        }
        return false;
    }

    ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        return this.segmentFor(referenceEntry.getHash()).copyEntry(referenceEntry, referenceEntry2);
    }

    Segment<K, V> createSegment(int n, int n2) {
        return new Segment(this, n, n2);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        EntrySet entrySet = this.entrySet;
        if (entrySet != null) {
            return entrySet;
        }
        this.entrySet = entrySet = new EntrySet();
        return entrySet;
    }

    boolean evictsBySize() {
        if (this.maximumSize != -1) {
            return true;
        }
        return false;
    }

    boolean expires() {
        if (this.expiresAfterWrite() || this.expiresAfterAccess()) {
            return true;
        }
        return false;
    }

    boolean expiresAfterAccess() {
        if (this.expireAfterAccessNanos > 0) {
            return true;
        }
        return false;
    }

    boolean expiresAfterWrite() {
        if (this.expireAfterWriteNanos > 0) {
            return true;
        }
        return false;
    }

    public V get(Object object) {
        if (object == null) {
            return null;
        }
        int n = this.hash(object);
        return this.segmentFor(n).get(object, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    V getLiveValue(ReferenceEntry<K, V> referenceEntry) {
        V v;
        if (referenceEntry.getKey() == null) {
            v = null;
            return v;
        } else {
            V v2 = referenceEntry.getValueReference().get();
            if (v2 == null) {
                return null;
            }
            v = v2;
            if (!this.expires()) return v;
            {
                v = v2;
                if (!this.isExpired(referenceEntry)) return v;
                return null;
            }
        }
    }

    int hash(Object object) {
        return MapMakerInternalMap.rehash(this.keyEquivalence.hash(object));
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isEmpty() {
        int n;
        long l = 0;
        Segment<K, V>[] arrsegment = this.segments;
        for (n = 0; n < arrsegment.length; l += (long)arrsegment[n].modCount, ++n) {
            if (arrsegment[n].count != 0) return false;
            {
                continue;
            }
        }
        if (l == 0) return true;
        for (n = 0; n < arrsegment.length; l -= (long)arrsegment[n].modCount, ++n) {
            if (arrsegment[n].count != 0) return false;
            {
                continue;
            }
        }
        if (l == 0) return true;
        return false;
    }

    boolean isExpired(ReferenceEntry<K, V> referenceEntry) {
        return this.isExpired(referenceEntry, this.ticker.read());
    }

    boolean isExpired(ReferenceEntry<K, V> referenceEntry, long l) {
        if (l - referenceEntry.getExpirationTime() > 0) {
            return true;
        }
        return false;
    }

    boolean isLive(ReferenceEntry<K, V> referenceEntry) {
        if (this.segmentFor(referenceEntry.getHash()).getLiveValue(referenceEntry) != null) {
            return true;
        }
        return false;
    }

    public Set<K> keySet() {
        KeySet keySet = this.keySet;
        if (keySet != null) {
            return keySet;
        }
        this.keySet = keySet = new KeySet();
        return keySet;
    }

    ReferenceEntry<K, V> newEntry(K k, int n, ReferenceEntry<K, V> referenceEntry) {
        return this.segmentFor(n).newEntry(k, n, referenceEntry);
    }

    final Segment<K, V>[] newSegmentArray(int n) {
        return new Segment[n];
    }

    ValueReference<K, V> newValueReference(ReferenceEntry<K, V> referenceEntry, V v) {
        int n = referenceEntry.getHash();
        return this.valueStrength.referenceValue(this.segmentFor(n), referenceEntry, v);
    }

    void processPendingNotifications() {
        MapMaker.RemovalNotification<K, V> removalNotification;
        while ((removalNotification = this.removalNotificationQueue.poll()) != null) {
            try {
                this.removalListener.onRemoval(removalNotification);
            }
            catch (Exception var1_2) {
                logger.log(Level.WARNING, "Exception thrown by removal listener", (Throwable)var1_2);
            }
        }
    }

    public V put(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int n = this.hash(k);
        return this.segmentFor(n).put(k, n, v, false);
    }

    public void putAll(Map<? extends K, ? extends V> object) {
        for (Map.Entry entry : object.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    public V putIfAbsent(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int n = this.hash(k);
        return this.segmentFor(n).put(k, n, v, true);
    }

    void reclaimKey(ReferenceEntry<K, V> referenceEntry) {
        int n = referenceEntry.getHash();
        this.segmentFor(n).reclaimKey(referenceEntry, n);
    }

    void reclaimValue(ValueReference<K, V> valueReference) {
        ReferenceEntry<K, V> referenceEntry = valueReference.getEntry();
        int n = referenceEntry.getHash();
        this.segmentFor(n).reclaimValue(referenceEntry.getKey(), n, valueReference);
    }

    public V remove(Object object) {
        if (object == null) {
            return null;
        }
        int n = this.hash(object);
        return this.segmentFor(n).remove(object, n);
    }

    public boolean remove(Object object, Object object2) {
        if (object == null || object2 == null) {
            return false;
        }
        int n = this.hash(object);
        return this.segmentFor(n).remove(object, n, object2);
    }

    public V replace(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int n = this.hash(k);
        return this.segmentFor(n).replace(k, n, v);
    }

    public boolean replace(K k, V v, V v2) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v2);
        if (v == null) {
            return false;
        }
        int n = this.hash(k);
        return this.segmentFor(n).replace(k, n, v, v2);
    }

    Segment<K, V> segmentFor(int n) {
        return this.segments[n >>> this.segmentShift & this.segmentMask];
    }

    public int size() {
        Segment<K, V>[] arrsegment = this.segments;
        long l = 0;
        for (int i = 0; i < arrsegment.length; ++i) {
            l += (long)arrsegment[i].count;
        }
        return Ints.saturatedCast(l);
    }

    boolean usesKeyReferences() {
        if (this.keyStrength != Strength.STRONG) {
            return true;
        }
        return false;
    }

    boolean usesValueReferences() {
        if (this.valueStrength != Strength.STRONG) {
            return true;
        }
        return false;
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        this.values = collection = new Values();
        return collection;
    }

    Object writeReplace() {
        return new SerializationProxy<K, V>(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this);
    }

    static abstract class AbstractReferenceEntry<K, V>
    implements ReferenceEntry<K, V> {
        AbstractReferenceEntry() {
        }

        @Override
        public long getExpirationTime() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getHash() {
            throw new UnsupportedOperationException();
        }

        @Override
        public K getKey() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ValueReference<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setExpirationTime(long l) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setValueReference(ValueReference<K, V> valueReference) {
            throw new UnsupportedOperationException();
        }
    }

    static abstract class AbstractSerializationProxy<K, V>
    extends ForwardingConcurrentMap<K, V>
    implements Serializable {
        private static final long serialVersionUID = 3;
        final int concurrencyLevel;
        transient ConcurrentMap<K, V> delegate;
        final long expireAfterAccessNanos;
        final long expireAfterWriteNanos;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final int maximumSize;
        final MapMaker.RemovalListener<? super K, ? super V> removalListener;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;

        AbstractSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long l, long l2, int n, int n2, MapMaker.RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> concurrentMap) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.expireAfterWriteNanos = l;
            this.expireAfterAccessNanos = l2;
            this.maximumSize = n;
            this.concurrencyLevel = n2;
            this.removalListener = removalListener;
            this.delegate = concurrentMap;
        }

        @Override
        protected ConcurrentMap<K, V> delegate() {
            return this.delegate;
        }

        void readEntries(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            Object object;
            while ((object = objectInputStream.readObject()) != null) {
                Object object2 = objectInputStream.readObject();
                this.delegate.put(object, object2);
            }
            return;
        }

        MapMaker readMapMaker(ObjectInputStream object) throws IOException {
            int n = object.readInt();
            object = new MapMaker().initialCapacity(n).setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).valueEquivalence(this.valueEquivalence).concurrencyLevel(this.concurrencyLevel);
            object.removalListener(this.removalListener);
            if (this.expireAfterWriteNanos > 0) {
                object.expireAfterWrite(this.expireAfterWriteNanos, TimeUnit.NANOSECONDS);
            }
            if (this.expireAfterAccessNanos > 0) {
                object.expireAfterAccess(this.expireAfterAccessNanos, TimeUnit.NANOSECONDS);
            }
            if (this.maximumSize != -1) {
                object.maximumSize(this.maximumSize);
            }
            return object;
        }

        void writeMapTo(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeInt(this.delegate.size());
            for (Map.Entry entry : this.delegate.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject((Object)null);
        }
    }

    static enum EntryFactory {
        STRONG{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new StrongEntry<K, V>(k, n, referenceEntry);
            }
        }
        ,
        STRONG_EXPIRABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyExpirableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new StrongExpirableEntry<K, V>(k, n, referenceEntry);
            }
        }
        ,
        STRONG_EVICTABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyEvictableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new StrongEvictableEntry<K, V>(k, n, referenceEntry);
            }
        }
        ,
        STRONG_EXPIRABLE_EVICTABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyExpirableEntry(referenceEntry, object);
                this.copyEvictableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new StrongExpirableEvictableEntry<K, V>(k, n, referenceEntry);
            }
        }
        ,
        SOFT{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new SoftEntry(segment.keyReferenceQueue, k, n, referenceEntry);
            }
        }
        ,
        SOFT_EXPIRABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyExpirableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new SoftExpirableEntry(segment.keyReferenceQueue, k, n, referenceEntry);
            }
        }
        ,
        SOFT_EVICTABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyEvictableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new SoftEvictableEntry(segment.keyReferenceQueue, k, n, referenceEntry);
            }
        }
        ,
        SOFT_EXPIRABLE_EVICTABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyExpirableEntry(referenceEntry, object);
                this.copyEvictableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new SoftExpirableEvictableEntry(segment.keyReferenceQueue, k, n, referenceEntry);
            }
        }
        ,
        WEAK{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new WeakEntry(segment.keyReferenceQueue, k, n, referenceEntry);
            }
        }
        ,
        WEAK_EXPIRABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyExpirableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new WeakExpirableEntry(segment.keyReferenceQueue, k, n, referenceEntry);
            }
        }
        ,
        WEAK_EVICTABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyEvictableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new WeakEvictableEntry(segment.keyReferenceQueue, k, n, referenceEntry);
            }
        }
        ,
        WEAK_EXPIRABLE_EVICTABLE{

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> object, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                object = super.copyEntry(object, referenceEntry, referenceEntry2);
                this.copyExpirableEntry(referenceEntry, object);
                this.copyEvictableEntry(referenceEntry, object);
                return object;
            }

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int n, ReferenceEntry<K, V> referenceEntry) {
                return new WeakExpirableEvictableEntry(segment.keyReferenceQueue, k, n, referenceEntry);
            }
        };
        
        static final EntryFactory[][] factories;

        static {
            EntryFactory entryFactory = STRONG;
            EntryFactory entryFactory2 = STRONG_EXPIRABLE;
            EntryFactory entryFactory3 = STRONG_EVICTABLE;
            EntryFactory entryFactory4 = STRONG_EXPIRABLE_EVICTABLE;
            EntryFactory entryFactory5 = SOFT;
            EntryFactory entryFactory6 = SOFT_EXPIRABLE;
            EntryFactory entryFactory7 = SOFT_EVICTABLE;
            EntryFactory entryFactory8 = SOFT_EXPIRABLE_EVICTABLE;
            EntryFactory entryFactory9 = WEAK;
            EntryFactory entryFactory10 = WEAK_EXPIRABLE;
            EntryFactory entryFactory11 = WEAK_EVICTABLE;
            EntryFactory entryFactory12 = WEAK_EXPIRABLE_EVICTABLE;
            factories = new EntryFactory[][]{{entryFactory, entryFactory2, entryFactory3, entryFactory4}, {entryFactory5, entryFactory6, entryFactory7, entryFactory8}, {entryFactory9, entryFactory10, entryFactory11, entryFactory12}};
        }

        private EntryFactory() {
        }

        /*
         * Enabled aggressive block sorting
         */
        static EntryFactory getFactory(Strength strength, boolean bl, boolean bl2) {
            int n = 0;
            int n2 = bl ? 1 : 0;
            if (bl2) {
                n = 2;
            }
            return factories[strength.ordinal()][n2 | n];
        }

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            return this.newEntry(segment, referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry2);
        }

        <K, V> void copyEvictableEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            MapMakerInternalMap.connectEvictables(referenceEntry.getPreviousEvictable(), referenceEntry2);
            MapMakerInternalMap.connectEvictables(referenceEntry2, referenceEntry.getNextEvictable());
            MapMakerInternalMap.nullifyEvictable(referenceEntry);
        }

        <K, V> void copyExpirableEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setExpirationTime(referenceEntry.getExpirationTime());
            MapMakerInternalMap.connectExpirables(referenceEntry.getPreviousExpirable(), referenceEntry2);
            MapMakerInternalMap.connectExpirables(referenceEntry2, referenceEntry.getNextExpirable());
            MapMakerInternalMap.nullifyExpirable(referenceEntry);
        }

        abstract <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> var1, K var2, int var3, ReferenceEntry<K, V> var4);

    }

    final class EntryIterator
    extends MapMakerInternalMap<K, V>
    implements Iterator<Map.Entry<K, V>> {
        EntryIterator() {
            super();
        }

        @Override
        public Map.Entry<K, V> next() {
            return this.nextEntry();
        }
    }

    final class EntrySet
    extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public boolean contains(Object object) {
            Object object2;
            if (!(object instanceof Map.Entry) || (object2 = (object = (Map.Entry)object).getKey()) == null || (object2 = MapMakerInternalMap.this.get(object2)) == null || !MapMakerInternalMap.this.valueEquivalence.equivalent(object.getValue(), object2)) {
                return false;
            }
            return true;
        }

        @Override
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public boolean remove(Object object) {
            Object object2;
            if (!(object instanceof Map.Entry) || (object2 = (object = (Map.Entry)object).getKey()) == null || !MapMakerInternalMap.this.remove(object2, object.getValue())) {
                return false;
            }
            return true;
        }

        @Override
        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    static final class EvictionQueue<K, V>
    extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head;

        EvictionQueue() {
            this.head = new AbstractReferenceEntry<K, V>(){
                ReferenceEntry<K, V> nextEvictable;
                ReferenceEntry<K, V> previousEvictable;

                @Override
                public ReferenceEntry<K, V> getNextEvictable() {
                    return this.nextEvictable;
                }

                @Override
                public ReferenceEntry<K, V> getPreviousEvictable() {
                    return this.previousEvictable;
                }

                @Override
                public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
                    this.nextEvictable = referenceEntry;
                }

                @Override
                public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
                    this.previousEvictable = referenceEntry;
                }
            };
        }

        public void clear() {
            ReferenceEntry<K, V> referenceEntry = this.head.getNextEvictable();
            while (referenceEntry != this.head) {
                ReferenceEntry<K, V> referenceEntry2 = referenceEntry.getNextEvictable();
                MapMakerInternalMap.nullifyEvictable(referenceEntry);
                referenceEntry = referenceEntry2;
            }
            this.head.setNextEvictable(this.head);
            this.head.setPreviousEvictable(this.head);
        }

        public boolean contains(Object object) {
            if (((ReferenceEntry)object).getNextEvictable() != NullEntry.INSTANCE) {
                return true;
            }
            return false;
        }

        public boolean isEmpty() {
            if (this.head.getNextEvictable() == this.head) {
                return true;
            }
            return false;
        }

        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractLinkedIterator<ReferenceEntry<K, V>>((ReferenceEntry)this.peek()){

                @Override
                protected ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> referenceEntry2;
                    referenceEntry = referenceEntry2 = referenceEntry.getNextEvictable();
                    if (referenceEntry2 == EvictionQueue.this.head) {
                        referenceEntry = null;
                    }
                    return referenceEntry;
                }
            };
        }

        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            MapMakerInternalMap.connectEvictables(referenceEntry.getPreviousEvictable(), referenceEntry.getNextEvictable());
            MapMakerInternalMap.connectEvictables(this.head.getPreviousEvictable(), referenceEntry);
            MapMakerInternalMap.connectEvictables(referenceEntry, this.head);
            return true;
        }

        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> referenceEntry;
            ReferenceEntry<K, V> referenceEntry2 = referenceEntry = this.head.getNextEvictable();
            if (referenceEntry == this.head) {
                referenceEntry2 = null;
            }
            return referenceEntry2;
        }

        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> referenceEntry = this.head.getNextEvictable();
            if (referenceEntry == this.head) {
                return null;
            }
            this.remove(referenceEntry);
            return referenceEntry;
        }

        public boolean remove(Object object) {
            object = (ReferenceEntry)object;
            ReferenceEntry referenceEntry = object.getPreviousEvictable();
            ReferenceEntry referenceEntry2 = object.getNextEvictable();
            MapMakerInternalMap.connectEvictables(referenceEntry, referenceEntry2);
            MapMakerInternalMap.nullifyEvictable(object);
            if (referenceEntry2 != NullEntry.INSTANCE) {
                return true;
            }
            return false;
        }

        public int size() {
            int n = 0;
            for (ReferenceEntry<K, V> referenceEntry = this.head.getNextEvictable(); referenceEntry != this.head; referenceEntry = referenceEntry.getNextEvictable()) {
                ++n;
            }
            return n;
        }

    }

    static final class ExpirationQueue<K, V>
    extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head;

        ExpirationQueue() {
            this.head = new AbstractReferenceEntry<K, V>(){
                ReferenceEntry<K, V> nextExpirable;
                ReferenceEntry<K, V> previousExpirable;

                @Override
                public long getExpirationTime() {
                    return Long.MAX_VALUE;
                }

                @Override
                public ReferenceEntry<K, V> getNextExpirable() {
                    return this.nextExpirable;
                }

                @Override
                public ReferenceEntry<K, V> getPreviousExpirable() {
                    return this.previousExpirable;
                }

                @Override
                public void setExpirationTime(long l) {
                }

                @Override
                public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
                    this.nextExpirable = referenceEntry;
                }

                @Override
                public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
                    this.previousExpirable = referenceEntry;
                }
            };
        }

        public void clear() {
            ReferenceEntry<K, V> referenceEntry = this.head.getNextExpirable();
            while (referenceEntry != this.head) {
                ReferenceEntry<K, V> referenceEntry2 = referenceEntry.getNextExpirable();
                MapMakerInternalMap.nullifyExpirable(referenceEntry);
                referenceEntry = referenceEntry2;
            }
            this.head.setNextExpirable(this.head);
            this.head.setPreviousExpirable(this.head);
        }

        public boolean contains(Object object) {
            if (((ReferenceEntry)object).getNextExpirable() != NullEntry.INSTANCE) {
                return true;
            }
            return false;
        }

        public boolean isEmpty() {
            if (this.head.getNextExpirable() == this.head) {
                return true;
            }
            return false;
        }

        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractLinkedIterator<ReferenceEntry<K, V>>((ReferenceEntry)this.peek()){

                @Override
                protected ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> referenceEntry2;
                    referenceEntry = referenceEntry2 = referenceEntry.getNextExpirable();
                    if (referenceEntry2 == ExpirationQueue.this.head) {
                        referenceEntry = null;
                    }
                    return referenceEntry;
                }
            };
        }

        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            MapMakerInternalMap.connectExpirables(referenceEntry.getPreviousExpirable(), referenceEntry.getNextExpirable());
            MapMakerInternalMap.connectExpirables(this.head.getPreviousExpirable(), referenceEntry);
            MapMakerInternalMap.connectExpirables(referenceEntry, this.head);
            return true;
        }

        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> referenceEntry;
            ReferenceEntry<K, V> referenceEntry2 = referenceEntry = this.head.getNextExpirable();
            if (referenceEntry == this.head) {
                referenceEntry2 = null;
            }
            return referenceEntry2;
        }

        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> referenceEntry = this.head.getNextExpirable();
            if (referenceEntry == this.head) {
                return null;
            }
            this.remove(referenceEntry);
            return referenceEntry;
        }

        public boolean remove(Object object) {
            object = (ReferenceEntry)object;
            ReferenceEntry referenceEntry = object.getPreviousExpirable();
            ReferenceEntry referenceEntry2 = object.getNextExpirable();
            MapMakerInternalMap.connectExpirables(referenceEntry, referenceEntry2);
            MapMakerInternalMap.nullifyExpirable(object);
            if (referenceEntry2 != NullEntry.INSTANCE) {
                return true;
            }
            return false;
        }

        public int size() {
            int n = 0;
            for (ReferenceEntry<K, V> referenceEntry = this.head.getNextExpirable(); referenceEntry != this.head; referenceEntry = referenceEntry.getNextExpirable()) {
                ++n;
            }
            return n;
        }

    }

    abstract class HashIterator {
        Segment<K, V> currentSegment;
        AtomicReferenceArray<ReferenceEntry<K, V>> currentTable;
        MapMakerInternalMap<K, V> lastReturned;
        ReferenceEntry<K, V> nextEntry;
        MapMakerInternalMap<K, V> nextExternal;
        int nextSegmentIndex;
        int nextTableIndex;

        HashIterator() {
            this.nextSegmentIndex = MapMakerInternalMap.this.segments.length - 1;
            this.nextTableIndex = -1;
            this.advance();
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        final void advance() {
            this.nextExternal = null;
            if (this.nextInChain()) {
                return;
            }
            if (this.nextInTable() != false) return;
            do lbl-1000: // 3 sources:
            {
                if (this.nextSegmentIndex < 0) return;
                var2_2 = MapMakerInternalMap.this.segments;
                var1_1 = this.nextSegmentIndex;
                this.nextSegmentIndex = var1_1 - 1;
                this.currentSegment = var2_2[var1_1];
                if (this.currentSegment.count == 0) ** GOTO lbl-1000
                this.currentTable = this.currentSegment.table;
                this.nextTableIndex = this.currentTable.length() - 1;
            } while (!this.nextInTable());
        }

        boolean advanceTo(ReferenceEntry<K, V> referenceEntry) {
            block4 : {
                K k = referenceEntry.getKey();
                referenceEntry = MapMakerInternalMap.this.getLiveValue(referenceEntry);
                if (referenceEntry == null) break block4;
                this.nextExternal = new WriteThroughEntry(k, referenceEntry);
                return true;
            }
            this.currentSegment.postReadCleanup();
            return false;
            finally {
                this.currentSegment.postReadCleanup();
            }
        }

        public boolean hasNext() {
            if (this.nextExternal != null) {
                return true;
            }
            return false;
        }

        MapMakerInternalMap<K, V> nextEntry() {
            if (this.nextExternal == null) {
                throw new NoSuchElementException();
            }
            this.lastReturned = this.nextExternal;
            this.advance();
            return this.lastReturned;
        }

        boolean nextInChain() {
            if (this.nextEntry != null) {
                this.nextEntry = this.nextEntry.getNext();
                while (this.nextEntry != null) {
                    if (this.advanceTo(this.nextEntry)) {
                        return true;
                    }
                    this.nextEntry = this.nextEntry.getNext();
                }
            }
            return false;
        }

        boolean nextInTable() {
            while (this.nextTableIndex >= 0) {
                Object object = this.currentTable;
                int n = this.nextTableIndex;
                this.nextTableIndex = n - 1;
                this.nextEntry = object = (ReferenceEntry)object.get(n);
                if (object == null || !this.advanceTo(this.nextEntry) && !this.nextInChain()) continue;
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void remove() {
            boolean bl = this.lastReturned != null;
            Preconditions.checkState(bl);
            MapMakerInternalMap.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    final class KeyIterator
    extends MapMakerInternalMap<K, V>
    implements Iterator<K> {
        KeyIterator() {
        }

        @Override
        public K next() {
            return this.nextEntry().getKey();
        }
    }

    final class KeySet
    extends AbstractSet<K> {
        KeySet() {
        }

        @Override
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        @Override
        public boolean contains(Object object) {
            return MapMakerInternalMap.this.containsKey(object);
        }

        @Override
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public boolean remove(Object object) {
            if (MapMakerInternalMap.this.remove(object) != null) {
                return true;
            }
            return false;
        }

        @Override
        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    private static enum NullEntry implements ReferenceEntry<Object, Object>
    {
        INSTANCE;
        

        private NullEntry() {
        }

        @Override
        public long getExpirationTime() {
            return 0;
        }

        @Override
        public int getHash() {
            return 0;
        }

        @Override
        public Object getKey() {
            return null;
        }

        @Override
        public ReferenceEntry<Object, Object> getNext() {
            return null;
        }

        @Override
        public ReferenceEntry<Object, Object> getNextEvictable() {
            return this;
        }

        @Override
        public ReferenceEntry<Object, Object> getNextExpirable() {
            return this;
        }

        @Override
        public ReferenceEntry<Object, Object> getPreviousEvictable() {
            return this;
        }

        @Override
        public ReferenceEntry<Object, Object> getPreviousExpirable() {
            return this;
        }

        @Override
        public ValueReference<Object, Object> getValueReference() {
            return null;
        }

        @Override
        public void setExpirationTime(long l) {
        }

        @Override
        public void setNextEvictable(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override
        public void setNextExpirable(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override
        public void setValueReference(ValueReference<Object, Object> valueReference) {
        }
    }

    static interface ReferenceEntry<K, V> {
        public long getExpirationTime();

        public int getHash();

        public K getKey();

        public ReferenceEntry<K, V> getNext();

        public ReferenceEntry<K, V> getNextEvictable();

        public ReferenceEntry<K, V> getNextExpirable();

        public ReferenceEntry<K, V> getPreviousEvictable();

        public ReferenceEntry<K, V> getPreviousExpirable();

        public ValueReference<K, V> getValueReference();

        public void setExpirationTime(long var1);

        public void setNextEvictable(ReferenceEntry<K, V> var1);

        public void setNextExpirable(ReferenceEntry<K, V> var1);

        public void setPreviousEvictable(ReferenceEntry<K, V> var1);

        public void setPreviousExpirable(ReferenceEntry<K, V> var1);

        public void setValueReference(ValueReference<K, V> var1);
    }

    static class Segment<K, V>
    extends ReentrantLock {
        volatile int count;
        final Queue<ReferenceEntry<K, V>> evictionQueue;
        final Queue<ReferenceEntry<K, V>> expirationQueue;
        final ReferenceQueue<K> keyReferenceQueue;
        final MapMakerInternalMap<K, V> map;
        final int maxSegmentSize;
        int modCount;
        final AtomicInteger readCount;
        final Queue<ReferenceEntry<K, V>> recencyQueue;
        volatile AtomicReferenceArray<ReferenceEntry<K, V>> table;
        int threshold;
        final ReferenceQueue<V> valueReferenceQueue;

        /*
         * Enabled aggressive block sorting
         */
        Segment(MapMakerInternalMap<K, V> object, int n, int n2) {
            Object var5_4 = null;
            this.readCount = new AtomicInteger();
            this.map = object;
            this.maxSegmentSize = n2;
            this.initTable(this.newEntryArray(n));
            Object object2 = object.usesKeyReferences() ? new ReferenceQueue() : null;
            this.keyReferenceQueue = object2;
            object2 = var5_4;
            if (object.usesValueReferences()) {
                object2 = new ReferenceQueue();
            }
            this.valueReferenceQueue = object2;
            object2 = object.evictsBySize() || object.expiresAfterAccess() ? new ConcurrentLinkedQueue() : MapMakerInternalMap.discardingQueue();
            this.recencyQueue = object2;
            object2 = object.evictsBySize() ? new EvictionQueue() : MapMakerInternalMap.discardingQueue();
            this.evictionQueue = object2;
            object = object.expires() ? new ExpirationQueue() : MapMakerInternalMap.discardingQueue();
            this.expirationQueue = object;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        void clear() {
            if (this.count != 0) {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray;
                int n;
                block8 : {
                    this.lock();
                    atomicReferenceArray = this.table;
                    if (this.map.removalNotificationQueue == MapMakerInternalMap.DISCARDING_QUEUE) break block8;
                    for (n = 0; n < atomicReferenceArray.length(); ++n) {
                        for (ReferenceEntry referenceEntry = (ReferenceEntry)atomicReferenceArray.get((int)n); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                            if (referenceEntry.getValueReference().isComputingReference()) continue;
                            this.enqueueNotification(referenceEntry, MapMaker.RemovalCause.EXPLICIT);
                        }
                    }
                }
                for (n = 0; n < atomicReferenceArray.length(); ++n) {
                    atomicReferenceArray.set(n, (Object)null);
                }
                this.clearReferenceQueues();
                this.evictionQueue.clear();
                this.expirationQueue.clear();
                this.readCount.set(0);
                ++this.modCount;
                this.count = 0;
            }
        }

        void clearKeyReferenceQueue() {
            while (this.keyReferenceQueue.poll() != null) {
            }
        }

        void clearReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                this.clearKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                this.clearValueReferenceQueue();
            }
        }

        boolean clearValue(K k, int n, ValueReference<K, V> valueReference) {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray;
            int n2;
            this.lock();
            try {
                atomicReferenceArray = this.table;
                n2 = n & atomicReferenceArray.length() - 1;
            }
            catch (Throwable var1_2) {
                this.unlock();
                this.postWriteCleanup();
                throw var1_2;
            }
            for (ReferenceEntry referenceEntry = referenceEntry2 = (ReferenceEntry)atomicReferenceArray.get((int)n2); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                block6 : {
                    block7 : {
                        ReferenceEntry referenceEntry2;
                        Object k2 = referenceEntry.getKey();
                        if (referenceEntry.getHash() != n || k2 == null) break block6;
                        if (!this.map.keyEquivalence.equivalent(k, k2)) break block6;
                        if (referenceEntry.getValueReference() != valueReference) break block7;
                        atomicReferenceArray.set(n2, this.removeFromChain(referenceEntry2, referenceEntry));
                        this.unlock();
                        this.postWriteCleanup();
                        return true;
                    }
                    this.unlock();
                    this.postWriteCleanup();
                    return false;
                }
                continue;
            }
            this.unlock();
            this.postWriteCleanup();
            return false;
        }

        void clearValueReferenceQueue() {
            while (this.valueReferenceQueue.poll() != null) {
            }
        }

        boolean containsKey(Object referenceEntry, int n) {
            block3 : {
                boolean bl;
                block5 : {
                    block4 : {
                        bl = false;
                        try {
                            if (this.count == 0) break block3;
                            if ((referenceEntry = this.getLiveEntry(referenceEntry, n)) != null) break block4;
                        }
                        catch (Throwable var1_2) {
                            this.postReadCleanup();
                            throw var1_2;
                        }
                        this.postReadCleanup();
                        return false;
                    }
                    referenceEntry = referenceEntry.getValueReference().get();
                    if (referenceEntry == null) break block5;
                    bl = true;
                }
                this.postReadCleanup();
                return bl;
            }
            this.postReadCleanup();
            return false;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        boolean containsValue(Object object) {
            block5 : {
                int n;
                try {
                    if (this.count == 0) break block5;
                    AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                    n = atomicReferenceArray.length();
                }
                catch (Throwable var1_2) {
                    this.postReadCleanup();
                    throw var1_2;
                }
                for (int i = 0; i < n; ++i) {
                    for (ReferenceEntry referenceEntry = (ReferenceEntry)atomicReferenceArray.get((int)i); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                        boolean bl;
                        V v = this.getLiveValue(referenceEntry);
                        if (v == null || !(bl = this.map.valueEquivalence.equivalent(object, v))) {
                            continue;
                        }
                        this.postReadCleanup();
                        return true;
                    }
                }
            }
            this.postReadCleanup();
            return false;
        }

        ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            referenceEntry = this.map.entryFactory.copyEntry(this, referenceEntry, referenceEntry2);
            referenceEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, referenceEntry));
            return referenceEntry;
        }

        void drainKeyReferenceQueue() {
            Object object;
            int n = 0;
            while ((object = this.keyReferenceQueue.poll()) != null) {
                int n2;
                object = (ReferenceEntry)object;
                this.map.reclaimKey((ReferenceEntry<K, V>)object);
                n = n2 = n + 1;
                if (n2 != 16) continue;
            }
        }

        void drainRecencyQueue() {
            ReferenceEntry<K, V> referenceEntry;
            while ((referenceEntry = this.recencyQueue.poll()) != null) {
                if (this.evictionQueue.contains(referenceEntry)) {
                    this.evictionQueue.add(referenceEntry);
                }
                if (!this.map.expiresAfterAccess() || !this.expirationQueue.contains(referenceEntry)) continue;
                this.expirationQueue.add(referenceEntry);
            }
        }

        void drainReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                this.drainKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                this.drainValueReferenceQueue();
            }
        }

        void drainValueReferenceQueue() {
            Object object;
            int n = 0;
            while ((object = this.valueReferenceQueue.poll()) != null) {
                int n2;
                object = (ValueReference)object;
                this.map.reclaimValue((ValueReference<K, V>)object);
                n = n2 = n + 1;
                if (n2 != 16) continue;
            }
        }

        void enqueueNotification(ReferenceEntry<K, V> referenceEntry, MapMaker.RemovalCause removalCause) {
            this.enqueueNotification(referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry.getValueReference().get(), removalCause);
        }

        void enqueueNotification(K object, int n, V v, MapMaker.RemovalCause removalCause) {
            if (this.map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE) {
                object = new MapMaker.RemovalNotification<K, V>(object, v, removalCause);
                this.map.removalNotificationQueue.offer((Object)object);
            }
        }

        boolean evictEntries() {
            if (this.map.evictsBySize() && this.count >= this.maxSegmentSize) {
                this.drainRecencyQueue();
                ReferenceEntry<K, V> referenceEntry = this.evictionQueue.remove();
                if (!this.removeEntry(referenceEntry, referenceEntry.getHash(), MapMaker.RemovalCause.SIZE)) {
                    throw new AssertionError();
                }
                return true;
            }
            return false;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        void expand() {
            var11_1 = this.table;
            var6_2 = var11_1.length();
            if (var6_2 >= 1073741824) {
                return;
            }
            var1_3 = this.count;
            var12_4 = this.newEntryArray(var6_2 << 1);
            this.threshold = var12_4.length() * 3 / 4;
            var7_5 = var12_4.length() - 1;
            var2_6 = 0;
            block0 : do {
                if (var2_6 >= var6_2) {
                    this.table = var12_4;
                    this.count = var1_3;
                    return;
                }
                var9_11 = (ReferenceEntry<K, V>)var11_1.get(var2_6);
                var3_7 = var1_3;
                if (var9_11 != null) {
                    var3_7 = var9_11.getHash() & var7_5;
                    if (var8_10 == null) {
                        var12_4.set(var3_7, (Object)var9_11);
                        var3_7 = var1_3;
                    } else {
                        var10_12 = var9_11;
                        for (var8_10 = var9_11.getNext(); var8_10 != null; var8_10 = var8_10.getNext()) {
                            var5_9 = var8_10.getHash() & var7_5;
                            var4_8 = var3_7;
                            if (var5_9 != var3_7) {
                                var4_8 = var5_9;
                                var10_12 = var8_10;
                            }
                            var3_7 = var4_8;
                        }
                        var12_4.set(var3_7, (Object)var10_12);
                        break;
                    }
                }
                do {
                    ++var2_6;
                    var1_3 = var3_7;
                    continue block0;
                    break;
                } while (true);
                break;
            } while (true);
            do {
                var3_7 = var1_3--;
                if (var9_11 == var10_12) ** continue;
                if (this.isCollected(var9_11)) {
                    this.removeCollectedEntry(var9_11);
                } else {
                    var3_7 = var9_11.getHash() & var7_5;
                    var12_4.set(var3_7, this.copyEntry(var9_11, (ReferenceEntry)var12_4.get(var3_7)));
                }
                var9_11 = var9_11.getNext();
            } while (true);
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        void expireEntries() {
            ReferenceEntry<K, V> referenceEntry;
            this.drainRecencyQueue();
            if (this.expirationQueue.isEmpty()) {
                return;
            }
            long l = this.map.ticker.read();
            do {
                if ((referenceEntry = this.expirationQueue.peek()) == null) return;
                if (!this.map.isExpired(referenceEntry, l)) return;
            } while (this.removeEntry(referenceEntry, referenceEntry.getHash(), MapMaker.RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        V get(Object referenceEntry, int n) {
            block8 : {
                referenceEntry = this.getLiveEntry(referenceEntry, n);
                if (referenceEntry != null) break block8;
                this.postReadCleanup();
                return null;
            }
            try {
                V v = referenceEntry.getValueReference().get();
                if (v != null) {
                    this.recordRead(referenceEntry);
                    do {
                        return v;
                        break;
                    } while (true);
                }
                this.tryDrainReferenceQueues();
                return v;
            }
            finally {
                this.postReadCleanup();
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        ReferenceEntry<K, V> getEntry(Object object, int n) {
            if (this.count != 0) {
                for (ReferenceEntry<K, V> referenceEntry = this.getFirst((int)n); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                    if (referenceEntry.getHash() != n) continue;
                    K k = referenceEntry.getKey();
                    if (k == null) {
                        this.tryDrainReferenceQueues();
                        continue;
                    }
                    if (!this.map.keyEquivalence.equivalent(object, k)) continue;
                    return referenceEntry;
                }
            }
            return null;
        }

        ReferenceEntry<K, V> getFirst(int n) {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            return (ReferenceEntry)atomicReferenceArray.get(atomicReferenceArray.length() - 1 & n);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        ReferenceEntry<K, V> getLiveEntry(Object referenceEntry, int n) {
            ReferenceEntry<K, V> referenceEntry2 = this.getEntry(referenceEntry, n);
            if (referenceEntry2 == null) {
                return null;
            }
            referenceEntry = referenceEntry2;
            if (!this.map.expires()) return referenceEntry;
            referenceEntry = referenceEntry2;
            if (!this.map.isExpired(referenceEntry2)) return referenceEntry;
            this.tryExpireEntries();
            return null;
        }

        /*
         * Enabled aggressive block sorting
         */
        V getLiveValue(ReferenceEntry<K, V> referenceEntry) {
            V v;
            if (referenceEntry.getKey() == null) {
                this.tryDrainReferenceQueues();
                v = null;
                return v;
            } else {
                V v2 = referenceEntry.getValueReference().get();
                if (v2 == null) {
                    this.tryDrainReferenceQueues();
                    return null;
                }
                v = v2;
                if (!this.map.expires()) return v;
                {
                    v = v2;
                    if (!this.map.isExpired(referenceEntry)) return v;
                    {
                        this.tryExpireEntries();
                        return null;
                    }
                }
            }
        }

        void initTable(AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray) {
            this.threshold = atomicReferenceArray.length() * 3 / 4;
            if (this.threshold == this.maxSegmentSize) {
                ++this.threshold;
            }
            this.table = atomicReferenceArray;
        }

        boolean isCollected(ReferenceEntry<K, V> referenceEntry) {
            if (referenceEntry.getKey() == null) {
                return true;
            }
            return this.isCollected(referenceEntry.getValueReference());
        }

        /*
         * Enabled aggressive block sorting
         */
        boolean isCollected(ValueReference<K, V> valueReference) {
            if (valueReference.isComputingReference() || valueReference.get() != null) {
                return false;
            }
            return true;
        }

        ReferenceEntry<K, V> newEntry(K k, int n, ReferenceEntry<K, V> referenceEntry) {
            return this.map.entryFactory.newEntry(this, k, n, referenceEntry);
        }

        AtomicReferenceArray<ReferenceEntry<K, V>> newEntryArray(int n) {
            return new AtomicReferenceArray(n);
        }

        void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                this.runCleanup();
            }
        }

        void postWriteCleanup() {
            this.runUnlockedCleanup();
        }

        void preWriteCleanup() {
            this.runLockedCleanup();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        V put(K referenceEntry, int n, V v, boolean bl) {
            this.lock();
            try {
                ValueReference valueReference;
                int n2;
                this.preWriteCleanup();
                int n3 = n2 = this.count + 1;
                if (n2 > this.threshold) {
                    this.expand();
                    n3 = this.count + 1;
                }
                Object object = this.table;
                n2 = n & object.length() - 1;
                for (Object object2 = valueReference = (ReferenceEntry<K, V>)object.get((int)n2); object2 != null; object2 = object2.getNext()) {
                    Object k = object2.getKey();
                    if (object2.getHash() != n || k == null || !this.map.keyEquivalence.equivalent(referenceEntry, k)) continue;
                    valueReference = object2.getValueReference();
                    object = valueReference.get();
                    if (object == null) {
                        ++this.modCount;
                        this.setValue((ReferenceEntry<K, V>)object2, v);
                        if (!valueReference.isComputingReference()) {
                            this.enqueueNotification(referenceEntry, n, object, MapMaker.RemovalCause.COLLECTED);
                            n3 = this.count;
                        } else if (this.evictEntries()) {
                            n3 = this.count + 1;
                        }
                        this.count = n3;
                        return null;
                    }
                    if (bl) {
                        this.recordLockedRead((ReferenceEntry<K, V>)object2);
                        return (V)object;
                    }
                    ++this.modCount;
                    this.enqueueNotification(referenceEntry, n, object, MapMaker.RemovalCause.REPLACED);
                    this.setValue((ReferenceEntry<K, V>)object2, v);
                    return (V)object;
                }
                ++this.modCount;
                referenceEntry = this.newEntry((K)referenceEntry, n, (ReferenceEntry<K, V>)((Object)valueReference));
                this.setValue(referenceEntry, v);
                object.set(n2, referenceEntry);
                if (this.evictEntries()) {
                    n3 = this.count + 1;
                }
                this.count = n3;
                return null;
            }
            finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        boolean reclaimKey(ReferenceEntry<K, V> referenceEntry, int n) {
            int n2;
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray;
            this.lock();
            try {
                n2 = this.count;
                atomicReferenceArray = this.table;
                n2 = n & atomicReferenceArray.length() - 1;
            }
            catch (Throwable var1_2) {
                throw var1_2;
            }
            finally {
                this.unlock();
                this.postWriteCleanup();
            }
            for (ReferenceEntry referenceEntry2 = referenceEntry3 = (ReferenceEntry)atomicReferenceArray.get((int)n2); referenceEntry2 != null; referenceEntry2 = referenceEntry2.getNext()) {
                if (referenceEntry2 == referenceEntry) {
                    ReferenceEntry referenceEntry3;
                    ++this.modCount;
                    this.enqueueNotification(referenceEntry2.getKey(), n, referenceEntry2.getValueReference().get(), MapMaker.RemovalCause.COLLECTED);
                    referenceEntry = this.removeFromChain(referenceEntry3, referenceEntry2);
                    n = this.count;
                    atomicReferenceArray.set(n2, referenceEntry);
                    this.count = n - 1;
                    return true;
                }
                continue;
            }
            this.unlock();
            this.postWriteCleanup();
            return false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        boolean reclaimValue(K referenceEntry, int n, ValueReference<K, V> valueReference) {
            int n2;
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray;
            boolean bl = false;
            this.lock();
            try {
                n2 = this.count;
                atomicReferenceArray = this.table;
                n2 = n & atomicReferenceArray.length() - 1;
            }
            catch (Throwable var1_2) {
                this.unlock();
                if (this.isHeldByCurrentThread()) throw var1_2;
                this.postWriteCleanup();
                throw var1_2;
            }
            for (ReferenceEntry referenceEntry2 = referenceEntry3 = (ReferenceEntry)atomicReferenceArray.get((int)n2); referenceEntry2 != null; referenceEntry2 = referenceEntry2.getNext()) {
                block6 : {
                    block7 : {
                        ReferenceEntry referenceEntry3;
                        Object k = referenceEntry2.getKey();
                        if (referenceEntry2.getHash() != n || k == null) break block6;
                        if (!this.map.keyEquivalence.equivalent(referenceEntry, k)) break block6;
                        if (referenceEntry2.getValueReference() != valueReference) break block7;
                        ++this.modCount;
                        this.enqueueNotification(referenceEntry, n, valueReference.get(), MapMaker.RemovalCause.COLLECTED);
                        referenceEntry = this.removeFromChain(referenceEntry3, referenceEntry2);
                        n = this.count;
                        atomicReferenceArray.set(n2, referenceEntry);
                        this.count = n - 1;
                        boolean bl2 = true;
                        this.unlock();
                        bl = bl2;
                        if (this.isHeldByCurrentThread()) return bl;
                        this.postWriteCleanup();
                        return bl2;
                    }
                    this.unlock();
                    if (this.isHeldByCurrentThread()) return bl;
                    this.postWriteCleanup();
                    return false;
                }
                continue;
            }
            this.unlock();
            if (this.isHeldByCurrentThread()) return bl;
            this.postWriteCleanup();
            return false;
        }

        void recordExpirationTime(ReferenceEntry<K, V> referenceEntry, long l) {
            referenceEntry.setExpirationTime(this.map.ticker.read() + l);
        }

        void recordLockedRead(ReferenceEntry<K, V> referenceEntry) {
            this.evictionQueue.add(referenceEntry);
            if (this.map.expiresAfterAccess()) {
                this.recordExpirationTime(referenceEntry, this.map.expireAfterAccessNanos);
                this.expirationQueue.add(referenceEntry);
            }
        }

        void recordRead(ReferenceEntry<K, V> referenceEntry) {
            if (this.map.expiresAfterAccess()) {
                this.recordExpirationTime(referenceEntry, this.map.expireAfterAccessNanos);
            }
            this.recencyQueue.add(referenceEntry);
        }

        /*
         * Enabled aggressive block sorting
         */
        void recordWrite(ReferenceEntry<K, V> referenceEntry) {
            this.drainRecencyQueue();
            this.evictionQueue.add(referenceEntry);
            if (this.map.expires()) {
                long l = this.map.expiresAfterAccess() ? this.map.expireAfterAccessNanos : this.map.expireAfterWriteNanos;
                this.recordExpirationTime(referenceEntry, l);
                this.expirationQueue.add(referenceEntry);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        V remove(Object object, int n) {
            this.lock();
            try {
                this.preWriteCleanup();
                int n2 = this.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                n2 = n & atomicReferenceArray.length() - 1;
                for (ReferenceEntry referenceEntry = referenceEntry2 = (ReferenceEntry)atomicReferenceArray.get((int)n2); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                    ReferenceEntry referenceEntry2;
                    Object k = referenceEntry.getKey();
                    if (referenceEntry.getHash() != n || k == null || !this.map.keyEquivalence.equivalent(object, k)) continue;
                    object = referenceEntry.getValueReference();
                    Object v = object.get();
                    if (v != null) {
                        object = MapMaker.RemovalCause.EXPLICIT;
                    } else {
                        if (!this.isCollected((ValueReference<K, V>)object)) {
                            return null;
                        }
                        object = MapMaker.RemovalCause.COLLECTED;
                    }
                    ++this.modCount;
                    this.enqueueNotification(k, n, v, (MapMaker.RemovalCause)((Object)object));
                    object = this.removeFromChain(referenceEntry2, referenceEntry);
                    n = this.count;
                    atomicReferenceArray.set(n2, object);
                    this.count = n - 1;
                    return v;
                }
                return null;
            }
            finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        boolean remove(Object valueReference, int n, Object object) {
            boolean bl = false;
            this.lock();
            try {
                ReferenceEntry referenceEntry;
                this.preWriteCleanup();
                int n2 = this.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                n2 = n & atomicReferenceArray.length() - 1;
                ReferenceEntry referenceEntry2 = referenceEntry = (ReferenceEntry)atomicReferenceArray.get(n2);
                while (referenceEntry2 != null) {
                    Object k = referenceEntry2.getKey();
                    if (referenceEntry2.getHash() == n && k != null && this.map.keyEquivalence.equivalent(valueReference, k)) {
                        valueReference = referenceEntry2.getValueReference();
                        Object v = valueReference.get();
                        if (this.map.valueEquivalence.equivalent(object, v)) {
                            valueReference = MapMaker.RemovalCause.EXPLICIT;
                        } else {
                            if (!this.isCollected(valueReference)) return false;
                            valueReference = MapMaker.RemovalCause.COLLECTED;
                        }
                        ++this.modCount;
                        this.enqueueNotification(k, n, v, (MapMaker.RemovalCause)((Object)valueReference));
                        object = this.removeFromChain(referenceEntry, referenceEntry2);
                        n = this.count;
                        atomicReferenceArray.set(n2, object);
                        this.count = n - 1;
                        object = MapMaker.RemovalCause.EXPLICIT;
                        if (valueReference != object) return bl;
                        bl = true;
                        return bl;
                    }
                    referenceEntry2 = referenceEntry2.getNext();
                }
                return false;
            }
            finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        void removeCollectedEntry(ReferenceEntry<K, V> referenceEntry) {
            this.enqueueNotification(referenceEntry, MapMaker.RemovalCause.COLLECTED);
            this.evictionQueue.remove(referenceEntry);
            this.expirationQueue.remove(referenceEntry);
        }

        boolean removeEntry(ReferenceEntry<K, V> referenceEntry, int n, MapMaker.RemovalCause removalCause) {
            int n2 = this.count;
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            n2 = n & atomicReferenceArray.length() - 1;
            for (ReferenceEntry referenceEntry2 = referenceEntry3 = (ReferenceEntry)atomicReferenceArray.get((int)n2); referenceEntry2 != null; referenceEntry2 = referenceEntry2.getNext()) {
                ReferenceEntry referenceEntry3;
                if (referenceEntry2 != referenceEntry) continue;
                ++this.modCount;
                this.enqueueNotification(referenceEntry2.getKey(), n, referenceEntry2.getValueReference().get(), removalCause);
                referenceEntry = this.removeFromChain(referenceEntry3, referenceEntry2);
                n = this.count;
                atomicReferenceArray.set(n2, referenceEntry);
                this.count = n - 1;
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        ReferenceEntry<K, V> removeFromChain(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            this.evictionQueue.remove(referenceEntry2);
            this.expirationQueue.remove(referenceEntry2);
            int n = this.count;
            ReferenceEntry<K, V> referenceEntry3 = referenceEntry2.getNext();
            do {
                if (referenceEntry == referenceEntry2) {
                    this.count = n;
                    return referenceEntry3;
                }
                if (this.isCollected(referenceEntry)) {
                    this.removeCollectedEntry(referenceEntry);
                    --n;
                } else {
                    referenceEntry3 = this.copyEntry(referenceEntry, referenceEntry3);
                }
                referenceEntry = referenceEntry.getNext();
            } while (true);
        }

        V replace(K object, int n, V v) {
            int n2;
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray;
            this.lock();
            try {
                this.preWriteCleanup();
                atomicReferenceArray = this.table;
                n2 = n & atomicReferenceArray.length() - 1;
            }
            catch (Throwable var1_2) {
                throw var1_2;
            }
            finally {
                this.unlock();
                this.postWriteCleanup();
            }
            for (ReferenceEntry referenceEntry = referenceEntry2 = (ReferenceEntry)atomicReferenceArray.get((int)n2); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                block10 : {
                    Object v2;
                    block11 : {
                        Object k = referenceEntry.getKey();
                        if (referenceEntry.getHash() != n || k == null) break block10;
                        if (!this.map.keyEquivalence.equivalent(object, k)) break block10;
                        ValueReference valueReference = referenceEntry.getValueReference();
                        v2 = valueReference.get();
                        if (v2 != null) break block11;
                        if (this.isCollected(valueReference)) {
                            ReferenceEntry referenceEntry2;
                            int n3 = this.count;
                            ++this.modCount;
                            this.enqueueNotification(k, n, v2, MapMaker.RemovalCause.COLLECTED);
                            object = this.removeFromChain(referenceEntry2, referenceEntry);
                            n = this.count;
                            atomicReferenceArray.set(n2, object);
                            this.count = n - 1;
                        }
                        return null;
                    }
                    ++this.modCount;
                    this.enqueueNotification(object, n, v2, MapMaker.RemovalCause.REPLACED);
                    this.setValue(referenceEntry, v);
                    this.unlock();
                    this.postWriteCleanup();
                    return v2;
                }
                continue;
            }
            this.unlock();
            this.postWriteCleanup();
            return null;
        }

        boolean replace(K object, int n, V v, V v2) {
            int n2;
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray;
            this.lock();
            try {
                this.preWriteCleanup();
                atomicReferenceArray = this.table;
                n2 = n & atomicReferenceArray.length() - 1;
            }
            catch (Throwable var1_2) {
                throw var1_2;
            }
            finally {
                this.unlock();
                this.postWriteCleanup();
            }
            for (ReferenceEntry referenceEntry = referenceEntry2 = (ReferenceEntry)atomicReferenceArray.get((int)n2); referenceEntry != null; referenceEntry = referenceEntry.getNext()) {
                block11 : {
                    block13 : {
                        Object v3;
                        block12 : {
                            Object k = referenceEntry.getKey();
                            if (referenceEntry.getHash() != n || k == null) break block11;
                            if (!this.map.keyEquivalence.equivalent(object, k)) break block11;
                            ValueReference valueReference = referenceEntry.getValueReference();
                            v3 = valueReference.get();
                            if (v3 != null) break block12;
                            if (this.isCollected(valueReference)) {
                                ReferenceEntry referenceEntry2;
                                int n3 = this.count;
                                ++this.modCount;
                                this.enqueueNotification(k, n, v3, MapMaker.RemovalCause.COLLECTED);
                                object = this.removeFromChain(referenceEntry2, referenceEntry);
                                n = this.count;
                                atomicReferenceArray.set(n2, object);
                                this.count = n - 1;
                            }
                            return false;
                        }
                        if (!this.map.valueEquivalence.equivalent(v, v3)) break block13;
                        ++this.modCount;
                        this.enqueueNotification(object, n, v3, MapMaker.RemovalCause.REPLACED);
                        this.setValue(referenceEntry, v2);
                        this.unlock();
                        this.postWriteCleanup();
                        return true;
                    }
                    this.recordLockedRead(referenceEntry);
                    this.unlock();
                    this.postWriteCleanup();
                    return false;
                }
                continue;
            }
            this.unlock();
            this.postWriteCleanup();
            return false;
        }

        void runCleanup() {
            this.runLockedCleanup();
            this.runUnlockedCleanup();
        }

        void runLockedCleanup() {
            if (this.tryLock()) {
                this.drainReferenceQueues();
                this.expireEntries();
                this.readCount.set(0);
            }
            return;
            finally {
                this.unlock();
            }
        }

        void runUnlockedCleanup() {
            if (!this.isHeldByCurrentThread()) {
                this.map.processPendingNotifications();
            }
        }

        void setValue(ReferenceEntry<K, V> referenceEntry, V v) {
            referenceEntry.setValueReference(this.map.valueStrength.referenceValue(this, referenceEntry, v));
            this.recordWrite(referenceEntry);
        }

        void tryDrainReferenceQueues() {
            if (this.tryLock()) {
                this.drainReferenceQueues();
            }
            return;
            finally {
                this.unlock();
            }
        }

        void tryExpireEntries() {
            if (this.tryLock()) {
                this.expireEntries();
            }
            return;
            finally {
                this.unlock();
            }
        }
    }

    private static final class SerializationProxy<K, V>
    extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        SerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long l, long l2, int n, int n2, MapMaker.RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> concurrentMap) {
            super(strength, strength2, equivalence, equivalence2, l, l2, n, n2, removalListener, concurrentMap);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = this.readMapMaker(objectInputStream).makeMap();
            this.readEntries(objectInputStream);
        }

        private Object readResolve() {
            return this.delegate;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            this.writeMapTo(objectOutputStream);
        }
    }

    static class SoftEntry<K, V>
    extends SoftReference<K>
    implements ReferenceEntry<K, V> {
        final int hash;
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = MapMakerInternalMap.unset();

        SoftEntry(ReferenceQueue<K> referenceQueue, K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(k, referenceQueue);
            this.hash = n;
            this.next = referenceEntry;
        }

        @Override
        public long getExpirationTime() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getHash() {
            return this.hash;
        }

        @Override
        public K getKey() {
            return (K)this.get();
        }

        @Override
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override
        public void setExpirationTime(long l) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setValueReference(ValueReference<K, V> valueReference) {
            ValueReference<K, V> valueReference2 = this.valueReference;
            this.valueReference = valueReference;
            valueReference2.clear(valueReference);
        }
    }

    static final class SoftEvictableEntry<K, V>
    extends SoftEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();

        SoftEvictableEntry(ReferenceQueue<K> referenceQueue, K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, n, referenceEntry);
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.nextEvictable = referenceEntry;
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.previousEvictable = referenceEntry;
        }
    }

    static final class SoftExpirableEntry<K, V>
    extends SoftEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        SoftExpirableEntry(ReferenceQueue<K> referenceQueue, K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, n, referenceEntry);
        }

        @Override
        public long getExpirationTime() {
            return this.time;
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override
        public void setExpirationTime(long l) {
            this.time = l;
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.nextExpirable = referenceEntry;
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.previousExpirable = referenceEntry;
        }
    }

    static final class SoftExpirableEvictableEntry<K, V>
    extends SoftEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        SoftExpirableEvictableEntry(ReferenceQueue<K> referenceQueue, K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, n, referenceEntry);
        }

        @Override
        public long getExpirationTime() {
            return this.time;
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override
        public void setExpirationTime(long l) {
            this.time = l;
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.nextEvictable = referenceEntry;
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.nextExpirable = referenceEntry;
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.previousEvictable = referenceEntry;
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.previousExpirable = referenceEntry;
        }
    }

    static final class SoftValueReference<K, V>
    extends SoftReference<V>
    implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        SoftValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            super(v, referenceQueue);
            this.entry = referenceEntry;
        }

        @Override
        public void clear(ValueReference<K, V> valueReference) {
            this.clear();
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, ReferenceEntry<K, V> referenceEntry) {
            return new SoftValueReference<K, V>(referenceQueue, this.get(), referenceEntry);
        }

        @Override
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override
        public boolean isComputingReference() {
            return false;
        }

        @Override
        public V waitForValue() {
            return (V)this.get();
        }
    }

    static enum Strength {
        STRONG{

            @Override
            Equivalence<Object> defaultEquivalence() {
                return Equivalences.equals();
            }

            @Override
            <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v) {
                return new StrongValueReference(v);
            }
        }
        ,
        SOFT{

            @Override
            Equivalence<Object> defaultEquivalence() {
                return Equivalences.identity();
            }

            @Override
            <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v) {
                return new SoftValueReference(segment.valueReferenceQueue, v, referenceEntry);
            }
        }
        ,
        WEAK{

            @Override
            Equivalence<Object> defaultEquivalence() {
                return Equivalences.identity();
            }

            @Override
            <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v) {
                return new WeakValueReference(segment.valueReferenceQueue, v, referenceEntry);
            }
        };
        

        private Strength() {
        }

        abstract Equivalence<Object> defaultEquivalence();

        abstract <K, V> ValueReference<K, V> referenceValue(Segment<K, V> var1, ReferenceEntry<K, V> var2, V var3);

    }

    static class StrongEntry<K, V>
    implements ReferenceEntry<K, V> {
        final int hash;
        final K key;
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = MapMakerInternalMap.unset();

        StrongEntry(K k, int n, ReferenceEntry<K, V> referenceEntry) {
            this.key = k;
            this.hash = n;
            this.next = referenceEntry;
        }

        @Override
        public long getExpirationTime() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getHash() {
            return this.hash;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override
        public void setExpirationTime(long l) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setValueReference(ValueReference<K, V> valueReference) {
            ValueReference<K, V> valueReference2 = this.valueReference;
            this.valueReference = valueReference;
            valueReference2.clear(valueReference);
        }
    }

    static final class StrongEvictableEntry<K, V>
    extends StrongEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();

        StrongEvictableEntry(K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(k, n, referenceEntry);
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.nextEvictable = referenceEntry;
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.previousEvictable = referenceEntry;
        }
    }

    static final class StrongExpirableEntry<K, V>
    extends StrongEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        StrongExpirableEntry(K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(k, n, referenceEntry);
        }

        @Override
        public long getExpirationTime() {
            return this.time;
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override
        public void setExpirationTime(long l) {
            this.time = l;
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.nextExpirable = referenceEntry;
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.previousExpirable = referenceEntry;
        }
    }

    static final class StrongExpirableEvictableEntry<K, V>
    extends StrongEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        StrongExpirableEvictableEntry(K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(k, n, referenceEntry);
        }

        @Override
        public long getExpirationTime() {
            return this.time;
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override
        public void setExpirationTime(long l) {
            this.time = l;
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.nextEvictable = referenceEntry;
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.nextExpirable = referenceEntry;
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.previousEvictable = referenceEntry;
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.previousExpirable = referenceEntry;
        }
    }

    static final class StrongValueReference<K, V>
    implements ValueReference<K, V> {
        final V referent;

        StrongValueReference(V v) {
            this.referent = v;
        }

        @Override
        public void clear(ValueReference<K, V> valueReference) {
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override
        public V get() {
            return this.referent;
        }

        @Override
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override
        public boolean isComputingReference() {
            return false;
        }

        @Override
        public V waitForValue() {
            return this.get();
        }
    }

    final class ValueIterator
    extends MapMakerInternalMap<K, V>
    implements Iterator<V> {
        ValueIterator() {
        }

        @Override
        public V next() {
            return this.nextEntry().getValue();
        }
    }

    static interface ValueReference<K, V> {
        public void clear(ValueReference<K, V> var1);

        public ValueReference<K, V> copyFor(ReferenceQueue<V> var1, ReferenceEntry<K, V> var2);

        public V get();

        public ReferenceEntry<K, V> getEntry();

        public boolean isComputingReference();

        public V waitForValue() throws ExecutionException;
    }

    final class Values
    extends AbstractCollection<V> {
        Values() {
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        public boolean contains(Object object) {
            return MapMakerInternalMap.this.containsValue(object);
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    static class WeakEntry<K, V>
    extends WeakReference<K>
    implements ReferenceEntry<K, V> {
        final int hash;
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = MapMakerInternalMap.unset();

        WeakEntry(ReferenceQueue<K> referenceQueue, K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(k, referenceQueue);
            this.hash = n;
            this.next = referenceEntry;
        }

        @Override
        public long getExpirationTime() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getHash() {
            return this.hash;
        }

        @Override
        public K getKey() {
            return (K)this.get();
        }

        @Override
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override
        public void setExpirationTime(long l) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setValueReference(ValueReference<K, V> valueReference) {
            ValueReference<K, V> valueReference2 = this.valueReference;
            this.valueReference = valueReference;
            valueReference2.clear(valueReference);
        }
    }

    static final class WeakEvictableEntry<K, V>
    extends WeakEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();

        WeakEvictableEntry(ReferenceQueue<K> referenceQueue, K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, n, referenceEntry);
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.nextEvictable = referenceEntry;
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.previousEvictable = referenceEntry;
        }
    }

    static final class WeakExpirableEntry<K, V>
    extends WeakEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        WeakExpirableEntry(ReferenceQueue<K> referenceQueue, K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, n, referenceEntry);
        }

        @Override
        public long getExpirationTime() {
            return this.time;
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override
        public void setExpirationTime(long l) {
            this.time = l;
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.nextExpirable = referenceEntry;
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.previousExpirable = referenceEntry;
        }
    }

    static final class WeakExpirableEvictableEntry<K, V>
    extends WeakEntry<K, V>
    implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        WeakExpirableEvictableEntry(ReferenceQueue<K> referenceQueue, K k, int n, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, n, referenceEntry);
        }

        @Override
        public long getExpirationTime() {
            return this.time;
        }

        @Override
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override
        public void setExpirationTime(long l) {
            this.time = l;
        }

        @Override
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.nextEvictable = referenceEntry;
        }

        @Override
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.nextExpirable = referenceEntry;
        }

        @Override
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            this.previousEvictable = referenceEntry;
        }

        @Override
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            this.previousExpirable = referenceEntry;
        }
    }

    static final class WeakValueReference<K, V>
    extends WeakReference<V>
    implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        WeakValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            super(v, referenceQueue);
            this.entry = referenceEntry;
        }

        @Override
        public void clear(ValueReference<K, V> valueReference) {
            this.clear();
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, ReferenceEntry<K, V> referenceEntry) {
            return new WeakValueReference<K, V>(referenceQueue, this.get(), referenceEntry);
        }

        @Override
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override
        public boolean isComputingReference() {
            return false;
        }

        @Override
        public V waitForValue() {
            return (V)this.get();
        }
    }

    final class WriteThroughEntry
    extends AbstractMapEntry<K, V> {
        final K key;
        V value;

        WriteThroughEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override
        public boolean equals(Object object) {
            boolean bl;
            boolean bl2 = bl = false;
            if (object instanceof Map.Entry) {
                object = (Map.Entry)object;
                bl2 = bl;
                if (this.key.equals(object.getKey())) {
                    bl2 = bl;
                    if (this.value.equals(object.getValue())) {
                        bl2 = true;
                    }
                }
            }
            return bl2;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        @Override
        public V setValue(V v) {
            V v2 = MapMakerInternalMap.this.put(this.key, v);
            this.value = v;
            return v2;
        }
    }

}

