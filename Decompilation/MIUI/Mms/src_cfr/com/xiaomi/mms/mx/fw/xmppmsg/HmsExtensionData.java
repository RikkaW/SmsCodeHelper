/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.mx.fw.xmppmsg;

import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.xmppmsg.ExtensionData;
import com.xiaomi.mms.mx.utils.Log;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HmsExtensionData
extends ExtensionData {
    private static final long serialVersionUID = -6972215280115358541L;
    private boolean mIsTemplate;
    private HmsMessageEntry mMessageEntry;
    private String mOwnerID;
    private String mOwnerName;
    private String mRawBody;
    private int mType;
    private String mXMPPMessageString;

    private HmsExtensionData(String string2) {
        this.mExtensionString = string2;
        try {
            this.parseHmsExtJSON(new JSONObject(string2));
            return;
        }
        catch (JSONException var1_2) {
            Log.e("HmsExtensionData", var1_2.toString());
            return;
        }
    }

    public static HmsExtensionData get(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            return new HmsExtensionData(string2);
        }
        return null;
    }

    private void parseHmsExtJSON(JSONObject jSONObject) throws JSONException {
        this.mOwnerID = jSONObject.getString("owner");
        this.mType = jSONObject.optInt("type");
        this.mOwnerName = jSONObject.optString("name");
        this.mRawBody = jSONObject.optString("body");
        this.mXMPPMessageString = jSONObject.getString("msg");
        this.mIsTemplate = jSONObject.has("template");
        if ((jSONObject = jSONObject.getJSONArray("msg")) != null && jSONObject.length() > 0) {
            this.mMessageEntry = new HmsMessageEntry(jSONObject.getJSONObject(0));
        }
    }

    public HmsMessageEntry getHmsMessageEntry() {
        return this.mMessageEntry;
    }

    public static class HmsMessageEntry
    implements Serializable {
        private static final long serialVersionUID = -235423453453411L;
        public String audio;
        public String content;
        public String id;
        public int messageSource;
        public String multiModule;
        public String pic;
        public String source;
        public String title;
        public int type;
        public String url;
        public String video;

        public HmsMessageEntry() {
        }

        public HmsMessageEntry(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.id = jSONObject.optString("id");
                this.title = jSONObject.optString("title");
                this.content = jSONObject.optString("content");
                this.source = jSONObject.optString("source");
                this.url = jSONObject.optString("url");
                this.pic = jSONObject.optString("pic");
                this.audio = jSONObject.optString("audio");
                this.video = jSONObject.optString("video");
                this.multiModule = jSONObject.optString("module");
                this.type = jSONObject.optInt("type");
                this.messageSource = jSONObject.optInt("sendinitiative");
            }
        }
    }

}

