/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 */
public class aa
implements u {
    private static char a(char c2) {
        if (c2 == '\\' || c2 == '\"') {
            return c2;
        }
        if (c2 == 'n') {
            return '\n';
        }
        if (c2 == 'r') {
            return '\r';
        }
        if (c2 == 't') {
            return '\t';
        }
        throw new s("\u5b57\u7b26\u8f6c\u4e49\u51fa\u9519");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public p a(r var1_1) {
        var4_2 = var1_1.a();
        var6_3 = new StringBuffer();
        var5_4 = var1_1.read();
        if (var5_4 == -1) throw new s("\u4e0d\u662f\u6709\u6548\u7684\u5b57\u7b26\u7a9c\u5f00\u59cb");
        if (var5_4 == 34) ** GOTO lbl15
        throw new s("\u4e0d\u662f\u6709\u6548\u7684\u5b57\u7b26\u7a9c\u5f00\u59cb");
lbl-1000: // 1 sources:
        {
            var3_6 = (char)var5_4;
            if (var3_6 == '\\') {
                var2_5 = aa.a((char)var1_1.read());
            } else {
                var2_5 = var3_6;
                if (var3_6 == '\"') {
                    return new p(var6_3.toString(), var4_2, p.a.b);
                }
            }
            var6_3.append(var2_5);
lbl15: // 2 sources:
            ** while ((var5_4 = var1_1.read()) != -1)
        }
lbl16: // 1 sources:
        throw new s("\u4e0d\u662f\u6709\u6548\u7684\u5b57\u7b26\u7a9c\u7ed3\u675f");
    }
}

