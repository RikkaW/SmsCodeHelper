package com.xiaomi.mms.utils;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import com.xiaomi.mms.utils.sec.AESUtil;
import com.xiaomi.mms.utils.sec.Base64Utils;
import com.xiaomi.mms.utils.sec.RSAUtils;
import com.xiaomi.mms.utils.sec.ReportConstants.ReturnCode;
import com.xiaomi.mms.utils.sec.ReportConstants.ReturnRSA;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class SmsReportUtil
{
  private static final String TAG = SmsReportUtil.class.getSimpleName();
  private static final SmsReportUtil mInstance = new SmsReportUtil();
  
  private static String getInfoFromServer(HttpClient paramHttpClient, String paramString)
    throws Exception
  {
    Object localObject = paramHttpClient.execute(new HttpGet(paramString));
    if (((HttpResponse)localObject).getStatusLine().getStatusCode() == 200)
    {
      paramHttpClient = null;
      try
      {
        paramString = ((HttpResponse)localObject).getEntity().getContent();
        paramHttpClient = paramString;
        localObject = inStream2String(paramString);
        return (String)localObject;
      }
      finally
      {
        if (paramHttpClient != null) {
          paramHttpClient.close();
        }
      }
    }
    MyLog.w(TAG, "Failed to get info from server. uri=" + paramString.toString());
    throw new Exception("Failed to get info from server.");
  }
  
  private RSAPublicKey getPublicKey(HttpClient paramHttpClient)
    throws Exception
  {
    return (RSAPublicKey)RSAUtils.loadPublicKey(parsePublicKeyResponse(getInfoFromServer(paramHttpClient, "http://mixin.xiaomi.net/report/pubkey")));
  }
  
  private static String inStream2String(InputStream paramInputStream)
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    return new String(localByteArrayOutputStream.toByteArray());
  }
  
  public static SmsReportUtil instance()
  {
    return mInstance;
  }
  
  private JSONObject makeMsgJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws Exception
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("userId", paramString1);
    localJSONObject.put("phoneNum", paramString2);
    localJSONObject.put("resId", paramString3);
    localJSONObject.put("reportedPhoneNum", paramString4);
    localJSONObject.put("smsInfo", paramString5);
    return localJSONObject;
  }
  
  private String makePostMsg(RSAPublicKey paramRSAPublicKey, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws Exception
  {
    String str = AESUtil.getAESKeyPlaintext();
    return Base64Utils.encrypt(makePostMsgJson(paramRSAPublicKey, str, Base64Utils.encrypt(AESUtil.encrypt(makeMsgJson(paramString1, paramString2, paramString3, paramString4, paramString5).toString(), str))).toString());
  }
  
  private JSONObject makePostMsgJson(RSAPublicKey paramRSAPublicKey, String paramString1, String paramString2)
    throws Exception
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("msgkey", Base64Utils.encrypt(RSAUtils.encryptByPublicKey(paramString1, paramRSAPublicKey)));
    localJSONObject.put("msgtxt", paramString2);
    return localJSONObject;
  }
  
  private boolean parsePostSmsResponse(String paramString)
    throws Exception
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    String str = localJSONObject.getString("code");
    if (ReportConstants.ReturnCode.OK.equals(str))
    {
      paramString = localJSONObject.getJSONObject("info");
      if (!"0".equals(paramString.getString("status")))
      {
        MyLog.w(TAG, "Failed to post sms with status not Ok. info=" + paramString.toString());
        throw new Exception("Failed to post sms with status not Ok.");
      }
      return true;
    }
    MyLog.w(TAG, "Failed to post sms. res=" + paramString.toString());
    throw new Exception("Failed to post sms.");
  }
  
  private String parsePublicKeyResponse(String paramString)
    throws Exception
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    String str = localJSONObject.getString("code");
    if (ReportConstants.ReturnCode.OK.equals(str)) {
      return localJSONObject.getJSONObject("info").getString(ReportConstants.ReturnRSA.JSON_KEY_PUBLIC_KEY);
    }
    MyLog.w(TAG, "Failed to get public key. res=" + paramString);
    throw new Exception("Failed to get public key.");
  }
  
  private static String postInfoToServer(HttpClient paramHttpClient, String paramString1, String paramString2)
    throws Exception
  {
    HttpPost localHttpPost = new HttpPost(paramString1);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("param", paramString2));
    localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList));
    localHttpPost.setHeader("Accept", "application/json");
    localHttpPost.setHeader("Conteny-type", "application/x-www.form-urlencoded");
    paramString2 = paramHttpClient.execute(localHttpPost);
    if (paramString2.getStatusLine().getStatusCode() == 200)
    {
      paramHttpClient = null;
      try
      {
        paramString1 = paramString2.getEntity().getContent();
        paramHttpClient = paramString1;
        paramString2 = inStream2String(paramString1);
        return paramString2;
      }
      finally
      {
        if (paramHttpClient != null) {
          paramHttpClient.close();
        }
      }
    }
    MyLog.w(TAG, "Failed to post info to server. uri=" + paramString1.toString());
    throw new Exception("Failed to post info to server.");
  }
  
  private boolean postSms(HttpClient paramHttpClient, String paramString)
    throws Exception
  {
    return parsePostSmsResponse(postInfoToServer(paramHttpClient, "http://mixin.xiaomi.net/report/postsms", paramString));
  }
  
  public boolean postSms(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws Exception
  {
    if ((TextUtils.isEmpty(paramString4)) || (TextUtils.isEmpty(paramString5))) {
      throw new Exception("Reported phone num or sms info is invalid.");
    }
    if ((TextUtils.isEmpty(paramString2)) && (TextUtils.isEmpty(paramString1))) {
      throw new Exception("Should set informer's phone num or mxid.");
    }
    paramContext = new DefaultHttpClient();
    MyLog.v(TAG, "Gets public key.");
    RSAPublicKey localRSAPublicKey = getPublicKey(paramContext);
    MyLog.v(TAG, "Makes post msg.");
    paramString1 = makePostMsg(localRSAPublicKey, paramString1, paramString2, paramString3, paramString4, paramString5);
    MyLog.v(TAG, "Does posting msg.");
    boolean bool = postSms(paramContext, paramString1);
    paramString1 = TAG;
    paramString2 = new StringBuilder().append("Posts sms ");
    if (bool) {}
    for (paramContext = "successful";; paramContext = "failed")
    {
      MyLog.i(paramString1, paramContext);
      return bool;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.SmsReportUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */