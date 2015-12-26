import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.ui.SlideEditorActivity;

public class yj
  implements View.OnClickListener
{
  public yj(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(View paramView)
  {
    paramView = new Intent();
    paramView.putExtra("done", true);
    a.setResult(-1, paramView);
    a.finish();
  }
}

/* Location:
 * Qualified Name:     yj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */