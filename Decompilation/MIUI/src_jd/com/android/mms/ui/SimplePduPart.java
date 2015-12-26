package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.mms.pdu.CharacterSets;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduPart;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import miui.graphics.drawable.GifAnimationDrawable;
import miui.util.IOUtils;

public class SimplePduPart
  extends PduPart
{
  private int mAttachmentType = -1;
  private Context mContext;
  
  SimplePduPart(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private Drawable extractImageFromData(int paramInt1, int paramInt2)
  {
    label331:
    for (;;)
    {
      try
      {
        Object localObject4 = getDataUri();
        Object localObject1 = new GifAnimationDrawable();
        boolean bool = ((GifAnimationDrawable)localObject1).load(mContext, (Uri)localObject4);
        if (bool) {
          return (Drawable)localObject1;
        }
        localObject3 = null;
        localObject1 = null;
        InputStream localInputStream;
        int i;
        BitmapFactory.Options localOptions;
        IOUtils.closeQuietly(localOutOfMemoryError);
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        try
        {
          localInputStream = mContext.getContentResolver().openInputStream((Uri)localObject4);
          i = 1;
          localObject1 = localInputStream;
          if (localInputStream == null) {
            break label331;
          }
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          localOptions = new BitmapFactory.Options();
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          inJustDecodeBounds = true;
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          BitmapFactory.decodeStream(localInputStream, null, localOptions);
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          if (outHeight > paramInt2)
          {
            localObject1 = localInputStream;
            localObject3 = localInputStream;
            i = outHeight / paramInt2;
          }
          paramInt2 = i;
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          if (outWidth > paramInt1)
          {
            localObject1 = localInputStream;
            localObject3 = localInputStream;
            paramInt2 = Math.max(i, outWidth / paramInt1);
          }
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          IOUtils.closeQuietly(localInputStream);
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          localInputStream = mContext.getContentResolver().openInputStream((Uri)localObject4);
          localObject1 = localInputStream;
          if (localInputStream == null) {
            break label331;
          }
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          localObject4 = new BitmapFactory.Options();
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          inSampleSize = paramInt2;
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          localObject4 = BitmapFactory.decodeStream(localInputStream, null, (BitmapFactory.Options)localObject4);
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          localObject4 = new BitmapDrawable(mContext.getResources(), (Bitmap)localObject4);
          IOUtils.closeQuietly(localInputStream);
          return (Drawable)localObject4;
        }
        catch (IOException localIOException)
        {
          localObject3 = localOutOfMemoryError;
          Log.e("SimplePduPart", "Cannot extract image from data", localIOException);
          IOUtils.closeQuietly(localOutOfMemoryError);
          continue;
        }
        finally
        {
          Object localObject3;
          IOUtils.closeQuietly((InputStream)localObject3);
        }
        localOutOfMemoryError = localOutOfMemoryError;
        MessageUtils.writeHprofDataToFile();
        Log.e("SimplePduPart", "Not enough memory.", localOutOfMemoryError);
        return null;
      }
    }
  }
  
  private String extractTextFromData()
  {
    if (loadData())
    {
      byte[] arrayOfByte = getData();
      if (arrayOfByte != null) {
        try
        {
          if (getCharset() == 0) {
            return new String(arrayOfByte);
          }
          String str = new String(arrayOfByte, CharacterSets.getMimeName(getCharset()));
          return str;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          return new String(arrayOfByte);
        }
      }
    }
    return "";
  }
  
  public int getAttachmentType()
  {
    return mAttachmentType;
  }
  
  public Drawable getImageNoCache(int paramInt1, int paramInt2)
  {
    return extractImageFromData(paramInt1, paramInt2);
  }
  
  public Intent getIntent()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(1);
    localIntent.setDataAndType(getDataUri(), MiuiPduPersister.toIsoString(getContentType()));
    String str = getPduPartName();
    if (!TextUtils.isEmpty(str)) {
      localIntent.putExtra("display_name", str);
    }
    return localIntent;
  }
  
  public String getPduPartName()
  {
    Object localObject2 = null;
    Object localObject1 = getContentLocation();
    if ((localObject1 != null) && (localObject1.length > 0)) {
      localObject1 = new String((byte[])localObject1);
    }
    byte[] arrayOfByte;
    do
    {
      do
      {
        return (String)localObject1;
        localObject1 = getContentId();
        if ((localObject1 != null) && (localObject1.length > 0)) {
          return new String((byte[])localObject1);
        }
        localObject1 = getFilename();
        if ((localObject1 != null) && (localObject1.length > 0)) {
          return new String((byte[])localObject1);
        }
        arrayOfByte = getName();
        localObject1 = localObject2;
      } while (arrayOfByte == null);
      localObject1 = localObject2;
    } while (arrayOfByte.length <= 0);
    return new String(arrayOfByte);
  }
  
  public String getText()
  {
    return extractTextFromData();
  }
  
  /* Error */
  boolean loadData()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 119	com/android/mms/ui/SimplePduPart:getData	()[B
    //   4: ifnull +5 -> 9
    //   7: iconst_1
    //   8: ireturn
    //   9: new 201	java/io/ByteArrayOutputStream
    //   12: dup
    //   13: invokespecial 202	java/io/ByteArrayOutputStream:<init>	()V
    //   16: astore 5
    //   18: aconst_null
    //   19: astore 4
    //   21: aconst_null
    //   22: astore_3
    //   23: aload_0
    //   24: getfield 17	com/android/mms/ui/SimplePduPart:mContext	Landroid/content/Context;
    //   27: invokevirtual 41	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   30: aload_0
    //   31: invokevirtual 28	com/android/mms/ui/SimplePduPart:getDataUri	()Landroid/net/Uri;
    //   34: invokevirtual 47	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   37: astore_2
    //   38: aload_2
    //   39: ifnonnull +13 -> 52
    //   42: aload_2
    //   43: ifnull +7 -> 50
    //   46: aload_2
    //   47: invokevirtual 207	java/io/InputStream:close	()V
    //   50: iconst_0
    //   51: ireturn
    //   52: aload_2
    //   53: astore_3
    //   54: aload_2
    //   55: astore 4
    //   57: sipush 256
    //   60: newarray <illegal type>
    //   62: astore 6
    //   64: aload_2
    //   65: astore_3
    //   66: aload_2
    //   67: astore 4
    //   69: aload_2
    //   70: aload 6
    //   72: invokevirtual 211	java/io/InputStream:read	([B)I
    //   75: istore_1
    //   76: iload_1
    //   77: iflt +32 -> 109
    //   80: aload_2
    //   81: astore_3
    //   82: aload_2
    //   83: astore 4
    //   85: aload 5
    //   87: aload 6
    //   89: iconst_0
    //   90: iload_1
    //   91: invokevirtual 215	java/io/ByteArrayOutputStream:write	([BII)V
    //   94: aload_2
    //   95: astore_3
    //   96: aload_2
    //   97: astore 4
    //   99: aload_2
    //   100: aload 6
    //   102: invokevirtual 211	java/io/InputStream:read	([B)I
    //   105: istore_1
    //   106: goto -30 -> 76
    //   109: aload_2
    //   110: ifnull +7 -> 117
    //   113: aload_2
    //   114: invokevirtual 207	java/io/InputStream:close	()V
    //   117: aload_0
    //   118: aload 5
    //   120: invokevirtual 218	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   123: invokevirtual 221	com/android/mms/ui/SimplePduPart:setData	([B)V
    //   126: iconst_1
    //   127: ireturn
    //   128: astore_2
    //   129: aload_3
    //   130: ifnull +7 -> 137
    //   133: aload_3
    //   134: invokevirtual 207	java/io/InputStream:close	()V
    //   137: iconst_0
    //   138: ireturn
    //   139: astore_2
    //   140: aload 4
    //   142: ifnull +8 -> 150
    //   145: aload 4
    //   147: invokevirtual 207	java/io/InputStream:close	()V
    //   150: aload_2
    //   151: athrow
    //   152: astore_2
    //   153: goto -103 -> 50
    //   156: astore_2
    //   157: goto -40 -> 117
    //   160: astore_2
    //   161: goto -24 -> 137
    //   164: astore_3
    //   165: goto -15 -> 150
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	this	SimplePduPart
    //   75	31	1	i	int
    //   37	77	2	localInputStream	InputStream
    //   128	1	2	localIOException1	IOException
    //   139	12	2	localObject1	Object
    //   152	1	2	localIOException2	IOException
    //   156	1	2	localIOException3	IOException
    //   160	1	2	localIOException4	IOException
    //   22	112	3	localObject2	Object
    //   164	1	3	localIOException5	IOException
    //   19	127	4	localObject3	Object
    //   16	103	5	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   62	39	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   23	38	128	java/io/IOException
    //   57	64	128	java/io/IOException
    //   69	76	128	java/io/IOException
    //   85	94	128	java/io/IOException
    //   99	106	128	java/io/IOException
    //   23	38	139	finally
    //   57	64	139	finally
    //   69	76	139	finally
    //   85	94	139	finally
    //   99	106	139	finally
    //   46	50	152	java/io/IOException
    //   113	117	156	java/io/IOException
    //   133	137	160	java/io/IOException
    //   145	150	164	java/io/IOException
  }
  
  public void setAttachmentType(int paramInt)
  {
    mAttachmentType = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimplePduPart
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */