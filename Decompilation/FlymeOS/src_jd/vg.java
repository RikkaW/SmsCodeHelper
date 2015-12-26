import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.ui.MeizuSearchActivity.b;

public class vg
  implements View.OnClickListener
{
  public vg(MeizuSearchActivity paramMeizuSearchActivity) {}
  
  public void onClick(View paramView)
  {
    if ((MeizuSearchActivity.a(a) != null) && (!TextUtils.isEmpty(MeizuSearchActivity.a(a).getText()))) {
      MeizuSearchActivity.a(a).setText(null);
    }
    MeizuSearchActivity.e(a).setVisibility(8);
    MeizuSearchActivity.a(a).requestFocus();
    MeizuSearchActivity.a(a, true);
    if ((MeizuSearchActivity.h(a) != null) && (MeizuSearchActivity.h(a).a()) && (MeizuSearchActivity.c(a) != null)) {
      MeizuSearchActivity.i(a).d();
    }
  }
}

/* Location:
 * Qualified Name:     vg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */