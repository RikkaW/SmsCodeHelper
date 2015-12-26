package sdk.meizu.traffic.auth;

import android.content.Intent;

public abstract interface AuthListener
{
  public abstract void onError(int paramInt);
  
  public abstract void onLoginRequst(Intent paramIntent);
  
  public abstract void onSuccess(String paramString);
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.auth.AuthListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */