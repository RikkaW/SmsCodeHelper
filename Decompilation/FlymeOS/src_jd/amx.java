import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.update.service.MzUpdateComponentService;

public class amx
{
  public static void a(Context paramContext)
  {
    if ((!TextUtils.isEmpty(PushManager.getPushId(paramContext))) && (!b(paramContext))) {
      MzUpdateComponentService.b(paramContext);
    }
  }
  
  public static final void a(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = c(paramContext).edit();
    if (paramBoolean) {
      localEditor.putString("push_version", anl.a(paramContext));
    }
    for (;;)
    {
      localEditor.apply();
      return;
      localEditor.putString("push_version", "");
    }
  }
  
  /* Error */
  public static boolean a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 67	android/content/Context:getPackageName	()Ljava/lang/String;
    //   4: astore_3
    //   5: new 69	org/json/JSONObject
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 73	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   13: astore 4
    //   15: aload 4
    //   17: aload_3
    //   18: invokevirtual 77	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   21: istore_2
    //   22: iload_2
    //   23: ifeq +62 -> 85
    //   26: aload 4
    //   28: aload_3
    //   29: invokevirtual 81	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   32: ldc 83
    //   34: invokevirtual 87	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   37: astore 4
    //   39: aload_0
    //   40: invokestatic 92	and:a	(Landroid/content/Context;)Land;
    //   43: getstatic 97	and$a:a	Land$a;
    //   46: aload 4
    //   48: aload_0
    //   49: aload_3
    //   50: invokestatic 100	anl:b	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   53: invokevirtual 103	and:a	(Land$a;Ljava/lang/String;Ljava/lang/String;)V
    //   56: iconst_1
    //   57: ireturn
    //   58: astore_0
    //   59: aload_0
    //   60: invokevirtual 106	org/json/JSONException:printStackTrace	()V
    //   63: new 108	java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   70: ldc 112
    //   72: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload_1
    //   76: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: invokestatic 123	anf:c	(Ljava/lang/String;)V
    //   85: iconst_0
    //   86: ireturn
    //   87: astore_0
    //   88: goto -32 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	paramContext	Context
    //   0	91	1	paramString	String
    //   21	2	2	bool	boolean
    //   4	46	3	str	String
    //   13	34	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	22	58	org/json/JSONException
    //   26	56	58	org/json/JSONException
    //   26	56	87	java/lang/Exception
  }
  
  public static final void b(Context paramContext, String paramString)
  {
    paramContext = c(paramContext).edit();
    paramContext.putString("skip_version", paramString);
    paramContext.apply();
  }
  
  public static final boolean b(Context paramContext)
  {
    String str = c(paramContext).getString("push_version", null);
    if (!TextUtils.isEmpty(str)) {
      return str.equals(anl.a(paramContext));
    }
    return false;
  }
  
  public static final SharedPreferences c(Context paramContext)
  {
    return paramContext.getSharedPreferences("mz_update_component_history", 0);
  }
  
  public static final boolean c(Context paramContext, String paramString)
  {
    paramContext = c(paramContext).getString("skip_version", null);
    return (!TextUtils.isEmpty(paramContext)) && (paramContext.equals(paramString));
  }
  
  public static final void d(Context paramContext)
  {
    if (!e(paramContext)) {
      anf.c("cloud server not enable, skip register");
    }
    do
    {
      return;
      if (TextUtils.isEmpty(PushManager.getPushId(paramContext)))
      {
        anf.b(paramContext, "Request sip register");
        PushManager.register(paramContext);
        return;
      }
    } while (b(paramContext));
    MzUpdateComponentService.b(paramContext);
  }
  
  public static final boolean e(Context paramContext)
  {
    paramContext = anl.b(paramContext, "com.meizu.cloud");
    if (!TextUtils.isEmpty(paramContext))
    {
      paramContext = paramContext.split("\\.");
      if (paramContext.length < 2) {}
    }
    try
    {
      int i = Integer.parseInt(paramContext[0]);
      int j = Integer.parseInt(paramContext[1]);
      if (i > 4) {}
      while ((i == 4) && (j > 5)) {
        return true;
      }
      return false;
    }
    catch (NumberFormatException paramContext) {}
    return false;
    return true;
  }
}

/* Location:
 * Qualified Name:     amx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */