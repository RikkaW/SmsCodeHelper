/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Arrays
 *  java.util.Collections
 *  java.util.HashSet
 */
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class afh
extends afc {
    static final Set<String> a = Collections.unmodifiableSet((Set)new HashSet((Collection)Arrays.asList((Object[])new String[]{"BEGIN", "END", "LOGO", "PHOTO", "LABEL", "FN", "TITLE", "SOUND", "VERSION", "TEL", "EMAIL", "TZ", "GEO", "NOTE", "URL", "BDAY", "ROLE", "REV", "UID", "KEY", "MAILER", "NAME", "PROFILE", "SOURCE", "NICKNAME", "CLASS", "SORT-STRING", "CATEGORIES", "PRODID", "IMPP"})));
    static final Set<String> b = Collections.unmodifiableSet((Set)new HashSet((Collection)Arrays.asList((Object[])new String[]{"7BIT", "8BIT", "BASE64", "B"})));
    private final afe c;

    public afh() {
        this.c = new afe();
    }

    public afh(int n2) {
        this.c = new afe(n2);
    }

    @Override
    public void a(afb afb2) {
        this.c.a(afb2);
    }

    @Override
    public void a(InputStream inputStream) {
        this.c.a(inputStream);
    }
}

