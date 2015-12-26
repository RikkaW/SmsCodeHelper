package com.android.mms.update;

import android.util.Log;
import com.android.mms.MmsApp;
import org.json.JSONObject;

final class TemplateRequest$1
  implements Runnable
{
  public void run()
  {
    TemplateRequest localTemplateRequest = new TemplateRequest();
    try
    {
      Object localObject2 = localTemplateRequest.requestUpdate();
      Log.v("TemplateRequest", "result is " + (String)localObject2);
      localObject2 = new JSONObject((String)localObject2);
      Log.d("TemplateRequest", "pulled data in json is: " + localObject2);
      TemplateRequest.access$000(localTemplateRequest, MmsApp.getApp(), (JSONObject)localObject2);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    finally
    {
      TemplateRequest.access$100(false);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.TemplateRequest.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */