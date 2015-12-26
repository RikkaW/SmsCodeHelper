/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ObjectInputStream
 *  java.io.ObjectOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.lang.ref.ReferenceQueue
 *  java.util.concurrent.ConcurrentMap
 */
package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ComputationException;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

class ComputingConcurrentHashMap<K, V>
extends MapMakerInternalMap<K, V> {
    private static final long serialVersionUID = 4;
    final Function<? super K, ? extends V> computingFunction;

    ComputingConcurrentHashMap(MapMaker mapMaker, Function<? super K, ? extends V> function) {
        super(mapMaker);
        this.computingFunction = Preconditions.checkNotNull(function);
    }

    @Override
    MapMakerInternalMap.Segment<K, V> createSegment(int n, int n2) {
        return new ComputingSegment(this, n, n2);
    }

    V getOrCompute(K k) throws ExecutionException {
        int n = this.hash(Preconditions.checkNotNull(k));
        return this.segmentFor(n).getOrCompute(k, n, this.computingFunction);
    }

    @Override
    ComputingSegment<K, V> segmentFor(int n) {
        return (ComputingSegment)super.segmentFor(n);
    }

    @Override
    Object writeReplace() {
        return new ComputingSerializationProxy<K, V>(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this, this.computingFunction);
    }

    private static final class ComputationExceptionReference<K, V>
    implements MapMakerInternalMap.ValueReference<K, V> {
        final Throwable t;

        ComputationExceptionReference(Throwable throwable) {
            this.t = throwable;
        }

        @Override
        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
        }

        @Override
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override
        public V get() {
            return null;
        }

        @Override
        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override
        public boolean isComputingReference() {
            return false;
        }

        @Override
        public V waitForValue() throws ExecutionException {
            throw new ExecutionException(this.t);
        }
    }

    private static final class ComputedReference<K, V>
    implements MapMakerInternalMap.ValueReference<K, V> {
        final V value;

        ComputedReference(V v) {
            this.value = v;
        }

        @Override
        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
        }

        @Override
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override
        public V get() {
            return this.value;
        }

        @Override
        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
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

    static final class ComputingMapAdapter<K, V>
    extends ComputingConcurrentHashMap<K, V>
    implements Serializable {
        private static final long serialVersionUID = 0;

        ComputingMapAdapter(MapMaker mapMaker, Function<? super K, ? extends V> function) {
            super(mapMaker, function);
        }

        @Override
        public V get(Object object) {
            Object v;
            try {
                v = this.getOrCompute(object);
                if (v == null) {
                    throw new NullPointerException(this.computingFunction + " returned null for key " + object + ".");
                }
            }
            catch (ExecutionException var1_2) {
                Throwable throwable = var1_2.getCause();
                Throwables.propagateIfInstanceOf(throwable, ComputationException.class);
                throw new ComputationException(throwable);
            }
            return v;
        }
    }

    static final class ComputingSegment<K, V>
    extends MapMakerInternalMap.Segment<K, V> {
        ComputingSegment(MapMakerInternalMap<K, V> mapMakerInternalMap, int n, int n2) {
            super(mapMakerInternalMap, n, n2);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        V compute(K k, int n, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry, ComputingValueReference<K, V> computingValueReference) throws ExecutionException {
            long l;
            Object v = null;
            Object v2 = null;
            System.nanoTime();
            long l2 = l = 0;
            l2 = l;
            v = v2;
            v2 = computingValueReference.compute(k, n);
            l2 = l;
            v = v2;
            l2 = l = System.nanoTime();
            v = v2;
            // MONITOREXIT : referenceEntry
            if (v2 == null) return v2;
            l2 = l;
            v = v2;
            if (this.put(k, n, v2, true) == null) return v2;
            l2 = l;
            v = v2;
            this.enqueueNotification(k, n, v2, MapMaker.RemovalCause.REPLACED);
            return v2;
            finally {
                if (l == 0) {
                    System.nanoTime();
                }
                if (v2 == null) {
                    this.clearValue(k, n, computingValueReference);
                }
            }
        }

        /*
         * Exception decompiling
         */
        V getOrCompute(K var1_1, int var2_5, Function<? super K, ? extends V> var3_6) throws ExecutionException {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }
    }

    static final class ComputingSerializationProxy<K, V>
    extends MapMakerInternalMap.AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 4;
        final Function<? super K, ? extends V> computingFunction;

        ComputingSerializationProxy(MapMakerInternalMap.Strength strength, MapMakerInternalMap.Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long l, long l2, int n, int n2, MapMaker.RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> concurrentMap, Function<? super K, ? extends V> function) {
            super(strength, strength2, equivalence, equivalence2, l, l2, n, n2, removalListener, concurrentMap);
            this.computingFunction = function;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = this.readMapMaker(objectInputStream).makeComputingMap(this.computingFunction);
            this.readEntries(objectInputStream);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            this.writeMapTo(objectOutputStream);
        }

        Object readResolve() {
            return this.delegate;
        }
    }

    private static final class ComputingValueReference<K, V>
    implements MapMakerInternalMap.ValueReference<K, V> {
        volatile MapMakerInternalMap.ValueReference<K, V> computedReference = MapMakerInternalMap.unset();
        final Function<? super K, ? extends V> computingFunction;

        public ComputingValueReference(Function<? super K, ? extends V> function) {
            this.computingFunction = function;
        }

        @Override
        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
            this.setValueReference(valueReference);
        }

        V compute(K object, int n) throws ExecutionException {
            try {
                object = this.computingFunction.apply(object);
            }
            catch (Throwable var1_2) {
                this.setValueReference(new ComputationExceptionReference(var1_2));
                throw new ExecutionException(var1_2);
            }
            this.setValueReference(new ComputedReference(object));
            return (V)object;
        }

        @Override
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override
        public V get() {
            return null;
        }

        @Override
        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override
        public boolean isComputingReference() {
            return true;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        void setValueReference(MapMakerInternalMap.ValueReference<K, V> valueReference) {
            synchronized (this) {
                if (this.computedReference == MapMakerInternalMap.UNSET) {
                    this.computedReference = valueReference;
                    this.notifyAll();
                }
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        @Override
        public V waitForValue() throws ExecutionException {
            boolean bl;
            if (this.computedReference != MapMakerInternalMap.UNSET) return this.computedReference.waitForValue();
            boolean bl2 = false;
            bl2 = bl = false;
            do {
                MapMakerInternalMap.ValueReference<K, V> valueReference;
                MapMakerInternalMap.ValueReference<Object, Object> valueReference2;
                if ((valueReference = this.computedReference) != (valueReference2 = MapMakerInternalMap.UNSET)) {
                    // MONITOREXIT : this
                    if (!bl2) return this.computedReference.waitForValue();
                    Thread.currentThread().interrupt();
                    return this.computedReference.waitForValue();
                }
                try {
                    this.wait();
                }
                catch (InterruptedException var3_4) {
                    bl2 = true;
                    continue;
                }
                break;
            } while (true);
        }
    }

}

