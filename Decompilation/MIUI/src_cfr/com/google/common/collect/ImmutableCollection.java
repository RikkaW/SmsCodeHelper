/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.collect;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Platform;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public abstract class ImmutableCollection<E>
implements Collection<E>,
Serializable {
    static final ImmutableCollection<Object> EMPTY_IMMUTABLE_COLLECTION = new EmptyImmutableCollection();

    ImmutableCollection() {
    }

    @Override
    public final boolean add(E e2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object object) {
        if (object != null && Iterators.contains(this.iterator(), object)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return Collections2.containsAllImpl(this, collection);
    }

    @Override
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public abstract UnmodifiableIterator<E> iterator();

    @Override
    public final boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return ObjectArrays.toArrayImpl(this);
    }

    @Override
    public <T> T[] toArray(T[] arrT) {
        return ObjectArrays.toArrayImpl(this, arrT);
    }

    public String toString() {
        return Collections2.toStringImpl(this);
    }

    Object writeReplace() {
        return new SerializedForm(this.toArray());
    }

    private static class ArrayImmutableCollection<E>
    extends ImmutableCollection<E> {
        private final E[] elements;

        ArrayImmutableCollection(E[] arrE) {
            this.elements = arrE;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public UnmodifiableIterator<E> iterator() {
            return Iterators.forArray(this.elements);
        }

        @Override
        public int size() {
            return this.elements.length;
        }
    }

    private static class EmptyImmutableCollection
    extends ImmutableCollection<Object> {
        private static final Object[] EMPTY_ARRAY = new Object[0];

        private EmptyImmutableCollection() {
        }

        @Override
        public boolean contains(Object object) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public UnmodifiableIterator<Object> iterator() {
            return Iterators.EMPTY_ITERATOR;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public Object[] toArray() {
            return EMPTY_ARRAY;
        }

        @Override
        public <T> T[] toArray(T[] arrT) {
            if (arrT.length > 0) {
                arrT[0] = null;
            }
            return arrT;
        }
    }

    private static class SerializedForm
    implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] arrobject) {
            this.elements = arrobject;
        }

        Object readResolve() {
            if (this.elements.length == 0) {
                return ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION;
            }
            return new ArrayImmutableCollection<Object>(Platform.clone(this.elements));
        }
    }

}

