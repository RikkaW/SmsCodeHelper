/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.ClassLoader
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.net.URL
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.xiaomi.common;

import com.xiaomi.common.Encoding;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileOperator {
    public static BufferedReader createBufferedReaderByFileName(String string2) throws IOException {
        if (SMSUnderstand.isResourceInJar()) {
            try {
                BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(FileOperator.class.getClassLoader().getResourceAsStream(string2), Encoding.utf8));
                return bufferedReader;
            }
            catch (Exception var1_2) {
                throw new IOException(String.valueOf((Object)string2) + " Read Error!!!");
            }
        }
        return new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(string2), Encoding.utf8));
    }

    public static boolean fileExist(String string2) {
        if (SMSUnderstand.isResourceInJar()) {
            if (FileOperator.class.getClassLoader().getResource(string2) == null) {
                return false;
            }
            return true;
        }
        return new File(string2).exists();
    }

    public static String getFileNameWithoutExtend(String string2) {
        String string3 = string2.replace((CharSequence)"\\", (CharSequence)"/").replaceAll("^[/\" ]", "").replaceAll("[/\" ]$", "");
        int n = string3.lastIndexOf(46);
        string2 = string3;
        if (n >= 0) {
            string2 = string3.substring(0, n);
        }
        n = string2.lastIndexOf("/");
        string3 = string2;
        if (n >= 0) {
            string3 = string2.substring(n + 1);
        }
        return string3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static List<List> readDic2Form(String var0, List<String> var1_1) throws IOException {
        var4_2 = new ArrayList();
        var5_3 = var1_1.iterator();
        do {
            if (!var5_3.hasNext()) break;
            var6_4 = var5_3.next();
            if (var6_4.equalsIgnoreCase("String")) {
                var4_2.add((List)new ArrayList());
            }
            if (var6_4.equalsIgnoreCase("int") || var6_4.equalsIgnoreCase("integer")) {
                var4_2.add((List)new ArrayList());
            }
            if (!var6_4.equalsIgnoreCase("double")) continue;
            var4_2.add((List)new ArrayList());
        } while (true);
        var5_3 = FileOperator.createBufferedReaderByFileName(var0);
        do {
            if ((var6_4 = var5_3.readLine()) == null) {
                var5_3.close();
                return var4_2;
            }
            if ((var6_4 = var6_4.trim()).equals((Object)"") || var6_4.startsWith("//")) continue;
            if (var6_4.startsWith("#include")) {
                var2_5 = var6_4.indexOf(" \"");
                var3_6 = var6_4.indexOf("\"", var2_5 + 2);
                if (var2_5 <= 0 || var3_6 <= 0) continue;
                var4_2 = var6_4.substring(var2_5 + 2, var3_6);
                var4_2 = FileOperator.readDic2Form(var0.replace((CharSequence)new File(var0).getName(), (CharSequence)var4_2), var1_1);
                continue;
            }
            if ((var6_4 = var6_4.trim().split("\\t")).length <= 0 || var6_4[0].length() <= 0) continue;
            var2_5 = 0;
            do {
                if (var2_5 >= var6_4.length) ** break;
                if (var1_1.get(var2_5).equalsIgnoreCase("String")) {
                    ((List)var4_2.get(var2_5)).add(var6_4[var2_5]);
                } else if (var1_1.get(var2_5).equalsIgnoreCase("int") || var1_1.get(var2_5).equalsIgnoreCase("integer")) {
                    var4_2.get(var2_5).add(Integer.valueOf((String)var6_4[var2_5]));
                } else if (var1_1.get(var2_5).equalsIgnoreCase("double")) {
                    var4_2.get(var2_5).add(Double.valueOf((String)var6_4[var2_5]));
                }
                ++var2_5;
            } while (true);
            break;
        } while (true);
    }

    public static int readDic2Map(String string2, Map<String, Double> map) throws IOException {
        BufferedReader bufferedReader = FileOperator.createBufferedReaderByFileName(string2);
        int n = -1;
        do {
            Object object;
            if ((object = bufferedReader.readLine()) == null) {
                bufferedReader.close();
                return n;
            }
            if ((object = object.trim()).equals((Object)"") || object.startsWith("//")) continue;
            if (object.startsWith("#include")) {
                int n2 = object.indexOf(" \"");
                int n3 = object.indexOf("\"", n2 + 2);
                if (n2 <= 0 || n3 <= 0) continue;
                object = object.substring(n2 + 2, n3);
                n2 = FileOperator.readDic2Map(string2.replace((CharSequence)new File(string2).getName(), (CharSequence)object), map);
                if (n >= n2) continue;
                n = n2;
                continue;
            }
            if ((object = object.trim().toLowerCase().split("\\t")).length != 2 || object[0].length() <= 0) continue;
            map.put(object[0], Double.valueOf((String)object[1]));
            if (n >= object[0].length()) continue;
            n = object[0].length();
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static int readDic2Set(String var0, Set<String> var1_1) throws IOException {
        var5_2 = FileOperator.createBufferedReaderByFileName(var0);
        var2_3 = -1;
        block0 : do {
            if ((var6_6 = var5_2.readLine()) == null) {
                var5_2.close();
                return var2_3;
            }
            if ((var6_6 = var6_6.trim()).equals((Object)"") || var6_6.startsWith("//")) continue;
            if (var6_6.startsWith("#include")) {
                var3_4 = var6_6.indexOf(" \"");
                var4_5 = var6_6.indexOf("\"", var3_4 + 2);
                if (var3_4 <= 0 || var4_5 <= 0) continue;
                var6_6 = var6_6.substring(var3_4 + 2, var4_5);
                var3_4 = FileOperator.readDic2Set(var0.replace((CharSequence)new File(var0).getName(), (CharSequence)var6_6), var1_1);
                if (var2_3 >= var3_4) continue;
                var2_3 = var3_4;
                continue;
            }
            if ((var6_6 = var6_6.split("\\t", 0)).length < 1) continue;
            var4_5 = 0;
            var3_4 = var2_3;
            do {
                var2_3 = var3_4;
                if (var4_5 < var6_6.length) ** break;
                continue block0;
                var6_6[var4_5] = var6_6[var4_5].trim().toLowerCase();
                if (var6_6[var4_5].length() < 1 || var6_6[var4_5].contains((CharSequence)"---")) ** GOTO lbl-1000
                var1_1.add((String)var6_6[var4_5]);
                if (var3_4 < var6_6[var4_5].length()) {
                    var2_3 = var6_6[var4_5].length();
                } else lbl-1000: // 2 sources:
                {
                    var2_3 = var3_4;
                }
                ++var4_5;
                var3_4 = var2_3;
            } while (true);
            break;
        } while (true);
    }

    public static ArrayList<String> readFile(String string2) throws IOException {
        ArrayList arrayList = new ArrayList();
        string2 = FileOperator.createBufferedReaderByFileName(string2);
        do {
            String string3;
            if ((string3 = string2.readLine()) == null) {
                string2.close();
                return arrayList;
            }
            if (string3.equals((Object)"")) continue;
            arrayList.add((Object)string3);
        } while (true);
    }

    public static void readFile(String string2, ArrayList<String> arrayList) throws IOException {
        string2 = FileOperator.createBufferedReaderByFileName(string2);
        do {
            String string3;
            if ((string3 = string2.readLine()) == null) {
                string2.close();
                return;
            }
            if (string3.equals((Object)"")) continue;
            arrayList.add((Object)string3);
        } while (true);
    }

    public static HashMap<String, String> readToMapChangeCol(String string2) throws IOException {
        HashMap hashMap = new HashMap();
        string2 = FileOperator.createBufferedReaderByFileName(string2);
        do {
            String string3;
            int n;
            if ((string3 = string2.readLine()) == null) {
                string2.close();
                return hashMap;
            }
            if (string3.trim().equals((Object)"") || (n = string3.indexOf("\t")) <= 0 || n >= string3.length() - 1) continue;
            hashMap.put((Object)string3.substring(n + 1), (Object)string3.substring(0, n));
        } while (true);
    }
}

