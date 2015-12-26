/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.preference.PreferenceManager
 *  android.util.DisplayMetrics
 *  com.google.android.collect.Maps
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.ParseException
 *  org.apache.http.StatusLine
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.EntityUtils
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.android.mms.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import com.android.mms.LogTag;
import com.google.android.collect.Maps;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FestivalUpdater {
    private static int MESSAGE_INCREMENTAL_SIZE = 20;
    private final Context mContext;
    private final SQLiteDatabase mDatabase;
    private final int mScreenWidth;

    public FestivalUpdater(Context context, SQLiteDatabase sQLiteDatabase) {
        this.mContext = context;
        this.mDatabase = sQLiteDatabase;
        new DisplayMetrics();
        this.mScreenWidth = this.mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private long getDataIdByUrl(int var1_1, String var2_2) throws HttpReadException {
        block8 : {
            var5_6 = this.mDatabase.query("data", new String[]{"_id"}, "url=?", new String[]{var2_2}, null, null, null);
            if (var5_6 != null) {
                if (!var5_6.moveToFirst()) break block8;
                var3_7 = var5_6.getLong(0);
                return var3_7;
            }
        }
        if ((var5_6 = this.httpGet(var2_2)) == null) {
            throw new HttpReadException();
        }
        ** GOTO lbl14
        finally {
            var5_6.close();
        }
lbl14: // 1 sources:
        try {
            var5_6 = EntityUtils.toByteArray((HttpEntity)var5_6);
            if (var5_6 == null) throw new HttpReadException();
        }
        catch (ParseException var2_4) {
            throw new HttpReadException();
        }
        catch (IOException var2_5) {
            throw new HttpReadException();
        }
        if (var5_6.length <= 0) throw new HttpReadException();
        var6_8 = new ContentValues(3);
        var6_8.put("type", Integer.valueOf((int)var1_1));
        var6_8.put("url", var2_2);
        var6_8.put("data", (byte[])var5_6);
        return this.mDatabase.insert("data", null, var6_8);
    }

    private HttpEntity httpGet(String string) throws HttpReadException {
        LogTag.verbose("Festival updater is downloading %s", string);
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, (int)30000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, (int)30000);
        basicHttpParams = new DefaultHttpClient((HttpParams)basicHttpParams);
        string = new HttpGet(string);
        try {
            string = basicHttpParams.execute((HttpUriRequest)string);
        }
        catch (ClientProtocolException var1_2) {
            throw new HttpReadException();
        }
        catch (IOException var1_3) {
            throw new HttpReadException();
        }
        if (string.getStatusLine().getStatusCode() != 200) {
            throw new HttpReadException();
        }
        return string.getEntity();
    }

    private JSONObject httpGetResponse(String string) throws HttpReadException, ParseException, IOException, JSONException, JSONContentException {
        if ((string = this.httpGet(string)) == null) {
            throw new HttpReadException();
        }
        if (!"ok".equals((Object)(string = new JSONObject(EntityUtils.toString((HttpEntity)string, (String)"UTF-8"))).getString("result"))) {
            throw new JSONContentException();
        }
        return string;
    }

    private void processCategories(JSONArray jSONArray) throws JSONException, JSONContentException, HttpReadException, ParseException, IOException {
        int n;
        String string;
        Object object;
        int n2;
        JSONArray jSONArray2;
        HashMap hashMap = Maps.newHashMap();
        for (n2 = 0; n2 < jSONArray.length(); ++n2) {
            jSONArray2 = jSONArray.getJSONArray(n2);
            for (n = 0; n < jSONArray2.length(); ++n) {
                if (Thread.currentThread().isInterrupted()) {
                    LogTag.warn("Category process interrupted.", new Object[0]);
                    return;
                }
                object = jSONArray2.getJSONObject(n);
                string = object.optString("imageUrl", null);
                object = object.optString("descImageUrl", null);
                if (string != null) {
                    hashMap.put((Object)string, (Object)this.getDataIdByUrl(1, string));
                }
                if (object == null) continue;
                hashMap.put(object, (Object)this.getDataIdByUrl(1, (String)object));
            }
        }
        this.mDatabase.beginTransaction();
        this.mDatabase.delete("categories", null, null);
        this.mDatabase.delete("messages", null, null);
        n2 = 0;
        do {
            block14 : {
                block13 : {
                    if (n2 >= jSONArray.length()) break block13;
                    jSONArray2 = jSONArray.getJSONArray(n2);
                    n = 0;
                    do {
                        block15 : {
                            if (n >= jSONArray2.length()) break block14;
                            if (!Thread.currentThread().isInterrupted()) break block15;
                            LogTag.warn("Category process interrupted.", new Object[0]);
                            this.mDatabase.endTransaction();
                            return;
                        }
                        string = jSONArray2.getJSONObject(n);
                        long l = string.getLong("gid");
                        object = string.getString("title");
                        String string2 = string.optString("descText", null);
                        int n3 = string.getInt("count");
                        String string3 = string.optString("imageUrl", null);
                        String string4 = string.optString("descImageUrl", null);
                        LogTag.verbose("Festival updater is processing category %s with %d messages", object, n3);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("category_id", Long.valueOf((long)l));
                        contentValues.put("row", Integer.valueOf((int)n2));
                        contentValues.put("title", (String)object);
                        contentValues.put("image_id", (Long)hashMap.get((Object)string3));
                        contentValues.put("desc_image_id", (Long)hashMap.get((Object)string4));
                        contentValues.put("image_text", string2);
                        contentValues.put("sms_count", Integer.valueOf((int)n3));
                        this.mDatabase.insert("categories", null, contentValues);
                        this.processMessages(string.getJSONArray("message"), l);
                        ++n;
                        continue;
                        break;
                    } while (true);
                }
                try {
                    this.mDatabase.delete("data", "type=1 AND _id NOT IN (SELECT image_id FROM categories UNION SELECT desc_image_id FROM categories)", null);
                    this.mDatabase.setTransactionSuccessful();
                    return;
                }
                catch (Throwable var1_2) {
                    throw var1_2;
                }
                finally {
                    this.mDatabase.endTransaction();
                }
            }
            ++n2;
        } while (true);
    }

    private void processMessages(JSONArray jSONArray, long l) throws JSONException {
        this.mDatabase.beginTransaction();
        int n = 0;
        do {
            if (n >= jSONArray.length()) break;
            Object object = jSONArray.getJSONObject(n);
            long l2 = object.getLong("mid");
            object = object.getString("text");
            ContentValues contentValues = new ContentValues();
            contentValues.put("message_id", Long.valueOf((long)l2));
            contentValues.put("category_id", Long.valueOf((long)l));
            contentValues.put("text", (String)object);
            this.mDatabase.insert("messages", null, contentValues);
            ++n;
            continue;
            break;
        } while (true);
        try {
            this.mDatabase.setTransactionSuccessful();
            return;
        }
        catch (Throwable var1_2) {
            throw var1_2;
        }
        finally {
            this.mDatabase.endTransaction();
        }
    }

    public void getMoreMessages(long l) throws HttpReadException, JSONException, JSONContentException, DatabaseContentException, ParseException, IOException {
        Cursor cursor = this.mDatabase.rawQuery("SELECT MIN(message_id) FROM messages WHERE category_id=" + l, null);
        if (cursor == null) {
            throw new DatabaseContentException();
        }
        try {
            if (cursor.moveToFirst()) {
                this.processMessages(this.httpGetResponse(String.format((String)"http://api.comm.miui.com/miuisms/res/messages?cat=%s&marker=%s&count=%s", (Object[])new Object[]{l, cursor.getLong(0), MESSAGE_INCREMENTAL_SIZE})).getJSONObject("data").getJSONArray("entries"), l);
            }
            return;
        }
        finally {
            cursor.close();
        }
    }

    public void updateMessages() throws HttpReadException, JSONException, JSONContentException, ParseException, IOException {
        if (this.httpGetResponse("http://api.comm.miui.com/miuisms/res/version").getLong("data") == PreferenceManager.getDefaultSharedPreferences((Context)this.mContext).getLong("festival_message_version", 0)) {
            return;
        }
        this.processCategories(this.httpGetResponse(String.format((String)"http://api.comm.miui.com/miuisms/res/categories?width=%s", (Object[])new Object[]{this.mScreenWidth})).getJSONArray("data"));
    }

    private static class DatabaseContentException
    extends Exception {
        private static final long serialVersionUID = 1;

        private DatabaseContentException() {
        }
    }

    private static class HttpReadException
    extends Exception {
        private static final long serialVersionUID = 1;

        private HttpReadException() {
        }
    }

    private static class JSONContentException
    extends Exception {
        private static final long serialVersionUID = 1;

        private JSONContentException() {
        }
    }

}

