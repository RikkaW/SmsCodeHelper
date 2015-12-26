/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ObjectOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class Synchronized {
    private Synchronized() {
    }

    private static <E> Collection<E> collection(Collection<E> collection, Object object) {
        return new SynchronizedCollection(collection, object);
    }

    static <K, V> Map<K, V> map(Map<K, V> map, Object object) {
        return new SynchronizedMap<K, V>(map, object);
    }

    static <E> Set<E> set(Set<E> set, Object object) {
        return new SynchronizedSet<E>(set, object);
    }

    static class SynchronizedBiMap<K, V>
    extends SynchronizedMap<K, V>
    implements BiMap<K, V>,
    Serializable {
        private static final long serialVersionUID = 0;
        private transient Set<V> valueSet;

        @Override
        BiMap<K, V> delegate() {
            return (BiMap)super.delegate();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public Set<V> values() {
            Object object = this.mutex;
            synchronized (object) {
                if (this.valueSet != null) return this.valueSet;
                this.valueSet = Synchronized.set(this.delegate().values(), this.mutex);
                return this.valueSet;
            }
        }
    }

    static class SynchronizedCollection<E>
    extends SynchronizedObject
    implements Collection<E> {
        private static final long serialVersionUID = 0;

        private SynchronizedCollection(Collection<E> collection, Object object) {
            super(collection, object);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean add(E e2) {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().add(e2);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean addAll(Collection<? extends E> collection) {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().addAll(collection);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void clear() {
            Object object = this.mutex;
            synchronized (object) {
                this.delegate().clear();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean contains(Object object) {
            Object object2 = this.mutex;
            synchronized (object2) {
                return this.delegate().contains(object);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean containsAll(Collection<?> collection) {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().containsAll(collection);
            }
        }

        @Override
        Collection<E> delegate() {
            return (Collection)super.delegate();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean isEmpty() {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().isEmpty();
            }
        }

        @Override
        public Iterator<E> iterator() {
            return this.delegate().iterator();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean remove(Object object) {
            Object object2 = this.mutex;
            synchronized (object2) {
                return this.delegate().remove(object);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean removeAll(Collection<?> collection) {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().removeAll(collection);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean retainAll(Collection<?> collection) {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().retainAll(collection);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public int size() {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().size();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public Object[] toArray() {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().toArray();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public <T> T[] toArray(T[] arrT) {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().toArray(arrT);
            }
        }
    }

    private static class SynchronizedMap<K, V>
    extends SynchronizedObject
    implements Map<K, V> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, V>> entrySet;
        transient Set<K> keySet;
        transient Collection<V> values;

        SynchronizedMap(Map<K, V> map, Object object) {
            super(map, object);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void clear() {
            Object object = this.mutex;
            synchronized (object) {
                this.delegate().clear();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean containsKey(Object object) {
            Object object2 = this.mutex;
            synchronized (object2) {
                return this.delegate().containsKey(object);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean containsValue(Object object) {
            Object object2 = this.mutex;
            synchronized (object2) {
                return this.delegate().containsValue(object);
            }
        }

        @Override
        Map<K, V> delegate() {
            return (Map)super.delegate();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            Object object = this.mutex;
            synchronized (object) {
                if (this.entrySet != null) return this.entrySet;
                this.entrySet = Synchronized.set(this.delegate().entrySet(), this.mutex);
                return this.entrySet;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            Object object2 = this.mutex;
            synchronized (object2) {
                return this.delegate().equals(object);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public V get(Object object) {
            Object object2 = this.mutex;
            synchronized (object2) {
                object = this.delegate().get(object);
                return (V)object;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public int hashCode() {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().hashCode();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean isEmpty() {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().isEmpty();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public Set<K> keySet() {
            Object object = this.mutex;
            synchronized (object) {
                if (this.keySet != null) return this.keySet;
                this.keySet = Synchronized.set(this.delegate().keySet(), this.mutex);
                return this.keySet;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public V put(K object, V v) {
            Object object2 = this.mutex;
            synchronized (object2) {
                object = this.delegate().put(object, v);
                return (V)object;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void putAll(Map<? extends K, ? extends V> map) {
            Object object = this.mutex;
            synchronized (object) {
                this.delegate().putAll(map);
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public V remove(Object object) {
            Object object2 = this.mutex;
            synchronized (object2) {
                object = this.delegate().remove(object);
                return (V)object;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public int size() {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().size();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public Collection<V> values() {
            Object object = this.mutex;
            synchronized (object) {
                if (this.values != null) return this.values;
                this.values = Synchronized.collection(this.delegate().values(), this.mutex);
                return this.values;
            }
        }
    }

    static class SynchronizedObject
    implements Serializable {
        private static final long serialVersionUID = 0;
        final Object delegate;
        final Object mutex;

        SynchronizedObject(Object object, Object object2) {
            this.delegate = Preconditions.checkNotNull(object);
            object = object2;
            if (object2 == null) {
                object = this;
            }
            this.mutex = object;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            Object object = this.mutex;
            synchronized (object) {
                objectOutputStream.defaultWriteObject();
                return;
            }
        }

        Object delegate() {
            return this.delegate;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public String toString() {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate.toString();
            }
        }
    }

    static class SynchronizedSet<E>
    extends SynchronizedCollection<E>
    implements Set<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSet(Set<E> set, Object object) {
            super(set, object);
        }

        @Override
        Set<E> delegate() {
            return (Set)super.delegate();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            Object object2 = this.mutex;
            synchronized (object2) {
                return this.delegate().equals(object);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public int hashCode() {
            Object object = this.mutex;
            synchronized (object) {
                return this.delegate().hashCode();
            }
        }
    }

}

