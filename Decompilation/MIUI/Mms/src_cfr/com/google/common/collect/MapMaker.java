/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.AbstractMap
 *  java.util.Collections
 *  java.util.Map$Entry
 *  java.util.concurrent.ConcurrentHashMap
 *  java.util.concurrent.ConcurrentMap
 *  java.util.concurrent.TimeUnit
 */
package com.google.common.collect;

import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import com.google.common.collect.ComputationException;
import com.google.common.collect.ComputingConcurrentHashMap;
import com.google.common.collect.GenericMapMaker;
import com.google.common.collect.ImmutableEntry;
import com.google.common.collect.MapMakerInternalMap;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public final class MapMaker
extends GenericMapMaker<Object, Object> {
    int concurrencyLevel = -1;
    long expireAfterAccessNanos = -1;
    long expireAfterWriteNanos = -1;
    int initialCapacity = -1;
    Equivalence<Object> keyEquivalence;
    MapMakerInternalMap.Strength keyStrength;
    int maximumSize = -1;
    RemovalCause nullRemovalCause;
    Ticker ticker;
    boolean useCustomMap;
    Equivalence<Object> valueEquivalence;
    MapMakerInternalMap.Strength valueStrength;

    /*
     * Enabled aggressive block sorting
     */
    private void checkExpiration(long l, TimeUnit timeUnit) {
        boolean bl = this.expireAfterWriteNanos == -1;
        Preconditions.checkState(bl, "expireAfterWrite was already set to %s ns", this.expireAfterWriteNanos);
        bl = this.expireAfterAccessNanos == -1;
        Preconditions.checkState(bl, "expireAfterAccess was already set to %s ns", this.expireAfterAccessNanos);
        bl = l >= 0;
        Preconditions.checkArgument(bl, "duration cannot be negative: %s %s", new Object[]{l, timeUnit});
    }

    private boolean useNullMap() {
        if (this.nullRemovalCause == null) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MapMaker concurrencyLevel(int n) {
        boolean bl = true;
        boolean bl2 = this.concurrencyLevel == -1;
        Preconditions.checkState(bl2, "concurrency level was already set to %s", this.concurrencyLevel);
        bl2 = n > 0 ? bl : false;
        Preconditions.checkArgument(bl2);
        this.concurrencyLevel = n;
        return this;
    }

    @Deprecated
    MapMaker expireAfterAccess(long l, TimeUnit timeUnit) {
        this.checkExpiration(l, timeUnit);
        this.expireAfterAccessNanos = timeUnit.toNanos(l);
        if (l == 0 && this.nullRemovalCause == null) {
            this.nullRemovalCause = RemovalCause.EXPIRED;
        }
        this.useCustomMap = true;
        return this;
    }

    @Deprecated
    MapMaker expireAfterWrite(long l, TimeUnit timeUnit) {
        this.checkExpiration(l, timeUnit);
        this.expireAfterWriteNanos = timeUnit.toNanos(l);
        if (l == 0 && this.nullRemovalCause == null) {
            this.nullRemovalCause = RemovalCause.EXPIRED;
        }
        this.useCustomMap = true;
        return this;
    }

    int getConcurrencyLevel() {
        if (this.concurrencyLevel == -1) {
            return 4;
        }
        return this.concurrencyLevel;
    }

    long getExpireAfterAccessNanos() {
        if (this.expireAfterAccessNanos == -1) {
            return 0;
        }
        return this.expireAfterAccessNanos;
    }

    long getExpireAfterWriteNanos() {
        if (this.expireAfterWriteNanos == -1) {
            return 0;
        }
        return this.expireAfterWriteNanos;
    }

    int getInitialCapacity() {
        if (this.initialCapacity == -1) {
            return 16;
        }
        return this.initialCapacity;
    }

    Equivalence<Object> getKeyEquivalence() {
        return Objects.firstNonNull(this.keyEquivalence, this.getKeyStrength().defaultEquivalence());
    }

    MapMakerInternalMap.Strength getKeyStrength() {
        return Objects.firstNonNull(this.keyStrength, MapMakerInternalMap.Strength.STRONG);
    }

    Ticker getTicker() {
        return Objects.firstNonNull(this.ticker, Ticker.systemTicker());
    }

    Equivalence<Object> getValueEquivalence() {
        return Objects.firstNonNull(this.valueEquivalence, this.getValueStrength().defaultEquivalence());
    }

    MapMakerInternalMap.Strength getValueStrength() {
        return Objects.firstNonNull(this.valueStrength, MapMakerInternalMap.Strength.STRONG);
    }

    /*
     * Enabled aggressive block sorting
     */
    public MapMaker initialCapacity(int n) {
        boolean bl = true;
        boolean bl2 = this.initialCapacity == -1;
        Preconditions.checkState(bl2, "initial capacity was already set to %s", this.initialCapacity);
        bl2 = n >= 0 ? bl : false;
        Preconditions.checkArgument(bl2);
        this.initialCapacity = n;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    MapMaker keyEquivalence(Equivalence<Object> equivalence) {
        boolean bl = this.keyEquivalence == null;
        Preconditions.checkState(bl, "key equivalence was already set to %s", this.keyEquivalence);
        this.keyEquivalence = Preconditions.checkNotNull(equivalence);
        this.useCustomMap = true;
        return this;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Deprecated
    public <K, V> ConcurrentMap<K, V> makeComputingMap(Function<? super K, ? extends V> abstractMap) {
        if (this.useNullMap()) {
            abstractMap = new ComputingConcurrentHashMap.ComputingMapAdapter<K, V>(this, (Function<? super K, ? extends V>)abstractMap);
            do {
                return abstractMap;
                break;
            } while (true);
        }
        abstractMap = new NullComputingConcurrentMap<K, V>(this, (Function<? super K, ? extends V>)abstractMap);
        return abstractMap;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public <K, V> ConcurrentMap<K, V> makeMap() {
        void var1_2;
        if (!this.useCustomMap) {
            return new ConcurrentHashMap(this.getInitialCapacity(), 0.75f, this.getConcurrencyLevel());
        }
        if (this.nullRemovalCause == null) {
            MapMakerInternalMap mapMakerInternalMap = new MapMakerInternalMap(this);
            do {
                return (ConcurrentMap)var1_2;
                break;
            } while (true);
        }
        NullConcurrentMap nullConcurrentMap = new NullConcurrentMap(this);
        return (ConcurrentMap)var1_2;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Deprecated
    MapMaker maximumSize(int n) {
        boolean bl = false;
        boolean bl2 = this.maximumSize == -1;
        Preconditions.checkState(bl2, "maximum size was already set to %s", this.maximumSize);
        bl2 = bl;
        if (n >= 0) {
            bl2 = true;
        }
        Preconditions.checkArgument(bl2, "maximum size must not be negative");
        this.maximumSize = n;
        this.useCustomMap = true;
        if (this.maximumSize == 0) {
            this.nullRemovalCause = RemovalCause.SIZE;
        }
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Deprecated
    <K, V> GenericMapMaker<K, V> removalListener(RemovalListener<K, V> removalListener) {
        boolean bl = this.removalListener == null;
        Preconditions.checkState(bl);
        this.removalListener = Preconditions.checkNotNull(removalListener);
        this.useCustomMap = true;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    MapMaker setKeyStrength(MapMakerInternalMap.Strength strength) {
        boolean bl = this.keyStrength == null;
        Preconditions.checkState(bl, "Key strength was already set to %s", new Object[]{this.keyStrength});
        this.keyStrength = Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.useCustomMap = true;
        }
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    MapMaker setValueStrength(MapMakerInternalMap.Strength strength) {
        boolean bl = this.valueStrength == null;
        Preconditions.checkState(bl, "Value strength was already set to %s", new Object[]{this.valueStrength});
        this.valueStrength = Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.useCustomMap = true;
        }
        return this;
    }

    public String toString() {
        Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this);
        if (this.initialCapacity != -1) {
            toStringHelper.add("initialCapacity", this.initialCapacity);
        }
        if (this.concurrencyLevel != -1) {
            toStringHelper.add("concurrencyLevel", this.concurrencyLevel);
        }
        if (this.maximumSize != -1) {
            toStringHelper.add("maximumSize", this.maximumSize);
        }
        if (this.expireAfterWriteNanos != -1) {
            toStringHelper.add("expireAfterWrite", "" + this.expireAfterWriteNanos + "ns");
        }
        if (this.expireAfterAccessNanos != -1) {
            toStringHelper.add("expireAfterAccess", "" + this.expireAfterAccessNanos + "ns");
        }
        if (this.keyStrength != null) {
            toStringHelper.add("keyStrength", Ascii.toLowerCase(this.keyStrength.toString()));
        }
        if (this.valueStrength != null) {
            toStringHelper.add("valueStrength", Ascii.toLowerCase(this.valueStrength.toString()));
        }
        if (this.keyEquivalence != null) {
            toStringHelper.addValue("keyEquivalence");
        }
        if (this.valueEquivalence != null) {
            toStringHelper.addValue("valueEquivalence");
        }
        if (this.removalListener != null) {
            toStringHelper.addValue("removalListener");
        }
        return toStringHelper.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    MapMaker valueEquivalence(Equivalence<Object> equivalence) {
        boolean bl = this.valueEquivalence == null;
        Preconditions.checkState(bl, "value equivalence was already set to %s", this.valueEquivalence);
        this.valueEquivalence = Preconditions.checkNotNull(equivalence);
        this.useCustomMap = true;
        return this;
    }

    public MapMaker weakKeys() {
        return this.setKeyStrength(MapMakerInternalMap.Strength.WEAK);
    }

    static final class NullComputingConcurrentMap<K, V>
    extends NullConcurrentMap<K, V> {
        private static final long serialVersionUID = 0;
        final Function<? super K, ? extends V> computingFunction;

        NullComputingConcurrentMap(MapMaker mapMaker, Function<? super K, ? extends V> function) {
            super(mapMaker);
            this.computingFunction = Preconditions.checkNotNull(function);
        }

        private V compute(K object) {
            Preconditions.checkNotNull(object);
            try {
                object = this.computingFunction.apply(object);
            }
            catch (ComputationException var1_2) {
                throw var1_2;
            }
            catch (Throwable var1_3) {
                throw new ComputationException(var1_3);
            }
            return (V)object;
        }

        @Override
        public V get(Object object) {
            V v = this.compute(object);
            Preconditions.checkNotNull(v, this.computingFunction + " returned null for key " + object + ".");
            this.notifyRemoval(object, v);
            return v;
        }
    }

    static class NullConcurrentMap<K, V>
    extends AbstractMap<K, V>
    implements ConcurrentMap<K, V>,
    Serializable {
        private static final long serialVersionUID = 0;
        private final RemovalCause removalCause;
        private final RemovalListener<K, V> removalListener;

        NullConcurrentMap(MapMaker mapMaker) {
            this.removalListener = mapMaker.getRemovalListener();
            this.removalCause = mapMaker.nullRemovalCause;
        }

        public boolean containsKey(Object object) {
            return false;
        }

        public boolean containsValue(Object object) {
            return false;
        }

        public Set<Map.Entry<K, V>> entrySet() {
            return Collections.emptySet();
        }

        public V get(Object object) {
            return null;
        }

        void notifyRemoval(K object, V v) {
            object = new RemovalNotification<K, V>(object, v, this.removalCause);
            this.removalListener.onRemoval((RemovalNotification<K, V>)object);
        }

        public V put(K k, V v) {
            Preconditions.checkNotNull(k);
            Preconditions.checkNotNull(v);
            this.notifyRemoval(k, v);
            return null;
        }

        public V putIfAbsent(K k, V v) {
            return this.put(k, v);
        }

        public V remove(Object object) {
            return null;
        }

        public boolean remove(Object object, Object object2) {
            return false;
        }

        public V replace(K k, V v) {
            Preconditions.checkNotNull(k);
            Preconditions.checkNotNull(v);
            return null;
        }

        public boolean replace(K k, V v, V v2) {
            Preconditions.checkNotNull(k);
            Preconditions.checkNotNull(v2);
            return false;
        }
    }

    static enum RemovalCause {
        EXPLICIT{}
        ,
        REPLACED{}
        ,
        COLLECTED{}
        ,
        EXPIRED{}
        ,
        SIZE{};
        

        private RemovalCause() {
        }

    }

    static interface RemovalListener<K, V> {
        public void onRemoval(RemovalNotification<K, V> var1);
    }

    static final class RemovalNotification<K, V>
    extends ImmutableEntry<K, V> {
        private static final long serialVersionUID = 0;
        private final RemovalCause cause;

        RemovalNotification(K k, V v, RemovalCause removalCause) {
            super(k, v);
            this.cause = removalCause;
        }
    }

}

