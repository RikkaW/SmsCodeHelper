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
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.List;

public class TimeSpanRecognition {
    private static ACAutomation actimeSpanNextWords;
    private static boolean ifInitial;
    private static List<String> timeSpanNextWords;
    private static String timeSpanNextWordsFileName;

    static {
        timeSpanNextWordsFileName = null;
        ifInitial = false;
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        timeSpanNextWords.clear();
        actimeSpanNextWords = null;
        ifInitial = false;
        return true;
    }

    public static boolean initial() throws IOException {
        if (ifInitial) {
            return true;
        }
        timeSpanNextWordsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/timeSpanNextWords.txt";
        timeSpanNextWords = FileOperator.readFile(timeSpanNextWordsFileName);
        actimeSpanNextWords = new ACAutomation(timeSpanNextWords);
        ifInitial = true;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static double isRightNumber(EntityInfo entityInfo, String string2) {
        int[] arrn;
        block6 : {
            int n;
            if (ifInitial && (n = entityInfo.getNumberCount()) > 0 && n + 1 >= entityInfo.getTarget().length()) {
                arrn = actimeSpanNextWords.findFirst(string2, entityInfo.getEndPosition());
                if (arrn != null && arrn[1] == entityInfo.getEndPosition()) {
                    entityInfo.setRemark(timeSpanNextWords.get(arrn[0]));
                    entityInfo.noNomal();
                    return 1.0;
                }
                if (arrn != null) {
                    int n2 = 1;
                    n = entityInfo.getEndPosition();
                    do {
                        if (n >= arrn[1]) {
                            n = n2;
                            if (n == 0) break;
                            break block6;
                        }
                        char c2 = string2.charAt(n);
                        if (c2 != ' ' && c2 != '\t') {
                            return 0.0;
                        }
                        ++n;
                    } while (true);
                }
            }
            return 0.0;
        }
        entityInfo.setRemark(timeSpanNextWords.get(arrn[0]));
        entityInfo.noNomal();
        return 1.0;
    }
}

