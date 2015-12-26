package cn.com.xy.sms.sdk.ui.popu.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.view.View;
import android.widget.TextView;
import br.a;
import br.b;
import br.f;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.Locale;
import org.json.JSONObject;

public class ContentUtil
{
  public static final String CHINESE = "[一-龥]";
  public static final String COR_BLACK = "#000000";
  public static final String COR_GRAY = "#7F000000";
  public static final String COR_LIGHT_BLUE = "#D4EEFB";
  public static final String COR_RED = "#FF1F00";
  public static final String COR_WHITE = "#ffffff";
  public static final String DUOQU_BUTTON_NAME_DELETE_EN;
  public static final String DUOQU_BUTTON_NAME_DELETE_TW;
  public static final String DUOQU_BUTTON_NAME_DELETE_ZH;
  public static final String DUOQU_BUTTON_NAME_HAS_READ_EN;
  public static final String DUOQU_BUTTON_NAME_HAS_READ_TW;
  public static final String DUOQU_BUTTON_NAME_HAS_READ_ZH;
  public static final String DUOQU_BUTTON_NAME_REPLY_EN = getResourceString(Constant.getContext(), br.f.duoqu_reply_en);
  public static final String DUOQU_BUTTON_NAME_REPLY_TW = getResourceString(Constant.getContext(), br.f.duoqu_reply_tw);
  public static final String DUOQU_BUTTON_NAME_REPLY_ZH;
  public static final int HORIZ_LF_TABLE_TITLE_WIDTH;
  public static final int HORIZ_TABLE_MIN_TITLE_WIDTH;
  public static final int HORIZ_TABLE_TITLE_LINE_SPACING;
  public static final int HORIZ_TABLE_TITLE_PADDING;
  public static final String NO_DATA = getResourceString(Constant.getContext(), br.f.duoqu_double_line);
  public static final String NO_DATA_EN = getResourceString(Constant.getContext(), br.f.duoqu_double_line_en);
  
  static
  {
    HORIZ_TABLE_TITLE_PADDING = getDimension(br.b.duoqu_title_padding);
    HORIZ_TABLE_TITLE_LINE_SPACING = getDimension(br.b.duoqu_title_Line_spacing);
    HORIZ_TABLE_MIN_TITLE_WIDTH = getDimension(br.b.duoqu_title_min_width);
    HORIZ_LF_TABLE_TITLE_WIDTH = getDimension(br.b.duoqu_title_width);
    DUOQU_BUTTON_NAME_HAS_READ_ZH = getResourceString(Constant.getContext(), br.f.duoqu_has_read_zh);
    DUOQU_BUTTON_NAME_HAS_READ_EN = getResourceString(Constant.getContext(), br.f.duoqu_has_read_en);
    DUOQU_BUTTON_NAME_HAS_READ_TW = getResourceString(Constant.getContext(), br.f.duoqu_has_read_tw);
    DUOQU_BUTTON_NAME_DELETE_ZH = getResourceString(Constant.getContext(), br.f.duoqu_delete_zh);
    DUOQU_BUTTON_NAME_DELETE_EN = getResourceString(Constant.getContext(), br.f.duoqu_delete_en);
    DUOQU_BUTTON_NAME_DELETE_TW = getResourceString(Constant.getContext(), br.f.duoqu_delete_tw);
    DUOQU_BUTTON_NAME_REPLY_ZH = getResourceString(Constant.getContext(), br.f.duoqu_reply_zh);
  }
  
  public static int getBackgroundResId(String paramString)
  {
    return br.a.duoqu_all_transparent;
  }
  
  public static String getBtnName(JSONObject paramJSONObject)
  {
    if ("zh-cn".equalsIgnoreCase(getLanguage())) {
      return (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "btn_name");
    }
    if ("zh-tw".equalsIgnoreCase(getLanguage())) {
      return (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "ftName");
    }
    return (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "egName");
  }
  
  public static int getDimension(int paramInt)
  {
    return (int)Constant.getContext().getResources().getDimension(paramInt);
  }
  
  public static String getLanguage()
  {
    String str1 = Locale.getDefault().getLanguage();
    String str2 = Locale.getDefault().getCountry();
    if ("zh".equals(str1))
    {
      if (("HK".equalsIgnoreCase(str2)) || ("TW".equalsIgnoreCase(str2))) {
        return "zh-tw";
      }
      return "zh-cn";
    }
    return "en";
  }
  
  public static String getResourceString(Context paramContext, int paramInt)
  {
    String str = null;
    if (paramContext != null) {}
    try
    {
      str = paramContext.getResources().getString(paramInt);
      return str;
    }
    catch (Resources.NotFoundException paramContext) {}
    return null;
  }
  
  public static int getStringLength(String paramString)
  {
    int j = 0;
    if (paramString == null) {
      return 0;
    }
    int i = 0;
    if (j < paramString.length())
    {
      if (Character.valueOf(paramString.charAt(j)).toString().matches("[一-龥]")) {
        i += 2;
      }
      for (;;)
      {
        j += 1;
        break;
        i += 1;
      }
    }
    return i;
  }
  
  public static void isTextSetColor(TextView paramTextView, String paramString, int paramInt)
  {
    if (paramTextView == null) {
      return;
    }
    int i = ResourceCacheUtil.parseColor(paramString);
    if (i != -1)
    {
      paramTextView.setTextColor(i);
      return;
    }
    paramTextView.setTextColor(paramInt);
  }
  
  public static void setText(TextView paramTextView, String paramString1, String paramString2)
  {
    if (paramTextView == null) {
      return;
    }
    if (StringUtils.isNull(paramString1))
    {
      paramTextView.setText(paramString2);
      return;
    }
    paramTextView.setText(paramString1.trim());
  }
  
  public static void setTextColor(TextView paramTextView, String paramString)
  {
    if (paramTextView != null) {}
    try
    {
      if (!StringUtils.isNull(paramString)) {
        paramTextView.setTextColor(ResourceCacheUtil.parseColor(paramString));
      }
      return;
    }
    catch (Exception paramTextView)
    {
      paramTextView.printStackTrace();
    }
  }
  
  public static void setViewVisibility(View paramView, int paramInt)
  {
    if (paramView != null) {
      paramView.setVisibility(paramInt);
    }
  }
  
  public static void textSetColor(TextView paramTextView, String paramString)
  {
    int i = ResourceCacheUtil.parseColor(paramString);
    if ((i != -1) && (paramTextView != null)) {
      paramTextView.setTextColor(i);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.ContentUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */