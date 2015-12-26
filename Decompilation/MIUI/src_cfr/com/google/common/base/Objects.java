/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.base;

import com.google.common.base.Preconditions;

public final class Objects {
    private Objects() {
    }

    public static boolean equal(Object object, Object object2) {
        if (object == object2 || object != null && object.equals(object2)) {
            return true;
        }
        return false;
    }

    public static <T> T firstNonNull(T t, T t2) {
        if (t != null) {
            return t;
        }
        return Preconditions.checkNotNull(t2);
    }

    private static String simpleName(Class<?> object) {
        int n;
        object = object.getName().replaceAll("\\$[0-9]+", "\\$");
        int n2 = n = object.lastIndexOf(36);
        if (n == -1) {
            n2 = object.lastIndexOf(46);
        }
        return object.substring(n2 + 1);
    }

    public static ToStringHelper toStringHelper(Object object) {
        return new ToStringHelper(Objects.simpleName(object.getClass()));
    }

    public static final class ToStringHelper {
        private final StringBuilder builder;
        private boolean needsSeparator = false;

        private ToStringHelper(String string2) {
            Preconditions.checkNotNull(string2);
            this.builder = new StringBuilder(32).append(string2).append('{');
        }

        private StringBuilder checkNameAndAppend(String string2) {
            Preconditions.checkNotNull(string2);
            return this.maybeAppendSeparator().append(string2).append('=');
        }

        private StringBuilder maybeAppendSeparator() {
            if (this.needsSeparator) {
                return this.builder.append(", ");
            }
            this.needsSeparator = true;
            return this.builder;
        }

        public ToStringHelper add(String string2, int n) {
            this.checkNameAndAppend(string2).append(n);
            return this;
        }

        public ToStringHelper add(String string2, Object object) {
            this.checkNameAndAppend(string2).append(object);
            return this;
        }

        public ToStringHelper addValue(Object object) {
            this.maybeAppendSeparator().append(object);
            return this;
        }

        public String toString() {
            try {
                String string2 = this.builder.append('}').toString();
                return string2;
            }
            finally {
                this.builder.setLength(this.builder.length() - 1);
            }
        }
    }

}

