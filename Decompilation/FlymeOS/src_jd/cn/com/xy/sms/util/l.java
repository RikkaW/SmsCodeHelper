package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONObject;

final class l
  implements Runnable
{
  l(String paramString1, String paramString2, JSONObject paramJSONObject, String paramString3, String paramString4) {}
  
  public final void run()
  {
    String str = MatchCacheManager.getMD5(a, b);
    if (StringUtils.isNull(str)) {
      return;
    }
    MatchCacheManager.removeUselessKey(c);
    MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, new String[] { "msg_num_md5", str, "phonenum", StringUtils.getPhoneNumberNo86(a), "scene_id", d, "msg_id", e, "bubble_result", c.toString(), "save_time", System.currentTimeMillis(), "bubble_lasttime", System.currentTimeMillis() }), 2);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */