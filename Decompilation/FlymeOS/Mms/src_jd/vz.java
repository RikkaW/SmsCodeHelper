import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.android.mms.ui.MessageListView;
import com.android.mms.ui.MessageListView.e;
import com.android.mms.view.MessageListItemBase;

public class vz
  implements AbsListView.OnScrollListener
{
  public vz(MessageListView paramMessageListView) {}
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if ((paramInt == 0) && (a.a(0)) && (a.l()))
    {
      localMessageListItemBase = MessageListView.a(a).a();
      if (localMessageListItemBase != null) {}
    }
    while (a.m())
    {
      MessageListItemBase localMessageListItemBase;
      return;
      if (a.indexOfChild(localMessageListItemBase) < 0)
      {
        Log.d("MessageListView", "onScrollStateChanged(), indexOfChild(v) < 0");
        a.k();
        return;
      }
      if ((localMessageListItemBase.getBottom() < MessageListView.p()) || ((localMessageListItemBase.getTop() > 0) && (a.getMeasuredHeight() - localMessageListItemBase.getTop() < MessageListView.q())))
      {
        Log.d("MessageListView", "onScrollStateChanged(), v.getBottom() = " + localMessageListItemBase.getBottom() + ", height = " + a.getMeasuredHeight() + ", v.getTop() = " + localMessageListItemBase.getTop());
        a.k();
        return;
      }
      RectF localRectF = new RectF(0.0F, 0.0F, localMessageListItemBase.getMessageBody().getWidth(), localMessageListItemBase.getMessageBody().getHeight());
      if (localRectF.height() > MessageListView.r() * 2) {
        localRectF.inset(0.0F, MessageListView.r());
      }
      MessageListView.b(a).a(localMessageListItemBase.getMessageBody(), localRectF);
    }
    if (paramAbsListView.getLastVisiblePosition() == paramAbsListView.getCount() - 1)
    {
      MessageListView.a(a, true);
      return;
    }
    MessageListView.a(a, false);
  }
}

/* Location:
 * Qualified Name:     vz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */