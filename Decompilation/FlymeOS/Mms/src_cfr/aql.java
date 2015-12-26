/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import android.text.TextUtils;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.VerificationCodeAction;
import com.ted.android.message.MessageUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aql
extends aqc {
    private static final String a = aql.class.getSimpleName();
    private static aql b;
    private static String[] c;
    private static String[] d;
    private static String[] e;
    private static String[] f;
    private static String[] g;
    private static String[] h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;

    static {
        c = new String[]{"12306\u65b0\u9a8c\u8bc1\u7801"};
        d = new String[]{"\u5f71", "\u53d6\u7968", "\u56e2\u8d2d", "\u51fa\u793a", "\u7f8e\u56e2\u5238", "\u4f18\u60e0\u5238", "\u4ee3\u91d1\u5238"};
        e = new String[]{"\u9a8c\u8bc1\u7801", "\u9a8c\u8bc1\u4ee3\u7801", "\u6821\u9a8c\u7801", "\u52a8\u6001\u7801", "\u52a8\u6001\u5bc6\u7801", "\u786e\u8ba4\u7801", "\u6fc0\u6d3b\u7801", "\u8ba4\u8bc1\u7801", "\u4e0a\u7f51\u5bc6\u7801", "\u9a8c\u8bc1\u77ed\u4fe1", "\u65e0\u7ebf\u767b\u5f55\u5bc6\u7801", "aWiFi\u767b\u5f55\u5bc6\u7801", "WLAN\u5bc6\u7801"};
        f = new String[]{"\u9a8c\u8bc1\u7801", "\u9a8c\u8bc1\u4ee3\u7801", "\u6821\u9a8c\u7801", "\u52a8\u6001\u7801", "\u52a8\u6001\u5bc6\u7801", "\u786e\u8ba4\u7801", "\u6fc0\u6d3b\u7801", "\u8ba4\u8bc1\u7801", "\u4e0a\u7f51\u5bc6\u7801", "\u9a8c\u8bc1\u7801", "\u767b\u5f55\u5bc6\u7801", "\u767b\u5f55\u5bc6\u7801", "\u767b\u5f55\u5bc6\u7801"};
        g = new String[]{"^(?:app|118114|116114|10010|10086|10000|12345|google|google|51job|51talk|88wifi|alipay|andriod|android|arrayvpn|awifi|baidu|bbtree|card|cctv|chaowifi|club|cmbchina|cmcc|cmhebao|cmwap|csdn|ctrip|ctrip|dear|deeplyfu|download|ebank|email|email|facebook|facebook|free|freewifi|guide|http|https|icbc|imax|ipad|iphone|ireader|itunes|jinhua|jumei|laiwang|liepin|login|netpay|oppo|pass|pingan|pinyin|ppmoney|pptv|rmb|samsung|shop|tantan|taobao|tenpay|tianya|tv189|usim|uu898|vivo|watson|watsons|wecash|weibo|weixin|wifi|witown|wlan|online|password|cvv2|cvv|cvc2|cvc|95155|95313|95316|95319|95333|95352|95356|95395|95398|95501|95508|95526|95527|95528|95533|95537|95541|95555|95558|95559|95561|95566|95568|95574|95577|95580|95588|95593|95594|95595|95599|955112|955113|955333|955533|955558|955559|955580|955588|955599|955666|955800|955886|955993|955999|9550833|9551186|9551286|9555801|9555812|9555816|9556662|9570277|9572150|95105565|95105588|95105665|95105757|95196558|95558000|95588000|955119005|955580788|955581006|955581101|955581110|955800118|9545397913|9546011284|9546011680|9546011941|9551290003|95558100202261487|9559315103003370|955128695511|angelababy|baby|uber|microsoft|tlsg)$", "^(?:rmb|iphone)[\\s\\S]*$"};
        h = new String[]{",", "\uff0c", ".", "\u3002", "!", "\uff01", "(", "\uff08", " "};
        i = "4e407154c818b14ac943e0fa5f3490d27f6f6c24537cac2f3ae9bd9b8773223c1301f051a8ee8113aa12f89fcb3aecc051562c38e9febe43047de9870de76178d85fa9a99b0c9faf1da472bc393cba2ae2e2cb42c78fa61daa2e955dfcd535697d80cf1733e0126eb28c57309a11fdd74fbac09f0ae43d197bd14e2b22cdeb199a5fea409505bc97ceadfefe78e2fe9de3933dc2a644ad6de14deb3f6f3dfc91cf9f30ff937e49be79b2a7d8431c04d0e82894fd014ef6c954e74d8ee3c1c8237fd6b27595d7fa10c887c095ba96a0178f5d08aaa7fe44f7d814b201d7886753558d7de8c116e65693bbab7f757ecae7480810bd1db7d3ee3d1fdc4effbe79a0ee9a3d9d33161252d0e17355364298ba69f0677c214b403e0197a59d50d440a5e5929be753a0e1db2587dd9a9d39cce61dace9c0c2bdaa76595eb3414bc4b0f1fe5c424e0d03895932c4eae068a7ec8a73901ae8116fb9b4cb1980ca79ddac478cee3119743331427f2280e26e3ee7d12d1063f88efb79486f1b98115a1fe5e061ffaf4e685bb97fb7898a15aff2a106566787287d7aa7d39f6b94c5ea4edb5cc6db2f6598d8bd9fbde2d7ca2e620dbbdfff7bd74d657200eb4a4aeee0681ddb715f8620b1c4ff43fde0c135089c7cda12b9bdd933500a89cdc40d452026bd99c5da8cdf18505c1d37cfb5f0057f15cbdf5ad34e04753e2e6282d358dcc1ea697b12707afd4c7e3cd4ba913795c69fc639084a601e99599800376fe6c50855e75e179090e48659519580cce5e355d3fd613326e912a7f224d7b9e17c5b1abc3f444c0cfa9fa06f31fe0a95ce83578aefb3d676e44be2f4615c43d6e3da8e330b4944aebc9d9a1334";
        j = "22dbf63a1ab196ee516b35e97ba990cc0d93655778c7eb75b29ed6bd27c82722133ee6bb7a0cbb8ca0d52f569a110d949d06be5c52671e491e42ff743865273577182a79806ab629e052fce59b30bdd3bf0656beacf68f6aa6484f4167991fa2afd0f0c973a931c294ac157ca32d86353d1fdc4effbe79a0ee9a3d9d33161252d0e17355364298ba69f0677c214b403e0197a59d50d440a5e5929be753a0e1db2587dd9a9d39cce61dace9c0c2bdaa76595eb3414bc4b0f1fe5c424e0d03895932c4eae068a7ec8a73901ae8116fb9b4cb1980ca79ddac474f0270f7abb0b578f3df644f66182411869aa43fcdf61e2359437d1e5a70086855678bc7cdbcc5664e49bce3261937dd542d2ac68241945e1deb99194795f439dce492e2381805948c5b09c843d7f043132b838e21d35b1a10d127a96bf123217f1c09989bac4fec506dbb587573bc84f2199da3dbace831345355e60c90b34449f44475bf0637869e918703f642f110d1b284a1c81001853daf43217e73003a2e16da69092122e2cf6560bba47cc4ca089e3981b44c744d";
        k = anv.b(i, DataBus.FILE_MASK);
        l = anv.b(j, DataBus.FILE_MASK);
        m = "4e407154c818b14ac943e0fa5f3490d27f6f6c24537cac2f3ae9bd9b8773223c1301f051a8ee8113aa12f89fcb3aecc051562c38e9febe43047de9870de76178d85fa9a99b0c9faf1da472bc393cba2ae2e2cb42c78fa61daa2e955dfcd535697d80cf1733e0126eb28c57309a11fdd74fbac09f0ae43d197bd14e2b22cdeb19df15a60a38ed1bb393bbab7f757ecae7480810bd1db7d3ee3d1fdc4effbe79a0ee9a3d9d33161252d0e17355364298ba69f0677c214b403e0197a59d50d440a5e5929be753a0e1db2587dd9a9d39cce61dace9c0c2bdaa76595eb3414bc4b0f1fe5c424e0d03895932c4eae068a7ec8a73901ae8116fb9b4cb1980ca79ddac478cee3119743331427f2280e26e3ee7d13aa5c083d3a1899fb1022ab13184d69bbce94dd5b838fc5e303c95d96e53ff39";
        n = anv.b(m, DataBus.FILE_MASK);
    }

    private aql() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aql a() {
        if (b == null) {
            synchronized (aql.class) {
                b = new aql();
            }
        }
        return b;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String a(String[] arrstring, String string2) {
        boolean bl2 = false;
        if (arrstring == null) return "";
        if (arrstring.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String string3 = arrstring[n3];
            if (bl2) {
                stringBuilder.append(string2);
            } else {
                bl2 = true;
            }
            stringBuilder.append(string3);
            ++n3;
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static List<String> a(HashMap<Integer, String> arrstring) {
        ArrayList arrayList = new ArrayList();
        if (arrstring.size() != 2 || !(arrstring = arrstring.values().toArray(new String[arrstring.values().size()]))[0].contains((CharSequence)arrstring[1]) && !arrstring[1].contains((CharSequence)arrstring[0])) return arrayList;
        if (arrstring[0].length() >= arrstring[1].length()) {
            arrayList.add(arrstring[0]);
            return arrayList;
        }
        arrayList.add(arrstring[1]);
        return arrayList;
    }

    private List<String> a(HashMap<Integer, String> object, String string2) {
        Map.Entry entry;
        string2 = new ArrayList();
        object = object.entrySet().iterator();
        do {
            if (object.hasNext()) continue;
            return string2;
        } while ((Integer)(entry = (Map.Entry)object.next()).getKey() != 0);
        string2.add((String)entry.getValue());
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Map<String, String> a(HashMap<Integer, String> object, HashMap<Integer, String> hashMap) {
        HashMap hashMap2 = new HashMap();
        Iterator iterator = object.entrySet().iterator();
        int n2 = Integer.MAX_VALUE;
        block0 : while (iterator.hasNext()) {
            object = (Map.Entry)iterator.next();
            int n3 = (Integer)object.getKey();
            String string2 = (String)object.getValue();
            Iterator iterator2 = hashMap.entrySet().iterator();
            do {
                String string3;
                if (!iterator2.hasNext()) continue block0;
                object = (Map.Entry)iterator2.next();
                int n4 = (Integer)object.getKey();
                object = (String)object.getValue();
                n4 = n3 < n4 ? n4 - n3 - string2.length() : n3 - n4 - object.length();
                if (n4 < n2) {
                    hashMap2.clear();
                }
                if (n4 > n2) continue;
                object = !TextUtils.isEmpty((CharSequence)object) ? (!TextUtils.isEmpty((CharSequence)(string3 = this.e((String)object))) ? "\u590d\u5236" + string3 : "\u590d\u5236" + (String)object) : "\u590d\u5236\u9a8c\u8bc1\u7801";
                hashMap2.put(string2, object);
                n2 = n4;
            } while (true);
            break;
        }
        return hashMap2;
    }

    private boolean a(String string2, String[] arrstring) {
        string2 = string2.toLowerCase();
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            if (string2.contains((CharSequence)arrstring[n3].toLowerCase())) {
                return true;
            }
            ++n3;
        }
        return false;
    }

    private boolean b(String string2, String[] arrstring) {
        string2 = string2.toLowerCase();
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            if (string2.matches(arrstring[n3])) {
                return true;
            }
            ++n3;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String d(String string2) {
        String string3 = string2.trim();
        int n2 = string3.length();
        if (string3.startsWith("[")) {
            int n3 = string3.indexOf("]");
            string2 = string3;
            if (n3 <= 0) return string2;
            string2 = string3;
            if (n3 >= n2 - 1) return string2;
            HashMap<Integer, String> hashMap = this.b(string3.substring(0, n3));
            if (hashMap == null) return string3.substring(n3 + 1);
            string2 = string3;
            if (hashMap.size() != 0) return string2;
            return string3.substring(n3 + 1);
        }
        string2 = string3;
        if (!string3.startsWith("\u3010")) return string2;
        int n4 = string3.indexOf("\u3011");
        string2 = string3;
        if (n4 <= 0) return string2;
        string2 = string3;
        if (n4 >= n2 - 1) return string2;
        HashMap<Integer, String> hashMap = this.b(string3.substring(0, n4));
        if (hashMap == null) return string3.substring(n4 + 1);
        string2 = string3;
        if (hashMap.size() != 0) return string2;
        return string3.substring(n4 + 1);
    }

    private String e(String string2) {
        int n2 = 0;
        while (n2 < e.length) {
            if (string2.equals((Object)e[n2])) {
                return f[n2];
            }
            ++n2;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected List<BubbleEntity> a(String string2, String string3) {
        string3 = new ArrayList();
        if (!MessageUtils.containsHanScript(string2)) {
            return string3;
        }
        Map<String, String> map = this.a(string2);
        if (map != null && map.size() > 0) {
            for (Map.Entry entry : map.entrySet()) {
                BubbleEntity bubbleEntity = new BubbleEntity();
                bubbleEntity.setId("-1");
                bubbleEntity.setIndex(string2.indexOf((String)entry.getKey()));
                bubbleEntity.setMatchedWords((String)entry.getKey());
                VerificationCodeAction verificationCodeAction = new VerificationCodeAction(bubbleEntity);
                verificationCodeAction.buttonText = !TextUtils.isEmpty((CharSequence)((CharSequence)entry.getValue())) ? (String)entry.getValue() : "\u590d\u5236\u9a8c\u8bc1\u7801";
                verificationCodeAction.setVerificationCode((String)entry.getKey());
                verificationCodeAction.clip = (String)entry.getKey();
                bubbleEntity.addAction(verificationCodeAction);
                string3.add(bubbleEntity);
            }
        }
        return string3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public Map<String, String> a(String var1_1) {
        if (var1_1 == null) {
            return null;
        }
        if (this.a((String)var1_1, aql.c)) {
            return null;
        }
        var6_2 = aqo.a((String)var1_1);
        var5_3 = this.c((String)var6_2);
        var4_4 = aql.a(aql.e, "|");
        aql.l = String.format((String)aql.l, (Object[])new Object[]{var4_4});
        var1_1 = Pattern.compile((String)aql.l).matcher((CharSequence)var6_2);
        var2_6 = var3_5 = var1_1.find();
        if (!var3_5) {
            aql.n = String.format((String)aql.n, (Object[])new Object[]{var4_4});
            var1_1 = Pattern.compile((String)aql.n).matcher((CharSequence)var6_2);
            var2_6 = var1_1.find();
        }
        if (!var2_6) ** GOTO lbl38
        var7_7 = new HashMap();
        var4_4 = var1_1.group(1);
        if (TextUtils.isEmpty((CharSequence)var4_4)) ** GOTO lbl38
        if (var5_3.containsValue((Object)"\u9a8c\u8bc1\u7801") != false ? this.a((String)var6_2, aql.d) != false : var5_3.containsValue((Object)"\u4e0a\u7f51\u5bc6\u7801") == false && var5_3.containsValue((Object)"\u65e0\u7ebf\u767b\u5f55\u5bc6\u7801") == false && var5_3.containsValue((Object)"aWiFi\u767b\u5f55\u5bc6\u7801") == false && var5_3.containsValue((Object)"\u52a8\u6001\u5bc6\u7801") == false && var6_2.contains((CharSequence)"\u5bc6\u7801") != false && this.a((String)var6_2, aql.d) != false) {
            return null;
        }
        var1_1 = var4_4;
        if (var4_4.startsWith("-")) {
            var1_1 = var4_4.substring(1, var4_4.length());
        }
        var4_4 = var1_1.endsWith("-") != false ? var1_1.substring(0, var1_1.length() - 1) : var1_1;
        var8_8 = var5_3.entrySet().iterator();
        do {
            if (var8_8.hasNext()) ** GOTO lbl33
            if (var7_7.size() == 0 && !this.b((String)var4_4, aql.g)) {
                var7_7.put(var4_4, "\u590d\u5236\u5bc6\u7801");
            }
            if (var7_7 != null && var7_7.size() > 0) {
                return var7_7;
            }
            ** GOTO lbl38
lbl33: // 1 sources:
            var1_1 = (String)((Map.Entry)var8_8.next()).getValue();
            if (TextUtils.isEmpty((CharSequence)var1_1)) ** GOTO lbl72
            var9_9 = this.e((String)var1_1);
            var1_1 = !TextUtils.isEmpty((CharSequence)var9_9) ? "\u590d\u5236" + var9_9 : "\u590d\u5236" + (String)var1_1;
            ** GOTO lbl73
lbl38: // 3 sources:
            if (var5_3.containsValue((Object)"\u9a8c\u8bc1\u7801") && this.a((String)var6_2, aql.d)) {
                return null;
            }
            if (var5_3 == null) return null;
            if (var5_3.size() == 0) {
                return null;
            }
            var4_4 = this.d((String)var6_2);
            var1_1 = MessageUtils.removePhoneNumber(MessageUtils.removeUrl((String)var4_4));
            if (var1_1.length() > 100) {
                return null;
            }
            var6_2 = this.a((HashMap<Integer, String>)(var1_1 = this.b((String)var1_1)), (String)var4_4);
            if (var6_2 != null && var6_2.size() > 0) {
                var7_7 = new HashMap();
                var1_1 = var4_4 = "\u590d\u5236\u9a8c\u8bc1\u7801";
                if (var5_3 != null) {
                    var1_1 = var4_4;
                    if (var5_3.size() > 0) {
                        var1_1 = var4_4;
                        if (!TextUtils.isEmpty((CharSequence)((CharSequence)var5_3.get((Object)0)))) {
                            var1_1 = "\u590d\u5236" + (String)var5_3.get((Object)0);
                        }
                    }
                }
                var7_7.put(var6_2.get(0), var1_1);
                return var7_7;
            }
            var6_2 = aql.a(var1_1);
            if (var6_2 == null) return this.a((HashMap<Integer, String>)var1_1, var5_3);
            if (var6_2.size() <= 0) return this.a((HashMap<Integer, String>)var1_1, var5_3);
            var7_7 = new HashMap();
            var1_1 = var4_4 = "\u590d\u5236\u9a8c\u8bc1\u7801";
            if (var5_3 != null) {
                var1_1 = var4_4;
                if (var5_3.size() > 0) {
                    var1_1 = var4_4;
                    if (!TextUtils.isEmpty((CharSequence)((CharSequence)var5_3.get((Object)0)))) {
                        var1_1 = "\u590d\u5236" + (String)var5_3.get((Object)0);
                    }
                }
            }
            var7_7.put(var6_2.get(0), var1_1);
            return var7_7;
lbl72: // 1 sources:
            var1_1 = "\u590d\u5236\u9a8c\u8bc1\u7801";
lbl73: // 2 sources:
            if (this.b((String)var4_4, aql.g)) continue;
            var7_7.put(var4_4, var1_1);
        } while (true);
    }

    public HashMap<Integer, String> b(String string2) {
        HashMap hashMap = new HashMap();
        string2 = Pattern.compile((String)k).matcher((CharSequence)string2);
        while (string2.find()) {
            int n2 = string2.start(1);
            if (this.b(string2.group(1), g)) continue;
            hashMap.put((Object)n2, (Object)string2.group(1));
        }
        return hashMap;
    }

    public HashMap<Integer, String> c(String string2) {
        HashMap hashMap = new HashMap();
        String[] arrstring = e;
        int n2 = arrstring.length;
        int n3 = 0;
        block0 : while (n3 < n2) {
            String string3 = arrstring[n3];
            int n4 = 0;
            do {
                if ((n4 = string2.indexOf(string3, n4)) == -1) {
                    ++n3;
                    continue block0;
                }
                hashMap.put((Object)n4, (Object)string3);
                ++n4;
            } while (true);
            break;
        }
        return hashMap;
    }
}

