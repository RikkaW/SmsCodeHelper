package com.android.mms.ui;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class SearchFragment$TextViewSnippet
  extends TextView
{
  private String mFullText;
  private int mHlAppearance;
  private String mTargetString;
  private String[] mTargetTokens;
  
  public SearchFragment$TextViewSnippet(Context paramContext)
  {
    super(paramContext);
  }
  
  public SearchFragment$TextViewSnippet(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public SearchFragment$TextViewSnippet(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    String str1 = mTargetTokens[0].toLowerCase();
    Object localObject = mFullText.toLowerCase();
    String str2 = str1.toLowerCase();
    int j = ((String)localObject).indexOf(str1);
    if ((mHlAppearance == 0) || (j == -1))
    {
      setText(mFullText);
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    int i = str2.length();
    int m = ((String)localObject).length();
    if (i > m)
    {
      setText(mFullText);
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    localObject = getPaint();
    float f1 = ((TextPaint)localObject).measureText(str1);
    float f2 = ((TextPaint)localObject).measureText(mFullText);
    float f3 = getWidth();
    str1 = mFullText;
    if (f1 > f3) {}
    for (str1 = mFullText.substring(j, j + i);; str1 = mFullText)
    {
      MessageUtils.showTextWithHighlight(this, str1, mTargetString, mHlAppearance);
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
      if (f2 > f3) {
        break;
      }
    }
    float f4 = ((TextPaint)localObject).measureText("…");
    i = j + mTargetTokens[0].length();
    f1 = ((TextPaint)localObject).measureText(mFullText, j, i);
    while ((f1 < f3 - 2.0F * f4) && ((j > 0) || (i < m)))
    {
      int k = j;
      f2 = f1;
      if (j > 0)
      {
        k = j - 1;
        f2 = f1 + ((TextPaint)localObject).measureText(mFullText, k, k + 1);
      }
      j = k;
      f1 = f2;
      if (i < m)
      {
        i += 1;
        f1 = f2 + ((TextPaint)localObject).measureText(mFullText, i - 1, i);
        j = k;
      }
    }
    if (j == 0)
    {
      str1 = "";
      label375:
      str2 = mFullText.substring(j, i);
      if (i != m) {
        break label435;
      }
    }
    label435:
    for (localObject = "";; localObject = "…")
    {
      str1 = String.format("%s%s%s", new Object[] { str1, str2, localObject });
      break;
      str1 = "…";
      break label375;
    }
  }
  
  public void setText(String paramString1, String paramString2, int paramInt)
  {
    mFullText = paramString1;
    mTargetString = paramString2;
    mTargetTokens = paramString2.split(" +");
    mHlAppearance = paramInt;
    requestLayout();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchFragment.TextViewSnippet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */