/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 */
package android.support.v4.util;

import android.support.v4.util.ContainerHelpers;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class MapCollections<K, V> {
    MapCollections<K, V> mEntrySet;
    MapCollections<K, V> mKeySet;
    MapCollections<K, V> mValues;

    MapCollections() {
    }

    public static <K, V> boolean containsAllHelper(Map<K, V> map, Collection<?> object) {
        object = object.iterator();
        while (object.hasNext()) {
            if (map.containsKey(object.next())) continue;
            return false;
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static <T> boolean equalsSetHelper(Set<T> set, Object object) {
        boolean bl2 = true;
        boolean bl3 = false;
        if (set == object) {
            return true;
        }
        if (!(object instanceof Set)) return bl3;
        object = (Set)object;
        try {
            if (set.size() != object.size()) return false;
            bl3 = set.containsAll(object);
            if (!bl3) return false;
            return bl2;
        }
        catch (ClassCastException var0_1) {
            return false;
        }
        catch (NullPointerException var0_2) {
            return false;
        }
    }

    public static <K, V> boolean removeAllHelper(Map<K, V> map, Collection<?> object) {
        int n2 = map.size();
        object = object.iterator();
        while (object.hasNext()) {
            map.remove(object.next());
        }
        if (n2 != map.size()) {
            return true;
        }
        return false;
    }

    public static <K, V> boolean retainAllHelper(Map<K, V> map, Collection<?> collection) {
        int n2 = map.size();
        Iterator<K> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            if (collection.contains(iterator.next())) continue;
            iterator.remove();
        }
        if (n2 != map.size()) {
            return true;
        }
        return false;
    }

    protected abstract void colClear();

    protected abstract Object colGetEntry(int var1, int var2);

    protected abstract Map<K, V> colGetMap();

    protected abstract int colGetSize();

    protected abstract int colIndexOfKey(Object var1);

    protected abstract int colIndexOfValue(Object var1);

    protected abstract void colPut(K var1, V var2);

    protected abstract void colRemoveAt(int var1);

    protected abstract V colSetValue(int var1, V var2);

    public Set<Map.Entry<K, V>> getEntrySet() {
        if (this.mEntrySet == null) {
            this.mEntrySet = new EntrySet();
        }
        return this.mEntrySet;
    }

    public Set<K> getKeySet() {
        if (this.mKeySet == null) {
            this.mKeySet = new KeySet();
        }
        return this.mKeySet;
    }

    public Collection<V> getValues() {
        if (this.mValues == null) {
            this.mValues = new ValuesCollection();
        }
        return this.mValues;
    }

    public Object[] toArrayHelper(int n2) {
        int n3 = this.colGetSize();
        Object[] arrobject = new Object[n3];
        for (int i2 = 0; i2 < n3; ++i2) {
            arrobject[i2] = this.colGetEntry(i2, n2);
        }
        return arrobject;
    }

    public <T> T[] toArrayHelper(T[] arrT, int n2) {
        int n3 = this.colGetSize();
        if (arrT.length < n3) {
            arrT = (Object[])Array.newInstance(arrT.getClass().getComponentType(), n3);
        }
        for (int i2 = 0; i2 < n3; ++i2) {
            arrT[i2] = this.colGetEntry(i2, n2);
        }
        if (arrT.length > n3) {
            arrT[n3] = null;
        }
        return arrT;
    }

    final class ArrayIterator<T>
    implements Iterator<T> {
        boolean mCanRemove;
        int mIndex;
        final int mOffset;
        int mSize;

        ArrayIterator(int n2) {
            this.mCanRemove = false;
            this.mOffset = n2;
            this.mSize = MapCollections.this.colGetSize();
        }

        @Override
        public boolean hasNext() {
            if (this.mIndex < this.mSize) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            Object object = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
            ++this.mIndex;
            this.mCanRemove = true;
            return (T)object;
        }

        @Override
        public void remove() {
            if (!this.mCanRemove) {
                throw new IllegalStateException();
            }
            --this.mIndex;
            --this.mSize;
            this.mCanRemove = false;
            MapCollections.this.colRemoveAt(this.mIndex);
        }
    }

    final class EntrySet
    implements Set<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override
        public boolean add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends Map.Entry<K, V>> object) {
            int n2 = MapCollections.this.colGetSize();
            object = object.iterator();
            while (object.hasNext()) {
                Map.Entry entry = (Map.Entry)object.next();
                MapCollections.this.colPut(entry.getKey(), entry.getValue());
            }
            if (n2 != MapCollections.this.colGetSize()) {
                return true;
            }
            return false;
        }

        @Override
        public void clear() {
            MapCollections.this.colClear();
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public boolean contains(Object object) {
            int n2;
            if (!(object instanceof Map.Entry) || (n2 = MapCollections.this.colIndexOfKey((object = (Map.Entry)object).getKey())) < 0) {
                return false;
            }
            return ContainerHelpers.equal(MapCollections.this.colGetEntry(n2, 1), object.getValue());
        }

        @Override
        public boolean containsAll(Collection<?> object) {
            object = object.iterator();
            while (object.hasNext()) {
                if (this.contains(object.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean equals(Object object) {
            return MapCollections.equalsSetHelper(this, object);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public int hashCode() {
            int n2 = MapCollections.this.colGetSize() - 1;
            int n3 = 0;
            while (n2 >= 0) {
                Object object = MapCollections.this.colGetEntry(n2, 0);
                Object object2 = MapCollections.this.colGetEntry(n2, 1);
                int n4 = object == null ? 0 : object.hashCode();
                int n5 = object2 == null ? 0 : object2.hashCode();
                --n2;
                n3 += n5 ^ n4;
            }
            return n3;
        }

        @Override
        public boolean isEmpty() {
            if (MapCollections.this.colGetSize() == 0) {
                return true;
            }
            return false;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new MapIterator();
        }

        @Override
        public boolean remove(Object object) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T> T[] toArray(T[] arrT) {
            throw new UnsupportedOperationException();
        }
    }

    final class KeySet
    implements Set<K> {
        KeySet() {
        }

        @Override
        public boolean add(K k2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            MapCollections.this.colClear();
        }

        @Override
        public boolean contains(Object object) {
            if (MapCollections.this.colIndexOfKey(object) >= 0) {
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), collection);
        }

        @Override
        public boolean equals(Object object) {
            return MapCollections.equalsSetHelper(this, object);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public int hashCode() {
            int n2 = MapCollections.this.colGetSize() - 1;
            int n3 = 0;
            while (n2 >= 0) {
                Object object = MapCollections.this.colGetEntry(n2, 0);
                int n4 = object == null ? 0 : object.hashCode();
                n3 += n4;
                --n2;
            }
            return n3;
        }

        @Override
        public boolean isEmpty() {
            if (MapCollections.this.colGetSize() == 0) {
                return true;
            }
            return false;
        }

        @Override
        public Iterator<K> iterator() {
            return new ArrayIterator(0);
        }

        @Override
        public boolean remove(Object object) {
            int n2 = MapCollections.this.colIndexOfKey(object);
            if (n2 >= 0) {
                MapCollections.this.colRemoveAt(n2);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), collection);
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), collection);
        }

        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }

        @Override
        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(0);
        }

        @Override
        public <T> T[] toArray(T[] arrT) {
            return MapCollections.this.toArrayHelper(arrT, 0);
        }
    }

    final class MapIterator
    implements Iterator<Map.Entry<K, V>>,
    Map.Entry<K, V> {
        int mEnd;
        boolean mEntryValid;
        int mIndex;

        MapIterator() {
            this.mEntryValid = false;
            this.mEnd = MapCollections.this.colGetSize() - 1;
            this.mIndex = -1;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean equals(Object object) {
            boolean bl2 = true;
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(object instanceof Map.Entry)) {
                return false;
            }
            if (!ContainerHelpers.equal((object = (Map.Entry)object).getKey(), MapCollections.this.colGetEntry(this.mIndex, 0))) return false;
            if (!ContainerHelpers.equal(object.getValue(), MapCollections.this.colGetEntry(this.mIndex, 1))) return false;
            return bl2;
        }

        public K getKey() {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (K)MapCollections.this.colGetEntry(this.mIndex, 0);
        }

        public V getValue() {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (V)MapCollections.this.colGetEntry(this.mIndex, 1);
        }

        @Override
        public boolean hasNext() {
            if (this.mIndex < this.mEnd) {
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public final int hashCode() {
            int n2 = 0;
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            Object object = MapCollections.this.colGetEntry(this.mIndex, 0);
            Object object2 = MapCollections.this.colGetEntry(this.mIndex, 1);
            int n3 = object == null ? 0 : object.hashCode();
            if (object2 == null) {
                return n2 ^ n3;
            }
            n2 = object2.hashCode();
            return n2 ^ n3;
        }

        @Override
        public Map.Entry<K, V> next() {
            ++this.mIndex;
            this.mEntryValid = true;
            return this;
        }

        @Override
        public void remove() {
            if (!this.mEntryValid) {
                throw new IllegalStateException();
            }
            MapCollections.this.colRemoveAt(this.mIndex);
            --this.mIndex;
            --this.mEnd;
            this.mEntryValid = false;
        }

        public V setValue(V v2) {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return MapCollections.this.colSetValue(this.mIndex, v2);
        }

        public final String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }

    final class ValuesCollection
    implements Collection<V> {
        ValuesCollection() {
        }

        @Override
        public boolean add(V v2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            MapCollections.this.colClear();
        }

        @Override
        public boolean contains(Object object) {
            if (MapCollections.this.colIndexOfValue(object) >= 0) {
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> object) {
            object = object.iterator();
            while (object.hasNext()) {
                if (this.contains(object.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean isEmpty() {
            if (MapCollections.this.colGetSize() == 0) {
                return true;
            }
            return false;
        }

        @Override
        public Iterator<V> iterator() {
            return new ArrayIterator(1);
        }

        @Override
        public boolean remove(Object object) {
            int n2 = MapCollections.this.colIndexOfValue(object);
            if (n2 >= 0) {
                MapCollections.this.colRemoveAt(n2);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            int n2 = 0;
            int n3 = MapCollections.this.colGetSize();
            boolean bl2 = false;
            while (n2 < n3) {
                int n4 = n2;
                int n5 = n3;
                if (collection.contains(MapCollections.this.colGetEntry(n2, 1))) {
                    MapCollections.this.colRemoveAt(n2);
                    n4 = n2 - 1;
                    n5 = n3 - 1;
                    bl2 = true;
                }
                n2 = n4 + 1;
                n3 = n5;
            }
            return bl2;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            int n2 = 0;
            int n3 = MapCollections.this.colGetSize();
            boolean bl2 = false;
            while (n2 < n3) {
                int n4 = n2;
                int n5 = n3;
                if (!collection.contains(MapCollections.this.colGetEntry(n2, 1))) {
                    MapCollections.this.colRemoveAt(n2);
                    n4 = n2 - 1;
                    n5 = n3 - 1;
                    bl2 = true;
                }
                n2 = n4 + 1;
                n3 = n5;
            }
            return bl2;
        }

        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }

        @Override
        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(1);
        }

        @Override
        public <T> T[] toArray(T[] arrT) {
            return MapCollections.this.toArrayHelper(arrT, 1);
        }
    }

}

