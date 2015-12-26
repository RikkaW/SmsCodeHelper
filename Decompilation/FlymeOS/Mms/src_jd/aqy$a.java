import android.content.Context;
import android.support.v7.appcompat.R.attr;
import android.support.v7.internal.widget.TintImageView;

class aqy$a
  extends TintImageView
{
  public aqy$a(aqy paramaqy, Context paramContext)
  {
    super(paramContext, null, R.attr.mzTabContainerCollapseButtonStyle);
    setClickable(true);
    setFocusable(true);
    setVisibility(0);
    setEnabled(true);
    setOnClickListener(new aqz(this, paramaqy));
    setBackgroundDrawable(new aqt(this, R.attr.mzActionButtonRippleStyle));
  }
  
  public boolean performClick()
  {
    if (super.performClick()) {
      return true;
    }
    playSoundEffect(0);
    return true;
  }
}

/* Location:
 * Qualified Name:     aqy.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */