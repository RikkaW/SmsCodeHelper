package com.xiaomi.mms.net;

import android.text.TextUtils;
import com.xiaomi.mms.net.exception.InvalidResponseException;
import com.xiaomi.mms.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudRequestExecutor
{
  private static final Integer INT_0 = Integer.valueOf(0);
  
  private static void checkResponse(byte[] paramArrayOfByte)
    throws IOException
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      throw new IOException("failed to get response from server");
    }
  }
  
  public static JSONObject getIDCConfig()
    throws IOException, InvalidResponseException
  {
    SimpleRequest localSimpleRequest = CloudRequestFactory.newGetIDCConfigRequest();
    Object localObject3 = null;
    try
    {
      localSimpleRequest.connect();
      Object localObject1 = localSimpleRequest.getResponseBody();
      checkResponse((byte[])localObject1);
      localObject1 = new String((byte[])localObject1, "UTF-8");
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        throw new InvalidResponseException("mx config idc information is null");
      }
    }
    finally
    {
      localSimpleRequest.close();
    }
    try
    {
      JSONObject localJSONObject = new JSONObject(str1);
      Object localObject2 = localObject3;
      if (localJSONObject != null)
      {
        String str2 = localJSONObject.optString("result");
        localObject2 = localObject3;
        if (!TextUtils.isEmpty(str2))
        {
          localObject2 = localObject3;
          if (str2.equalsIgnoreCase("ok")) {
            localObject2 = localJSONObject.getJSONObject("data");
          }
        }
      }
      localSimpleRequest.close();
      return (JSONObject)localObject2;
    }
    catch (JSONException localJSONException)
    {
      throw new InvalidResponseException("parse config information arise json exception");
    }
  }
  
  public static String getUid(SimpleRequest paramSimpleRequest)
    throws IOException, InvalidResponseException
  {
    try
    {
      paramSimpleRequest.connect();
      Object localObject1 = paramSimpleRequest.getResponseBody();
      checkResponse((byte[])localObject1);
      localObject1 = ObjectUtils.jsonToMap((byte[])localObject1, "UTF-8");
      if (localObject1 == null) {
        throw new InvalidResponseException("failed to convert result to map");
      }
    }
    finally
    {
      paramSimpleRequest.close();
    }
    Object localObject4 = ((Map)localObject2).get("code");
    if (INT_0.equals(localObject4))
    {
      Object localObject3 = ((Map)localObject2).get("data");
      if ((localObject3 instanceof Map))
      {
        localObject3 = ((Map)localObject3).get("userId");
        long l = -1L;
        if ((localObject3 instanceof Integer))
        {
          l = ((Integer)localObject3).intValue();
          if (l <= 0L) {
            break label153;
          }
        }
        label153:
        for (localObject3 = String.valueOf(l);; localObject3 = null)
        {
          paramSimpleRequest.close();
          return (String)localObject3;
          if (!(localObject3 instanceof Long)) {
            break;
          }
          l = ((Long)localObject3).longValue();
          break;
        }
      }
    }
    paramSimpleRequest.close();
    throw new InvalidResponseException("failed to parse response");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.net.CloudRequestExecutor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */