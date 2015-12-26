/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.ACAutomation;
import com.xiaomi.common.FileOperator;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.List;

public class ChongzhiRecognition {
    private static ACAutomation acChongzhiWords;
    private static String chongzhiWordsFileName;
    private static boolean ifInitial;

    static {
        chongzhiWordsFileName = null;
        ifInitial = false;
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        acChongzhiWords = null;
        ifInitial = false;
        return true;
    }

    public static boolean initial() throws IOException {
        if (ifInitial) {
            return true;
        }
        chongzhiWordsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/chongzhiWords.txt";
        acChongzhiWords = new ACAutomation((List<String>)FileOperator.readFile(chongzhiWordsFileName));
        ifInitial = true;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static double isRight(String string2) {
        if (!ifInitial || acChongzhiWords.findFirst(string2, 0) == null) {
            return 0.0;
        }
        return 1.0;
    }
}

