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
import com.ted.android.data.bubbleAction.CarrierAction;
import com.ted.android.message.MessageUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class apy
extends aqc {
    public static final String a;
    private static final String b;
    private static final String c;
    private static apy d;
    private static final HashMap<String, String> e;

    static {
        b = apy.class.getSimpleName();
        a = anv.b("ed9b375a2ab43d11a86f10183e36165c6a199dd39ff20a89ed4a098a4d24afd18f7e9b59e9dbb2146c5dc66e5271e4fc191be52a45a50652fb9abce08aaac7f4e03e9db2d4391d76909bd75320d4918e2faef94c3071a012f8c43e5ed163be565974624b6602acc82a33ad13157002a7fece16dd867801b4f59a493d291bcd53ddb63c7040814088d62ba426adc04027efde7ffb626d6d028a0a63594e1bb6bea4ef51b6a3ea0bc92b35282db100917ab35dda3794f6b5e70f538137b3122f7cc54cee615eb0961e618d3e81e95116df0a4f63d4196e0d9aa2c76f6430f9a473053e7feef2461b55c95ed188ae79ed8901f45ef7a46511c404804906ca0e7b2fcbe2682231ff41ef751cd4c0226e876de98564ab90eb18493873c467f042136fc6fcb12fb1668b5a990ce47c8aa02b89bf6366519a6b08ab634f3266ccc7bbfe2334d2f8f0cdc151102cde77e7d550882939c2cc6707da67c9514ce1f8d7ab25", DataBus.FILE_MASK);
        c = anv.b("a6f6c024abbebe3e67ed49733b3ff81810a0b63ebaab73e65ff1faa37edd443ef311570ab6714f3e735cfe0f53373f9d8c0444f1888916e15dcc871d93ae984f45de513dfb02a94df2578efc21e8e2cd3d7be57a24b60f177944bbe0a3243c882accda3c078446466fcb0a47ff9fa9660bace34b92a9753e30757d43c105a6e05998d2842b9e01ef37800e04fcdaa8d7d2aaf1ed856941a155ca98770aca5f43fd6a80902fb2ef840303bf33338eed3530757d43c105a6e05998d2842b9e01ef37800e04fcdaa8d7d2aaf1ed856941a1e439b94490114d45afcea358d8be6bb8", DataBus.FILE_MASK);
        e = new apz();
    }

    private apy() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static apy a() {
        if (d == null) {
            synchronized (apy.class) {
                d = new apy();
            }
        }
        return d;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2) {
        String string3;
        if (TextUtils.isEmpty((CharSequence)string2)) return null;
        string2 = Pattern.compile((String)a).matcher((CharSequence)string2);
        do {
            if (string2.find()) continue;
            return null;
        } while (!e.containsKey((Object)(string3 = string2.group())));
        return (String)e.get((Object)string3);
    }

    private List<String> a(List<String> object) {
        ArrayList arrayList = new ArrayList();
        object = object.iterator();
        while (object.hasNext()) {
            String string2 = (String)object.next();
            if (arrayList.contains(string2)) continue;
            arrayList.add(string2);
        }
        return arrayList;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String b(String string2) {
        String string3 = "";
        string2 = MessageUtils.removePhoneNumber(MessageUtils.removeUrl(string2.replaceAll(c, "  ").replace((CharSequence)"\u8ba2\u5355", (CharSequence)"  ").replace((CharSequence)"\u5176\u4e2d", (CharSequence)"  ")));
        Object object = Pattern.compile((String)"(?=.*?(?:\u5feb\u9012|\u5feb\u4ef6|\u9556\u53f7|\u5929\u5929|\u6302\u53f7\u4fe1|\u589e\u76ca|\u98de\u8c79|\u4eac\u4e1c|\u901f\u5c14|\u4fe1\u4e30|\u4f73\u5409|\u4f73\u6021|\u534e\u5b87|\u9f99\u90a6|\u98de\u5eb7\u8fbe|\u8054\u660a\u901a|\u8fd0\u901a|\u8def\u901a|\u5feb\u6377|\u6377\u7279|\u65b0\u90a6|\u5168\u65e5\u901a|\u6e90\u4f1f\u4e30|\u5b89\u6377|\u4e30\u8fbe|\u94f6\u6377|\u57ce\u5e02\u4e4b\u661f|\u76db\u8f89|\u8fdc\u6210|\u4e00\u90a6|\u987a\u4e30|\u7533\u901a|\u5706\u901a|\u56fd\u901a|\u5168\u4e00|\u4e2d\u901a|\u5168\u5cf0|\u5fb7\u90a6|\u97f5\u8fbe|\u5b85\u6025\u9001|EMS|DHL|UPS|\u5982\u98ce\u8fbe|\u6c47\u901a|\u4f18\u901f|\u90ae\u5c40))(?=.*?(?:^|[^0-9a-zA-Z\u81f3])(\\d{10,30}|[a-zA-Z]{2}\\d{8,28}|[a-zA-Z]{2}\\d{6,26}[a-zA-Z]{2})(?!\u8ba2\u5355|[0-9a-zA-Z]))").matcher((CharSequence)string2);
        HashMap hashMap = new HashMap();
        do {
            if (!object.find()) {
                object = this.a(hashMap, this.c(string2));
                string2 = string3;
                if (object != null) {
                    string2 = string3;
                    if (object.size() > 0) {
                        string2 = this.a((List<String>)object).get(0);
                    }
                }
                string3 = string2;
                if (string2.length() != 10) return string3;
                object = anu.b(string2);
                if (string2.toString().startsWith("400")) return "";
                if (string2.toString().startsWith("800")) return "";
                string3 = string2;
                if (!anu.c((String)object)) return string3;
                return "";
            }
            String string4 = object.group(1);
            hashMap.put((Object)object.start(1), (Object)string4);
        } while (true);
    }

    private HashMap<Integer, String> c(String string2) {
        HashMap hashMap = new HashMap();
        String[] arrstring = a.split("\\|");
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

    @Override
    public List<BubbleEntity> a(String string2, String string3) {
        string3 = new ArrayList();
        String string4 = this.b(string2);
        Object object = anu.b(string4);
        if (!TextUtils.isEmpty((CharSequence)string4) && !anu.c((String)object)) {
            object = new BubbleEntity();
            object.setId("-2");
            object.setMatchedWords(string4);
            object.setIndex(string2.indexOf(string4));
            CarrierAction carrierAction = new CarrierAction((BubbleEntity)object);
            carrierAction.setCarrierName(apy.a(string2));
            carrierAction.setCariierNumber(string4);
            object.addAction(carrierAction);
            string3.add(object);
        }
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     */
    public List<String> a(HashMap<Integer, String> iterator, HashMap<Integer, String> hashMap) {
        ArrayList arrayList = new ArrayList();
        iterator = iterator.entrySet().iterator();
        int n2 = Integer.MAX_VALUE;
        block0 : while (iterator.hasNext()) {
            Object object = (Map.Entry)iterator.next();
            int n3 = (Integer)object.getKey();
            object = (String)object.getValue();
            Iterator iterator2 = hashMap.entrySet().iterator();
            do {
                if (!iterator2.hasNext()) continue block0;
                Object object2 = (Map.Entry)iterator2.next();
                int n4 = (Integer)object2.getKey();
                object2 = (String)object2.getValue();
                n4 = n3 < n4 ? n4 - n3 - object.length() : n3 - n4 - object2.length();
                if (n4 < n2) {
                    arrayList.clear();
                }
                if (n4 > n2) continue;
                arrayList.add(object);
                n2 = n4;
            } while (true);
            break;
        }
        return arrayList;
    }
}

