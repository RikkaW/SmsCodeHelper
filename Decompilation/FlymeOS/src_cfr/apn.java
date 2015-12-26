/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
public class apn {
    public static final String[] a = new String[]{"\u65e0\u6807\u8bb0", "\u5e7f\u544a\u63a8\u9500", "\u4fdd\u9669\u9500\u552e", "\u7591\u4f3c\u8bc8\u9a97", "\u9a9a\u6270\u7535\u8bdd", "\u623f\u4ea7\u4e2d\u4ecb", "\u5feb\u9012\u670d\u52a1", "\u5916\u5356\u9001\u9910", "\u51fa\u79df\u53f8\u673a", "\u62db\u8058\u730e\u5934", "\u91d1\u878d\u7406\u8d22"};

    public static int a(String string2) {
        if ("\u65e0\u6807\u8bb0".equals((Object)string2)) {
            return -1;
        }
        if ("\u7591\u4f3c\u8bc8\u9a97".equals((Object)string2) || "\u9a9a\u6270\u7535\u8bdd".equals((Object)string2)) {
            return 0;
        }
        if ("\u5e7f\u544a\u63a8\u9500".equals((Object)string2) || "\u623f\u4ea7\u4e2d\u4ecb".equals((Object)string2) || "\u4fdd\u9669\u9500\u552e".equals((Object)string2)) {
            return 1;
        }
        if ("\u5feb\u9012\u670d\u52a1".equals((Object)string2) || "\u5916\u5356\u9001\u9910".equals((Object)string2) || "\u62db\u8058\u730e\u5934".equals((Object)string2) || "\u51fa\u79df\u53f8\u673a".equals((Object)string2) || "\u91d1\u878d\u7406\u8d22".equals((Object)string2)) {
            return 2;
        }
        return Integer.MIN_VALUE;
    }
}

