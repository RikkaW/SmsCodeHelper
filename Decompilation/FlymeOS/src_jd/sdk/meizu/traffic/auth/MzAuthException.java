package sdk.meizu.traffic.auth;

import android.content.Intent;

public class MzAuthException
  extends Exception
{
  private Intent mLoginIntent;
  
  public MzAuthException(String paramString)
  {
    super(paramString);
  }
  
  public MzAuthException(String paramString, Intent paramIntent)
  {
    super(paramString);
    mLoginIntent = paramIntent;
  }
  
  public Intent getLoginIntent()
  {
    return mLoginIntent;
  }
  
  public boolean needLogin()
  {
    return mLoginIntent != null;
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.auth.MzAuthException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */