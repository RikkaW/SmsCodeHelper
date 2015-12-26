package com.android.mms.ui;

import com.android.mms.data.Contact;

class PrivateContactPreference$1
  implements Runnable
{
  PrivateContactPreference$1(PrivateContactPreference paramPrivateContactPreference) {}
  
  public void run()
  {
    if (PrivateContactPreference.access$000(this$0) != null)
    {
      this$0.setTitle(PrivateContactPreference.access$000(this$0).getName());
      this$0.setSummary(PrivateContactPreference.access$000(this$0).getNumber());
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivateContactPreference.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */