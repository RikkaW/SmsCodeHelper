package com.android.mms.view;

import aec;
import aed;
import android.content.Context;
import android.content.res.Resources;
import android.telephony.MzTelephony.MmsExt;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.transaction.MessagePopupService.b;
import wd;

public class MessagePopupReplyView
  extends FrameLayout
  implements View.OnClickListener
{
  private EditText a;
  private View b;
  private a c;
  
  public MessagePopupReplyView(Context paramContext)
  {
    super(paramContext);
  }
  
  public MessagePopupReplyView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public MessagePopupReplyView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void b(CharSequence paramCharSequence)
  {
    b.setEnabled(a(paramCharSequence));
  }
  
  public void a(long paramLong, String paramString)
  {
    if (TextUtils.isEmpty(a.getText()))
    {
      a.setText(paramString);
      a.setSelection(a.getText().length());
      return;
    }
    MessagePopupService.a(true, 2, "MessagePopupService.MessagePopupReplyView", "onDraftLoaded-->fill draft failed, because has content in editText...");
  }
  
  public void a(MessagePopupService.b paramb)
  {
    a(paramb, j, e);
  }
  
  public void a(MessagePopupService.b paramb, boolean paramBoolean, String paramString)
  {
    if (MzTelephony.MmsExt.isSnsRecipient(paramString)) {
      a.setHint(2131493614);
    }
    for (;;)
    {
      a.setHintTextColor(getResources().getColor(2131820866));
      return;
      if ((paramBoolean) && (q))
      {
        j = paramBoolean;
        a.setHint(2131493612);
      }
      else
      {
        j = paramBoolean;
        a.setHint(2131493613);
      }
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    if (!MzTelephony.MmsExt.isSnsRecipient(paramString))
    {
      if ((!paramBoolean1) || (!paramBoolean2)) {
        break label41;
      }
      a.setHint(2131493612);
    }
    for (;;)
    {
      a.setHintTextColor(getResources().getColor(2131820866));
      return;
      label41:
      a.setHint(2131493613);
    }
  }
  
  public final boolean a(CharSequence paramCharSequence)
  {
    return (paramCharSequence != null) && (TextUtils.getTrimmedLength(paramCharSequence) > 0) && (!wd.a(paramCharSequence));
  }
  
  public final String getEditText()
  {
    if ((a == null) || (a.getVisibility() == 8)) {}
    Editable localEditable;
    do
    {
      return null;
      localEditable = a.getText();
    } while (TextUtils.isEmpty(localEditable));
    return localEditable.toString();
  }
  
  public EditText getEidtTextView()
  {
    return a;
  }
  
  public void onClick(View paramView)
  {
    if (c == null) {
      return;
    }
    switch (paramView.getId())
    {
    default: 
      return;
    }
    c.a(a.getText().toString());
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(getContext(), 2130968752, this);
    a = ((EditText)findViewById(2131886617));
    a.setFilters(new InputFilter[] { new InputFilter.LengthFilter(1000) });
    b = findViewById(2131886618);
    b.setEnabled(false);
    b.setOnClickListener(this);
    a.addTextChangedListener(new aec(this));
    a.setOnClickListener(new aed(this));
  }
  
  public void setReplyCallback(a parama)
  {
    c = parama;
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString);
    
    public abstract void b(boolean paramBoolean);
    
    public abstract void f();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessagePopupReplyView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */