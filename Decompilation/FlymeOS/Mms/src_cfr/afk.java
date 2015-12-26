/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Arrays
 *  java.util.HashSet
 */
import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class afk
implements afb {
    private static Set<String> a = new HashSet((Collection)Arrays.asList((Object[])new String[]{"X-PHONETIC-FIRST-NAME", "X-PHONETIC-MIDDLE-NAME", "X-PHONETIC-LAST-NAME", "X-ABADR", "X-ABUID"}));
    private static Set<String> b = new HashSet((Collection)Arrays.asList((Object[])new String[]{"X-GNO", "X-GN", "X-REDUCTION"}));
    private static Set<String> c = new HashSet((Collection)Arrays.asList((Object[])new String[]{"X-MICROSOFT-ASST_TEL", "X-MICROSOFT-ASSISTANT", "X-MICROSOFT-OFFICELOC"}));
    private static Set<String> d = new HashSet((Collection)Arrays.asList((Object[])new String[]{"X-SD-VERN", "X-SD-FORMAT_VER", "X-SD-CATEGORIES", "X-SD-CLASS", "X-SD-DCREATED", "X-SD-DESCRIPTION"}));
    private static String e = "X-SD-CHAR_CODE";
    private int f = 0;
    private int g = -1;
    private String h;

    @Override
    public void a() {
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void a(afj object) {
        String string2 = object.a();
        object = object.d();
        if (string2.equalsIgnoreCase("VERSION") && object.size() > 0) {
            if ((object = object.get(0)).equals((Object)"2.1")) {
                this.g = 0;
            } else if (object.equals((Object)"3.0")) {
                this.g = 1;
            } else if (object.equals((Object)"4.0")) {
                this.g = 2;
            } else {
                Log.w((String)"vCard", (String)("Invalid version string: " + (String)object));
            }
        } else if (string2.equalsIgnoreCase(e)) {
            this.f = 3;
            if (object.size() > 0) {
                this.h = (String)object.get(0);
            }
        }
        if (this.f != 0) {
            return;
        }
        if (c.contains(string2)) {
            this.f = 4;
            return;
        }
        if (d.contains(string2)) {
            this.f = 3;
            return;
        }
        if (b.contains(string2)) {
            this.f = 2;
            return;
        }
        if (!a.contains(string2)) return;
        this.f = 1;
    }

    @Override
    public void b() {
    }

    @Override
    public void c() {
    }

    @Override
    public void d() {
    }

    public int e() {
        switch (this.f) {
            default: {
                if (this.g != 0) break;
                return -1073741824;
            }
            case 3: {
                return 939524104;
            }
            case 2: {
                return 402653192;
            }
        }
        if (this.g == 1) {
            return -1073741823;
        }
        if (this.g == 2) {
            return -1073741822;
        }
        return 0;
    }
}

