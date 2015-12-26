package com.android.mms.understand;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import miui.telephony.PhoneNumberUtils.PhoneNumber;

public class UnderstandLoader
{
  private static UnderstandLoader sInstance;
  private Handler.Callback mCallback = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      switch (what)
      {
      }
      do
      {
        return true;
      } while (!mPendingMap.containsKey(obj));
      ((UnderstandLoader.RequestCallback)mPendingMap.get(obj)).onRequestDone(true);
      Log.v("UnderstandLoader", "loading number resource done");
      return true;
    }
  };
  private Handler mHandler = new Handler(Looper.getMainLooper(), mCallback);
  private LoaderThread mLoaderThread;
  private TemplateUpdate.UpdatePatch mPatcher;
  private final Map<Object, RequestCallback> mPendingMap = new HashMap();
  
  public static void destroy()
  {
    getInstance().requestDestroy();
  }
  
  public static void destroy(String paramString, RequestCallback paramRequestCallback)
  {
    getInstance().requestDestroy(paramString, paramRequestCallback);
  }
  
  public static UnderstandLoader getInstance()
  {
    if (sInstance == null) {
      sInstance = new UnderstandLoader();
    }
    return sInstance;
  }
  
  private LoaderThread getLoaderThread()
  {
    if (mLoaderThread == null)
    {
      mLoaderThread = new LoaderThread();
      mLoaderThread.setPriority(1);
      mLoaderThread.start();
    }
    return mLoaderThread;
  }
  
  public static void init()
  {
    getInstance().requestInit();
  }
  
  public static void prepare()
  {
    getInstance().requestInitFiles();
  }
  
  public static void rePrepare(TemplateUpdate.UpdatePatch paramUpdatePatch)
  {
    getInstance().requestUpdateFiles(paramUpdatePatch);
  }
  
  public static void request(String paramString, RequestCallback paramRequestCallback)
  {
    getInstance().requestLoading(paramString, paramRequestCallback);
  }
  
  public static void update()
  {
    getInstance().requestUpdate();
  }
  
  public void requestDestroy()
  {
    getLoaderThread().request(6);
  }
  
  public void requestDestroy(String paramString, RequestCallback paramRequestCallback)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (paramRequestCallback == null) {
      return;
    }
    if (mPendingMap.containsValue(paramRequestCallback))
    {
      mPendingMap.remove(paramString);
      getLoaderThread().requestDestroy(paramString);
      return;
    }
    Log.w("UnderstandLoader", " callback was already replaced, ignore. ");
  }
  
  public void requestInit()
  {
    getLoaderThread().request(4);
  }
  
  public void requestInitFiles()
  {
    getLoaderThread().request(1);
  }
  
  public void requestLoading(String paramString, RequestCallback paramRequestCallback)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      mPendingMap.put(paramString, paramRequestCallback);
      getLoaderThread().requestLoad(paramString);
    }
  }
  
  public void requestUpdate()
  {
    getLoaderThread().request(5);
  }
  
  public void requestUpdateFiles(TemplateUpdate.UpdatePatch paramUpdatePatch)
  {
    mPatcher = paramUpdatePatch;
    getLoaderThread().request(2);
  }
  
  private class LoaderThread
    extends HandlerThread
    implements Handler.Callback
  {
    private Handler mLoaderThreadHandler;
    
    public LoaderThread()
    {
      super();
    }
    
    public boolean handleMessage(Message paramMessage)
    {
      Log.v("UnderstandLoader", " handleMessage: " + what);
      switch (what)
      {
      default: 
        Log.e("UnderstandLoader", "unknown action for load thread with what is " + what);
      }
      Object localObject;
      do
      {
        do
        {
          do
          {
            return true;
            UnderstandFactory.initUnderstandFiles();
            return true;
          } while (!UnderstandFactory.unzipFiles());
          UnderstandFactory.setInitialized(false);
          UnderstandFactory.updateVersion(mPatcher.mVersion);
          mLoaderThreadHandler.sendEmptyMessage(5);
          return true;
          mLoaderThreadHandler.removeMessages(5);
          UnderstandFactory.reStartInitUnderstand();
          return true;
          UnderstandFactory.initUnderstand();
          mLoaderThreadHandler.sendEmptyMessage(7);
          return true;
          paramMessage = (String)obj;
          UnderstandFactory.loadResourceForResident(paramMessage, "");
          localObject = Message.obtain();
          what = 21;
          obj = paramMessage;
          mHandler.sendMessage((Message)localObject);
          return true;
          UnderstandFactory.freeAllResourcesForResident();
          mHandler.removeCallbacksAndMessages(null);
          return true;
          UnderstandFactory.freeResourceForResident((String)obj, null);
          return true;
          paramMessage = MessageUtils.getPhoneNumbers(MmsApp.getApp());
          if (paramMessage.size() > 0)
          {
            UnderstandFactory.setLocalHostNumber((String)paramMessage.get(0), 0);
            localObject = PhoneNumberUtils.PhoneNumber.parse((CharSequence)paramMessage.get(0));
            String str = ((PhoneNumberUtils.PhoneNumber)localObject).getLocation(MmsApp.getApp());
            ((PhoneNumberUtils.PhoneNumber)localObject).recycle();
            if (!TextUtils.isEmpty(str)) {
              UnderstandFactory.setLocalHostPlace(str, 0);
            }
          }
        } while (paramMessage.size() <= 1);
        UnderstandFactory.setLocalHostNumber((String)paramMessage.get(1), 1);
        paramMessage = PhoneNumberUtils.PhoneNumber.parse((CharSequence)paramMessage.get(1));
        localObject = paramMessage.getLocation(MmsApp.getApp());
        paramMessage.recycle();
      } while (TextUtils.isEmpty((CharSequence)localObject));
      UnderstandFactory.setLocalHostPlace((String)localObject, 1);
      return true;
    }
    
    public void request(int paramInt)
    {
      if (mLoaderThreadHandler == null) {
        mLoaderThreadHandler = new Handler(getLooper(), this);
      }
      mLoaderThreadHandler.sendEmptyMessage(paramInt);
    }
    
    public void requestDestroy(String paramString)
    {
      if (mLoaderThreadHandler == null) {
        mLoaderThreadHandler = new Handler(getLooper(), this);
      }
      Message localMessage = Message.obtain();
      what = 8;
      obj = paramString;
      mLoaderThreadHandler.sendMessage(localMessage);
    }
    
    public void requestLoad(String paramString)
    {
      if (mLoaderThreadHandler == null) {
        mLoaderThreadHandler = new Handler(getLooper(), this);
      }
      Message localMessage = Message.obtain();
      what = 3;
      obj = paramString;
      mLoaderThreadHandler.sendMessage(localMessage);
    }
  }
  
  public static abstract interface RequestCallback
  {
    public abstract void onRequestDone(boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.understand.UnderstandLoader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */