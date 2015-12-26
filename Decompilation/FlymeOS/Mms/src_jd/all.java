import android.content.Context;
import com.meizu.update.UpdateInfo;

public class all
  extends aks
{
  private akr e;
  
  public all(Context paramContext, UpdateInfo paramUpdateInfo, akr paramakr, boolean paramBoolean)
  {
    super(paramContext, paramUpdateInfo);
    a(paramBoolean);
    e = paramakr;
  }
  
  private void c()
  {
    if (b != null)
    {
      and.a(a).a(and.a.e, b.mVersionName, anl.b(a, a.getPackageName()));
      amx.b(a, b.mVersionName);
    }
  }
  
  private void d()
  {
    if (e != null) {
      e.a(1, b);
    }
  }
  
  public aks.a a()
  {
    return new aks.a(null, null, a.getString(akq.d.mzuc_skip_warn_tip), a.getString(akq.d.mzuc_ok), a.getString(akq.d.mzuc_cancel), null, new alm(this));
  }
}

/* Location:
 * Qualified Name:     all
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */