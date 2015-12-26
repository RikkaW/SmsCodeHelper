import android.app.Activity;
import android.content.Context;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class ake
{
  public static final akg a(Activity paramActivity, akr paramakr, UpdateInfo paramUpdateInfo)
  {
    return a(paramActivity, paramakr, paramUpdateInfo, false, false);
  }
  
  public static final akg a(Context paramContext, akr paramakr, UpdateInfo paramUpdateInfo, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramUpdateInfo == null) || (!mExistsUpdate))
    {
      anf.d("request display while no update!");
      return null;
    }
    akk.b(paramContext);
    if (anj.a())
    {
      anf.d("request display while update in process, skip!");
      return null;
    }
    String str = aki.c(paramContext, mVersionName);
    if (anl.c(paramContext, str)) {
      paramContext = new ald(paramContext, null, paramUpdateInfo, str);
    }
    for (;;)
    {
      paramContext.a(paramBoolean1);
      return paramContext.b();
      paramContext = new alo(paramContext, paramakr, paramUpdateInfo, false, true);
      paramContext.b(paramBoolean2);
    }
  }
  
  public static final void a(Context paramContext)
  {
    amx.a(paramContext);
  }
  
  public static final void a(Context paramContext, akn paramakn, long paramLong, boolean paramBoolean)
  {
    new akl(paramContext, paramakn, paramLong).a(paramBoolean);
    b(paramContext);
  }
  
  public static final boolean a(Context paramContext, String paramString)
  {
    if (amx.a(paramContext, paramString))
    {
      MzUpdateComponentService.a(paramContext);
      return true;
    }
    return false;
  }
  
  public static final void b(Context paramContext)
  {
    amx.d(paramContext);
  }
}

/* Location:
 * Qualified Name:     ake
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */