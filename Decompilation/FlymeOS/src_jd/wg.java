import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.android.mms.ui.ConversationList;
import com.meizu.common.app.PermissionDialogBuilder.OnPermissionClickListener;

final class wg
  implements PermissionDialogBuilder.OnPermissionClickListener
{
  wg(ConversationList paramConversationList) {}
  
  public void onPerMisssionClick(DialogInterface paramDialogInterface, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean2)
    {
      if (paramBoolean1)
      {
        paramDialogInterface = PreferenceManager.getDefaultSharedPreferences(a).edit();
        paramDialogInterface.putBoolean("show_cta_dialog", false);
        paramDialogInterface.apply();
      }
      wd.b(a);
      return;
    }
    a.finish();
  }
}

/* Location:
 * Qualified Name:     wg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */