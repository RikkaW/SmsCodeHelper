/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.text.TextUtils
 *  android.util.Pair
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.HashMap
 *  java.util.Locale
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 *  miui.os.Build
 */
package com.android.mms.model;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Pair;
import com.android.mms.model.ContactMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import miui.os.Build;

public class ContactParser {
    private static Pattern CONTACT_INFO_PTN;
    private static ContactParser mParser;
    private static final String[] sContactEntiesCN;
    private static final String[] sContactEntiesEN;
    private static final String[] sContactEntiesTW;
    private HashMap<String, String> mLabelTypeMap = new HashMap();
    private HashMap<String, String> mTypeLabelMap = new HashMap();
    private ArrayList<String> mTypes;

    static {
        sContactEntiesEN = new String[]{"Name", "Nickname", "Phone", "Email", "Organization", "IM", "SIP Phone", "Birthday", "Website", "Address", "Note"};
        sContactEntiesCN = new String[]{"\u59d3\u540d", "\u6635\u79f0", "\u7535\u8bdd", "\u90ae\u4ef6", "\u516c\u53f8", "\u5373\u65f6\u6d88\u606f", "\u7f51\u7edc\u7535\u8bdd", "\u751f\u65e5", "\u7f51\u5740", "\u5730\u5740", "\u5907\u6ce8"};
        sContactEntiesTW = new String[]{"\u59d3\u540d", "\u533f\u7a31", "\u96fb\u8a71", "\u90f5\u4ef6", "\u6a5f\u69cb", "\u5373\u6642\u6d88\u606f", "\u7db2\u7d61\u96fb\u8a71", "\u751f\u65e5", "\u7db2\u5740", "\u4f4d\u5740", "\u5099\u8a3b"};
        CONTACT_INFO_PTN = Pattern.compile((String)"\\[(.+?)\\]((.|\n)*?);");
    }

    /*
     * Enabled aggressive block sorting
     */
    private ContactParser(Context object) {
        boolean bl;
        boolean bl2;
        String[] arrstring = object.getResources().getStringArray(2131230724);
        Object[] arrobject = object.getResources().getStringArray(2131230725);
        this.mTypes = new ArrayList((Collection)Arrays.asList((Object[])arrobject));
        int n = Math.min((int)arrstring.length, (int)arrobject.length);
        object = object.getResources().getConfiguration().locale.getLanguage();
        if (Build.IS_INTERNATIONAL_BUILD) {
            bl2 = false;
            bl = !"en".equals(object);
        } else {
            bl = !"zh".equals(object) && !"en".equals(object);
            bl2 = true;
        }
        int n2 = 0;
        while (n2 < n) {
            this.mLabelTypeMap.put((Object)sContactEntiesEN[n2], arrobject[n2]);
            if (bl2) {
                this.mLabelTypeMap.put((Object)sContactEntiesCN[n2], arrobject[n2]);
                this.mLabelTypeMap.put((Object)sContactEntiesTW[n2], arrobject[n2]);
            }
            if (bl) {
                this.mLabelTypeMap.put((Object)arrstring[n2], arrobject[n2]);
            }
            this.mTypeLabelMap.put(arrobject[n2], (Object)arrstring[n2]);
            ++n2;
        }
    }

    public static ContactParser getContactParser(Context context) {
        if (mParser == null) {
            mParser = new ContactParser(context);
        }
        return mParser;
    }

    public String getContactFromIntent(Intent intent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : this.mTypes) {
            Object object = intent.getStringArrayListExtra(string);
            if (object == null) continue;
            object = object.iterator();
            while (object.hasNext()) {
                String string2 = (String)object.next();
                if (TextUtils.isEmpty((CharSequence)string2)) continue;
                stringBuilder.append("" + '[' + (String)this.mTypeLabelMap.get((Object)string) + ']' + string2.replace(';', ',') + ";\n");
            }
        }
        return stringBuilder.toString();
    }

    public String getTypeLabel(Context arrstring, String string) {
        arrstring = arrstring.getResources().getStringArray(2131230724);
        int n = this.mTypes.indexOf((Object)string);
        if (n == 0) {
            return "type_name";
        }
        if (n > 0 && n < arrstring.length) {
            return arrstring[n];
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     */
    public ContactMessage parseFromMessage(String string) {
        if (TextUtils.isEmpty((CharSequence)string)) {
            return null;
        }
        Matcher matcher = CONTACT_INFO_PTN.matcher((CharSequence)string);
        Object object = null;
        do {
            String string2 = object;
            if (!matcher.find()) return string2;
            int n = matcher.start();
            int n2 = matcher.end();
            Object object2 = matcher.group(1).trim();
            String string3 = matcher.group(2).trim();
            if (this.mLabelTypeMap.containsKey(object2)) {
                string2 = (String)this.mLabelTypeMap.get(object2);
            } else {
                string2 = object2;
                if (!this.mTypeLabelMap.containsKey(object2)) continue;
            }
            object2 = object;
            if (object == null) {
                object2 = new ContactMessage(string, this.mTypeLabelMap);
            }
            object2.addRecord(string2, string3, n, n2);
            object = object2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public Intent putContactToIntent(ContactMessage.ContactRecord object, Intent intent) {
        if (object != null) {
            HashMap hashMap = new HashMap();
            for (Pair pair : object.getContactRecord()) {
                Object object2;
                object = object2 = (ArrayList)hashMap.get(pair.first);
                if (object2 == null) {
                    object = new ArrayList();
                    hashMap.put(pair.first, object);
                }
                object.add(pair.second);
            }
            for (Object object2 : hashMap.keySet()) {
                intent.putStringArrayListExtra((String)object2, (ArrayList)hashMap.get(object2));
            }
        }
        return intent;
    }
}

