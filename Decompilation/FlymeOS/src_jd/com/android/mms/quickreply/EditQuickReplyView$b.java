package com.android.mms.quickreply;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

class EditQuickReplyView$b
  implements InputFilter
{
  private int b;
  
  public EditQuickReplyView$b(EditQuickReplyView paramEditQuickReplyView, int paramInt)
  {
    b = paramInt;
  }
  
  private int a(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      paramCharSequence = paramCharSequence.subSequence(paramInt1, paramInt2);
      if (!TextUtils.isEmpty(paramCharSequence)) {}
    }
    else
    {
      return 0;
    }
    return paramCharSequence.length() + EditQuickReplyView.a(a, paramCharSequence);
  }
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    int i = 0;
    int j = b - (paramSpanned.length() + EditQuickReplyView.a(a, paramSpanned) - (EditQuickReplyView.a(a, paramSpanned.subSequence(paramInt3, paramInt4)) + paramInt4 - paramInt3));
    if (j <= 0)
    {
      if (!TextUtils.isEmpty(paramCharSequence)) {
        EditQuickReplyView.b(a).c();
      }
      return "";
    }
    if (j >= a(paramCharSequence, paramInt1, paramInt2)) {
      return null;
    }
    paramInt4 = 0;
    paramInt3 = i;
    while (paramInt4 < paramInt2)
    {
      if (EditQuickReplyView.a(a, paramCharSequence.charAt(paramInt4))) {
        paramInt3 += 2;
      }
      while (paramInt3 >= j)
      {
        return paramCharSequence.subSequence(paramInt1, paramInt4 + 1 + paramInt1);
        paramInt3 += 1;
      }
      paramInt4 += 1;
    }
    return paramCharSequence.subSequence(paramInt1, j + paramInt1);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.quickreply.EditQuickReplyView.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */