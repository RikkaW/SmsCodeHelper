package com.android.mms.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.data.ContactList;
import java.util.ArrayList;
import java.util.StringTokenizer;

class NewMessageActivity$15
  implements TextWatcher
{
  NewMessageActivity$15(NewMessageActivity paramNewMessageActivity) {}
  
  public void afterTextChanged(Editable paramEditable)
  {
    if ((NewMessageActivity.access$1700(this$0).isEmpty()) && (NewMessageActivity.access$400(this$0).getText().length() == 0)) {
      NewMessageActivity.access$3000(this$0).setVisibility(0);
    }
    String str;
    ArrayList localArrayList;
    for (;;)
    {
      str = paramEditable.toString() + '$';
      StringTokenizer localStringTokenizer = new StringTokenizer(str, NewMessageActivity.access$3100());
      localArrayList = new ArrayList();
      while (localStringTokenizer.hasMoreTokens()) {
        localArrayList.add(localStringTokenizer.nextToken());
      }
      NewMessageActivity.access$3000(this$0).setVisibility(8);
    }
    if ((localArrayList.size() > 1) || (((String)localArrayList.get(0)).length() < str.length()))
    {
      str = (String)localArrayList.get(localArrayList.size() - 1);
      str = str.substring(0, str.length() - 1);
      int j = NewMessageActivity.access$400(this$0).getSelectionEnd() - (paramEditable.length() - str.length());
      int i = j;
      if (j < 0) {
        i = 0;
      }
      NewMessageActivity.access$400(this$0).setText(str);
      NewMessageActivity.access$700(this$0).start();
      NewMessageActivity.access$700(this$0).getFilter().filter(str);
      NewMessageActivity.access$400(this$0).setSelection(i);
      j = 0;
      i = 0;
      while (i < localArrayList.size() - 1)
      {
        int k = j;
        if (NewMessageActivity.access$1100(this$0, (String)localArrayList.get(i)))
        {
          k = j;
          if (j == 0) {
            k = 1;
          }
        }
        i += 1;
        j = k;
      }
      if (j != 0) {
        NewMessageActivity.access$1200(this$0);
      }
    }
    for (;;)
    {
      this$0.postUpdateSendButtonState();
      return;
      NewMessageActivity.access$700(this$0).start();
      NewMessageActivity.access$700(this$0).getFilter().filter(paramEditable);
      NewMessageActivity.access$3200(this$0).setSelection(0);
    }
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this$0.onUserInteraction();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */