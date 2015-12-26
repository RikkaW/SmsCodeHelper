package com.android.mms;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.DataSetObserver;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import atj;
import gm;
import gq;
import gr;
import java.util.ArrayList;
import java.util.Iterator;

public class SuggestionsProvider
  extends ContentProvider
{
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
  
  public String getType(Uri paramUri)
  {
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    return null;
  }
  
  public boolean onCreate()
  {
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    paramArrayOfString1 = paramArrayOfString2[0];
    if ((paramArrayOfString2 == null) || (paramArrayOfString2[0] == null) || (paramArrayOfString2[0].equals("")))
    {
      Log.d("SuggestionsProvider", "selectionArgs is null");
      return null;
    }
    paramUri = null;
    try
    {
      if (!TextUtils.isEmpty(paramArrayOfString1))
      {
        Log.d("SuggestionsProvider", "jopencc.Convertor B: " + paramArrayOfString1.hashCode());
        paramUri = atj.c(paramArrayOfString1);
        Log.d("SuggestionsProvider", "jopencc.Convertor E:" + paramArrayOfString1.hashCode());
      }
      if (paramUri != null)
      {
        paramString1 = new ArrayList();
        i = 0;
        if (i < paramUri.length) {
          paramArrayOfString1 = Uri.parse("content://mms-sms/meizuSearchSuggest").buildUpon().appendQueryParameter("pattern", paramUri[i]).build();
        }
      }
    }
    catch (Exception paramUri)
    {
      try
      {
        for (;;)
        {
          int i;
          paramArrayOfString1 = getContext().getContentResolver().query(paramArrayOfString1, null, null, null, null);
          if (paramArrayOfString1 != null) {
            paramString1.add(paramArrayOfString1);
          }
          i += 1;
        }
        paramUri = paramUri;
        paramUri.printStackTrace();
        paramUri = new String[] { paramArrayOfString1 };
      }
      catch (Exception paramArrayOfString1)
      {
        for (;;)
        {
          paramArrayOfString1.printStackTrace();
          paramArrayOfString1 = null;
        }
      }
      paramUri = new MergeCursor((Cursor[])paramString1.toArray(new Cursor[paramString1.size()]));
    }
    for (;;)
    {
      return new a(paramUri, paramArrayOfString2[0]);
      paramUri = new MatrixCursor(new String[] { "index_text", "thread_id", "_id", "msgtype", "address", "group_msg_id" }, 0);
    }
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
  
  class a
    implements CrossProcessCursor
  {
    Cursor a;
    int b;
    int c;
    ArrayList<a> d = new ArrayList();
    String e;
    private String[] g = { "suggest_intent_data", "suggest_intent_action", "suggest_intent_extra_data", "suggest_text_1", "suggest_icon_1", "suggest_text_2" };
    private final int h = 0;
    private final int i = 1;
    private final int j = 2;
    private final int k = 3;
    private final int l = 4;
    private final int m = 5;
    
    public a(Cursor paramCursor, String paramString)
    {
      a = paramCursor;
      e = paramString;
      b = paramCursor.getColumnCount();
      try
      {
        a();
        return;
      }
      catch (SQLiteException this$1)
      {
        d.clear();
      }
    }
    
    private String a(gr paramgr)
    {
      if (paramgr == null) {
        return "";
      }
      Object localObject = paramgr.h();
      paramgr = new StringBuilder();
      int i1 = ((gq)localObject).size();
      localObject = ((gq)localObject).iterator();
      int n = 0;
      for (;;)
      {
        if (((Iterator)localObject).hasNext())
        {
          paramgr.append(((gm)((Iterator)localObject).next()).g());
          if (n < i1 - 1) {
            paramgr.append(", ");
          }
          n += 1;
          if (n < 4) {}
        }
        else
        {
          return paramgr.toString();
        }
      }
    }
    
    private void a()
    {
      int i1 = a.getColumnIndex("index_text");
      int i2 = a.getColumnIndex("thread_id");
      int i3 = a.getColumnIndex("_id");
      int i4 = a.getColumnIndex("msgtype");
      int i5 = a.getColumnIndex("address");
      int i6 = a.getColumnIndex("group_msg_id");
      int i7 = a.getCount();
      Object localObject = null;
      int n = 0;
      if (n < i7)
      {
        a.moveToPosition(n);
        String str1 = a.getString(i1);
        long l1 = a.getLong(i2);
        long l2 = a.getLong(i3);
        int i8 = a.getInt(i4);
        String str2 = a.getString(i5);
        String str3 = a.getString(i6);
        if (TextUtils.equals((CharSequence)localObject, str1)) {
          break label236;
        }
        d.add(new a(n, str1, l1, l2, i8, str2, str3));
        localObject = str1;
      }
      label236:
      for (;;)
      {
        n += 1;
        break;
        return;
      }
    }
    
    public void close()
    {
      a.close();
    }
    
    public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer)
    {
      a.copyStringToBuffer(paramInt, paramCharArrayBuffer);
    }
    
    public void deactivate()
    {
      a.deactivate();
    }
    
    public void fillWindow(int paramInt, CursorWindow paramCursorWindow)
    {
      int n = getCount();
      if ((paramInt < 0) || (paramInt > n + 1)) {
        return;
      }
      paramCursorWindow.acquireReference();
      for (;;)
      {
        try
        {
          i1 = getPosition();
          paramCursorWindow.clear();
          paramCursorWindow.setStartPosition(paramInt);
          i2 = getColumnCount();
          paramCursorWindow.setNumColumns(i2);
          if ((!moveToPosition(paramInt)) || (!paramCursorWindow.allocRow())) {
            continue;
          }
          n = 0;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          int i1;
          int i2;
          String str;
          return;
          n += 1;
          continue;
          moveToPosition(i1);
          return;
        }
        finally
        {
          paramCursorWindow.releaseReference();
        }
        if (n < i2)
        {
          str = getString(n);
          if (str != null)
          {
            if (!paramCursorWindow.putString(str, paramInt, n))
            {
              paramCursorWindow.freeLastRow();
              break label153;
            }
          }
          else if (!paramCursorWindow.putNull(paramInt, n))
          {
            paramCursorWindow.freeLastRow();
            break label153;
          }
        }
        label153:
        paramInt += 1;
      }
    }
    
    public byte[] getBlob(int paramInt)
    {
      return null;
    }
    
    public int getColumnCount()
    {
      return b + g.length;
    }
    
    public int getColumnIndex(String paramString)
    {
      int n = 0;
      while (n < g.length)
      {
        if (g[n].equals(paramString)) {
          return n + b;
        }
        n += 1;
      }
      return a.getColumnIndex(paramString);
    }
    
    public int getColumnIndexOrThrow(String paramString)
    {
      return 0;
    }
    
    public String getColumnName(int paramInt)
    {
      return null;
    }
    
    public String[] getColumnNames()
    {
      String[] arrayOfString1 = a.getColumnNames();
      String[] arrayOfString2 = new String[arrayOfString1.length + g.length];
      System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, arrayOfString1.length);
      System.arraycopy(g, 0, arrayOfString2, arrayOfString1.length, g.length);
      return arrayOfString2;
    }
    
    public int getCount()
    {
      return d.size();
    }
    
    public double getDouble(int paramInt)
    {
      return 0.0D;
    }
    
    public Bundle getExtras()
    {
      return Bundle.EMPTY;
    }
    
    public float getFloat(int paramInt)
    {
      return 0.0F;
    }
    
    public int getInt(int paramInt)
    {
      return 0;
    }
    
    public long getLong(int paramInt)
    {
      return 0L;
    }
    
    public Uri getNotificationUri()
    {
      return a.getNotificationUri();
    }
    
    public int getPosition()
    {
      return c;
    }
    
    public short getShort(int paramInt)
    {
      return 0;
    }
    
    public String getString(int paramInt)
    {
      if (paramInt < b) {
        return a.getString(paramInt);
      }
      a locala = (a)d.get(c);
      Object localObject;
      switch (paramInt - b)
      {
      default: 
        return null;
      case 0: 
        Uri.Builder localBuilder = Uri.parse("content://mms-sms/search").buildUpon().appendQueryParameter("pattern", locala.a()).appendQueryParameter("source_id", String.valueOf(locala.c())).appendQueryParameter("thread_id", String.valueOf(locala.b()));
        localObject = localBuilder;
        if (!TextUtils.isEmpty(locala.d())) {
          localObject = localBuilder.appendQueryParameter("msg_type", locala.d());
        }
        return ((Uri.Builder)localObject).appendQueryParameter("group_msg_id", locala.f()).build().toString();
      case 1: 
        return "android.intent.action.SEARCH";
      case 2: 
        return locala.a();
      case 3: 
        localObject = gr.a(MmsApp.c(), locala.b(), false);
        if ((localObject != null) && (((gr)localObject).h() != null) && (((gr)localObject).h().size() > 0)) {
          return a((gr)localObject);
        }
        return locala.e();
      case 4: 
        return null;
      }
      return locala.a();
    }
    
    public int getType(int paramInt)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean getWantsAllOnMoveCalls()
    {
      return false;
    }
    
    public CursorWindow getWindow()
    {
      return null;
    }
    
    public boolean isAfterLast()
    {
      return c >= d.size();
    }
    
    public boolean isBeforeFirst()
    {
      return c < 0;
    }
    
    public boolean isClosed()
    {
      return a.isClosed();
    }
    
    public boolean isFirst()
    {
      return c == 0;
    }
    
    public boolean isLast()
    {
      return c == d.size() - 1;
    }
    
    public boolean isNull(int paramInt)
    {
      return false;
    }
    
    public boolean move(int paramInt)
    {
      return moveToPosition(c + paramInt);
    }
    
    public boolean moveToFirst()
    {
      return moveToPosition(0);
    }
    
    public boolean moveToLast()
    {
      return moveToPosition(d.size() - 1);
    }
    
    public boolean moveToNext()
    {
      return moveToPosition(c + 1);
    }
    
    public boolean moveToPosition(int paramInt)
    {
      if ((paramInt >= 0) && (paramInt < d.size()))
      {
        c = paramInt;
        a.moveToPosition(a.a((a)d.get(paramInt)));
        return true;
      }
      return false;
    }
    
    public boolean moveToPrevious()
    {
      return moveToPosition(c - 1);
    }
    
    public boolean onMove(int paramInt1, int paramInt2)
    {
      return ((CrossProcessCursor)a).onMove(paramInt1, paramInt2);
    }
    
    public void registerContentObserver(ContentObserver paramContentObserver)
    {
      a.registerContentObserver(paramContentObserver);
    }
    
    public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      a.registerDataSetObserver(paramDataSetObserver);
    }
    
    public boolean requery()
    {
      return false;
    }
    
    public Bundle respond(Bundle paramBundle)
    {
      return a.respond(paramBundle);
    }
    
    public void setNotificationUri(ContentResolver paramContentResolver, Uri paramUri)
    {
      a.setNotificationUri(paramContentResolver, paramUri);
    }
    
    public void unregisterContentObserver(ContentObserver paramContentObserver)
    {
      a.unregisterContentObserver(paramContentObserver);
    }
    
    public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      a.unregisterDataSetObserver(paramDataSetObserver);
    }
    
    class a
    {
      private String b;
      private long c;
      private long d;
      private String e;
      private String f;
      private int g;
      private String h;
      
      public a(int paramInt1, String paramString1, long paramLong1, long paramLong2, int paramInt2, String paramString2, String paramString3)
      {
        b = paramString1.trim();
        c = paramLong1;
        d = paramLong2;
        if (paramInt2 == 1) {
          e = "sms";
        }
        for (;;)
        {
          f = paramString2;
          g = paramInt1;
          h = paramString3;
          return;
          if (paramInt2 == 2) {
            e = "mms";
          }
        }
      }
      
      public String a()
      {
        return b;
      }
      
      public long b()
      {
        return c;
      }
      
      public long c()
      {
        return d;
      }
      
      public String d()
      {
        return e;
      }
      
      public String e()
      {
        return f;
      }
      
      public String f()
      {
        return h;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.SuggestionsProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */