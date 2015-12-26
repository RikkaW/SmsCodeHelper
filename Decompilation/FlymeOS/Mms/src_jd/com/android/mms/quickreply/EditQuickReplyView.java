package com.android.mms.quickreply;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView.OnEditorActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mn;
import mo;
import mp;

public class EditQuickReplyView
  extends LinearLayout
{
  private EditText a;
  private int b = 0;
  private int c;
  private a d;
  private int e;
  private final View.OnFocusChangeListener f = new mn(this);
  private final TextView.OnEditorActionListener g = new mo(this);
  private final TextWatcher h = new mp(this);
  
  public EditQuickReplyView(Context paramContext)
  {
    super(paramContext);
  }
  
  public EditQuickReplyView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public EditQuickReplyView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private int a(CharSequence paramCharSequence)
  {
    int i = 0;
    int j = 0;
    if (TextUtils.isEmpty(paramCharSequence)) {
      return j;
    }
    paramCharSequence = Pattern.compile("[一-龥]").matcher(paramCharSequence);
    for (;;)
    {
      j = i;
      if (!paramCharSequence.find()) {
        break;
      }
      i += 1;
    }
  }
  
  private void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      e = 0;
      return;
    }
    e = 1;
  }
  
  private boolean a(char paramChar)
  {
    return (paramChar >= '一') && (paramChar <= 40869);
  }
  
  public void a()
  {
    a.requestFocus();
    EditText localEditText = a;
    if (a.getText() == null) {}
    for (int i = 0;; i = a.getText().length())
    {
      localEditText.setSelection(0, i);
      return;
    }
  }
  
  public void b() {}
  
  public int getIndex()
  {
    return c;
  }
  
  public int getMode()
  {
    return e;
  }
  
  public String getNewText()
  {
    if (!TextUtils.isEmpty(a.getText())) {
      return a.getText().toString().trim();
    }
    if (TextUtils.isEmpty(a.getHint())) {
      return "";
    }
    return a.getHint().toString().trim();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    a = ((EditText)findViewById(2131886434));
    a.setOnFocusChangeListener(f);
    a.setOnEditorActionListener(g);
    a.addTextChangedListener(h);
    a.setFilters(new InputFilter[] { new b(140) });
    e = 1;
  }
  
  public void setChangeModeCallback(a parama)
  {
    d = parama;
  }
  
  public void setEditHint(String paramString)
  {
    a.setHint(paramString);
  }
  
  public void setIndex(int paramInt)
  {
    c = paramInt;
  }
  
  public void setText(String paramString)
  {
    a.setText(paramString);
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(boolean paramBoolean, int paramInt);
    
    public abstract void c();
    
    public abstract void e();
  }
  
  class b
    implements InputFilter
  {
    private int b;
    
    public b(int paramInt)
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
      return paramCharSequence.length() + EditQuickReplyView.a(EditQuickReplyView.this, paramCharSequence);
    }
    
    public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
    {
      int i = 0;
      int j = b - (paramSpanned.length() + EditQuickReplyView.a(EditQuickReplyView.this, paramSpanned) - (EditQuickReplyView.a(EditQuickReplyView.this, paramSpanned.subSequence(paramInt3, paramInt4)) + paramInt4 - paramInt3));
      if (j <= 0)
      {
        if (!TextUtils.isEmpty(paramCharSequence)) {
          EditQuickReplyView.b(EditQuickReplyView.this).c();
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
        if (EditQuickReplyView.a(EditQuickReplyView.this, paramCharSequence.charAt(paramInt4))) {
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
}

/* Location:
 * Qualified Name:     com.android.mms.quickreply.EditQuickReplyView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */