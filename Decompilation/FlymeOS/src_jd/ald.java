import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.meizu.update.UpdateInfo;
import com.meizu.update.iresponse.MzUpdateResponse;
import com.meizu.update.service.MzUpdateComponentService;

public class ald
  extends aks
{
  private String e;
  private Handler f;
  private akr g;
  private ProgressDialog h;
  private amt i = new ale(this);
  
  public ald(Context paramContext, akr paramakr, UpdateInfo paramUpdateInfo, String paramString)
  {
    super(paramContext, paramUpdateInfo);
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("params cant be null!");
    }
    g = paramakr;
    e = paramString;
    if (g != null)
    {
      f = new Handler(paramContext.getMainLooper());
      h = anm.a(paramContext);
      h.setMessage(paramContext.getString(akq.d.mzuc_installing));
      h.setCancelable(false);
      h.setOnCancelListener(new alg(this));
    }
  }
  
  private void a(int paramInt)
  {
    g();
    switch (paramInt)
    {
    default: 
      return;
    case 1: 
      d();
      return;
    case 2: 
      e();
      return;
    }
    h();
  }
  
  private void a(Runnable paramRunnable)
  {
    f.post(paramRunnable);
  }
  
  private void d()
  {
    if (g != null) {
      g.a(1, b);
    }
  }
  
  private void e()
  {
    if (g != null) {
      g.a(3, b);
    }
  }
  
  private void f()
  {
    try
    {
      if (h != null) {
        h.show();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void g()
  {
    try
    {
      if ((h != null) && (h.isShowing())) {
        h.dismiss();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void h()
  {
    amr.a(a, e, b);
    f.postDelayed(new ali(this), 1000L);
  }
  
  public aks.a a()
  {
    String str1 = anl.h(a);
    String str2 = String.format(a.getString(akq.d.mzuc_download_finish_s), new Object[] { str1, b.mVersionName });
    String str3 = a.getString(akq.d.mzuc_install_immediately);
    if (!b.mNeedUpdate) {}
    for (str1 = a.getString(akq.d.mzuc_install_later);; str1 = null)
    {
      and.a(a).a(and.a.i, b.mVersionName);
      return new aks.a(null, null, str2, str3, str1, null, new alh(this));
    }
  }
  
  protected void c()
  {
    f();
    MzUpdateResponse localMzUpdateResponse = null;
    if (g != null) {
      localMzUpdateResponse = new MzUpdateResponse(i);
    }
    MzUpdateComponentService.a(a, b, e, localMzUpdateResponse);
  }
}

/* Location:
 * Qualified Name:     ald
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */