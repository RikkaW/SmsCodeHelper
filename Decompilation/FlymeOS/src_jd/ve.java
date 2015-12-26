import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.ImageView;
import android.widget.ListView;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.ui.MeizuSearchActivity.a;

public class ve
  implements TextWatcher
{
  public ve(MeizuSearchActivity paramMeizuSearchActivity) {}
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    MeizuSearchActivity.d(a).removeCallbacks(a.c);
    if ((paramCharSequence.length() > 0) && (MeizuSearchActivity.e(a).getVisibility() == 8)) {
      MeizuSearchActivity.e(a).setVisibility(0);
    }
    for (;;)
    {
      if ((BaseInputConnection.getComposingSpanStart((Spannable)paramCharSequence) == -1) && (BaseInputConnection.getComposingSpanEnd((Spannable)paramCharSequence) == -1))
      {
        MeizuSearchActivity.a(a, paramCharSequence.toString().trim());
        MeizuSearchActivity.f(a);
      }
      return;
      if (paramCharSequence.length() == 0)
      {
        if (MeizuSearchActivity.e(a).getVisibility() == 0) {
          MeizuSearchActivity.e(a).setVisibility(8);
        }
        if (MeizuSearchActivity.c(a).getEmptyView().getVisibility() == 0) {
          MeizuSearchActivity.c(a).getEmptyView().setVisibility(4);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     ve
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */