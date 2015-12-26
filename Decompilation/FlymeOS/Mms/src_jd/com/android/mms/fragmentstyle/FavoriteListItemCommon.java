package com.android.mms.fragmentstyle;

import aaa;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.view.MmsTextViewSnippet;
import gm;
import gm.b;
import gq;
import gr;
import java.util.Iterator;
import jw;
import vv;
import wd;

public class FavoriteListItemCommon
  extends LinearLayout
  implements gm.b
{
  private MmsTextViewSnippet a;
  private TextView b;
  private gr c;
  private gq d;
  private String e;
  private Handler f = new Handler();
  private Context g;
  private boolean h;
  
  public FavoriteListItemCommon(Context paramContext)
  {
    super(paramContext);
    g = paramContext;
  }
  
  public FavoriteListItemCommon(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    g = paramContext;
  }
  
  public FavoriteListItemCommon(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    g = paramContext;
  }
  
  private CharSequence a(gq paramgq)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    if (h) {
      localSpannableStringBuilder.append(getContext().getResources().getString(2131493713));
    }
    int j = paramgq.size();
    paramgq = paramgq.iterator();
    int i = 0;
    for (;;)
    {
      if (paramgq.hasNext())
      {
        localSpannableStringBuilder.append(((gm)paramgq.next()).g());
        if (i < j - 1) {
          localSpannableStringBuilder.append(", ");
        }
        i += 1;
        if (i < 8) {}
      }
      else
      {
        return localSpannableStringBuilder;
      }
    }
  }
  
  private void a()
  {
    if (!TextUtils.isEmpty(e)) {
      a(e);
    }
    for (;;)
    {
      a.setText(a(d));
      return;
      d = c.h();
    }
  }
  
  private void a(String paramString)
  {
    d = null;
    if ((!TextUtils.isEmpty(paramString)) && (!TextUtils.isEmpty(PhoneNumberUtils.replaceUnicodeDigits(paramString))))
    {
      d = gq.a(PhoneNumberUtils.replaceUnicodeDigits(paramString), false, true);
      return;
    }
    d = new gq();
  }
  
  public void a(gm paramgm)
  {
    if ((d != null) && (d.contains(paramgm))) {
      f.post(new jw(this));
    }
  }
  
  public void a(vv paramvv)
  {
    String str = W;
    h = paramvv.B();
    b.setText(wd.a(g, ab));
    if (!TextUtils.isEmpty(str))
    {
      e = str;
      a(str);
    }
    while ((d == null) || (d.size() <= 0))
    {
      do
      {
        return;
        c = gr.a(MmsApp.c(), f, false);
      } while (c == null);
      d = c.h();
    }
    a.setText(a(d));
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    a.setMaxWidth(getResources().getDimensionPixelSize(2131559010));
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(g, 2130968661, this);
    a = ((MmsTextViewSnippet)findViewById(16908308));
    aaa.a(a);
    b = ((TextView)findViewById(2131886451));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteListItemCommon
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */