/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CreditCardHuankuanRecognition {
    private static Map<String, String> configMap;
    private static boolean ifInitial;
    private static Map<String, SoftReference<List<String>>> index;
    private static String resouceDirectory;

    static {
        index = new HashMap();
        configMap = new HashMap();
        resouceDirectory = null;
        ifInitial = false;
    }

    public static boolean checkMes(String string2, String string3) throws IOException {
        if ((string2 = configMap.get(string2)) == null) {
            return false;
        }
        Log.i("CreditCardHuankuanRecognition", "ID\t" + string2);
        if (index.containsKey(string2) && index.get(string2).get() != null) {
            return CreditCardHuankuanRecognition.match(string3, index.get(string2).get());
        }
        List<String> list = CreditCardHuankuanRecognition.loadContent(String.valueOf((Object)resouceDirectory) + string2);
        index.put(string2, new SoftReference<List<String>>(list));
        return CreditCardHuankuanRecognition.match(string3, list);
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        index.clear();
        configMap.clear();
        ifInitial = false;
        return true;
    }

    public static boolean initial() {
        if (ifInitial) {
            return true;
        }
        index.clear();
        configMap.clear();
        resouceDirectory = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/creditCardHuankuanResource/";
        try {
            CreditCardHuankuanRecognition.loadConfig(String.valueOf((Object)resouceDirectory) + "config");
            ifInitial = true;
            return true;
        }
        catch (Exception var0) {
            return false;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void loadConfig(String var0) throws IOException {
        var0 = FileOperator.createBufferedReaderByFileName(var0);
        block0 : do lbl-1000: // 3 sources:
        {
            if ((var2_2 = var0.readLine()) == null) {
                var0.close();
                return;
            }
            if ((var2_2 = var2_2.split(" ")).length <= 1) ** GOTO lbl-1000
            var1_1 = 1;
            do {
                if (var1_1 >= var2_2.length) continue block0;
                CreditCardHuankuanRecognition.configMap.put(var2_2[var1_1], var2_2[0]);
                ++var1_1;
            } while (true);
            break;
        } while (true);
    }

    private static List<String> loadContent(String string2) throws IOException {
        ArrayList arrayList = new ArrayList();
        string2 = FileOperator.createBufferedReaderByFileName(string2);
        do {
            String string3;
            if ((string3 = string2.readLine()) == null) {
                string2.close();
                return arrayList;
            }
            arrayList.add(string3);
        } while (true);
    }

    private static boolean match(String string2, List<String> object) {
        object = object.iterator();
        do {
            if (object.hasNext()) continue;
            return false;
        } while (!string2.matches((String)object.next()));
        return true;
    }
}

