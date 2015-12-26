import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.location.core.a;
import com.amap.api.location.core.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.List<*>;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.json.JSONObject;

public class agu
  implements ahp
{
  private long A = 0L;
  private long B = 0L;
  private ahq C = ahq.a();
  private int D = 0;
  private String E = "00:00:00:00:00:00";
  private aie F = null;
  private StringBuilder G = new StringBuilder();
  private long H = 0L;
  private ahg I = null;
  private CellLocation J = null;
  private boolean K = false;
  ahi a;
  int b = -1;
  boolean c = false;
  agu.a d = new agu.a();
  TimerTask e;
  Timer f;
  agb g;
  int h = 0;
  private Context i = null;
  private int j = 9;
  private ConnectivityManager k = null;
  private WifiManager l = null;
  private TelephonyManager m = null;
  private List<ahh> n = new ArrayList();
  private List<ScanResult> o = new ArrayList();
  private Map<PendingIntent, List<aho>> p = new HashMap();
  private Map<PendingIntent, List<aho>> q = new HashMap();
  private PhoneStateListener r = null;
  private int s = -113;
  private agu.b t = new agu.b(null);
  private WifiInfo u = null;
  private JSONObject v = null;
  private ahf w = null;
  private long x = 0L;
  private boolean y = false;
  private long z = 0L;
  
  private void A()
  {
    try
    {
      if ((!c) && (a.b()) && (a != null))
      {
        a.b();
        c = true;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      c = true;
    }
  }
  
  private ahf a(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (i == null) {
      paramArrayOfByte = null;
    }
    for (;;)
    {
      return paramArrayOfByte;
      Object localObject = new ahr();
      ahz.b();
      paramArrayOfByte = C.a(paramArrayOfByte, i, v);
      ahz.b();
      try
      {
        d.a(paramArrayOfByte);
        localObject = ((ahr)localObject).a(paramArrayOfByte);
        if (!ahz.a((ahf)localObject)) {
          throw new AMapLocException("未知的错误");
        }
      }
      catch (AMapLocException paramArrayOfByte)
      {
        throw paramArrayOfByte;
        paramArrayOfByte = (byte[])localObject;
        if (((ahf)localObject).t() == null) {
          continue;
        }
        return (ahf)localObject;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  private ahh a(NeighboringCellInfo paramNeighboringCellInfo)
  {
    if (ahz.c() < 5) {
      return null;
    }
    try
    {
      ahh localahh = new ahh();
      String[] arrayOfString = ahz.a(m);
      a = arrayOfString[0];
      b = arrayOfString[1];
      c = paramNeighboringCellInfo.getLac();
      d = paramNeighboringCellInfo.getCid();
      j = ahz.a(paramNeighboringCellInfo.getRssi());
      return localahh;
    }
    catch (Throwable paramNeighboringCellInfo)
    {
      paramNeighboringCellInfo.printStackTrace();
    }
    return null;
  }
  
  private String a(int paramInt1, int paramInt2, int paramInt3)
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("e", paramInt1);
    localJSONObject.put("d", paramInt2);
    localJSONObject.put("u", paramInt3);
    return localJSONObject.toString();
  }
  
  private void a(CellLocation paramCellLocation)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!y)
    {
      localObject1 = localObject2;
      if (m != null)
      {
        localObject1 = localObject2;
        if (m != null) {
          localObject1 = z();
        }
      }
    }
    if (localObject1 == null) {}
    for (;;)
    {
      if (paramCellLocation == null) {}
      do
      {
        return;
        switch (ahz.a(paramCellLocation, i))
        {
        default: 
          return;
        }
      } while (m == null);
      c(paramCellLocation);
      return;
      d(paramCellLocation);
      return;
      paramCellLocation = (CellLocation)localObject1;
    }
  }
  
  private void a(List<ScanResult> paramList)
  {
    if (paramList != null) {}
    for (;;)
    {
      int i1;
      HashMap localHashMap;
      try
      {
        i1 = paramList.size();
        if (i1 < 1) {
          return;
        }
        localHashMap = new HashMap();
        i1 = 0;
        if (i1 >= paramList.size()) {
          break label145;
        }
        localObject = (ScanResult)paramList.get(i1);
        if ((paramList.size() > 20) && (!a(level))) {
          break label236;
        }
        if (SSID != null)
        {
          SSID = SSID.replace("*", ".");
          localHashMap.put(Integer.valueOf(level * 30 + i1), localObject);
        }
      }
      finally {}
      SSID = "null";
      continue;
      label145:
      Object localObject = new TreeMap(Collections.reverseOrder());
      ((TreeMap)localObject).putAll(localHashMap);
      paramList.clear();
      Iterator localIterator = ((TreeMap)localObject).entrySet().iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        paramList.add(((Map.Entry)localIterator.next()).getValue());
      } while (paramList.size() <= 29);
      localHashMap.clear();
      ((TreeMap)localObject).clear();
      continue;
      label236:
      i1 += 1;
    }
  }
  
  private boolean a(int paramInt)
  {
    int i1 = 20;
    try
    {
      paramInt = WifiManager.calculateSignalLevel(paramInt, 20);
      if (paramInt >= 1) {
        return true;
      }
    }
    catch (ArithmeticException localArithmeticException)
    {
      for (;;)
      {
        ahz.a(localArithmeticException);
        paramInt = i1;
      }
    }
    return false;
  }
  
  private boolean a(long paramLong)
  {
    long l1 = ahz.a();
    if (l1 - paramLong < 300L)
    {
      paramLong = 0L;
      if (w != null) {
        paramLong = l1 - w.h();
      }
    }
    return paramLong <= 10000L;
  }
  
  private boolean a(ScanResult paramScanResult)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramScanResult != null) {
      try
      {
        if (TextUtils.isEmpty(BSSID))
        {
          bool1 = bool2;
        }
        else
        {
          boolean bool3 = BSSID.equals("00:00:00:00:00:00");
          bool1 = bool2;
          if (!bool3) {
            bool1 = true;
          }
        }
      }
      catch (Exception paramScanResult)
      {
        return true;
      }
    }
    return bool1;
  }
  
  private boolean a(WifiInfo paramWifiInfo)
  {
    if ((paramWifiInfo == null) || (paramWifiInfo.getBSSID() == null)) {}
    while ((paramWifiInfo.getSSID() == null) || (paramWifiInfo.getBSSID().equals("00:00:00:00:00:00")) || (TextUtils.isEmpty(paramWifiInfo.getSSID()))) {
      return false;
    }
    return true;
  }
  
  /* Error */
  private byte[] a(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 426	aht
    //   5: dup
    //   6: invokespecial 427	aht:<init>	()V
    //   9: astore 10
    //   11: ldc_w 429
    //   14: putstatic 433	ahk:c	Ljava/lang/String;
    //   17: ldc_w 429
    //   20: astore 5
    //   22: ldc_w 429
    //   25: astore 8
    //   27: ldc_w 435
    //   30: invokestatic 440	aie:a	(Ljava/lang/String;)Ljava/lang/String;
    //   33: astore 12
    //   35: new 143	java/lang/StringBuilder
    //   38: dup
    //   39: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   42: astore 11
    //   44: new 143	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   51: astore 7
    //   53: new 143	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   60: astore 6
    //   62: aload_0
    //   63: getfield 81	agu:j	I
    //   66: iconst_2
    //   67: if_icmpne +1312 -> 1379
    //   70: ldc_w 442
    //   73: astore 4
    //   75: aload_0
    //   76: getfield 87	agu:m	Landroid/telephony/TelephonyManager;
    //   79: ifnull +87 -> 166
    //   82: getstatic 443	ahk:a	Ljava/lang/String;
    //   85: ifnull +17 -> 102
    //   88: ldc_w 445
    //   91: getstatic 443	ahk:a	Ljava/lang/String;
    //   94: invokevirtual 412	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   97: istore_3
    //   98: iload_3
    //   99: ifeq +25 -> 124
    //   102: aload_0
    //   103: getfield 87	agu:m	Landroid/telephony/TelephonyManager;
    //   106: invokevirtual 450	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   109: putstatic 443	ahk:a	Ljava/lang/String;
    //   112: getstatic 443	ahk:a	Ljava/lang/String;
    //   115: ifnonnull +9 -> 124
    //   118: ldc_w 445
    //   121: putstatic 443	ahk:a	Ljava/lang/String;
    //   124: getstatic 451	ahk:b	Ljava/lang/String;
    //   127: ifnull +17 -> 144
    //   130: ldc_w 445
    //   133: getstatic 451	ahk:b	Ljava/lang/String;
    //   136: invokevirtual 412	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   139: istore_3
    //   140: iload_3
    //   141: ifeq +25 -> 166
    //   144: aload_0
    //   145: getfield 87	agu:m	Landroid/telephony/TelephonyManager;
    //   148: invokevirtual 454	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
    //   151: putstatic 451	ahk:b	Ljava/lang/String;
    //   154: getstatic 451	ahk:b	Ljava/lang/String;
    //   157: ifnonnull +9 -> 166
    //   160: ldc_w 445
    //   163: putstatic 451	ahk:b	Ljava/lang/String;
    //   166: aconst_null
    //   167: astore_1
    //   168: aload_0
    //   169: getfield 83	agu:k	Landroid/net/ConnectivityManager;
    //   172: invokevirtual 460	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   175: astore 9
    //   177: aload 9
    //   179: astore_1
    //   180: aload_1
    //   181: invokestatic 463	ahq:a	(Landroid/net/NetworkInfo;)I
    //   184: iconst_m1
    //   185: if_icmpeq +557 -> 742
    //   188: aload_0
    //   189: getfield 87	agu:m	Landroid/telephony/TelephonyManager;
    //   192: invokestatic 466	ahq:a	(Landroid/telephony/TelephonyManager;)Ljava/lang/String;
    //   195: astore 8
    //   197: aload_0
    //   198: invokespecial 468	agu:t	()Z
    //   201: ifeq +503 -> 704
    //   204: aload_0
    //   205: aload_0
    //   206: getfield 112	agu:u	Landroid/net/wifi/WifiInfo;
    //   209: invokespecial 470	agu:a	(Landroid/net/wifi/WifiInfo;)Z
    //   212: ifeq +492 -> 704
    //   215: ldc_w 472
    //   218: astore_1
    //   219: aload 8
    //   221: astore 5
    //   223: aload_0
    //   224: getfield 114	agu:v	Lorg/json/JSONObject;
    //   227: invokestatic 475	ahq:a	(Lorg/json/JSONObject;)[Ljava/lang/String;
    //   230: iconst_1
    //   231: aaload
    //   232: astore 8
    //   234: aload 10
    //   236: aload 4
    //   238: putfield 477	aht:i	Ljava/lang/String;
    //   241: aload 10
    //   243: ldc_w 479
    //   246: putfield 481	aht:j	Ljava/lang/String;
    //   249: aload 10
    //   251: ldc_w 479
    //   254: putfield 483	aht:k	Ljava/lang/String;
    //   257: aload 10
    //   259: ldc_w 479
    //   262: putfield 485	aht:l	Ljava/lang/String;
    //   265: aload 10
    //   267: ldc_w 479
    //   270: putfield 487	aht:m	Ljava/lang/String;
    //   273: aload 10
    //   275: getstatic 489	ahk:d	Ljava/lang/String;
    //   278: putfield 490	aht:c	Ljava/lang/String;
    //   281: aload 10
    //   283: getstatic 492	ahk:e	Ljava/lang/String;
    //   286: putfield 493	aht:d	Ljava/lang/String;
    //   289: aload 10
    //   291: aload 8
    //   293: putfield 495	aht:n	Ljava/lang/String;
    //   296: aload 10
    //   298: getstatic 443	ahk:a	Ljava/lang/String;
    //   301: putfield 497	aht:o	Ljava/lang/String;
    //   304: aload 10
    //   306: getstatic 433	ahk:c	Ljava/lang/String;
    //   309: putfield 499	aht:r	Ljava/lang/String;
    //   312: aload 10
    //   314: getstatic 451	ahk:b	Ljava/lang/String;
    //   317: putfield 501	aht:p	Ljava/lang/String;
    //   320: aload 10
    //   322: aload_0
    //   323: getfield 139	agu:E	Ljava/lang/String;
    //   326: putfield 503	aht:q	Ljava/lang/String;
    //   329: aload 10
    //   331: aload 5
    //   333: putfield 505	aht:s	Ljava/lang/String;
    //   336: aload 10
    //   338: aload_1
    //   339: putfield 507	aht:t	Ljava/lang/String;
    //   342: aload 10
    //   344: invokestatic 511	com/amap/api/location/core/c:e	()Ljava/lang/String;
    //   347: putfield 513	aht:f	Ljava/lang/String;
    //   350: aload 10
    //   352: new 143	java/lang/StringBuilder
    //   355: dup
    //   356: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   359: ldc_w 515
    //   362: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: invokestatic 521	com/amap/api/location/core/c:d	()Ljava/lang/String;
    //   368: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: invokevirtual 522	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   374: putfield 524	aht:g	Ljava/lang/String;
    //   377: aload 10
    //   379: new 143	java/lang/StringBuilder
    //   382: dup
    //   383: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   386: invokestatic 526	com/amap/api/location/core/c:g	()Ljava/lang/String;
    //   389: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: ldc_w 528
    //   395: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   398: invokestatic 530	com/amap/api/location/core/c:h	()Ljava/lang/String;
    //   401: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   404: invokevirtual 522	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   407: putfield 532	aht:h	Ljava/lang/String;
    //   410: aload 10
    //   412: ldc_w 534
    //   415: putfield 536	aht:B	Ljava/lang/String;
    //   418: aload 10
    //   420: aload 12
    //   422: putfield 538	aht:C	Ljava/lang/String;
    //   425: aload_0
    //   426: getfield 94	agu:o	Ljava/util/List;
    //   429: ifnull +47 -> 476
    //   432: aload_0
    //   433: getfield 94	agu:o	Ljava/util/List;
    //   436: invokeinterface 289 1 0
    //   441: ifle +35 -> 476
    //   444: aload 10
    //   446: new 143	java/lang/StringBuilder
    //   449: dup
    //   450: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   453: invokestatic 192	ahz:b	()J
    //   456: aload_0
    //   457: getfield 126	agu:B	J
    //   460: lsub
    //   461: invokevirtual 541	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   464: ldc_w 429
    //   467: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: invokevirtual 522	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   473: putfield 542	aht:E	Ljava/lang/String;
    //   476: aload_0
    //   477: getfield 92	agu:n	Ljava/util/List;
    //   480: invokeinterface 289 1 0
    //   485: ifle +887 -> 1372
    //   488: new 143	java/lang/StringBuilder
    //   491: dup
    //   492: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   495: astore 4
    //   497: aload_0
    //   498: getfield 81	agu:j	I
    //   501: tableswitch	default:+886->1387, 1:+260->761, 2:+517->1018
    //   524: aload 4
    //   526: iconst_0
    //   527: aload 4
    //   529: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   532: invokevirtual 549	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   535: pop
    //   536: aload_0
    //   537: invokespecial 468	agu:t	()Z
    //   540: ifeq +681 -> 1221
    //   543: aload_0
    //   544: aload_0
    //   545: getfield 112	agu:u	Landroid/net/wifi/WifiInfo;
    //   548: invokespecial 470	agu:a	(Landroid/net/wifi/WifiInfo;)Z
    //   551: ifeq +843 -> 1394
    //   554: aload 6
    //   556: aload_0
    //   557: getfield 112	agu:u	Landroid/net/wifi/WifiInfo;
    //   560: invokevirtual 418	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   563: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: ldc_w 528
    //   569: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: pop
    //   573: aload 6
    //   575: aload_0
    //   576: getfield 112	agu:u	Landroid/net/wifi/WifiInfo;
    //   579: invokevirtual 550	android/net/wifi/WifiInfo:getRssi	()I
    //   582: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   585: ldc_w 528
    //   588: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   591: pop
    //   592: aload 6
    //   594: aload_0
    //   595: getfield 112	agu:u	Landroid/net/wifi/WifiInfo;
    //   598: invokevirtual 421	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   601: ldc_w 306
    //   604: ldc_w 308
    //   607: invokevirtual 314	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   610: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   613: pop
    //   614: goto +780 -> 1394
    //   617: iload_2
    //   618: aload_0
    //   619: getfield 94	agu:o	Ljava/util/List;
    //   622: invokeinterface 289 1 0
    //   627: if_icmpge +598 -> 1225
    //   630: aload_0
    //   631: getfield 94	agu:o	Ljava/util/List;
    //   634: iload_2
    //   635: invokeinterface 293 2 0
    //   640: checkcast 295	android/net/wifi/ScanResult
    //   643: astore 4
    //   645: aload_0
    //   646: aload 4
    //   648: invokespecial 555	agu:a	(Landroid/net/wifi/ScanResult;)Z
    //   651: ifeq +748 -> 1399
    //   654: aload 7
    //   656: aload 4
    //   658: getfield 403	android/net/wifi/ScanResult:BSSID	Ljava/lang/String;
    //   661: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: ldc_w 528
    //   667: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: pop
    //   671: aload 7
    //   673: aload 4
    //   675: getfield 298	android/net/wifi/ScanResult:level	I
    //   678: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   681: ldc_w 528
    //   684: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: pop
    //   688: aload 7
    //   690: iload_2
    //   691: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   694: ldc_w 306
    //   697: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: pop
    //   701: goto +698 -> 1399
    //   704: ldc_w 442
    //   707: astore 9
    //   709: aload 9
    //   711: astore_1
    //   712: aload 8
    //   714: astore 5
    //   716: aload_0
    //   717: invokespecial 468	agu:t	()Z
    //   720: ifne -497 -> 223
    //   723: aload_0
    //   724: invokespecial 557	agu:o	()V
    //   727: aload 9
    //   729: astore_1
    //   730: aload 8
    //   732: astore 5
    //   734: goto -511 -> 223
    //   737: astore_1
    //   738: aload_0
    //   739: monitorexit
    //   740: aload_1
    //   741: athrow
    //   742: aload_0
    //   743: aconst_null
    //   744: putfield 112	agu:u	Landroid/net/wifi/WifiInfo;
    //   747: aload 8
    //   749: astore_1
    //   750: goto -527 -> 223
    //   753: astore_1
    //   754: aload_1
    //   755: invokevirtual 251	java/lang/Throwable:printStackTrace	()V
    //   758: goto -282 -> 476
    //   761: aload_0
    //   762: getfield 92	agu:n	Ljava/util/List;
    //   765: iconst_0
    //   766: invokeinterface 293 2 0
    //   771: checkcast 221	ahh
    //   774: astore_1
    //   775: aload 4
    //   777: iconst_0
    //   778: aload 4
    //   780: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   783: invokevirtual 549	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   786: pop
    //   787: aload 4
    //   789: ldc_w 559
    //   792: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   795: aload_1
    //   796: getfield 227	ahh:a	Ljava/lang/String;
    //   799: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: ldc_w 561
    //   805: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   808: pop
    //   809: aload 4
    //   811: ldc_w 563
    //   814: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: aload_1
    //   818: getfield 229	ahh:b	Ljava/lang/String;
    //   821: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   824: ldc_w 565
    //   827: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   830: pop
    //   831: aload 4
    //   833: ldc_w 567
    //   836: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   839: aload_1
    //   840: getfield 236	ahh:c	I
    //   843: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   846: ldc_w 569
    //   849: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   852: pop
    //   853: aload 4
    //   855: ldc_w 571
    //   858: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   861: aload_1
    //   862: getfield 241	ahh:d	I
    //   865: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   868: pop
    //   869: aload 4
    //   871: ldc_w 573
    //   874: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   877: pop
    //   878: aload 4
    //   880: ldc_w 575
    //   883: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   886: aload_1
    //   887: getfield 248	ahh:j	I
    //   890: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   893: pop
    //   894: aload 4
    //   896: ldc_w 577
    //   899: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   902: pop
    //   903: aload 4
    //   905: invokevirtual 522	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   908: astore_1
    //   909: iconst_0
    //   910: istore_2
    //   911: iload_2
    //   912: aload_0
    //   913: getfield 92	agu:n	Ljava/util/List;
    //   916: invokeinterface 289 1 0
    //   921: if_icmpge +492 -> 1413
    //   924: iload_2
    //   925: ifne +6 -> 931
    //   928: goto +478 -> 1406
    //   931: aload_0
    //   932: getfield 92	agu:n	Ljava/util/List;
    //   935: iload_2
    //   936: invokeinterface 293 2 0
    //   941: checkcast 221	ahh
    //   944: astore 5
    //   946: aload 11
    //   948: aload 5
    //   950: getfield 236	ahh:c	I
    //   953: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   956: ldc_w 528
    //   959: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   962: pop
    //   963: aload 11
    //   965: aload 5
    //   967: getfield 241	ahh:d	I
    //   970: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   973: ldc_w 528
    //   976: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: pop
    //   980: aload 11
    //   982: aload 5
    //   984: getfield 248	ahh:j	I
    //   987: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   990: pop
    //   991: iload_2
    //   992: aload_0
    //   993: getfield 92	agu:n	Ljava/util/List;
    //   996: invokeinterface 289 1 0
    //   1001: iconst_1
    //   1002: isub
    //   1003: if_icmpeq +403 -> 1406
    //   1006: aload 11
    //   1008: ldc_w 306
    //   1011: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1014: pop
    //   1015: goto +391 -> 1406
    //   1018: aload_0
    //   1019: getfield 92	agu:n	Ljava/util/List;
    //   1022: iconst_0
    //   1023: invokeinterface 293 2 0
    //   1028: checkcast 221	ahh
    //   1031: astore_1
    //   1032: aload 4
    //   1034: iconst_0
    //   1035: aload 4
    //   1037: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   1040: invokevirtual 549	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   1043: pop
    //   1044: aload 4
    //   1046: ldc_w 559
    //   1049: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1052: aload_1
    //   1053: getfield 227	ahh:a	Ljava/lang/String;
    //   1056: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1059: ldc_w 561
    //   1062: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1065: pop
    //   1066: aload 4
    //   1068: ldc_w 579
    //   1071: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1074: aload_1
    //   1075: getfield 581	ahh:g	I
    //   1078: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1081: ldc_w 583
    //   1084: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1087: pop
    //   1088: aload 4
    //   1090: ldc_w 585
    //   1093: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1096: aload_1
    //   1097: getfield 586	ahh:h	I
    //   1100: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1103: ldc_w 588
    //   1106: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1109: pop
    //   1110: aload 4
    //   1112: ldc_w 590
    //   1115: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1118: aload_1
    //   1119: getfield 592	ahh:i	I
    //   1122: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1125: ldc_w 594
    //   1128: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1131: pop
    //   1132: aload_1
    //   1133: getfield 596	ahh:f	I
    //   1136: ifle +54 -> 1190
    //   1139: aload_1
    //   1140: getfield 598	ahh:e	I
    //   1143: ifle +47 -> 1190
    //   1146: aload 4
    //   1148: ldc_w 600
    //   1151: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1154: aload_1
    //   1155: getfield 596	ahh:f	I
    //   1158: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1161: ldc_w 602
    //   1164: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1167: pop
    //   1168: aload 4
    //   1170: ldc_w 604
    //   1173: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: aload_1
    //   1177: getfield 598	ahh:e	I
    //   1180: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1183: ldc_w 606
    //   1186: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1189: pop
    //   1190: aload 4
    //   1192: ldc_w 575
    //   1195: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1198: aload_1
    //   1199: getfield 248	ahh:j	I
    //   1202: invokevirtual 553	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1205: ldc_w 577
    //   1208: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1211: pop
    //   1212: aload 4
    //   1214: invokevirtual 522	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1217: astore_1
    //   1218: goto -694 -> 524
    //   1221: aload_0
    //   1222: invokespecial 557	agu:o	()V
    //   1225: aload 7
    //   1227: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   1230: ifle +16 -> 1246
    //   1233: aload 7
    //   1235: aload 7
    //   1237: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   1240: iconst_1
    //   1241: isub
    //   1242: invokevirtual 609	java/lang/StringBuilder:deleteCharAt	(I)Ljava/lang/StringBuilder;
    //   1245: pop
    //   1246: aload 7
    //   1248: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   1251: ifne +114 -> 1365
    //   1254: aload 6
    //   1256: astore 4
    //   1258: aload 10
    //   1260: aload_1
    //   1261: putfield 611	aht:v	Ljava/lang/String;
    //   1264: aload 10
    //   1266: aload 11
    //   1268: invokevirtual 522	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1271: putfield 613	aht:w	Ljava/lang/String;
    //   1274: aload 10
    //   1276: aload 6
    //   1278: invokevirtual 522	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1281: putfield 615	aht:x	Ljava/lang/String;
    //   1284: aload 10
    //   1286: aload 4
    //   1288: invokevirtual 522	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1291: putfield 617	aht:y	Ljava/lang/String;
    //   1294: aload 10
    //   1296: aload_0
    //   1297: getfield 81	agu:j	I
    //   1300: invokestatic 620	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   1303: putfield 622	aht:u	Ljava/lang/String;
    //   1306: aload 11
    //   1308: iconst_0
    //   1309: aload 11
    //   1311: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   1314: invokevirtual 549	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   1317: pop
    //   1318: aload 4
    //   1320: iconst_0
    //   1321: aload 4
    //   1323: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   1326: invokevirtual 549	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   1329: pop
    //   1330: aload 6
    //   1332: iconst_0
    //   1333: aload 6
    //   1335: invokevirtual 545	java/lang/StringBuilder:length	()I
    //   1338: invokevirtual 549	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   1341: pop
    //   1342: aload 10
    //   1344: invokevirtual 625	aht:a	()[B
    //   1347: astore_1
    //   1348: aload_0
    //   1349: monitorexit
    //   1350: aload_1
    //   1351: areturn
    //   1352: astore 9
    //   1354: goto -1174 -> 180
    //   1357: astore_1
    //   1358: goto -1192 -> 166
    //   1361: astore_1
    //   1362: goto -1238 -> 124
    //   1365: aload 7
    //   1367: astore 4
    //   1369: goto -111 -> 1258
    //   1372: ldc_w 429
    //   1375: astore_1
    //   1376: goto -840 -> 536
    //   1379: ldc_w 479
    //   1382: astore 4
    //   1384: goto -1309 -> 75
    //   1387: ldc_w 429
    //   1390: astore_1
    //   1391: goto -867 -> 524
    //   1394: iconst_0
    //   1395: istore_2
    //   1396: goto -779 -> 617
    //   1399: iload_2
    //   1400: iconst_1
    //   1401: iadd
    //   1402: istore_2
    //   1403: goto -786 -> 617
    //   1406: iload_2
    //   1407: iconst_1
    //   1408: iadd
    //   1409: istore_2
    //   1410: goto -499 -> 911
    //   1413: goto -889 -> 524
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1416	0	this	agu
    //   0	1416	1	paramObject	Object
    //   617	793	2	i1	int
    //   97	44	3	bool	boolean
    //   73	1310	4	localObject1	Object
    //   20	963	5	localObject2	Object
    //   60	1274	6	localStringBuilder1	StringBuilder
    //   51	1315	7	localStringBuilder2	StringBuilder
    //   25	723	8	str1	String
    //   175	553	9	localObject3	Object
    //   1352	1	9	localSecurityException	SecurityException
    //   9	1334	10	localaht	aht
    //   42	1268	11	localStringBuilder3	StringBuilder
    //   33	388	12	str2	String
    // Exception table:
    //   from	to	target	type
    //   2	17	737	finally
    //   27	70	737	finally
    //   75	98	737	finally
    //   102	124	737	finally
    //   124	140	737	finally
    //   144	166	737	finally
    //   168	177	737	finally
    //   180	215	737	finally
    //   223	425	737	finally
    //   425	476	737	finally
    //   476	524	737	finally
    //   524	536	737	finally
    //   536	614	737	finally
    //   617	701	737	finally
    //   716	727	737	finally
    //   742	747	737	finally
    //   754	758	737	finally
    //   761	909	737	finally
    //   911	924	737	finally
    //   931	1015	737	finally
    //   1018	1190	737	finally
    //   1190	1218	737	finally
    //   1221	1225	737	finally
    //   1225	1246	737	finally
    //   1246	1254	737	finally
    //   1258	1348	737	finally
    //   425	476	753	java/lang/Throwable
    //   168	177	1352	java/lang/SecurityException
    //   144	166	1357	java/lang/SecurityException
    //   102	124	1361	java/lang/SecurityException
  }
  
  private ahh b(CellLocation paramCellLocation)
  {
    paramCellLocation = (GsmCellLocation)paramCellLocation;
    ahh localahh = new ahh();
    String[] arrayOfString = ahz.a(m);
    a = arrayOfString[0];
    b = arrayOfString[1];
    c = paramCellLocation.getLac();
    d = paramCellLocation.getCid();
    j = s;
    return localahh;
  }
  
  private CellLocation b(List<?> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty()))
    {
      paramList = null;
      return paramList;
    }
    ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
    Object localObject4 = null;
    int i2 = 0;
    int i1 = 0;
    Object localObject1 = null;
    Object localObject7;
    for (;;)
    {
      if (i2 >= paramList.size()) {
        break label534;
      }
      localObject7 = paramList.get(i2);
      if (localObject7 != null) {
        break;
      }
      label56:
      i2 += 1;
    }
    for (;;)
    {
      Class localClass2;
      Class localClass3;
      Class localClass4;
      Class localClass1;
      boolean bool;
      int i5;
      int i6;
      try
      {
        localClass2 = localClassLoader.loadClass("android.telephony.CellInfoGsm");
        localClass3 = localClassLoader.loadClass("android.telephony.CellInfoWcdma");
        localClass4 = localClassLoader.loadClass("android.telephony.CellInfoLte");
        localClass1 = localClassLoader.loadClass("android.telephony.CellInfoCdma");
        bool = localClass2.isInstance(localObject7);
        if (bool)
        {
          i1 = 1;
          if (i1 <= 0) {
            continue;
          }
          localObject3 = null;
          if (i1 != 1) {
            continue;
          }
        }
      }
      catch (Exception localException2) {}
      try
      {
        localObject3 = localClass2.cast(localObject7);
        localObject7 = ahs.a(localObject3, "getCellIdentity", new Object[0]);
        if (localObject7 == null)
        {
          break label56;
          if (localClass3.isInstance(localObject7))
          {
            i1 = 2;
            continue;
          }
          if (localClass4.isInstance(localObject7))
          {
            i1 = 3;
            continue;
          }
          bool = localClass1.isInstance(localObject7);
          if (bool)
          {
            i1 = 4;
            continue;
          }
          i1 = 0;
          continue;
          if (i1 == 2)
          {
            localObject3 = localClass3.cast(localObject7);
            continue;
          }
          if (i1 == 3)
          {
            localObject3 = localClass4.cast(localObject7);
            continue;
          }
          if (i1 != 4) {
            continue;
          }
          localObject3 = localClass1.cast(localObject7);
          continue;
        }
        if (i1 == 4) {
          localObject3 = new CdmaCellLocation();
        }
      }
      catch (Exception localException3) {}
      try
      {
        i3 = ahs.b(localObject7, "getSystemId", new Object[0]);
        i4 = ahs.b(localObject7, "getNetworkId", new Object[0]);
        i5 = ahs.b(localObject7, "getBasestationId", new Object[0]);
        i6 = ahs.b(localObject7, "getLongitude", new Object[0]);
        ((CdmaCellLocation)localObject3).setCellLocationData(i5, ahs.b(localObject7, "getLatitude", new Object[0]), i6, i3, i4);
        localObject1 = localObject4;
        paramList = (List<?>)localObject3;
        if (i1 == 4) {
          break;
        }
        return (CellLocation)localObject1;
      }
      catch (Exception localException1)
      {
        localObject2 = localException3;
      }
      if (i1 == 3)
      {
        i3 = ahs.b(localObject7, "getTac", new Object[0]);
        i4 = ahs.b(localObject7, "getCi", new Object[0]);
        localObject3 = new GsmCellLocation();
      }
      try
      {
        ((GsmCellLocation)localObject3).setLacAndCid(i3, i4);
        paramList = (List<?>)localObject1;
        localObject1 = localObject3;
      }
      catch (Exception localException4)
      {
        localObject5 = localException3;
      }
      int i3 = ahs.b(localObject7, "getLac", new Object[0]);
      int i4 = ahs.b(localObject7, "getCid", new Object[0]);
      Object localObject3 = new GsmCellLocation();
      try
      {
        ((GsmCellLocation)localObject3).setLacAndCid(i3, i4);
        paramList = (List<?>)localObject1;
        localObject1 = localObject3;
      }
      catch (Exception localException5)
      {
        Object localObject5;
        localObject6 = localException3;
      }
      break label56;
      break label56;
      break label56;
      break label56;
      break label56;
      Object localObject6;
      break label56;
      label534:
      paramList = (List<?>)localObject2;
      Object localObject2 = localObject6;
    }
  }
  
  private void b(int paramInt)
  {
    if (paramInt == -113) {
      s = -113;
    }
    do
    {
      return;
      s = paramInt;
      switch (j)
      {
      default: 
        return;
      }
    } while (n.size() <= 0);
    n.get(0)).j = s;
  }
  
  private void c(int paramInt)
  {
    try
    {
      if (ahz.b() - H < 45000L) {
        return;
      }
      if ((e()) && ((!e()) || (F.f() >= 20)))
      {
        y();
        if (e == null) {
          e = new agw(this, paramInt);
        }
        if (f == null)
        {
          f = new Timer(false);
          f.schedule(e, 3000L, 3000L);
          return;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private void c(CellLocation paramCellLocation)
  {
    if ((n == null) || (paramCellLocation == null) || (m == null)) {
      return;
    }
    n.clear();
    Object localObject = (GsmCellLocation)paramCellLocation;
    int i1;
    if (((GsmCellLocation)localObject).getLac() == -1) {
      i1 = 0;
    }
    for (;;)
    {
      if (i1 == 0)
      {
        j = 9;
        ahz.a(new Object[] { "case 2,gsm illegal" });
        return;
        if ((((GsmCellLocation)localObject).getCid() == -1) || (((GsmCellLocation)localObject).getCid() == 65535) || (((GsmCellLocation)localObject).getCid() >= 268435455))
        {
          i1 = 0;
          continue;
        }
        if (((GsmCellLocation)localObject).getLac() == 0)
        {
          i1 = 0;
          continue;
        }
        if (((GsmCellLocation)localObject).getLac() > 65535)
        {
          i1 = 0;
          continue;
        }
        if (((GsmCellLocation)localObject).getCid() == 0) {
          i1 = 0;
        }
      }
      else
      {
        j = 1;
        localObject = null;
        n.add(b(paramCellLocation));
        paramCellLocation = (CellLocation)localObject;
        if (m != null) {
          paramCellLocation = m.getNeighboringCellInfo();
        }
        if (paramCellLocation == null) {
          break;
        }
        paramCellLocation = paramCellLocation.iterator();
        label189:
        label254:
        label339:
        for (;;)
        {
          if (paramCellLocation.hasNext())
          {
            localObject = (NeighboringCellInfo)paramCellLocation.next();
            if (((NeighboringCellInfo)localObject).getCid() == -1) {
              continue;
            }
            if (((NeighboringCellInfo)localObject).getLac() != -1) {
              break label254;
            }
            i1 = 0;
          }
          for (;;)
          {
            if (i1 == 0) {
              break label339;
            }
            localObject = a((NeighboringCellInfo)localObject);
            if (localObject == null) {
              break label189;
            }
            n.add(localObject);
            break label189;
            break;
            if (((NeighboringCellInfo)localObject).getLac() == 0) {
              i1 = 0;
            } else if (((NeighboringCellInfo)localObject).getLac() > 65535) {
              i1 = 0;
            } else if (((NeighboringCellInfo)localObject).getCid() == -1) {
              i1 = 0;
            } else if (((NeighboringCellInfo)localObject).getCid() == 0) {
              i1 = 0;
            } else if (((NeighboringCellInfo)localObject).getCid() == 65535) {
              i1 = 0;
            } else if (((NeighboringCellInfo)localObject).getCid() >= 268435455) {
              i1 = 0;
            } else {
              i1 = 1;
            }
          }
        }
      }
      i1 = 1;
    }
  }
  
  private void d(int paramInt)
  {
    int i2 = 70254591;
    if (!e()) {
      return;
    }
    for (;;)
    {
      try
      {
        x();
        i1 = i2;
        switch (paramInt)
        {
        case 0: 
          F.a(null, a(1, i1, 1));
          g = F.d();
          if (g != null)
          {
            Object localObject = g.a();
            localObject = C.a((byte[])localObject, i);
            if (e())
            {
              if ((TextUtils.isEmpty((CharSequence)localObject)) || (!((String)localObject).equals("true"))) {
                break label200;
              }
              F.a(g, a(1, i1, 1));
            }
          }
          y();
          if ((!e()) || (F.f() != 0)) {
            break label231;
          }
          w();
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return;
      }
      int i1 = 674234367;
      continue;
      if (!n())
      {
        i1 = 674234367;
        continue;
        label200:
        h += 1;
        F.a(g, a(1, i1, 0));
        continue;
        label231:
        if (h < 3) {
          break;
        }
        w();
        return;
        i1 = i2;
        continue;
      }
      i1 = 2083520511;
    }
  }
  
  private void d(CellLocation paramCellLocation)
  {
    n.clear();
    if (ahz.c() < 5) {
      return;
    }
    try
    {
      paramCellLocation = (CdmaCellLocation)paramCellLocation;
      if (paramCellLocation.getSystemId() <= 0)
      {
        j = 9;
        ahz.a(new Object[] { "cdma illegal" });
        return;
      }
    }
    catch (Throwable paramCellLocation)
    {
      paramCellLocation.printStackTrace();
      return;
    }
    if (paramCellLocation.getNetworkId() < 0)
    {
      j = 9;
      ahz.a(new Object[] { "cdma illegal" });
      return;
    }
    if (paramCellLocation.getBaseStationId() < 0)
    {
      j = 9;
      ahz.a(new Object[] { "cdma illegal" });
      return;
    }
    j = 2;
    String[] arrayOfString = ahz.a(m);
    ahh localahh = new ahh();
    a = arrayOfString[0];
    b = arrayOfString[1];
    g = paramCellLocation.getSystemId();
    h = paramCellLocation.getNetworkId();
    i = paramCellLocation.getBaseStationId();
    j = s;
    e = paramCellLocation.getBaseStationLatitude();
    f = paramCellLocation.getBaseStationLongitude();
    n.add(localahh);
  }
  
  private boolean e(CellLocation paramCellLocation)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    if (paramCellLocation == null)
    {
      bool2 = bool1;
      return bool2;
    }
    switch (ahz.a(paramCellLocation, i))
    {
    default: 
      label44:
      bool1 = true;
    }
    for (;;)
    {
      bool2 = bool1;
      if (bool1) {
        break;
      }
      return bool1;
      paramCellLocation = (GsmCellLocation)paramCellLocation;
      bool1 = bool2;
      if (paramCellLocation.getLac() != -1)
      {
        bool1 = bool2;
        if (paramCellLocation.getLac() != 0)
        {
          bool1 = bool2;
          if (paramCellLocation.getLac() <= 65535)
          {
            bool1 = bool2;
            if (paramCellLocation.getCid() != -1)
            {
              bool1 = bool2;
              if (paramCellLocation.getCid() != 0)
              {
                bool1 = bool2;
                if (paramCellLocation.getCid() != 65535)
                {
                  if (paramCellLocation.getCid() < 268435455) {
                    break label44;
                  }
                  bool1 = bool2;
                  continue;
                  bool1 = bool2;
                  try
                  {
                    if (ahs.b(paramCellLocation, "getSystemId", new Object[0]) > 0)
                    {
                      bool1 = bool2;
                      if (ahs.b(paramCellLocation, "getNetworkId", new Object[0]) >= 0)
                      {
                        int i1 = ahs.b(paramCellLocation, "getBaseStationId", new Object[0]);
                        if (i1 >= 0) {
                          break label44;
                        }
                        bool1 = bool2;
                      }
                    }
                  }
                  catch (Exception paramCellLocation)
                  {
                    bool1 = true;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  private void f()
  {
    l = ((WifiManager)ahz.b(i, "wifi"));
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
    localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
    localIntentFilter.addAction("android.intent.action.SCREEN_ON");
    localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
    localIntentFilter.addAction("android.intent.action.AIRPLANE_MODE");
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    i.registerReceiver(t, localIntentFilter);
    p();
  }
  
  private void g()
  {
    try
    {
      CellLocation.requestLocationUpdate();
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  private void h()
  {
    k = ((ConnectivityManager)ahz.b(i, "connectivity"));
    g();
    A = ahz.b();
    m = ((TelephonyManager)ahz.b(i, "phone"));
    if (m != null) {}
    for (int i1 = m.getPhoneType();; i1 = 9)
    {
      switch (i1)
      {
      default: 
        j = 9;
        r = new agv(this);
        if (ahz.c() < 7)
        {
          i1 = 2;
          label112:
          if (i1 != 0) {
            break label160;
          }
          if (m != null) {
            m.listen(r, 16);
          }
        }
        break;
      }
      for (;;)
      {
        return;
        j = 1;
        break;
        j = 2;
        break;
        i1 = 256;
        break label112;
        try
        {
          label160:
          if (m != null)
          {
            m.listen(r, i1 | 0x10);
            return;
          }
        }
        catch (SecurityException localSecurityException)
        {
          ahz.a(localSecurityException);
          return;
        }
      }
    }
  }
  
  private String i()
  {
    Object localObject3 = null;
    v();
    if (t())
    {
      u = l.getConnectionInfo();
      switch (j)
      {
      default: 
        label64:
        localObject1 = "";
      }
    }
    Object localObject2;
    label419:
    do
    {
      do
      {
        return (String)localObject1;
        o();
        break;
        if (n.size() <= 0) {
          break label64;
        }
        localObject1 = (ahh)n.get(0);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(a).append("#");
        ((StringBuilder)localObject2).append(b).append("#");
        ((StringBuilder)localObject2).append(c).append("#");
        ((StringBuilder)localObject2).append(d).append("#");
        ((StringBuilder)localObject2).append("network").append("#");
        if (o.size() > 0) {}
        for (localObject1 = "cellwifi";; localObject1 = "cell")
        {
          ((StringBuilder)localObject2).append((String)localObject1);
          return ((StringBuilder)localObject2).toString();
        }
        if (n.size() <= 0) {
          break label64;
        }
        localObject1 = (ahh)n.get(0);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(a).append("#");
        ((StringBuilder)localObject2).append(b).append("#");
        ((StringBuilder)localObject2).append(g).append("#");
        ((StringBuilder)localObject2).append(h).append("#");
        ((StringBuilder)localObject2).append(i).append("#");
        ((StringBuilder)localObject2).append("network").append("#");
        if (o.size() > 0) {}
        for (localObject1 = "cellwifi";; localObject1 = "cell")
        {
          ((StringBuilder)localObject2).append((String)localObject1);
          return ((StringBuilder)localObject2).toString();
        }
        localObject2 = String.format("#%s#", new Object[] { "network" });
        if (o.size() != 1) {
          break label419;
        }
        localObject1 = localObject3;
      } while (!a(u));
      localObject1 = localObject3;
    } while (o.size() == 0);
    if ((o.size() == 1) && (a(u)))
    {
      localObject1 = (ScanResult)o.get(0);
      if ((localObject1 == null) || (!u.getBSSID().equals(BSSID))) {
        break label517;
      }
    }
    label517:
    for (Object localObject1 = null;; localObject1 = localObject2)
    {
      return (String)localObject1;
      return (String)localObject2 + "wifi";
    }
  }
  
  private StringBuilder j()
  {
    v();
    StringBuilder localStringBuilder = new StringBuilder(700);
    switch (j)
    {
    default: 
      if ((E == null) || (E.equals("00:00:00:00:00:00")))
      {
        if (u != null) {
          break;
        }
        if (l != null)
        {
          u = l.getConnectionInfo();
          if (u != null)
          {
            E = u.getMacAddress();
            if (E == null) {
              E = "00:00:00:00:00:00";
            }
            u = null;
          }
        }
      }
      if (t()) {
        if (!a(u)) {
          break label438;
        }
      }
      break;
    }
    label258:
    label282:
    label438:
    for (Object localObject1 = u.getBSSID();; localObject1 = "")
    {
      int i2 = 0;
      int i1 = 0;
      for (;;)
      {
        if (i2 < o.size())
        {
          Object localObject2 = (ScanResult)o.get(i2);
          int i3 = i1;
          if (a((ScanResult)localObject2))
          {
            String str = BSSID;
            localObject2 = "nb";
            if (((String)localObject1).equals(str))
            {
              localObject2 = "access";
              i1 = 1;
            }
            localStringBuilder.append(String.format("#%s,%s", new Object[] { str, localObject2 }));
            i3 = i1;
          }
          i2 += 1;
          i1 = i3;
          continue;
          i1 = 0;
          if (i1 < n.size()) {
            if (i1 != 0) {
              break label282;
            }
          }
          for (;;)
          {
            i1 += 1;
            break label258;
            break;
            localObject1 = (ahh)n.get(i1);
            localStringBuilder.append("#").append(b);
            localStringBuilder.append("|").append(c);
            localStringBuilder.append("|").append(d);
          }
          E = u.getMacAddress();
          if (E != null) {
            break;
          }
          E = "00:00:00:00:00:00";
          break;
        }
      }
      if ((i1 == 0) && (((String)localObject1).length() > 0))
      {
        localStringBuilder.append("#").append((String)localObject1);
        localStringBuilder.append(",access");
      }
      for (;;)
      {
        if (localStringBuilder.length() > 0) {
          localStringBuilder.deleteCharAt(0);
        }
        return localStringBuilder;
        o();
      }
    }
  }
  
  private byte[] k()
  {
    try
    {
      if (l())
      {
        g();
        A = ahz.b();
      }
      if (m()) {
        p();
      }
      byte[] arrayOfByte = a(null);
      return arrayOfByte;
    }
    finally {}
  }
  
  private boolean l()
  {
    if (y) {}
    while ((A == 0L) || (ahz.b() - A < ahk.j)) {
      return false;
    }
    return true;
  }
  
  private boolean m()
  {
    if (!t()) {}
    while ((B == 0L) || (ahz.b() - B < ahk.i)) {
      return false;
    }
    return true;
  }
  
  private boolean n()
  {
    if (l == null) {}
    for (;;)
    {
      return false;
      if (t())
      {
        NetworkInfo localNetworkInfo = null;
        try
        {
          if (k != null) {
            localNetworkInfo = k.getActiveNetworkInfo();
          }
          if (ahq.a(localNetworkInfo) != -1)
          {
            boolean bool = a(l.getConnectionInfo());
            if (bool) {
              return true;
            }
          }
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          return false;
        }
        catch (SecurityException localSecurityException) {}
      }
    }
    return false;
  }
  
  private void o()
  {
    o.clear();
    u = null;
  }
  
  private void p()
  {
    if (t()) {}
    try
    {
      l.startScan();
      B = ahz.b();
      return;
    }
    catch (SecurityException localSecurityException) {}
  }
  
  private boolean q()
  {
    if (z == 0L) {}
    while (ahz.b() - z >= 2000L) {
      return false;
    }
    return true;
  }
  
  private void r()
  {
    if ((w == null) || (p.size() < 1)) {
      return;
    }
    Iterator localIterator = p.entrySet().iterator();
    while ((localIterator != null) && (localIterator.hasNext()))
    {
      Object localObject1 = (Map.Entry)localIterator.next();
      PendingIntent localPendingIntent = (PendingIntent)((Map.Entry)localObject1).getKey();
      Object localObject2 = (List)((Map.Entry)localObject1).getValue();
      localObject1 = new Intent();
      Bundle localBundle = new Bundle();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        aho localaho = (aho)((Iterator)localObject2).next();
        long l1 = localaho.a();
        if ((l1 == -1L) || (l1 >= ahz.b()))
        {
          float f1 = ahz.a(new double[] { b, a, w.f(), w.e() });
          if (f1 < c)
          {
            localBundle.putFloat("distance", f1);
            localBundle.putString("fence", localaho.b());
            ((Intent)localObject1).putExtras(localBundle);
            try
            {
              localPendingIntent.send(i, 0, (Intent)localObject1);
            }
            catch (Throwable localThrowable)
            {
              localThrowable.printStackTrace();
            }
          }
        }
      }
    }
  }
  
  private void s()
  {
    switch (j)
    {
    }
    do
    {
      do
      {
        return;
      } while (n.size() != 0);
      j = 9;
      return;
    } while (n.size() != 0);
    j = 9;
  }
  
  private boolean t()
  {
    boolean bool1 = false;
    boolean bool2 = false;
    if (l == null) {}
    do
    {
      do
      {
        return bool2;
        try
        {
          bool2 = l.isWifiEnabled();
          bool1 = bool2;
        }
        catch (Exception localException2)
        {
          for (;;) {}
        }
        bool2 = bool1;
      } while (bool1);
      bool2 = bool1;
    } while (ahz.c() <= 17);
    try
    {
      bool2 = String.valueOf(ahs.a(l, "isScanAlwaysAvailable", new Object[0])).equals("true");
      return bool2;
    }
    catch (Exception localException1)
    {
      return bool1;
    }
  }
  
  private ahf u()
  {
    return a(k(), false);
  }
  
  private void v()
  {
    if (y)
    {
      j = 9;
      n.clear();
      return;
    }
    s();
  }
  
  private void w()
  {
    if (f != null)
    {
      f.cancel();
      f = null;
    }
    if (e != null)
    {
      e.cancel();
      e = null;
    }
  }
  
  private void x()
  {
    if (!e()) {
      return;
    }
    try
    {
      F.a(768);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      ahz.a(localThrowable);
    }
  }
  
  private void y()
  {
    if (!e()) {}
    for (;;)
    {
      return;
      if (F.f() <= 0) {
        try
        {
          boolean bool = F.e();
          if (bool) {}
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
        }
      }
    }
  }
  
  private CellLocation z()
  {
    Object localObject3;
    if (m == null) {
      localObject3 = null;
    }
    for (;;)
    {
      return (CellLocation)localObject3;
      try
      {
        Object localObject1 = m.getCellLocation();
        localObject3 = localObject1;
        if (e((CellLocation)localObject1)) {
          continue;
        }
        try
        {
          localObject3 = b((List)ahs.a(m, "getAllCellInfo", new Object[0]));
          localObject1 = localObject3;
        }
        catch (Exception localException2)
        {
          Object localObject2;
          for (;;) {}
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          for (;;) {}
        }
        localObject3 = localObject1;
        if (!e((CellLocation)localObject1)) {
          continue;
        }
        return (CellLocation)localObject1;
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          localObject2 = null;
        }
      }
    }
  }
  
  public int a(boolean paramBoolean, int paramInt)
  {
    if (!paramBoolean) {
      w();
    }
    while (e())
    {
      return F.f();
      c(paramInt);
    }
    return -1;
  }
  
  public ahf a()
  {
    int i2 = 1;
    if (i == null) {
      throw new AMapLocException("未知的错误");
    }
    if (TextUtils.isEmpty(ahk.d)) {
      throw new AMapLocException("key鉴权失败");
    }
    if (TextUtils.isEmpty(ahk.e)) {
      throw new AMapLocException("key鉴权失败");
    }
    if ("false".equals(ahq.a(v)[0]))
    {
      Log.e("AuthLocation", "key鉴权失败");
      throw new AMapLocException("key鉴权失败");
    }
    if (l())
    {
      g();
      A = ahz.b();
    }
    if (m()) {
      p();
    }
    D += 1;
    if (D > 1)
    {
      c();
      if ((!c) && (a.b())) {
        A();
      }
    }
    if (o == null) {
      o = new ArrayList();
    }
    if (D == 1)
    {
      y = ahz.a(i);
      if ((t()) && (o.isEmpty()) && (l != null)) {
        o = l.getScanResults();
      }
    }
    if ((D == 1) && (t()) && (o.isEmpty()))
    {
      i1 = 4;
      while ((i1 > 0) && (o.size() == 0))
      {
        SystemClock.sleep(500L);
        i1 -= 1;
      }
    }
    if ((a(x)) && (w != null))
    {
      x = ahz.a();
      return w;
    }
    String str;
    try
    {
      a(J);
      a(o);
      str = i();
      if (!TextUtils.isEmpty(str)) {
        break label436;
      }
      if (a.b())
      {
        if (b != 0) {
          SystemClock.sleep(500L);
        }
        if (b == 0)
        {
          w = a.d();
          r();
          d();
          if (w != null) {
            return w;
          }
        }
      }
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        localThrowable1.printStackTrace();
      }
    }
    throw new AMapLocException("获取基站/WiFi信息为空或失败");
    label436:
    StringBuilder localStringBuilder = j();
    Object localObject1 = null;
    try
    {
      localObject2 = I.a(str, localStringBuilder, "mem");
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        Object localObject2;
        long l1;
        continue;
        i1 = 0;
      }
    }
    if (localObject1 != null)
    {
      l1 = ((ahf)localObject1).h();
      if (ahz.a() - l1 > 3600000L)
      {
        i1 = i2;
        if ((localObject1 == null) || (i1 != 0)) {}
        for (;;)
        {
          try
          {
            localObject2 = u();
            w = ((ahf)localObject2);
            I.a(str, w, localStringBuilder);
            localStringBuilder.delete(0, localStringBuilder.length());
            x = ahz.a();
            r();
            d();
            return w;
          }
          catch (AMapLocException localAMapLocException)
          {
            localObject2 = localObject1;
            if (localObject1 != null) {
              continue;
            }
            throw localAMapLocException;
          }
          w = ((ahf)localObject1);
        }
      }
    }
  }
  
  public void a(aho paramaho, PendingIntent paramPendingIntent)
  {
    if ((paramPendingIntent == null) || (paramaho == null)) {}
    long l1;
    do
    {
      return;
      l1 = paramaho.a();
    } while ((l1 != -1L) && (l1 < ahz.b()));
    if (p.get(paramPendingIntent) != null)
    {
      localObject = (List)p.get(paramPendingIntent);
      ((List)localObject).add(paramaho);
      p.put(paramPendingIntent, localObject);
      return;
    }
    Object localObject = new ArrayList();
    ((List)localObject).add(paramaho);
    p.put(paramPendingIntent, localObject);
  }
  
  public void a(PendingIntent paramPendingIntent)
  {
    if (paramPendingIntent == null) {
      return;
    }
    p.remove(paramPendingIntent);
  }
  
  public void a(Context paramContext)
  {
    if (paramContext == null) {}
    while (i != null) {
      return;
    }
    i = paramContext.getApplicationContext();
    try
    {
      if (a == null)
      {
        a = new ahi(paramContext);
        a.a(d);
      }
      try
      {
        if (I == null) {
          I = new ahg(paramContext);
        }
        ahz.a(i, "in debug mode, only for test");
        f();
        h();
        H = ahz.b();
        return;
      }
      catch (Throwable paramContext)
      {
        for (;;) {}
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  public void a(Context paramContext, AMapLocation paramAMapLocation)
  {
    if ((paramAMapLocation == null) || (q.size() < 1)) {
      return;
    }
    Iterator localIterator = q.entrySet().iterator();
    while ((localIterator != null) && (localIterator.hasNext()))
    {
      Object localObject1 = (Map.Entry)localIterator.next();
      PendingIntent localPendingIntent = (PendingIntent)((Map.Entry)localObject1).getKey();
      Object localObject2 = (List)((Map.Entry)localObject1).getValue();
      localObject1 = new Intent();
      Bundle localBundle = new Bundle();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        aho localaho = (aho)((Iterator)localObject2).next();
        long l1 = localaho.a();
        if ((l1 == -1L) || (l1 >= ahz.b()))
        {
          float f1 = ahz.a(new double[] { b, a, paramAMapLocation.getLatitude(), paramAMapLocation.getLongitude() });
          if (f1 >= c)
          {
            if (d != 0) {
              d = 0;
            }
          }
          else if (f1 < c)
          {
            if (d != 1) {
              d = 1;
            }
          }
          else
          {
            localBundle.putFloat("distance", f1);
            localBundle.putString("fence", localaho.b());
            localBundle.putInt("status", d);
            ((Intent)localObject1).putExtras(localBundle);
            try
            {
              localPendingIntent.send(paramContext, 0, (Intent)localObject1);
            }
            catch (Throwable localThrowable)
            {
              localThrowable.printStackTrace();
            }
          }
        }
      }
    }
  }
  
  public void a(String paramString)
  {
    if ((paramString == null) || (paramString.indexOf("##") == -1)) {}
    do
    {
      return;
      paramString = paramString.split("##");
    } while (paramString.length != 3);
    ahk.a(paramString[0]);
    if (!ahk.e.equals(paramString[1])) {
      I.a();
    }
    ahk.b(paramString[1]);
    ahk.c(paramString[2]);
  }
  
  public void a(JSONObject paramJSONObject)
  {
    v = paramJSONObject;
  }
  
  /* Error */
  public void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 141	agu:F	Laie;
    //   4: ifnull +15 -> 19
    //   7: aload_0
    //   8: getfield 141	agu:F	Laie;
    //   11: invokevirtual 1113	aie:c	()V
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 163	agu:K	Z
    //   19: aload_0
    //   20: getfield 79	agu:i	Landroid/content/Context;
    //   23: ifnull +14 -> 37
    //   26: aload_0
    //   27: getfield 79	agu:i	Landroid/content/Context;
    //   30: aload_0
    //   31: getfield 110	agu:t	Lagu$b;
    //   34: invokevirtual 1117	android/content/Context:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   37: aload_0
    //   38: aconst_null
    //   39: putfield 110	agu:t	Lagu$b;
    //   42: aload_0
    //   43: invokespecial 771	agu:w	()V
    //   46: aload_0
    //   47: getfield 87	agu:m	Landroid/telephony/TelephonyManager;
    //   50: ifnull +22 -> 72
    //   53: aload_0
    //   54: getfield 103	agu:r	Landroid/telephony/PhoneStateListener;
    //   57: ifnull +15 -> 72
    //   60: aload_0
    //   61: getfield 87	agu:m	Landroid/telephony/TelephonyManager;
    //   64: aload_0
    //   65: getfield 103	agu:r	Landroid/telephony/PhoneStateListener;
    //   68: iconst_0
    //   69: invokevirtual 844	android/telephony/TelephonyManager:listen	(Landroid/telephony/PhoneStateListener;I)V
    //   72: aload_0
    //   73: getfield 79	agu:i	Landroid/content/Context;
    //   76: ifnull +24 -> 100
    //   79: aload_0
    //   80: getfield 154	agu:I	Lahg;
    //   83: ifnull +17 -> 100
    //   86: aload_0
    //   87: getfield 154	agu:I	Lahg;
    //   90: invokevirtual 1107	ahg:a	()V
    //   93: aload_0
    //   94: getfield 154	agu:I	Lahg;
    //   97: invokevirtual 1119	ahg:b	()V
    //   100: iconst_0
    //   101: invokestatic 1121	ahk:a	(Z)V
    //   104: aload_0
    //   105: lconst_0
    //   106: putfield 118	agu:x	J
    //   109: aload_0
    //   110: getfield 92	agu:n	Ljava/util/List;
    //   113: invokeinterface 343 1 0
    //   118: aload_0
    //   119: getfield 99	agu:p	Ljava/util/Map;
    //   122: invokeinterface 1122 1 0
    //   127: aload_0
    //   128: getfield 101	agu:q	Ljava/util/Map;
    //   131: invokeinterface 1122 1 0
    //   136: aload_0
    //   137: bipush -113
    //   139: putfield 105	agu:s	I
    //   142: aload_0
    //   143: invokespecial 557	agu:o	()V
    //   146: aload_0
    //   147: aconst_null
    //   148: putfield 116	agu:w	Lahf;
    //   151: aload_0
    //   152: aconst_null
    //   153: putfield 79	agu:i	Landroid/content/Context;
    //   156: aload_0
    //   157: aconst_null
    //   158: putfield 87	agu:m	Landroid/telephony/TelephonyManager;
    //   161: aload_0
    //   162: getfield 175	agu:a	Lahi;
    //   165: ifnull +39 -> 204
    //   168: aload_0
    //   169: getfield 150	agu:b	I
    //   172: ifne +17 -> 189
    //   175: aload_0
    //   176: getfield 152	agu:c	Z
    //   179: ifeq +10 -> 189
    //   182: aload_0
    //   183: getfield 175	agu:a	Lahi;
    //   186: invokevirtual 1123	ahi:a	()V
    //   189: aload_0
    //   190: iconst_m1
    //   191: putfield 150	agu:b	I
    //   194: aload_0
    //   195: iconst_0
    //   196: putfield 152	agu:c	Z
    //   199: aload_0
    //   200: aconst_null
    //   201: putfield 175	agu:a	Lahi;
    //   204: return
    //   205: astore_1
    //   206: aload_1
    //   207: invokevirtual 251	java/lang/Throwable:printStackTrace	()V
    //   210: goto -191 -> 19
    //   213: astore_1
    //   214: aload_0
    //   215: aconst_null
    //   216: putfield 110	agu:t	Lagu$b;
    //   219: aload_1
    //   220: athrow
    //   221: astore_1
    //   222: aload_1
    //   223: invokevirtual 251	java/lang/Throwable:printStackTrace	()V
    //   226: goto -154 -> 72
    //   229: astore_1
    //   230: return
    //   231: astore_1
    //   232: goto -195 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	235	0	this	agu
    //   205	2	1	localThrowable1	Throwable
    //   213	7	1	localObject	Object
    //   221	2	1	localThrowable2	Throwable
    //   229	1	1	localThrowable3	Throwable
    //   231	1	1	localThrowable4	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	19	205	java/lang/Throwable
    //   19	37	213	finally
    //   46	72	221	java/lang/Throwable
    //   161	189	229	java/lang/Throwable
    //   189	204	229	java/lang/Throwable
    //   19	37	231	java/lang/Throwable
  }
  
  public void b(aho paramaho, PendingIntent paramPendingIntent)
  {
    if ((paramPendingIntent == null) || (paramaho == null)) {}
    long l1;
    do
    {
      return;
      l1 = paramaho.a();
    } while ((l1 != -1L) && (l1 < ahz.b()));
    if (q.get(paramPendingIntent) != null)
    {
      localObject = (List)q.get(paramPendingIntent);
      ((List)localObject).add(paramaho);
      q.put(paramPendingIntent, localObject);
      return;
    }
    Object localObject = new ArrayList();
    ((List)localObject).add(paramaho);
    q.put(paramPendingIntent, localObject);
  }
  
  public void b(PendingIntent paramPendingIntent)
  {
    if (paramPendingIntent == null) {
      return;
    }
    q.remove(paramPendingIntent);
  }
  
  public void c()
  {
    try
    {
      if (F == null)
      {
        F = aie.a(i);
        F.a(256);
      }
      if (!K)
      {
        K = true;
        F.a();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public void d()
  {
    if ((w == null) || (q.size() < 1)) {
      return;
    }
    Iterator localIterator = q.entrySet().iterator();
    while ((localIterator != null) && (localIterator.hasNext()))
    {
      Object localObject1 = (Map.Entry)localIterator.next();
      PendingIntent localPendingIntent = (PendingIntent)((Map.Entry)localObject1).getKey();
      Object localObject2 = (List)((Map.Entry)localObject1).getValue();
      localObject1 = new Intent();
      Bundle localBundle = new Bundle();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        aho localaho = (aho)((Iterator)localObject2).next();
        long l1 = localaho.a();
        if ((l1 == -1L) || (l1 >= ahz.b()))
        {
          float f1 = ahz.a(new double[] { b, a, w.f(), w.e() });
          if (f1 >= c)
          {
            if (d != 0) {
              d = 0;
            }
          }
          else if (f1 < c)
          {
            if (d != 1) {
              d = 1;
            }
          }
          else
          {
            localBundle.putFloat("distance", f1);
            localBundle.putString("fence", localaho.b());
            localBundle.putInt("status", d);
            ((Intent)localObject1).putExtras(localBundle);
            try
            {
              localPendingIntent.send(i, 0, (Intent)localObject1);
            }
            catch (Throwable localThrowable)
            {
              localThrowable.printStackTrace();
            }
          }
        }
      }
    }
  }
  
  boolean e()
  {
    return F != null;
  }
  
  class a
    implements ahi.a
  {
    a() {}
    
    public void a(int paramInt)
    {
      b = paramInt;
    }
  }
  
  class b
    extends BroadcastReceiver
  {
    private b() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent == null) {}
      do
      {
        do
        {
          do
          {
            for (;;)
            {
              return;
              try
              {
                paramIntent = paramIntent.getAction();
                if (paramIntent.equals("android.net.wifi.SCAN_RESULTS"))
                {
                  if (agu.d(agu.this) == null) {
                    continue;
                  }
                  agu.a(agu.this, agu.d(agu.this).getScanResults());
                  agu.c(agu.this, ahz.b());
                  if (agu.e(agu.this) != null) {
                    continue;
                  }
                  agu.a(agu.this, new ArrayList());
                }
              }
              catch (Throwable paramContext)
              {
                paramContext.printStackTrace();
                return;
              }
            }
            if (!paramIntent.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
              break;
            }
            paramContext = agu.d(agu.this);
          } while (paramContext == null);
          int i = 4;
          try
          {
            int j = agu.d(agu.this).getWifiState();
            i = j;
          }
          catch (SecurityException paramContext)
          {
            for (;;) {}
          }
          switch (i)
          {
          case 2: 
          case 3: 
          default: 
            return;
          case 0: 
            agu.f(agu.this);
            return;
          case 1: 
            agu.f(agu.this);
            return;
          }
          agu.f(agu.this);
          return;
          if (paramIntent.equals("android.intent.action.SCREEN_ON"))
          {
            agu.g(agu.this);
            agu.h(agu.this);
            ahk.i = 10000L;
            ahk.j = 30000L;
            return;
          }
          if (!paramIntent.equals("android.intent.action.SCREEN_OFF")) {
            break;
          }
        } while (agu.i(agu.this) < 5);
        ahk.i = 20000L;
        ahk.j = 60000L;
        return;
        if (paramIntent.equals("android.intent.action.AIRPLANE_MODE"))
        {
          agu.a(agu.this, ahz.a(paramContext));
          return;
        }
      } while (!paramIntent.equals("android.net.conn.CONNECTIVITY_CHANGE"));
      a(true, 2);
    }
  }
}

/* Location:
 * Qualified Name:     agu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */