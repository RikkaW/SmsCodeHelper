package com.android.mms.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FestivalUpdater
{
  private static int MESSAGE_INCREMENTAL_SIZE = 20;
  private final Context mContext;
  private final SQLiteDatabase mDatabase;
  private final int mScreenWidth;
  
  public FestivalUpdater(Context paramContext, SQLiteDatabase paramSQLiteDatabase)
  {
    mContext = paramContext;
    mDatabase = paramSQLiteDatabase;
    new DisplayMetrics();
    mScreenWidth = mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
  }
  
  private long getDataIdByUrl(int paramInt, String paramString)
    throws FestivalUpdater.HttpReadException
  {
    Object localObject = mDatabase.query("data", new String[] { "_id" }, "url=?", new String[] { paramString }, null, null, null);
    if (localObject != null) {}
    try
    {
      if (((Cursor)localObject).moveToFirst())
      {
        long l = ((Cursor)localObject).getLong(0);
        ((Cursor)localObject).close();
        return l;
      }
      ((Cursor)localObject).close();
      localObject = httpGet(paramString);
      if (localObject == null) {
        throw new HttpReadException(null);
      }
    }
    finally
    {
      ((Cursor)localObject).close();
    }
    try
    {
      localObject = EntityUtils.toByteArray((HttpEntity)localObject);
      if ((localObject != null) && (localObject.length > 0))
      {
        ContentValues localContentValues = new ContentValues(3);
        localContentValues.put("type", Integer.valueOf(paramInt));
        localContentValues.put("url", paramString);
        localContentValues.put("data", (byte[])localObject);
        return mDatabase.insert("data", null, localContentValues);
      }
    }
    catch (ParseException paramString)
    {
      throw new HttpReadException(null);
    }
    catch (IOException paramString)
    {
      throw new HttpReadException(null);
    }
    throw new HttpReadException(null);
  }
  
  private HttpEntity httpGet(String paramString)
    throws FestivalUpdater.HttpReadException
  {
    LogTag.verbose("Festival updater is downloading %s", new Object[] { paramString });
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 30000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 30000);
    localObject = new DefaultHttpClient((HttpParams)localObject);
    paramString = new HttpGet(paramString);
    try
    {
      paramString = ((DefaultHttpClient)localObject).execute(paramString);
      if (paramString.getStatusLine().getStatusCode() != 200) {
        throw new HttpReadException(null);
      }
    }
    catch (ClientProtocolException paramString)
    {
      throw new HttpReadException(null);
    }
    catch (IOException paramString)
    {
      throw new HttpReadException(null);
    }
    return paramString.getEntity();
  }
  
  private JSONObject httpGetResponse(String paramString)
    throws FestivalUpdater.HttpReadException, ParseException, IOException, JSONException, FestivalUpdater.JSONContentException
  {
    paramString = httpGet(paramString);
    if (paramString == null) {
      throw new HttpReadException(null);
    }
    paramString = new JSONObject(EntityUtils.toString(paramString, "UTF-8"));
    if (!"ok".equals(paramString.getString("result"))) {
      throw new JSONContentException(null);
    }
    return paramString;
  }
  
  private void processCategories(JSONArray paramJSONArray)
    throws JSONException, FestivalUpdater.JSONContentException, FestivalUpdater.HttpReadException, ParseException, IOException
  {
    HashMap localHashMap = Maps.newHashMap();
    int i = 0;
    JSONArray localJSONArray;
    int j;
    Object localObject2;
    Object localObject1;
    while (i < paramJSONArray.length())
    {
      localJSONArray = paramJSONArray.getJSONArray(i);
      j = 0;
      while (j < localJSONArray.length())
      {
        if (Thread.currentThread().isInterrupted())
        {
          LogTag.warn("Category process interrupted.", new Object[0]);
          return;
        }
        localObject2 = localJSONArray.getJSONObject(j);
        localObject1 = ((JSONObject)localObject2).optString("imageUrl", null);
        localObject2 = ((JSONObject)localObject2).optString("descImageUrl", null);
        if (localObject1 != null) {
          localHashMap.put(localObject1, Long.valueOf(getDataIdByUrl(1, (String)localObject1)));
        }
        if (localObject2 != null) {
          localHashMap.put(localObject2, Long.valueOf(getDataIdByUrl(1, (String)localObject2)));
        }
        j += 1;
      }
      i += 1;
    }
    mDatabase.beginTransaction();
    for (;;)
    {
      try
      {
        mDatabase.delete("categories", null, null);
        mDatabase.delete("messages", null, null);
        i = 0;
        if (i < paramJSONArray.length())
        {
          localJSONArray = paramJSONArray.getJSONArray(i);
          j = 0;
          if (j < localJSONArray.length())
          {
            if (Thread.currentThread().isInterrupted())
            {
              LogTag.warn("Category process interrupted.", new Object[0]);
              return;
            }
            localObject1 = localJSONArray.getJSONObject(j);
            long l = ((JSONObject)localObject1).getLong("gid");
            localObject2 = ((JSONObject)localObject1).getString("title");
            String str1 = ((JSONObject)localObject1).optString("descText", null);
            int k = ((JSONObject)localObject1).getInt("count");
            String str2 = ((JSONObject)localObject1).optString("imageUrl", null);
            String str3 = ((JSONObject)localObject1).optString("descImageUrl", null);
            LogTag.verbose("Festival updater is processing category %s with %d messages", new Object[] { localObject2, Integer.valueOf(k) });
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("category_id", Long.valueOf(l));
            localContentValues.put("row", Integer.valueOf(i));
            localContentValues.put("title", (String)localObject2);
            localContentValues.put("image_id", (Long)localHashMap.get(str2));
            localContentValues.put("desc_image_id", (Long)localHashMap.get(str3));
            localContentValues.put("image_text", str1);
            localContentValues.put("sms_count", Integer.valueOf(k));
            mDatabase.insert("categories", null, localContentValues);
            processMessages(((JSONObject)localObject1).getJSONArray("message"), l);
            j += 1;
            continue;
          }
        }
        else
        {
          mDatabase.delete("data", "type=1 AND _id NOT IN (SELECT image_id FROM categories UNION SELECT desc_image_id FROM categories)", null);
          mDatabase.setTransactionSuccessful();
          return;
        }
      }
      finally
      {
        mDatabase.endTransaction();
      }
      i += 1;
    }
  }
  
  private void processMessages(JSONArray paramJSONArray, long paramLong)
    throws JSONException
  {
    mDatabase.beginTransaction();
    int i = 0;
    try
    {
      while (i < paramJSONArray.length())
      {
        Object localObject = paramJSONArray.getJSONObject(i);
        long l = ((JSONObject)localObject).getLong("mid");
        localObject = ((JSONObject)localObject).getString("text");
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("message_id", Long.valueOf(l));
        localContentValues.put("category_id", Long.valueOf(paramLong));
        localContentValues.put("text", (String)localObject);
        mDatabase.insert("messages", null, localContentValues);
        i += 1;
      }
      mDatabase.setTransactionSuccessful();
      return;
    }
    finally
    {
      mDatabase.endTransaction();
    }
  }
  
  public void getMoreMessages(long paramLong)
    throws FestivalUpdater.HttpReadException, JSONException, FestivalUpdater.JSONContentException, FestivalUpdater.DatabaseContentException, ParseException, IOException
  {
    Cursor localCursor = mDatabase.rawQuery("SELECT MIN(message_id) FROM messages WHERE category_id=" + paramLong, null);
    if (localCursor == null) {
      throw new DatabaseContentException(null);
    }
    try
    {
      if (localCursor.moveToFirst()) {
        processMessages(httpGetResponse(String.format("http://api.comm.miui.com/miuisms/res/messages?cat=%s&marker=%s&count=%s", new Object[] { Long.valueOf(paramLong), Long.valueOf(localCursor.getLong(0)), Integer.valueOf(MESSAGE_INCREMENTAL_SIZE) })).getJSONObject("data").getJSONArray("entries"), paramLong);
      }
      return;
    }
    finally
    {
      localCursor.close();
    }
  }
  
  public void updateMessages()
    throws FestivalUpdater.HttpReadException, JSONException, FestivalUpdater.JSONContentException, ParseException, IOException
  {
    if (httpGetResponse("http://api.comm.miui.com/miuisms/res/version").getLong("data") == PreferenceManager.getDefaultSharedPreferences(mContext).getLong("festival_message_version", 0L)) {
      return;
    }
    processCategories(httpGetResponse(String.format("http://api.comm.miui.com/miuisms/res/categories?width=%s", new Object[] { Integer.valueOf(mScreenWidth) })).getJSONArray("data"));
  }
  
  private static class DatabaseContentException
    extends Exception
  {
    private static final long serialVersionUID = 1L;
  }
  
  private static class HttpReadException
    extends Exception
  {
    private static final long serialVersionUID = 1L;
  }
  
  private static class JSONContentException
    extends Exception
  {
    private static final long serialVersionUID = 1L;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.FestivalUpdater
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */