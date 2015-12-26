import android.view.KeyEvent;
import android.view.View;
import com.meizu.commonwidget.RecipientEdit;
import com.meizu.commonwidget.RecipientEdit.RecipientAutoCompleteTextView.a;

public class aik
  implements RecipientEdit.RecipientAutoCompleteTextView.a
{
  public aik(RecipientEdit paramRecipientEdit) {}
  
  public boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (RecipientEdit.b(a) != null) {
      return RecipientEdit.b(a).a(a, paramInt, paramKeyEvent);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     aik
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */