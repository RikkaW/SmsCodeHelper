package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.util.StringUtils;

final class c
  implements Runnable
{
  c(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong) {}
  
  public final void run()
  {
    String str2 = MatchCacheManager.getMD5(a, b);
    String str3;
    String str4;
    if (!StringUtils.isNull(str2))
    {
      str3 = StringUtils.getPhoneNumberNo86(a);
      str4 = c;
      if (d != null) {
        break label130;
      }
    }
    label130:
    for (String str1 = "";; str1 = d)
    {
      MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, new String[] { "msg_num_md5", str2, "phonenum", str3, "msg_id", str4, "session_reuslt", str1, "save_time", String.valueOf(e), "session_lasttime", String.valueOf(System.currentTimeMillis()) }), 1);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */