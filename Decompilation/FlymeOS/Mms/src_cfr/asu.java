/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
public class asu
implements Comparable {
    public boolean a;
    public boolean b = false;
    private String c;
    private Integer d;
    private String e = "";
    private String f = "0";
    private String g = "";

    public String a() {
        return this.c;
    }

    public void a(Integer n2) {
        this.d = n2;
    }

    public void a(String string2) {
        this.c = string2;
    }

    public void a(boolean bl2) {
        this.a = bl2;
    }

    public String b() {
        return this.g;
    }

    public void b(String string2) {
        this.g = string2;
    }

    public void c(String string2) {
        this.e = string2;
    }

    public boolean c() {
        return this.a;
    }

    public int compareTo(Object object) {
        object = (asu)object;
        return this.a().compareTo(object.a());
    }

    public Integer d() {
        return this.d;
    }

    public void d(String string2) {
        this.f = string2;
    }

    public String e() {
        return this.e;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        boolean bl2 = false;
        if (this == object) {
            return true;
        }
        boolean bl3 = bl2;
        if (object == null) return bl3;
        bl3 = bl2;
        if (this.getClass() != object.getClass()) return bl3;
        object = (asu)object;
        bl3 = bl2;
        if (this.a != object.a) return bl3;
        return this.c.equals((Object)object.c);
    }

    public String f() {
        return this.f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int hashCode() {
        int n2;
        int n3 = this.c.hashCode();
        if (this.a) {
            n2 = 1;
            do {
                return n2 + n3 * 31;
                break;
            } while (true);
        }
        n2 = 0;
        return n2 + n3 * 31;
    }

    public String toString() {
        return "Reply{content='" + this.c + '\'' + ", priority=" + (Object)this.d + ", desc='" + this.e + '\'' + ", phone='" + this.f + '\'' + ", underline='" + this.g + '\'' + ", editable=" + this.a + ", isMulti=" + this.b + '}';
    }
}

