package sdk.meizu.traffic.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MzAccountAuthHelper
{
  public static final String ACCOUNT_TYPE = "com.meizu.account";
  public static final String BASIC_SCOPE = "basic";
  private static final String TAG = "MzAccountAuthHelper";
  private AuthListener mAuthListener;
  private boolean mCanceled;
  private Context mContext;
  private AccountManagerFuture<Bundle> mRequestFuture;
  
  public MzAccountAuthHelper(Context paramContext, AuthListener paramAuthListener)
  {
    mContext = paramContext;
    mAuthListener = paramAuthListener;
  }
  
  private void onError(int paramInt)
  {
    if ((!mCanceled) && (mAuthListener != null)) {
      mAuthListener.onError(paramInt);
    }
  }
  
  private void onLoginRequest(Intent paramIntent)
  {
    if ((!mCanceled) && (mAuthListener != null)) {
      mAuthListener.onLoginRequst(paramIntent);
    }
  }
  
  private void onSuccess(String paramString)
  {
    if ((!mCanceled) && (mAuthListener != null)) {
      mAuthListener.onSuccess(paramString);
    }
  }
  
  public void cancel()
  {
    mCanceled = true;
    if (mRequestFuture != null) {
      mRequestFuture.cancel(true);
    }
  }
  
  public void getToken(boolean paramBoolean)
  {
    AccountManager localAccountManager = AccountManager.get(mContext);
    Object localObject2 = MzAccountManager.getBaseAccount(mContext);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new Account("unknown", "com.meizu.account");
    }
    localObject2 = new Bundle();
    if (paramBoolean) {
      ((Bundle)localObject2).putBoolean("invalidateToken", true);
    }
    mCanceled = false;
    mRequestFuture = localAccountManager.getAuthToken((Account)localObject1, "basic", (Bundle)localObject2, null, new MzAccountAuthHelper.1(this), null);
  }
  
  public String getTokenSynchronize(boolean paramBoolean)
  {
    Object localObject2 = MzAccountManager.getBaseAccount(mContext);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new Account("unknown", "com.meizu.account");
    }
    localObject2 = new Bundle();
    if (paramBoolean) {
      ((Bundle)localObject2).putBoolean("invalidateToken", true);
    }
    localObject1 = AccountManager.get(mContext).getAuthToken((Account)localObject1, "basic", (Bundle)localObject2, null, null, null);
    try
    {
      localObject1 = (Bundle)((AccountManagerFuture)localObject1).getResult(30L, TimeUnit.SECONDS);
      if (((Bundle)localObject1).containsKey("authtoken")) {
        return ((Bundle)localObject1).getString("authtoken");
      }
      if (((Bundle)localObject1).containsKey("intent")) {
        throw new MzAuthException("Relogin Needed!", (Intent)((Bundle)localObject1).getParcelable("intent"));
      }
    }
    catch (OperationCanceledException localOperationCanceledException)
    {
      localOperationCanceledException.printStackTrace();
      return null;
      if (localOperationCanceledException.containsKey("errorMessage")) {
        throw new MzAuthException(localOperationCanceledException.getString("errorMessage"));
      }
    }
    catch (AuthenticatorException localAuthenticatorException)
    {
      localAuthenticatorException.printStackTrace();
      return null;
      throw new MzAuthException("Auth Error");
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }
  
  public boolean handleAuthResult(int paramInt, Intent paramIntent)
  {
    if (mCanceled)
    {
      Log.d("MzAccountAuthHelper", "op canceled.");
      return true;
    }
    if (paramInt == -1)
    {
      getToken(false);
      return true;
    }
    if (paramInt == 0)
    {
      onError(4);
      return true;
    }
    onError(1);
    return true;
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.auth.MzAccountAuthHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */