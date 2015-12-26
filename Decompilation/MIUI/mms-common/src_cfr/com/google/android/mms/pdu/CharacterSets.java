/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.google.android.mms.pdu;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class CharacterSets {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int ANY_CHARSET = 0;
    public static final int BIG5 = 2026;
    public static final int DEFAULT_CHARSET = 106;
    public static final String DEFAULT_CHARSET_NAME = "utf-8";
    public static final int ISO_8859_1 = 4;
    public static final int ISO_8859_2 = 5;
    public static final int ISO_8859_3 = 6;
    public static final int ISO_8859_4 = 7;
    public static final int ISO_8859_5 = 8;
    public static final int ISO_8859_6 = 9;
    public static final int ISO_8859_7 = 10;
    public static final int ISO_8859_8 = 11;
    public static final int ISO_8859_9 = 12;
    private static final int[] MIBENUM_NUMBERS;
    private static final HashMap<Integer, String> MIBENUM_TO_NAME_MAP;
    public static final String MIMENAME_ANY_CHARSET = "*";
    public static final String MIMENAME_BIG5 = "big5";
    public static final String MIMENAME_ISO_8859_1 = "iso-8859-1";
    public static final String MIMENAME_ISO_8859_2 = "iso-8859-2";
    public static final String MIMENAME_ISO_8859_3 = "iso-8859-3";
    public static final String MIMENAME_ISO_8859_4 = "iso-8859-4";
    public static final String MIMENAME_ISO_8859_5 = "iso-8859-5";
    public static final String MIMENAME_ISO_8859_6 = "iso-8859-6";
    public static final String MIMENAME_ISO_8859_7 = "iso-8859-7";
    public static final String MIMENAME_ISO_8859_8 = "iso-8859-8";
    public static final String MIMENAME_ISO_8859_9 = "iso-8859-9";
    public static final String MIMENAME_SHIFT_JIS = "shift_JIS";
    public static final String MIMENAME_UCS2 = "iso-10646-ucs-2";
    public static final String MIMENAME_US_ASCII = "us-ascii";
    public static final String MIMENAME_UTF_16 = "utf-16";
    public static final String MIMENAME_UTF_8 = "utf-8";
    private static final String[] MIME_NAMES;
    private static final HashMap<String, Integer> NAME_TO_MIBENUM_MAP;
    public static final int SHIFT_JIS = 17;
    public static final int UCS2 = 1000;
    public static final int US_ASCII = 3;
    public static final int UTF_16 = 1015;
    public static final int UTF_8 = 106;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !CharacterSets.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
        MIBENUM_NUMBERS = new int[]{0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 17, 106, 2026, 1000, 1015};
        MIME_NAMES = new String[]{"*", "us-ascii", "iso-8859-1", "iso-8859-2", "iso-8859-3", "iso-8859-4", "iso-8859-5", "iso-8859-6", "iso-8859-7", "iso-8859-8", "iso-8859-9", "shift_JIS", "utf-8", "big5", "iso-10646-ucs-2", "utf-16"};
        MIBENUM_TO_NAME_MAP = new HashMap();
        NAME_TO_MIBENUM_MAP = new HashMap();
        if (!$assertionsDisabled && MIBENUM_NUMBERS.length != MIME_NAMES.length) {
            throw new AssertionError();
        }
        int n = MIBENUM_NUMBERS.length;
        int n2 = 0;
        while (n2 <= n - 1) {
            MIBENUM_TO_NAME_MAP.put((Object)MIBENUM_NUMBERS[n2], (Object)MIME_NAMES[n2]);
            NAME_TO_MIBENUM_MAP.put((Object)MIME_NAMES[n2], (Object)MIBENUM_NUMBERS[n2]);
            ++n2;
        }
    }

    private CharacterSets() {
    }

    public static int getMibEnumValue(String string) throws UnsupportedEncodingException {
        if (string == null) {
            return -1;
        }
        if ((string = (Integer)NAME_TO_MIBENUM_MAP.get((Object)string)) == null) {
            throw new UnsupportedEncodingException();
        }
        return string.intValue();
    }

    public static String getMimeName(int n) throws UnsupportedEncodingException {
        String string = (String)MIBENUM_TO_NAME_MAP.get((Object)n);
        if (string == null) {
            throw new UnsupportedEncodingException();
        }
        return string;
    }
}

