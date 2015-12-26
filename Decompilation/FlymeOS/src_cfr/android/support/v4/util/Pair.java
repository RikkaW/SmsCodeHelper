/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.util;

public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F f2, S s2) {
        this.first = f2;
        this.second = s2;
    }

    public static <A, B> Pair<A, B> create(A a2, B b2) {
        return new Pair<A, B>(a2, b2);
    }

    private static boolean objectsEqual(Object object, Object object2) {
        if (object == object2 || object != null && object.equals(object2)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        if (!(object instanceof Pair)) {
            return false;
        }
        object = (Pair)object;
        if (!Pair.objectsEqual(object.first, this.first)) return false;
        if (!Pair.objectsEqual(object.second, this.second)) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n2 = 0;
        int n3 = this.first == null ? 0 : this.first.hashCode();
        if (this.second == null) {
            return n3 ^ n2;
        }
        n2 = this.second.hashCode();
        return n3 ^ n2;
    }
}

