import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.NeighboringCellInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class agc
{
  private Context a;
  private int b = 0;
  private int c = 0;
  private int d = 0;
  
  protected agc(Context paramContext)
  {
    a = paramContext;
    a(768);
  }
  
  private static int a(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return paramInt1;
    }
    return paramInt2;
  }
  
  protected static afv a(Location paramLocation, agf paramagf, int paramInt, byte paramByte, long paramLong, boolean paramBoolean)
  {
    afv localafv = new afv();
    if ((paramInt <= 0) || (paramInt > 3) || (paramagf == null)) {
      return null;
    }
    int j;
    label50:
    Object localObject1;
    label266:
    Object localObject2;
    if ((paramInt == 1) || (paramInt == 3))
    {
      i = 1;
      if ((paramInt != 2) && (paramInt != 3)) {
        break label702;
      }
      j = 1;
      localObject1 = paramagf.p().getBytes();
      System.arraycopy(localObject1, 0, c, 0, a(localObject1.length, c.length));
      localObject1 = paramagf.f().getBytes();
      System.arraycopy(localObject1, 0, g, 0, a(localObject1.length, g.length));
      localObject1 = paramagf.g().getBytes();
      System.arraycopy(localObject1, 0, a, 0, a(localObject1.length, a.length));
      localObject1 = paramagf.h().getBytes();
      System.arraycopy(localObject1, 0, b, 0, a(localObject1.length, b.length));
      d = ((short)paramagf.q());
      e = ((short)paramagf.r());
      f = ((byte)paramagf.s());
      localObject1 = paramagf.t().getBytes();
      System.arraycopy(localObject1, 0, h, 0, a(localObject1.length, h.length));
      paramLong /= 1000L;
      if ((paramLocation == null) || (!paramagf.e())) {
        break label708;
      }
      paramInt = 1;
      if (paramInt == 0) {
        break label722;
      }
      localObject1 = new aid();
      b = ((int)paramLong);
      localObject2 = new afu();
      a = ((int)(paramLocation.getLongitude() * 1000000.0D));
      b = ((int)(paramLocation.getLatitude() * 1000000.0D));
      c = ((int)paramLocation.getAltitude());
      d = ((int)paramLocation.getAccuracy());
      e = ((int)paramLocation.getSpeed());
      f = ((short)(int)paramLocation.getBearing());
      if ((Build.MODEL.equals("sdk")) || ((agf.b(paramagf.y())) && (aie.b))) {
        break label713;
      }
      g = 0;
      label398:
      h = paramByte;
      i = System.currentTimeMillis();
      j = paramagf.o();
      c = ((afu)localObject2);
      j.add(localObject1);
    }
    Object localObject3;
    Object localObject4;
    for (;;)
    {
      k = 1;
      paramInt = k;
      if (!paramagf.c()) {
        break label977;
      }
      paramInt = k;
      if (paramagf.i()) {
        break label977;
      }
      paramInt = k;
      if (i == 0) {
        break label977;
      }
      paramInt = k;
      if (paramBoolean) {
        break label977;
      }
      localObject1 = new aid();
      b = ((int)paramLong);
      localObject2 = new aib();
      localObject3 = paramagf.a(paramLocation.getSpeed());
      if ((localObject3 != null) && (((List)localObject3).size() >= 3))
      {
        a = ((short)((Integer)((List)localObject3).get(0)).intValue());
        b = ((Integer)((List)localObject3).get(1)).intValue();
      }
      c = paramagf.l();
      localObject3 = paramagf.m();
      d = ((byte)((List)localObject3).size());
      paramInt = 0;
      while (paramInt < ((List)localObject3).size())
      {
        localObject4 = new age();
        a = ((short)((NeighboringCellInfo)((List)localObject3).get(paramInt)).getLac());
        b = ((NeighboringCellInfo)((List)localObject3).get(paramInt)).getCid();
        c = ((byte)((NeighboringCellInfo)((List)localObject3).get(paramInt)).getRssi());
        e.add(localObject4);
        paramInt += 1;
      }
      i = 0;
      break;
      label702:
      j = 0;
      break label50;
      label708:
      paramInt = 0;
      break label266;
      label713:
      g = 1;
      break label398;
      label722:
      if (!paramBoolean) {
        break label955;
      }
      localObject1 = new aid();
      b = ((int)paramLong);
      localObject2 = new afx();
      a = paramagf.x();
      paramInt = 0;
      while (paramInt < a)
      {
        localObject3 = new afy();
        a = ((byte)paramagf.a(paramInt).length());
        System.arraycopy(paramagf.a(paramInt).getBytes(), 0, b, 0, a);
        c = paramagf.b(paramInt);
        d = paramagf.c(paramInt);
        e = paramagf.d(paramInt);
        f = paramagf.e(paramInt);
        g = paramagf.f(paramInt);
        h = ((byte)paramagf.g(paramInt).length());
        System.arraycopy(paramagf.g(paramInt).getBytes(), 0, i, 0, h);
        j = paramagf.h(paramInt);
        b.add(localObject3);
        paramInt += 1;
      }
      g = ((afx)localObject2);
      j.add(localObject1);
    }
    label955:
    return null;
    d = ((aib)localObject2);
    paramInt = 2;
    j.add(localObject1);
    label977:
    int k = paramInt;
    paramInt = k;
    if (paramagf.c())
    {
      paramInt = k;
      if (paramagf.i())
      {
        paramInt = k;
        if (i != 0)
        {
          paramInt = k;
          if (!paramBoolean)
          {
            localObject1 = new aid();
            b = ((int)paramLong);
            localObject2 = new agd();
            paramLocation = paramagf.b(paramLocation.getSpeed());
            if ((paramLocation != null) && (paramLocation.size() >= 6))
            {
              a = ((Integer)paramLocation.get(3)).intValue();
              b = ((Integer)paramLocation.get(4)).intValue();
              c = ((short)((Integer)paramLocation.get(0)).intValue());
              d = ((short)((Integer)paramLocation.get(1)).intValue());
              e = ((Integer)paramLocation.get(2)).intValue();
              f = paramagf.l();
            }
            e = ((agd)localObject2);
            paramInt = k + 1;
            j.add(localObject1);
          }
        }
      }
    }
    int i = paramInt;
    if (paramagf.d())
    {
      i = paramInt;
      if (j != 0)
      {
        i = paramInt;
        if (!paramBoolean)
        {
          paramLocation = new aid();
          localObject1 = new afz();
          paramagf = paramagf.u();
          b = ((int)(((Long)paramagf.get(0)).longValue() / 1000L));
          a = ((byte)(paramagf.size() - 1));
          i = 1;
          while (i < paramagf.size())
          {
            localObject3 = (List)paramagf.get(i);
            if ((localObject3 != null) && (((List)localObject3).size() >= 3))
            {
              localObject2 = new aga();
              localObject4 = ((String)((List)localObject3).get(0)).getBytes();
              System.arraycopy(localObject4, 0, a, 0, a(localObject4.length, a.length));
              b = ((short)((Integer)((List)localObject3).get(1)).intValue());
              localObject3 = ((String)((List)localObject3).get(2)).getBytes();
              System.arraycopy(localObject3, 0, c, 0, a(localObject3.length, c.length));
              b.add(localObject2);
            }
            i += 1;
          }
          f = ((afz)localObject1);
          i = paramInt + 1;
          j.add(paramLocation);
        }
      }
    }
    i = ((short)i);
    if ((i < 2) && (!paramBoolean)) {
      return null;
    }
    return localafv;
  }
  
  protected static File a(Context paramContext)
  {
    paramContext = "/Android/data/" + paramContext.getPackageName() + "/files/";
    return new File(Environment.getExternalStorageDirectory().getPath() + paramContext);
  }
  
  public static Object a(Object paramObject, String paramString, Object... paramVarArgs)
  {
    Class localClass = paramObject.getClass();
    Class[] arrayOfClass = new Class[paramVarArgs.length];
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      arrayOfClass[i] = paramVarArgs[i].getClass();
      if (arrayOfClass[i] == Integer.class) {
        arrayOfClass[i] = Integer.TYPE;
      }
      i += 1;
    }
    paramString = localClass.getDeclaredMethod(paramString, arrayOfClass);
    if (!paramString.isAccessible()) {
      paramString.setAccessible(true);
    }
    return paramString.invoke(paramObject, paramVarArgs);
  }
  
  private static ArrayList a(File[] paramArrayOfFile)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayOfFile.length)
    {
      if ((paramArrayOfFile[i].isFile()) && (paramArrayOfFile[i].getName().length() == 10) && (TextUtils.isDigitsOnly(paramArrayOfFile[i].getName()))) {
        localArrayList.add(paramArrayOfFile[i]);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  protected static byte[] a(BitSet paramBitSet)
  {
    byte[] arrayOfByte = new byte[paramBitSet.size() / 8];
    int i = 0;
    if (i < paramBitSet.size())
    {
      int k = i / 8;
      int m = arrayOfByte[k];
      if (paramBitSet.get(i)) {}
      for (int j = 1;; j = 0)
      {
        arrayOfByte[k] = ((byte)(j << 7 - i % 8 | m));
        i += 1;
        break;
      }
    }
    return arrayOfByte;
  }
  
  protected static byte[] a(byte[] paramArrayOfByte)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localObject1 = localObject2;
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localObject1 = localObject2;
      localGZIPOutputStream.write(paramArrayOfByte);
      localObject1 = localObject2;
      localGZIPOutputStream.finish();
      localObject1 = localObject2;
      localGZIPOutputStream.close();
      localObject1 = localObject2;
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      localObject1 = paramArrayOfByte;
      localByteArrayOutputStream.close();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte) {}
    return (byte[])localObject1;
  }
  
  protected static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      return null;
    }
    int i = new String(paramArrayOfByte).indexOf(0);
    if (i > 0) {
      if (i + 1 <= paramInt) {}
    }
    for (;;)
    {
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramInt);
      arrayOfByte[(paramInt - 1)] = 0;
      return arrayOfByte;
      paramInt = i + 1;
      continue;
      paramInt = 1;
    }
  }
  
  public static int b(Object paramObject, String paramString, Object... paramVarArgs)
  {
    Class localClass = paramObject.getClass();
    Class[] arrayOfClass = new Class[paramVarArgs.length];
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      arrayOfClass[i] = paramVarArgs[i].getClass();
      if (arrayOfClass[i] == Integer.class) {
        arrayOfClass[i] = Integer.TYPE;
      }
      i += 1;
    }
    paramString = localClass.getDeclaredMethod(paramString, arrayOfClass);
    if (!paramString.isAccessible()) {
      paramString.setAccessible(true);
    }
    return ((Integer)paramString.invoke(paramObject, paramVarArgs)).intValue();
  }
  
  protected static BitSet b(byte[] paramArrayOfByte)
  {
    BitSet localBitSet = new BitSet(paramArrayOfByte.length << 3);
    int i = 0;
    int j = 0;
    while (i < paramArrayOfByte.length)
    {
      int k = 7;
      if (k >= 0)
      {
        if ((paramArrayOfByte[i] & 1 << k) >> k == 1) {}
        for (boolean bool = true;; bool = false)
        {
          localBitSet.set(j, bool);
          k -= 1;
          j += 1;
          break;
        }
      }
      i += 1;
    }
    return localBitSet;
  }
  
  private File c(long paramLong)
  {
    boolean bool2 = false;
    Object localObject1;
    if (Process.myUid() == 1000)
    {
      localObject1 = null;
      return (File)localObject1;
    }
    boolean bool1;
    Object localObject2;
    try
    {
      bool1 = "mounted".equals(Environment.getExternalStorageState());
      if ((!c()) || (bool1))
      {
        localObject1 = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (((StatFs)localObject1).getAvailableBlocks() * ((StatFs)localObject1).getBlockSize() <= c / 2) {
          return null;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        bool1 = false;
      }
      localObject2 = a(a).getPath();
      localObject2 = new File((String)localObject2 + File.separator + "carrierdata");
      if ((!((File)localObject2).exists()) || (!((File)localObject2).isDirectory())) {
        ((File)localObject2).mkdirs();
      }
      localObject2 = new File(((File)localObject2).getPath() + File.separator + paramLong);
    }
    for (;;)
    {
      try
      {
        bool1 = ((File)localObject2).createNewFile();
        if (bool1) {
          break;
        }
        return null;
      }
      catch (IOException localIOException)
      {
        bool1 = bool2;
        continue;
      }
      localObject2 = null;
      bool1 = bool2;
    }
  }
  
  protected static boolean c()
  {
    if (Build.VERSION.SDK_INT >= 9) {
      try
      {
        boolean bool = ((Boolean)Environment.class.getMethod("isExternalStorageRemovable", null).invoke(null, null)).booleanValue();
        return bool;
      }
      catch (Exception localException) {}
    }
    return true;
  }
  
  private File d()
  {
    if (Process.myUid() == 1000) {
      return null;
    }
    try
    {
      bool = "mounted".equals(Environment.getExternalStorageState());
      if ((!c()) || (bool))
      {
        Object localObject1 = a(a).getPath();
        localObject1 = new File((String)localObject1 + File.separator + "carrierdata");
        if ((((File)localObject1).exists()) && (((File)localObject1).isDirectory()))
        {
          localObject1 = ((File)localObject1).listFiles();
          if ((localObject1 != null) && (localObject1.length > 0))
          {
            localObject1 = a((File[])localObject1);
            if (((ArrayList)localObject1).size() == 1)
            {
              if (((File)((ArrayList)localObject1).get(0)).length() >= d) {
                break label204;
              }
              localObject1 = (File)((ArrayList)localObject1).get(0);
              return (File)localObject1;
            }
          }
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        boolean bool = false;
        continue;
        Object localObject2;
        if (localException.size() >= 2)
        {
          File localFile1 = (File)localException.get(0);
          File localFile2 = (File)localException.get(1);
          localObject2 = localFile1;
          if (localFile1.getName().compareTo(localFile2.getName()) <= 0) {
            localObject2 = localFile2;
          }
        }
        else
        {
          label204:
          localObject2 = null;
        }
      }
    }
  }
  
  private int e()
  {
    if (Process.myUid() == 1000) {}
    label144:
    do
    {
      for (;;)
      {
        return 0;
        try
        {
          bool = "mounted".equals(Environment.getExternalStorageState());
          if ((!c()) || (bool))
          {
            Object localObject = a(a).getPath();
            localObject = new File((String)localObject + File.separator + "carrierdata");
            if ((((File)localObject).exists()) && (((File)localObject).isDirectory()))
            {
              localObject = ((File)localObject).listFiles();
              if ((localObject != null) && (localObject.length > 0))
              {
                localObject = a((File[])localObject);
                if (((ArrayList)localObject).size() != 1) {
                  break label144;
                }
                if (((File)((ArrayList)localObject).get(0)).length() <= 0L) {
                  return 10;
                }
              }
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            boolean bool = false;
          }
        }
      }
      return 1;
    } while (localException.size() < 2);
    return 2;
  }
  
  private File f()
  {
    if (Process.myUid() == 1000) {
      return null;
    }
    try
    {
      bool = "mounted".equals(Environment.getExternalStorageState());
      if ((!c()) || (bool))
      {
        Object localObject1 = a(a);
        if (localObject1 != null)
        {
          localObject1 = ((File)localObject1).getPath();
          localObject1 = new File((String)localObject1 + File.separator + "carrierdata");
          if ((((File)localObject1).exists()) && (((File)localObject1).isDirectory()))
          {
            localObject1 = ((File)localObject1).listFiles();
            if ((localObject1 != null) && (localObject1.length > 0))
            {
              Object localObject3 = a((File[])localObject1);
              if (((ArrayList)localObject3).size() >= 2)
              {
                localObject1 = (File)((ArrayList)localObject3).get(0);
                localObject3 = (File)((ArrayList)localObject3).get(1);
                if (((File)localObject1).getName().compareTo(((File)localObject3).getName()) > 0)
                {
                  localObject1 = localObject3;
                  return (File)localObject1;
                }
              }
            }
          }
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        boolean bool = false;
        continue;
        continue;
        Object localObject2 = null;
      }
    }
  }
  
  protected int a()
  {
    return b;
  }
  
  protected File a(long paramLong)
  {
    try
    {
      File localFile2 = d();
      File localFile1 = localFile2;
      if (localFile2 == null) {
        localFile1 = c(paramLong);
      }
      return localFile1;
    }
    finally {}
  }
  
  protected void a(int paramInt)
  {
    b = paramInt;
    c = ((b << 3) * 1500 + b + 4);
    if ((b == 256) || (b == 768)) {
      d = (c / 100);
    }
    while (b != 8736) {
      return;
    }
    d = (c - 5000);
  }
  
  protected File b()
  {
    return f();
  }
  
  /* Error */
  protected boolean b(long paramLong)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokespecial 555	agc:e	()I
    //   9: istore_3
    //   10: iload_3
    //   11: ifne +8 -> 19
    //   14: aload_0
    //   15: monitorexit
    //   16: iload 4
    //   18: ireturn
    //   19: iload_3
    //   20: iconst_1
    //   21: if_icmpne +21 -> 42
    //   24: aload_0
    //   25: lload_1
    //   26: invokespecial 550	agc:c	(J)Ljava/io/File;
    //   29: astore 5
    //   31: aload 5
    //   33: ifnull -19 -> 14
    //   36: iconst_1
    //   37: istore 4
    //   39: goto -25 -> 14
    //   42: iload_3
    //   43: iconst_2
    //   44: if_icmpne -30 -> 14
    //   47: iconst_1
    //   48: istore 4
    //   50: goto -36 -> 14
    //   53: astore 5
    //   55: aload_0
    //   56: monitorexit
    //   57: aload 5
    //   59: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	60	0	this	agc
    //   0	60	1	paramLong	long
    //   9	36	3	i	int
    //   1	48	4	bool	boolean
    //   29	3	5	localFile	File
    //   53	5	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   5	10	53	finally
    //   24	31	53	finally
  }
}

/* Location:
 * Qualified Name:     agc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */