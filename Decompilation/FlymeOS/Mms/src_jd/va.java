import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.view.MeizuSearchListItem;

public class va
  implements Runnable
{
  public va(MeizuSearchActivity paramMeizuSearchActivity, View paramView) {}
  
  public void run()
  {
    Intent localIntent = new Intent(b, ComposeMessageActivity.class);
    localIntent.putExtras((Bundle)a.getTag());
    b.startActivity(localIntent);
    ((MeizuSearchListItem)a).b();
    b.a = ((MeizuSearchListItem)a).a();
  }
}

/* Location:
 * Qualified Name:     va
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */