package com.android.mms.ui;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.android.mms.model.SlideshowModel;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.PduBody;

class ConversationBase$PduBodyCache
{
  private static PduBody mLastPduBody;
  private static Uri mLastUri;
  
  public static PduBody getPduBody(Context paramContext, Uri paramUri)
  {
    if (paramUri.equals(mLastUri)) {
      return mLastPduBody;
    }
    try
    {
      mLastPduBody = SlideshowModel.getPduBody(paramContext, paramUri);
      mLastUri = paramUri;
      return mLastPduBody;
    }
    catch (MmsException paramContext)
    {
      Log.e("ConversationBase", paramContext.getMessage(), paramContext);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.PduBodyCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */