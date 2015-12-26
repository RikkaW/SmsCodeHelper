import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import com.amap.api.location.core.b;
import com.amap.api.location.core.c;
import com.amap.api.location.core.e;

public class ahi
{
  ahi.a a = null;
  private String b = null;
  private Context c = null;
  private boolean d = true;
  private aft e = null;
  private ServiceConnection f = null;
  private Intent g = new Intent();
  private String h = "com.autonavi.minimap";
  private String i = "com.amap.api.service.AMapService";
  private String j = "invaid type";
  private String k = "empty appkey";
  private String l = "refused";
  private String m = "failed";
  
  ahi(Context paramContext)
  {
    c = paramContext;
    try
    {
      b = b.a(e.b(c.a.getBytes("UTF-8"), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  private ahf a(Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_1
    //   7: ldc 108
    //   9: invokevirtual 114	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   12: ifeq +162 -> 174
    //   15: aload_1
    //   16: ldc 108
    //   18: invokevirtual 118	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   21: invokestatic 120	com/amap/api/location/core/b:a	(Ljava/lang/String;)[B
    //   24: astore 7
    //   26: aload 7
    //   28: ldc 88
    //   30: invokestatic 122	com/amap/api/location/core/e:c	([BLjava/lang/String;)[B
    //   33: astore 7
    //   35: aload_1
    //   36: ldc 124
    //   38: invokevirtual 114	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   41: ifeq -37 -> 4
    //   44: aload_1
    //   45: ldc 124
    //   47: invokevirtual 118	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   50: invokestatic 120	com/amap/api/location/core/b:a	(Ljava/lang/String;)[B
    //   53: astore_1
    //   54: new 82	java/lang/String
    //   57: dup
    //   58: aload 7
    //   60: aload_1
    //   61: invokestatic 127	com/amap/api/location/core/e:b	([B[B)[B
    //   64: ldc -127
    //   66: invokespecial 132	java/lang/String:<init>	([BLjava/lang/String;)V
    //   69: astore_1
    //   70: aload_1
    //   71: ifnull -67 -> 4
    //   74: new 134	org/json/JSONObject
    //   77: dup
    //   78: aload_1
    //   79: invokespecial 137	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   82: astore 9
    //   84: aload 9
    //   86: ldc -117
    //   88: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   91: ifeq +89 -> 180
    //   94: aload 9
    //   96: ldc -117
    //   98: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   101: astore_1
    //   102: aload_0
    //   103: getfield 62	ahi:j	Ljava/lang/String;
    //   106: aload_1
    //   107: invokevirtual 147	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   110: ifeq +8 -> 118
    //   113: aload_0
    //   114: iconst_0
    //   115: putfield 39	ahi:d	Z
    //   118: aload_0
    //   119: getfield 66	ahi:k	Ljava/lang/String;
    //   122: aload_1
    //   123: invokevirtual 147	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   126: ifeq +8 -> 134
    //   129: aload_0
    //   130: iconst_0
    //   131: putfield 39	ahi:d	Z
    //   134: aload_0
    //   135: getfield 70	ahi:l	Ljava/lang/String;
    //   138: aload_1
    //   139: invokevirtual 147	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   142: ifeq +8 -> 150
    //   145: aload_0
    //   146: iconst_0
    //   147: putfield 39	ahi:d	Z
    //   150: aload_0
    //   151: getfield 74	ahi:m	Ljava/lang/String;
    //   154: aload_1
    //   155: invokevirtual 147	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   158: istore 6
    //   160: iload 6
    //   162: ifeq -158 -> 4
    //   165: aconst_null
    //   166: areturn
    //   167: astore 7
    //   169: aload 7
    //   171: invokevirtual 148	java/lang/Exception:printStackTrace	()V
    //   174: aconst_null
    //   175: astore 7
    //   177: goto -142 -> 35
    //   180: new 150	ahf
    //   183: dup
    //   184: invokespecial 151	ahf:<init>	()V
    //   187: astore 8
    //   189: aload 9
    //   191: ldc -103
    //   193: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   196: ifeq +15 -> 211
    //   199: aload 8
    //   201: aload 9
    //   203: ldc -103
    //   205: invokevirtual 157	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   208: invokevirtual 160	ahf:a	(J)V
    //   211: aload 9
    //   213: ldc -94
    //   215: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   218: ifeq +16 -> 234
    //   221: aload 8
    //   223: aload 9
    //   225: ldc -94
    //   227: invokevirtual 166	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   230: i2f
    //   231: invokevirtual 169	ahf:a	(F)V
    //   234: aload 9
    //   236: ldc -85
    //   238: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   241: ifeq +18 -> 259
    //   244: aload 8
    //   246: aload 9
    //   248: ldc -85
    //   250: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   253: invokestatic 177	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   256: invokevirtual 179	ahf:b	(F)V
    //   259: aload 8
    //   261: ldc -75
    //   263: invokevirtual 183	ahf:f	(Ljava/lang/String;)V
    //   266: aload 9
    //   268: ldc -71
    //   270: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   273: ifeq +510 -> 783
    //   276: aload 9
    //   278: ldc -71
    //   280: invokevirtual 189	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   283: dstore_2
    //   284: aload 9
    //   286: ldc -65
    //   288: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   291: ifeq +486 -> 777
    //   294: aload 9
    //   296: ldc -65
    //   298: invokevirtual 189	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   301: dstore 4
    //   303: aload 9
    //   305: ldc -63
    //   307: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   310: ifeq +462 -> 772
    //   313: aload 9
    //   315: ldc -63
    //   317: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   320: astore_1
    //   321: aload 9
    //   323: ldc -61
    //   325: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   328: ifeq +438 -> 766
    //   331: aload 9
    //   333: ldc -61
    //   335: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   338: astore 7
    //   340: aload 8
    //   342: aload 7
    //   344: invokevirtual 198	ahf:q	(Ljava/lang/String;)V
    //   347: aload 9
    //   349: ldc -56
    //   351: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   354: ifeq +406 -> 760
    //   357: aload 9
    //   359: ldc -56
    //   361: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   364: astore 7
    //   366: aload 8
    //   368: aload 7
    //   370: invokevirtual 202	ahf:j	(Ljava/lang/String;)V
    //   373: aload 9
    //   375: ldc -52
    //   377: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   380: ifeq +374 -> 754
    //   383: aload 9
    //   385: ldc -52
    //   387: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   390: astore 7
    //   392: aload 8
    //   394: aload 7
    //   396: invokevirtual 207	ahf:p	(Ljava/lang/String;)V
    //   399: aload 9
    //   401: ldc -47
    //   403: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   406: ifeq +342 -> 748
    //   409: aload 9
    //   411: ldc -47
    //   413: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   416: astore 7
    //   418: aload 8
    //   420: aload 7
    //   422: invokevirtual 211	ahf:a	(Ljava/lang/String;)V
    //   425: aload 9
    //   427: ldc -43
    //   429: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   432: ifeq +310 -> 742
    //   435: aload 9
    //   437: ldc -43
    //   439: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   442: astore 7
    //   444: aload 8
    //   446: aload 7
    //   448: invokevirtual 215	ahf:b	(Ljava/lang/String;)V
    //   451: aload 9
    //   453: ldc -39
    //   455: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   458: ifeq +278 -> 736
    //   461: aload 9
    //   463: ldc -39
    //   465: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   468: astore 7
    //   470: aload 8
    //   472: aload 7
    //   474: invokevirtual 220	ahf:o	(Ljava/lang/String;)V
    //   477: aload 9
    //   479: ldc -34
    //   481: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   484: ifeq +246 -> 730
    //   487: aload 9
    //   489: ldc -34
    //   491: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   494: astore 7
    //   496: aload 8
    //   498: aload 7
    //   500: invokevirtual 225	ahf:n	(Ljava/lang/String;)V
    //   503: aload 9
    //   505: ldc -29
    //   507: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   510: ifeq +214 -> 724
    //   513: aload 9
    //   515: ldc -29
    //   517: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   520: astore 7
    //   522: aload 8
    //   524: aload 7
    //   526: invokevirtual 229	ahf:l	(Ljava/lang/String;)V
    //   529: aload 9
    //   531: ldc -25
    //   533: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   536: ifeq +182 -> 718
    //   539: aload 9
    //   541: ldc -25
    //   543: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   546: astore 7
    //   548: aload 8
    //   550: aload 7
    //   552: invokevirtual 233	ahf:i	(Ljava/lang/String;)V
    //   555: aload 9
    //   557: ldc -21
    //   559: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   562: ifeq +150 -> 712
    //   565: aload 9
    //   567: ldc -21
    //   569: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   572: astore 7
    //   574: aload 8
    //   576: aload 7
    //   578: invokevirtual 237	ahf:m	(Ljava/lang/String;)V
    //   581: aload 9
    //   583: ldc -17
    //   585: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   588: ifeq +118 -> 706
    //   591: aload 9
    //   593: ldc -17
    //   595: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   598: astore 7
    //   600: aload 8
    //   602: aload 7
    //   604: invokevirtual 241	ahf:k	(Ljava/lang/String;)V
    //   607: aload 9
    //   609: ldc -13
    //   611: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   614: ifeq +86 -> 700
    //   617: aload 9
    //   619: ldc -13
    //   621: invokevirtual 143	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   624: astore 7
    //   626: aload 8
    //   628: aload 7
    //   630: invokevirtual 245	ahf:c	(Ljava/lang/String;)V
    //   633: ldc -9
    //   635: aload_1
    //   636: invokevirtual 147	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   639: ifeq +38 -> 677
    //   642: dload_2
    //   643: dload 4
    //   645: invokestatic 250	com/amap/api/location/core/c:a	(DD)Z
    //   648: ifeq +29 -> 677
    //   651: dload 4
    //   653: dload_2
    //   654: invokestatic 255	aia:a	(DD)[D
    //   657: astore_1
    //   658: aload 8
    //   660: aload_1
    //   661: iconst_1
    //   662: daload
    //   663: invokevirtual 258	ahf:b	(D)V
    //   666: aload 8
    //   668: aload_1
    //   669: iconst_0
    //   670: daload
    //   671: invokevirtual 260	ahf:a	(D)V
    //   674: goto +114 -> 788
    //   677: aload 8
    //   679: dload_2
    //   680: invokevirtual 258	ahf:b	(D)V
    //   683: aload 8
    //   685: dload 4
    //   687: invokevirtual 260	ahf:a	(D)V
    //   690: goto +98 -> 788
    //   693: astore_1
    //   694: aload_1
    //   695: invokevirtual 148	java/lang/Exception:printStackTrace	()V
    //   698: aconst_null
    //   699: areturn
    //   700: aconst_null
    //   701: astore 7
    //   703: goto -77 -> 626
    //   706: aconst_null
    //   707: astore 7
    //   709: goto -109 -> 600
    //   712: aconst_null
    //   713: astore 7
    //   715: goto -141 -> 574
    //   718: aconst_null
    //   719: astore 7
    //   721: goto -173 -> 548
    //   724: aconst_null
    //   725: astore 7
    //   727: goto -205 -> 522
    //   730: aconst_null
    //   731: astore 7
    //   733: goto -237 -> 496
    //   736: aconst_null
    //   737: astore 7
    //   739: goto -269 -> 470
    //   742: aconst_null
    //   743: astore 7
    //   745: goto -301 -> 444
    //   748: aconst_null
    //   749: astore 7
    //   751: goto -333 -> 418
    //   754: aconst_null
    //   755: astore 7
    //   757: goto -365 -> 392
    //   760: aconst_null
    //   761: astore 7
    //   763: goto -397 -> 366
    //   766: aconst_null
    //   767: astore 7
    //   769: goto -429 -> 340
    //   772: aconst_null
    //   773: astore_1
    //   774: goto -453 -> 321
    //   777: dconst_0
    //   778: dstore 4
    //   780: goto -477 -> 303
    //   783: dconst_0
    //   784: dstore_2
    //   785: goto -501 -> 284
    //   788: aload 8
    //   790: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	791	0	this	ahi
    //   0	791	1	paramBundle	Bundle
    //   283	502	2	d1	double
    //   301	478	4	d2	double
    //   158	3	6	bool	boolean
    //   24	35	7	arrayOfByte	byte[]
    //   167	3	7	localException	Exception
    //   175	593	7	str	String
    //   187	602	8	localahf	ahf
    //   82	536	9	localJSONObject	org.json.JSONObject
    // Exception table:
    //   from	to	target	type
    //   26	35	167	java/lang/Exception
    //   54	70	693	java/lang/Exception
    //   74	118	693	java/lang/Exception
    //   118	134	693	java/lang/Exception
    //   134	150	693	java/lang/Exception
    //   150	160	693	java/lang/Exception
    //   180	211	693	java/lang/Exception
    //   211	234	693	java/lang/Exception
    //   234	259	693	java/lang/Exception
    //   259	284	693	java/lang/Exception
    //   284	303	693	java/lang/Exception
    //   303	321	693	java/lang/Exception
    //   321	340	693	java/lang/Exception
    //   340	366	693	java/lang/Exception
    //   366	392	693	java/lang/Exception
    //   392	418	693	java/lang/Exception
    //   418	444	693	java/lang/Exception
    //   444	470	693	java/lang/Exception
    //   470	496	693	java/lang/Exception
    //   496	522	693	java/lang/Exception
    //   522	548	693	java/lang/Exception
    //   548	574	693	java/lang/Exception
    //   574	600	693	java/lang/Exception
    //   600	626	693	java/lang/Exception
    //   626	674	693	java/lang/Exception
    //   677	690	693	java/lang/Exception
  }
  
  public void a()
  {
    c();
    c = null;
    e = null;
    f = null;
    if (a != null) {
      a.a(-1);
    }
    d = true;
  }
  
  public void a(ahi.a parama)
  {
    try
    {
      a = parama;
      if (f == null) {
        f = new ahj(this, parama);
      }
      return;
    }
    catch (Throwable parama)
    {
      parama.printStackTrace();
    }
  }
  
  boolean b()
  {
    try
    {
      g.setComponent(new ComponentName(h, i));
      g.putExtra("appkey", b);
      boolean bool = c.bindService(g, f, 1);
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  void c()
  {
    try
    {
      c.unbindService(f);
      e = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        localIllegalArgumentException.printStackTrace();
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  ahf d()
  {
    try
    {
      if (!d) {
        return null;
      }
      Object localObject = new Bundle();
      ((Bundle)localObject).putString("type", "corse");
      ((Bundle)localObject).putString("appkey", b);
      e.a((Bundle)localObject);
      if (((Bundle)localObject).size() >= 1)
      {
        localObject = a((Bundle)localObject);
        return (ahf)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      return null;
    }
    catch (RemoteException localRemoteException) {}
    return null;
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
  }
}

/* Location:
 * Qualified Name:     ahi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */