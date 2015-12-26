import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.meizu.update.UpdateInfo;
import java.io.File;

public class amr
{
  public static amr.a a(Context paramContext, String paramString)
  {
    Object localObject = new Object();
    locala = amr.a.b;
    try
    {
      int i = ((Integer)aji.a("android.content.pm.PackageManager", "INSTALL_REPLACE_EXISTING")).intValue();
      int j = ((Integer)aji.a("android.content.pm.PackageManager", "INSTALL_SUCCEEDED")).intValue();
      PackageManager localPackageManager = paramContext.getPackageManager();
      Class localClass = Integer.TYPE;
      paramString = Uri.parse("file://" + paramString);
      ams localams = new ams(j, locala, localObject);
      aji.a(localPackageManager, "installPackage", new Class[] { Uri.class, IPackageInstallObserver.class, localClass, String.class }, new Object[] { paramString, localams, Integer.valueOf(i), null });
      try
      {
        localObject.wait(120000L);
        if (locala.a() != j)
        {
          paramString = amr.a.c;
          paramString.a(locala.a());
          return paramString;
        }
      }
      catch (InterruptedException paramString)
      {
        paramString.printStackTrace();
        paramString = amr.a.c;
        return paramString;
      }
      finally {}
      return locala;
    }
    catch (Exception paramString)
    {
      anf.a(paramContext, "background install error :" + paramString.getMessage());
      paramString.printStackTrace();
      return amr.a.a;
    }
  }
  
  public static final Intent a(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    return localIntent;
  }
  
  public static final void a(Context paramContext, String paramString, UpdateInfo paramUpdateInfo)
  {
    amy.a(paramContext, paramUpdateInfo);
    paramString = a(paramString);
    if (!(paramContext instanceof Activity)) {
      paramString.addFlags(268435456);
    }
    paramContext.startActivity(paramString);
  }
  
  public static enum a
  {
    private int d = 55536;
    
    private a() {}
    
    public int a()
    {
      return d;
    }
    
    protected void a(int paramInt)
    {
      d = paramInt;
    }
  }
}

/* Location:
 * Qualified Name:     amr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */