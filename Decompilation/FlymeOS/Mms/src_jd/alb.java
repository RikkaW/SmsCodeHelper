import android.content.Context;
import android.content.res.Resources;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class alb
  extends aks
{
  private boolean e;
  
  public alb(Context paramContext, UpdateInfo paramUpdateInfo, boolean paramBoolean)
  {
    super(paramContext, paramUpdateInfo);
    e = paramBoolean;
    a(true);
  }
  
  private void c() {}
  
  private void d()
  {
    MzUpdateComponentService.a(a, b, null);
  }
  
  public aks.a a()
  {
    String str3 = anc.b(b, a);
    String str1;
    if (e) {
      str1 = a.getString(akq.d.mzuc_download_fail);
    }
    for (String str2 = a.getResources().getString(akq.d.mzuc_cancel_download);; str2 = a.getResources().getString(akq.d.mzuc_cancel_install))
    {
      return new aks.a(str3, null, str1, a.getResources().getString(akq.d.mzuc_retry), str2, null, new alc(this));
      str1 = a.getString(akq.d.mzuc_install_fail);
    }
  }
}

/* Location:
 * Qualified Name:     alb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */