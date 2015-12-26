/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class asz
extends asv {
    @Override
    public boolean a(String string2) {
        Matcher matcher = Pattern.compile((String)"\u56de|\u7f16\u8f91|\u53d1\u9001").matcher((CharSequence)string2);
        string2 = Pattern.compile((String)"\u5e8f\u53f7|\u5e8f\u5217|\u7f16\u7801|\u8bc4\u4ef7|\u66f4\u591a\u4e1a\u52a1|\u4e1a\u52a1\u79cd\u7c7b|\u83b7\u53d6\u670d\u52a1").matcher((CharSequence)string2);
        if (matcher.find() && string2.find()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean b(String string2) {
        if (string2.length() == 1 || Pattern.compile((String)"[a-z0-9A-Z:/]+").matcher((CharSequence)string2).matches()) {
            return true;
        }
        return false;
    }
}

