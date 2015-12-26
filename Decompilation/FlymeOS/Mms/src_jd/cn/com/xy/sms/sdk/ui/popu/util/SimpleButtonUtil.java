package cn.com.xy.sms.sdk.ui.popu.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import br.b;
import br.f;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONObject;

public class SimpleButtonUtil
{
  private static final int DRAWABLE_BOUNDS_RIGHT = (int)ViewUtil.getDimension(br.b.duoqu_drawable_bounds_right);
  static int mBottom = 0;
  static int mRight;
  private static int mTop = 0;
  
  static
  {
    mRight = 0;
  }
  
  public static int bindButtonData(TextView paramTextView, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!StringUtils.isNull(paramString))
    {
      if (!paramString.equalsIgnoreCase("url")) {
        break label29;
      }
      if (paramBoolean1) {
        paramTextView.setText(br.f.duoqu_open_net);
      }
    }
    label29:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        return -1;
                        if ((!paramString.equalsIgnoreCase("reply_sms")) && (!paramString.equalsIgnoreCase("send_sms"))) {
                          break;
                        }
                      } while (!paramBoolean1);
                      paramTextView.setText(br.f.duoqu_reply_sms);
                      return -1;
                      if (!paramString.equalsIgnoreCase("reply_sms_fwd")) {
                        break;
                      }
                    } while (!paramBoolean1);
                    paramTextView.setText(br.f.duoqu_forword_sms);
                    return -1;
                    if ((!paramString.equalsIgnoreCase("call_phone")) && (!paramString.equalsIgnoreCase("call"))) {
                      break;
                    }
                  } while (!paramBoolean1);
                  paramTextView.setText(br.f.duoqu_call_phone);
                  return -1;
                  if (!paramString.equalsIgnoreCase("reply_sms_open")) {
                    break;
                  }
                } while (!paramBoolean1);
                paramTextView.setText(br.f.duoqu_open_text);
                return -1;
                if (!paramString.equalsIgnoreCase("access_url")) {
                  break;
                }
              } while (!paramBoolean1);
              paramTextView.setText(br.f.duoqu_open_net);
              return -1;
              if (!paramString.equalsIgnoreCase("down_url")) {
                break;
              }
            } while (!paramBoolean1);
            paramTextView.setText(br.f.duoqu_open_net);
            return -1;
            if (!paramString.equalsIgnoreCase("send_email")) {
              break;
            }
          } while (!paramBoolean1);
          paramTextView.setText(br.f.duoqu_send_email);
          return -1;
          if ((!paramString.equalsIgnoreCase("map_site")) && (!paramString.equalsIgnoreCase("open_map_list"))) {
            break;
          }
        } while (!paramBoolean1);
        paramTextView.setText(br.f.duoqu_open_map);
        return -1;
        if ((!paramString.equalsIgnoreCase("chong_zhi")) && (!paramString.equalsIgnoreCase("recharge"))) {
          break;
        }
      } while (!paramBoolean1);
      paramTextView.setText(br.f.duoqu_chonzhi);
      return -1;
    } while ((paramString.equalsIgnoreCase("WEB_QUERY_EXPRESS_FLOW")) || (paramString.equalsIgnoreCase("WEB_QUERY_FLIGHT_TREND")) || (paramString.equalsIgnoreCase("WEB_TRAFFIC_ORDER")) || (paramString.equalsIgnoreCase("WEB_INSTALMENT_PLAN")) || (paramString.equalsIgnoreCase("copy_code")) || (paramString.equalsIgnoreCase("zfb_repayment")) || (paramString.equalsIgnoreCase("repayment")) || (paramString.equalsIgnoreCase("sdk_time_remind")) || (paramString.equalsIgnoreCase("time_remind")) || (paramString.equalsIgnoreCase("web_train_station")) || (paramString.equalsIgnoreCase("open_map")) || (!paramString.equalsIgnoreCase("pay_water_gas")));
    return -1;
  }
  
  public static void doAction(Activity paramActivity, JSONObject paramJSONObject, HashMap<String, Object> paramHashMap)
  {
    try
    {
      HashMap localHashMap = new HashMap();
      if ((paramHashMap != null) && (!paramHashMap.isEmpty()))
      {
        paramHashMap = paramHashMap.entrySet().iterator();
        while (paramHashMap.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramHashMap.next();
          if ((localEntry.getValue() instanceof String)) {
            localHashMap.put(localEntry.getKey(), (String)localEntry.getValue());
          }
        }
      }
      DuoquUtils.doAction(paramActivity, (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "action_data"), localHashMap);
    }
    catch (Exception paramActivity)
    {
      if (LogManager.debug) {
        paramActivity.printStackTrace();
      }
      return;
    }
  }
  
  public static int getBottom()
  {
    if (mBottom == 0) {
      mBottom = getRight();
    }
    return mBottom;
  }
  
  public static int getRight()
  {
    if (mRight == 0) {
      mRight = DRAWABLE_BOUNDS_RIGHT;
    }
    return mRight;
  }
  
  public static int getTop()
  {
    return mTop;
  }
  
  public static void setBotton(Activity paramActivity, View paramView, TextView paramTextView, JSONObject paramJSONObject, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    if (paramJSONObject != null)
    {
      String str = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "action");
      setButtonValue(paramActivity, paramTextView, paramJSONObject, paramBoolean, str, true);
      if (!StringUtils.isNull(str))
      {
        paramView.setTag(paramJSONObject);
        paramView.setOnClickListener(new SimpleButtonUtil.1(paramActivity, paramJSONObject, paramHashMap));
      }
    }
  }
  
  public static void setBottonValue(Activity paramActivity, TextView paramTextView, JSONObject paramJSONObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramJSONObject != null) {
      setButtonValue(paramActivity, paramTextView, paramJSONObject, paramBoolean1, (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "action"), paramBoolean2);
    }
  }
  
  public static void setButtonTextAndImg(Context paramContext, TextView paramTextView, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramTextView == null) {
      return;
    }
    try
    {
      int i = bindButtonData(paramTextView, paramString, StringUtils.isNull(paramTextView.getText().toString()), paramBoolean2);
      if ((paramBoolean1) && (i != -1))
      {
        paramContext = Constant.getContext().getResources().getDrawable(i);
        paramContext.setBounds(0, getTop(), getRight(), getBottom());
        paramTextView.setCompoundDrawables(paramContext, null, null, null);
        return;
      }
    }
    catch (Resources.NotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return;
      paramTextView.setCompoundDrawables(null, null, null, null);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void setButtonValue(Activity paramActivity, TextView paramTextView, JSONObject paramJSONObject, boolean paramBoolean1, String paramString, boolean paramBoolean2)
  {
    paramActivity = ContentUtil.getBtnName(paramJSONObject);
    if (!StringUtils.isNull(paramActivity))
    {
      if (paramBoolean2) {}
      paramTextView.setText(paramActivity);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.SimpleButtonUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */