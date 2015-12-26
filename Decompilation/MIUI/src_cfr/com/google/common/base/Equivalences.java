/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.System
 */
package com.google.common.base;

import com.google.common.base.Equivalence;
import java.io.Serializable;

public final class Equivalences {
    private Equivalences() {
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    private static final class Equals
    extends Equivalence<Object>
    implements Serializable {
        static final Equals INSTANCE = new Equals();
        private static final long serialVersionUID = 1;

        private Equals() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override
        protected boolean doEquivalent(Object object, Object object2) {
            return object.equals(object2);
        }

        @Override
        public int doHash(Object object) {
            return object.hashCode();
        }
    }

    private static final class Identity
    extends Equivalence<Object>
    implements Serializable {
        static final Identity INSTANCE = new Identity();
        private static final long serialVersionUID = 1;

        private Identity() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override
        protected boolean doEquivalent(Object object, Object object2) {
            return false;
        }

        @Override
        protected int doHash(Object object) {
            return System.identityHashCode((Object)object);
        }
    }

}

