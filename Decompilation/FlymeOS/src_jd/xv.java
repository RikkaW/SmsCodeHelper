import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.Button;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.MessageListItemBase;
import java.util.ArrayList;

public class xv
{
  private AlertDialog a;
  private xv.a b;
  private AlertDialog.Builder c;
  private AlertDialog d;
  private DialogInterface.OnClickListener e;
  private xv.b f;
  
  /* Error */
  public ArrayList<vv> a(vv paramvv, Context paramContext, Uri paramUri, boolean paramBoolean)
  {
    // Byte code:
    //   0: new 37	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 38	java/util/ArrayList:<init>	()V
    //   7: astore 11
    //   9: aload_1
    //   10: invokevirtual 44	vv:z	()Z
    //   13: ifeq +8 -> 21
    //   16: iload 4
    //   18: ifne +15 -> 33
    //   21: ldc 46
    //   23: aload_1
    //   24: getfield 49	vv:d	Ljava/lang/String;
    //   27: invokevirtual 55	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   30: ifne +13 -> 43
    //   33: aload 11
    //   35: aload_1
    //   36: invokevirtual 58	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   39: pop
    //   40: aload 11
    //   42: areturn
    //   43: aload_1
    //   44: getfield 62	vv:N	J
    //   47: lstore 7
    //   49: lload 7
    //   51: lconst_0
    //   52: lcmp
    //   53: ifge +6 -> 59
    //   56: aload 11
    //   58: areturn
    //   59: new 64	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   66: ldc 67
    //   68: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: lload 7
    //   73: invokevirtual 74	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   76: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: astore 9
    //   81: aload_2
    //   82: invokevirtual 84	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   85: aload_3
    //   86: getstatic 89	vx:a	[Ljava/lang/String;
    //   89: aload 9
    //   91: aconst_null
    //   92: aconst_null
    //   93: invokevirtual 95	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   96: astore 9
    //   98: aload 9
    //   100: ifnull +143 -> 243
    //   103: aload 9
    //   105: astore_3
    //   106: aload 9
    //   108: invokeinterface 101 1 0
    //   113: istore 6
    //   115: aload 9
    //   117: astore_3
    //   118: ldc 103
    //   120: invokestatic 109	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   123: astore 12
    //   125: aload 9
    //   127: astore_3
    //   128: aload 9
    //   130: invokeinterface 112 1 0
    //   135: pop
    //   136: iconst_0
    //   137: istore 5
    //   139: iload 5
    //   141: iload 6
    //   143: if_icmpge +115 -> 258
    //   146: aload 9
    //   148: astore_3
    //   149: new 40	vv
    //   152: dup
    //   153: aload_2
    //   154: aload_1
    //   155: getfield 49	vv:d	Ljava/lang/String;
    //   158: aload 9
    //   160: new 114	vx$a
    //   163: dup
    //   164: invokespecial 115	vx$a:<init>	()V
    //   167: aload 12
    //   169: invokespecial 118	vv:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/Cursor;Lvx$a;Ljava/util/regex/Pattern;)V
    //   172: astore 10
    //   174: aload 10
    //   176: ifnull +25 -> 201
    //   179: aload 9
    //   181: astore_3
    //   182: aload 10
    //   184: invokevirtual 44	vv:z	()Z
    //   187: ifeq +14 -> 201
    //   190: aload 9
    //   192: astore_3
    //   193: aload 11
    //   195: aload 10
    //   197: invokevirtual 58	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   200: pop
    //   201: aload 9
    //   203: astore_3
    //   204: aload 9
    //   206: invokeinterface 121 1 0
    //   211: pop
    //   212: iload 5
    //   214: iconst_1
    //   215: iadd
    //   216: istore 5
    //   218: goto -79 -> 139
    //   221: astore 10
    //   223: aload 9
    //   225: astore_3
    //   226: ldc 123
    //   228: aload 10
    //   230: invokevirtual 126	com/google/android/mms/MmsException:getMessage	()Ljava/lang/String;
    //   233: invokestatic 131	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   236: pop
    //   237: aconst_null
    //   238: astore 10
    //   240: goto -66 -> 174
    //   243: aload 9
    //   245: ifnull +10 -> 255
    //   248: aload 9
    //   250: invokeinterface 134 1 0
    //   255: aload 11
    //   257: areturn
    //   258: aload 9
    //   260: ifnull +10 -> 270
    //   263: aload 9
    //   265: invokeinterface 134 1 0
    //   270: aload 11
    //   272: areturn
    //   273: astore_1
    //   274: aconst_null
    //   275: astore 9
    //   277: aload 9
    //   279: astore_3
    //   280: aload_2
    //   281: aload_1
    //   282: invokestatic 139	aau:a	(Landroid/content/Context;Landroid/database/sqlite/SQLiteException;)V
    //   285: aload 9
    //   287: ifnull -17 -> 270
    //   290: aload 9
    //   292: invokeinterface 134 1 0
    //   297: goto -27 -> 270
    //   300: astore_1
    //   301: aconst_null
    //   302: astore_3
    //   303: aload_3
    //   304: ifnull +9 -> 313
    //   307: aload_3
    //   308: invokeinterface 134 1 0
    //   313: aload_1
    //   314: athrow
    //   315: astore_1
    //   316: goto -13 -> 303
    //   319: astore_1
    //   320: goto -43 -> 277
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	323	0	this	xv
    //   0	323	1	paramvv	vv
    //   0	323	2	paramContext	Context
    //   0	323	3	paramUri	Uri
    //   0	323	4	paramBoolean	boolean
    //   137	80	5	i	int
    //   113	31	6	j	int
    //   47	25	7	l	long
    //   79	212	9	localObject1	Object
    //   172	24	10	localvv	vv
    //   221	8	10	localMmsException	com.google.android.mms.MmsException
    //   238	1	10	localObject2	Object
    //   7	264	11	localArrayList	ArrayList
    //   123	45	12	localPattern	java.util.regex.Pattern
    // Exception table:
    //   from	to	target	type
    //   149	174	221	com/google/android/mms/MmsException
    //   81	98	273	android/database/sqlite/SQLiteException
    //   81	98	300	finally
    //   106	115	315	finally
    //   118	125	315	finally
    //   128	136	315	finally
    //   149	174	315	finally
    //   182	190	315	finally
    //   193	201	315	finally
    //   204	212	315	finally
    //   226	237	315	finally
    //   280	285	315	finally
    //   106	115	319	android/database/sqlite/SQLiteException
    //   118	125	319	android/database/sqlite/SQLiteException
    //   128	136	319	android/database/sqlite/SQLiteException
    //   149	174	319	android/database/sqlite/SQLiteException
    //   182	190	319	android/database/sqlite/SQLiteException
    //   193	201	319	android/database/sqlite/SQLiteException
    //   204	212	319	android/database/sqlite/SQLiteException
    //   226	237	319	android/database/sqlite/SQLiteException
  }
  
  public void a()
  {
    if ((d != null) && (d.isShowing())) {
      d.dismiss();
    }
  }
  
  public void a(Context paramContext, boolean paramBoolean)
  {
    if (c == null)
    {
      c = new AlertDialog.Builder(paramContext, 2131624313);
      c.setTitle(2131493791);
      e = new xy(this);
    }
    String[] arrayOfString;
    if (paramBoolean)
    {
      arrayOfString = new String[3];
      arrayOfString[0] = zv.a(paramContext, 0);
      arrayOfString[1] = zv.a(paramContext, 1);
      arrayOfString[2] = paramContext.getString(2131493792);
    }
    for (;;)
    {
      c.setItems(arrayOfString, e);
      if ((paramContext instanceof ComposeMessageActivity)) {
        ((ComposeMessageActivity)paramContext).d(false);
      }
      d = null;
      d = c.show();
      return;
      arrayOfString = new String[2];
      arrayOfString[0] = zv.a(paramContext, 0);
      arrayOfString[1] = zv.a(paramContext, 1);
    }
  }
  
  public final void a(MessageListItemBase paramMessageListItemBase, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Uri paramUri, int paramInt, vv paramvv)
  {
    boolean bool;
    if (paramInt == 1)
    {
      bool = true;
      paramUri = a(paramvv, paramContext, paramUri, bool);
      if ((!paramvv.t()) || (((!paramBoolean3) || (!zv.m())) && (!zv.j()))) {
        break label82;
      }
      if (f == null) {
        f = new xw(this, paramUri);
      }
      a(paramContext, paramBoolean3);
    }
    label82:
    int i;
    AlertDialog.Builder localBuilder;
    label149:
    do
    {
      return;
      bool = false;
      break;
      i = paramUri.size();
      bool = paramvv.j();
      localBuilder = new AlertDialog.Builder(paramContext);
      if (!Q) {
        break label265;
      }
      if (!bool) {
        break label256;
      }
      paramMessageListItemBase = Integer.toString(paramInt);
      localBuilder.setTitle(paramContext.getString(2131493576, new Object[] { paramMessageListItemBase }));
      paramInt = 2131493828;
      localBuilder.setPositiveButton(paramInt, new xx(this, paramUri, paramBoolean1));
    } while ((bool) && (a != null) && (a.isShowing()));
    localBuilder.setNegativeButton(paramContext.getResources().getString(2131493573), null);
    a = localBuilder.show();
    paramMessageListItemBase = a.getButton(-1);
    if ((paramBoolean2) && ((paramBoolean3) || ((zv.m()) && (!paramvv.m())))) {}
    for (paramBoolean1 = true;; paramBoolean1 = false)
    {
      paramMessageListItemBase.setEnabled(paramBoolean1);
      return;
      label256:
      paramMessageListItemBase = Integer.toString(i);
      break;
      label265:
      paramInt = 2131493166;
      localBuilder.setTitle(2131493829);
      break label149;
    }
  }
  
  public void a(xv.a parama)
  {
    b = parama;
  }
  
  public void a(xv.b paramb)
  {
    f = paramb;
  }
  
  public static abstract interface a
  {
    public abstract void a(ArrayList<vv> paramArrayList, boolean paramBoolean, int paramInt);
  }
  
  public static abstract interface b
  {
    public abstract void a(int paramInt1, String paramString, int paramInt2);
  }
}

/* Location:
 * Qualified Name:     xv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */