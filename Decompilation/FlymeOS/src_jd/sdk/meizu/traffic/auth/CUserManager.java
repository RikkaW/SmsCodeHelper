package sdk.meizu.traffic.auth;

import android.content.Context;

public class CUserManager
{
  private static CUserManager sInstance;
  private MzAccountAuthHelper mAuthHelper;
  private String mAuthToken;
  
  private CUserManager(Context paramContext)
  {
    mAuthHelper = new MzAccountAuthHelper(paramContext.getApplicationContext(), null);
  }
  
  public static final CUserManager getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new CUserManager(paramContext);
    }
    return sInstance;
  }
  
  private boolean hasToken()
  {
    return mAuthToken != null;
  }
  
  public String getToken(boolean paramBoolean)
  {
    if (paramBoolean) {
      mAuthToken = null;
    }
    if (!hasToken()) {
      mAuthToken = mAuthHelper.getTokenSynchronize(true);
    }
    return mAuthToken;
  }
  
  public boolean hasLogined()
  {
    return hasToken();
  }
  
  public void logout()
  {
    mAuthToken = null;
  }
  
  public void resetToken()
  {
    mAuthToken = null;
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.auth.CUserManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */