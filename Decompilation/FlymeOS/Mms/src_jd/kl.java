import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import com.android.mms.location.BaseGetLocationView;

class kl
  implements TextWatcher
{
  kl(kf paramkf) {}
  
  public void afterTextChanged(Editable paramEditable)
  {
    a.a.a(a.b.getText());
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramCharSequence.length() > 0) && (a.c.getVisibility() == 8)) {
      a.c.setVisibility(0);
    }
    while ((paramCharSequence.length() != 0) || (a.c.getVisibility() != 0)) {
      return;
    }
    a.c.setVisibility(8);
  }
}

/* Location:
 * Qualified Name:     kl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */