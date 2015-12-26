import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.android.mms.ui.NotificationReply;

public class wy
  implements TextWatcher
{
  public wy(NotificationReply paramNotificationReply) {}
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    paramCharSequence = NotificationReply.a(a).getText().toString();
    NotificationReply.b(a).setEnabled(NotificationReply.a(a, paramCharSequence));
  }
}

/* Location:
 * Qualified Name:     wy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */