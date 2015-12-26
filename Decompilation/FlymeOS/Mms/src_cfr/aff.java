/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
import java.util.Set;

class aff
extends afe {
    public static String c(char c2) {
        if (c2 == 'n' || c2 == 'N') {
            return "\n";
        }
        return String.valueOf((char)c2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String g(String string2) {
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
    protected String d(String string2) {
        return aff.g(string2);
    }

    @Override
    protected int f() {
        return 2;
    }

    @Override
    protected String g() {
        return "4.0";
    }

    @Override
    protected Set<String> h() {
        return afi.a;
    }
}

