/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
package com.xiaomi.mms.net;

import android.text.TextUtils;
import com.xiaomi.mms.net.CloudRequestFactory;
import com.xiaomi.mms.net.SimpleRequest;
import com.xiaomi.mms.net.exception.InvalidResponseException;
import com.xiaomi.mms.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import org.json.JSONObject;

public class CloudRequestExecutor {
    private static final Integer INT_0 = 0;

    private static void checkResponse(byte[] arrby) throws IOException {
        if (arrby == null || arrby.length == 0) {
            throw new IOException("failed to get response from server");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static JSONObject getIDCConfig() throws IOException, InvalidResponseException {
        SimpleRequest simpleRequest = CloudRequestFactory.newGetIDCConfigRequest();
        Object var1_1 = null;
        try {
            simpleRequest.connect();
            Object object = simpleRequest.getResponseBody();
            CloudRequestExecutor.checkResponse((byte[])object);
            object = new String((byte[])object, "UTF-8");
            if (TextUtils.isEmpty((CharSequence)object)) {
                throw new InvalidResponseException("mx config idc information is null");
            }
            JSONObject jSONObject = new JSONObject((String)object);
            object = var1_1;
            if (jSONObject == null) return object;
            {
                String string2 = jSONObject.optString("result");
                object = var1_1;
                if (TextUtils.isEmpty((CharSequence)string2)) return object;
                {
                    object = var1_1;
                    if (!string2.equalsIgnoreCase("ok")) return object;
                    {
                        object = jSONObject.getJSONObject("data");
                        return object;
                    }
                }
            }
        }
        finally {
            simpleRequest.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getUid(SimpleRequest simpleRequest) throws IOException, InvalidResponseException {
        block8 : {
            Object object;
            long l;
            try {
                simpleRequest.connect();
                object = simpleRequest.getResponseBody();
                CloudRequestExecutor.checkResponse((byte[])object);
                object = ObjectUtils.jsonToMap((byte[])object, "UTF-8");
                if (object == null) {
                    throw new InvalidResponseException("failed to convert result to map");
                }
                Object v = object.get("code");
                if (!INT_0.equals(v) || !((object = object.get("data")) instanceof Map)) break block8;
                object = ((Map)object).get("userId");
                l = -1;
                if (object instanceof Integer) {
                    l = ((Integer)object).intValue();
                } else if (object instanceof Long) {
                    l = (Long)object;
                }
            }
            catch (Throwable var3_2) {
                simpleRequest.close();
                throw var3_2;
            }
            object = l > 0 ? String.valueOf((long)l) : null;
            simpleRequest.close();
            return object;
        }
        simpleRequest.close();
        throw new InvalidResponseException("failed to parse response");
    }
}

