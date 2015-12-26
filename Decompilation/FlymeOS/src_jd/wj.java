import android.os.Handler;
import android.util.Log;
import com.android.mms.ui.ConversationList;
import com.meizu.update.UpdateInfo;

final class wj
  implements akn
{
  wj(ConversationList paramConversationList) {}
  
  public void a(int paramInt, UpdateInfo paramUpdateInfo)
  {
    Log.v("MzUpdateComponent", "checkUpdate onCheckEnd " + paramInt);
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      if (paramUpdateInfo != null) {
        Log.v("MzUpdateComponent", "checkUpdate SUCCESS " + mExistsUpdate);
      }
      while (mExistsUpdate)
      {
        a.h().post(new wk(this, paramUpdateInfo));
        return;
        Log.v("MzUpdateComponent", "checkUpdate SUCCESS info is null");
      }
    }
  }
}

/* Location:
 * Qualified Name:     wj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */