/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
public class aho {
    public double a = 0.0;
    public double b = 0.0;
    public float c = 0.0f;
    int d = -1;
    private long e = -1;

    public long a() {
        return this.e;
    }

    public void a(long l2) {
        if (l2 >= 0) {
            this.e = ahz.b() + l2;
            return;
        }
        this.e = l2;
    }

    public String b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a).append("#").append(this.b).append("#").append(this.c);
        return stringBuilder.toString();
    }
}

