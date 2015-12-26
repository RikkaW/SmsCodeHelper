import com.android.mms.MmsApp.g;
import com.android.mms.ui.MeizuSearchActivity;

public class vf
  implements MmsApp.g
{
  public vf(MeizuSearchActivity paramMeizuSearchActivity) {}
  
  public void a(boolean paramBoolean)
  {
    if (MeizuSearchActivity.g(a) != null) {
      MeizuSearchActivity.g(a).notifyDataSetChanged();
    }
  }
}

/* Location:
 * Qualified Name:     vf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */