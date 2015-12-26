package com.android.mms.ui;

import com.android.mms.data.Contact;

class PrivatePreferenceActivity$1
  implements PrivateContactPreference.OnClickDeleteBtnListener
{
  PrivatePreferenceActivity$1(PrivatePreferenceActivity paramPrivatePreferenceActivity) {}
  
  public void onClick(long paramLong, Contact paramContact)
  {
    PrivatePreferenceActivity.access$200(this$0, new PrivatePreferenceActivity.ExportPrivateContactListener(this$0, paramLong, paramContact, PrivatePreferenceActivity.access$000(this$0), PrivatePreferenceActivity.access$100(this$0)), PrivatePreferenceActivity.access$100(this$0));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */