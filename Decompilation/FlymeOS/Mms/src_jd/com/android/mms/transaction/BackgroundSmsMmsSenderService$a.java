package com.android.mms.transaction;

import android.content.Intent;
import android.os.AsyncTask;

class BackgroundSmsMmsSenderService$a
  extends AsyncTask<Intent, Void, Void>
{
  private BackgroundSmsMmsSenderService$a(BackgroundSmsMmsSenderService paramBackgroundSmsMmsSenderService) {}
  
  protected Void a(Intent... paramVarArgs)
  {
    BackgroundSmsMmsSenderService.a(a, paramVarArgs[0]);
    return null;
  }
  
  protected void a(Void paramVoid)
  {
    a.stopSelf();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.BackgroundSmsMmsSenderService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */