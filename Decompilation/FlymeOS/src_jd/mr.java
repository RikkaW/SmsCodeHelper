import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;

class mr
  implements DialogInterface.OnClickListener
{
  mr(mq parammq) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = null;
    try
    {
      Cursor localCursor = (Cursor)mq.a(a).getItem(paramInt);
      if (paramDialogInterface == null) {
        break label58;
      }
    }
    finally
    {
      try
      {
        mq.b(a).a(localCursor.getString(1));
        if (localCursor != null) {
          localCursor.close();
        }
        return;
      }
      finally
      {
        paramDialogInterface = (DialogInterface)localObject1;
        Object localObject2 = localObject3;
      }
      localObject1 = finally;
    }
    paramDialogInterface.close();
    label58:
    throw ((Throwable)localObject1);
  }
}

/* Location:
 * Qualified Name:     mr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */