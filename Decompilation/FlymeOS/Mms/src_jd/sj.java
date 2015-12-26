import android.text.Spannable;
import android.view.View;
import android.widget.TextView;
import com.android.mms.fragmentstyle.ConversationListFragment;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ComposeMessageActivity.e;
import com.android.mms.ui.MessageListView;
import com.android.mms.ui.MessageListView.d;
import com.android.mms.view.MessageListItemBase;
import java.util.ArrayList;

public class sj
  implements MessageListView.d
{
  public sj(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public boolean a(int paramInt, MessageListItemBase paramMessageListItemBase)
  {
    if (paramMessageListItemBase == null) {}
    do
    {
      do
      {
        do
        {
          do
          {
            return true;
            switch (paramInt)
            {
            default: 
              return true;
            case 1: 
              ComposeMessageActivity.c(a, paramMessageListItemBase.getMessageItem());
              return true;
            case 2: 
              ComposeMessageActivity.d(a, paramMessageListItemBase.getMessageItem());
              return true;
            case 3: 
              localObject = paramMessageListItemBase.getContentTextView();
            }
          } while ((localObject == null) || (!(((TextView)localObject).getText() instanceof Spannable)));
          paramMessageListItemBase.postDelayed(new sk(this, (TextView)localObject, new me((TextView)localObject)), 250L);
          return true;
          localObject = paramMessageListItemBase.getMessageItem();
        } while (localObject == null);
        a.a(1, 1, ((vv)localObject).j());
        if (((vv)localObject).n())
        {
          paramInt = ((vv)localObject).O();
          if ((paramInt == 129) || (paramInt == 131)) {
            MessageListItemBase.a(a, (vv)localObject);
          }
        }
        if (1 >= a.b.getCount())
        {
          paramMessageListItemBase = new ArrayList();
          paramMessageListItemBase.add(Long.valueOf(a.a.e()));
          a.v();
          gr.a(ComposeMessageActivity.h(a), 1801, false, paramMessageListItemBase);
          ConversationListFragment.a(paramMessageListItemBase, false);
          return true;
        }
        new ComposeMessageActivity.e(a, new vv[] { paramMessageListItemBase.getMessageItem() }).a(false);
        return true;
        paramMessageListItemBase = paramMessageListItemBase.getMessageItem();
      } while (paramMessageListItemBase == null);
      ComposeMessageActivity.a(a, 1, paramMessageListItemBase, 9703);
      return true;
      paramMessageListItemBase = paramMessageListItemBase.getMessageItem();
    } while (paramMessageListItemBase == null);
    ComposeMessageActivity.a(a, 0, paramMessageListItemBase, 9704);
    return true;
    Object localObject = paramMessageListItemBase.getMessageItem();
    if (zv.e == 1)
    {
      wd.a((vv)localObject, a, zv.a());
      return true;
    }
    if (ComposeMessageActivity.ac(a) == null)
    {
      ComposeMessageActivity.a(a, new vr(a, paramMessageListItemBase.getContentTextView()));
      ComposeMessageActivity.ac(a).a(2131493701, 2131493702);
    }
    ComposeMessageActivity.ac(a).a(null);
    ComposeMessageActivity.ac(a).a(new sl(this, (vv)localObject));
    ComposeMessageActivity.ac(a).a(paramMessageListItemBase.getContentTextView());
    ComposeMessageActivity.ac(a).a();
    return true;
    ComposeMessageActivity.a(a, paramMessageListItemBase.getMessageItem());
    return true;
    if (a.y()) {
      ComposeMessageActivity.P(a);
    }
    ComposeMessageActivity.R(a).o();
    return true;
  }
  
  public boolean a(View paramView)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     sj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */