/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIndexedListIterator;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Iterators {
    static final UnmodifiableIterator<Object> EMPTY_ITERATOR = new UnmodifiableIterator<Object>(){

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException();
        }
    };
    private static final Iterator<Object> EMPTY_MODIFIABLE_ITERATOR = new Iterator<Object>(){

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new IllegalStateException();
        }
    };

    private Iterators() {
    }

    public static boolean contains(Iterator<?> iterator, Object object) {
        if (object == null) {
            while (iterator.hasNext()) {
                if (iterator.next() != null) continue;
                return true;
            }
        } else {
            while (iterator.hasNext()) {
                if (!object.equals(iterator.next())) continue;
                return true;
            }
        }
        return false;
    }

    public static <T> UnmodifiableIterator<T> emptyIterator() {
        return EMPTY_ITERATOR;
    }

    public static /* varargs */ <T> UnmodifiableIterator<T> forArray(final T ... arrT) {
        Preconditions.checkNotNull(arrT);
        return new AbstractIndexedListIterator<T>(arrT.length){

            @Override
            protected T get(int n) {
                return (T)arrT[n];
            }
        };
    }

    public static <T> T getOnlyElement(Iterator<T> iterator) {
        T t = iterator.next();
        if (!iterator.hasNext()) {
            return t;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("expected one element but was: <" + t);
        for (int i = 0; i < 4 && iterator.hasNext(); ++i) {
            stringBuilder.append(", " + iterator.next());
        }
        if (iterator.hasNext()) {
            stringBuilder.append(", ...");
        }
        stringBuilder.append('>');
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(final T t) {
        return new UnmodifiableIterator<T>(){
            boolean done;

            @Override
            public boolean hasNext() {
                if (!this.done) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (this.done) {
                    throw new NoSuchElementException();
                }
                this.done = true;
                return (T)t;
            }
        };
    }

    public static String toString(Iterator<?> iterator) {
        if (!iterator.hasNext()) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[').append(iterator.next());
        while (iterator.hasNext()) {
            stringBuilder.append(", ").append(iterator.next());
        }
        return stringBuilder.append(']').toString();
    }

    public static <F, T> Iterator<T> transform(final Iterator<F> iterator, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(iterator);
        Preconditions.checkNotNull(function);
        return new Iterator<T>(){

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                Object e2 = iterator.next();
                return function.apply(e2);
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

}

