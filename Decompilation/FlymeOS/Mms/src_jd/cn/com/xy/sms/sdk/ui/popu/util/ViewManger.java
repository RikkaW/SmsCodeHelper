package cn.com.xy.sms.sdk.ui.popu.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import br.b;
import br.e;
import cm;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.util.StringUtils;
import co;
import cp;
import cu;
import cv;
import cw;
import dd;
import de;
import df;
import dg;
import dk;
import er;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewManger
{
  public static final int ONE_SIDE_POPUPVIEW = 1;
  private static final int TYPE_MARGIN_11 = getIntDimen(Constant.getContext(), br.b.duoqu_type_margin_11);
  private static final int TYPE_PADDING_11 = getIntDimen(Constant.getContext(), br.b.duoqu_type_padding_11);
  private static final int TYPE_SPLIT_LR_MARGIN_111;
  private static final int TYPE_SPLIT_LR_MARGIN_112;
  private static final int TYPE_VIEW_HEIGHT_11 = getIntDimen(Constant.getContext(), br.b.duoqu_type_view_height_11);
  private static final Integer[] VIEW_PART_ID = { Integer.valueOf(101), Integer.valueOf(102), Integer.valueOf(510), Integer.valueOf(501), Integer.valueOf(508), Integer.valueOf(901), Integer.valueOf(902), Integer.valueOf(504), Integer.valueOf(503), Integer.valueOf(505), Integer.valueOf(509), Integer.valueOf(502) };
  
  static
  {
    TYPE_SPLIT_LR_MARGIN_111 = getIntDimen(Constant.getContext(), br.b.duoqu_type_split_lr_margin_111);
    TYPE_SPLIT_LR_MARGIN_112 = getIntDimen(Constant.getContext(), br.b.duoqu_type_split_lr_margin_112);
  }
  
  static boolean checkHasViewPartId(int paramInt)
  {
    Integer[] arrayOfInteger = VIEW_PART_ID;
    int j = arrayOfInteger.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfInteger[i].intValue() == paramInt) {
        return true;
      }
      i += 1;
    }
    throw new Exception("checkHasViewPartId partId: " + paramInt + " not Find.");
  }
  
  public static View createContextByLayoutId(Context paramContext, int paramInt, ViewGroup paramViewGroup)
  {
    try
    {
      paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(paramInt, paramViewGroup);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static ViewGroup createFrameViewGroup(Context paramContext)
  {
    return (ViewGroup)createContextByLayoutId(paramContext, br.e.duoqu_frame_view, null);
  }
  
  public static RelativeLayout createRootView(Context paramContext)
  {
    paramContext = new RelativeLayout(paramContext);
    paramContext.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    return paramContext;
  }
  
  public static ScrollView createScrollView(Context paramContext, View paramView)
  {
    return (ScrollView)createContextByLayoutId(paramContext, br.e.duoqu_scroll_view, null);
  }
  
  public static boolean displayMarkImage(BusinessSmsMessage paramBusinessSmsMessage)
  {
    return true;
  }
  
  public static boolean displayTime(BusinessSmsMessage paramBusinessSmsMessage)
  {
    return false;
  }
  
  private static dg getBodyUIPartByPartId(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, ViewGroup paramViewGroup, int paramInt)
  {
    switch (paramInt)
    {
    case 506: 
    case 507: 
    case 508: 
    case 509: 
    default: 
      return null;
    case 501: 
      return new cu(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_horz_title_lf_table, paramViewGroup, paramInt);
    case 510: 
      return new cu(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_horz_title_lf_table, paramViewGroup, paramInt);
    case 502: 
      return new cv(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_horz_title_table, paramViewGroup, paramInt);
    case 503: 
      return new cp(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_horz_air_table, paramViewGroup, paramInt);
    case 504: 
      return new cw(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_horz_tarin_table, paramViewGroup, paramInt);
    }
    return new cm(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_bubble_body_callsmessage, paramViewGroup, paramInt);
  }
  
  public static int getBodyViewPadding(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    }
    return TYPE_PADDING_11;
  }
  
  public static int getDouquAttrDimen(er paramer, int paramInt)
  {
    paramer = paramer.a((byte)1, paramInt);
    if (paramer != null) {
      return Math.round(((Float)paramer).floatValue());
    }
    return 0;
  }
  
  private static dg getFootUIPartByPartId(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, ViewGroup paramViewGroup, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 901: 
      return new co(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_bubble_bottom_two, paramViewGroup, paramInt);
    }
    paramActivity = new df(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_top_split, paramViewGroup, paramInt);
    paramActivity.a("MLR", Integer.valueOf(112));
    return paramActivity;
  }
  
  private static dg getHeadUIPartByPartId(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, ViewGroup paramViewGroup, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 101: 
      return new dd(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_bubble_title_head, paramViewGroup, paramInt);
    }
    return new de(paramActivity, paramBusinessSmsMessage, paramXyCallBack, br.e.duoqu_bubble_title_split_head, paramViewGroup, paramInt);
  }
  
  public static int getIdentifier(String paramString1, String paramString2)
  {
    return Constant.getContext().getResources().getIdentifier(paramString1, paramString2, Constant.getContext().getPackageName());
  }
  
  public static int getInnerLayoutMargin(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 111: 
      return TYPE_SPLIT_LR_MARGIN_111;
    }
    return TYPE_SPLIT_LR_MARGIN_112;
  }
  
  public static int getIntDimen(Context paramContext, int paramInt)
  {
    return Math.round(paramContext.getResources().getDimension(paramInt));
  }
  
  public static dg getUIPartByPartId(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, ViewGroup paramViewGroup, int paramInt)
  {
    dg localdg = null;
    if (paramInt < 500) {
      localdg = getHeadUIPartByPartId(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramViewGroup, paramInt);
    }
    do
    {
      return localdg;
      if (paramInt < 900) {
        return getBodyUIPartByPartId(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramViewGroup, paramInt);
      }
    } while (paramInt < 900);
    return getFootUIPartByPartId(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramViewGroup, paramInt);
  }
  
  public static ArrayList<Integer> getViewPartList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramString.length();
    int i = 0;
    for (;;)
    {
      if ((i >= j) || (i + 3 > j)) {
        return localArrayList;
      }
      int k = Integer.parseInt(paramString.substring(i, i + 3));
      checkHasViewPartId(k);
      localArrayList.add(Integer.valueOf(k));
      i += 3;
    }
  }
  
  public static int indexOfChild(View paramView, ViewGroup paramViewGroup)
  {
    int j;
    if ((paramView == null) || (paramViewGroup == null))
    {
      Log.e("duoqu_xiaoyuan", "indexOfChild view == null || apView == null");
      j = -1;
    }
    int i;
    do
    {
      return j;
      int k = paramViewGroup.getChildCount();
      i = 0;
      if (i >= k) {
        break;
      }
      localView = paramViewGroup.getChildAt(i);
      j = i;
    } while (localView == paramView);
    View localView = localView.findViewById(999999999);
    if (localView == null) {}
    while (localView != paramView)
    {
      i += 1;
      break;
    }
    return i;
    return -1;
  }
  
  public static boolean isPopupAble(Map<String, Object> paramMap, String paramString)
  {
    if (paramMap != null) {
      try
      {
        if (!StringUtils.isNull(paramString))
        {
          if (paramMap.containsKey("View_viewid"))
          {
            paramString = (String)paramMap.get("View_viewid");
            if (!StringUtils.isNull(paramString))
            {
              int i = Integer.parseInt(paramString);
              try
              {
                paramString = parseViewPartParam((String)paramMap.get("View_fdes"));
                if ((paramString != null) && (!paramString.isEmpty()))
                {
                  paramMap.put("viewPartParam", paramString);
                  if (i == 1) {}
                  for (;;)
                  {
                    return true;
                    if (i != 1) {}
                  }
                }
                return false;
              }
              catch (Exception paramMap)
              {
                paramMap.printStackTrace();
              }
            }
          }
          return false;
        }
      }
      catch (Exception paramMap)
      {
        paramMap.printStackTrace();
        return false;
      }
    }
    return false;
  }
  
  public static Object obtainStyledAttributes(TypedArray paramTypedArray, byte paramByte, int paramInt)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    if (paramTypedArray != null) {
      switch (paramByte)
      {
      }
    }
    for (localObject1 = localObject2;; localObject1 = Float.valueOf(paramTypedArray.getDimension(paramInt, -1.0F)))
    {
      paramTypedArray.recycle();
      return localObject1;
    }
  }
  
  public static Map<String, dk> parseViewPartParam(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    String[] arrayOfString = paramString.split(";");
    HashMap localHashMap = new HashMap();
    int j = arrayOfString.length;
    int i = 0;
    if (i < j)
    {
      paramString = arrayOfString[i];
      dk localdk = new dk();
      int k = paramString.indexOf(",");
      String str1;
      if (k > 0) {
        str1 = paramString.substring(0, k);
      }
      for (paramString = paramString.substring(k + 1);; paramString = null)
      {
        String str2 = str1.substring(0, 1);
        if (("H".equals(str2)) || ("F".equals(str2)) || ("B".equals(str2)))
        {
          a = getViewPartList(str1.substring(1));
          localHashMap.put(str2, localdk);
          setPartViewParamRule(localdk, paramString);
        }
        i += 1;
        break;
        str1 = paramString;
      }
    }
    return localHashMap;
  }
  
  public static int setBodyLayoutHeight(Context paramContext, ViewGroup.LayoutParams paramLayoutParams, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    }
    for (paramInt1 = -1;; paramInt1 = TYPE_VIEW_HEIGHT_11)
    {
      if (paramInt1 != -1) {
        height = paramInt1;
      }
      return paramInt1;
    }
  }
  
  public static int setBodyViewPadding(Context paramContext, View paramView1, View paramView2, dk paramdk, int paramInt)
  {
    if ((paramView1 == null) || (paramdk == null)) {
      return -1;
    }
    paramInt = getBodyViewPadding(paramContext, f);
    int i = getBodyViewPadding(paramContext, g);
    int j = getBodyViewPadding(paramContext, h);
    int k = getBodyViewPadding(paramContext, i);
    if ((paramInt != 0) || (i != 0) || (j != 0) || (k != 0)) {
      paramView1.setPadding(paramInt, i, j, k);
    }
    return 1;
  }
  
  public static int setLayoutMarginTop(Context paramContext, ViewGroup.LayoutParams paramLayoutParams, int paramInt)
  {
    if ((paramLayoutParams != null) && ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)))
    {
      paramContext = (ViewGroup.MarginLayoutParams)paramLayoutParams;
      switch (paramInt)
      {
      }
      for (paramInt = -1;; paramInt = TYPE_MARGIN_11)
      {
        if (paramInt != -1) {
          paramContext.setMargins(leftMargin, paramInt, rightMargin, bottomMargin);
        }
        return paramInt;
      }
    }
    return -1;
  }
  
  private static void setPartViewParamRule(dk paramdk, String paramString)
  {
    boolean bool2 = true;
    int i;
    if (paramString != null)
    {
      i = paramString.length();
      if (i > 0)
      {
        if (Integer.parseInt(paramString.substring(0, 1)) != 1) {
          break label205;
        }
        bool1 = true;
        b = bool1;
      }
      if (i > 1) {
        if (Integer.parseInt(paramString.substring(1, 2)) != 1) {
          break label210;
        }
      }
    }
    label205:
    label210:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      c = bool1;
      if (i > 3) {
        d = Integer.parseInt(paramString.substring(2, 4));
      }
      if (i > 5) {
        e = Integer.parseInt(paramString.substring(4, 6));
      }
      if (i > 7) {
        f = Integer.parseInt(paramString.substring(6, 8));
      }
      if (i > 9) {
        g = Integer.parseInt(paramString.substring(8, 10));
      }
      if (i > 11) {
        h = Integer.parseInt(paramString.substring(10, 12));
      }
      if (i > 13) {
        paramdk.i = Integer.parseInt(paramString.substring(12, 14));
      }
      if (i > 15) {
        j = Integer.parseInt(paramString.substring(14, 16));
      }
      return;
      bool1 = false;
      break;
    }
  }
  
  public static void setRippleDrawable(View paramView) {}
  
  public static void setViewBg(Context paramContext, View paramView, String paramString, int paramInt1, int paramInt2)
  {
    setViewBg(paramContext, paramView, paramString, paramInt1, paramInt2, false);
  }
  
  public static void setViewBg(Context paramContext, View paramView, String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    LogManager.i("setViewBg", "relativePath=" + paramString + "resId=" + paramInt1);
    try
    {
      paramContext = ViewUtil.getDrawable(paramContext, paramString, false, paramBoolean);
      if (paramContext != null)
      {
        ViewUtil.setBackground(paramView, paramContext);
        return;
      }
      if (paramInt1 == -1) {
        return;
      }
      paramView.setBackgroundResource(paramInt1);
      paramContext = (GradientDrawable)paramView.getBackground();
      if (StringUtils.isNull(paramString)) {
        return;
      }
      paramContext.setColor(ResourceCacheUtil.parseColor(paramString));
      if (paramInt2 > 0)
      {
        paramContext.setStroke(paramInt2, ResourceCacheUtil.parseColor(paramString));
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    paramContext.setColor(ResourceCacheUtil.parseColor(paramString));
  }
  
  public static void setViewBg(Context paramContext, View paramView, String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return;
      if (paramView == null) {
        continue;
      }
      try
      {
        if ((StringUtils.isNull(paramString1)) || (StringUtils.isNull(paramString2))) {
          continue;
        }
        paramContext = paramString1.trim();
        paramString1 = paramString2.trim();
        try
        {
          paramView.setBackgroundResource(paramInt1);
          paramView = (GradientDrawable)paramView.getBackground();
          if (!StringUtils.isNull(paramContext)) {
            paramView.setColor(ResourceCacheUtil.parseColor(paramContext));
          }
          if (StringUtils.isNull(paramString1)) {
            continue;
          }
          paramView.setStroke(paramInt2, ResourceCacheUtil.parseColor(paramString1));
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static void setViewTreeObserver(View paramView, XyCallBack paramXyCallBack)
  {
    try
    {
      paramView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewManger.1(paramView, paramXyCallBack));
      return;
    }
    catch (Exception paramView) {}
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.ViewManger
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */