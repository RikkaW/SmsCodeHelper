package com.xiaomi.mipush.sdk;

import android.content.Context;

final class MiPushClient$3
  implements Runnable
{
  MiPushClient$3(Context paramContext) {}
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
    //   4: invokestatic 29	com/xiaomi/mipush/sdk/MiPushClient:shouldUseMIUIPush	(Landroid/content/Context;)Z
    //   7: ifne +211 -> 218
    //   10: iconst_1
    //   11: aload_0
    //   12: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
    //   15: invokestatic 35	com/xiaomi/mipush/sdk/AppInfoHolder:getInstance	(Landroid/content/Context;)Lcom/xiaomi/mipush/sdk/AppInfoHolder;
    //   18: invokevirtual 39	com/xiaomi/mipush/sdk/AppInfoHolder:getEnvType	()I
    //   21: if_icmpne +197 -> 218
    //   24: aload_0
    //   25: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
    //   28: invokevirtual 45	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   31: iconst_4
    //   32: invokevirtual 51	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   35: astore 4
    //   37: aload 4
    //   39: ifnull +179 -> 218
    //   42: aload 4
    //   44: invokeinterface 57 1 0
    //   49: astore 4
    //   51: aload 4
    //   53: invokeinterface 63 1 0
    //   58: ifeq +160 -> 218
    //   61: aload 4
    //   63: invokeinterface 67 1 0
    //   68: checkcast 69	android/content/pm/PackageInfo
    //   71: getfield 73	android/content/pm/PackageInfo:services	[Landroid/content/pm/ServiceInfo;
    //   74: astore 5
    //   76: aload 5
    //   78: ifnull -27 -> 51
    //   81: aload 5
    //   83: arraylength
    //   84: istore_2
    //   85: iconst_0
    //   86: istore_1
    //   87: iload_1
    //   88: iload_2
    //   89: if_icmpge -38 -> 51
    //   92: aload 5
    //   94: iload_1
    //   95: aaload
    //   96: astore 6
    //   98: aload 6
    //   100: getfield 79	android/content/pm/ServiceInfo:exported	Z
    //   103: ifeq +106 -> 209
    //   106: aload 6
    //   108: getfield 82	android/content/pm/ServiceInfo:enabled	Z
    //   111: ifeq +98 -> 209
    //   114: ldc 84
    //   116: aload 6
    //   118: getfield 88	android/content/pm/ServiceInfo:name	Ljava/lang/String;
    //   121: invokevirtual 94	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   124: ifeq +85 -> 209
    //   127: aload_0
    //   128: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
    //   131: invokevirtual 98	android/content/Context:getPackageName	()Ljava/lang/String;
    //   134: aload 6
    //   136: getfield 101	android/content/pm/ServiceInfo:packageName	Ljava/lang/String;
    //   139: invokevirtual 94	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   142: istore_3
    //   143: iload_3
    //   144: ifne +65 -> 209
    //   147: invokestatic 107	java/lang/Math:random	()D
    //   150: ldc2_w 108
    //   153: dmul
    //   154: ldc2_w 110
    //   157: dadd
    //   158: d2l
    //   159: ldc2_w 112
    //   162: lmul
    //   163: invokestatic 119	java/lang/Thread:sleep	(J)V
    //   166: new 121	android/content/Intent
    //   169: dup
    //   170: invokespecial 122	android/content/Intent:<init>	()V
    //   173: astore 7
    //   175: aload 7
    //   177: aload 6
    //   179: getfield 101	android/content/pm/ServiceInfo:packageName	Ljava/lang/String;
    //   182: aload 6
    //   184: getfield 88	android/content/pm/ServiceInfo:name	Ljava/lang/String;
    //   187: invokevirtual 126	android/content/Intent:setClassName	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   190: pop
    //   191: aload 7
    //   193: ldc -128
    //   195: invokevirtual 132	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   198: pop
    //   199: aload_0
    //   200: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
    //   203: aload 7
    //   205: invokevirtual 136	android/content/Context:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   208: pop
    //   209: iload_1
    //   210: iconst_1
    //   211: iadd
    //   212: istore_1
    //   213: goto -126 -> 87
    //   216: astore 4
    //   218: return
    //   219: astore 7
    //   221: goto -55 -> 166
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	224	0	this	3
    //   86	127	1	i	int
    //   84	6	2	j	int
    //   142	2	3	bool	boolean
    //   35	27	4	localObject	Object
    //   216	1	4	localThrowable	Throwable
    //   74	19	5	arrayOfServiceInfo	android.content.pm.ServiceInfo[]
    //   96	87	6	localServiceInfo	android.content.pm.ServiceInfo
    //   173	31	7	localIntent	android.content.Intent
    //   219	1	7	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   24	37	216	java/lang/Throwable
    //   42	51	216	java/lang/Throwable
    //   51	76	216	java/lang/Throwable
    //   81	85	216	java/lang/Throwable
    //   98	143	216	java/lang/Throwable
    //   147	166	216	java/lang/Throwable
    //   166	209	216	java/lang/Throwable
    //   147	166	219	java/lang/InterruptedException
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MiPushClient.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */