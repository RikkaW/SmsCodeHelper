import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import com.meizu.update.UpdateInfo;
import com.meizu.update.iresponse.MzUpdateResponse;
import com.meizu.update.service.MzUpdateComponentService;

public class alo
  extends aks
{
  private akr e;
  private Handler f;
  private ProgressDialog g;
  private boolean h;
  private boolean i;
  private boolean j;
  private amt k = new alp(this);
  
  public alo(Context paramContext, akr paramakr, UpdateInfo paramUpdateInfo, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext, paramUpdateInfo);
    a(paramBoolean1);
    e = paramakr;
    h = paramBoolean2;
    if (e != null)
    {
      f = new Handler(paramContext.getMainLooper());
      g = anm.a(paramContext);
      g.setMessage(paramContext.getString(akq.d.mzuc_downloading));
      g.setOnCancelListener(new alr(this));
    }
  }
  
  private void a(int paramInt, Bundle paramBundle)
  {
    h();
    if (i)
    {
      c();
      return;
    }
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      paramBundle = paramBundle.getString("apk_path");
      new ald(a, e, b, paramBundle).c();
      return;
    case 2: 
      d();
      return;
    }
    c();
  }
  
  private void a(Runnable paramRunnable)
  {
    f.post(paramRunnable);
  }
  
  private void c()
  {
    if (e != null) {
      e.a(1, b);
    }
  }
  
  private void d()
  {
    if (e != null) {
      e.a(2, b);
    }
  }
  
  private void e()
  {
    MzUpdateResponse localMzUpdateResponse = null;
    if (e != null) {
      localMzUpdateResponse = new MzUpdateResponse(k);
    }
    g();
    MzUpdateComponentService.a(a, b, localMzUpdateResponse);
  }
  
  private void f()
  {
    MzUpdateComponentService.c(a);
  }
  
  private void g()
  {
    if (g != null) {
      g.show();
    }
  }
  
  private void h()
  {
    try
    {
      if ((g != null) && (g.isShowing())) {
        g.dismiss();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public aks.a a()
  {
    String str2 = null;
    String str3 = String.format(a.getString(akq.d.mzuc_found_update_s), new Object[] { b.mVersionName });
    String str4 = String.format(a.getString(akq.d.mzuc_file_size_s), new Object[] { b.mSize });
    String str5 = b.mVersionDesc;
    String str6 = a.getResources().getString(akq.d.mzuc_update_immediately);
    String str1;
    if (!b.mNeedUpdate)
    {
      str1 = str2;
      if (h)
      {
        str1 = str2;
        if (!j) {
          str1 = a.getResources().getString(akq.d.mzuc_skip_version);
        }
      }
      if (j) {
        str2 = a.getString(akq.d.mzuc_cancel);
      }
    }
    for (;;)
    {
      and.a(a).a(and.a.b, b.mVersionName, anl.b(a, a.getPackageName()), j);
      return new aks.a(str3, str4, str5, str6, str1, str2, new als(this));
      str2 = a.getString(akq.d.mzuc_warn_later);
      continue;
      str2 = null;
      str1 = null;
    }
  }
  
  public void b(boolean paramBoolean)
  {
    j = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     alo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */