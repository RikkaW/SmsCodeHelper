/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 *  java.util.Date
 */
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ad {
    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static m a(String var0, int var1_1, l[] var2_2) {
        block22 : {
            block21 : {
                if (var0 == null) {
                    throw new IllegalArgumentException("\u51fd\u6570\u540d\u4e3a\u7a7a");
                }
                var4_5 = ae.a(var0);
                var5_6 = var4_5.getParameterTypes();
                if (var2_2.length != var5_6.length) throw new i("\u51fd\u6570\"" + var0 + "\"\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d", var0, var1_1);
                var3_7 = var2_2.length - 1;
                do {
                    if (var3_7 < 0) {
                        var2_2 = var4_5.getReturnType();
                        if (Boolean.TYPE == var2_2) return new m(l.a.c, (Object)Boolean.FALSE);
                        if (Boolean.class != var2_2) break;
                        return new m(l.a.c, (Object)Boolean.FALSE);
                    }
                    var6_8 = var2_2[var3_7].n();
                    if (var6_8 != null && !ad.a(var5_6[var5_6.length - var3_7 - 1], var6_8)) {
                        throw new i("\u51fd\u6570\"" + var0 + "\"\u53c2\u6570\u7c7b\u578b\u4e0d\u5339\u914d,\u51fd\u6570\u53c2\u6570\u5b9a\u4e49\u7c7b\u578b\u4e3a\uff1a" + var5_6[var3_7].getName() + " \u4f20\u5165\u53c2\u6570\u5b9e\u9645\u7c7b\u578b\u4e3a\uff1a" + var6_8.getName(), var0, var1_1);
                    }
                    --var3_7;
                } while (true);
                if (Date.class == var2_2) {
                    return new m(l.a.h, null);
                }
                if (Double.TYPE != var2_2 && Double.class != var2_2) ** GOTO lbl24
                return new m(l.a.g, 0.0);
lbl24: // 1 sources:
                if (Float.TYPE != var2_2 && Float.class != var2_2) ** GOTO lbl27
                return new m(l.a.f, (Object)Float.valueOf((float)0.0f));
lbl27: // 1 sources:
                if (Integer.TYPE != var2_2 && Integer.class != var2_2) ** GOTO lbl30
                return new m(l.a.d, 0);
lbl30: // 1 sources:
                if (Long.TYPE != var2_2 && Long.class != var2_2) break block21;
                return new m(l.a.e, 0);
            }
            if (String.class == var2_2) {
                return new m(l.a.b, null);
            }
            if (List.class == var2_2) {
                return new m(l.a.i, null);
            }
            if (Object.class != var2_2) ** GOTO lbl43
            return new m(l.a.j, null);
lbl43: // 1 sources:
            if (Void.TYPE == var2_2) break block22;
            if (Void.class != var2_2) throw new IllegalStateException("\u89e3\u6790\u5668\u5185\u90e8\u9519\u8bef\uff1a\u4e0d\u652f\u6301\u7684\u51fd\u6570\u8fd4\u56de\u7c7b\u578b");
        }
        try {
            return new m(l.a.j, null);
        }
        catch (SecurityException var2_3) {
            throw new i("\u51fd\u6570\"" + var0 + "\"\u4e0d\u5b58\u5728\u6216\u53c2\u6570\u7c7b\u578b\u4e0d\u5339\u914d", var0, var1_1);
        }
        catch (NoSuchMethodException var2_4) {
            throw new i("\u51fd\u6570\"" + var0 + "\"\u4e0d\u5b58\u5728\u6216\u53c2\u6570\u7c7b\u578b\u4e0d\u5339\u914d", var0, var1_1);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static m a(String string2, int n2, m[] object) {
        block22 : {
            block21 : {
                block20 : {
                    block19 : {
                        if (string2 == null) {
                            throw new IllegalArgumentException("\u51fd\u6570\u540d\u4e3a\u7a7a");
                        }
                        if (object == null) {
                            throw new IllegalArgumentException("\u51fd\u6570\u53c2\u6570\u5217\u8868\u4e3a\u7a7a");
                        }
                        int n3 = 0;
                        do {
                            if (n3 >= object.length) {
                                object = ad.b(string2, n2, (m[])object);
                                object = ae.a(string2, (Object[])object);
                                if (object instanceof Boolean) {
                                    return new m(l.a.c, object);
                                }
                                break block19;
                            }
                            if (object[n3].p()) {
                                object[n3] = ((n)object[n3].b()).b();
                            }
                            ++n3;
                        } while (true);
                        catch (i i2) {
                            throw new IllegalArgumentException("\u51fd\u6570\"" + string2 + "\"\u8fd0\u884c\u65f6\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
                        }
                    }
                    if (!(object instanceof Date)) break block20;
                    return new m(l.a.h, object);
                }
                if (!(object instanceof Double)) break block21;
                return new m(l.a.g, object);
            }
            if (!(object instanceof Float)) break block22;
            return new m(l.a.f, object);
        }
        try {
            if (object instanceof Integer) {
                return new m(l.a.d, object);
            }
        }
        catch (NoSuchMethodException var2_4) {
            var2_4.printStackTrace();
            throw new IllegalStateException("\u51fd\u6570\"" + string2 + "\"\u4e0d\u5b58\u5728\u6216\u53c2\u6570\u7c7b\u578b\u4e0d\u5339\u914d");
        }
        catch (IllegalArgumentException var2_5) {
            var2_5.printStackTrace();
            throw new IllegalStateException("\u51fd\u6570\"" + string2 + "\"\u53c2\u6570\u7c7b\u578b\u4e0d\u5339\u914d");
        }
        catch (Exception var2_6) {
            var2_6.printStackTrace();
            throw new IllegalStateException("\u51fd\u6570\"" + string2 + "\"\u8bbf\u95ee\u5f02\u5e38:" + var2_6.getMessage());
        }
        if (object instanceof Long) {
            return new m(l.a.e, object);
        }
        if (object instanceof String) {
            return new m(l.a.b, object);
        }
        if (!(object instanceof List)) return new m(l.a.j, object);
        return new m(l.a.i, object);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean a(Class<?> class_, Class<?> class_2) {
        if (Object.class == class_ || class_ == class_2) return true;
        if (Double.TYPE == class_) {
            if (Float.TYPE == class_2 || Long.TYPE == class_2 || Integer.TYPE == class_2) return true;
            return false;
        }
        if (Double.class == class_) {
            if (Double.TYPE == class_2) return true;
            return false;
        }
        if (Float.TYPE == class_) {
            if (Long.TYPE == class_2 || Integer.TYPE == class_2) return true;
            return false;
        }
        if (Float.class == class_) {
            if (Float.TYPE == class_2) return true;
            return false;
        }
        if (Long.TYPE == class_) {
            if (Integer.TYPE == class_2) return true;
            return false;
        }
        if (Long.class == class_) {
            if (Long.TYPE == class_2) return true;
            return false;
        }
        if (Integer.class != class_) {
            return false;
        }
        if (Integer.TYPE != class_2) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Object[] b(String string2, int n2, m[] arrm) {
        if (arrm == null) {
            return new Object[0];
        }
        Object[] arrobject = new Object[arrm.length];
        n2 = arrm.length - 1;
        do {
            Object[] arrobject2 = arrobject;
            if (n2 < 0) return arrobject2;
            try {
                arrobject[arrm.length - 1 - n2] = arrm[n2].o();
                --n2;
                continue;
            }
            catch (ParseException var2_3) {}
            throw new i("\u51fd\u6570\"" + string2 + "\"\u53c2\u6570\u8f6c\u5316Java\u5bf9\u8c61\u9519\u8bef");
        } while (true);
    }
}

