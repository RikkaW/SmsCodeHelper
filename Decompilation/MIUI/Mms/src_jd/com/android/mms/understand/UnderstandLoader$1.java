package com.android.mms.understand;

import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import java.util.Map;

class UnderstandLoader$1
  implements Handler.Callback
{
  UnderstandLoader$1(UnderstandLoader paramUnderstandLoader) {}
  
  public boolean handleMessage(Message paramMessage)
  {
    switch (what)
    {
    }
    do
    {
      return true;
    } while (!UnderstandLoader.access$000(this$0).containsKey(obj));
    ((UnderstandLoader.RequestCallback)UnderstandLoader.access$000(this$0).get(obj)).onRequestDone(true);
    Log.v("UnderstandLoader", "loading number resource done");
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.understand.UnderstandLoader.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */