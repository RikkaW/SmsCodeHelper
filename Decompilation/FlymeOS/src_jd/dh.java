import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class dh
{
  public List<dg> a = null;
  public List<dg> b = null;
  public List<dg> c = null;
  public BusinessSmsMessage d;
  public Activity e;
  public XyCallBack f = null;
  public ViewGroup g = null;
  public int h = 0;
  int i = 100000;
  private Map<String, dk> j = null;
  
  public dh(Activity paramActivity, XyCallBack paramXyCallBack, BusinessSmsMessage paramBusinessSmsMessage, ViewGroup paramViewGroup)
  {
    a(paramActivity, paramXyCallBack, paramBusinessSmsMessage, paramViewGroup);
    h = Math.round(Constant.getContext().getResources().getDimension(br.b.popup_content_padding));
  }
  
  public static RelativeLayout.LayoutParams a(RelativeLayout.LayoutParams paramLayoutParams, int paramInt1, int paramInt2, int... paramVarArgs)
  {
    RelativeLayout.LayoutParams localLayoutParams = paramLayoutParams;
    if (paramLayoutParams == null) {
      localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    }
    if (paramInt2 > 0) {
      localLayoutParams.addRule(paramInt1, paramInt2);
    }
    if (paramVarArgs != null)
    {
      paramInt2 = paramVarArgs.length;
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        localLayoutParams.addRule(paramVarArgs[paramInt1]);
        paramInt1 += 1;
      }
    }
    return localLayoutParams;
  }
  
  private void a(String paramString, List<dg> paramList, di paramdi)
  {
    int n;
    if (paramList != null)
    {
      n = paramList.size();
      if (n > 0) {
        break label18;
      }
    }
    for (;;)
    {
      return;
      label18:
      paramdi = (dk)j.get(paramString);
      boolean bool = "B".equals(paramString);
      int m = i + 1;
      int k = 0;
      while (k < n)
      {
        paramString = (dg)paramList.get(k);
        paramString.d();
        RelativeLayout.LayoutParams localLayoutParams = a(e, i);
        e.setId(m);
        g.addView(e, localLayoutParams);
        a(e, paramdi, paramString);
        if ((e != null) && (paramdi != null) && (k > 0)) {
          ViewManger.setLayoutMarginTop(Constant.getContext(), e.getLayoutParams(), j);
        }
        if (bool) {
          ViewManger.setBodyViewPadding(Constant.getContext(), e, e, paramdi, h);
        }
        i = m;
        m += 1;
        k += 1;
      }
    }
  }
  
  public RelativeLayout.LayoutParams a(View paramView, int paramInt)
  {
    paramView = paramView.getLayoutParams();
    if ((paramView != null) && ((paramView instanceof RelativeLayout.LayoutParams))) {}
    for (paramView = (RelativeLayout.LayoutParams)paramView;; paramView = null)
    {
      if (paramInt == 100000) {}
      for (paramView = a(paramView, -1, -1, new int[] { 10 }); d.viewType == 0; paramView = a(paramView, 3, paramInt, new int[0]))
      {
        paramView.addRule(14);
        return paramView;
      }
      paramView.addRule(5);
      return paramView;
    }
  }
  
  public List<dg> a(String paramString, Map<String, dk> paramMap)
  {
    paramString = (dk)paramMap.get(paramString);
    if ((paramString == null) || (a == null)) {
      return null;
    }
    int m = a.size();
    paramMap = new ArrayList();
    int k = 0;
    while (k < m)
    {
      dg localdg = ViewManger.getUIPartByPartId(e, d, f, g, ((Integer)a.get(k)).intValue());
      if (localdg != null) {
        paramMap.add(localdg);
      }
      k += 1;
    }
    return paramMap;
  }
  
  public void a(Activity paramActivity, XyCallBack paramXyCallBack, BusinessSmsMessage paramBusinessSmsMessage, ViewGroup paramViewGroup)
  {
    e = paramActivity;
    f = paramXyCallBack;
    d = paramBusinessSmsMessage;
    g = paramViewGroup;
    try
    {
      j = ((Map)paramBusinessSmsMessage.getValue("viewPartParam"));
      if (j == null)
      {
        Log.e("duoqu_xiaoyuan", "BaseCompriseBubbleView.initData mPartViewMap is null.");
        return;
      }
      a = a("H", j);
      b = a("B", j);
      c = a("F", j);
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public void a(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean)
  {
    d = paramBusinessSmsMessage;
    Iterator localIterator;
    dg localdg;
    if (a != null)
    {
      localIterator = a.iterator();
      while (localIterator.hasNext())
      {
        localdg = (dg)localIterator.next();
        f = paramActivity;
        localdg.a(paramBusinessSmsMessage, paramBoolean);
      }
    }
    if (b != null)
    {
      localIterator = b.iterator();
      while (localIterator.hasNext())
      {
        localdg = (dg)localIterator.next();
        f = paramActivity;
        localdg.a(paramBusinessSmsMessage, paramBoolean);
      }
    }
    if (c != null)
    {
      localIterator = c.iterator();
      while (localIterator.hasNext())
      {
        localdg = (dg)localIterator.next();
        f = paramActivity;
        localdg.a(paramBusinessSmsMessage, paramBoolean);
      }
    }
  }
  
  public void a(View paramView, dk paramdk, dg paramdg)
  {
    if (paramView == null) {
      break label4;
    }
    label4:
    while (paramdg == null) {
      return;
    }
    paramdk = (Integer)paramdg.a("MLR");
    if (paramdk != null) {}
    for (int k = ViewManger.getInnerLayoutMargin(Constant.getContext(), paramdk.intValue());; k = 0)
    {
      paramdk = (Integer)paramdg.a("H");
      if (paramdk != null) {
        getLayoutParamsheight = paramdk.intValue();
      }
      paramdk = (Integer)paramdg.a("MTPO");
      if ((paramdk != null) && ((paramView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))) {
        getLayoutParamstopMargin = paramdk.intValue();
      }
      if ((k > 0) && ((paramView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)))
      {
        paramdk = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
        leftMargin = k;
        rightMargin = k;
      }
      if ((paramdg instanceof co)) {
        ((co)paramdg).b();
      }
      if (!(paramView instanceof er)) {
        break;
      }
      k = ViewManger.getDouquAttrDimen((er)paramView, br.h.duoqu_attr_duoqu_height);
      if (k <= 0) {
        break;
      }
      getLayoutParamsheight = k;
      return;
    }
  }
  
  public void a(ViewGroup paramViewGroup, di paramdi)
  {
    if (paramViewGroup != null) {
      g = paramViewGroup;
    }
    a("H", a, paramdi);
    a("B", b, paramdi);
    a("F", c, paramdi);
  }
}

/* Location:
 * Qualified Name:     dh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */