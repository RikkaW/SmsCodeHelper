package com.meizu.common.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;

class StretchSearchView$3
  implements TextWatcher
{
  StretchSearchView$3(StretchSearchView paramStretchSearchView) {}
  
  public void afterTextChanged(Editable paramEditable)
  {
    paramEditable = StretchSearchView.access$000(this$0).getText().toString();
    if ((paramEditable == null) || (paramEditable.isEmpty()))
    {
      StretchSearchView.access$100(this$0).setVisibility(8);
      if ((StretchSearchView.access$200(this$0) == 2) && (StretchSearchView.access$300(this$0))) {
        StretchSearchView.access$400(this$0).setVisibility(0);
      }
      this$0.showIme(true);
      return;
    }
    if (StretchSearchView.access$300(this$0)) {
      StretchSearchView.access$400(this$0).setVisibility(8);
    }
    StretchSearchView.access$100(this$0).setVisibility(0);
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */