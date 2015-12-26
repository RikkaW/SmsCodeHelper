import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.meizu.update.UpdateInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class akf
{
  public static final UpdateInfo a(Context paramContext)
  {
    return a(paramContext, paramContext.getPackageName());
  }
  
  public static final UpdateInfo a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = b(paramContext, paramString);
      return paramContext;
    }
    catch (ane paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private static String a(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new Pair("apps", paramString1));
    localArrayList.add(new Pair("sign", paramString2));
    return ank.b("http://u.meizu.com/appupgrade/check", localArrayList);
  }
  
  private static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new Pair("subservices", paramString1));
    localArrayList.add(new Pair("sign", paramString2));
    paramString1 = ank.a("http://u.meizu.com/subscription/registerWithSign", localArrayList);
    if (paramString1 != null)
    {
      if (new JSONObject(paramString1).getJSONObject("reply").getInt("code") == 200)
      {
        anf.a(paramContext, "register push success");
        return true;
      }
      anf.a(paramContext, "register push failed: " + paramString1);
    }
    for (;;)
    {
      return false;
      anf.a(paramContext, "register push response null");
    }
  }
  
  public static final UpdateInfo b(Context paramContext, String paramString)
  {
    try
    {
      Object localObject1 = anl.e(paramContext);
      String str1 = anl.d(paramContext);
      String str2 = anl.f(paramContext);
      String str3 = anl.c(paramContext);
      String str4 = anl.b(paramContext);
      Object localObject2 = anl.a(paramContext, paramString);
      paramContext = new JSONObject();
      paramContext.put("serviceName", paramString);
      paramContext.put("version", localObject2);
      localObject2 = new JSONArray();
      ((JSONArray)localObject2).put(paramContext);
      paramContext = new JSONObject();
      paramContext.put("deviceType", str1);
      paramContext.put("firmware", str3);
      paramContext.put("sysVer", str4);
      paramContext.put("imei", localObject1);
      paramContext.put("sn", str2);
      paramContext.put("services", localObject2);
      paramContext = paramContext.toString();
      localObject1 = new StringBuffer();
      ((StringBuffer)localObject1).append(paramContext).append("2635881a7ab0593849fe89e685fc56cd");
      paramContext = a(paramContext, anl.b(((StringBuffer)localObject1).toString()));
      if (!TextUtils.isEmpty(paramContext))
      {
        paramString = b(paramContext, paramString);
        if (paramString != null)
        {
          if ((mNeedUpdate) || (mExistsUpdate))
          {
            anf.b("new version : " + mVersionName);
            return paramString;
          }
          anf.b("no update");
          return paramString;
        }
      }
    }
    catch (ane paramContext)
    {
      throw paramContext;
      anf.c("check update parse failed.");
      throw new ane("Cant parse server response:" + paramContext);
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      throw new ane(paramContext.getMessage());
    }
    anf.c("check update response null.");
    throw new ane("Check update response null.");
  }
  
  private static UpdateInfo b(String paramString1, String paramString2)
  {
    paramString1 = new JSONObject(paramString1).getJSONObject("reply");
    int i = paramString1.getInt("code");
    if (i == 200)
    {
      anf.a("updateinfo: " + paramString1.toString());
      paramString1 = paramString1.getJSONArray("value");
      i = paramString1.length();
      if (i == 1)
      {
        paramString1 = paramString1.getJSONObject(0);
        String str = paramString1.getString("serviceName");
        if (paramString2.equals(str))
        {
          paramString2 = new UpdateInfo();
          mExistsUpdate = paramString1.getBoolean("existsUpdate");
          mNeedUpdate = paramString1.getBoolean("needUpdate");
          if ((mExistsUpdate) || (mNeedUpdate))
          {
            mUpdateUrl = paramString1.getString("updateUrl");
            mSize = paramString1.getString("fileSize");
            mVersionDate = paramString1.getString("releaseDate");
            mVersionDesc = paramString1.getString("releaseNote");
            mVersionName = paramString1.getString("latestVersion");
            if (paramString1.has("digest")) {
              mDigest = paramString1.getString("digest");
            }
            if (paramString1.has("verifyMode")) {
              mVerifyMode = paramString1.getInt("verifyMode");
            }
            if (paramString1.has("size")) {
              mSizeByte = paramString1.getLong("size");
            }
            if (paramString1.has("updateUrl2")) {
              mUpdateUrl2 = paramString1.getString("updateUrl2");
            }
            if ((anl.c()) && (!TextUtils.isEmpty(mVersionName)) && (mVersionName.endsWith("_i"))) {
              mVersionName = mVersionName.substring(0, mVersionName.length() - "_i".length());
            }
            if (paramString1.has("noteNetwork")) {
              mNoteNetWork = paramString1.getBoolean("noteNetwork");
            }
          }
          return paramString2;
        }
        anf.d("server return package : " + str);
      }
    }
    for (;;)
    {
      return null;
      anf.d("server return size : " + i);
      continue;
      anf.c("unknown server code : " + i);
    }
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      anf.a(paramContext, "start register push to server");
      Object localObject = anl.e(paramContext);
      String str1 = anl.d(paramContext);
      String str2 = anl.f(paramContext);
      String str3 = anl.c(paramContext);
      String str4 = anl.b(paramContext);
      String str5 = paramContext.getPackageName();
      String str6 = anl.a(paramContext);
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("serviceName", str5);
      localJSONObject.put("subStatus", 1);
      localJSONObject.put("version", str6);
      localJSONObject.put("serviceToken", paramString);
      paramString = new JSONArray();
      paramString.put(localJSONObject);
      localJSONObject = new JSONObject();
      localJSONObject.put("deviceType", str1);
      localJSONObject.put("firmware", str3);
      localJSONObject.put("sysVer", str4);
      localJSONObject.put("imei", localObject);
      localJSONObject.put("sn", str2);
      localJSONObject.put("services", paramString);
      paramString = localJSONObject.toString();
      localObject = new StringBuffer();
      ((StringBuffer)localObject).append(paramString).append("2635881a7ab0593849fe89e685fc56cd");
      boolean bool = a(paramContext, paramString, anl.b(((StringBuffer)localObject).toString()));
      return bool;
    }
    catch (Exception paramString)
    {
      anf.a(paramContext, "register push to server exception:" + paramString.getMessage());
      paramString.printStackTrace();
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     akf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */