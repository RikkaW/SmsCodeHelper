import android.content.Context;
import android.content.Intent;
import com.meizu.common.app.SlideNotice;
import com.meizu.common.app.SlideNotice.OnClickNoticeListener;

class aal
  implements SlideNotice.OnClickNoticeListener
{
  aal(aak paramaak) {}
  
  public void onClick(SlideNotice paramSlideNotice)
  {
    try
    {
      paramSlideNotice = new Intent(a.a.a);
      paramSlideNotice.putExtra("android.intent.extra.TITLE", a.a.c);
      paramSlideNotice.putExtra("from_notify", true);
      a.a.b.startActivity(paramSlideNotice);
      return;
    }
    catch (Exception paramSlideNotice)
    {
      paramSlideNotice.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     aal
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */