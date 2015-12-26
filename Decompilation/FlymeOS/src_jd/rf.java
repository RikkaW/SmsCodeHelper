import android.content.res.Resources;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.EditTextEx;

public class rf
  implements Runnable
{
  public rf(ComposeMessageActivity paramComposeMessageActivity, int paramInt1, int paramInt2) {}
  
  public void run()
  {
    boolean bool2 = true;
    Object localObject2 = c.getResources();
    ((Resources)localObject2).getString(a);
    switch (b)
    {
    default: 
      throw new IllegalArgumentException("unknown error " + b);
    case -3: 
    case -1: 
      wd.a(((Resources)localObject2).getString(2131493160), c, 0, 1, true, 0, ComposeMessageActivity.n(c));
      return;
    case -4: 
      localObject1 = ((Resources)localObject2).getString(2131492963);
      localObject2 = ((Resources)localObject2).getString(2131493086);
      wd.a(c, (String)localObject1, (String)localObject2);
      return;
    }
    ComposeMessageActivity.d(c, -2);
    Object localObject1 = c;
    boolean bool1 = bool2;
    if (!c.c.h()) {
      if (!c.c.l()) {
        break label210;
      }
    }
    label210:
    for (bool1 = bool2;; bool1 = false)
    {
      ComposeMessageActivity.h((ComposeMessageActivity)localObject1, bool1);
      ComposeMessageActivity.a(c, ComposeMessageActivity.l(c).getText(), 0, 0, 0);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     rf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */