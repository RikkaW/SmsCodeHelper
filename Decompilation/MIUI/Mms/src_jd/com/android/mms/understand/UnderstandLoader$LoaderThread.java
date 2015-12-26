package com.android.mms.understand;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageUtils;
import java.util.List;
import miui.telephony.PhoneNumberUtils.PhoneNumber;

class UnderstandLoader$LoaderThread
  extends HandlerThread
  implements Handler.Callback
{
  private Handler mLoaderThreadHandler;
  
  public UnderstandLoader$LoaderThread(UnderstandLoader paramUnderstandLoader)
  {
    super("UnderstandLoader");
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
        UnderstandFactory.updateVersion(access$100this$0).mVersion);
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
        UnderstandLoader.access$200(this$0).sendMessage((Message)localObject);
        return true;
        UnderstandFactory.freeAllResourcesForResident();
        UnderstandLoader.access$200(this$0).removeCallbacksAndMessages(null);
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

/* Location:
 * Qualified Name:     com.android.mms.understand.UnderstandLoader.LoaderThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */