package cn.com.xy.sms.sdk.ui.popu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import br.c;
import br.h;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import eo;
import ep;
import java.util.ArrayList;
import java.util.List;

public class DuoquHorizLFItemTable
  extends DuoquBaseTable
{
  private int d = 0;
  private int e = 0;
  private int f = 0;
  private int g = 0;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  private String k = null;
  private int l = 0;
  private int m = 0;
  private int n = 0;
  private int o = 0;
  private int p = 0;
  
  public DuoquHorizLFItemTable(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }
  
  private RelativeLayout.LayoutParams b(int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    if (paramInt > 1001)
    {
      localLayoutParams.addRule(3, b - 1);
      return localLayoutParams;
    }
    localLayoutParams.addRule(10);
    return localLayoutParams;
  }
  
  protected RelativeLayout.LayoutParams a(int paramInt)
  {
    if (paramInt % 4 == 1)
    {
      localLayoutParams = new RelativeLayout.LayoutParams(p, h);
      localLayoutParams.addRule(9);
      localLayoutParams.addRule(10);
      localLayoutParams.setMargins(0, 0, m, 0);
      return localLayoutParams;
    }
    if ((paramInt % 4 == 2) || (paramInt % 4 == 3))
    {
      localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams.addRule(1, paramInt - 1);
      localLayoutParams.addRule(11);
      localLayoutParams.addRule(10);
      return localLayoutParams;
    }
    if (paramInt % 4 == 3)
    {
      localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams.addRule(1, paramInt - 2);
      localLayoutParams.addRule(11);
      localLayoutParams.addRule(10);
      return localLayoutParams;
    }
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, h);
    localLayoutParams.addRule(1, paramInt - 3);
    localLayoutParams.addRule(11);
    localLayoutParams.addRule(10);
    return localLayoutParams;
  }
  
  protected void a(int paramInt1, BusinessSmsMessage paramBusinessSmsMessage, int paramInt2, String paramString, boolean paramBoolean)
  {
    ep localep = new ep();
    f = new RelativeLayout(getContext());
    a = new TextView(getContext());
    b = new TextView(getContext());
    c = new TextView(getContext());
    d = new TextView(getContext());
    e = new ImageView(getContext());
    Object localObject = a;
    paramInt2 = a + 1;
    a = paramInt2;
    ((TextView)localObject).setId(paramInt2);
    a.setGravity(16);
    a.setSingleLine(true);
    a.setEllipsize(TextUtils.TruncateAt.END);
    localObject = a(a);
    ((RelativeLayout.LayoutParams)localObject).addRule(15, -1);
    f.addView(a, (ViewGroup.LayoutParams)localObject);
    localObject = b;
    paramInt2 = a + 1;
    a = paramInt2;
    ((TextView)localObject).setId(paramInt2);
    b.setGravity(21);
    b.setPadding(0, n, 0, n);
    localObject = a(a);
    b.setLineSpacing(ContentUtil.HORIZ_TABLE_TITLE_LINE_SPACING, 1.0F);
    b.setMinHeight(h);
    b.setVisibility(8);
    f.addView(b, (ViewGroup.LayoutParams)localObject);
    localObject = c;
    paramInt2 = a + 1;
    a = paramInt2;
    ((TextView)localObject).setId(paramInt2);
    c.setGravity(21);
    c.setPadding(0, n, 0, n);
    localObject = a(a);
    c.setLineSpacing(ContentUtil.HORIZ_TABLE_TITLE_LINE_SPACING, 1.0F);
    c.setMinHeight(h);
    c.setVisibility(8);
    c.setMaxLines(2);
    c.setEllipsize(TextUtils.TruncateAt.END);
    f.addView(c, (ViewGroup.LayoutParams)localObject);
    localObject = d;
    paramInt2 = a + 1;
    a = paramInt2;
    ((TextView)localObject).setId(paramInt2);
    d.setGravity(21);
    localObject = a(a);
    d.setSingleLine(true);
    d.setEllipsize(TextUtils.TruncateAt.END);
    d.setMinHeight(h);
    d.setVisibility(8);
    f.addView(d, (ViewGroup.LayoutParams)localObject);
    localObject = e;
    paramInt2 = b + 1;
    b = paramInt2;
    ((ImageView)localObject).setId(paramInt2);
    e.setBackgroundResource(br.c.duoqu_dotted_split);
    localObject = new RelativeLayout.LayoutParams(-1, 3);
    ((RelativeLayout.LayoutParams)localObject).setMargins(l, 0, l, 0);
    if (1001 == b) {
      ((RelativeLayout.LayoutParams)localObject).addRule(10);
    }
    for (;;)
    {
      localObject = f;
      paramInt2 = b + 1;
      b = paramInt2;
      ((RelativeLayout)localObject).setId(paramInt2);
      f.setPadding(l, 0, l, 0);
      addView(f, b(b));
      a.setTextSize(0, d);
      b.setTextSize(0, e);
      localep.a(paramInt1, paramBusinessSmsMessage, paramString, paramBoolean);
      c.add(localep);
      return;
      ((RelativeLayout.LayoutParams)localObject).addRule(3, b - 1);
      addView(e, (ViewGroup.LayoutParams)localObject);
    }
  }
  
  protected void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, br.h.duoqu_table_attr);
    d = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_title_textsize, 0.0F));
    j = paramContext.getResourceId(br.h.duoqu_table_attr_title_textcolor, Integer.MIN_VALUE);
    e = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_content_textsize, 0.0F));
    f = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_title_paddingtop, 0.0F));
    g = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_content_paddingleft, 0.0F));
    h = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_line_spacing, 0.0F));
    k = paramContext.getString(br.h.duoqu_table_attr_single_line);
    i = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_margin_top, 0.0F));
    l = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_line_spacing_left, 0.0F));
    m = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_line_spacing_right, 0.0F));
    n = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_line_spacing_top, 0.0F));
    o = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_line_spacing_top_bigsize, 0.0F));
    p = Math.round(paramContext.getDimension(br.h.duoqu_table_attr_title_width, 0.0F));
    paramContext.recycle();
    if (i != 0)
    {
      paramContext = new RelativeLayout.LayoutParams(-1, -2);
      paramContext.setMargins(0, i, 0, 0);
      setLayoutParams(paramContext);
    }
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, int paramInt, String paramString, boolean paramBoolean)
  {
    int i4 = 5;
    int i1 = 0;
    int i3 = 0;
    int i2 = 0;
    List localList;
    label63:
    eo localeo;
    if (paramInt == 0)
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
      localList = c;
      if ((paramBoolean) && (localList != null))
      {
        i3 = localList.size();
        if (i3 > paramInt)
        {
          i1 = i2;
          if (i1 >= i3) {
            break label238;
          }
          localeo = (eo)localList.get(i1);
          if (i1 < paramInt)
          {
            localeo.a(0);
            localeo.a(i1, paramBusinessSmsMessage, paramString, paramBoolean);
            break label250;
          }
          localeo.a(8);
          break label250;
        }
      }
    }
    for (;;)
    {
      if (i1 < paramInt)
      {
        if (i1 < i3)
        {
          localeo = (eo)localList.get(i1);
          localeo.a(0);
          localeo.a(i1, paramBusinessSmsMessage, paramString, paramBoolean);
        }
        else
        {
          a(i1, paramBusinessSmsMessage, paramInt, paramString, false);
          break label259;
          c = new ArrayList();
          a = 0;
          removeAllViews();
          i1 = i3;
          while (i1 < paramInt)
          {
            a(i1, paramBusinessSmsMessage, paramInt, paramString, false);
            i1 += 1;
          }
        }
      }
      else
      {
        label238:
        do
        {
          break;
          return;
        } while (paramInt <= 5);
        paramInt = i4;
        break;
        label250:
        i1 += 1;
        break label63;
      }
      label259:
      i1 += 1;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.widget.DuoquHorizLFItemTable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */