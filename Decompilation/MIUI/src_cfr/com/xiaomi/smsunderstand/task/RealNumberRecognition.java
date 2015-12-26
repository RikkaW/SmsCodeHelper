/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.StringProcess;
import com.xiaomi.smsunderstand.EntityInfo;

public class RealNumberRecognition {
    public static double isRightNumber(EntityInfo entityInfo, String string2) {
        string2 = entityInfo.getTarget();
        int n = entityInfo.getNumberCount();
        if (n == string2.length()) {
            entityInfo.setRemark("Integer");
            return 1.0;
        }
        int n2 = StringProcess.getCharCount(string2, Character.valueOf((char)'.'));
        if (n2 != 1 || n + n2 != string2.length()) {
            return 0.0;
        }
        entityInfo.setRemark("Double");
        return 1.0;
    }
}

