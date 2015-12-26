import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.ParseRichBubbleManager;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class cf
{
  private static View a(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, String paramString, View paramView, ViewGroup paramViewGroup, ce paramce)
  {
    Object localObject2;
    Object localObject1;
    if (!StringUtils.isNull(paramString))
    {
      localObject2 = paramce.c(paramString);
      if (localObject2 != null)
      {
        int k = ((LinkedList)localObject2).size();
        int i = 0;
        int m;
        int j;
        do
        {
          localObject1 = a((LinkedList)localObject2);
          m = ViewManger.indexOfChild((View)localObject1, paramViewGroup);
          j = i + 1;
          if (m == -1) {
            break;
          }
          i = j;
        } while (j < k);
        if (m != -1)
        {
          localObject1 = null;
          paramView = (View)localObject1;
          paramViewGroup = (ViewGroup)localObject2;
          if (localObject1 == null) {}
        }
      }
    }
    for (;;)
    {
      try
      {
        ((dj)localObject1).b(paramActivity, paramBusinessSmsMessage);
        paramViewGroup = (ViewGroup)localObject2;
        paramView = (View)localObject1;
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        paramView = null;
        paramViewGroup = (ViewGroup)localObject2;
        continue;
        paramActivity = paramViewGroup;
        continue;
      }
      localObject1 = paramView;
      if (paramView == null)
      {
        localObject1 = new dj(paramActivity);
        localObject2 = (Map)c.get(paramString);
        paramView = (View)localObject2;
        if (localObject2 == null)
        {
          paramView = ViewManger.parseViewPartParam(paramString);
          c.put(paramString, paramView);
        }
        paramBusinessSmsMessage.putValue("viewPartParam", paramView);
        ((dj)localObject1).a(paramActivity, paramBusinessSmsMessage, null);
        ((dj)localObject1).setId(999999999);
        if (paramViewGroup == null)
        {
          paramActivity = new LinkedList();
          paramce.a(paramString, paramActivity);
          a((dj)localObject1, paramActivity);
        }
      }
      else
      {
        return (View)localObject1;
      }
      break;
      paramView = null;
      paramViewGroup = (ViewGroup)localObject2;
      continue;
      paramViewGroup = null;
      paramView = null;
    }
  }
  
  public static View a(Activity paramActivity, JSONObject paramJSONObject, String paramString1, String paramString2, String paramString3, View paramView, AdapterView paramAdapterView, HashMap<String, Object> paramHashMap)
  {
    Object localObject3 = null;
    String str = null;
    Object localObject1 = localObject3;
    try
    {
      System.currentTimeMillis();
      localObject1 = localObject3;
      ce localce = ce.a(paramString3);
      localObject1 = localObject3;
      Object localObject2 = localce.b(paramString1);
      if (localObject2 != null)
      {
        localObject1 = localObject3;
        paramJSONObject = (String)((BusinessSmsMessage)localObject2).getValue("View_fdes");
      }
      do
      {
        do
        {
          try
          {
            extendParamMap = paramHashMap;
            paramActivity = a(paramActivity, (BusinessSmsMessage)localObject2, paramJSONObject, paramView, paramAdapterView, localce);
            localObject2 = paramActivity;
            return (View)localObject2;
          }
          catch (Exception paramActivity)
          {
            for (;;)
            {
              localObject1 = localObject3;
              paramActivity.printStackTrace();
              paramActivity = null;
            }
          }
          localObject2 = str;
        } while (paramJSONObject == null);
        localObject2 = str;
        localObject1 = localObject3;
      } while (!paramJSONObject.has("View_fdes"));
      localObject1 = localObject3;
      str = paramJSONObject.getString("View_fdes");
      localObject1 = localObject3;
      localObject2 = BusinessSmsMessage.createMsgObj();
      localObject1 = localObject3;
      smsId = Long.parseLong(paramString1);
      localObject1 = localObject3;
      viewType = 1;
      localObject1 = localObject3;
      bubbleJsonObj = paramJSONObject;
      localObject1 = localObject3;
      messageBody = paramString2;
      localObject1 = localObject3;
      originatingAddress = paramString3;
      localObject1 = localObject3;
      titleNo = paramJSONObject.optString("title_num");
      localObject1 = localObject3;
      extendParamMap = paramHashMap;
      localObject1 = localObject3;
      simIndex = XyUtil.getSimIndex(paramHashMap);
      localObject1 = localObject3;
      paramActivity = a(paramActivity, (BusinessSmsMessage)localObject2, str, paramView, paramAdapterView, localce);
      localObject1 = paramActivity;
      localce.a(paramString1, (BusinessSmsMessage)localObject2);
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return (View)localObject1;
  }
  
  @SuppressLint({"NewApi"})
  private static dj a(LinkedList<dj> paramLinkedList)
  {
    if (paramLinkedList != null)
    {
      dj localdj = (dj)paramLinkedList.pollFirst();
      if (localdj != null) {
        a(localdj, paramLinkedList);
      }
      return localdj;
    }
    return null;
  }
  
  public static JSONObject a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, byte paramByte, View paramView, ViewGroup paramViewGroup1, ViewGroup paramViewGroup2, AdapterView paramAdapterView, HashMap<String, Object> paramHashMap, SdkCallBack paramSdkCallBack, boolean paramBoolean)
  {
    if (StringUtils.isNull(paramString1))
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), null, paramString1 });
      return null;
    }
    try
    {
      ParseRichBubbleManager.queryDataByMsgItem(paramString1, paramString2, paramString4, paramLong, paramString3, 2, paramSdkCallBack, paramBoolean, paramHashMap);
      return null;
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), null, paramString1 });
        paramActivity.printStackTrace();
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  private static void a(dj paramdj, LinkedList<dj> paramLinkedList)
  {
    paramLinkedList.offerLast(paramdj);
  }
}

/* Location:
 * Qualified Name:     cf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */