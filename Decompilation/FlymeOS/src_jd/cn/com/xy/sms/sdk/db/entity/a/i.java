package cn.com.xy.sms.sdk.db.entity.a;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.g;
import cn.com.xy.sms.sdk.log.LogManager;

final class i
  implements Runnable
{
  i(String paramString1, String paramString2) {}
  
  public final void run()
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("lastloadtime", Long.valueOf(System.currentTimeMillis()));
      DBManager.update("tb_public_num_info", localContentValues, " num = ? ", new String[] { a });
      LogManager.e("pubInfo", "updateNumLoadTime: " + a + " areaCode: " + b);
      g.b(a, b);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */