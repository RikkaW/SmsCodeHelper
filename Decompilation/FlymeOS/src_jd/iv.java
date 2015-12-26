import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import com.android.mms.MmsApp;

class iv
  implements ActionBar.TabListener
{
  iv(ir paramir) {}
  
  public void onTabReselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction)
  {
    ir.c(a).b();
  }
  
  public void onTabSelected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction)
  {
    MmsApp.a(paramTab.getPosition());
    ir.c(a).b();
  }
  
  public void onTabUnselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction)
  {
    ir.c(a).a(paramTab.getPosition());
  }
}

/* Location:
 * Qualified Name:     iv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */