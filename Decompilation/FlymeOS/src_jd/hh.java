import java.util.HashMap;

class hh
  implements Runnable
{
  hh(hb paramhb, gr paramgr, boolean paramBoolean, HashMap paramHashMap) {}
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: invokestatic 34	zt:c	()Lzt;
    //   3: iconst_1
    //   4: invokevirtual 37	zt:a	(Z)V
    //   7: aload_0
    //   8: getfield 18	hh:d	Lhb;
    //   11: invokestatic 42	hb:a	(Lhb;)Lcom/android/mms/ui/ComposeMessageActivity;
    //   14: invokestatic 48	com/meizu/android/mms/pdu/MzPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;
    //   17: astore_1
    //   18: aload_0
    //   19: getfield 20	hh:a	Lgr;
    //   22: aload_0
    //   23: getfield 18	hh:d	Lhb;
    //   26: invokestatic 51	hb:b	(Lhb;)Ljava/lang/CharSequence;
    //   29: invokestatic 54	hb:a	(Lgr;Ljava/lang/CharSequence;)Lcom/meizu/android/mms/pdu/MzSendReq;
    //   32: astore_2
    //   33: aload_0
    //   34: getfield 18	hh:d	Lhb;
    //   37: aload_0
    //   38: getfield 20	hh:a	Lgr;
    //   41: aload_0
    //   42: getfield 22	hh:b	Z
    //   45: invokestatic 57	hb:a	(Lhb;Lgr;Z)V
    //   48: aload_0
    //   49: getfield 20	hh:a	Lgr;
    //   52: iconst_1
    //   53: invokevirtual 61	gr:b	(Z)V
    //   56: aload_0
    //   57: getfield 18	hh:d	Lhb;
    //   60: invokestatic 64	hb:c	(Lhb;)Landroid/net/Uri;
    //   63: ifnonnull +115 -> 178
    //   66: aload_0
    //   67: getfield 18	hh:d	Lhb;
    //   70: aload_1
    //   71: aload_2
    //   72: aload_0
    //   73: getfield 18	hh:d	Lhb;
    //   76: invokestatic 67	hb:d	(Lhb;)Llr;
    //   79: aconst_null
    //   80: aload_0
    //   81: getfield 18	hh:d	Lhb;
    //   84: invokestatic 42	hb:a	(Lhb;)Lcom/android/mms/ui/ComposeMessageActivity;
    //   87: aload_0
    //   88: getfield 24	hh:c	Ljava/util/HashMap;
    //   91: iconst_1
    //   92: invokestatic 70	hb:a	(Lcom/meizu/android/mms/pdu/MzPduPersister;Lcom/meizu/android/mms/pdu/MzSendReq;Llr;Landroid/net/Uri;Landroid/content/Context;Ljava/util/HashMap;Z)Landroid/net/Uri;
    //   95: invokestatic 73	hb:b	(Lhb;Landroid/net/Uri;)Landroid/net/Uri;
    //   98: pop
    //   99: ldc 75
    //   101: iconst_2
    //   102: invokestatic 81	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   105: ifeq +47 -> 152
    //   108: new 83	java/lang/StringBuilder
    //   111: dup
    //   112: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   115: ldc 86
    //   117: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: aload_0
    //   121: getfield 20	hh:a	Lgr;
    //   124: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   127: ldc 95
    //   129: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_0
    //   133: getfield 18	hh:d	Lhb;
    //   136: invokestatic 64	hb:c	(Lhb;)Landroid/net/Uri;
    //   139: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: iconst_0
    //   146: anewarray 4	java/lang/Object
    //   149: invokestatic 104	fl:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   152: aload_0
    //   153: getfield 18	hh:d	Lhb;
    //   156: aload_0
    //   157: getfield 20	hh:a	Lgr;
    //   160: invokevirtual 107	hb:b	(Lgr;)V
    //   163: invokestatic 34	zt:c	()Lzt;
    //   166: iconst_0
    //   167: invokevirtual 37	zt:a	(Z)V
    //   170: aload_0
    //   171: getfield 24	hh:c	Ljava/util/HashMap;
    //   174: invokestatic 110	hb:a	(Ljava/util/HashMap;)V
    //   177: return
    //   178: aload_0
    //   179: getfield 18	hh:d	Lhb;
    //   182: invokestatic 64	hb:c	(Lhb;)Landroid/net/Uri;
    //   185: aload_1
    //   186: aload_0
    //   187: getfield 18	hh:d	Lhb;
    //   190: invokestatic 67	hb:d	(Lhb;)Llr;
    //   193: aload_2
    //   194: aload_0
    //   195: getfield 24	hh:c	Ljava/util/HashMap;
    //   198: iconst_1
    //   199: invokestatic 113	hb:a	(Landroid/net/Uri;Lcom/meizu/android/mms/pdu/MzPduPersister;Llr;Lcom/meizu/android/mms/pdu/MzSendReq;Ljava/util/HashMap;Z)V
    //   202: goto -103 -> 99
    //   205: astore_1
    //   206: invokestatic 34	zt:c	()Lzt;
    //   209: iconst_0
    //   210: invokevirtual 37	zt:a	(Z)V
    //   213: aload_0
    //   214: getfield 24	hh:c	Ljava/util/HashMap;
    //   217: invokestatic 110	hb:a	(Ljava/util/HashMap;)V
    //   220: aload_1
    //   221: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	222	0	this	hh
    //   17	169	1	localMzPduPersister	com.meizu.android.mms.pdu.MzPduPersister
    //   205	16	1	localObject	Object
    //   32	162	2	localMzSendReq	com.meizu.android.mms.pdu.MzSendReq
    // Exception table:
    //   from	to	target	type
    //   0	99	205	finally
    //   99	152	205	finally
    //   152	163	205	finally
    //   178	202	205	finally
  }
}

/* Location:
 * Qualified Name:     hh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */