/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Comparator
 *  java.util.concurrent.ConcurrentMap
 *  java.util.concurrent.atomic.AtomicInteger
 */
package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Platform;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Ordering<T>
implements Comparator<T> {
    protected Ordering() {
    }

    static class ArbitraryOrdering
    extends Ordering<Object> {
        private Map<Object, Integer> uids;

        ArbitraryOrdering() {
            this.uids = Platform.tryWeakKeys(new MapMaker()).makeComputingMap(new Function<Object, Integer>(){
                final AtomicInteger counter;

                @Override
                public Integer apply(Object object) {
                    return this.counter.getAndIncrement();
                }
            });
        }

        public int compare(Object object, Object object2) {
            int n;
            if (object == object2) {
                return 0;
            }
            int n2 = this.identityHashCode(object);
            if (n2 != (n = this.identityHashCode(object2))) {
                if (n2 < n) {
                    return -1;
                }
                return 1;
            }
            n2 = this.uids.get(object).compareTo(this.uids.get(object2));
            if (n2 == 0) {
                throw new AssertionError();
            }
            return n2;
        }

        int identityHashCode(Object object) {
            return System.identityHashCode((Object)object);
        }

        public String toString() {
            return "Ordering.arbitrary()";
        }

    }

    static class IncomparableValueException
    extends ClassCastException {
        private static final long serialVersionUID = 0;
        final Object value;
    }

}

