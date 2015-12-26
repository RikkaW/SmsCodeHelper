import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.android.mms.ui.ManageSimMessages;
import com.android.mms.ui.ManageSimMessages.b;

public class uw
  implements View.OnClickListener
{
  public uw(ManageSimMessages.b paramb) {}
  
  public void onClick(View paramView)
  {
    if (ManageSimMessages.b(a.a).getCount() == ManageSimMessages.c(a.a).getCheckedItemCount())
    {
      ManageSimMessages.e(a.a).f();
      aaa.a(ManageSimMessages.b.a(a), false);
      aaa.a(ManageSimMessages.b.b(a), false);
    }
    for (;;)
    {
      aaa.a(a.a, ManageSimMessages.d(a.a), ManageSimMessages.c(a.a).getCheckedItemCount(), ManageSimMessages.b(a.a).getCount());
      return;
      ManageSimMessages.e(a.a).b();
      aaa.a(ManageSimMessages.b.a(a), ManageSimMessages.f(a.a));
      aaa.a(ManageSimMessages.b.b(a), ga.C());
    }
  }
}

/* Location:
 * Qualified Name:     uw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */