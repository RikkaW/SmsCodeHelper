package com.android.mms.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import ga;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MmsTextViewSnippet
  extends TextView
{
  private static String a = "‥";
  private int b = 0;
  private String c;
  private String d;
  private Pattern e;
  private boolean f;
  
  public MmsTextViewSnippet(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public MmsTextViewSnippet(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public MmsTextViewSnippet(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void a(String paramString1, String paramString2)
  {
    e = Pattern.compile(Pattern.quote(paramString2), 2);
    f = true;
    c = paramString1;
    d = paramString2;
    super.setText(paramString1);
  }
  
  public void b(String paramString1, String paramString2)
  {
    e = Pattern.compile(Pattern.quote(paramString2), 2);
    f = true;
    c = paramString1;
    d = paramString2;
    super.setText(paramString1);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((!f) || (c == null) || (d == null))
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    Object localObject2 = c;
    Matcher localMatcher = e.matcher(c);
    TextPaint localTextPaint = getPaint();
    paramInt1 = getWidth();
    paramInt2 = getMaxLines() * paramInt1;
    Object localObject1 = localObject2;
    if (localTextPaint.measureText(c) > paramInt2)
    {
      localObject1 = localObject2;
      if (localMatcher.find(0))
      {
        paramInt1 = localMatcher.start();
        if (localTextPaint.measureText(c.substring(paramInt1, c.length())) <= paramInt2) {
          break label247;
        }
        paramInt1 = Math.max(0, localMatcher.start() - 5);
        localObject2 = c.substring(paramInt1, c.length());
        localObject1 = localObject2;
        if (paramInt1 > 0) {
          localObject1 = a + (String)localObject2;
        }
      }
    }
    localObject2 = new SpannableString((CharSequence)localObject1);
    localObject1 = e.matcher((CharSequence)localObject1);
    for (paramInt1 = 0;; paramInt1 = ((Matcher)localObject1).end())
    {
      if ((!((Matcher)localObject1).find(paramInt1)) || (((Matcher)localObject1).start() == ((Matcher)localObject1).end()))
      {
        setText((CharSequence)localObject2);
        return;
        label247:
        paramInt3 = b;
        for (;;)
        {
          localObject1 = localObject2;
          if (paramInt1 < 0) {
            break;
          }
          if ((c.charAt(paramInt1) == '\n') || (localTextPaint.measureText(c.substring(paramInt1, c.length())) + localTextPaint.measureText(a) > paramInt2 - paramInt3))
          {
            paramInt1 += 1;
            localObject2 = c.substring(paramInt1, c.length());
            localObject1 = localObject2;
            if (paramInt1 <= 0) {
              break;
            }
            localObject1 = a + (String)localObject2;
            break;
          }
          paramInt1 -= 1;
        }
      }
      ((SpannableString)localObject2).setSpan(new ForegroundColorSpan(ga.a), ((Matcher)localObject1).start(), ((Matcher)localObject1).end(), 0);
    }
  }
  
  public void setText(String paramString)
  {
    f = false;
    super.setText(paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsTextViewSnippet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */