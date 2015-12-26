import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class abp
{
  public static int a(Context paramContext, Uri paramUri)
  {
    try
    {
      paramContext = paramContext.getContentResolver().openInputStream(paramUri);
      if (paramContext == null)
      {
        Log.w("ThumbnailUtils", "getOrientFromInputStream ipStream  null ");
        return 0;
      }
    }
    catch (FileNotFoundException paramContext)
    {
      Log.e("ThumbnailUtils", paramContext.getMessage(), paramContext);
      return 0;
    }
    int i = a(paramContext);
    try
    {
      paramContext.close();
      return i;
    }
    catch (IOException paramContext)
    {
      Log.e("ThumbnailUtils", "getOrientFromInputStream " + paramContext.toString());
    }
    return i;
  }
  
  public static int a(InputStream paramInputStream)
  {
    if (paramInputStream == null) {}
    label217:
    label303:
    label323:
    label364:
    label399:
    label484:
    label525:
    label563:
    label565:
    label589:
    label618:
    label632:
    for (;;)
    {
      return 0;
      byte[] arrayOfByte = new byte[6];
      int i;
      int n;
      int j;
      try
      {
        if ((paramInputStream.read(arrayOfByte, 0, 4) != 4) || (arrayOfByte[0] != -1) || (arrayOfByte[1] != -40) || (arrayOfByte[2] != -1) || (arrayOfByte[3] != -31) || (2 != paramInputStream.read(arrayOfByte, 0, 2))) {
          continue;
        }
        i = ((arrayOfByte[0] & 0xFF) << 8) + (arrayOfByte[1] & 0xFF);
        if (i < 8) {
          continue;
        }
        n = i - 8;
        if ((6 != paramInputStream.read(arrayOfByte, 0, 6)) || (arrayOfByte[0] != 69) || (arrayOfByte[1] != 120) || (arrayOfByte[2] != 105) || (arrayOfByte[3] != 102) || (arrayOfByte[4] != 0) || (arrayOfByte[5] != 0)) {
          continue;
        }
        arrayOfByte = new byte[n];
        if ((n < 12) || (n != paramInputStream.read(arrayOfByte, 0, n))) {
          continue;
        }
        if ((arrayOfByte[0] != 73) || (arrayOfByte[1] != 73)) {
          break label484;
        }
        j = 0;
      }
      catch (IOException paramInputStream)
      {
        Log.e("ThumbnailUtils", "getOrientFromInputStream " + paramInputStream.toString());
        return 0;
      }
      catch (Exception paramInputStream)
      {
        Log.e("ThumbnailUtils", "getOrientFromInputStream " + paramInputStream.toString());
        return 0;
      }
      if (arrayOfByte[(i + 9)] == 0)
      {
        i = arrayOfByte[(i + 8)];
        return i & 0xFF;
        int k;
        int m;
        if (j != 0)
        {
          if ((arrayOfByte[2] != 0) || (arrayOfByte[3] != 42)) {
            continue;
          }
          if (j == 0) {
            break label525;
          }
          if ((arrayOfByte[4] != 0) || (arrayOfByte[5] != 0)) {
            continue;
          }
          k = ((arrayOfByte[6] & 0xFF) << 8) + (arrayOfByte[7] & 0xFF);
          if (k > n - 2) {
            break label563;
          }
          if (j == 0) {
            break label565;
          }
          i = ((arrayOfByte[k] & 0xFF) << 8) + (arrayOfByte[(k + 1)] & 0xFF);
          if (i == 0) {
            break label589;
          }
          m = k + 2;
          k = i;
          i = m;
        }
        for (;;)
        {
          if (i > n - 12) {
            break label632;
          }
          if (j != 0) {}
          for (m = ((arrayOfByte[i] & 0xFF) << 8) + (arrayOfByte[(i + 1)] & 0xFF);; m = ((arrayOfByte[(i + 1)] & 0xFF) << 8) + (arrayOfByte[i] & 0xFF))
          {
            if (m != 274) {
              break label618;
            }
            if (j == 0) {
              break label217;
            }
            if (arrayOfByte[(i + 8)] != 0) {
              break;
            }
            return arrayOfByte[(i + 9)] & 0xFF;
            if ((arrayOfByte[0] != 77) || (arrayOfByte[1] != 77)) {
              break;
            }
            j = 1;
            break label303;
            if (arrayOfByte[3] != 0) {
              break;
            }
            if (arrayOfByte[2] == 42) {
              break label323;
            }
            return 0;
            if ((arrayOfByte[7] != 0) || (arrayOfByte[6] != 0)) {
              break;
            }
            k = ((arrayOfByte[5] & 0xFF) << 8) + (arrayOfByte[4] & 0xFF);
            break label364;
            break;
            i = ((arrayOfByte[(k + 1)] & 0xFF) << 8) + (arrayOfByte[k] & 0xFF);
            break label399;
            break;
          }
          k -= 1;
          if (k == 0) {
            break;
          }
          i += 12;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     abp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */