import android.text.Editable;
import android.text.TextWatcher;
import com.android.mms.view.MessagePopupReplyView;
import com.android.mms.view.MessagePopupReplyView.a;

public class aec
  implements TextWatcher
{
  public aec(MessagePopupReplyView paramMessagePopupReplyView) {}
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    MessagePopupReplyView.a(a, paramCharSequence);
    if (MessagePopupReplyView.a(a) != null) {
      MessagePopupReplyView.a(a).b(true);
    }
  }
}

/* Location:
 * Qualified Name:     aec
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */