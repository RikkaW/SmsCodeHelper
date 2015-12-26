/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.webkit.MimeTypeMap
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.mx.data;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.xiaomi.mms.mx.utils.AttachmentUtils;
import com.xiaomi.mms.mx.utils.Log;
import java.io.File;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class Attachment
implements Serializable {
    public long attId;
    public long datasize;
    public long expireAt;
    public String filename;
    public int height;
    public String link;
    public boolean mIsRead;
    public String mimeType;
    public int playTime;
    public String shareId;
    public int width;

    public Attachment() {
        this.mIsRead = false;
        this.width = 0;
        this.height = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public Attachment(JSONObject jSONObject) throws JSONException {
        boolean bl = false;
        this.mIsRead = false;
        this.width = 0;
        this.height = 0;
        if (jSONObject != null) {
            this.attId = jSONObject.optLong("attId");
            this.mimeType = jSONObject.optString("mime_type", null);
            this.filename = jSONObject.optString("filename", null);
            if (TextUtils.isEmpty((CharSequence)this.mimeType) && !TextUtils.isEmpty((CharSequence)this.filename)) {
                this.mimeType = Attachment.getMimeType(this.filename);
            }
            this.datasize = jSONObject.optLong("filesize", 0);
            this.playTime = jSONObject.optInt("play_time");
            this.width = jSONObject.optInt("width", 0);
            this.height = jSONObject.optInt("height", 0);
            this.shareId = jSONObject.optString("share_id", null);
            this.expireAt = jSONObject.optLong("expires", 0);
            this.link = jSONObject.optString("link", null);
            if (jSONObject.optInt("is_read", 0) != 0) {
                bl = true;
            }
            this.mIsRead = bl;
        }
    }

    private File getFilebyKey(Context object, String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2) && !TextUtils.isEmpty((CharSequence)(object = AttachmentUtils.getCachedImagePath((Context)object, string2))) && (object = new File((String)object)).exists()) {
            return object;
        }
        return null;
    }

    public static String getMimeType(String string2) {
        String string3 = "";
        int n = string2.lastIndexOf(46);
        if (n > 0) {
            string2 = string2.substring(n + 1);
            string3 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(string2);
        }
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public JSONObject generateJSONObject(boolean bl) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty((CharSequence)this.mimeType)) {
                jSONObject.put("mime_type", (Object)this.mimeType);
            }
            if (!bl) {
                jSONObject.put("filename", (Object)this.filename);
                jSONObject.put("attId", this.attId);
            }
            jSONObject.put("filesize", this.datasize);
            if (!TextUtils.isEmpty((CharSequence)this.shareId)) {
                jSONObject.put("share_id", (Object)this.shareId);
            }
            if (!TextUtils.isEmpty((CharSequence)this.link)) {
                jSONObject.put("link", (Object)this.link);
            }
            if (this.playTime > 0) {
                jSONObject.put("play_time", this.playTime);
            }
            if (this.width > 0) {
                jSONObject.put("width", this.width);
            }
            if (this.height > 0) {
                jSONObject.put("height", this.height);
            }
            int n = this.mIsRead ? 1 : 0;
            jSONObject.put("is_read", n);
            return jSONObject;
        }
        catch (JSONException var3_3) {
            Log.e("Attachment", "generateJSONObject exception :" + (Object)var3_3);
            return null;
        }
    }

    public JSONObject generateLocalJSONObject() {
        return this.generateJSONObject(false);
    }

    public JSONObject generateServerJSONObject() {
        return this.generateJSONObject(true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public File getFile(Context context, String string2) {
        File file = null;
        switch (AttachmentUtils.getMessageTypeFromMimeType(this.mimeType)) {
            default: {
                return file;
            }
            case 2: {
                string2 = file = this.getFilebyKey(context, this.filename);
                if (file != null) return string2;
                return this.getFilebyKey(context, this.shareId);
            }
            case 3: 
        }
        return new File(string2 + "/" + this.filename);
    }

    public String getLocalPath(Context context, String string2) {
        switch (AttachmentUtils.getMessageTypeFromMimeType(this.mimeType)) {
            default: {
                return null;
            }
            case 2: {
                return AttachmentUtils.getCachedImagePath(context, this.filename);
            }
            case 3: 
        }
        return string2 + "/" + this.filename;
    }
}

