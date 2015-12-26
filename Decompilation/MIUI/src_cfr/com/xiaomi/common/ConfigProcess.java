/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.xiaomi.common;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.StringProcess;
import java.util.HashMap;

public class ConfigProcess {
    public static HashMap<String, String> readConfig(String string2) {
        String[] arrstring;
        HashMap hashMap = new HashMap();
        try {
            string2 = FileOperator.createBufferedReaderByFileName(string2);
            do {
                if ((arrstring = string2.readLine()) != null) break block5;
                break;
            } while (true);
        }
        catch (Exception var0_1) {
            return null;
        }
        {
            block5 : {
                string2.close();
                return hashMap;
            }
            if ((arrstring = arrstring.trim()).equals((Object)"") || arrstring.startsWith("//") || (arrstring = arrstring.split("::=", -1)).length != 2) continue;
            hashMap.put((Object)arrstring[0].trim(), (Object)StringProcess.trim(arrstring[1], Character.valueOf((char)'\"'), Character.valueOf((char)';'), Character.valueOf((char)' '), Character.valueOf((char)'\t'), Character.valueOf((char)'\u201c'), Character.valueOf((char)'\u201d'), Character.valueOf((char)'\uff1b')));
            continue;
        }
    }
}

