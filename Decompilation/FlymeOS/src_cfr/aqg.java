/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.Context;
import android.text.TextUtils;
import com.ted.android.contacts.bubble.SmsCoreDatabaseHelper;
import com.ted.android.contacts.bubble.SmsCoreEngine;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.CommonAction;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class aqg
extends aqc {
    private static final String b = aqg.class.getSimpleName();
    private static aqg c;
    ash a;
    private Context d;
    private atd e;

    private aqg(Context context) {
        this.d = context;
        this.e = atd.a();
        this.a = new ash();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aqg a(Context context) {
        if (c == null) {
            synchronized (aqg.class) {
                c = new aqg(context);
            }
        }
        return c;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private List<BubbleEntity> a(bn bn2, String list, String string2) {
        int n2 = 0;
        ArrayList arrayList = new ArrayList();
        bn bn3 = bn2.a(this.d);
        try {}
        catch (PatternSyntaxException var1_2) {
            var1_2.printStackTrace();
            return arrayList;
        }
        Matcher matcher = Pattern.compile((String)bn3.b).matcher(list);
        if (!matcher.find()) return arrayList;
        int n3 = matcher.groupCount();
        int n4 = 0;
        while (n4 <= n3) {
            int n5;
            try {
                list = new JSONObject(bn3.d);
                String string3 = matcher.group(n4);
                TedSDKLog.d(b, "Matched Words: " + string3);
                if (TextUtils.isEmpty((CharSequence)string3) && n4 != 0) {
                    n5 = n2;
                } else {
                    int n6 = matcher.start(n4);
                    string2 = "{group" + n4 + "}";
                    String string4 = "\\{group" + n4 + "\\}";
                    n5 = n2;
                    if (bn3.d.contains((CharSequence)string2)) {
                        string2 = list.getString(string2);
                        n5 = n2;
                        if (!TextUtils.isEmpty((CharSequence)string2)) {
                            BubbleEntity bubbleEntity = new BubbleEntity();
                            if (n4 == 0) {
                                bubbleEntity.setIndex(-1);
                                list = string2;
                            } else {
                                bubbleEntity.setIndex(n6);
                                list = string2;
                                if (!TextUtils.isEmpty((CharSequence)string3)) {
                                    list = string2.replaceAll(string4, string3);
                                }
                            }
                            bubbleEntity.setId(String.valueOf((int)bn3.a));
                            bubbleEntity.setMatchedWords(string3);
                            bubbleEntity.setShowType(bn2.g);
                            list = this.a(bubbleEntity, (String)((Object)list));
                            if (list != null && list.size() != 0) {
                                bubbleEntity.addActions(list);
                            }
                            arrayList.add(bubbleEntity);
                            n5 = ++n2;
                            if (n2 >= 3) {
                                return arrayList;
                            }
                        }
                    }
                }
            }
            catch (JSONException var2_4) {
                n5 = n2;
            }
            catch (PatternSyntaxException var2_5) {
                var2_5.printStackTrace();
                n5 = n2;
            }
            ++n4;
            n2 = n5;
        }
        return arrayList;
    }

    private List<ActionBase> a(BubbleEntity bubbleEntity, String string2) {
        ArrayList arrayList = new ArrayList();
        string2 = new JSONArray(string2);
        int n2 = 0;
        do {
            block6 : {
                try {
                    if (n2 < string2.length()) break block6;
                    return arrayList;
                }
                catch (JSONException var1_2) {
                    return arrayList;
                }
            }
            CommonAction commonAction = new CommonAction(bubbleEntity, string2.getString(n2));
            if (!(commonAction.action == 3 && TextUtils.isEmpty((CharSequence)commonAction.url) || commonAction.action == 8 && TextUtils.isEmpty((CharSequence)commonAction.number) || commonAction.action == 10 && TextUtils.isEmpty((CharSequence)commonAction.address) || "\u963f\u91cc\u5df4\u5df4".equals((Object)commonAction.buttonText))) {
                arrayList.add(commonAction);
            }
            ++n2;
        } while (true);
    }

    private List<BubbleEntity> a(String string2, String object, String string3) {
        ArrayList arrayList = new ArrayList();
        if ((object = (Object)SmsCoreEngine.searchBubble((String)object, string3)) == null || object.length == 0) {
            return arrayList;
        }
        new StringBuilder();
        object = SmsCoreDatabaseHelper.a(this.d).a((int[])object).iterator();
        while (object.hasNext()) {
            List<BubbleEntity> list = this.a((bn)object.next(), string2, string3);
            if (list == null || list.size() <= 0) continue;
            arrayList.addAll(list);
        }
        return arrayList;
    }

    @Override
    protected List<BubbleEntity> a(String string2, String string3) {
        return this.a(string2, this.e.a(string2), string3);
    }
}

