package com.android.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import gm;

public final class MessagingNotification$b
{
  public final Intent a;
  public final String b;
  public final CharSequence c;
  public final long d;
  public final String e;
  public final Bitmap f;
  public final gm g;
  public final boolean h;
  public final int i;
  public final String j;
  public final long k;
  public final Uri l;
  public final int m;
  
  public MessagingNotification$b(boolean paramBoolean, Intent paramIntent, String paramString1, String paramString2, CharSequence paramCharSequence, long paramLong1, String paramString3, Bitmap paramBitmap, gm paramgm, int paramInt1, long paramLong2, Uri paramUri, int paramInt2)
  {
    h = paramBoolean;
    a = paramIntent;
    b = paramString1;
    j = paramString2;
    c = paramCharSequence;
    d = paramLong1;
    e = paramString3;
    f = null;
    g = paramgm;
    i = paramInt1;
    k = paramLong2;
    l = paramUri;
    m = paramInt2;
  }
  
  public long a()
  {
    return d;
  }
  
  public CharSequence a(Context paramContext)
  {
    if (!TextUtils.isEmpty(b)) {
      b.replaceAll("\\n\\s+", "\n");
    }
    for (;;)
    {
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
      if (!TextUtils.isEmpty(j)) {
        localSpannableStringBuilder.append(j);
      }
      if (i > 0)
      {
        if (localSpannableStringBuilder.length() > 0) {
          localSpannableStringBuilder.append('\n');
        }
        localSpannableStringBuilder.append(MessagingNotification.d(paramContext, i));
      }
      if (b != null)
      {
        if (localSpannableStringBuilder.length() > 0) {
          localSpannableStringBuilder.append('\n');
        }
        localSpannableStringBuilder.append(b);
      }
      return localSpannableStringBuilder;
    }
  }
  
  public CharSequence b(Context paramContext)
  {
    if (!TextUtils.isEmpty(b)) {}
    for (String str1 = b.replaceAll("\\n\\s+", "\n");; str1 = "")
    {
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
      String str2 = g.g();
      if (!TextUtils.isEmpty(str2)) {
        localSpannableStringBuilder.append(str2);
      }
      str2 = paramContext.getString(2131493030);
      if (!h)
      {
        if (!TextUtils.isEmpty(j))
        {
          if (localSpannableStringBuilder.length() > 0) {
            localSpannableStringBuilder.append(str2);
          }
          localSpannableStringBuilder.length();
          localSpannableStringBuilder.append(j);
        }
        if (i > 0)
        {
          if (localSpannableStringBuilder.length() > 0) {
            localSpannableStringBuilder.append(str2);
          }
          localSpannableStringBuilder.append(MessagingNotification.d(paramContext, i));
        }
      }
      if (str1.length() > 0)
      {
        if (localSpannableStringBuilder.length() > 0) {
          localSpannableStringBuilder.append(str2);
        }
        localSpannableStringBuilder.length();
        localSpannableStringBuilder.append(str1);
      }
      return localSpannableStringBuilder;
    }
  }
  
  public CharSequence c(Context paramContext)
  {
    TextAppearanceSpan localTextAppearanceSpan = new TextAppearanceSpan(paramContext, 2131624181);
    if (!TextUtils.isEmpty(b)) {}
    for (paramContext = b.replaceAll("\\n\\s+", "\n");; paramContext = "")
    {
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
      if (!TextUtils.isEmpty(j))
      {
        localSpannableStringBuilder.append(j);
        localSpannableStringBuilder.setSpan(localTextAppearanceSpan, 0, j.length(), 0);
      }
      if ((paramContext.length() > 0) && (localSpannableStringBuilder.length() == 0))
      {
        localSpannableStringBuilder.append(paramContext);
        localSpannableStringBuilder.setSpan(localTextAppearanceSpan, 0, paramContext.length(), 0);
      }
      return localSpannableStringBuilder;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */