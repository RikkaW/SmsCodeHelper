package com.android.mms.ui;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.OperationApplicationException;
import android.os.AsyncTask;
import android.os.RemoteException;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import miui.app.ProgressDialog;
import miui.provider.ExtraTelephony.PrivateAddresses;

class PrivatePreferenceActivity$3
  extends AsyncTask<Void, Void, ContentProviderResult[]>
{
  PrivatePreferenceActivity$3(PrivatePreferenceActivity paramPrivatePreferenceActivity, long paramLong, String paramString) {}
  
  protected ContentProviderResult[] doInBackground(Void... paramVarArgs)
  {
    paramVarArgs = Lists.newArrayList();
    paramVarArgs.add(ContentProviderOperation.newDelete(ContentUris.withAppendedId(ExtraTelephony.PrivateAddresses.CONTENT_URI, val$id)).build());
    try
    {
      paramVarArgs = this$0.getContentResolver().applyBatch("mms-sms", paramVarArgs);
      if (paramVarArgs != null) {
        PrivatePreferenceActivity.access$300(this$0, this$0, val$address, false);
      }
      return paramVarArgs;
    }
    catch (RemoteException paramVarArgs)
    {
      paramVarArgs.printStackTrace();
      return null;
    }
    catch (OperationApplicationException paramVarArgs)
    {
      for (;;)
      {
        paramVarArgs.printStackTrace();
      }
    }
  }
  
  protected void onPostExecute(ContentProviderResult[] paramArrayOfContentProviderResult)
  {
    PrivatePreferenceActivity.access$702(this$0, null);
    if (paramArrayOfContentProviderResult != null)
    {
      if (PrivatePreferenceActivity.access$800(this$0) != null)
      {
        PrivatePreferenceActivity.access$800(this$0).dismiss();
        PrivatePreferenceActivity.access$802(this$0, null);
      }
      PrivatePreferenceActivity.access$600(PrivatePreferenceActivity.access$000(this$0));
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */