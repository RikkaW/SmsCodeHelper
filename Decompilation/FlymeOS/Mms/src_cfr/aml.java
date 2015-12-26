/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
public class aml {
    private final boolean a;
    private final String b;

    private aml(boolean bl2, String string2) {
        this.a = bl2;
        this.b = string2;
    }

    protected static aml a() {
        return new aml(true, null);
    }

    protected static aml a(String string2) {
        return new aml(false, string2);
    }

    public boolean b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }
}

