import android.content.Context;
import com.meizu.update.UpdateInfo;

public class akj
{
  private Context a;
  private akn b;
  private long c;
  
  protected akj(Context paramContext, akn paramakn, long paramLong)
  {
    if ((paramContext == null) || (paramakn == null)) {
      throw new IllegalArgumentException("listener and context cant be null");
    }
    b = paramakn;
    a = paramContext;
    c = paramLong;
  }
  
  protected UpdateInfo a(boolean paramBoolean)
  {
    aki.b(a);
    anc.a(a);
    boolean bool = anl.i(a);
    if ((anl.d()) || (!akk.a(a, c)))
    {
      anf.d("check interval interrupt");
      return UpdateInfo.generateNoUpdateInfo();
    }
    if (!bool)
    {
      anf.c("request check no network : " + a.getPackageName());
      return null;
    }
    anf.a(a, "start check update for :" + a.getPackageName());
    UpdateInfo localUpdateInfo = akf.a(a);
    int i;
    if (localUpdateInfo != null)
    {
      anf.a(a, "check update result :" + mExistsUpdate + "," + mNeedUpdate + "," + mVersionName);
      if (mExistsUpdate) {
        if (mNeedUpdate)
        {
          i = 1;
          akk.a(a, i);
          if ((mExistsUpdate) && (!mNeedUpdate) && (amx.c(a, mVersionName)))
          {
            if (paramBoolean) {
              break label314;
            }
            anf.c("skip version: " + mVersionName);
            mExistsUpdate = false;
            akk.b(a);
          }
        }
      }
    }
    for (;;)
    {
      return localUpdateInfo;
      i = 2;
      break;
      akk.b(a);
      i = 3;
      aki.a(a);
      break;
      label314:
      anf.c("manual check while skip version: " + mVersionName);
      continue;
      anf.a(a, "check update return null");
    }
  }
  
  protected void a()
  {
    b.a(2, null);
  }
  
  protected void a(UpdateInfo paramUpdateInfo)
  {
    b.a(0, paramUpdateInfo);
  }
}

/* Location:
 * Qualified Name:     akj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */