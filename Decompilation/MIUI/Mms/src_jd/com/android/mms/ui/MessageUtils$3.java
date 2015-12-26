package com.android.mms.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.android.mms.MmsConfig;
import com.google.android.mms.pdu.PduPart;

final class MessageUtils$3
  implements Runnable
{
  MessageUtils$3(Context paramContext, Uri paramUri, Handler paramHandler, Runnable paramRunnable, MessageUtils.ResizeImageResultCallback paramResizeImageResultCallback, boolean paramBoolean) {}
  
  public void run()
  {
    try
    {
      final Object localObject1 = new UriImage(val$context, val$imageUri);
      int i = MmsConfig.getMaxImageWidth();
      int k = MmsConfig.getMaxImageHeight();
      int m = k;
      int j = i;
      if (((UriImage)localObject1).getHeight() > ((UriImage)localObject1).getWidth())
      {
        j = k;
        m = i;
      }
      localObject1 = ((UriImage)localObject1).getResizedImageAsPart(j, m, MmsConfig.getMaxMessageSize() - 5000);
      val$handler.removeCallbacks(val$showProgress);
      val$handler.post(new Runnable()
      {
        public void run()
        {
          val$cb.onResizeResult(localObject1, val$append);
        }
      });
      return;
    }
    finally
    {
      val$handler.removeCallbacks(val$showProgress);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */