import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

class aaz$b
  extends aaz.c
{
  public aaz$b(aaz paramaaz, String paramString)
  {
    super(paramaaz, paramString);
  }
  
  void a(View paramView, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("http://huati.weibo.com/k/" + paramString));
    paramView.getContext().startActivity(paramString);
  }
}

/* Location:
 * Qualified Name:     aaz.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */