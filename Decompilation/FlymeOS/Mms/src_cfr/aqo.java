/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import android.text.TextUtils;
import com.ted.android.data.BubbleEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aqo
extends aqc {
    private static final String a = aqo.class.getSimpleName();
    private static final Pattern b = Pattern.compile((String)"[\\d\\-/]{1,5}");
    private static final Pattern[] c = new Pattern[]{aqp.c, aru.c, arv.c, arw.c, arx.c, ary.c};
    private static aqo d;
    private long e;
    private List<aqm> f = new ArrayList();

    private aqo() {
        this.f.add(new aqp());
        this.f.add(new aru());
        this.f.add(new arv());
        this.f.add(new arw());
        this.f.add(new arx());
        this.f.add(new ary());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aqo a() {
        if (d == null) {
            synchronized (aqo.class) {
                d = new aqo();
            }
        }
        return d;
    }

    public static String a(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            Pattern[] arrpattern = c;
            int n2 = arrpattern.length;
            int n3 = 0;
            String string3 = string2;
            block0 : do {
                if (n3 >= n2) {
                    return string3;
                }
                Matcher matcher = arrpattern[n3].matcher((CharSequence)string2);
                do {
                    if (!matcher.find()) {
                        ++n3;
                        continue block0;
                    }
                    String string4 = matcher.group(1);
                    if (b.matcher((CharSequence)string4).matches() || TextUtils.isEmpty((CharSequence)string4)) continue;
                    string3 = string3.replace((CharSequence)string4, (CharSequence)"");
                } while (true);
                break;
            } while (true);
        }
        return string2;
    }

    @Override
    public List<BubbleEntity> a(String string2, String string3) {
        ArrayList arrayList = new ArrayList();
        String[] arrstring = string2.split("\uff0c|\u3002|\uff1b|\uff01|\\n|\u548c|\u6216|\uff1a|\\.|,|;");
        int n2 = arrstring.length;
        int n3 = 0;
        block0 : while (n3 < n2) {
            string3 = arrstring[n3];
            Iterator<aqm> iterator = this.f.iterator();
            do {
                if (!iterator.hasNext()) {
                    ++n3;
                    continue block0;
                }
                string3 = iterator.next().a(string3, (List<BubbleEntity>)arrayList, this.e, string2);
            } while (true);
            break;
        }
        return arrayList;
    }

    public void a(long l2) {
        this.e = l2;
    }
}

