import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class anf
{
  public static void a(Context paramContext, String paramString)
  {
    boolean bool = true;
    try
    {
      c(paramString);
      paramContext = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + paramContext.getPackageName() + "/";
      Object localObject = new File(paramContext);
      if ((!((File)localObject).exists()) || (!((File)localObject).isDirectory())) {
        ((File)localObject).mkdir();
      }
      paramContext = new File(paramContext + "update_component_log");
      if (!paramContext.exists())
      {
        if (paramContext.createNewFile()) {}
      }
      else if (paramContext.length() > 16384L) {
        bool = false;
      }
      localObject = Calendar.getInstance();
      int i = ((Calendar)localObject).get(1);
      int j = ((Calendar)localObject).get(2);
      int k = ((Calendar)localObject).get(5);
      int m = ((Calendar)localObject).get(11);
      int n = ((Calendar)localObject).get(12);
      int i1 = ((Calendar)localObject).get(13);
      localObject = new StringBuffer();
      ((StringBuffer)localObject).append("[");
      ((StringBuffer)localObject).append(String.format("%04d-%02d-%02d %02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(j + 1), Integer.valueOf(k), Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(i1) }));
      ((StringBuffer)localObject).append("]");
      ((StringBuffer)localObject).append(paramString);
      ((StringBuffer)localObject).append("\n");
      label331:
      return;
    }
    catch (IOException paramContext)
    {
      try
      {
        paramString = new FileOutputStream(paramContext, bool);
      }
      finally
      {
        paramString = null;
        if (paramString != null) {
          paramString.close();
        }
      }
      try
      {
        paramString.write(((StringBuffer)localObject).toString().getBytes());
        if (paramString == null) {
          return;
        }
        paramString.close();
        return;
      }
      finally
      {
        break label331;
      }
      paramContext = paramContext;
      paramContext.printStackTrace();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
  }
  
  public static final void a(String paramString) {}
  
  public static void b(Context paramContext, String paramString)
  {
    new Thread(new ang(paramContext, paramString)).start();
  }
  
  public static final void b(String paramString)
  {
    Log.d("MzUpdateComponent", paramString);
  }
  
  public static final void c(String paramString)
  {
    Log.w("MzUpdateComponent", paramString);
  }
  
  public static final void d(String paramString)
  {
    Log.e("MzUpdateComponent", paramString);
  }
}

/* Location:
 * Qualified Name:     anf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */