package com.xiaomi.mms.transaction;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;

final class MxFeedbackService$2
  implements AccountManagerCallback<Bundle>
{
  MxFeedbackService$2(String paramString, Activity paramActivity) {}
  
  public void run(AccountManagerFuture<Bundle> paramAccountManagerFuture)
  {
    if (paramAccountManagerFuture != null) {}
    try
    {
      paramAccountManagerFuture = (Bundle)paramAccountManagerFuture.getResult();
      if ((paramAccountManagerFuture != null) && (!TextUtils.isEmpty(paramAccountManagerFuture.getString("authAccount"))))
      {
        paramAccountManagerFuture = new Intent("com.xiaomi.mms.mx.ACTION_START_MX_FEEDBACK");
        paramAccountManagerFuture.putExtra("extra_description", val$description);
        paramAccountManagerFuture.setPackage(val$activity.getPackageName());
        val$activity.startService(paramAccountManagerFuture);
      }
      return;
    }
    catch (OperationCanceledException paramAccountManagerFuture)
    {
      MyLog.e("MxFeedbackService", "startMxFeedback OperationCanceledException", paramAccountManagerFuture);
      return;
    }
    catch (AuthenticatorException paramAccountManagerFuture)
    {
      MyLog.e("MxFeedbackService", "startMxFeedback AuthenticatorException", paramAccountManagerFuture);
      return;
    }
    catch (IOException paramAccountManagerFuture)
    {
      MyLog.e("MxFeedbackService", "startMxFeedback IOException", paramAccountManagerFuture);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxFeedbackService.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */