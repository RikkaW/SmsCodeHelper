package sdk.meizu.traffic.auth;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;

class MzAccountAuthHelper$1
  implements AccountManagerCallback<Bundle>
{
  MzAccountAuthHelper$1(MzAccountAuthHelper paramMzAccountAuthHelper) {}
  
  public void run(AccountManagerFuture<Bundle> paramAccountManagerFuture)
  {
    Log.d("MzAccountAuthHelper", "receive account callback");
    if (MzAccountAuthHelper.access$000(this$0))
    {
      Log.d("MzAccountAuthHelper", "op canceled.");
      return;
    }
    try
    {
      paramAccountManagerFuture = (Bundle)paramAccountManagerFuture.getResult();
      if (paramAccountManagerFuture != null)
      {
        if (paramAccountManagerFuture.containsKey("authtoken"))
        {
          paramAccountManagerFuture = paramAccountManagerFuture.getString("authtoken");
          MzAccountAuthHelper.access$100(this$0, paramAccountManagerFuture);
          return;
        }
        if (paramAccountManagerFuture.containsKey("intent"))
        {
          paramAccountManagerFuture = (Intent)paramAccountManagerFuture.getParcelable("intent");
          MzAccountAuthHelper.access$200(this$0, paramAccountManagerFuture);
          return;
        }
      }
    }
    catch (AuthenticatorException paramAccountManagerFuture)
    {
      MzAccountAuthHelper.access$300(this$0, 1);
      return;
      if (paramAccountManagerFuture.containsKey("errorCode"))
      {
        MzAccountAuthHelper.access$300(this$0, paramAccountManagerFuture.getInt("errorCode"));
        return;
      }
    }
    catch (IOException paramAccountManagerFuture)
    {
      MzAccountAuthHelper.access$300(this$0, 1);
      return;
      MzAccountAuthHelper.access$300(this$0, 1);
      return;
      MzAccountAuthHelper.access$300(this$0, 1);
      return;
    }
    catch (OperationCanceledException paramAccountManagerFuture) {}
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.auth.MzAccountAuthHelper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */