package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashSet;

class PrivatePreferenceActivity$5
  implements DialogInterface.OnClickListener
{
  PrivatePreferenceActivity$5(PrivatePreferenceActivity paramPrivatePreferenceActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = PrivatePreferenceActivity.access$1000(this$0).getText().toString();
    if (TextUtils.isEmpty(paramDialogInterface))
    {
      Toast.makeText(PrivatePreferenceActivity.access$100(this$0), 2131362264, 0).show();
      return;
    }
    HashSet localHashSet = new HashSet();
    localHashSet.add(paramDialogInterface);
    PrivatePreferenceActivity.access$1100(this$0, PrivatePreferenceActivity.access$000(this$0), localHashSet);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */