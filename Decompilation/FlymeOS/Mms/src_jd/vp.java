import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.ui.MeizuSlideshowActivity;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzCharacterSets;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzMultimediaMessagePdu;
import com.meizu.android.mms.pdu.MzPduBody;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.util.MzPduCacheEntry;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.w3c.dom.NodeList;

public class vp
{
  public ArrayList<vp.a> a;
  private MeizuSlideshowActivity b;
  private Uri c;
  private Uri d = null;
  private String e;
  private MzPduCacheEntry f;
  private MzMultimediaMessagePdu g;
  private auc h;
  private String i;
  
  public vp(MeizuSlideshowActivity paramMeizuSlideshowActivity, Uri paramUri)
  {
    b = paramMeizuSlideshowActivity;
    c = paramUri;
    f = a(c);
    if (f == null)
    {
      Log.e("MeizuSlideviewDoc", "mPduCache is null");
      a = new ArrayList();
      return;
    }
    g = ((MzMultimediaMessagePdu)f.getPdu());
    if (g != null)
    {
      if (g.getSubject() != null) {
        i = g.getSubject().getString();
      }
      h = ls.a(g.getBody());
      c();
      return;
    }
    a = new ArrayList();
    Log.e("MeizuSlideviewDoc", "mPdu is null");
  }
  
  private MzPduPart a(MzPduBody paramMzPduBody, String paramString)
  {
    Object localObject = null;
    String str;
    if (paramString != null)
    {
      str = a(paramString);
      if (!str.startsWith("cid:")) {
        break label88;
      }
      localObject = str.substring("cid:".length());
      paramString = (String)localObject;
      if (((String)localObject).length() > 0)
      {
        paramString = (String)localObject;
        if (((String)localObject).charAt(0) != '<') {
          paramString = "<" + (String)localObject + ">";
        }
      }
      localObject = paramMzPduBody.getPartByContentId(paramString);
    }
    label88:
    do
    {
      do
      {
        do
        {
          return (MzPduPart)localObject;
          paramString = paramMzPduBody.getPartByName(str);
          localObject = paramString;
        } while (paramString != null);
        paramString = paramMzPduBody.getPartByFileName(str);
        localObject = paramString;
      } while (paramString != null);
      paramString = paramMzPduBody.getPartByContentLocation(str);
      localObject = paramString;
    } while (paramString != null);
    return b(paramMzPduBody, str);
  }
  
  private MzPduCacheEntry a(Uri paramUri)
  {
    paramUri = MzPduPersister.getPduPersister(b);
    try
    {
      paramUri = paramUri.load(c, true);
      return paramUri;
    }
    catch (MmsException paramUri)
    {
      Log.e("MeizuSlideviewDoc", "Fail to create MzGenericPdu");
    }
    return null;
  }
  
  private String a(String paramString)
  {
    return paramString.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&amp;", "&");
  }
  
  private String a(byte[] paramArrayOfByte, int paramInt)
  {
    int j;
    if (paramArrayOfByte != null)
    {
      j = paramInt;
      if (paramInt == 0) {
        j = 4;
      }
      if (j == 0) {}
      try
      {
        return new String(paramArrayOfByte);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        String str;
        Log.e("MeizuSlideviewDoc", "Unsupported encoding: " + j, localUnsupportedEncodingException);
        return new String(paramArrayOfByte);
      }
      str = new String(paramArrayOfByte, MzCharacterSets.getMimeName(j));
      return str;
    }
    return "";
  }
  
  /* Error */
  private void a(Context paramContext, String paramString1, String paramString2, MzPduPart paramMzPduPart, ArrayList<vp.a> paramArrayList)
  {
    // Byte code:
    //   0: aload 4
    //   2: invokevirtual 214	com/meizu/android/mms/pdu/MzPduPart:getContentType	()[B
    //   5: astore 10
    //   7: aload 10
    //   9: ifnonnull +13 -> 22
    //   12: new 216	java/lang/IllegalArgumentException
    //   15: dup
    //   16: ldc -38
    //   18: invokespecial 221	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   21: athrow
    //   22: new 102	java/lang/String
    //   25: dup
    //   26: aload 10
    //   28: invokespecial 189	java/lang/String:<init>	([B)V
    //   31: astore 10
    //   33: aload 10
    //   35: astore 11
    //   37: aload 10
    //   39: ifnull +10 -> 49
    //   42: aload 10
    //   44: invokevirtual 224	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   47: astore 11
    //   49: aload 5
    //   51: invokevirtual 227	java/util/ArrayList:size	()I
    //   54: ifle +1432 -> 1486
    //   57: aload 5
    //   59: aload 5
    //   61: invokevirtual 227	java/util/ArrayList:size	()I
    //   64: iconst_1
    //   65: isub
    //   66: invokevirtual 231	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   69: checkcast 6	vp$a
    //   72: astore 10
    //   74: aload 10
    //   76: invokevirtual 234	java/lang/Object:hashCode	()I
    //   79: i2l
    //   80: lstore 7
    //   82: aload_2
    //   83: ldc -20
    //   85: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   88: ifeq +292 -> 380
    //   91: aload 5
    //   93: invokevirtual 227	java/util/ArrayList:size	()I
    //   96: ifle +241 -> 337
    //   99: aload 5
    //   101: aload 5
    //   103: invokevirtual 227	java/util/ArrayList:size	()I
    //   106: iconst_1
    //   107: isub
    //   108: invokevirtual 231	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   111: checkcast 6	vp$a
    //   114: getfield 242	vp$a:a	Ljava/lang/String;
    //   117: ldc -20
    //   119: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   122: istore 9
    //   124: iload 9
    //   126: ifeq +211 -> 337
    //   129: iconst_0
    //   130: istore 6
    //   132: aload 10
    //   134: getfield 244	vp$a:b	Ljava/lang/String;
    //   137: invokestatic 250	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   140: ifeq +38 -> 178
    //   143: aload 10
    //   145: aload_0
    //   146: aload 4
    //   148: invokevirtual 253	com/meizu/android/mms/pdu/MzPduPart:getData	()[B
    //   151: aload 4
    //   153: invokevirtual 256	com/meizu/android/mms/pdu/MzPduPart:getCharset	()I
    //   156: invokespecial 258	vp:a	([BI)Ljava/lang/String;
    //   159: putfield 244	vp$a:b	Ljava/lang/String;
    //   162: aload 10
    //   164: astore_1
    //   165: iload 6
    //   167: ifeq +10 -> 177
    //   170: aload 5
    //   172: aload_1
    //   173: invokevirtual 261	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   176: pop
    //   177: return
    //   178: aload 10
    //   180: new 120	java/lang/StringBuilder
    //   183: dup
    //   184: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   187: aload 10
    //   189: getfield 244	vp$a:b	Ljava/lang/String;
    //   192: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: ldc_w 263
    //   198: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: aload_0
    //   202: aload 4
    //   204: invokevirtual 253	com/meizu/android/mms/pdu/MzPduPart:getData	()[B
    //   207: aload 4
    //   209: invokevirtual 256	com/meizu/android/mms/pdu/MzPduPart:getCharset	()I
    //   212: invokespecial 258	vp:a	([BI)Ljava/lang/String;
    //   215: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   221: putfield 244	vp$a:b	Ljava/lang/String;
    //   224: aload 10
    //   226: astore_1
    //   227: goto -62 -> 165
    //   230: astore 4
    //   232: aload 10
    //   234: astore_2
    //   235: iconst_0
    //   236: istore 6
    //   238: ldc 43
    //   240: new 120	java/lang/StringBuilder
    //   243: dup
    //   244: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   247: ldc_w 265
    //   250: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: aload 4
    //   255: invokevirtual 268	com/google/android/mms/MmsException:getMessage	()Ljava/lang/String;
    //   258: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   264: invokestatic 50	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   267: pop
    //   268: lload 7
    //   270: aload_2
    //   271: invokevirtual 234	java/lang/Object:hashCode	()I
    //   274: i2l
    //   275: lcmp
    //   276: ifeq +1073 -> 1349
    //   279: aload_2
    //   280: iconst_0
    //   281: putfield 271	vp$a:e	Z
    //   284: ldc2_w 272
    //   287: lstore 7
    //   289: aload_0
    //   290: getfield 65	vp:g	Lcom/meizu/android/mms/pdu/MzMultimediaMessagePdu;
    //   293: instanceof 275
    //   296: ifeq +15 -> 311
    //   299: aload_0
    //   300: getfield 65	vp:g	Lcom/meizu/android/mms/pdu/MzMultimediaMessagePdu;
    //   303: checkcast 275	com/meizu/android/mms/pdu/MzSendReq
    //   306: invokevirtual 279	com/meizu/android/mms/pdu/MzSendReq:getMessageSize	()J
    //   309: lstore 7
    //   311: aload_2
    //   312: new 9	vp$b
    //   315: dup
    //   316: aload_1
    //   317: ldc_w 281
    //   320: aload 11
    //   322: aload_3
    //   323: aconst_null
    //   324: lload 7
    //   326: invokespecial 284	vp$b:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[BJ)V
    //   329: putfield 287	vp$a:c	Llm;
    //   332: aload_2
    //   333: astore_1
    //   334: goto -169 -> 165
    //   337: new 6	vp$a
    //   340: dup
    //   341: invokespecial 288	vp$a:<init>	()V
    //   344: astore 12
    //   346: aload 12
    //   348: aload_2
    //   349: putfield 242	vp$a:a	Ljava/lang/String;
    //   352: aload 12
    //   354: aload_0
    //   355: aload 4
    //   357: invokevirtual 253	com/meizu/android/mms/pdu/MzPduPart:getData	()[B
    //   360: aload 4
    //   362: invokevirtual 256	com/meizu/android/mms/pdu/MzPduPart:getCharset	()I
    //   365: invokespecial 258	vp:a	([BI)Ljava/lang/String;
    //   368: putfield 244	vp$a:b	Ljava/lang/String;
    //   371: aload 12
    //   373: astore_1
    //   374: iconst_1
    //   375: istore 6
    //   377: goto -212 -> 165
    //   380: aload_2
    //   381: ldc_w 290
    //   384: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   387: ifeq +49 -> 436
    //   390: new 6	vp$a
    //   393: dup
    //   394: invokespecial 288	vp$a:<init>	()V
    //   397: astore 12
    //   399: aload 12
    //   401: aload_2
    //   402: putfield 242	vp$a:a	Ljava/lang/String;
    //   405: aload 12
    //   407: new 292	lk
    //   410: dup
    //   411: aload_1
    //   412: aload 11
    //   414: aload_3
    //   415: aload 4
    //   417: invokevirtual 296	com/meizu/android/mms/pdu/MzPduPart:getDataUri	()Landroid/net/Uri;
    //   420: aconst_null
    //   421: invokespecial 299	lk:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Llp;)V
    //   424: putfield 287	vp$a:c	Llm;
    //   427: iconst_1
    //   428: istore 6
    //   430: aload 12
    //   432: astore_1
    //   433: goto -268 -> 165
    //   436: aload_2
    //   437: ldc_w 301
    //   440: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   443: ifeq +48 -> 491
    //   446: new 6	vp$a
    //   449: dup
    //   450: invokespecial 288	vp$a:<init>	()V
    //   453: astore 12
    //   455: aload 12
    //   457: aload_2
    //   458: putfield 242	vp$a:a	Ljava/lang/String;
    //   461: aload 12
    //   463: new 303	le
    //   466: dup
    //   467: aload_1
    //   468: aload 11
    //   470: aload_3
    //   471: aload 4
    //   473: invokevirtual 296	com/meizu/android/mms/pdu/MzPduPart:getDataUri	()Landroid/net/Uri;
    //   476: invokespecial 306	le:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   479: putfield 287	vp$a:c	Llm;
    //   482: aload 12
    //   484: astore_1
    //   485: iconst_1
    //   486: istore 6
    //   488: goto -323 -> 165
    //   491: aload_2
    //   492: ldc_w 308
    //   495: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   498: ifeq +49 -> 547
    //   501: new 6	vp$a
    //   504: dup
    //   505: invokespecial 288	vp$a:<init>	()V
    //   508: astore 12
    //   510: aload 12
    //   512: aload_2
    //   513: putfield 242	vp$a:a	Ljava/lang/String;
    //   516: aload 12
    //   518: new 310	lw
    //   521: dup
    //   522: aload_1
    //   523: aload 11
    //   525: aload_3
    //   526: aload 4
    //   528: invokevirtual 296	com/meizu/android/mms/pdu/MzPduPart:getDataUri	()Landroid/net/Uri;
    //   531: aconst_null
    //   532: invokespecial 311	lw:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Llp;)V
    //   535: putfield 287	vp$a:c	Llm;
    //   538: iconst_1
    //   539: istore 6
    //   541: aload 12
    //   543: astore_1
    //   544: goto -379 -> 165
    //   547: aload_2
    //   548: ldc_w 313
    //   551: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   554: ifeq +562 -> 1116
    //   557: aload 11
    //   559: invokestatic 318	com/meizu/android/mms/MzContentType:isTextType	(Ljava/lang/String;)Z
    //   562: ifeq +222 -> 784
    //   565: aload 11
    //   567: invokestatic 321	com/meizu/android/mms/MzContentType:isVcardType	(Ljava/lang/String;)Z
    //   570: ifeq +46 -> 616
    //   573: new 6	vp$a
    //   576: dup
    //   577: invokespecial 288	vp$a:<init>	()V
    //   580: astore_2
    //   581: aload_2
    //   582: ldc_w 313
    //   585: putfield 242	vp$a:a	Ljava/lang/String;
    //   588: aload_2
    //   589: new 323	lv
    //   592: dup
    //   593: aload_1
    //   594: aload 11
    //   596: aload_3
    //   597: aload 4
    //   599: invokevirtual 296	com/meizu/android/mms/pdu/MzPduPart:getDataUri	()Landroid/net/Uri;
    //   602: invokespecial 324	lv:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   605: putfield 287	vp$a:c	Llm;
    //   608: aload_2
    //   609: astore_1
    //   610: iconst_1
    //   611: istore 6
    //   613: goto -448 -> 165
    //   616: aload 5
    //   618: invokevirtual 227	java/util/ArrayList:size	()I
    //   621: ifle +123 -> 744
    //   624: aload 5
    //   626: aload 5
    //   628: invokevirtual 227	java/util/ArrayList:size	()I
    //   631: iconst_1
    //   632: isub
    //   633: invokevirtual 231	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   636: checkcast 6	vp$a
    //   639: getfield 242	vp$a:a	Ljava/lang/String;
    //   642: ldc -20
    //   644: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   647: ifeq +97 -> 744
    //   650: aload 10
    //   652: getfield 244	vp$a:b	Ljava/lang/String;
    //   655: invokestatic 250	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   658: ifeq +31 -> 689
    //   661: aload 10
    //   663: aload_0
    //   664: aload 4
    //   666: invokevirtual 253	com/meizu/android/mms/pdu/MzPduPart:getData	()[B
    //   669: aload 4
    //   671: invokevirtual 256	com/meizu/android/mms/pdu/MzPduPart:getCharset	()I
    //   674: invokespecial 258	vp:a	([BI)Ljava/lang/String;
    //   677: putfield 244	vp$a:b	Ljava/lang/String;
    //   680: iconst_1
    //   681: istore 6
    //   683: aload 10
    //   685: astore_1
    //   686: goto -521 -> 165
    //   689: aload 10
    //   691: new 120	java/lang/StringBuilder
    //   694: dup
    //   695: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   698: aload 10
    //   700: getfield 244	vp$a:b	Ljava/lang/String;
    //   703: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   706: ldc_w 263
    //   709: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   712: aload_0
    //   713: aload 4
    //   715: invokevirtual 253	com/meizu/android/mms/pdu/MzPduPart:getData	()[B
    //   718: aload 4
    //   720: invokevirtual 256	com/meizu/android/mms/pdu/MzPduPart:getCharset	()I
    //   723: invokespecial 258	vp:a	([BI)Ljava/lang/String;
    //   726: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   729: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   732: putfield 244	vp$a:b	Ljava/lang/String;
    //   735: iconst_1
    //   736: istore 6
    //   738: aload 10
    //   740: astore_1
    //   741: goto -576 -> 165
    //   744: new 6	vp$a
    //   747: dup
    //   748: invokespecial 288	vp$a:<init>	()V
    //   751: astore_2
    //   752: aload_2
    //   753: ldc -20
    //   755: putfield 242	vp$a:a	Ljava/lang/String;
    //   758: aload_2
    //   759: aload_0
    //   760: aload 4
    //   762: invokevirtual 253	com/meizu/android/mms/pdu/MzPduPart:getData	()[B
    //   765: aload 4
    //   767: invokevirtual 256	com/meizu/android/mms/pdu/MzPduPart:getCharset	()I
    //   770: invokespecial 258	vp:a	([BI)Ljava/lang/String;
    //   773: putfield 244	vp$a:b	Ljava/lang/String;
    //   776: aload_2
    //   777: astore_1
    //   778: iconst_1
    //   779: istore 6
    //   781: goto -616 -> 165
    //   784: aload 11
    //   786: invokestatic 327	com/meizu/android/mms/MzContentType:isImageType	(Ljava/lang/String;)Z
    //   789: ifeq +47 -> 836
    //   792: new 6	vp$a
    //   795: dup
    //   796: invokespecial 288	vp$a:<init>	()V
    //   799: astore_2
    //   800: aload_2
    //   801: ldc_w 290
    //   804: putfield 242	vp$a:a	Ljava/lang/String;
    //   807: aload_2
    //   808: new 292	lk
    //   811: dup
    //   812: aload_1
    //   813: aload 11
    //   815: aload_3
    //   816: aload 4
    //   818: invokevirtual 296	com/meizu/android/mms/pdu/MzPduPart:getDataUri	()Landroid/net/Uri;
    //   821: aconst_null
    //   822: invokespecial 299	lk:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Llp;)V
    //   825: putfield 287	vp$a:c	Llm;
    //   828: iconst_1
    //   829: istore 6
    //   831: aload_2
    //   832: astore_1
    //   833: goto -668 -> 165
    //   836: aload 11
    //   838: invokestatic 330	com/meizu/android/mms/MzContentType:isAudioType	(Ljava/lang/String;)Z
    //   841: ifeq +46 -> 887
    //   844: new 6	vp$a
    //   847: dup
    //   848: invokespecial 288	vp$a:<init>	()V
    //   851: astore_2
    //   852: aload_2
    //   853: ldc_w 301
    //   856: putfield 242	vp$a:a	Ljava/lang/String;
    //   859: aload_2
    //   860: new 303	le
    //   863: dup
    //   864: aload_1
    //   865: aload 11
    //   867: aload_3
    //   868: aload 4
    //   870: invokevirtual 296	com/meizu/android/mms/pdu/MzPduPart:getDataUri	()Landroid/net/Uri;
    //   873: invokespecial 306	le:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   876: putfield 287	vp$a:c	Llm;
    //   879: aload_2
    //   880: astore_1
    //   881: iconst_1
    //   882: istore 6
    //   884: goto -719 -> 165
    //   887: aload 11
    //   889: invokestatic 333	com/meizu/android/mms/MzContentType:isVideoType	(Ljava/lang/String;)Z
    //   892: ifeq +47 -> 939
    //   895: new 6	vp$a
    //   898: dup
    //   899: invokespecial 288	vp$a:<init>	()V
    //   902: astore_2
    //   903: aload_2
    //   904: ldc_w 308
    //   907: putfield 242	vp$a:a	Ljava/lang/String;
    //   910: aload_2
    //   911: new 310	lw
    //   914: dup
    //   915: aload_1
    //   916: aload 11
    //   918: aload_3
    //   919: aload 4
    //   921: invokevirtual 296	com/meizu/android/mms/pdu/MzPduPart:getDataUri	()Landroid/net/Uri;
    //   924: aconst_null
    //   925: invokespecial 311	lw:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Llp;)V
    //   928: putfield 287	vp$a:c	Llm;
    //   931: iconst_1
    //   932: istore 6
    //   934: aload_2
    //   935: astore_1
    //   936: goto -771 -> 165
    //   939: aload 5
    //   941: invokevirtual 227	java/util/ArrayList:size	()I
    //   944: ifle +125 -> 1069
    //   947: aload 5
    //   949: aload 5
    //   951: invokevirtual 227	java/util/ArrayList:size	()I
    //   954: iconst_1
    //   955: isub
    //   956: invokevirtual 231	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   959: checkcast 6	vp$a
    //   962: getfield 242	vp$a:a	Ljava/lang/String;
    //   965: ldc -20
    //   967: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   970: istore 9
    //   972: iload 9
    //   974: ifeq +95 -> 1069
    //   977: iconst_0
    //   978: istore 6
    //   980: aload 10
    //   982: getfield 244	vp$a:b	Ljava/lang/String;
    //   985: invokestatic 250	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   988: ifeq +35 -> 1023
    //   991: aload 10
    //   993: new 120	java/lang/StringBuilder
    //   996: dup
    //   997: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   1000: ldc_w 335
    //   1003: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1006: aload 11
    //   1008: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1011: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1014: putfield 244	vp$a:b	Ljava/lang/String;
    //   1017: aload 10
    //   1019: astore_1
    //   1020: goto -855 -> 165
    //   1023: aload 10
    //   1025: new 120	java/lang/StringBuilder
    //   1028: dup
    //   1029: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   1032: aload 10
    //   1034: getfield 244	vp$a:b	Ljava/lang/String;
    //   1037: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1040: ldc_w 263
    //   1043: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1046: ldc_w 335
    //   1049: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1052: aload 11
    //   1054: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1057: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1060: putfield 244	vp$a:b	Ljava/lang/String;
    //   1063: aload 10
    //   1065: astore_1
    //   1066: goto -901 -> 165
    //   1069: new 6	vp$a
    //   1072: dup
    //   1073: invokespecial 288	vp$a:<init>	()V
    //   1076: astore_2
    //   1077: aload_2
    //   1078: ldc -20
    //   1080: putfield 242	vp$a:a	Ljava/lang/String;
    //   1083: aload_2
    //   1084: new 120	java/lang/StringBuilder
    //   1087: dup
    //   1088: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   1091: ldc_w 335
    //   1094: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1097: aload 11
    //   1099: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1102: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1105: putfield 244	vp$a:b	Ljava/lang/String;
    //   1108: aload_2
    //   1109: astore_1
    //   1110: iconst_1
    //   1111: istore 6
    //   1113: goto -948 -> 165
    //   1116: aload_2
    //   1117: ldc_w 281
    //   1120: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1123: ifeq +48 -> 1171
    //   1126: new 6	vp$a
    //   1129: dup
    //   1130: invokespecial 288	vp$a:<init>	()V
    //   1133: astore 12
    //   1135: aload 12
    //   1137: aload_2
    //   1138: putfield 242	vp$a:a	Ljava/lang/String;
    //   1141: aload 12
    //   1143: new 337	li
    //   1146: dup
    //   1147: aload_1
    //   1148: aload 11
    //   1150: aload_3
    //   1151: aload 4
    //   1153: invokevirtual 296	com/meizu/android/mms/pdu/MzPduPart:getDataUri	()Landroid/net/Uri;
    //   1156: invokespecial 338	li:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   1159: putfield 287	vp$a:c	Llm;
    //   1162: aload 12
    //   1164: astore_1
    //   1165: iconst_1
    //   1166: istore 6
    //   1168: goto -1003 -> 165
    //   1171: aload 5
    //   1173: invokevirtual 227	java/util/ArrayList:size	()I
    //   1176: ifle +123 -> 1299
    //   1179: aload 5
    //   1181: aload 5
    //   1183: invokevirtual 227	java/util/ArrayList:size	()I
    //   1186: iconst_1
    //   1187: isub
    //   1188: invokevirtual 231	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1191: checkcast 6	vp$a
    //   1194: getfield 242	vp$a:a	Ljava/lang/String;
    //   1197: ldc -20
    //   1199: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1202: istore 9
    //   1204: iload 9
    //   1206: ifeq +93 -> 1299
    //   1209: iconst_0
    //   1210: istore 6
    //   1212: aload 10
    //   1214: getfield 244	vp$a:b	Ljava/lang/String;
    //   1217: invokestatic 250	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1220: ifeq +34 -> 1254
    //   1223: aload 10
    //   1225: new 120	java/lang/StringBuilder
    //   1228: dup
    //   1229: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   1232: ldc_w 340
    //   1235: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1238: aload_2
    //   1239: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1242: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1245: putfield 244	vp$a:b	Ljava/lang/String;
    //   1248: aload 10
    //   1250: astore_1
    //   1251: goto -1086 -> 165
    //   1254: aload 10
    //   1256: new 120	java/lang/StringBuilder
    //   1259: dup
    //   1260: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   1263: aload 10
    //   1265: getfield 244	vp$a:b	Ljava/lang/String;
    //   1268: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1271: ldc_w 263
    //   1274: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1277: ldc_w 340
    //   1280: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1283: aload_2
    //   1284: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1287: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1290: putfield 244	vp$a:b	Ljava/lang/String;
    //   1293: aload 10
    //   1295: astore_1
    //   1296: goto -1131 -> 165
    //   1299: new 6	vp$a
    //   1302: dup
    //   1303: invokespecial 288	vp$a:<init>	()V
    //   1306: astore 4
    //   1308: aload 4
    //   1310: ldc -20
    //   1312: putfield 242	vp$a:a	Ljava/lang/String;
    //   1315: aload 4
    //   1317: new 120	java/lang/StringBuilder
    //   1320: dup
    //   1321: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   1324: ldc_w 340
    //   1327: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1330: aload_2
    //   1331: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1334: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1337: putfield 244	vp$a:b	Ljava/lang/String;
    //   1340: aload 4
    //   1342: astore_1
    //   1343: iconst_1
    //   1344: istore 6
    //   1346: goto -1181 -> 165
    //   1349: iconst_0
    //   1350: istore 6
    //   1352: aload_2
    //   1353: astore_1
    //   1354: goto -1189 -> 165
    //   1357: astore 4
    //   1359: aload 10
    //   1361: astore_2
    //   1362: iconst_1
    //   1363: istore 6
    //   1365: goto -1127 -> 238
    //   1368: astore 4
    //   1370: aload 12
    //   1372: astore_2
    //   1373: iconst_1
    //   1374: istore 6
    //   1376: goto -1138 -> 238
    //   1379: astore 4
    //   1381: iconst_1
    //   1382: istore 6
    //   1384: aload 12
    //   1386: astore_2
    //   1387: goto -1149 -> 238
    //   1390: astore 4
    //   1392: aload 12
    //   1394: astore_2
    //   1395: iconst_1
    //   1396: istore 6
    //   1398: goto -1160 -> 238
    //   1401: astore 4
    //   1403: iconst_1
    //   1404: istore 6
    //   1406: aload 12
    //   1408: astore_2
    //   1409: goto -1171 -> 238
    //   1412: astore 4
    //   1414: iconst_1
    //   1415: istore 6
    //   1417: goto -1179 -> 238
    //   1420: astore 4
    //   1422: iconst_1
    //   1423: istore 6
    //   1425: goto -1187 -> 238
    //   1428: astore 4
    //   1430: iconst_1
    //   1431: istore 6
    //   1433: goto -1195 -> 238
    //   1436: astore 4
    //   1438: iconst_1
    //   1439: istore 6
    //   1441: goto -1203 -> 238
    //   1444: astore 4
    //   1446: iconst_1
    //   1447: istore 6
    //   1449: goto -1211 -> 238
    //   1452: astore 4
    //   1454: iconst_1
    //   1455: istore 6
    //   1457: goto -1219 -> 238
    //   1460: astore 4
    //   1462: aload 12
    //   1464: astore_2
    //   1465: iconst_1
    //   1466: istore 6
    //   1468: goto -1230 -> 238
    //   1471: astore 10
    //   1473: aload 4
    //   1475: astore_2
    //   1476: aload 10
    //   1478: astore 4
    //   1480: iconst_1
    //   1481: istore 6
    //   1483: goto -1245 -> 238
    //   1486: lconst_0
    //   1487: lstore 7
    //   1489: aconst_null
    //   1490: astore 10
    //   1492: goto -1410 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1495	0	this	vp
    //   0	1495	1	paramContext	Context
    //   0	1495	2	paramString1	String
    //   0	1495	3	paramString2	String
    //   0	1495	4	paramMzPduPart	MzPduPart
    //   0	1495	5	paramArrayList	ArrayList<vp.a>
    //   130	1352	6	j	int
    //   80	1408	7	l	long
    //   122	1083	9	bool	boolean
    //   5	1355	10	localObject1	Object
    //   1471	6	10	localMmsException	MmsException
    //   1490	1	10	localObject2	Object
    //   35	1114	11	localObject3	Object
    //   344	1119	12	locala	vp.a
    // Exception table:
    //   from	to	target	type
    //   132	162	230	com/google/android/mms/MmsException
    //   178	224	230	com/google/android/mms/MmsException
    //   980	1017	230	com/google/android/mms/MmsException
    //   1023	1063	230	com/google/android/mms/MmsException
    //   1212	1248	230	com/google/android/mms/MmsException
    //   1254	1293	230	com/google/android/mms/MmsException
    //   82	124	1357	com/google/android/mms/MmsException
    //   337	346	1357	com/google/android/mms/MmsException
    //   380	399	1357	com/google/android/mms/MmsException
    //   436	455	1357	com/google/android/mms/MmsException
    //   491	510	1357	com/google/android/mms/MmsException
    //   547	581	1357	com/google/android/mms/MmsException
    //   616	680	1357	com/google/android/mms/MmsException
    //   689	735	1357	com/google/android/mms/MmsException
    //   744	752	1357	com/google/android/mms/MmsException
    //   784	800	1357	com/google/android/mms/MmsException
    //   836	852	1357	com/google/android/mms/MmsException
    //   887	903	1357	com/google/android/mms/MmsException
    //   939	972	1357	com/google/android/mms/MmsException
    //   1069	1077	1357	com/google/android/mms/MmsException
    //   1116	1135	1357	com/google/android/mms/MmsException
    //   1171	1204	1357	com/google/android/mms/MmsException
    //   1299	1308	1357	com/google/android/mms/MmsException
    //   346	371	1368	com/google/android/mms/MmsException
    //   399	427	1379	com/google/android/mms/MmsException
    //   455	482	1390	com/google/android/mms/MmsException
    //   510	538	1401	com/google/android/mms/MmsException
    //   581	608	1412	com/google/android/mms/MmsException
    //   752	776	1420	com/google/android/mms/MmsException
    //   800	828	1428	com/google/android/mms/MmsException
    //   852	879	1436	com/google/android/mms/MmsException
    //   903	931	1444	com/google/android/mms/MmsException
    //   1077	1108	1452	com/google/android/mms/MmsException
    //   1135	1162	1460	com/google/android/mms/MmsException
    //   1308	1340	1471	com/google/android/mms/MmsException
  }
  
  private MzPduPart b(MzPduBody paramMzPduBody, String paramString)
  {
    Object localObject = null;
    int j = paramString.lastIndexOf(".");
    if (j < 0) {}
    String str;
    do
    {
      do
      {
        do
        {
          return (MzPduPart)localObject;
        } while (paramString.length() - j > 5);
        str = paramString.substring(0, j);
        paramString = paramMzPduBody.getPartByName(str);
        localObject = paramString;
      } while (paramString != null);
      paramString = paramMzPduBody.getPartByFileName(str);
      localObject = paramString;
    } while (paramString != null);
    return paramMzPduBody.getPartByContentLocation(str);
  }
  
  private void c()
  {
    e = f.getFileLink();
    Object localObject1 = lr.a(f.getMmsDescription(), e);
    Object localObject2 = h.l().getChildNodes();
    int m = ((NodeList)localObject2).getLength();
    a = new ArrayList(m);
    int j = 0;
    Object localObject3;
    while (j < m)
    {
      localObject3 = ((aug)((NodeList)localObject2).item(j)).getChildNodes();
      int n = ((NodeList)localObject3).getLength();
      int k = 0;
      while (k < n)
      {
        auf localauf = (auf)((NodeList)localObject3).item(k);
        MzPduPart localMzPduPart = a(g.getBody(), localauf.k());
        if (localMzPduPart != null) {
          a(b, localauf.getTagName(), localauf.k(), localMzPduPart, a);
        }
        k += 1;
      }
      j += 1;
    }
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject2 = lr.b(e);
      localObject3 = new MzPduPart();
      ((MzPduPart)localObject3).setDataUri(lr.a(e));
      ((MzPduPart)localObject3).setContentType(((String)localObject1).getBytes());
      ((MzPduPart)localObject3).setFilename(((String)localObject2).getBytes());
      a(b, "file", (String)localObject2, (MzPduPart)localObject3, a);
    }
    do
    {
      do
      {
        return;
      } while (a.size() != 2);
      if (("text".equals(a.get(1)).a)) && (!"video".equals(a.get(0)).a)) && (!"img".equals(a.get(0)).a)))
      {
        localObject1 = (vp.a)a.remove(0);
        a.add(localObject1);
        return;
      }
    } while ((!"text".equals(a.get(0)).a)) || ((!"video".equals(a.get(1)).a)) && (!"img".equals(a.get(1)).a))));
    localObject1 = (vp.a)a.remove(0);
    a.add(localObject1);
  }
  
  public ArrayList<vp.a> a()
  {
    return a;
  }
  
  public void a(Uri paramUri, String paramString)
  {
    if ((paramUri == null) || (!paramUri.getAuthority().startsWith("mms"))) {}
    while ((e.equals(paramString)) || (TextUtils.isEmpty(paramString))) {
      return;
    }
    int j = 0;
    for (;;)
    {
      if ((j >= a.size()) || (((vp.a)a.get(j)).a(Uri.parse(paramString))))
      {
        MmsApp.c().e().a(paramUri);
        String str = e;
        e = paramString;
        new Thread(new vq(this, paramString, str, paramUri), "MeizuSlideviewDoc.updateFlymeMmsAttachPath").start();
        return;
      }
      j += 1;
    }
  }
  
  public boolean a(lm paramlm)
  {
    boolean bool2 = false;
    boolean bool1;
    if (TextUtils.isEmpty(e)) {
      bool1 = b(paramlm);
    }
    do
    {
      do
      {
        return bool1;
        localObject2 = e;
        localObject1 = localObject2;
        if (((String)localObject2).startsWith("file://")) {
          localObject1 = ((String)localObject2).substring("file://".length());
        }
        bool1 = bool2;
      } while (TextUtils.isEmpty((CharSequence)localObject1));
      bool1 = bool2;
    } while (c == null);
    Object localObject2 = new File((String)localObject1);
    if ((!((File)localObject2).exists()) || (!((File)localObject2).isFile()))
    {
      wd.a(2131493296, b, 0, 1, true, 0);
      return false;
    }
    localObject2 = ((File)localObject2).getParent();
    if (((String)localObject1).startsWith("/sdcard/Download/FMessage/")) {}
    for (Object localObject1 = wd.e();; localObject1 = localObject2)
    {
      if ((!paramlm.w()) && (paramlm.k().getAuthority().equals("mms"))) {
        d = paramlm.k();
      }
      localObject2 = lr.a(e);
      Intent localIntent = new Intent();
      localIntent.addFlags(524288);
      localIntent.setAction("com.meizu.action.SAVE_FILE");
      localIntent.putExtra("init_directory", (String)localObject1);
      localIntent.putExtra("SAVEATTACHMENT", true);
      localIntent.putExtra("android.intent.extra.TITLE", wd.a(paramlm.n(), paramlm.j(), "jpg"));
      localIntent.putExtra("android.intent.extra.STREAM", ((Uri)localObject2).toString());
      localIntent.putExtra("title", b.getString(2131493088));
      b.startActivityForResult(localIntent, 114);
      return true;
    }
  }
  
  public String b()
  {
    return i;
  }
  
  public boolean b(lm paramlm)
  {
    Uri localUri = paramlm.k();
    String str = wd.e();
    Intent localIntent = new Intent();
    localIntent.addFlags(524288);
    localIntent.setAction("com.meizu.action.SAVE_FILE");
    localIntent.putExtra("init_directory", str);
    localIntent.putExtra("SAVEATTACHMENT", true);
    localIntent.putExtra("android.intent.extra.TITLE", wd.a(paramlm.n(), paramlm.j(), "jpg"));
    localIntent.putExtra("android.intent.extra.STREAM", localUri.toString());
    localIntent.putExtra("title", b.getString(2131493088));
    b.startActivityForResult(localIntent, 110);
    return true;
  }
  
  public static final class a
  {
    public String a;
    public String b = "";
    public lm c;
    public int[] d;
    public boolean e = true;
    
    public boolean a(Uri paramUri)
    {
      if ((c instanceof li))
      {
        c.a(paramUri);
        return true;
      }
      return false;
    }
  }
  
  public static final class b
    extends lm
  {
    public b(Context paramContext, String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte, long paramLong)
    {
      super(paramString1, paramString2, paramString3, new byte[1]);
      i = paramLong;
    }
    
    public void a(att paramatt) {}
  }
}

/* Location:
 * Qualified Name:     vp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */