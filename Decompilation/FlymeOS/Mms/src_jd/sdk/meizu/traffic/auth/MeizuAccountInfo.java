package sdk.meizu.traffic.auth;

import android.database.Cursor;

public class MeizuAccountInfo
{
  public static final String LOCAL_KEY_NICK_NAME = "nickName";
  public static final String LOCAL_KEY_PHONE = "phone";
  public static final String LOCAL_KEY_USER_ID = "userId";
  private String mNickName;
  private String mPhone;
  private String mUserId;
  
  public MeizuAccountInfo(Cursor paramCursor)
  {
    mUserId = paramCursor.getString(paramCursor.getColumnIndex("userId"));
    mNickName = paramCursor.getString(paramCursor.getColumnIndex("nickName"));
    mPhone = paramCursor.getString(paramCursor.getColumnIndex("phone"));
  }
  
  public String getNickName()
  {
    return mNickName;
  }
  
  public String getPhone()
  {
    return mPhone;
  }
  
  public String getUserId()
  {
    return mUserId;
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.auth.MeizuAccountInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */