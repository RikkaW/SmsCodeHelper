package com.xiaomi.mms.utils;

import android.content.Context;
import android.os.AsyncTask;
import com.xiaomi.mms.net.exception.InvalidResponseException;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import org.json.JSONObject;

final class MxConfigCompat$1
  extends AsyncTask<Void, Void, JSONObject>
{
  MxConfigCompat$1(Context paramContext) {}
  
  protected JSONObject doInBackground(Void... paramVarArgs)
  {
    try
    {
      paramVarArgs = MxConfigCompat.access$000(val$context);
      return paramVarArgs;
    }
    catch (IOException paramVarArgs)
    {
      MyLog.e("MxConfigCompat", "get mx config info from network failed, beause " + paramVarArgs.toString());
      return null;
    }
    catch (InvalidResponseException paramVarArgs)
    {
      for (;;)
      {
        MyLog.e("MxConfigCompat", "get mx config info from network failed, beause " + paramVarArgs.toString());
      }
    }
  }
  
  protected void onPostExecute(JSONObject paramJSONObject)
  {
    super.onPostExecute(paramJSONObject);
    if (paramJSONObject != null)
    {
      MxConfigCompat.access$100(val$context, paramJSONObject);
      MxConfigCompat.access$200(val$context);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxConfigCompat.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */