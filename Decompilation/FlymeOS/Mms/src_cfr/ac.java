/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 */
public class ac
implements u {
    private String b(r r2) {
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = 1;
        int n3;
        while ((n3 = r2.read()) != -1) {
            char c2 = (char)n3;
            if ("+-*/%^<>=&|!?:#$(),[]'\" \r\n\t".indexOf((int)c2) >= 0 && n2 == 0) {
                r2.reset();
                return stringBuffer.toString();
            }
            if (!Character.isJavaIdentifierPart((char)c2)) {
                throw new s("\u540d\u79f0\u4e0d\u80fd\u4e3a\u975e\u6cd5\u5b57\u7b26\uff1a" + c2);
            }
            n3 = n2;
            if (n2 != 0) {
                if (!Character.isJavaIdentifierStart((char)c2)) {
                    throw new s("\u540d\u79f0\u5f00\u5934\u4e0d\u80fd\u4e3a\u5b57\u7b26\uff1a" + c2);
                }
                n3 = 0;
            }
            stringBuffer.append(c2);
            r2.mark(0);
            n2 = n3;
        }
        return stringBuffer.toString();
    }

    @Override
    public p a(r object) {
        int n2 = object.a();
        if ("true".equals(object = this.b((r)((Object)object))) || "false".equals(object)) {
            return new p((String)object, n2, p.a.c);
        }
        if ("null".equals(object)) {
            return new p((String)object, n2, p.a.a);
        }
        return new p((String)object, n2, p.a.i);
    }
}

