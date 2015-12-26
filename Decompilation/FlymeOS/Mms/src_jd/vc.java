import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;
import com.android.mms.ui.MeizuSearchActivity;
import java.util.HashMap;

public class vc
  implements DialogInterface.OnClickListener
{
  public vc(MeizuSearchActivity paramMeizuSearchActivity, HashMap paramHashMap, CheckBox paramCheckBox) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    MeizuSearchActivity.b(c, (HashMap)a.clone(), b.isChecked());
    MeizuSearchActivity.i(c).d();
  }
}

/* Location:
 * Qualified Name:     vc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */