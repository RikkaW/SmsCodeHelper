import android.widget.AutoCompleteTextView.OnDismissListener;
import com.android.mms.recipient.MessageRecipient;

public class mu
  implements AutoCompleteTextView.OnDismissListener
{
  public mu(MessageRecipient paramMessageRecipient) {}
  
  public void onDismiss()
  {
    MessageRecipient.a(a, true);
  }
}

/* Location:
 * Qualified Name:     mu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */