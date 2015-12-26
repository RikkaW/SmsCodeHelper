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

public class asx
extends asv {
    @Override
    public boolean a(String string2) {
        return Pattern.compile((String)"\u76f4\u63a5\u56de\u590d10\u4e3a\u975e\u5e38\u6ee1\u610f").matcher((CharSequence)string2).find();
    }
}

