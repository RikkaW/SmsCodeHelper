/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.ted.android.contacts.bubble.SmsCoreDatabaseHelper;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.CommonAction;
import com.ted.android.smscard.CardBase;
import com.ted.android.smscard.CardBaseUtils;
import com.ted.android.smscard.CardbaseFactory;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class bn {
    private static final String j = bn.class.getSimpleName();
    private static final Pattern k = Pattern.compile((String)"(\\d+)");
    public int a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g = 0;
    public String h;
    public String i;

    private int a(int n2) {
        int n3 = n2;
        if (n2 > 100000) {
            n3 = n2 / 100000 * 100000;
        }
        return n3;
    }

    private String a(String string2, Matcher matcher) {
        int n2 = 1;
        while (n2 <= matcher.groupCount()) {
            String string3 = "{group" + n2 + "}";
            String string4 = string2;
            if (string2.indexOf(string3) > -1) {
                string4 = string2.replace((CharSequence)string3, (CharSequence)matcher.group(n2));
            }
            ++n2;
            string2 = string4;
        }
        return string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<bn> a(Cursor cursor, boolean bl2) {
        ArrayList arrayList = new ArrayList();
        if (cursor == null || cursor.getCount() <= 0) return arrayList;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            arrayList.add(bn.b(cursor, bl2));
            cursor.moveToNext();
        }
        return arrayList;
    }

    private void a(String string2, CardBase cardBase, Matcher matcher) {
        block6 : {
            ArrayList arrayList;
            int n2;
            try {
                TedSDKLog.d(j, "MenuString: " + string2);
                string2 = new JSONObject(string2).getJSONArray("{group0}");
                arrayList = new ArrayList();
                if (string2 == null) break block6;
                n2 = 0;
            }
            catch (Exception var1_2) {
                var1_2.printStackTrace();
            }
            do {
                if (n2 >= string2.length()) {
                    if (arrayList.size() > 0) {
                        cardBase.setActions((List<ActionBase>)arrayList);
                        return;
                    }
                    break;
                }
                arrayList.add(new CommonAction(null, this.a(string2.getString(n2), matcher)));
                ++n2;
            } while (true);
        }
    }

    private static bn b(Cursor cursor, boolean bl2) {
        String string2;
        bn bn2 = new bn();
        bn2.a = cursor.getInt(cursor.getColumnIndex("b_id"));
        String string3 = string2 = cursor.getString(cursor.getColumnIndex("b_regex"));
        if (bl2) {
            string3 = anv.b(string2, DataBus.FILE_MASK);
        }
        bn2.b = string3;
        bn2.c = cursor.getString(cursor.getColumnIndex("b_number"));
        bn2.d = cursor.getString(cursor.getColumnIndex("b_json_string"));
        bn2.d = cursor.getString(cursor.getColumnIndex("b_json_string"));
        bn2.e = cursor.getString(cursor.getColumnIndex("b_keys"));
        bn2.f = cursor.getString(cursor.getColumnIndex("b_type"));
        bn2.g = cursor.getInt(cursor.getColumnIndex("b_show_type"));
        bn2.h = cursor.getString(cursor.getColumnIndex("b_card_title"));
        bn2.i = cursor.getString(cursor.getColumnIndex("b_card_subtitle"));
        return bn2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String b(String string2) {
        String string3 = string2;
        if (string2.isEmpty()) return string3;
        if (string2.endsWith("\u3002")) return string2.substring(0, string2.length() - 1);
        if (string2.endsWith("\uff0c")) return string2.substring(0, string2.length() - 1);
        string3 = string2;
        if (!string2.endsWith("")) return string3;
        return string2.substring(0, string2.length() - 1);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public bn a(Context var1_1) {
        var3_2 = 0;
        var2_3 = this.a(this.a);
        if (var2_3 < 100000) {
            return this;
        }
        var2_3 = this.a(var2_3);
        var5_4 = SmsCoreDatabaseHelper.a((Context)var1_1).a(var2_3);
        if (var5_4 == null) return this;
        if (this.g == -1 && var5_4.g != -1) {
            this.g = var5_4.g;
        }
        var1_1 = var5_4.b;
        var6_5 = this.b;
        var4_6 = var1_1;
        if (TextUtils.isEmpty((CharSequence)var1_1)) ** GOTO lbl21
        var4_6 = var1_1;
        if (TextUtils.isEmpty((CharSequence)var6_5)) ** GOTO lbl21
        var4_6 = var6_5.split(",");
        var2_3 = 0;
        do {
            if (var2_3 < var4_6.length) ** GOTO lbl30
            var4_6 = var1_1;
lbl21: // 3 sources:
            this.b = var4_6;
            var1_1 = var5_4.d;
            var5_4 = this.d;
            var4_6 = var1_1;
            if (!TextUtils.isEmpty((CharSequence)var1_1)) {
                var4_6 = var1_1;
                if (!TextUtils.isEmpty((CharSequence)var5_4)) {
                    break;
                }
            }
            ** GOTO lbl38
lbl30: // 1 sources:
            var1_1 = var1_1.replace((CharSequence)("{ted" + (var2_3 + 1) + "}"), (CharSequence)var4_6[var2_3]);
            ++var2_3;
        } while (true);
        var4_6 = var5_4.split(",");
        var2_3 = var3_2;
        do {
            if (var2_3 >= var4_6.length) {
                var4_6 = var1_1;
lbl38: // 2 sources:
                this.d = var4_6;
                return this;
            }
            var5_4 = "{ted" + (var2_3 + 1) + "}";
            var1_1 = TextUtils.equals((CharSequence)"*", (CharSequence)var4_6[var2_3]) != false ? var1_1.replace((CharSequence)var5_4, (CharSequence)"") : var1_1.replace((CharSequence)var5_4, (CharSequence)var4_6[var2_3]);
            ++var2_3;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public CardBase a(String object) {
        String string2 = null;
        Object object2 = this.b;
        String string3 = this.f;
        String string4 = this.e;
        String string5 = this.d;
        TedSDKLog.d(j, "Checking Regex id: " + this.a);
        string4 = Pattern.compile((String)object2).matcher((CharSequence)object);
        object = string2;
        if (!string4.find()) return object;
        object2 = CardbaseFactory.creator(string3);
        object2.mCardType = CardBaseUtils.convertTypeToInt(string3);
        object2.setMatchedId(this.a);
        this.a(string5, (CardBase)object2, (Matcher)string4);
        int n2 = string4.groupCount();
        object = "";
        int n3 = 1;
        do {
            if (n3 > n2) {
                if (!TextUtils.isEmpty((CharSequence)this.i) && !TextUtils.isEmpty((CharSequence)object)) {
                    object2.setSubTitle(this.a(this.i, (String)object));
                }
                if (!TextUtils.isEmpty((CharSequence)this.e) && !TextUtils.isEmpty((CharSequence)object)) {
                    string2 = this.e;
                    if (this.e.contains((CharSequence)this.i)) {
                        string2 = this.e.replace((CharSequence)this.i, (CharSequence)"");
                    }
                    object2.addData(this.a(string2, (String)object));
                }
                if (TextUtils.isEmpty((CharSequence)this.h)) return object2;
                if (TextUtils.isEmpty((CharSequence)object)) return object2;
                object2.setTitle(this.a(this.h, (String)object));
                return object2;
            }
            string2 = string3 = string4.group(n3);
            if (TextUtils.isEmpty((CharSequence)string3)) {
                string2 = "null";
            }
            object = n3 == n2 ? String.valueOf((Object)object) + string2 : String.valueOf((Object)object) + string2 + "<teddy>";
            ++n3;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public LinkedHashMap<String, String> a(String var1_1, String var2_3) {
        var14_4 = new ArrayList();
        var12_5 = new ArrayList();
        var10_6 = new ArrayList();
        var13_7 = new ArrayList();
        var11_8 = var1_1.toCharArray();
        var9_9 = "";
        var3_10 = 0;
        do {
            if (var3_10 >= var11_8.length) {
                var15_12 = var2_3.split("<teddy>");
                if (var14_4.size() == 0) {
                    var10_6 = var12_5;
                    break;
                }
                break;
            }
            var4_11 = var11_8[var3_10];
            if (var4_11 != 60) ** GOTO lbl25
            if (var3_10 - 1 <= 0) ** GOTO lbl-1000
            var1_1 = var9_9;
            if (var11_8[var3_10 - 1] != '>') lbl-1000: // 2 sources:
            {
                var1_1 = var9_9;
                if (var9_9 != "") {
                    var12_5.add(var9_9);
                    var1_1 = "";
                }
            }
            ** GOTO lbl35
lbl25: // 1 sources:
            if (var4_11 == 62) {
                if (var3_10 + 1 >= var11_8.length || var11_8[var3_10 + 1] != '<') {
                    var1_1 = var9_9;
                    if (var9_9 != "") {
                        var14_4.add(var9_9);
                        var1_1 = "";
                    }
                } else {
                    var1_1 = String.valueOf((Object)var9_9) + "<teddy>";
                }
            } else {
                var1_1 = String.valueOf((Object)var9_9) + var11_8[var3_10];
            }
lbl35: // 4 sources:
            if (var3_10 == var11_8.length - 1) {
                var12_5.add(var1_1);
            }
            ++var3_10;
            var9_9 = var1_1;
        } while (true);
        var4_11 = 0;
        block3 : do {
            if (var4_11 >= var14_4.size()) {
                var1_1 = new LinkedHashMap<K, V>();
                if (var13_7.size() > 0) {
                } else {
                    var2_3 = var10_6.iterator();
                    while (var2_3.hasNext() != false) {
                        var1_1.put((Object)((String)var2_3.next()), (Object)"");
                    }
                    return var1_1;
                }
                var2_3 = var10_6.iterator();
                var3_10 = 0;
                do {
                    if (!var2_3.hasNext()) {
                        return var1_1;
                    }
                    var9_9 = (String)var2_3.next();
                    if (!TextUtils.isEmpty((CharSequence)((CharSequence)var13_7.get(var3_10)))) {
                        var1_1.put(var9_9, (Object)((String)var13_7.get(var3_10)));
                    }
                    ++var3_10;
                } while (true);
            }
            var16_17 = ((String)var14_4.get(var4_11)).split("<teddy>");
            var7_15 = var16_17.length;
            var5_13 = 0;
            var6_14 = 0;
            var9_9 = "";
            do {
                block37 : {
                    block36 : {
                        if (var5_13 >= var7_15) {
                            if (var6_14 == 0) {
                                var10_6.add((String)var12_5.get(var4_11));
                                var13_7.add(this.b((String)var9_9));
                            }
                            ++var4_11;
                            continue block3;
                        }
                        var1_1 = var16_17[var5_13];
                        if (!var1_1.replaceAll("\\d+", "").equals((Object)"")) ** GOTO lbl86
                        var2_3 = var15_12[Integer.parseInt((String)var1_1) - 1].replace((CharSequence)" ", (CharSequence)"");
                        var3_10 = var6_14;
                        var1_1 = var9_9;
                        if (!TextUtils.isEmpty((CharSequence)var2_3)) {
                            if ("null".equals(var2_3)) {
                                var1_1 = var9_9;
                                var3_10 = var6_14;
                            } else {
                                var1_1 = String.valueOf((Object)var9_9) + (String)var2_3 + " ";
                                var3_10 = var6_14;
                            }
                        }
                        ** GOTO lbl140
lbl86: // 1 sources:
                        var8_16 = var1_1.startsWith("!");
                        var11_8 = var1_1;
                        if (var8_16) {
                            var11_8 = var1_1.substring(1);
                        }
                        var17_18 = var11_8.replaceAll("\\d+", "");
                        var2_3 = var9_9;
                        try {
                            var18_19 = var15_12[Integer.parseInt((String)var11_8.replace((CharSequence)var17_18, (CharSequence)"")) - 1];
                            var2_3 = var9_9;
                            if (TextUtils.isEmpty((CharSequence)var18_19)) break block36;
                            var2_3 = var9_9;
                            if ("null".equals((Object)var18_19)) {
                            }
                            ** GOTO lbl-1000
                        }
                        catch (NumberFormatException var1_2) {
                            var9_9 = bn.k.matcher((CharSequence)var11_8);
                            var3_10 = var6_14;
                            var1_1 = var2_3;
                            if (!var9_9.find()) break block37;
                            var9_9 = var9_9.group(1);
                            var3_10 = var6_14;
                            var1_1 = var2_3;
                            if (!TextUtils.isDigitsOnly((CharSequence)var9_9)) break block37;
                            var1_1 = var15_12[Integer.parseInt((String)var9_9) - 1];
                            if ((TextUtils.isEmpty((CharSequence)var1_1) || "null".equals(var1_1)) && var8_16) {
                                var3_10 = 1;
                                var1_1 = var2_3;
                                break block37;
                            }
                            var1_1 = var11_8.replace((CharSequence)var9_9, (CharSequence)var1_1);
                            var1_1 = String.valueOf((Object)var2_3) + (String)var1_1;
                            var3_10 = var6_14;
                        }
                    }
                    if (var8_16) {
                        var3_10 = 1;
                        var1_1 = var9_9;
                    } else lbl-1000: // 2 sources:
                    {
                        var2_3 = var9_9;
                        if (var17_18.equals((Object)"")) {
                            var2_3 = var9_9;
                            var1_1 = String.valueOf((Object)var9_9) + var18_19 + " ";
                            var3_10 = var6_14;
                        } else {
                            var2_3 = var9_9;
                            if (var11_8.split("\\d+")[0].equals((Object)var17_18)) {
                                var2_3 = var9_9;
                                var2_3 = var1_1 = String.valueOf((Object)var9_9) + var17_18;
                                var1_1 = String.valueOf((Object)var1_1) + var18_19;
                                var3_10 = var6_14;
                            } else {
                                var2_3 = var9_9;
                                var2_3 = var1_1 = String.valueOf((Object)var9_9) + var18_19;
                                var1_1 = String.valueOf((Object)var1_1) + var17_18;
                                var3_10 = var6_14;
                            }
                        }
                    }
                }
                ++var5_13;
                var6_14 = var3_10;
                var9_9 = var1_1;
            } while (true);
            break;
        } while (true);
    }
}

