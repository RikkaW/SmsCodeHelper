/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.view.menu;

class BaseWrapper<T> {
    final T mWrappedObject;

    BaseWrapper(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.mWrappedObject = t2;
    }

    public T getWrappedObject() {
        return this.mWrappedObject;
    }
}

