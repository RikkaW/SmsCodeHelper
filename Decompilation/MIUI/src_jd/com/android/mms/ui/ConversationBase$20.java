package com.android.mms.ui;

import android.os.RemoteException;
import android.text.TextUtils;
import com.android.fileexplorer.service.IQueryCallBack.Stub;

class ConversationBase$20
  extends IQueryCallBack.Stub
{
  ConversationBase$20(ConversationBase paramConversationBase) {}
  
  public void onQueryFinish()
    throws RemoteException
  {}
  
  public boolean onQueryItem(String paramString, int paramInt)
    throws RemoteException
  {
    return false;
  }
  
  public void onQueryItemEnd(String paramString1, String paramString2)
    throws RemoteException
  {
    if (TextUtils.isEmpty(paramString2)) {
      return;
    }
    ConversationBase.access$2202(paramString2);
  }
  
  public void onStartQuery(int paramInt)
    throws RemoteException
  {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.20
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */