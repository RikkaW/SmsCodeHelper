package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Environment;
import com.amap.api.maps2d.MapsInitializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

class r
{
  private Context a;
  private q b = null;
  private String c = "/sdcard/Amap/RMap";
  private final int d = 128;
  
  public r(Context paramContext, boolean paramBoolean, aw paramaw)
  {
    a = paramContext;
    if (paramaw == null) {}
    do
    {
      return;
      if (paramBoolean == true)
      {
        c = paramContext.getFilesDir().getPath();
        return;
      }
      paramBoolean = false;
      if (!l.equals(""))
      {
        paramContext = new File(l);
        boolean bool = paramContext.exists();
        paramBoolean = bool;
        if (!bool) {
          paramBoolean = paramContext.mkdirs();
        }
      }
    } while (paramBoolean);
    c = a(a, c, paramaw);
  }
  
  private int a(int paramInt1, int paramInt2)
  {
    return paramInt1 % 128 * 128 + paramInt2 % 128;
  }
  
  public static String a(Context paramContext, String paramString, aw paramaw)
  {
    if (!Environment.getExternalStorageState().equals("mounted")) {
      return paramContext.getFilesDir().getPath();
    }
    if ((MapsInitializer.sdcardDir == null) || (MapsInitializer.sdcardDir.equals("")))
    {
      paramContext = new File(Environment.getExternalStorageDirectory(), "Amap");
      if (!paramContext.exists()) {
        paramContext.mkdir();
      }
      paramContext = new File(paramContext, a);
      if (!paramContext.exists()) {
        paramContext.mkdir();
      }
      return paramContext.toString() + "/";
    }
    paramContext = new File(MapsInitializer.sdcardDir);
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    paramContext = new File(paramContext, "Amap");
    if (!paramContext.exists()) {
      paramContext.mkdir();
    }
    paramContext = new File(paramContext, a);
    if (!paramContext.exists()) {
      paramContext.mkdir();
    }
    return paramContext.toString() + "/";
  }
  
  private void a(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length != 4)) {
      return;
    }
    int i = paramArrayOfByte[0];
    paramArrayOfByte[0] = paramArrayOfByte[3];
    paramArrayOfByte[3] = i;
    i = paramArrayOfByte[1];
    paramArrayOfByte[1] = paramArrayOfByte[2];
    paramArrayOfByte[2] = i;
  }
  
  private byte[] a(int paramInt)
  {
    return new byte[] { (byte)(paramInt & 0xFF), (byte)((0xFF00 & paramInt) >> 8), (byte)((0xFF0000 & paramInt) >> 16), (byte)((0xFF000000 & paramInt) >> 24) };
  }
  
  private String[] a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    paramInt1 /= 128;
    paramInt2 /= 128;
    int i = paramInt1 / 10;
    int j = paramInt2 / 10;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(c);
    localStringBuilder.append("/");
    localStringBuilder.append(paramInt3);
    localStringBuilder.append("/");
    localStringBuilder.append(i);
    localStringBuilder.append("/");
    localStringBuilder.append(j);
    localStringBuilder.append("/");
    if (!paramBoolean)
    {
      File localFile = new File(localStringBuilder.toString());
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
    }
    localStringBuilder.append(paramInt3);
    localStringBuilder.append("_");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append("_");
    localStringBuilder.append(paramInt2);
    return new String[] { localStringBuilder.toString() + ".idx", localStringBuilder.toString() + ".dat" };
  }
  
  private int b(byte[] paramArrayOfByte)
  {
    return paramArrayOfByte[0] & 0xFF | paramArrayOfByte[1] << 8 & 0xFF00 | paramArrayOfByte[2] << 16 & 0xFF0000 | paramArrayOfByte[3] << 24 & 0xFF000000;
  }
  
  public int a(n.a parama)
  {
    String[] arrayOfString = a(b, c, d, true);
    if ((arrayOfString == null) || (arrayOfString.length != 2) || (arrayOfString[0].equals("")) || (Arrays.equals(arrayOfString, new String[arrayOfString.length]))) {}
    for (;;)
    {
      return -1;
      Object localObject1 = new File(arrayOfString[0]);
      if (!((File)localObject1).exists()) {
        continue;
      }
      int i = a(b, c);
      if (i < 0) {
        continue;
      }
      StringBuilder localStringBuilder;
      byte[] arrayOfByte2;
      try
      {
        localObject1 = new RandomAccessFile((File)localObject1, "r");
        if (localObject1 == null) {
          continue;
        }
        l = i * 4;
      }
      catch (FileNotFoundException localIOException4)
      {
        try
        {
          ((RandomAccessFile)localObject1).seek(l);
          arrayOfByte1 = new byte[4];
        }
        catch (IOException localIOException4)
        {
          try
          {
            ((RandomAccessFile)localObject1).read(arrayOfByte1, 0, 4);
            a(arrayOfByte1);
            i = b(arrayOfByte1);
          }
          catch (IOException localIOException4)
          {
            try
            {
              ((RandomAccessFile)localObject1).close();
              if (i < 0) {
                continue;
              }
              localObject1 = new File(arrayOfString[1]);
              if (!((File)localObject1).exists()) {
                continue;
              }
            }
            catch (IOException localIOException4)
            {
              try
              {
                localObject1 = new RandomAccessFile((File)localObject1, "r");
                if (localObject1 == null) {
                  continue;
                }
                l = i;
              }
              catch (FileNotFoundException localIOException4)
              {
                try
                {
                  long l;
                  ((RandomAccessFile)localObject1).seek(l);
                }
                catch (IOException localIOException4)
                {
                  try
                  {
                    for (;;)
                    {
                      byte[] arrayOfByte1;
                      ((RandomAccessFile)localObject1).read(arrayOfByte1, 0, 4);
                      a(arrayOfByte1);
                      i = b(arrayOfByte1);
                      if ((i > 0) && (i <= 204800)) {
                        break;
                      }
                      try
                      {
                        ((RandomAccessFile)localObject1).close();
                        return -1;
                      }
                      catch (IOException parama)
                      {
                        cy.a(parama, "CachManager", "getTileFromCach");
                        return -1;
                      }
                      localFileNotFoundException1 = localFileNotFoundException1;
                      cy.a(localFileNotFoundException1, "CachManager", "getTileFromCach");
                      Object localObject2 = null;
                      continue;
                      localIOException3 = localIOException3;
                      cy.a(localIOException3, "CachManager", "getTileFromCach");
                      continue;
                      localIOException7 = localIOException7;
                      cy.a(localIOException7, "CachManager", "getTileFromCach");
                      continue;
                      localIOException1 = localIOException1;
                      cy.a(localIOException1, "CachManager", "getTileFromCach");
                      continue;
                      localFileNotFoundException2 = localFileNotFoundException2;
                      cy.a(localFileNotFoundException2, "CachManager", "getTileFromCach");
                      localStringBuilder = null;
                      continue;
                      localIOException4 = localIOException4;
                      cy.a(localIOException4, "CachManager", "getTileFromCach");
                    }
                  }
                  catch (IOException localIOException5)
                  {
                    for (;;)
                    {
                      cy.a(localIOException5, "CachManager", "getTileFromCach");
                    }
                    arrayOfByte2 = new byte[i];
                  }
                }
              }
            }
          }
        }
      }
      try
      {
        localStringBuilder.read(arrayOfByte2, 0, i);
      }
      catch (IOException localIOException6)
      {
        try
        {
          for (;;)
          {
            localStringBuilder.close();
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(b);
            localStringBuilder.append("-");
            localStringBuilder.append(c);
            localStringBuilder.append("-");
            localStringBuilder.append(d);
            if (b == null) {
              break;
            }
            return b.a(arrayOfByte2, null, true, null, localStringBuilder.toString());
            localIOException6 = localIOException6;
            cy.a(localIOException6, "CachManager", "getTileFromCach");
          }
        }
        catch (IOException localIOException2)
        {
          for (;;)
          {
            cy.a(localIOException2, "CachManager", "getTileFromCach");
          }
        }
      }
    }
  }
  
  public void a(q paramq)
  {
    b = paramq;
  }
  
  public boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramArrayOfByte == null) {}
    label367:
    label586:
    for (boolean bool1 = false;; bool1 = false) {
      for (;;)
      {
        return bool1;
        int i;
        String[] arrayOfString;
        Object localObject1;
        boolean bool2;
        try
        {
          i = paramArrayOfByte.length;
          if (i <= 0)
          {
            bool1 = false;
            continue;
          }
          arrayOfString = a(paramInt1, paramInt2, paramInt3, false);
          if ((arrayOfString == null) || (arrayOfString.length != 2) || (arrayOfString[0].equals("")) || (Arrays.equals(arrayOfString, new String[arrayOfString.length]))) {
            break label586;
          }
          localObject1 = new File(arrayOfString[1]);
          bool1 = ((File)localObject1).exists();
          if (!bool1)
          {
            bool1 = false;
            try
            {
              bool2 = ((File)localObject1).createNewFile();
              bool1 = bool2;
            }
            catch (IOException localIOException6)
            {
              for (;;)
              {
                cy.a(localIOException6, "CachManager", "addDataToCach");
              }
            }
            if (!bool1)
            {
              bool1 = false;
              continue;
            }
          }
        }
        finally {}
        long l1;
        long l2;
        try
        {
          localObject1 = new RandomAccessFile((File)localObject1, "rws");
          if (localObject1 == null) {
            bool1 = false;
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          Object localObject2;
          for (;;)
          {
            cy.a(localFileNotFoundException, "CachManager", "addDataToCach");
            localObject2 = null;
          }
          byte[] arrayOfByte3 = a(i);
          a(arrayOfByte3);
          try
          {
            l1 = ((RandomAccessFile)localObject2).length();
          }
          catch (IOException paramArrayOfByte)
          {
            try
            {
              ((RandomAccessFile)localObject2).seek(l1);
            }
            catch (IOException paramArrayOfByte)
            {
              try
              {
                ((RandomAccessFile)localObject2).write(arrayOfByte3);
              }
              catch (IOException paramArrayOfByte)
              {
                try
                {
                  ((RandomAccessFile)localObject2).write(paramArrayOfByte);
                }
                catch (IOException paramArrayOfByte)
                {
                  try
                  {
                    for (;;)
                    {
                      ((RandomAccessFile)localObject2).close();
                      paramArrayOfByte = new File(arrayOfString[0]);
                      bool1 = paramArrayOfByte.exists();
                      if (bool1) {
                        break label367;
                      }
                      bool1 = false;
                      try
                      {
                        bool2 = paramArrayOfByte.createNewFile();
                        bool1 = bool2;
                      }
                      catch (IOException localIOException1)
                      {
                        for (;;)
                        {
                          cy.a(localIOException1, "CachManager", "addDataToCach");
                        }
                      }
                      if (bool1) {
                        break label367;
                      }
                      bool1 = false;
                      break;
                      localIOException8 = localIOException8;
                      cy.a(localIOException8, "CachManager", "addDataToCach");
                      l1 = 0L;
                      continue;
                      localIOException9 = localIOException9;
                      cy.a(localIOException9, "CachManager", "addDataToCach");
                      continue;
                      localIOException7 = localIOException7;
                      cy.a(localIOException7, "CachManager", "addDataToCach");
                      continue;
                      paramArrayOfByte = paramArrayOfByte;
                      cy.a(paramArrayOfByte, "CachManager", "addDataToCach");
                    }
                  }
                  catch (IOException paramArrayOfByte)
                  {
                    for (;;)
                    {
                      cy.a(paramArrayOfByte, "CachManager", "addDataToCach");
                    }
                    try
                    {
                      paramArrayOfByte = new RandomAccessFile(paramArrayOfByte, "rws");
                      if (paramArrayOfByte == null) {
                        bool1 = false;
                      }
                    }
                    catch (FileNotFoundException paramArrayOfByte)
                    {
                      for (;;)
                      {
                        cy.a(paramArrayOfByte, "CachManager", "addDataToCach");
                        paramArrayOfByte = null;
                      }
                      l2 = 0L;
                      try
                      {
                        long l3 = paramArrayOfByte.length();
                        l2 = l3;
                        if (l2 == 0L)
                        {
                          arrayOfByte1 = new byte[65536];
                          Arrays.fill(arrayOfByte1, (byte)-1);
                        }
                      }
                      catch (IOException localIOException3)
                      {
                        try
                        {
                          byte[] arrayOfByte1;
                          paramArrayOfByte.write(arrayOfByte1);
                          paramInt1 = a(paramInt1, paramInt2);
                          if (paramInt1 >= 0) {}
                        }
                        catch (IOException localIOException3)
                        {
                          try
                          {
                            for (;;)
                            {
                              paramArrayOfByte.close();
                              bool1 = false;
                              break;
                              localIOException2 = localIOException2;
                              cy.a(localIOException2, "CachManager", "addDataToCach");
                            }
                            localIOException3 = localIOException3;
                            cy.a(localIOException3, "CachManager", "addDataToCach");
                          }
                          catch (IOException paramArrayOfByte)
                          {
                            for (;;)
                            {
                              cy.a(paramArrayOfByte, "CachManager", "addDataToCach");
                            }
                          }
                          l2 = paramInt1 * 4;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        try
        {
          paramArrayOfByte.seek(l2);
          paramInt1 = (int)l1;
          arrayOfByte2 = a(paramInt1);
          a(arrayOfByte2);
        }
        catch (IOException localIOException5)
        {
          try
          {
            byte[] arrayOfByte2;
            paramArrayOfByte.write(arrayOfByte2);
          }
          catch (IOException localIOException5)
          {
            try
            {
              for (;;)
              {
                paramArrayOfByte.close();
                bool1 = true;
                break;
                localIOException4 = localIOException4;
                cy.a(localIOException4, "CachManager", "addDataToCach");
                continue;
                localIOException5 = localIOException5;
                cy.a(localIOException5, "CachManager", "addDataToCach");
              }
            }
            catch (IOException paramArrayOfByte)
            {
              for (;;)
              {
                cy.a(paramArrayOfByte, "CachManager", "addDataToCach");
              }
            }
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.r
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */