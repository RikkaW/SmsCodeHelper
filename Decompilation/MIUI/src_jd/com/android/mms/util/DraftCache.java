package com.android.mms.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony.MmsSms;
import android.util.Log;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.provider.ExtraTelephony.Hms;

public class DraftCache
{
  static final String[] DRAFT_PROJECTION = { "thread_id" };
  private static DraftCache sInstance;
  private final HashSet<OnDraftChangedListener> mChangeListeners = new HashSet(1);
  private final Context mContext;
  private HashSet<Long> mDraftSet = new HashSet(4);
  private boolean mSavingDraft;
  
  private DraftCache(Context paramContext)
  {
    if (Log.isLoggable("Mms:app", 3)) {
      log("DraftCache.constructor", new Object[0]);
    }
    mContext = paramContext;
    refresh();
  }
  
  public static DraftCache getInstance()
  {
    return sInstance;
  }
  
  public static void init(Context paramContext)
  {
    sInstance = new DraftCache(paramContext.getApplicationContext());
  }
  
  private void log(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(paramString, paramVarArgs);
    Log.d("Mms/draft", "[DraftCache/" + Thread.currentThread().getId() + "] " + paramString);
  }
  
  private void rebuildCache()
  {
    try
    {
      if (Log.isLoggable("Mms:app", 3)) {
        log("rebuildCache", new Object[0]);
      }
      HashSet localHashSet1 = mDraftSet;
      ??? = new HashSet(localHashSet1.size());
      Cursor localCursor = mContext.getContentResolver().query(Telephony.MmsSms.CONTENT_DRAFT_URI, DRAFT_PROJECTION, null, null, null);
      if (localCursor != null)
      {
        try
        {
          if (localCursor.moveToFirst()) {
            while (!localCursor.isAfterLast())
            {
              long l = localCursor.getLong(0);
              ???.add(Long.valueOf(l));
              if (Log.isLoggable("Mms:app", 3)) {
                log("rebuildCache: add tid=" + l, new Object[0]);
              }
              localCursor.moveToNext();
              continue;
              localObject1 = finally;
            }
          }
        }
        finally
        {
          localCursor.close();
        }
        ((Cursor)localObject1).close();
      }
    }
    finally {}
    ??? = mContext.getContentResolver().query(ExtraTelephony.Hms.CONTENT_URI, DRAFT_PROJECTION, "type = 3", null, null);
    if (??? != null) {
      try
      {
        if (((Cursor)???).moveToFirst()) {
          while (!((Cursor)???).isAfterLast())
          {
            ???.add(Long.valueOf(((Cursor)???).getLong(0)));
            ((Cursor)???).moveToNext();
          }
        }
      }
      finally
      {
        ((Cursor)???).close();
      }
    }
    mDraftSet = ???;
    label464:
    for (;;)
    {
      OnDraftChangedListener localOnDraftChangedListener;
      synchronized (mChangeListeners)
      {
        if (mChangeListeners.size() < 1) {
          return;
        }
        ??? = new HashSet(???);
        ((Set)???).removeAll(localCollection);
        HashSet localHashSet2 = new HashSet(localCollection);
        localHashSet2.removeAll(???);
        synchronized (mChangeListeners)
        {
          Iterator localIterator1 = mChangeListeners.iterator();
          if (!localIterator1.hasNext()) {
            break label464;
          }
          localOnDraftChangedListener = (OnDraftChangedListener)localIterator1.next();
          localIterator2 = ((Set)???).iterator();
          if (localIterator2.hasNext()) {
            localOnDraftChangedListener.onDraftChanged(((Long)localIterator2.next()).longValue(), true);
          }
        }
      }
      Iterator localIterator2 = ((Set)localObject5).iterator();
      while (localIterator2.hasNext()) {
        localOnDraftChangedListener.onDraftChanged(((Long)localIterator2.next()).longValue(), false);
      }
    }
  }
  
  public void addOnDraftChangedListener(OnDraftChangedListener paramOnDraftChangedListener)
  {
    synchronized (mChangeListeners)
    {
      if (Log.isLoggable("Mms:app", 3)) {
        log("addOnDraftChangedListener " + paramOnDraftChangedListener, new Object[0]);
      }
      mChangeListeners.add(paramOnDraftChangedListener);
      return;
    }
  }
  
  public boolean getSavingDraft()
  {
    try
    {
      boolean bool = mSavingDraft;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean hasDraft(long paramLong)
  {
    try
    {
      boolean bool = mDraftSet.contains(Long.valueOf(paramLong));
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void refresh()
  {
    if (Log.isLoggable("Mms:app", 3)) {
      log("refresh", new Object[0]);
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        DraftCache.this.rebuildCache();
      }
    }).start();
  }
  
  public void removeOnDraftChangedListener(OnDraftChangedListener paramOnDraftChangedListener)
  {
    synchronized (mChangeListeners)
    {
      if (Log.isLoggable("Mms:app", 3)) {
        log("removeOnDraftChangedListener " + paramOnDraftChangedListener, new Object[0]);
      }
      mChangeListeners.remove(paramOnDraftChangedListener);
      return;
    }
  }
  
  public void setDraftState(long paramLong, boolean paramBoolean)
  {
    if (paramLong <= 0L) {}
    label167:
    for (;;)
    {
      return;
      if (paramBoolean) {}
      for (;;)
      {
        try
        {
          boolean bool = mDraftSet.add(Long.valueOf(paramLong));
          if (Log.isLoggable("Mms:app", 3)) {
            log("setDraftState: tid=" + paramLong + ", value=" + paramBoolean + ", changed=" + bool, new Object[0]);
          }
          if (!bool) {
            break;
          }
          synchronized (mChangeListeners)
          {
            Iterator localIterator = mChangeListeners.iterator();
            if (!localIterator.hasNext()) {
              break label167;
            }
            ((OnDraftChangedListener)localIterator.next()).onDraftChanged(paramLong, paramBoolean);
          }
          bool = mDraftSet.remove(Long.valueOf(paramLong));
        }
        finally {}
      }
    }
  }
  
  public void setSavingDraft(boolean paramBoolean)
  {
    try
    {
      mSavingDraft = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static abstract interface OnDraftChangedListener
  {
    public abstract void onDraftChanged(long paramLong, boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DraftCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */