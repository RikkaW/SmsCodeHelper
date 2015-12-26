import android.text.TextUtils;
import com.meizu.commonwidget.RecipientEdit;
import com.meizu.commonwidget.RecipientEdit.RecipientAutoCompleteTextView;
import com.meizu.commonwidget.RecipientEdit.n;
import java.util.ArrayList;
import java.util.HashMap;

public class ain
  implements Runnable
{
  public ain(RecipientEdit paramRecipientEdit) {}
  
  public void run()
  {
    int i = 0;
    while (i < RecipientEdit.i(a).size())
    {
      String str1 = (String)RecipientEdit.i(a).get(i);
      String str2 = ((aii)RecipientEdit.a(a).getAdapter()).a(str1);
      if (!TextUtils.equals(str2, (String)RecipientEdit.h(a).get(str1))) {
        a.post(new RecipientEdit.n(a, str1, str2));
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     ain
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */