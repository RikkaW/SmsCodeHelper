/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.HashSet
 */
package com.xiaomi.common;

import java.util.HashMap;
import java.util.HashSet;

public class ConstantData {
    public static HashMap<String, Integer> DICMaxLength;
    public static HashMap<String, HashSet<String>> DICs;

    static {
        DICs = new HashMap();
        DICMaxLength = new HashMap();
    }
}

