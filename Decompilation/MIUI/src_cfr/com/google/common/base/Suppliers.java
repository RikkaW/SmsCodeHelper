/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.base;

import com.google.common.base.Supplier;
import java.io.Serializable;

public final class Suppliers {
    private Suppliers() {
    }

    static class ExpiringMemoizingSupplier<T>
    implements Supplier<T>,
    Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        final long durationNanos;
    }

    static class MemoizingSupplier<T>
    implements Supplier<T>,
    Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
    }

}

