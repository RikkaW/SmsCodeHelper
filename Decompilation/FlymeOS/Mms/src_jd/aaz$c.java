import android.text.style.ClickableSpan;
import android.view.View;

abstract class aaz$c
  extends ClickableSpan
{
  private String a;
  
  public aaz$c(aaz paramaaz, String paramString)
  {
    a = paramString;
  }
  
  abstract void a(View paramView, String paramString);
  
  public void onClick(View paramView)
  {
    a(paramView, a);
  }
}

/* Location:
 * Qualified Name:     aaz.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */