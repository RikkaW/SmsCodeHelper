/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.mx.utils;

import android.text.TextUtils;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.AttachmentUtils;
import com.xiaomi.mms.mx.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mx2ExtentionHelper {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String convertServerExtensionToLocal(String string2) {
        String string3 = null;
        if (TextUtils.isEmpty((CharSequence)string2)) return string3;
        string2 = new JSONArray(string2);
        int n = 0;
        do {
            if (n >= string2.length()) break;
            string2.getJSONObject(n).put("attId", AttachmentUtils.generateAttachmentId());
            ++n;
            continue;
            break;
        } while (true);
        try {
            return string2.toString();
        }
        catch (JSONException jSONException) {
            Log.v("Mx2ExtentionHelper", "convertReceivedExtensionStringToLocal Exception " + (Object)jSONException);
            return null;
        }
    }

    public static String generateAttachmentsExtentionString(List<Attachment> object, boolean bl) {
        Object object2 = null;
        if (object != null) {
            object2 = new JSONArray();
            object = object.iterator();
            while (object.hasNext()) {
                Attachment attachment = (Attachment)object.next();
                if (bl) {
                    object2.put((Object)attachment.generateServerJSONObject());
                    continue;
                }
                object2.put((Object)attachment.generateLocalJSONObject());
            }
            object2 = object2.toString();
        }
        return object2;
    }

    public static ArrayList<Attachment> praseAttachmentsExtentionString(String string2) {
        ArrayList arrayList = null;
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            int n;
            ArrayList arrayList2 = new ArrayList();
            try {
                string2 = new JSONArray(string2);
                n = 0;
            }
            catch (JSONException var0_1) {
                Log.v("Mx2ExtentionHelper", "praseAttachmentsExtentionString Exception " + (Object)var0_1);
                arrayList = arrayList2;
            }
            do {
                arrayList = arrayList2;
                if (n < string2.length()) {
                    arrayList2.add((Object)new Attachment(string2.getJSONObject(n)));
                    ++n;
                    continue;
                }
                break;
            } while (true);
        }
        return arrayList;
    }
}

