import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.inputmethod.BaseInputConnection;
import android.widget.ImageView;

class iw
  implements TextWatcher
{
  iw(ir paramir) {}
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramCharSequence.length() > 0) && (ir.b(a).getVisibility() == 8)) {
      ir.b(a).setVisibility(0);
    }
    for (;;)
    {
      if ((BaseInputConnection.getComposingSpanStart((Spannable)paramCharSequence) == -1) && (BaseInputConnection.getComposingSpanEnd((Spannable)paramCharSequence) == -1) && (paramCharSequence.toString().trim().length() > 0) && (ir.c(a) != null)) {
        ir.c(a).a(paramCharSequence.toString().trim());
      }
      return;
      if ((paramCharSequence.length() == 0) && (ir.b(a).getVisibility() == 0)) {
        ir.b(a).setVisibility(8);
      }
    }
  }
}

/* Location:
 * Qualified Name:     iw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */