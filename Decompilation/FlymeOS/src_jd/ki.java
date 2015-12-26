import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import com.android.mms.location.BaseGetLocationView;

class ki
  implements View.OnKeyListener
{
  ki(kf paramkf) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 0) && (paramInt == 66))
    {
      a.i();
      if (!TextUtils.isEmpty(a.b.getText()))
      {
        if (a.j)
        {
          a.a.a(13);
          a.j = false;
        }
        kf.a(true, 4, "location/BaseGetLocationActivity", "mGetLocationView.searchThroughInput text =" + a.b.getText().toString());
        a.a.d(a.b.getText().toString());
      }
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     ki
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */