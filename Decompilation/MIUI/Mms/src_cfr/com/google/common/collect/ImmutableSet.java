/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.System
 *  java.util.ArrayList
 */
package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIndexedListIterator;
import com.google.common.collect.EmptyImmutableSet;
import com.google.common.collect.Hashing;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Iterators;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class ImmutableSet<E>
extends ImmutableCollection<E>
implements Set<E> {
    ImmutableSet() {
    }

    /*
     * Enabled aggressive block sorting
     */
    static int chooseTableSize(int n) {
        if (n < 536870912) {
            return Integer.highestOneBit((int)n) << 2;
        }
        boolean bl = n < 1073741824;
        Preconditions.checkArgument(bl, "collection too large");
        return 1073741824;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static /* varargs */ <E> ImmutableSet<E> construct(Object ... var0) {
        var5_1 = ImmutableSet.chooseTableSize(var0.length);
        var10_2 = new Object[var5_1];
        var6_3 = var5_1 - 1;
        var8_4 = null;
        var2_5 = 0;
        var1_6 = 0;
        block0 : do {
            if (var1_6 >= var0.length) {
                if (var0.length == 1) {
                    return new SingletonImmutableSet<Object>(var0[0], var2_5);
                }
                if (var5_1 <= ImmutableSet.chooseTableSize(var0.length) * 2) return new RegularImmutableSet<E>(var0, var2_5, var10_2, var6_3);
                return ImmutableSet.construct(var0);
            }
            var9_10 = var0[var1_6];
            var4_8 = var9_10.hashCode();
            var3_7 = Hashing.smear(var4_8);
            do {
                if ((var11_11 = var10_2[var7_9 = var3_7 & var6_3]) != null) ** GOTO lbl24
                if (var8_4 != null) {
                    var8_4.add(var9_10);
                }
                var10_2[var7_9] = var9_10;
                var3_7 = var2_5 + var4_8;
                var9_10 = var8_4;
                ** GOTO lbl38
lbl24: // 1 sources:
                if (var11_11.equals(var9_10)) {
                    var3_7 = var2_5;
                    var9_10 = var8_4;
                    if (var8_4 == null) {
                        var8_4 = new ArrayList(var0.length);
                        var4_8 = 0;
                        do {
                            var3_7 = var2_5;
                            var9_10 = var8_4;
                            if (var4_8 >= var1_6) ** break;
                            var8_4.add(var0[var4_8]);
                            ++var4_8;
                        } while (true);
                    } else {
                        ** GOTO lbl38
                    }
                }
                ** GOTO lbl42
lbl38: // 4 sources:
                ++var1_6;
                var2_5 = var3_7;
                var8_4 = var9_10;
                continue block0;
lbl42: // 1 sources:
                ++var3_7;
            } while (true);
            break;
        } while (true);
    }

    public static <E> ImmutableSet<E> copyOf(E[] arrE) {
        switch (arrE.length) {
            default: {
                return ImmutableSet.construct((Object[])arrE.clone());
            }
            case 0: {
                return ImmutableSet.of();
            }
            case 1: 
        }
        return ImmutableSet.of(arrE[0]);
    }

    public static <E> ImmutableSet<E> of() {
        return EmptyImmutableSet.INSTANCE;
    }

    public static <E> ImmutableSet<E> of(E e2) {
        return new SingletonImmutableSet<E>(e2);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof ImmutableSet && this.isHashCodeFast() && ((ImmutableSet)object).isHashCodeFast() && this.hashCode() != object.hashCode()) {
            return false;
        }
        return Sets.equalsImpl(this, object);
    }

    @Override
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    boolean isHashCodeFast() {
        return false;
    }

    @Override
    public abstract UnmodifiableIterator<E> iterator();

    @Override
    Object writeReplace() {
        return new SerializedForm(this.toArray());
    }

    static abstract class ArrayImmutableSet<E>
    extends ImmutableSet<E> {
        final transient Object[] elements;

        ArrayImmutableSet(Object[] arrobject) {
            this.elements = arrobject;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean containsAll(Collection<?> arrobject) {
            if (arrobject == this) {
                return true;
            }
            if (!(arrobject instanceof ArrayImmutableSet)) {
                return super.containsAll(arrobject);
            }
            if (arrobject.size() > this.size()) {
                return false;
            }
            arrobject = ((ArrayImmutableSet)arrobject).elements;
            int n = arrobject.length;
            int n2 = 0;
            while (n2 < n) {
                if (!this.contains(arrobject[n2])) {
                    return false;
                }
                ++n2;
            }
            return true;
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

        @Override
        public Object[] toArray() {
            Object[] arrobject = new Object[this.size()];
            System.arraycopy((Object)this.elements, (int)0, (Object)arrobject, (int)0, (int)this.size());
            return arrobject;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public <T> T[] toArray(T[] arrT) {
            T[] arrT2;
            int n = this.size();
            if (arrT.length < n) {
                arrT2 = ObjectArrays.newArray(arrT, n);
            } else {
                arrT2 = arrT;
                if (arrT.length > n) {
                    arrT[n] = null;
                    arrT2 = arrT;
                }
            }
            System.arraycopy((Object)this.elements, (int)0, arrT2, (int)0, (int)n);
            return arrT2;
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
            return ImmutableSet.copyOf(this.elements);
        }
    }

    static abstract class TransformedImmutableSet<D, E>
    extends ImmutableSet<E> {
        final int hashCode;
        final D[] source;

        TransformedImmutableSet(D[] arrD, int n) {
            this.source = arrD;
            this.hashCode = n;
        }

        @Override
        public final int hashCode() {
            return this.hashCode;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        boolean isHashCodeFast() {
            return true;
        }

        @Override
        public UnmodifiableIterator<E> iterator() {
            return new AbstractIndexedListIterator<E>(this.source.length){

                @Override
                protected E get(int n) {
                    return TransformedImmutableSet.this.transform(TransformedImmutableSet.this.source[n]);
                }
            };
        }

        @Override
        public int size() {
            return this.source.length;
        }

        @Override
        public Object[] toArray() {
            return this.toArray(new Object[this.size()]);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public <T> T[] toArray(T[] arrT) {
            T[] arrT2;
            int n = this.size();
            if (arrT.length < n) {
                arrT2 = ObjectArrays.newArray(arrT, n);
            } else {
                arrT2 = arrT;
                if (arrT.length > n) {
                    arrT[n] = null;
                    arrT2 = arrT;
                }
            }
            n = 0;
            while (n < this.source.length) {
                arrT2[n] = this.transform(this.source[n]);
                ++n;
            }
            return arrT2;
        }

        abstract E transform(D var1);

    }

}

