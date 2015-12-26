import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewStub;
import cn.com.xy.sms.sdk.ui.popu.util.XySdkUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class nm
  implements SdkCallBack
{
  private nk a = null;
  private ViewGroup b = null;
  private View c = null;
  private nj d = null;
  private nl e = null;
  private JSONObject f = null;
  private Activity g;
  private int h;
  
  public nm(nk paramnk, nl paramnl)
  {
    a = paramnk;
    e = paramnl;
    if ((e == null) || (paramnk == null)) {
      return;
    }
    g = paramnk.e();
    e();
  }
  
  private void a(int paramInt)
  {
    try
    {
      if (f == null) {
        return;
      }
      f.put("DISPLAY", paramInt);
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }
  
  private void a(long paramLong, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return;
    }
    if (!paramJSONObject.has("DISPLAY")) {
      a(h);
    }
    XySdkUtil.putBubbleDataToCache(Long.valueOf(paramLong), paramJSONObject);
  }
  
  private boolean a(boolean paramBoolean)
  {
    long l = d.a();
    Object localObject = d.b();
    if (!a.f()) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      ((HashMap)localObject).put("isClickAble", Boolean.valueOf(paramBoolean));
      localObject = cf.a(g, f, String.valueOf(l), d.f(), d.c(), e.getListItemView(), a.g(), (HashMap)localObject);
      if ((localObject == null) || (b == null)) {
        break;
      }
      b.removeAllViews();
      ViewParent localViewParent = ((View)localObject).getParent();
      if ((localViewParent instanceof ViewGroup)) {
        ((ViewGroup)localViewParent).removeView((View)localObject);
      }
      b.addView((View)localObject);
      d();
      a(2);
      d.a(true);
      e.a(b);
      d.a(b);
      return true;
    }
    a(-1);
    i();
    d.a(false);
    return true;
  }
  
  private void b()
  {
    if ((a == null) || (d == null))
    {
      Log.w("XYBubbleListItem", "com.android.mms.ui.SmartSmsBubbleManager.bindRichBubbleView mSmartSmsUiHolder or mMessageItem is null");
      return;
    }
    if (b == null)
    {
      Log.w("XYBubbleListItem", "com.android.mms.ui.SmartSmsBubbleManager.bindRichBubbleView mRichItemGroup  is null");
      return;
    }
    f = XySdkUtil.getBubbleDataFromCache(Long.valueOf(d.a()));
    if (f == null)
    {
      g();
      return;
    }
    if (f() == 2)
    {
      a(false);
      return;
    }
    b(false);
  }
  
  private void b(boolean paramBoolean)
  {
    if (e != null) {
      e.l_();
    }
    a();
  }
  
  private void c()
  {
    if (c != null) {
      c.setVisibility(0);
    }
    if (b != null) {
      b.setVisibility(8);
    }
  }
  
  private void c(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    ViewGroup.LayoutParams localLayoutParams = b.getLayoutParams();
    if (paramBoolean) {}
    for (width = g.getResources().getDimensionPixelSize(2131558810);; width = g.getResources().getDimensionPixelSize(2131558811))
    {
      b.setLayoutParams(localLayoutParams);
      return;
    }
  }
  
  private void d()
  {
    if (c != null) {
      c.setVisibility(8);
    }
    if (b != null) {
      b.setVisibility(0);
    }
  }
  
  private void e()
  {
    b = ((ViewGroup)((ViewStub)e.findViewById(2131886607)).inflate());
    c = e.findViewById(2131886454);
  }
  
  private int f()
  {
    try
    {
      if (f == null) {
        return -1;
      }
      if (!f.has("DISPLAY")) {
        return 2;
      }
      int i = f.getInt("DISPLAY");
      return i;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return 1;
  }
  
  private void g()
  {
    String str = String.valueOf(d.a());
    b.setTag(str);
    cf.a(g, str, d.c(), d.d(), d.f(), d.e(), (byte)1, e.getListItemView(), null, b, a.g(), d.b(), this, h());
  }
  
  private boolean h()
  {
    if ((a == null) || (a.g() == null)) {
      return false;
    }
    return a.h();
  }
  
  private void i()
  {
    f = null;
    b(false);
  }
  
  public void a()
  {
    c();
  }
  
  public void a(nj paramnj, int paramInt, boolean paramBoolean)
  {
    if ((paramnj == null) || (a == null)) {
      return;
    }
    d = paramnj;
    h = paramInt;
    c(paramBoolean);
    b();
  }
  
  public void execute(Object... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length == 0) || (a == null)) {
      i();
    }
    do
    {
      String str1;
      String str2;
      do
      {
        return;
        if (paramVarArgs.length <= 2) {
          break;
        }
        str1 = (String)paramVarArgs[2];
        str2 = (String)b.getTag();
      } while ((StringUtils.isNull(str2)) || (StringUtils.isNull(str1)) || (!str2.equals(str1)));
      switch (((Integer)paramVarArgs[0]).intValue())
      {
      case -3: 
      default: 
        return;
      case -4: 
        i();
        return;
      case -2: 
        i();
        return;
      case -1: 
        i();
        return;
      case 0: 
        f = ((JSONObject)paramVarArgs[1]);
        if ((h == 2) && (f() == 2)) {
          a(false);
        }
        for (;;)
        {
          a(d.a(), f);
          return;
          b(false);
        }
      }
    } while (g == null);
    f = ((JSONObject)paramVarArgs[1]);
    a(d.a(), f);
  }
}

/* Location:
 * Qualified Name:     nm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */