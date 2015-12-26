package cn.com.xy.sms.sdk.net.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.a;
import cn.com.xy.sms.sdk.db.entity.a.b;
import cn.com.xy.sms.sdk.db.entity.a.c;
import cn.com.xy.sms.sdk.db.entity.d;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.x;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class i
{
  public static int a = 0;
  public static int b = 1;
  private static String c = "0";
  private static String d = "1";
  private static String e = "-1";
  private static int f = -1;
  private static String g = null;
  
  public static String a()
  {
    if (StringUtils.isNull(g))
    {
      IccidInfo localIccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
      if ((localIccidInfo != null) && (!StringUtils.isNull(iccid))) {
        g = k.a(iccid);
      }
    }
    if (StringUtils.isNull(g)) {
      return "";
    }
    return g;
  }
  
  private static String a(IccidInfo paramIccidInfo, int paramInt)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<queryIccidInfoRequest>");
      localStringBuffer.append("<iccid>");
      localStringBuffer.append(iccid);
      localStringBuffer.append("</iccid>");
      localStringBuffer.append("<cmd>" + paramInt + "</cmd>");
      localStringBuffer.append("<operator>" + operator + "</operator>");
      localStringBuffer.append("<provinces>" + provinces + "</provinces>");
      localStringBuffer.append("<city>" + city + "</city>");
      localStringBuffer.append("<updateTime>" + updateTime + "</updateTime>");
      localStringBuffer.append("</queryIccidInfoRequest>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramIccidInfo)
    {
      for (;;)
      {
        paramIccidInfo.printStackTrace();
      }
    }
  }
  
  public static String a(String paramString)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<QueryToken>");
      localStringBuffer.append("<sdkVersion>");
      localStringBuffer.append(DexUtil.getSceneVersion());
      localStringBuffer.append("</sdkVersion>");
      localStringBuffer.append("<iccid>" + paramString + "</iccid>");
      localStringBuffer.append("</QueryToken>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static String a(String paramString, int paramInt)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<checkResourseRequest>");
      localStringBuffer.append("<sdk_version>");
      localStringBuffer.append(DexUtil.getSceneVersion());
      localStringBuffer.append("</sdk_version>");
      localStringBuffer.append("<res_type>");
      localStringBuffer.append(paramInt);
      localStringBuffer.append("</res_type>");
      localStringBuffer.append("<res_version>");
      localStringBuffer.append(paramString);
      localStringBuffer.append("</res_version>");
      localStringBuffer.append("</checkResourseRequest>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
  }
  
  public static String a(String paramString, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<UpdatePublicInfoRequest>");
      localStringBuffer.append("<PublicInfoVersion>");
      localStringBuffer.append(paramString);
      localStringBuffer.append("</PublicInfoVersion>");
      localStringBuffer.append("<status>");
      localStringBuffer.append(paramInt1);
      localStringBuffer.append("</status>");
      localStringBuffer.append("<count>");
      localStringBuffer.append(paramInt2);
      localStringBuffer.append("</count>");
      localStringBuffer.append("</UpdatePublicInfoRequest>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<QueryLocationRequest>");
      localStringBuffer.append("<cNum>");
      localStringBuffer.append(paramString1);
      localStringBuffer.append("</cNum>");
      localStringBuffer.append("<iccid>" + paramString2 + "</iccid>");
      localStringBuffer.append("<num>" + paramString3 + "</num>");
      localStringBuffer.append("</QueryLocationRequest>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public static String a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<queryIccidSceneRequest>");
      localStringBuffer.append("<iccid>");
      localStringBuffer.append(paramString1);
      localStringBuffer.append("</iccid>");
      localStringBuffer.append("<cmd>" + paramString2 + "</cmd>");
      localStringBuffer.append("<imei>" + paramString3 + "</imei>");
      localStringBuffer.append("<sceneId>" + paramString4 + "</sceneId>");
      localStringBuffer.append("</queryIccidSceneRequest>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public static String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    Object localObject1 = "";
    Object localObject3 = c.b(StringUtils.getPhoneNumberNo86(paramString1));
    Object localObject2 = localObject1;
    if (localObject3 != null)
    {
      if (!StringUtils.isNull(d)) {
        localObject1 = d;
      }
      localObject2 = localObject1;
      if (StringUtils.isNull(c)) {}
    }
    for (localObject2 = c;; localObject2 = "")
    {
      localObject3 = b();
      for (;;)
      {
        try
        {
          ((StringBuffer)localObject3).append("<QueryPubInfoRequest>");
          ((StringBuffer)localObject3).append("<num>");
          ((StringBuffer)localObject3).append(paramString1);
          ((StringBuffer)localObject3).append("</num>");
          ((StringBuffer)localObject3).append("<cnum>");
          if (paramString2 != null) {
            continue;
          }
          ((StringBuffer)localObject3).append((String)localObject1);
          ((StringBuffer)localObject3).append("</cnum>");
          ((StringBuffer)localObject3).append("<areaCode>" + paramString3 + "</areaCode>");
          ((StringBuffer)localObject3).append("<iccid>" + paramString4 + "</iccid>");
          ((StringBuffer)localObject3).append("<type>" + paramString5 + "</type>");
          ((StringBuffer)localObject3).append("<sign>");
          ((StringBuffer)localObject3).append(g((String)localObject2));
          ((StringBuffer)localObject3).append("</sign>");
          ((StringBuffer)localObject3).append("</QueryPubInfoRequest>");
        }
        catch (Throwable paramString1)
        {
          paramString1.printStackTrace();
          continue;
        }
        return ((StringBuffer)localObject3).toString();
        localObject1 = paramString2;
      }
      localObject1 = localObject2;
    }
  }
  
  public static String a(List<SceneRule> paramList)
  {
    int j = 0;
    int k = 0;
    StringBuffer localStringBuffer = b();
    for (;;)
    {
      int m;
      try
      {
        localStringBuffer.append("<QuerySceneRuleRequest>");
        localStringBuffer.append("<SceneRuleList>");
        i = k;
        if (paramList != null)
        {
          i = k;
          if (!paramList.isEmpty())
          {
            m = paramList.size();
            k = 0;
            i = j;
            j = k;
            break label234;
          }
        }
        localStringBuffer.append("</SceneRuleList>");
        localStringBuffer.append("<clientVersion>");
        localStringBuffer.append(DexUtil.getSceneVersion());
        localStringBuffer.append("</clientVersion>");
        localStringBuffer.append("</QuerySceneRuleRequest>");
        if (i != 0) {
          break label225;
        }
        return "";
      }
      catch (Throwable paramList)
      {
        int i;
        SceneRule localSceneRule;
        paramList.printStackTrace();
      }
      localSceneRule = (SceneRule)paramList.get(j);
      if (!StringUtils.isNull(sceneruleVersion))
      {
        localStringBuffer.append("<SceneRule>");
        localStringBuffer.append("<id>");
        localStringBuffer.append(id);
        localStringBuffer.append("</id>");
        localStringBuffer.append("<version>");
        localStringBuffer.append(sceneruleVersion);
        localStringBuffer.append("</version>");
        localStringBuffer.append("</SceneRule>");
        i = 1;
        j += 1;
        break label234;
        label225:
        return localStringBuffer.toString();
      }
      else
      {
        continue;
      }
      label234:
      if (j < m) {}
    }
  }
  
  public static String a(List<String> paramList, String paramString1, String paramString2, String paramString3)
  {
    if (paramList == null) {
      return "";
    }
    StringBuffer localStringBuffer = b();
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      try
      {
        localStringBuffer.append("<QueryPubInfoRequest>");
        localStringBuffer.append("<areaCode>" + paramString1 + "</areaCode>");
        localStringBuffer.append("<iccid>" + paramString2 + "</iccid>");
        localStringBuffer.append("<type>" + paramString3 + "</type>");
        localStringBuffer.append("<allNums>");
        if (localIterator.hasNext()) {
          continue;
        }
        localStringBuffer.append("</allNums>");
        localStringBuffer.append("</QueryPubInfoRequest>");
      }
      catch (Throwable paramList)
      {
        paramList.printStackTrace();
        continue;
        paramList = g(c.a(paramList, true));
        continue;
      }
      return localStringBuffer.toString();
      paramString1 = new JSONObject((String)localIterator.next());
      paramString2 = new StringBuilder("<num ver =\"").append(paramString1.optString("version")).append("\" sign =\"");
      paramList = paramString1.optString("num");
      if (!StringUtils.isNull(paramList)) {
        continue;
      }
      paramList = "";
      localStringBuffer.append(paramList + "\">");
      localStringBuffer.append(paramString1.optString("num"));
      localStringBuffer.append("</num>");
    }
  }
  
  public static String a(List<d> paramList, Map<String, String> paramMap, String paramString, boolean paramBoolean)
  {
    StringBuffer localStringBuffer1 = b();
    StringBuffer localStringBuffer2 = new StringBuffer();
    StringBuffer localStringBuffer3 = new StringBuffer();
    int i = 0;
    int j = SysParamEntityManager.getIntParam(Constant.getContext(), "ONLINE_UPDATE_SDK_PERIOD");
    if (j <= 0) {
      j = 1;
    }
    for (;;)
    {
      int k = 0;
      for (;;)
      {
        try
        {
          if (k >= paramList.size())
          {
            if (i != 0) {
              continue;
            }
            return null;
          }
          d locald = (d)paramList.get(k);
          if ((paramBoolean) && (System.currentTimeMillis() < e + DexUtil.getUpdateCycleByType(8, 86400000L * j))) {
            continue;
          }
          i += 1;
          if (k != 0)
          {
            localStringBuffer2.append(",");
            localStringBuffer3.append(",");
          }
          localStringBuffer2.append(b);
          localObject2 = b;
          localObject1 = localObject2;
          if (((String)localObject2).startsWith("PU")) {
            localObject1 = ((String)localObject2).replace("PU", "");
          }
          localObject1 = (String)paramMap.get(localObject1);
          if (!StringUtils.isNull((String)localObject1)) {
            localStringBuffer2.append((String)localObject1);
          }
          localStringBuffer3.append(c);
          localObject1 = b;
        }
        catch (Throwable paramList)
        {
          Object localObject2;
          Object localObject1;
          paramList.printStackTrace();
          continue;
          continue;
        }
        try
        {
          localObject2 = new ContentValues();
          ((ContentValues)localObject2).put("update_time", System.currentTimeMillis());
          DBManager.update("tb_jar_list", (ContentValues)localObject2, "name = ? ", new String[] { localObject1 });
          k += 1;
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
        }
      }
      localStringBuffer1.append("<UpdateRecognitionJarRequest>");
      localStringBuffer1.append("<reqVersion>");
      localStringBuffer1.append(DexUtil.getSuanfaVersion());
      localStringBuffer1.append("</reqVersion>");
      localStringBuffer1.append("<jarVersion>");
      localStringBuffer1.append(localStringBuffer3.toString());
      localStringBuffer1.append("</jarVersion>");
      localStringBuffer1.append("<jarname>");
      localStringBuffer1.append(localStringBuffer2.toString());
      localStringBuffer1.append("</jarname>");
      localStringBuffer1.append("<emVer>");
      localStringBuffer1.append(paramString);
      localStringBuffer1.append("</emVer>");
      localStringBuffer1.append("</UpdateRecognitionJarRequest>");
      return localStringBuffer1.toString();
    }
  }
  
  private static Map<String, JSONObject> a(Document paramDocument)
  {
    HashMap localHashMap = new HashMap();
    NodeList localNodeList = paramDocument.getElementsByTagName("info");
    paramDocument = paramDocument.getDocumentElement();
    int i = 0;
    for (;;)
    {
      if (i >= localNodeList.getLength()) {
        return localHashMap;
      }
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("id", x.a(paramDocument, "rstCode"));
        localObject = (Element)localNodeList.item(i);
        String str1 = ((Element)localObject).getAttribute("pubId");
        localObject = ((Element)localObject).getChildNodes();
        j = 0;
        if (j >= ((NodeList)localObject).getLength())
        {
          localHashMap.put(str1, localJSONObject);
          i += 1;
        }
      }
      catch (JSONException localJSONException1)
      {
        for (;;)
        {
          Object localObject;
          int j;
          localJSONException1.printStackTrace();
          continue;
          Node localNode = ((NodeList)localObject).item(j);
          if (localNode.getNodeType() == 1)
          {
            String str2 = localNode.getNodeName();
            try
            {
              if ("pubId".equalsIgnoreCase(str2)) {
                localJSONObject.put("pubId", x.a(localNode));
              } else if ("pubName".equalsIgnoreCase(str2)) {
                localJSONObject.put("pubName", x.a(localNode));
              }
            }
            catch (JSONException localJSONException2)
            {
              localJSONException2.printStackTrace();
            }
            if ("pubType".equalsIgnoreCase(str2)) {
              localJSONObject.put("pubType", x.a(localJSONException2));
            } else if ("pubTypeCode".equalsIgnoreCase(str2)) {
              localJSONObject.put("classifyCode", x.a(localJSONException2));
            } else if ("weiXin".equalsIgnoreCase(str2)) {
              localJSONObject.put("weiXin", x.a(localJSONException2));
            } else if ("weiBoName".equalsIgnoreCase(str2)) {
              localJSONObject.put("weiBoName", x.a(localJSONException2));
            } else if ("weiBoUrl".equalsIgnoreCase(str2)) {
              localJSONObject.put("weiBoUrl", x.a(localJSONException2));
            } else if ("introduce".equalsIgnoreCase(str2)) {
              localJSONObject.put("introduce", x.a(localJSONException2));
            } else if ("address".equalsIgnoreCase(str2)) {
              localJSONObject.put("address", x.a(localJSONException2));
            } else if ("faxNum".equalsIgnoreCase(str2)) {
              localJSONObject.put("faxNum", x.a(localJSONException2));
            } else if ("website".equalsIgnoreCase(str2)) {
              localJSONObject.put("webSite", x.a(localJSONException2));
            } else if ("versionCode".equalsIgnoreCase(str2)) {
              localJSONObject.put("versionCode", x.a(localJSONException2));
            } else if ("email".equalsIgnoreCase(str2)) {
              localJSONObject.put("email", x.a(localJSONException2));
            } else if ("parentPubId".equalsIgnoreCase(str2)) {
              localJSONObject.put("parentPubId", x.a(localJSONException2));
            } else if ("slogan".equalsIgnoreCase(str2)) {
              localJSONObject.put("slogan", x.a(localJSONException2));
            } else if ("rectLogoName".equalsIgnoreCase(str2)) {
              localJSONObject.put("rectLogoName", x.a(localJSONException2));
            } else if ("circleLogoName".equalsIgnoreCase(str2)) {
              localJSONObject.put("circleLogoName", x.a(localJSONException2));
            } else if ("extend".equalsIgnoreCase(str2)) {
              localJSONObject.put("extend", x.a(localJSONException2));
            } else if ("moveWebsite".equalsIgnoreCase(str2)) {
              localJSONObject.put("moveWebsite", x.a(localJSONException2));
            }
          }
          j += 1;
        }
      }
    }
  }
  
  private static void a(Map<String, JSONObject> paramMap, Document paramDocument)
  {
    NodeList localNodeList1 = paramDocument.getElementsByTagName("menuList");
    int i = 0;
    JSONArray localJSONArray;
    HashMap localHashMap;
    String str1;
    NodeList localNodeList2;
    int j;
    for (;;)
    {
      if (i >= localNodeList1.getLength()) {
        return;
      }
      localJSONArray = new JSONArray();
      localHashMap = new HashMap();
      paramDocument = (Element)localNodeList1.item(i);
      str1 = paramDocument.getAttribute("pubId");
      localNodeList2 = paramDocument.getElementsByTagName("menu");
      j = 0;
      if (j < localNodeList2.getLength()) {
        break;
      }
      localHashMap.clear();
      paramDocument = (JSONObject)paramMap.get(str1);
      if (paramDocument != null) {
        paramDocument.put("pubMenuInfolist", localJSONArray);
      }
      i += 1;
    }
    paramDocument = ((Element)localNodeList2.item(j)).getChildNodes();
    Object localObject4 = new JSONObject();
    int k = 0;
    for (;;)
    {
      Object localObject3;
      if (k >= paramDocument.getLength())
      {
        ((JSONObject)localObject4).put("pubId", str1);
        localObject1 = ((JSONObject)localObject4).optString("menuType");
        localObject3 = ((JSONObject)localObject4).optString("actionData");
        paramDocument = (Document)localObject3;
        if (!"menu".equalsIgnoreCase((String)localObject1))
        {
          paramDocument = (Document)localObject3;
          if (StringUtils.isNull((String)localObject3))
          {
            new StringBuilder("actionType=").append((String)localObject1).append(" pubMenuInfo=").append(localObject4);
            if (!StringUtils.isNull((String)localObject1)) {
              break label766;
            }
            paramDocument = "";
          }
        }
        ((JSONObject)localObject4).put("actionData", paramDocument);
      }
      try
      {
        str2 = ((JSONObject)localObject4).optString("menuCode");
        localObject3 = ((JSONObject)localObject4).optString("menuName");
        str3 = ((JSONObject)localObject4).optString("extend");
        str4 = ((JSONObject)localObject4).optString("pubId");
        if (str2.length() != 2) {
          break label3736;
        }
        if ("menu".equalsIgnoreCase((String)localObject1)) {
          break label3599;
        }
        paramDocument = JsonUtil.getJsonObject((JSONObject)localObject4, new String[] { "menuCode", str2, "pubId", str4, "extend", str3, "name", localObject3, "type", localObject1, "action_data", paramDocument });
        if (paramDocument != null) {
          localJSONArray.put(paramDocument);
        }
      }
      catch (Throwable paramDocument)
      {
        String str2;
        String str3;
        String str4;
        label425:
        Object localObject2;
        for (;;) {}
      }
      j += 1;
      break;
      Object localObject1 = paramDocument.item(k);
      if (((Node)localObject1).getNodeType() == 1)
      {
        localObject3 = ((Node)localObject1).getNodeName();
        try
        {
          if ("menuCode".equalsIgnoreCase((String)localObject3)) {
            ((JSONObject)localObject4).put("menuCode", x.a((Node)localObject1));
          } else if ("menuName".equalsIgnoreCase((String)localObject3)) {
            ((JSONObject)localObject4).put("menuName", x.a((Node)localObject1));
          }
        }
        catch (Throwable localThrowable)
        {
          new StringBuilder("generateMenuList error: ").append(localThrowable.getMessage());
        }
        if ("menuDesc".equalsIgnoreCase((String)localObject3))
        {
          ((JSONObject)localObject4).put("menuDesc", x.a(localThrowable));
        }
        else if ("menuType".equalsIgnoreCase((String)localObject3))
        {
          ((JSONObject)localObject4).put("menuType", x.a(localThrowable));
        }
        else if ("sendTo".equalsIgnoreCase((String)localObject3))
        {
          ((JSONObject)localObject4).put("sendTo", x.a(localThrowable));
        }
        else if ("sp".equalsIgnoreCase((String)localObject3))
        {
          ((JSONObject)localObject4).put("sp", x.a(localThrowable));
        }
        else if ("sms".equalsIgnoreCase((String)localObject3))
        {
          ((JSONObject)localObject4).put("sms", x.a(localThrowable));
        }
        else if ("url".equalsIgnoreCase((String)localObject3))
        {
          ((JSONObject)localObject4).put("url", x.a(localThrowable));
        }
        else if ("phoneNum".equalsIgnoreCase((String)localObject3))
        {
          ((JSONObject)localObject4).put("phoneNum", x.a(localThrowable));
        }
        else if ("extend".equalsIgnoreCase((String)localObject3))
        {
          ((JSONObject)localObject4).put("extend", x.a(localThrowable));
          break label3929;
          label766:
          localObject3 = new StringBuffer();
          if (!localThrowable.startsWith("WEB_")) {}
          for (paramDocument = localThrowable.toLowerCase();; paramDocument = (Document)localObject2)
          {
            if ("reply_sms".equalsIgnoreCase(paramDocument))
            {
              ((StringBuffer)localObject3).append("{");
              ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
              ((StringBuffer)localObject3).append("\"send_code\":\"" + ((JSONObject)localObject4).optString("sms") + "\",");
              ((StringBuffer)localObject3).append("\"phone\":\"" + ((JSONObject)localObject4).optString("sendTo") + "\",");
              ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
              ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
              ((StringBuffer)localObject3).append("}");
            }
            for (;;)
            {
              paramDocument = StringUtils.encode(((StringBuffer)localObject3).toString());
              break;
              if ("send_sms".equalsIgnoreCase(paramDocument))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"send_code\":\"" + ((JSONObject)localObject4).optString("sms") + "\",");
                ((StringBuffer)localObject3).append("\"phone\":\"" + ((JSONObject)localObject4).optString("sendTo") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if (("access_url".equalsIgnoreCase(paramDocument)) || ("open_url".equalsIgnoreCase(paramDocument)))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"url\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if ("down_url".equalsIgnoreCase(paramDocument))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"url\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if ("download".equalsIgnoreCase(paramDocument))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"url\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"appName\":\"" + ((JSONObject)localObject4).optString("appName") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"extend\":\"" + ((JSONObject)localObject4).optString("extend") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if ("weibo_url".equalsIgnoreCase(paramDocument))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"url\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if (("call_phone".equalsIgnoreCase(paramDocument)) || ("call".equalsIgnoreCase(paramDocument)))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"phoneNum\":\"" + ((JSONObject)localObject4).optString("phoneNum") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if (("map_site".equalsIgnoreCase(paramDocument)) || ("open_map".equalsIgnoreCase(paramDocument)))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"address\":\"" + ((JSONObject)localObject4).optString("extend") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if ("open_map_list".equalsIgnoreCase(paramDocument))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"address\":\"" + ((JSONObject)localObject4).optString("extend") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if (("repayment".equalsIgnoreCase(paramDocument)) || ("zfb_repayment".equalsIgnoreCase(paramDocument)))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"appName\":\"" + ((JSONObject)localObject4).optString("extend") + "\",");
                ((StringBuffer)localObject3).append("\"appDownUrl\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if (("recharge".equalsIgnoreCase(paramDocument)) || ("zfb_recharge".equalsIgnoreCase(paramDocument)))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"sp\":\"" + ((JSONObject)localObject4).optString("sp") + "\",");
                ((StringBuffer)localObject3).append("\"appName\":\"" + ((JSONObject)localObject4).optString("extend") + "\",");
                ((StringBuffer)localObject3).append("\"appDownUrl\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if ("open_app".equalsIgnoreCase(paramDocument))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"appName\":\"" + ((JSONObject)localObject4).optString("extend") + "\",");
                ((StringBuffer)localObject3).append("\"appDownUrl\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if ("open_app_url".equalsIgnoreCase(paramDocument))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"appName\":\"" + ((JSONObject)localObject4).optString("extend") + "\",");
                ((StringBuffer)localObject3).append("\"appDownUrl\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
              else if ("WEB_TRAFFIC_ORDER".equalsIgnoreCase(paramDocument))
              {
                ((StringBuffer)localObject3).append("{");
                ((StringBuffer)localObject3).append("\"type\":\"" + paramDocument + "\",");
                ((StringBuffer)localObject3).append("\"sp\":\"" + ((JSONObject)localObject4).optString("sp") + "\",");
                ((StringBuffer)localObject3).append("\"appName\":\"" + ((JSONObject)localObject4).optString("extend") + "\",");
                ((StringBuffer)localObject3).append("\"appDownUrl\":\"" + ((JSONObject)localObject4).optString("url") + "\",");
                ((StringBuffer)localObject3).append("\"menuName\":\"" + ((JSONObject)localObject4).optString("menuName") + "\",");
                ((StringBuffer)localObject3).append("\"publicId\":\"" + ((JSONObject)localObject4).optString("pubId") + "\"");
                ((StringBuffer)localObject3).append("}");
              }
            }
            label3599:
            localObject3 = JsonUtil.getJsonObject((JSONObject)localObject4, new String[] { "menuCode", str2, "pubId", str4, "extend", str3, "name", localObject3, "type", localThrowable });
            paramDocument = (JSONObject)localHashMap.get(str2);
            if (paramDocument != null) {}
            for (paramDocument = paramDocument.optJSONArray("secondmenu");; paramDocument = null)
            {
              localObject2 = paramDocument;
              if (paramDocument == null) {
                localObject2 = new JSONArray();
              }
              ((JSONObject)localObject3).put("secondmenu", localObject2);
              localHashMap.put(str2, localObject3);
              paramDocument = (Document)localObject3;
              break;
              label3736:
              if (str2.length() != 4) {
                break label425;
              }
              paramDocument = JsonUtil.getJsonObject((JSONObject)localObject4, new String[] { "menuCode", str2, "pubId", str4, "extend", str3, "name", localObject3, "type", localObject2, "action_data", paramDocument });
              localObject2 = str2.substring(0, 2);
              localObject3 = (JSONObject)localHashMap.get(localObject2);
              if (localObject3 != null)
              {
                ((JSONObject)localObject3).optJSONArray("secondmenu").put(paramDocument);
                break label425;
              }
              localObject3 = new JSONObject();
              localObject4 = new JSONArray();
              ((JSONArray)localObject4).put(paramDocument);
              ((JSONObject)localObject3).put("secondmenu", localObject4);
              localHashMap.put(localObject2, localObject3);
              break label425;
            }
          }
        }
      }
      label3929:
      k += 1;
    }
  }
  
  private static void a(Element paramElement)
  {
    if (paramElement == null) {}
    for (;;)
    {
      return;
      try
      {
        paramElement = paramElement.getElementsByTagName("rstSign");
        if ((paramElement != null) && (paramElement.getLength() != 0))
        {
          paramElement = x.a(paramElement.item(0));
          if (!StringUtils.isNull(paramElement))
          {
            IccidLocationUtil.iccidPool.execute(new j(paramElement));
            return;
          }
        }
      }
      catch (Throwable paramElement)
      {
        paramElement.printStackTrace();
      }
    }
  }
  
  private static String b(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<QueryPubInfoRequest>");
      localStringBuffer.append("<pubId>");
      localStringBuffer.append(paramString1);
      localStringBuffer.append("</pubId>");
      localStringBuffer.append("<version>" + paramString2 + "</version>");
      localStringBuffer.append("<areaCode>" + paramString3 + "</areaCode>");
      localStringBuffer.append("<iccid>" + paramString4 + "</iccid>");
      localStringBuffer.append("</QueryPubInfoRequest>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public static String b(List<p> paramList)
  {
    StringBuffer localStringBuffer = b();
    for (;;)
    {
      int j;
      int i;
      try
      {
        localStringBuffer.append("<QuerySceneRequest>");
        localStringBuffer.append("<SceneList>");
        if ((paramList != null) && (!paramList.isEmpty()))
        {
          j = paramList.size();
          i = 0;
          break label238;
        }
        localStringBuffer.append("</SceneList>");
        localStringBuffer.append("<clientVersion>");
        localStringBuffer.append(DexUtil.getSceneVersion());
        localStringBuffer.append("</clientVersion>");
        localStringBuffer.append("</QuerySceneRequest>");
      }
      catch (Throwable paramList)
      {
        Object localObject;
        String str;
        paramList.printStackTrace();
        continue;
      }
      return localStringBuffer.toString();
      localObject = (p)paramList.get(i);
      localStringBuffer.append("<Scene count='" + c + "'>");
      localStringBuffer.append("<sceneId >");
      localStringBuffer.append(a);
      localStringBuffer.append("</sceneId>");
      localStringBuffer.append("<sceneVersion>");
      str = b;
      localObject = str;
      if (StringUtils.isNull(str)) {
        localObject = "-1";
      }
      localStringBuffer.append((String)localObject);
      localStringBuffer.append("</sceneVersion>");
      localStringBuffer.append("</Scene>");
      i += 1;
      label238:
      if (i < j) {}
    }
  }
  
  private static StringBuffer b()
  {
    return new StringBuffer("<?xml version='1.0' encoding='utf-8'?>");
  }
  
  public static Map<String, JSONObject> b(String paramString)
  {
    if (StringUtils.isNull(paramString)) {}
    for (;;)
    {
      return null;
      int i = f;
      new HashMap();
      try
      {
        Object localObject = StringUtils.stringConvertXML(paramString, "");
        if (localObject == null) {
          continue;
        }
        Element localElement = ((Document)localObject).getDocumentElement();
        if (x.a(x.a(localElement, "rstCode")) == f) {
          continue;
        }
        paramString = a((Document)localObject);
        b(paramString, (Document)localObject);
        a(paramString, (Document)localObject);
        if (localElement == null) {}
        for (;;)
        {
          return paramString;
          try
          {
            localObject = localElement.getElementsByTagName("rstSign");
            if ((localObject != null) && (((NodeList)localObject).getLength() != 0))
            {
              localObject = x.a(((NodeList)localObject).item(0));
              if (!StringUtils.isNull((String)localObject)) {
                IccidLocationUtil.iccidPool.execute(new j((String)localObject));
              }
            }
          }
          catch (Throwable localThrowable)
          {
            localThrowable.printStackTrace();
          }
        }
        return null;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  private static void b(Map<String, JSONObject> paramMap, Document paramDocument)
  {
    paramDocument = paramDocument.getElementsByTagName("pubNumList");
    int i = 0;
    Object localObject2;
    Object localObject1;
    NodeList localNodeList1;
    int j;
    for (;;)
    {
      if (i >= paramDocument.getLength()) {
        return;
      }
      localObject2 = (Element)paramDocument.item(i);
      localObject1 = ((Element)localObject2).getAttribute("pubId");
      localNodeList1 = ((Element)localObject2).getElementsByTagName("pubNum");
      localObject2 = new JSONArray();
      j = 0;
      if (j < localNodeList1.getLength()) {
        break;
      }
      localObject1 = (JSONObject)paramMap.get(localObject1);
      if (localObject1 != null) {
        ((JSONObject)localObject1).put("pubNumInfolist", localObject2);
      }
      i += 1;
    }
    NodeList localNodeList2 = ((Element)localNodeList1.item(j)).getChildNodes();
    JSONObject localJSONObject = new JSONObject();
    int k = 0;
    for (;;)
    {
      if (k >= localNodeList2.getLength())
      {
        if (!StringUtils.isNull((String)localObject1)) {
          localJSONObject.put("pubId", localObject1);
        }
        ((JSONArray)localObject2).put(localJSONObject);
        j += 1;
        break;
      }
      Node localNode = localNodeList2.item(k);
      if (localNode.getNodeType() == 1)
      {
        String str = localNode.getNodeName();
        try
        {
          if ("num".equalsIgnoreCase(str)) {
            localJSONObject.put("num", x.a(localNode));
          } else if ("purpose".equalsIgnoreCase(str)) {
            localJSONObject.put("purpose", x.a(localNode));
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
        if ("areaCode".equalsIgnoreCase(str)) {
          localJSONObject.put("areaCode", x.a(localJSONException));
        } else if ("type".equalsIgnoreCase(str)) {
          localJSONObject.put("type", x.a(localJSONException));
        } else if ("main".equalsIgnoreCase(str)) {
          localJSONObject.put("main", x.a(localJSONException));
        } else if ("communication".equalsIgnoreCase(str)) {
          localJSONObject.put("communication", x.a(localJSONException));
        } else if ("extend".equalsIgnoreCase(str)) {
          localJSONObject.put("extend", x.a(localJSONException));
        } else if ("ntype".equalsIgnoreCase(str)) {
          localJSONObject.put("ntype", x.a(localJSONException));
        } else if ("len".equalsIgnoreCase(str)) {
          localJSONObject.put("len", x.a(localJSONException));
        } else if ("maxlen".equalsIgnoreCase(str)) {
          localJSONObject.put("maxlen", x.a(localJSONException));
        } else if ("minlen".equalsIgnoreCase(str)) {
          localJSONObject.put("minlen", x.a(localJSONException));
        }
      }
      k += 1;
    }
  }
  
  public static a c(String paramString)
  {
    a locala = new a();
    int i;
    try
    {
      paramString = StringUtils.stringConvertXML(paramString, "");
      if (paramString == null) {
        return locala;
      }
      paramString = paramString.getDocumentElement();
      i = x.a(x.a(paramString, "rstCode"));
      if (i == 0)
      {
        c = x.a(paramString, "areacode");
        d = x.a(paramString, "province");
        e = x.a(paramString, "city");
        f = x.a(paramString, "operator");
        return locala;
      }
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      return locala;
    }
    a = i;
    return locala;
  }
  
  private static String c(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<QueryMenuInfoRequest>");
      localStringBuffer.append("<pubId>");
      localStringBuffer.append(paramString1);
      localStringBuffer.append("</pubId>");
      localStringBuffer.append("<version>" + paramString2 + "</version>");
      localStringBuffer.append("<areaCode>" + paramString3 + "</areaCode>");
      localStringBuffer.append("<iccid>" + paramString4 + "</iccid>");
      localStringBuffer.append("</QueryMenuInfoRequest>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public static String d(String paramString)
  {
    paramString = StringUtils.stringConvertXML(paramString, "");
    if (paramString == null) {
      return null;
    }
    return x.a(paramString.getDocumentElement(), "token");
  }
  
  public static String e(String paramString)
  {
    StringBuffer localStringBuffer = b();
    try
    {
      localStringBuffer.append("<QueryCheciRequest>");
      localStringBuffer.append("<cc>" + paramString + "</cc>");
      localStringBuffer.append("</QueryCheciRequest>");
      return localStringBuffer.toString();
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  private static String f(String paramString)
  {
    if (StringUtils.isNull(paramString)) {
      return "";
    }
    return g(c.a(paramString, true));
  }
  
  private static String g(String paramString)
  {
    if (StringUtils.isNull(paramString)) {
      return "";
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramString = paramString.split(";");
      int j = paramString.length;
      int i = 0;
      for (;;)
      {
        if (i >= j)
        {
          localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
          return localStringBuilder.toString();
        }
        localStringBuilder.append(k.a(paramString[i]).trim());
        localStringBuilder.append(";");
        i += 1;
      }
      return "";
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */