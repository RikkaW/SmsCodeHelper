import android.content.Context;
import android.content.res.Resources;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class akz
  extends aks
{
  public akz(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    super(paramContext, paramUpdateInfo);
    a(true);
  }
  
  private void c()
  {
    MzUpdateComponentService.c(a);
  }
  
  public aks.a a()
  {
    return new aks.a(a.getString(akq.d.mzuc_downloading), null, anc.b(b, a) + " , " + b.mSize, a.getResources().getString(akq.d.mzuc_delete), a.getResources().getString(akq.d.mzuc_cancel), null, new ala(this));
  }
}

/* Location:
 * Qualified Name:     akz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */