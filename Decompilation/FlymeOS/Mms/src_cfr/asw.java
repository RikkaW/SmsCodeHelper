/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class asw
extends asv {
    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(asu asu2) {
        boolean bl2 = !Pattern.compile((String)"[0-9a-zA-Z]+").matcher((CharSequence)asu2.a()).matches();
        asu2.a(bl2);
    }

    @Override
    public boolean b(String string2) {
        if (string2.equals((Object)"\u4e1a\u52a1\u5e8f\u53f7")) {
            return false;
        }
        return true;
    }
}

