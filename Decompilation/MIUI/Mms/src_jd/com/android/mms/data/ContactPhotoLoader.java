package com.android.mms.data;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.ContactsContract.Data;
import android.provider.MiuiSettings.System;
import android.widget.ImageView;
import com.google.android.collect.Lists;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import miui.graphics.BitmapFactory;
import miui.yellowpage.ContactThumbnailProcessor;
import miui.yellowpage.YellowPageImgLoader;

public class ContactPhotoLoader
  implements Handler.Callback
{
  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  private final String[] CONTACT_COLUMNS = { "_id", "data15" };
  private final ConcurrentHashMap<Long, BitmapHolder> mContactBitmapCache = new ConcurrentHashMap();
  private final ConcurrentHashMap<String, BitmapHolder> mContactNameBitmapCache = new ConcurrentHashMap();
  private final int mContactPhotoWidth;
  private final Context mContext;
  private final int mDefaultContactResourceId;
  private final int mDefaultSPResourceId;
  private LoaderThread mLoaderThread;
  private boolean mLoadingRequested;
  private final Handler mMainThreadHandler = new Handler(this);
  private boolean mPaused;
  private final ConcurrentHashMap<ImageView, Contact> mPendingRequests = new ConcurrentHashMap();
  private ContactThumbnailProcessor mRoundImageProcessor;
  
  public ContactPhotoLoader(Context paramContext, int paramInt1, int paramInt2)
  {
    mDefaultContactResourceId = paramInt1;
    mDefaultSPResourceId = paramInt2;
    mRoundImageProcessor = new ContactThumbnailProcessor(paramContext);
    mContext = paramContext;
    mContactPhotoWidth = paramContext.getResources().getDimensionPixelSize(2131427447);
  }
  
  private void cacheBitmap(long paramLong, byte[] paramArrayOfByte)
  {
    if (!mPaused) {
      mContactBitmapCache.put(Long.valueOf(paramLong), createBitmapHolderFromBytes(paramArrayOfByte));
    }
  }
  
  private void cacheNameBitmap(String paramString)
  {
    if (!mPaused) {
      mContactNameBitmapCache.put(paramString, createNameBitmapHolderFromName(paramString));
    }
  }
  
  private void clearContactBitmapCache(boolean paramBoolean)
  {
    if ((!paramBoolean) && (mContactBitmapCache.size() > 0)) {
      mContactBitmapCache.clear();
    }
    if (mContactNameBitmapCache.size() > 0) {
      mContactNameBitmapCache.clear();
    }
  }
  
  private BitmapHolder createBitmapHolderFromBytes(byte[] paramArrayOfByte)
  {
    BitmapHolder localBitmapHolder = new BitmapHolder(null);
    state = 2;
    if (paramArrayOfByte != null) {}
    try
    {
      paramArrayOfByte = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, null);
      bitmapRef = new SoftReference(BitmapFactory.createPhoto(mContext, paramArrayOfByte, mContactPhotoWidth));
      return localBitmapHolder;
    }
    catch (OutOfMemoryError paramArrayOfByte) {}
    return localBitmapHolder;
  }
  
  private BitmapHolder createNameBitmapHolderFromName(String paramString)
  {
    BitmapHolder localBitmapHolder = new BitmapHolder(null);
    state = 2;
    if (paramString != null) {
      bitmapRef = new SoftReference(BitmapFactory.createNameBitmap(mContext, paramString, mContactPhotoWidth));
    }
    return localBitmapHolder;
  }
  
  private boolean loadCachedNamePhoto(ImageView paramImageView, Contact paramContact)
  {
    Object localObject = paramContact.getName();
    if (localObject == null)
    {
      paramImageView.setImageResource(mDefaultContactResourceId);
      return true;
    }
    paramContact = (BitmapHolder)mContactNameBitmapCache.get(localObject);
    if (paramContact == null)
    {
      paramContact = new BitmapHolder(null);
      mContactNameBitmapCache.put(localObject, paramContact);
    }
    for (;;)
    {
      paramImageView.setImageResource(mDefaultContactResourceId);
      state = 0;
      return false;
      if (bitmapRef == null)
      {
        paramImageView.setImageResource(mDefaultContactResourceId);
        if (state == 2) {
          break;
        }
        return false;
      }
      localObject = (Bitmap)bitmapRef.get();
      if (localObject != null)
      {
        paramImageView.setImageBitmap((Bitmap)localObject);
        if (state == 2) {
          break;
        }
        return false;
      }
      bitmapRef = null;
    }
  }
  
  private boolean loadCachedPhoto(ImageView paramImageView, Contact paramContact)
  {
    boolean bool = true;
    BitmapHolder localBitmapHolder = (BitmapHolder)mContactBitmapCache.get(Long.valueOf(paramContact.getPhotoId()));
    if (localBitmapHolder == null)
    {
      localBitmapHolder = new BitmapHolder(null);
      mContactBitmapCache.put(Long.valueOf(paramContact.getPhotoId()), localBitmapHolder);
    }
    for (paramContact = localBitmapHolder;; paramContact = localBitmapHolder)
    {
      paramImageView.setImageResource(mDefaultContactResourceId);
      state = 0;
      bool = false;
      do
      {
        do
        {
          return bool;
          if (bitmapRef != null) {
            break;
          }
          paramImageView.setImageResource(mDefaultContactResourceId);
        } while (state == 2);
        return false;
        paramContact = (Bitmap)bitmapRef.get();
        if (paramContact == null) {
          break;
        }
        paramImageView.setImageBitmap(paramContact);
      } while (state == 2);
      return false;
      bitmapRef = null;
    }
  }
  
  private void obtainPhotoIdsAndNamesToLoad(ArrayList<Long> paramArrayList, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2)
  {
    paramArrayList.clear();
    paramArrayList1.clear();
    paramArrayList2.clear();
    Iterator localIterator = mPendingRequests.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (Contact)localIterator.next();
      Object localObject1 = Long.valueOf(((Contact)localObject2).getPhotoId());
      if (((Long)localObject1).longValue() == 0L)
      {
        localObject1 = ((Contact)localObject2).getName();
        if (localObject1 != null)
        {
          localObject2 = (BitmapHolder)mContactNameBitmapCache.get(localObject1);
          if ((localObject2 != null) && (state == 0))
          {
            state = 1;
            paramArrayList2.add(localObject1);
          }
        }
      }
      else
      {
        localObject2 = (BitmapHolder)mContactBitmapCache.get(localObject1);
        if ((localObject2 != null) && (state == 0))
        {
          state = 1;
          paramArrayList.add(localObject1);
          paramArrayList1.add(((Long)localObject1).toString());
        }
      }
    }
  }
  
  private void processLoadedImages()
  {
    Iterator localIterator = mPendingRequests.keySet().iterator();
    label85:
    while (localIterator.hasNext())
    {
      ImageView localImageView = (ImageView)localIterator.next();
      Contact localContact = (Contact)mPendingRequests.get(localImageView);
      if (localContact.getPhotoId() == 0L) {}
      for (boolean bool = loadCachedNamePhoto(localImageView, localContact);; bool = loadCachedPhoto(localImageView, localContact))
      {
        if (!bool) {
          break label85;
        }
        localIterator.remove();
        break;
      }
    }
    if (!mPendingRequests.isEmpty()) {
      requestLoading();
    }
  }
  
  private void requestLoading()
  {
    if (!mLoadingRequested)
    {
      mLoadingRequested = true;
      mMainThreadHandler.sendEmptyMessage(1);
    }
  }
  
  public void cancelRequest(ImageView paramImageView)
  {
    YellowPageImgLoader.cancelLoading(mContext, paramImageView);
    mPendingRequests.remove(paramImageView);
  }
  
  public void clear()
  {
    mPendingRequests.clear();
    Iterator localIterator = mContactBitmapCache.values().iterator();
    while (localIterator.hasNext()) {
      nextstate = 0;
    }
    localIterator = mContactNameBitmapCache.values().iterator();
    while (localIterator.hasNext()) {
      nextstate = 0;
    }
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    boolean bool = true;
    switch (what)
    {
    default: 
      bool = false;
    }
    do
    {
      do
      {
        return bool;
        mLoadingRequested = false;
      } while (mPaused);
      if (mLoaderThread == null)
      {
        mLoaderThread = new LoaderThread(mContext.getContentResolver());
        mLoaderThread.setPriority(1);
        mLoaderThread.start();
      }
      mLoaderThread.requestLoading();
      return true;
    } while (mPaused);
    processLoadedImages();
    return true;
  }
  
  public void loadPhoto(ImageView paramImageView, Contact paramContact)
  {
    if ((!paramContact.isB2cNumber()) && (!paramContact.isYellowPageNumber()) && (paramContact.getPhotoId() == 0L)) {
      if (MiuiSettings.System.useWordPhoto(mContext))
      {
        YellowPageImgLoader.cancelLoading(mContext, paramImageView);
        if (loadCachedNamePhoto(paramImageView, paramContact)) {
          mPendingRequests.remove(paramImageView);
        }
      }
    }
    do
    {
      do
      {
        return;
        mPendingRequests.put(paramImageView, paramContact);
      } while (mPaused);
      requestLoading();
      return;
      paramImageView.setImageResource(mDefaultContactResourceId);
      mPendingRequests.remove(paramImageView);
      return;
      if (paramContact.isB2cNumber())
      {
        YellowPageImgLoader.cancelLoading(mContext, paramImageView);
        mPendingRequests.remove(paramImageView);
        YellowPageImgLoader.loadThumbnailByName(mContext, paramImageView, mRoundImageProcessor, paramContact.getYellowPageThumbnailName(), mDefaultSPResourceId);
        return;
      }
      if (paramContact.isYellowPageNumber())
      {
        YellowPageImgLoader.cancelLoading(mContext, paramImageView);
        mPendingRequests.remove(paramImageView);
        YellowPageImgLoader.loadThumbnail(mContext, paramImageView, mRoundImageProcessor, paramContact.getNumber(), mDefaultSPResourceId);
        return;
      }
      YellowPageImgLoader.cancelLoading(mContext, paramImageView);
      if (loadCachedPhoto(paramImageView, paramContact))
      {
        mPendingRequests.remove(paramImageView);
        return;
      }
      mPendingRequests.put(paramImageView, paramContact);
    } while (mPaused);
    requestLoading();
  }
  
  public void pause()
  {
    mPaused = true;
  }
  
  public void requestClear(boolean paramBoolean)
  {
    if (mLoaderThread != null) {
      mLoaderThread.requestClear(paramBoolean);
    }
    while (mLoadingRequested) {
      return;
    }
    clearContactBitmapCache(paramBoolean);
  }
  
  public void resume()
  {
    mPaused = false;
    if (!mPendingRequests.isEmpty()) {
      requestLoading();
    }
  }
  
  public void stop()
  {
    pause();
    if (mLoaderThread != null)
    {
      mLoaderThread.quit();
      mLoaderThread = null;
    }
    clear();
  }
  
  private static class BitmapHolder
  {
    SoftReference<Bitmap> bitmapRef;
    int state;
  }
  
  private class LoaderThread
    extends HandlerThread
    implements Handler.Callback
  {
    private Handler mLoaderThreadHandler;
    private final ArrayList<String> mNameList = Lists.newArrayList();
    private final ArrayList<Long> mPhotoIds = Lists.newArrayList();
    private final ArrayList<String> mPhotoIdsAsStrings = Lists.newArrayList();
    private final ContentResolver mResolver;
    private final StringBuilder mStringBuilder = new StringBuilder();
    
    public LoaderThread(ContentResolver paramContentResolver)
    {
      super();
      mResolver = paramContentResolver;
    }
    
    private void loadContactNamesPhoto()
    {
      int j = mNameList.size();
      if (j == 0) {}
      for (;;)
      {
        return;
        int i = 0;
        while (i < j)
        {
          ContactPhotoLoader.this.cacheNameBitmap((String)mNameList.get(i));
          i += 1;
        }
      }
    }
    
    private void loadContactPhotosFromDatabase()
    {
      int j = mPhotoIds.size();
      if (j == 0) {}
      for (;;)
      {
        return;
        mStringBuilder.setLength(0);
        mStringBuilder.append("_id IN(");
        int i = 0;
        while (i < j)
        {
          if (i != 0) {
            mStringBuilder.append(',');
          }
          mStringBuilder.append('?');
          i += 1;
        }
        mStringBuilder.append(')');
        Object localObject1 = null;
        try
        {
          Cursor localCursor = mResolver.query(ContactsContract.Data.CONTENT_URI, CONTACT_COLUMNS, mStringBuilder.toString(), (String[])mPhotoIdsAsStrings.toArray(ContactPhotoLoader.EMPTY_STRING_ARRAY), null);
          if (localCursor != null) {
            for (;;)
            {
              localObject1 = localCursor;
              if (!localCursor.moveToNext()) {
                break;
              }
              localObject1 = localCursor;
              Long localLong = Long.valueOf(localCursor.getLong(0));
              localObject1 = localCursor;
              byte[] arrayOfByte = localCursor.getBlob(1);
              localObject1 = localCursor;
              ContactPhotoLoader.this.cacheBitmap(localLong.longValue(), arrayOfByte);
              localObject1 = localCursor;
              mPhotoIds.remove(localLong);
            }
          }
          if (localObject2 == null) {
            break label228;
          }
        }
        finally
        {
          if (localObject1 != null) {
            ((Cursor)localObject1).close();
          }
        }
        ((Cursor)localObject2).close();
        label228:
        j = mPhotoIds.size();
        i = 0;
        while (i < j)
        {
          ContactPhotoLoader.this.cacheBitmap(((Long)mPhotoIds.get(i)).longValue(), null);
          i += 1;
        }
      }
    }
    
    public boolean handleMessage(Message paramMessage)
    {
      switch (what)
      {
      }
      for (;;)
      {
        return true;
        ContactPhotoLoader.this.obtainPhotoIdsAndNamesToLoad(mPhotoIds, mPhotoIdsAsStrings, mNameList);
        loadContactPhotosFromDatabase();
        loadContactNamesPhoto();
        mMainThreadHandler.sendEmptyMessage(2);
        continue;
        boolean bool = ((Boolean)obj).booleanValue();
        ContactPhotoLoader.this.clearContactBitmapCache(bool);
      }
    }
    
    public void requestClear(boolean paramBoolean)
    {
      if (mLoaderThreadHandler == null) {
        mLoaderThreadHandler = new Handler(getLooper(), this);
      }
      mLoaderThreadHandler.removeMessages(1);
      Message.obtain(mLoaderThreadHandler, 1, Boolean.valueOf(paramBoolean)).sendToTarget();
    }
    
    public void requestLoading()
    {
      if (mLoaderThreadHandler == null) {
        mLoaderThreadHandler = new Handler(getLooper(), this);
      }
      mLoaderThreadHandler.removeMessages(0);
      mLoaderThreadHandler.sendEmptyMessage(0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.ContactPhotoLoader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */