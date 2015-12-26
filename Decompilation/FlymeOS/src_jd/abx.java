import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.ted.android.data.BubbleEntity;

class abx
  extends ClickableSpan
{
  abx(abu paramabu, BubbleEntity paramBubbleEntity) {}
  
  public void onClick(View paramView)
  {
    abu.a(b, a);
  }
  
  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.setColor(linkColor);
    paramTextPaint.setUnderlineText(false);
  }
}

/* Location:
 * Qualified Name:     abx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */