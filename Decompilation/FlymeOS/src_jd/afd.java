import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class afd
{
  protected final String a = "ISO-8859-1";
  protected String b;
  protected String c;
  protected afd.a d;
  protected final Set<String> e = new HashSet();
  protected final Set<String> f = new HashSet();
  private final List<afb> g = new ArrayList();
  private boolean h;
  
  public afd()
  {
    this(aex.b);
  }
  
  public afd(int paramInt) {}
  
  static String a(char paramChar)
  {
    if ((paramChar == '\\') || (paramChar == ';') || (paramChar == ':') || (paramChar == ',')) {
      return String.valueOf(paramChar);
    }
    return null;
  }
  
  private void a(afj paramafj, String paramString1, String paramString2, String paramString3)
  {
    ArrayList localArrayList = new ArrayList();
    if (b.equals("QUOTED-PRINTABLE"))
    {
      paramString1 = e(paramString1);
      paramafj.c(paramString1);
      paramString1 = afl.a(paramString1, f()).iterator();
      while (paramString1.hasNext()) {
        localArrayList.add(afl.a((String)paramString1.next(), false, paramString2, paramString3));
      }
    }
    paramString1 = afl.a(f(paramString1), f()).iterator();
    while (paramString1.hasNext()) {
      localArrayList.add(afl.a((String)paramString1.next(), paramString2, paramString3));
    }
    paramafj.a(localArrayList);
    paramString1 = g.iterator();
    while (paramString1.hasNext()) {
      ((afb)paramString1.next()).a(paramafj);
    }
  }
  
  private boolean b(char paramChar)
  {
    return ((paramChar >= 'a') && (paramChar <= 'z')) || ((paramChar >= 'A') && (paramChar <= 'Z'));
  }
  
  private String e(String paramString)
  {
    Object localObject = paramString;
    if (paramString.trim().endsWith("="))
    {
      int i = paramString.length() - 1;
      while (paramString.charAt(i) != '=') {}
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString.substring(0, i + 1));
      ((StringBuilder)localObject).append("\r\n");
      for (;;)
      {
        paramString = a();
        if (paramString == null) {
          throw new afn("File ended during parsing a Quoted-Printable String");
        }
        if (!paramString.trim().endsWith("=")) {
          break;
        }
        i = paramString.length() - 1;
        while (paramString.charAt(i) != '=') {}
        ((StringBuilder)localObject).append(paramString.substring(0, i + 1));
        ((StringBuilder)localObject).append("\r\n");
      }
      ((StringBuilder)localObject).append(paramString);
      localObject = ((StringBuilder)localObject).toString();
    }
    return (String)localObject;
  }
  
  private String f(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    for (;;)
    {
      paramString = b();
      if ((paramString == null) || (paramString.length() == 0)) {}
      while (g(paramString) != null) {
        return localStringBuilder.toString();
      }
      a();
      localStringBuilder.append(" ").append(paramString);
    }
  }
  
  private String g(String paramString)
  {
    int i = paramString.indexOf(":");
    if (i > -1)
    {
      int j = paramString.indexOf(";");
      if (i == -1) {
        i = j;
      }
      for (;;)
      {
        return paramString.substring(0, i).toUpperCase();
        if (j != -1) {
          i = Math.min(i, j);
        }
      }
    }
    return null;
  }
  
  private void i(afj paramafj, String paramString)
  {
    String str = paramafj.c();
    if (paramString.equals("AGENT"))
    {
      a(paramafj);
      return;
    }
    if (a(paramString))
    {
      if ((paramString.equals("VERSION")) && (!str.equals(g()))) {
        throw new afs("Incompatible version: " + str + " != " + g());
      }
      h(paramafj, paramString);
      return;
    }
    throw new afn("Unknown property name: \"" + paramString + "\"");
  }
  
  private boolean l()
  {
    b = "8BIT";
    c = "UTF-8";
    if (!a(false)) {
      return false;
    }
    Iterator localIterator = g.iterator();
    while (localIterator.hasNext()) {
      ((afb)localIterator.next()).c();
    }
    d();
    localIterator = g.iterator();
    while (localIterator.hasNext()) {
      ((afb)localIterator.next()).d();
    }
    return true;
  }
  
  private void m()
  {
    Iterator localIterator = g.iterator();
    while (localIterator.hasNext()) {
      ((afb)localIterator.next()).c();
    }
    d();
    localIterator = g.iterator();
    while (localIterator.hasNext()) {
      ((afb)localIterator.next()).d();
    }
  }
  
  protected String a()
  {
    return d.readLine();
  }
  
  public void a(afb paramafb)
  {
    g.add(paramafb);
  }
  
  protected void a(afj paramafj)
  {
    if (!paramafj.c().toUpperCase().contains("BEGIN:VCARD"))
    {
      Iterator localIterator = g.iterator();
      while (localIterator.hasNext()) {
        ((afb)localIterator.next()).a(paramafj);
      }
    }
    throw new afm("AGENT Property is not supported now.");
  }
  
  protected void a(afj paramafj, String paramString)
  {
    Object localObject = paramString.split("=", 2);
    if (localObject.length == 2)
    {
      paramString = localObject[0].trim().toUpperCase();
      localObject = localObject[1].trim();
      if (paramString.equals("TYPE"))
      {
        c(paramafj, (String)localObject);
        return;
      }
      if (paramString.equals("VALUE"))
      {
        d(paramafj, (String)localObject);
        return;
      }
      if (paramString.equals("ENCODING"))
      {
        e(paramafj, ((String)localObject).toUpperCase());
        return;
      }
      if (paramString.equals("CHARSET"))
      {
        f(paramafj, (String)localObject);
        return;
      }
      if (paramString.equals("LANGUAGE"))
      {
        g(paramafj, (String)localObject);
        return;
      }
      if (paramString.startsWith("X-"))
      {
        a(paramafj, paramString, (String)localObject);
        return;
      }
      throw new afn("Unknown type \"" + paramString + "\"");
    }
    b(paramafj, localObject[0]);
  }
  
  protected void a(afj paramafj, String paramString1, String paramString2)
  {
    paramafj.a(paramString1, paramString2);
  }
  
  public void a(InputStream paramInputStream)
  {
    if (paramInputStream == null) {
      throw new NullPointerException("InputStream must not be null.");
    }
    d = new afd.a(new InputStreamReader(paramInputStream, a));
    System.currentTimeMillis();
    paramInputStream = g.iterator();
    while (paramInputStream.hasNext()) {
      ((afb)paramInputStream.next()).a();
    }
    for (;;)
    {
      try
      {
        if (h) {
          Log.i("vCard", "Cancel request has come. exitting parse operation.");
        }
        for (;;)
        {
          paramInputStream = g.iterator();
          while (paramInputStream.hasNext()) {
            ((afb)paramInputStream.next()).b();
          }
          if (l()) {
            break;
          }
        }
        return;
      }
      finally {}
    }
  }
  
  protected boolean a(String paramString)
  {
    if ((!h().contains(paramString.toUpperCase())) && (!paramString.startsWith("X-")) && (!e.contains(paramString)))
    {
      e.add(paramString);
      Log.w("vCard", "Property name unsupported by vCard 2.1: " + paramString);
    }
    return true;
  }
  
  protected boolean a(boolean paramBoolean)
  {
    do
    {
      String str;
      do
      {
        str = a();
        if (str == null) {
          return false;
        }
      } while (str.trim().length() <= 0);
      String[] arrayOfString = str.split(":", 2);
      if ((arrayOfString.length == 2) && (arrayOfString[0].trim().equalsIgnoreCase("BEGIN")) && (arrayOfString[1].trim().equalsIgnoreCase("VCARD"))) {
        return true;
      }
      if (!paramBoolean) {
        throw new afn("Expected String \"BEGIN:VCARD\" did not come (Instead, \"" + str + "\" came)");
      }
    } while (paramBoolean);
    throw new afn("Reached where must not be reached.");
  }
  
  protected afj b(String paramString)
  {
    int m = 0;
    afj localafj = new afj();
    int n = paramString.length();
    if ((n > 0) && (paramString.charAt(0) == '#')) {
      throw new afo();
    }
    int k = 0;
    int j = 0;
    if (k < n)
    {
      int i1 = paramString.charAt(k);
      int i;
      switch (j)
      {
      default: 
        i = m;
      }
      for (;;)
      {
        k += 1;
        m = i;
        break;
        if (i1 == 58)
        {
          localafj.a(paramString.substring(m, k));
          if (k < n - 1) {}
          for (paramString = paramString.substring(k + 1);; paramString = "")
          {
            localafj.c(paramString);
            return localafj;
          }
        }
        if (i1 == 46)
        {
          String str = paramString.substring(m, k);
          if (str.length() == 0) {
            Log.w("vCard", "Empty group found. Ignoring.");
          }
          for (;;)
          {
            i = k + 1;
            break;
            localafj.b(str);
          }
        }
        i = m;
        if (i1 == 59)
        {
          localafj.a(paramString.substring(m, k));
          i = k + 1;
          j = 1;
          continue;
          if (i1 == 34)
          {
            if ("2.1".equalsIgnoreCase(g())) {
              Log.w("vCard", "Double-quoted params found in vCard 2.1. Silently allow it");
            }
            j = 2;
            i = m;
          }
          else if (i1 == 59)
          {
            a(localafj, paramString.substring(m, k));
            i = k + 1;
          }
          else
          {
            i = m;
            if (i1 == 58)
            {
              a(localafj, paramString.substring(m, k));
              if (k < n - 1) {}
              for (paramString = paramString.substring(k + 1);; paramString = "")
              {
                localafj.c(paramString);
                return localafj;
              }
              i = m;
              if (i1 == 34)
              {
                if ("2.1".equalsIgnoreCase(g())) {
                  Log.w("vCard", "Double-quoted params found in vCard 2.1. Silently allow it");
                }
                j = 1;
                i = m;
              }
            }
          }
        }
      }
    }
    throw new afp("Invalid line: \"" + paramString + "\"");
  }
  
  protected String b()
  {
    return d.a();
  }
  
  protected void b(afj paramafj, String paramString)
  {
    c(paramafj, paramString);
  }
  
  protected String c()
  {
    String str;
    do
    {
      str = a();
      if (str == null) {
        throw new afn("Reached end of buffer.");
      }
    } while (str.trim().length() <= 0);
    return str;
  }
  
  protected String c(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    for (;;)
    {
      paramString = b();
      if (paramString == null) {
        throw new afn("File ended during parsing BASE64 binary");
      }
      String str = g(paramString);
      if ((h().contains(str)) || ("X-ANDROID-CUSTOM".equals(str)))
      {
        Log.w("vCard", "Found a next property during parsing a BASE64 string, which must not contain semi-colon or colon. Treat the line as next property.");
        Log.w("vCard", "Problematic line: " + paramString.trim());
      }
      do
      {
        return localStringBuilder.toString();
        a();
      } while (paramString.length() == 0);
      localStringBuilder.append(paramString.trim());
    }
  }
  
  protected void c(afj paramafj, String paramString)
  {
    if ((!i().contains(paramString.toUpperCase())) && (!paramString.startsWith("X-")) && (!e.contains(paramString)))
    {
      e.add(paramString);
      Log.w("vCard", String.format("TYPE unsupported by %s: ", new Object[] { Integer.valueOf(f()), paramString }));
    }
    paramafj.a("TYPE", paramString);
  }
  
  protected String d(String paramString)
  {
    return paramString;
  }
  
  protected void d()
  {
    int i = 0;
    try
    {
      bool = e();
      i = bool;
    }
    catch (afo localafo1)
    {
      for (;;)
      {
        try
        {
          boolean bool = e();
          i = bool;
        }
        catch (afo localafo2)
        {
          Log.e("vCard", "Invalid line which looks like some comment was found. Ignored.");
        }
        localafo1 = localafo1;
        Log.e("vCard", "Invalid line which looks like some comment was found. Ignored.");
      }
    }
    if (i == 0) {}
  }
  
  protected void d(afj paramafj, String paramString)
  {
    if ((!j().contains(paramString.toUpperCase())) && (!paramString.startsWith("X-")) && (!f.contains(paramString)))
    {
      f.add(paramString);
      Log.w("vCard", String.format("The value unsupported by TYPE of %s: ", new Object[] { Integer.valueOf(f()), paramString }));
    }
    paramafj.a("VALUE", paramString);
  }
  
  protected void e(afj paramafj, String paramString)
  {
    if ((k().contains(paramString)) || (paramString.startsWith("X-")))
    {
      paramafj.a("ENCODING", paramString);
      b = paramString.toUpperCase();
      return;
    }
    throw new afn("Unknown encoding \"" + paramString + "\"");
  }
  
  protected boolean e()
  {
    b = "8BIT";
    afj localafj = b(c());
    String str1 = localafj.a().toUpperCase();
    String str2 = localafj.c();
    if (str1.equals("BEGIN")) {
      if (str2.equalsIgnoreCase("VCARD")) {
        m();
      }
    }
    for (;;)
    {
      return false;
      throw new afn("Unknown BEGIN type: " + str2);
      if (str1.equals("END"))
      {
        if (str2.equalsIgnoreCase("VCARD")) {
          return true;
        }
        throw new afn("Unknown END type: " + str2);
      }
      i(localafj, str1);
    }
  }
  
  protected int f()
  {
    return 0;
  }
  
  protected void f(afj paramafj, String paramString)
  {
    c = paramString;
    paramafj.a("CHARSET", paramString);
  }
  
  protected String g()
  {
    return "2.1";
  }
  
  protected void g(afj paramafj, String paramString)
  {
    int j = 0;
    Object localObject1 = paramString.split("-");
    if (localObject1.length != 2) {
      throw new afn("Invalid Language: \"" + paramString + "\"");
    }
    Object localObject2 = localObject1[0];
    int k = ((String)localObject2).length();
    int i = 0;
    while (i < k)
    {
      if (!b(((String)localObject2).charAt(i))) {
        throw new afn("Invalid Language: \"" + paramString + "\"");
      }
      i += 1;
    }
    localObject1 = localObject1[1];
    k = ((String)localObject1).length();
    i = j;
    while (i < k)
    {
      if (!b(((String)localObject1).charAt(i))) {
        throw new afn("Invalid Language: \"" + paramString + "\"");
      }
      i += 1;
    }
    paramafj.a("LANGUAGE", paramString);
  }
  
  protected Set<String> h()
  {
    return afg.a;
  }
  
  /* Error */
  protected void h(afj paramafj, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_1
    //   4: invokevirtual 413	afj:a	()Ljava/lang/String;
    //   7: invokevirtual 175	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   10: astore 6
    //   12: aload_1
    //   13: invokevirtual 185	afj:c	()Ljava/lang/String;
    //   16: astore 5
    //   18: aload_1
    //   19: ldc_w 258
    //   22: invokevirtual 445	afj:d	(Ljava/lang/String;)Ljava/util/Collection;
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull +74 -> 101
    //   30: aload_2
    //   31: invokeinterface 448 1 0
    //   36: invokeinterface 97 1 0
    //   41: checkcast 53	java/lang/String
    //   44: astore_2
    //   45: aload_2
    //   46: astore_3
    //   47: aload_2
    //   48: invokestatic 453	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   51: ifeq +6 -> 57
    //   54: ldc -44
    //   56: astore_3
    //   57: aload 6
    //   59: ldc_w 455
    //   62: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   65: ifne +25 -> 90
    //   68: aload 6
    //   70: ldc_w 457
    //   73: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   76: ifne +14 -> 90
    //   79: aload 6
    //   81: ldc_w 459
    //   84: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   87: ifeq +19 -> 106
    //   90: aload_0
    //   91: aload_1
    //   92: aload 5
    //   94: ldc 48
    //   96: aload_3
    //   97: invokespecial 461	afd:a	(Lafj;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   100: return
    //   101: aconst_null
    //   102: astore_2
    //   103: goto -58 -> 45
    //   106: aload_0
    //   107: getfield 59	afd:b	Ljava/lang/String;
    //   110: ldc 61
    //   112: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   115: ifne +31 -> 146
    //   118: aload 6
    //   120: ldc_w 463
    //   123: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   126: ifeq +90 -> 216
    //   129: aload_1
    //   130: ldc -2
    //   132: invokevirtual 445	afj:d	(Ljava/lang/String;)Ljava/util/Collection;
    //   135: ifnonnull +81 -> 216
    //   138: aload 5
    //   140: invokestatic 464	afl:a	(Ljava/lang/String;)Z
    //   143: ifeq +73 -> 216
    //   146: aload_0
    //   147: aload 5
    //   149: invokespecial 68	afd:e	(Ljava/lang/String;)Ljava/lang/String;
    //   152: astore_2
    //   153: aload_2
    //   154: iconst_0
    //   155: ldc 48
    //   157: aload_3
    //   158: invokestatic 100	afl:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   161: astore_3
    //   162: aload_1
    //   163: aload_2
    //   164: invokevirtual 73	afj:c	(Ljava/lang/String;)V
    //   167: aload_1
    //   168: iconst_1
    //   169: anewarray 53	java/lang/String
    //   172: dup
    //   173: iconst_0
    //   174: aload_3
    //   175: aastore
    //   176: invokevirtual 467	afj:a	([Ljava/lang/String;)V
    //   179: aload_0
    //   180: getfield 39	afd:g	Ljava/util/List;
    //   183: invokeinterface 87 1 0
    //   188: astore_2
    //   189: aload_2
    //   190: invokeinterface 93 1 0
    //   195: ifeq -95 -> 100
    //   198: aload_2
    //   199: invokeinterface 97 1 0
    //   204: checkcast 113	afb
    //   207: aload_1
    //   208: invokeinterface 116 2 0
    //   213: goto -24 -> 189
    //   216: aload_0
    //   217: getfield 59	afd:b	Ljava/lang/String;
    //   220: ldc_w 469
    //   223: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   226: ifne +16 -> 242
    //   229: aload_0
    //   230: getfield 59	afd:b	Ljava/lang/String;
    //   233: ldc_w 471
    //   236: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   239: ifeq +134 -> 373
    //   242: aload_0
    //   243: aload 5
    //   245: invokevirtual 473	afd:c	(Ljava/lang/String;)Ljava/lang/String;
    //   248: astore_2
    //   249: aload_1
    //   250: aload_2
    //   251: iconst_0
    //   252: invokestatic 479	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   255: invokevirtual 482	afj:a	([B)V
    //   258: aload_0
    //   259: getfield 39	afd:g	Ljava/util/List;
    //   262: invokeinterface 87 1 0
    //   267: astore_2
    //   268: aload_2
    //   269: invokeinterface 93 1 0
    //   274: ifeq -174 -> 100
    //   277: aload_2
    //   278: invokeinterface 97 1 0
    //   283: checkcast 113	afb
    //   286: aload_1
    //   287: invokeinterface 116 2 0
    //   292: goto -24 -> 268
    //   295: astore_2
    //   296: ldc_w 305
    //   299: ldc_w 484
    //   302: invokestatic 399	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   305: pop
    //   306: aload_0
    //   307: getfield 39	afd:g	Ljava/util/List;
    //   310: invokeinterface 87 1 0
    //   315: astore_2
    //   316: aload_2
    //   317: invokeinterface 93 1 0
    //   322: ifeq -222 -> 100
    //   325: aload_2
    //   326: invokeinterface 97 1 0
    //   331: checkcast 113	afb
    //   334: aload_1
    //   335: invokeinterface 116 2 0
    //   340: goto -24 -> 316
    //   343: astore_2
    //   344: new 151	afn
    //   347: dup
    //   348: new 136	java/lang/StringBuilder
    //   351: dup
    //   352: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   355: ldc_w 486
    //   358: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: aload 5
    //   363: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   369: invokespecial 155	afn:<init>	(Ljava/lang/String;)V
    //   372: athrow
    //   373: aload_0
    //   374: getfield 59	afd:b	Ljava/lang/String;
    //   377: ldc_w 488
    //   380: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   383: ifne +59 -> 442
    //   386: aload_0
    //   387: getfield 59	afd:b	Ljava/lang/String;
    //   390: ldc -46
    //   392: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   395: ifne +47 -> 442
    //   398: aload_0
    //   399: getfield 59	afd:b	Ljava/lang/String;
    //   402: ldc_w 266
    //   405: invokevirtual 269	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   408: ifne +34 -> 442
    //   411: ldc_w 305
    //   414: ldc_w 490
    //   417: iconst_2
    //   418: anewarray 4	java/lang/Object
    //   421: dup
    //   422: iconst_0
    //   423: aload_0
    //   424: getfield 59	afd:b	Ljava/lang/String;
    //   427: aastore
    //   428: dup
    //   429: iconst_1
    //   430: aload_0
    //   431: invokevirtual 194	afd:g	()Ljava/lang/String;
    //   434: aastore
    //   435: invokestatic 393	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   438: invokestatic 329	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   441: pop
    //   442: aload_0
    //   443: invokevirtual 76	afd:f	()I
    //   446: ifne +170 -> 616
    //   449: aload 4
    //   451: astore_2
    //   452: aload_0
    //   453: invokevirtual 160	afd:b	()Ljava/lang/String;
    //   456: astore 6
    //   458: aload 6
    //   460: invokestatic 453	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   463: ifne +75 -> 538
    //   466: aload 6
    //   468: iconst_0
    //   469: invokevirtual 134	java/lang/String:charAt	(I)C
    //   472: bipush 32
    //   474: if_icmpne +64 -> 538
    //   477: ldc_w 492
    //   480: aload 6
    //   482: invokevirtual 175	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   485: invokevirtual 235	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   488: ifne +50 -> 538
    //   491: aload_0
    //   492: invokevirtual 149	afd:a	()Ljava/lang/String;
    //   495: pop
    //   496: aload_2
    //   497: astore 4
    //   499: aload_2
    //   500: ifnonnull +20 -> 520
    //   503: new 136	java/lang/StringBuilder
    //   506: dup
    //   507: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   510: astore 4
    //   512: aload 4
    //   514: aload 5
    //   516: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   519: pop
    //   520: aload 4
    //   522: aload 6
    //   524: iconst_1
    //   525: invokevirtual 352	java/lang/String:substring	(I)Ljava/lang/String;
    //   528: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: pop
    //   532: aload 4
    //   534: astore_2
    //   535: goto -83 -> 452
    //   538: aload_2
    //   539: ifnull +77 -> 616
    //   542: aload_2
    //   543: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   546: astore_2
    //   547: new 36	java/util/ArrayList
    //   550: dup
    //   551: invokespecial 37	java/util/ArrayList:<init>	()V
    //   554: astore 4
    //   556: aload 4
    //   558: aload_0
    //   559: aload_2
    //   560: invokevirtual 494	afd:d	(Ljava/lang/String;)Ljava/lang/String;
    //   563: ldc 48
    //   565: aload_3
    //   566: invokestatic 108	afl:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   569: invokevirtual 495	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   572: pop
    //   573: aload_1
    //   574: aload 4
    //   576: invokevirtual 111	afj:a	(Ljava/util/List;)V
    //   579: aload_0
    //   580: getfield 39	afd:g	Ljava/util/List;
    //   583: invokeinterface 87 1 0
    //   588: astore_2
    //   589: aload_2
    //   590: invokeinterface 93 1 0
    //   595: ifeq -495 -> 100
    //   598: aload_2
    //   599: invokeinterface 97 1 0
    //   604: checkcast 113	afb
    //   607: aload_1
    //   608: invokeinterface 116 2 0
    //   613: goto -24 -> 589
    //   616: aload 5
    //   618: astore_2
    //   619: goto -72 -> 547
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	622	0	this	afd
    //   0	622	1	paramafj	afj
    //   0	622	2	paramString	String
    //   46	520	3	str1	String
    //   1	574	4	localObject	Object
    //   16	601	5	str2	String
    //   10	513	6	str3	String
    // Exception table:
    //   from	to	target	type
    //   242	249	295	java/lang/OutOfMemoryError
    //   249	258	295	java/lang/OutOfMemoryError
    //   258	268	295	java/lang/OutOfMemoryError
    //   268	292	295	java/lang/OutOfMemoryError
    //   344	373	295	java/lang/OutOfMemoryError
    //   249	258	343	java/lang/IllegalArgumentException
  }
  
  protected Set<String> i()
  {
    return afg.b;
  }
  
  protected Set<String> j()
  {
    return afg.c;
  }
  
  protected Set<String> k()
  {
    return afg.d;
  }
  
  public static final class a
    extends BufferedReader
  {
    private long a;
    private boolean b;
    private String c;
    
    public a(Reader paramReader)
    {
      super();
    }
    
    public String a()
    {
      if (!b)
      {
        long l = System.currentTimeMillis();
        String str = super.readLine();
        a = (System.currentTimeMillis() - l + a);
        c = str;
        b = true;
      }
      return c;
    }
    
    public String readLine()
    {
      if (b)
      {
        str = c;
        c = null;
        b = false;
        return str;
      }
      long l = System.currentTimeMillis();
      String str = super.readLine();
      a = (System.currentTimeMillis() - l + a);
      return str;
    }
  }
}

/* Location:
 * Qualified Name:     afd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */