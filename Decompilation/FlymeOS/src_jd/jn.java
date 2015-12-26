import com.android.mms.MmsApp.g;
import com.android.mms.fragmentstyle.FavoriteActivity;

public class jn
  implements MmsApp.g
{
  public jn(FavoriteActivity paramFavoriteActivity) {}
  
  public void a(boolean paramBoolean)
  {
    if (FavoriteActivity.a(a) != null) {
      FavoriteActivity.a(a).notifyDataSetChanged();
    }
  }
}

/* Location:
 * Qualified Name:     jn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */