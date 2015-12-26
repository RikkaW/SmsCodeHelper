import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.meizu.commonwidget.RecipientEdit;
import com.meizu.commonwidget.RecipientEdit.RecipientAutoCompleteTextView;

public class aij
  implements ViewTreeObserver.OnScrollChangedListener
{
  public aij(RecipientEdit paramRecipientEdit) {}
  
  public void onScrollChanged()
  {
    if (RecipientEdit.a(a).isPopupShowing())
    {
      RecipientEdit.a(a).a();
      RecipientEdit.a(a).showDropDown();
    }
  }
}

/* Location:
 * Qualified Name:     aij
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */