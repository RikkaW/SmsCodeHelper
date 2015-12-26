import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;

public class fl
{
  public static final boolean a;
  
  static
  {
    if (Build.TYPE.contentEquals("eng")) {}
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      return;
    }
  }
  
  private static String a(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length == 0) {
      return "[]";
    }
    StringBuilder localStringBuilder = new StringBuilder("[");
    int j = paramArrayOfString.length - 1;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(paramArrayOfString[i]);
      localStringBuilder.append(", ");
      i += 1;
    }
    localStringBuilder.append(paramArrayOfString[j]);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public static void a(Context paramContext) {}
  
  public static void a(String paramString, Activity paramActivity)
  {
    Log.e("Mms", "WARNING!!!! " + paramString, new RuntimeException());
  }
  
  public static void a(String paramString, Object... paramVarArgs)
  {
    Log.d("Mms", d(paramString, paramVarArgs));
  }
  
  public static void b(String paramString, Object... paramVarArgs)
  {
    Log.w("Mms", d(paramString, paramVarArgs));
  }
  
  public static void c(String paramString, Object... paramVarArgs)
  {
    Log.e("Mms", d(paramString, paramVarArgs));
  }
  
  private static String d(String paramString, Object... paramVarArgs)
  {
    int i = 0;
    while (i < paramVarArgs.length)
    {
      if ((paramVarArgs[i] instanceof String[])) {
        paramVarArgs[i] = a((String[])(String[])paramVarArgs[i]);
      }
      i += 1;
    }
    paramString = String.format(paramString, paramVarArgs);
    return "[" + Thread.currentThread().getId() + "] " + paramString;
  }
}

/* Location:
 * Qualified Name:     fl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */