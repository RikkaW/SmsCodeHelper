import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import com.android.mms.ui.SmsCenterNumberEditTextPreference;

public class zc
  implements TextWatcher
{
  public zc(SmsCenterNumberEditTextPreference paramSmsCenterNumberEditTextPreference) {}
  
  public void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.toString().equals(""))
    {
      SmsCenterNumberEditTextPreference.a(a).setEnabled(false);
      return;
    }
    SmsCenterNumberEditTextPreference.a(a).setEnabled(true);
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}

/* Location:
 * Qualified Name:     zc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */