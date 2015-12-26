/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Pair
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import android.content.Context;
import android.util.Pair;
import com.ted.android.contacts.bubble.SmsCoreDatabaseHelper;
import com.ted.android.contacts.bubble.SmsCoreEngine;
import com.ted.android.smscard.CardBase;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class apx {
    public static List<Pair<String, String>> a;
    private static final String b;
    private static apx c;
    private Context d;

    static {
        b = apx.class.getSimpleName();
        a = new ArrayList();
    }

    private apx(Context context) {
        this.d = context;
        SmsCoreEngine.init(context);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static apx a(Context context) {
        if (c == null) {
            synchronized (apx.class) {
                c = new apx(context);
            }
        }
        return c;
    }

    private CardBase a(bn bn2, String string2) {
        if (Pattern.compile((String)bn2.b).matcher((CharSequence)string2).find()) {
            return bn2.a(string2);
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public CardBase a(String string2, String string3) {
        Object object;
        Object object2;
        Object object3;
        block4 : {
            object = null;
            object2 = SmsCoreEngine.searchCard(atd.a().a(string2), string3);
            if (object2 == null) return null;
            if (object2.length != 0) {
                object3 = new StringBuilder();
                object3.append("ids: ");
                int n2 = 0;
                do {
                    if (n2 >= object2.length) {
                        TedSDKLog.d(b, object3.toString());
                        if (object2 == null || object2.length == 0 || (object2 = (Object)SmsCoreDatabaseHelper.a(this.d).a((int[])object2)) == null || object2.size() <= 0) break;
                        object3 = object2.iterator();
                        break block4;
                    }
                    object3.append(object2[n2]).append(" ");
                    ++n2;
                } while (true);
            }
            return null;
        }
        while (object3.hasNext()) {
            object2 = (bn)object3.next();
            if (this.b(string3, String.valueOf((int)object2.a))) continue;
            object = object2 = this.a((bn)object2, string2);
            if (object2 != null) break;
            object = object2;
        }
        return object;
    }

    public boolean b(String string2, String string3) {
        Iterator<Pair<String, String>> iterator = a.iterator();
        do {
            if (!iterator.hasNext()) {
                return false;
            }
            Pair<String, String> pair = iterator.next();
        } while (!string2.equals(pair.first) || !string3.equals(pair.second));
        return true;
    }
}

