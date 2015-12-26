package cn.com.xy.sms.sdk.db.entity;

public class MatchCache
{
  public String bubble_result;
  public String card_result;
  public String extend;
  public long id;
  public String msg_id;
  public String msg_num_md5;
  public String phonenum;
  public String popup_window_result;
  public long save_time;
  public String scene_id;
  public String session_reuslt;
  
  public MatchCache() {}
  
  public MatchCache(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
  {
    msg_num_md5 = paramString1;
    phonenum = paramString2;
    msg_id = paramString3;
    scene_id = paramString4;
    popup_window_result = paramString5;
    bubble_result = paramString6;
    session_reuslt = paramString7;
    card_result = paramString8;
    extend = paramString9;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.MatchCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */