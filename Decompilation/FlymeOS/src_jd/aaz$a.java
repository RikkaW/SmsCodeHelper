import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

class aaz$a
  extends aaz.c
{
  public aaz$a(aaz paramaaz, String paramString)
  {
    super(paramaaz, paramString);
  }
  
  private String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "@";
    }
    return "@" + paramString;
  }
  
  void a(View paramView, String paramString)
  {
    Intent localIntent = new Intent("com.meizu.weibo.action.TIMELINE");
    localIntent.putExtra("sns_name", a(paramString));
    paramView.getContext().startActivity(localIntent);
  }
}

/* Location:
 * Qualified Name:     aaz.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */