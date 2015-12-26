import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.AbsoluteLayout;
import android.widget.TextView;
import com.meizu.commonwidget.RecipientEdit;
import com.meizu.commonwidget.RecipientEdit.ItemView;
import com.meizu.commonwidget.RecipientEdit.a;
import java.util.ArrayList;
import java.util.HashMap;

public class aim
  extends Handler
{
  public aim(RecipientEdit paramRecipientEdit) {}
  
  public void handleMessage(Message paramMessage)
  {
    String str = obj).a;
    paramMessage = obj).b;
    RecipientEdit.h(a).put(str, paramMessage);
    int i = RecipientEdit.i(a).indexOf(str);
    if (i > -1)
    {
      if (!TextUtils.isEmpty(paramMessage)) {
        break label204;
      }
      paramMessage = str;
    }
    label180:
    label204:
    for (;;)
    {
      RecipientEdit.ItemView localItemView = (RecipientEdit.ItemView)RecipientEdit.j(a).getChildAt(i + 1);
      localItemView.a(paramMessage + "，");
      if ((!RecipientEdit.a(a, str)) && (RecipientEdit.k(a)))
      {
        if (RecipientEdit.l(a) != 2) {
          break label180;
        }
        localItemView.a().setTextColor(a.getResources().getColor(aih.a.mw_recipient_text_invalidate_calendar));
      }
      for (;;)
      {
        a.b(a.hasFocus());
        RecipientEdit.a(a, false);
        return;
        localItemView.a().setTextColor(a.getResources().getColor(aih.a.mw_recipient_text_invalidate));
      }
    }
  }
}

/* Location:
 * Qualified Name:     aim
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */