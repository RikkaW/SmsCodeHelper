/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.String
 */
import android.util.Log;
import java.util.Set;

class afe
extends afd {
    private String g;
    private boolean h = false;

    public afe() {
    }

    public afe(int n2) {
        super(n2);
    }

    public static String b(char c2) {
        if (c2 == 'n' || c2 == 'N') {
            return "\n";
        }
        return String.valueOf((char)c2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(afj afj2, String string2, String string3) {
        int n2 = string3.length();
        int n3 = 0;
        boolean bl2 = false;
        StringBuilder stringBuilder = null;
        while (n3 < n2) {
            char c2 = string3.charAt(n3);
            if (c2 == '\"') {
                if (bl2) {
                    afj2.a(string2, this.e(stringBuilder.toString()));
                    bl2 = false;
                    stringBuilder = null;
                } else {
                    if (stringBuilder != null) {
                        if (stringBuilder.length() > 0) {
                            Log.w((String)"vCard", (String)"Unexpected Dquote inside property.");
                        } else {
                            afj2.a(string2, this.e(stringBuilder.toString()));
                        }
                    }
                    bl2 = true;
                }
            } else if (c2 == ',' && !bl2) {
                if (stringBuilder == null) {
                    Log.w((String)"vCard", (String)("Comma is used before actual string comes. (" + string3 + ")"));
                } else {
                    afj2.a(string2, this.e(stringBuilder.toString()));
                    stringBuilder = null;
                }
            } else {
                StringBuilder stringBuilder2 = stringBuilder;
                if (stringBuilder == null) {
                    stringBuilder2 = new StringBuilder();
                }
                stringBuilder2.append(c2);
                stringBuilder = stringBuilder2;
            }
            ++n3;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String f(String string2) {
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = string2.length();
        int n3 = 0;
        while (n3 < n2) {
            char c2 = string2.charAt(n3);
            if (c2 == '\\' && n3 < n2 - 1) {
                if ((c2 = string2.charAt(++n3)) == 'n' || c2 == 'N') {
                    stringBuilder.append("\n");
                } else {
                    stringBuilder.append(c2);
                }
            } else {
                stringBuilder.append(c2);
            }
            ++n3;
        }
        return stringBuilder.toString();
    }

    @Override
    protected String a() {
        if (this.g != null) {
            String string2 = this.g;
            this.g = null;
            return string2;
        }
        return this.d.readLine();
    }

    @Override
    protected void a(afj afj2) {
        if (!this.h) {
            Log.w((String)"vCard", (String)"AGENT in vCard 3.0 is not supported yet. Ignore it");
            this.h = true;
        }
    }

    @Override
    protected void a(afj afj2, String string2) {
        try {
            super.a(afj2, string2);
            return;
        }
        catch (afn var3_3) {
            String[] arrstring = string2.split("=", 2);
            if (arrstring.length == 2) {
                this.a(afj2, arrstring[0], arrstring[1]);
                return;
            }
            throw new afn("Unknown params value: " + string2);
        }
    }

    @Override
    protected void a(afj afj2, String string2, String string3) {
        this.b(afj2, string2, string3);
    }

    @Override
    protected boolean a(boolean bl2) {
        return super.a(bl2);
    }

    @Override
    protected void b(afj afj2, String string2) {
        this.c(afj2, string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected String c() {
        void var1_6;
        void var1_3;
        String string2;
        java.lang.Object var3_1 = null;
        java.lang.Object var1_2 = null;
        while ((string2 = this.d.readLine()) != null) {
            if (string2.length() == 0) continue;
            if (string2.charAt(0) == ' ' || string2.charAt(0) == '\t') {
                StringBuilder stringBuilder = var1_3;
                if (var1_3 == null) {
                    stringBuilder = new StringBuilder();
                }
                if (this.g != null) {
                    stringBuilder.append(this.g);
                    this.g = null;
                }
                stringBuilder.append(string2.substring(1));
                StringBuilder stringBuilder2 = stringBuilder;
                continue;
            }
            if (var1_3 != null || this.g != null) break;
            this.g = string2;
        }
        if (var1_3 != null) {
            String string3 = var1_3.toString();
        } else {
            java.lang.Object var1_7 = var3_1;
            if (this.g != null) {
                String string4 = this.g;
            }
        }
        this.g = string2;
        if (var1_6 == null) {
            throw new afn("Reached end of buffer.");
        }
        return var1_6;
    }

    @Override
    protected String c(String string2) {
        return string2;
    }

    @Override
    protected void c(afj afj2, String string2) {
        this.b(afj2, "TYPE", string2);
    }

    @Override
    protected String d(String string2) {
        return afe.f(string2);
    }

    protected String e(String string2) {
        return afl.a(string2, "ISO-8859-1", "UTF-8");
    }

    @Override
    protected int f() {
        return 1;
    }

    @Override
    protected String g() {
        return "3.0";
    }

    @Override
    protected Set<String> h() {
        return afh.a;
    }
}

