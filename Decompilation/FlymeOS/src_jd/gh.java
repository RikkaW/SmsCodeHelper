import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.meizu.common.widget.AnimCheckBox;
import com.meizu.common.widget.AnimCheckBox.UpdateListener;

class gh
  implements AnimCheckBox.UpdateListener
{
  gh(gg paramgg, TextView paramTextView, AnimCheckBox paramAnimCheckBox) {}
  
  public void getUpdateTransition(float paramFloat)
  {
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)a.getLayoutParams();
    rightMargin = ((int)((b.getWidth() + 30) * paramFloat));
    a.setLayoutParams(localLayoutParams);
  }
}

/* Location:
 * Qualified Name:     gh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */