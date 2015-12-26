package com.android.mms.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

class MmsTabActivity$2
  implements TextWatcher
{
  MmsTabActivity$2(MmsTabActivity paramMmsTabActivity) {}
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    String str = null;
    if (paramCharSequence != null) {
      str = paramCharSequence.toString();
    }
    if (MmsTabActivity.access$000(this$0) == null) {}
    do
    {
      return;
      if (!TextUtils.isEmpty(str)) {
        break;
      }
    } while (MmsTabActivity.access$000(this$0).isHidden());
    MmsTabActivity.access$100(this$0, false, false, false);
    MmsTabActivity.access$000(this$0).selectToFirst();
    return;
    if (MmsTabActivity.access$000(this$0).isHidden()) {
      MmsTabActivity.access$100(this$0, false, false, true);
    }
    MmsTabActivity.access$000(this$0).query(str);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MmsTabActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */