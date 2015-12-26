/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.Map$Entry
 */
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

public class aay<K, V> {
    private final aay<K, V> a;
    private final aay<K, V> b;

    public aay(int n2, int n3, float f2, boolean bl2) {
        if (bl2) {
            this.a = null;
            this.b = new a(n2, n3, f2);
            return;
        }
        this.a = new b(n2, n3, f2);
        this.b = null;
    }

    private static <V> V a(SoftReference<V> softReference) {
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    public V a(Object object) {
        if (this.a != null) {
            return aay.a((SoftReference)this.a.get(object));
        }
        return this.b.get(object);
    }

    public V a(K k2, V v2) {
        if (this.a != null) {
            return aay.a((SoftReference)this.a.put(k2, new SoftReference<V>(v2)));
        }
        return (V)this.b.put(k2, v2);
    }

    public void a() {
        if (this.a != null) {
            this.a.clear();
            return;
        }
        this.b.clear();
    }

    public V b(K k2) {
        if (this.a != null) {
            return aay.a((SoftReference)this.a.remove(k2));
        }
        return (V)this.b.remove(k2);
    }

    class a
    extends LinkedHashMap<K, V> {
        private final int b;

        public a(int n2, int n3, float f2) {
            super(n2, f2, true);
            this.b = n3;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
            if (this.size() > this.b) {
                return true;
            }
            return false;
        }
    }

    class b
    extends LinkedHashMap<K, SoftReference<V>> {
        private final int b;

        public b(int n2, int n3, float f2) {
            super(n2, f2, true);
            this.b = n3;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, SoftReference<V>> entry) {
            if (this.size() > this.b) {
                return true;
            }
            return false;
        }
    }

}

