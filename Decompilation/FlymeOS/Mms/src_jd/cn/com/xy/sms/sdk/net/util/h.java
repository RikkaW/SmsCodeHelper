package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class h
{
  private static int a(String paramString, IccidInfo paramIccidInfo)
  {
    int j = -1;
    int i = j;
    try
    {
      paramString = StringUtils.stringConvertXML(paramString, "");
      if (paramString == null) {
        return -1;
      }
      i = j;
      paramString = paramString.getDocumentElement();
      i = j;
      j = Integer.parseInt(a(paramString, "rstCode"));
      i = j;
      if (j == 1)
      {
        i = j;
        iccid = a(paramString, "iccid");
        i = j;
        operator = a(paramString, "operator");
        i = j;
        provinces = a(paramString, "provinces");
        i = j;
        city = a(paramString, "city");
        i = j;
        updateTime = Long.parseLong(a(paramString, "updateTime"));
        return j;
      }
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return i;
  }
  
  private static String a(Element paramElement, String paramString)
  {
    paramElement = paramElement.getElementsByTagName(paramString);
    if ((paramElement != null) && (paramElement.getLength() > 0)) {
      return x.a(paramElement.item(0));
    }
    return "";
  }
  
  public static Map<String, Object> a(String paramString)
  {
    long l3 = 0L;
    for (;;)
    {
      long l1;
      try
      {
        paramString = StringUtils.stringConvertXML(paramString, "");
        if (paramString == null) {
          return null;
        }
        localObject1 = paramString.getDocumentElement();
        localObject4 = ((Element)localObject1).getElementsByTagName("em");
        localObject5 = a((Element)localObject1, "jars");
        localObject3 = a((Element)localObject1, "jarVersion");
        localObject2 = a((Element)localObject1, "downLoadUrl");
        if (((StringUtils.isNull((String)localObject5)) || (StringUtils.isNull((String)localObject3))) && (((NodeList)localObject4).getLength() == 0)) {
          return null;
        }
        paramString = new HashMap();
        l1 = -1L;
        if (localObject4 == null) {}
      }
      catch (Throwable localThrowable1)
      {
        Object localObject1;
        Object localObject4;
        Object localObject5;
        Object localObject3;
        Object localObject2;
        JSONArray localJSONArray;
        int i;
        long l2;
        JSONObject localJSONObject;
        Object localObject6;
        String str;
        paramString = null;
        localThrowable1.printStackTrace();
      }
      try
      {
        if (((NodeList)localObject4).getLength() > 0)
        {
          localJSONArray = new JSONArray();
          i = 0;
          if (i < ((NodeList)localObject4).getLength()) {
            continue;
          }
          SysParamEntityManager.setParam("EM_VERSION", l1);
          paramString.put("emergencyArray", localJSONArray);
        }
        if ((StringUtils.isNull((String)localObject5)) || (StringUtils.isNull((String)localObject3))) {
          break label551;
        }
        localObject4 = ((String)localObject5).split(",");
        localObject3 = ((String)localObject3).split(",");
        localObject2 = ((String)localObject2).split(",");
        if ((localObject4.length != localObject3.length) || (localObject3.length != localObject2.length)) {
          break label551;
        }
        localObject5 = a((Element)localObject1, "delaystart");
        l2 = l3;
        if (!StringUtils.isNull((String)localObject5)) {
          l2 = Long.parseLong((String)localObject5);
        }
        localObject1 = a((Element)localObject1, "delayend");
        if (StringUtils.isNull((String)localObject1)) {
          break label546;
        }
        l1 = Long.parseLong((String)localObject1);
      }
      catch (Throwable localThrowable2)
      {
        continue;
        l1 = 0L;
        break label553;
        return paramString;
        l3 = l1;
        if (l1 > 0L) {
          continue;
        }
        l3 = 86400000L;
      }
    }
    localObject1 = new JSONArray();
    i = 0;
    label296:
    if (i >= localObject4.length)
    {
      paramString.put("updataJars", localObject1);
      break label570;
      localJSONObject = new JSONObject();
      localObject6 = ((NodeList)localObject4).item(i);
      str = x.a((Node)localObject6);
      localObject6 = ((Element)localObject6).getAttribute("version");
      localJSONObject.put("emContent", str);
      localJSONObject.put("emVersion", localObject6);
    }
    label546:
    label551:
    label553:
    label570:
    label572:
    for (;;)
    {
      try
      {
        if (StringUtils.isNull((String)localObject6)) {
          break label572;
        }
        l2 = Long.parseLong((String)localObject6);
        if (l1 >= l2) {
          break label572;
        }
        l1 = l2;
        localJSONArray.put(localJSONObject);
        i += 1;
      }
      catch (Throwable localThrowable3)
      {
        LogManager.e("XIAOYUAN", "parseJarsUpdateRespose " + localThrowable3.getLocalizedMessage(), localThrowable3);
        break label572;
      }
      localObject5 = new JSONObject();
      ((JSONObject)localObject5).put("name", localObject4[i]);
      ((JSONObject)localObject5).put("version", localObject3[i]);
      ((JSONObject)localObject5).put("url", localObject2[i]);
      ((JSONObject)localObject5).put("delayStart", l2);
      ((JSONObject)localObject5).put("delayEnd", l3);
      ((JSONArray)localObject1).put(localObject5);
      i += 1;
      break label296;
      return paramString;
    }
  }
  
  public static List<SceneRule> b(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      int j;
      try
      {
        paramString = StringUtils.stringConvertXML(paramString, "");
        if (paramString == null) {
          return null;
        }
        if (LogManager.debug) {}
        paramString = paramString.getElementsByTagName("SceneRule");
        int i = 0;
        if (i < paramString.getLength())
        {
          localSceneRule = new SceneRule();
          NodeList localNodeList = ((Element)paramString.item(i)).getChildNodes();
          j = 0;
          if (j >= localNodeList.getLength())
          {
            localArrayList.add(localSceneRule);
            i += 1;
            continue;
          }
          localNode = localNodeList.item(j);
          if (localNode.getNodeType() != 1) {
            break label464;
          }
          str = localNode.getNodeName();
          if ("sceneId".equalsIgnoreCase(str)) {
            scene_id = x.a(localNode);
          } else if ("sceneRuleVersion".equalsIgnoreCase(str)) {
            sceneruleVersion = x.a(localNode);
          }
        }
      }
      catch (Throwable paramString)
      {
        SceneRule localSceneRule;
        Node localNode;
        String str;
        paramString.printStackTrace();
        break label462;
        if ("province".equalsIgnoreCase(str)) {
          province = x.a(localNode);
        } else if ("id".equalsIgnoreCase(str)) {
          id = x.a(localNode);
        } else if ("operator".equalsIgnoreCase(str)) {
          operator = x.a(localNode);
        } else if ("expire_date".equalsIgnoreCase(str)) {
          expire_date = x.a(localNode);
        } else if ("fun_call".equalsIgnoreCase(str)) {
          Func_call = Integer.parseInt(x.a(localNode));
        } else if ("fun_acc_url".equalsIgnoreCase(str)) {
          Func_acc_url = Integer.parseInt(x.a(localNode));
        } else if ("fun_reply_sms".equalsIgnoreCase(str)) {
          Func_reply_sms = Integer.parseInt(x.a(localNode));
        } else if ("fun_config".equalsIgnoreCase(str)) {
          Func_config = x.a(localNode);
        } else if ("res_urls".equalsIgnoreCase(str)) {
          res_urls = x.a(localNode);
        } else if ("s_version".equalsIgnoreCase(str)) {
          s_version = x.a(localNode);
        } else if ("scene_page_conf".equalsIgnoreCase(str)) {
          Scene_page_config = x.a(localNode);
        }
      }
      label462:
      return localArrayList;
      label464:
      j += 1;
    }
  }
  
  public static boolean c(String paramString)
  {
    try
    {
      paramString = StringUtils.stringConvertXML(paramString, "");
      if (paramString == null) {
        return false;
      }
      paramString = paramString.getDocumentElement().getElementsByTagName("rstCode");
      if ((paramString != null) && (paramString.getLength() > 0))
      {
        paramString = x.a(paramString.item(0)).toString();
        if (!StringUtils.isNull(paramString))
        {
          boolean bool = paramString.equals("0");
          if (bool) {
            return true;
          }
        }
      }
    }
    catch (Throwable paramString) {}
    return false;
  }
  
  public static boolean d(String paramString)
  {
    try
    {
      paramString = StringUtils.stringConvertXML(paramString, "");
      if (paramString == null) {
        return false;
      }
      paramString = paramString.getDocumentElement().getElementsByTagName("rstCode");
      if ((paramString != null) && (paramString.getLength() > 0))
      {
        paramString = x.a(paramString.item(0)).toString();
        if (!StringUtils.isNull(paramString))
        {
          boolean bool = paramString.equals("0");
          if (bool) {
            return true;
          }
        }
      }
    }
    catch (Throwable paramString) {}
    return false;
  }
  
  public static HashMap<String, Object> e(String paramString)
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    Object localObject1;
    int i;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    int j;
    label154:
    int m;
    for (;;)
    {
      try
      {
        paramString = StringUtils.stringConvertXML(paramString, "");
        if (paramString == null) {
          return null;
        }
        if (LogManager.debug) {}
        localObject1 = paramString.getElementsByTagName("Scene");
        i = 0;
        if (i < ((NodeList)localObject1).getLength()) {
          continue;
        }
        paramString = paramString.getElementsByTagName("SceneUrl");
        localObject1 = new ArrayList();
        if (paramString != null)
        {
          i = 0;
          if (i < paramString.getLength()) {
            break label735;
          }
        }
        localHashMap.put("sceneUrllist", localObject1);
      }
      catch (Throwable paramString)
      {
        label195:
        paramString.printStackTrace();
        LogManager.e("result ", "error Throwable =" + paramString.getMessage(), paramString);
        continue;
      }
      localHashMap.put("sceneconfigList", localArrayList);
      return localHashMap;
      localObject2 = new p();
      localObject3 = (Element)((NodeList)localObject1).item(i);
      localObject4 = ((Element)localObject3).getChildNodes();
      j = 0;
      if (j < ((NodeList)localObject4).getLength()) {
        continue;
      }
      localObject3 = ((Element)localObject3).getElementsByTagName("SceneRule");
      if (localObject3 != null)
      {
        m = ((NodeList)localObject3).getLength();
        j = 0;
        break label770;
      }
      localArrayList.add(localObject2);
      i += 1;
    }
    Object localObject5 = ((NodeList)localObject4).item(j);
    Object localObject6;
    label322:
    int k;
    if (((Node)localObject5).getNodeType() == 1)
    {
      localObject6 = ((Node)localObject5).getNodeName();
      if ("sceneId".equalsIgnoreCase((String)localObject6))
      {
        a = x.a((Node)localObject5);
      }
      else if ("sceneVersion".equalsIgnoreCase((String)localObject6))
      {
        b = x.a((Node)localObject5);
        break label779;
        localObject4 = new SceneRule();
        localObject5 = ((Element)((NodeList)localObject3).item(j)).getChildNodes();
        k = 0;
      }
    }
    for (;;)
    {
      if (k >= ((NodeList)localObject5).getLength())
      {
        ((p)localObject2).a((SceneRule)localObject4);
        j += 1;
      }
      else
      {
        localObject6 = ((NodeList)localObject5).item(k);
        if (((Node)localObject6).getNodeType() != 1) {
          break label786;
        }
        String str = ((Node)localObject6).getNodeName();
        if (((Node)localObject6).getNodeType() != 1) {
          break label786;
        }
        if ("sceneId".equalsIgnoreCase(str))
        {
          scene_id = x.a((Node)localObject6);
          break label786;
        }
        if ("sceneRuleVersion".equalsIgnoreCase(str))
        {
          sceneruleVersion = x.a((Node)localObject6);
          break label786;
        }
        if ("province".equalsIgnoreCase(str))
        {
          province = x.a((Node)localObject6);
          break label786;
        }
        if ("id".equalsIgnoreCase(str))
        {
          id = x.a((Node)localObject6);
          break label786;
        }
        if ("operator".equalsIgnoreCase(str))
        {
          operator = x.a((Node)localObject6);
          break label786;
        }
        if ("expire_date".equalsIgnoreCase(str))
        {
          expire_date = x.a((Node)localObject6);
          break label786;
        }
        if ("fun_call".equalsIgnoreCase(str))
        {
          Func_call = Integer.parseInt(x.a((Node)localObject6));
          break label786;
        }
        if ("fun_acc_url".equalsIgnoreCase(str))
        {
          Func_acc_url = Integer.parseInt(x.a((Node)localObject6));
          break label786;
        }
        if ("fun_reply_sms".equalsIgnoreCase(str))
        {
          Func_reply_sms = Integer.parseInt(x.a((Node)localObject6));
          break label786;
        }
        if ("fun_config".equalsIgnoreCase(str))
        {
          Func_config = x.a((Node)localObject6);
          break label786;
        }
        if ("res_urls".equalsIgnoreCase(str))
        {
          res_urls = x.a((Node)localObject6);
          break label786;
        }
        if ("s_version".equalsIgnoreCase(str))
        {
          s_version = x.a((Node)localObject6);
          break label786;
        }
        if (!"scene_page_conf".equalsIgnoreCase(str)) {
          break label786;
        }
        Scene_page_config = x.a((Node)localObject6);
        break label786;
        label735:
        localObject2 = x.a(paramString.item(i));
        if (!StringUtils.isNull((String)localObject2)) {
          ((ArrayList)localObject1).add(localObject2);
        }
        i += 1;
        break;
      }
      label770:
      if (j < m) {
        break label322;
      }
      break label195;
      label779:
      j += 1;
      break label154;
      label786:
      k += 1;
    }
  }
  
  public static JSONArray f(String paramString)
  {
    for (;;)
    {
      try
      {
        JSONArray localJSONArray = new JSONArray();
        Object localObject1 = StringUtils.stringConvertXML(paramString, "");
        if (localObject1 == null) {
          return null;
        }
        paramString = ((Document)localObject1).getDocumentElement();
        Object localObject2 = paramString.getElementsByTagName("code");
        if ((localObject2 != null) && (((NodeList)localObject2).getLength() > 0))
        {
          localObject2 = x.a(((NodeList)localObject2).item(0)).toString();
          if ((StringUtils.isNull((String)localObject2)) || (!"0".equals(localObject2))) {
            break;
          }
        }
        paramString = paramString.getElementsByTagName("res_type");
        if ((paramString != null) && (paramString.getLength() > 0))
        {
          paramString = x.a(paramString.item(0)).toString();
          localObject1 = ((Document)localObject1).getElementsByTagName("res");
          int i = 0;
          if (i >= ((NodeList)localObject1).getLength()) {
            return localJSONArray;
          }
          localObject2 = new JSONObject();
          Element localElement = (Element)((NodeList)localObject1).item(i);
          String str1 = localElement.getAttribute("version");
          String str2 = localElement.getAttribute("del_history");
          ((JSONObject)localObject2).put("res_version", str1);
          ((JSONObject)localObject2).put("del_history", str2);
          ((JSONObject)localObject2).put("res_url", x.a(localElement));
          ((JSONObject)localObject2).put("res_type", paramString);
          localJSONArray.put(localObject2);
          i += 1;
          continue;
        }
        paramString = "";
      }
      catch (Throwable paramString)
      {
        return null;
      }
    }
    return null;
  }
  
  public static JSONObject g(String paramString)
  {
    try
    {
      paramString = StringUtils.stringConvertXML(paramString, "");
      if (paramString == null) {
        return null;
      }
      paramString = paramString.getDocumentElement().getElementsByTagName("data");
      if ((paramString == null) || (paramString.getLength() <= 0)) {
        break label78;
      }
      paramString = x.a(paramString.item(0)).toString();
      if (StringUtils.isNull(paramString)) {
        break label78;
      }
      paramString = new JSONObject(paramString);
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        label78:
        paramString = null;
      }
    }
    return paramString;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */