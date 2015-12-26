package cn.com.xy.sms.sdk.ui.popu.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import br.c;
import br.h;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import eq;
import java.util.ArrayList;
import java.util.List;

public class DuoquHorizItemTable
  extends DuoquBaseTable
{
  private int d = 0;
  private int e = 0;
  private int f = 0;
  private int g = 0;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  private int k = 0;
  private int l = 0;
  private int m = 0;
  private String n = null;
  
  public DuoquHorizItemTable(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }
  
  public RelativeLayout.LayoutParams a(int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams;
    if (paramInt % 3 - 2 == 0)
    {
      localLayoutParams = new RelativeLayout.LayoutParams(m, i);
      localLayoutParams.setMargins(j, 0, k, 0);
      if ((paramInt == 3) || (paramInt == 2)) {
        localLayoutParams.addRule(10);
      }
      if (paramInt % 3 - 2 != 0) {
        break label117;
      }
      localLayoutParams.addRule(9);
    }
    for (;;)
    {
      if (paramInt - 3 > 0) {
        localLayoutParams.addRule(3, (paramInt - 1) / 3 * 3 + 1);
      }
      return localLayoutParams;
      localLayoutParams = new RelativeLayout.LayoutParams(-1, i);
      localLayoutParams.setMargins(0, 0, j, 0);
      break;
      label117:
      localLayoutParams.addRule(1, paramInt - 1);
      localLayoutParams.addRule(11);
    }
  }
  
  @SuppressLint({"ResourceAsColor"})
  protected void a(int paramInt1, BusinessSmsMessage paramBusinessSmsMessage, int paramInt2, String paramString, boolean paramBoolean)
  {
    eq localeq = new eq();
    a = new TextView(getContext());
    d = new TextView(getContext());
    e = new ImageView(getContext());
    Object localObject = e;
    paramInt2 = a + 1;
    a = paramInt2;
    ((ImageView)localObject).setId(paramInt2);
    e.setBackgroundResource(br.c.duoqu_dotted_split);
    localObject = new RelativeLayout.LayoutParams(-1, 3);
    ((RelativeLayout.LayoutParams)localObject).setMargins(j, 0, j, 0);
    if (1 == a) {
      ((RelativeLayout.LayoutParams)localObject).addRule(10);
    }
    for (;;)
    {
      localObject = a;
      paramInt2 = a + 1;
      a = paramInt2;
      ((TextView)localObject).setId(paramInt2);
      a.setGravity(16);
      localObject = a(a);
      addView(a, (ViewGroup.LayoutParams)localObject);
      localObject = d;
      paramInt2 = a + 1;
      a = paramInt2;
      ((TextView)localObject).setId(paramInt2);
      d.setGravity(21);
      localObject = a(a);
      addView(d, (ViewGroup.LayoutParams)localObject);
      a.setTextSize(0, d);
      d.setTextSize(0, e);
      if (f != Integer.MIN_VALUE) {
        a.setTextColor(f);
      }
      if ("true".equals(n))
      {
        d.setSingleLine();
        d.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        a.setSingleLine();
        a.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
      }
      localeq.a(paramInt1, paramBusinessSmsMessage, paramString, paramBoolean);
      c.add(localeq);
      return;
      ((RelativeLayout.LayoutParams)localObject).addRule(3, a - 2);
      addView(e, (ViewGroup.LayoutParams)localObject);
    }
  }
  
  protected void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, br.h.duoqu_table_attr);
    d = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_title_textsize, 0.0F));
    e = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_content_textsize, 0.0F));
    f = paramContext.getResourceId(br.h.duoqu_table_attr_title_textcolor, Integer.MIN_VALUE);
    g = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_title_paddingtop, 0.0F));
    h = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_content_paddingleft, 0.0F));
    i = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_line_spacing, 0.0F));
    j = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_line_spacing_left, 0.0F));
    k = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_line_spacing_right, 0.0F));
    n = paramContext.getString(br.h.duoqu_table_attr_single_line);
    l = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_margin_top, 0.0F));
    m = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_title_width, 0.0F));
    paramContext.recycle();
    if (l != 0)
    {
      paramContext = new RelativeLayout.LayoutParams(-1, -2);
      paramContext.setMargins(0, l, 0, 0);
      setLayoutParams(paramContext);
    }
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, int paramInt, String paramString, boolean paramBoolean)
  {
    int i2 = 5;
    int i1 = 0;
    if (paramInt == 0) {}
    for (;;)
    {
      try
      {
        setVisibility(8);
        return;
      }
      catch (Exception paramBusinessSmsMessage)
      {
        paramBusinessSmsMessage.printStackTrace();
        return;
      }
      setVisibility(0);
      c = new ArrayList();
      a = 0;
      Log.i("getHolder", "smsId=" + smsId + "  dataSize=" + paramInt);
      removeAllViews();
      if (i1 < paramInt)
      {
        a(i1, paramBusinessSmsMessage, paramInt, paramString, false);
        i1 += 1;
      }
      else
      {
        do
        {
          break;
          return;
        } while (paramInt <= 5);
        paramInt = i2;
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.widget.DuoquHorizItemTable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */