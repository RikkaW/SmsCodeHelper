package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.android.mms.LogTag;
import com.android.mms.model.ImageModel;
import com.google.android.mms.pdu.PduPart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import miui.util.IOUtils;

public class UriImage
{
  private String mContentType;
  private final Context mContext;
  private int mHeight;
  private String mPath;
  private int mRotation;
  private String mSrc;
  private final Uri mUri;
  private int mWidth;
  
  public UriImage(Context paramContext, Uri paramUri)
  {
    if ((paramContext == null) || (paramUri == null)) {
      throw new IllegalArgumentException();
    }
    if (paramUri.getScheme().equals("content")) {
      initFromContentUri(paramContext, paramUri);
    }
    for (;;)
    {
      mSrc = mPath.substring(mPath.lastIndexOf('/') + 1);
      if ((mSrc.startsWith(".")) && (mSrc.length() > 1)) {
        mSrc = mSrc.substring(1);
      }
      mSrc = mSrc.replace(' ', '_');
      mContext = paramContext;
      mUri = paramUri;
      decodeBoundsInfo();
      return;
      if (paramUri.getScheme().equals("file")) {
        initFromFile(paramContext, paramUri);
      }
    }
  }
  
  private void decodeBoundsInfo()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        localInputStream = mContext.getContentResolver().openInputStream(mUri);
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
        if (mRotation % 180 == 0)
        {
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          mWidth = outWidth;
          localObject1 = localInputStream;
          localObject3 = localInputStream;
          mHeight = outHeight;
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        InputStream localInputStream;
        BitmapFactory.Options localOptions;
        localObject3 = localObject1;
        Log.e("Mms/image", "IOException caught while opening stream", localFileNotFoundException);
        if (localObject1 == null) {
          continue;
        }
        try
        {
          ((InputStream)localObject1).close();
          return;
        }
        catch (IOException localIOException1)
        {
          Log.e("Mms/image", "IOException caught while closing stream", localIOException1);
          return;
        }
      }
      finally
      {
        if (localObject3 == null) {
          break label185;
        }
      }
      try
      {
        localInputStream.close();
        return;
      }
      catch (IOException localIOException2)
      {
        Log.e("Mms/image", "IOException caught while closing stream", localIOException2);
        return;
      }
      localObject1 = localInputStream;
      localObject3 = localInputStream;
      mHeight = outWidth;
      localObject1 = localInputStream;
      localObject3 = localInputStream;
      mWidth = outHeight;
    }
    try
    {
      ((InputStream)localObject3).close();
      label185:
      throw ((Throwable)localObject2);
    }
    catch (IOException localIOException3)
    {
      for (;;)
      {
        Log.e("Mms/image", "IOException caught while closing stream", localIOException3);
      }
    }
  }
  
  public static int exifOrientationToDegrees(int paramInt)
  {
    switch (paramInt)
    {
    case 4: 
    case 5: 
    case 7: 
    default: 
      return 0;
    case 6: 
      return 90;
    case 3: 
      return 180;
    }
    return 270;
  }
  
  /* Error */
  private byte[] getGifImageData(int paramInt1, int paramInt2, int paramInt3)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aload 8
    //   5: astore 4
    //   7: aload_0
    //   8: getfield 116	com/android/mms/ui/UriImage:mWidth	I
    //   11: iload_1
    //   12: if_icmpgt +245 -> 257
    //   15: aload 8
    //   17: astore 4
    //   19: aload_0
    //   20: getfield 121	com/android/mms/ui/UriImage:mHeight	I
    //   23: iload_2
    //   24: if_icmpgt +233 -> 257
    //   27: aconst_null
    //   28: astore 6
    //   30: aconst_null
    //   31: astore 4
    //   33: aconst_null
    //   34: astore 5
    //   36: aload_0
    //   37: getfield 69	com/android/mms/ui/UriImage:mContext	Landroid/content/Context;
    //   40: invokevirtual 90	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   43: aload_0
    //   44: getfield 71	com/android/mms/ui/UriImage:mUri	Landroid/net/Uri;
    //   47: invokevirtual 96	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   50: astore 7
    //   52: aload 7
    //   54: astore 5
    //   56: aload 7
    //   58: astore 6
    //   60: aload 7
    //   62: astore 4
    //   64: iconst_3
    //   65: newarray <illegal type>
    //   67: astore 10
    //   69: aload 7
    //   71: astore 5
    //   73: aload 7
    //   75: astore 6
    //   77: aload 7
    //   79: astore 4
    //   81: new 143	org/apache/http/util/ByteArrayBuffer
    //   84: dup
    //   85: aload 10
    //   87: arraylength
    //   88: sipush 4096
    //   91: iadd
    //   92: invokespecial 146	org/apache/http/util/ByteArrayBuffer:<init>	(I)V
    //   95: astore 9
    //   97: aload 7
    //   99: astore 5
    //   101: aload 7
    //   103: astore 6
    //   105: aload 7
    //   107: astore 4
    //   109: aload 7
    //   111: aload 10
    //   113: invokevirtual 150	java/io/InputStream:read	([B)I
    //   116: istore_1
    //   117: iload_1
    //   118: iconst_3
    //   119: if_icmpne +241 -> 360
    //   122: aload 10
    //   124: iconst_0
    //   125: baload
    //   126: bipush 71
    //   128: if_icmpne +232 -> 360
    //   131: aload 10
    //   133: iconst_1
    //   134: baload
    //   135: bipush 73
    //   137: if_icmpne +223 -> 360
    //   140: aload 10
    //   142: iconst_2
    //   143: baload
    //   144: bipush 70
    //   146: if_icmpne +214 -> 360
    //   149: aload 7
    //   151: astore 5
    //   153: aload 7
    //   155: astore 6
    //   157: aload 7
    //   159: astore 4
    //   161: aload 9
    //   163: aload 10
    //   165: iconst_0
    //   166: iload_1
    //   167: invokevirtual 154	org/apache/http/util/ByteArrayBuffer:append	([BII)V
    //   170: aload 7
    //   172: astore 5
    //   174: aload 7
    //   176: astore 6
    //   178: aload 7
    //   180: astore 4
    //   182: sipush 4096
    //   185: newarray <illegal type>
    //   187: astore 10
    //   189: aload 7
    //   191: astore 5
    //   193: aload 7
    //   195: astore 6
    //   197: aload 7
    //   199: astore 4
    //   201: aload 7
    //   203: aload 10
    //   205: invokevirtual 150	java/io/InputStream:read	([B)I
    //   208: istore_1
    //   209: iload_1
    //   210: iconst_m1
    //   211: if_icmpeq +104 -> 315
    //   214: aload 7
    //   216: astore 5
    //   218: aload 7
    //   220: astore 6
    //   222: aload 7
    //   224: astore 4
    //   226: aload 9
    //   228: invokevirtual 155	org/apache/http/util/ByteArrayBuffer:length	()I
    //   231: istore_2
    //   232: iload_2
    //   233: iload_1
    //   234: iadd
    //   235: iload_3
    //   236: if_icmple +24 -> 260
    //   239: aload 8
    //   241: astore 4
    //   243: aload 7
    //   245: ifnull +12 -> 257
    //   248: aload 7
    //   250: invokevirtual 126	java/io/InputStream:close	()V
    //   253: aload 8
    //   255: astore 4
    //   257: aload 4
    //   259: areturn
    //   260: aload 7
    //   262: astore 5
    //   264: aload 7
    //   266: astore 6
    //   268: aload 7
    //   270: astore 4
    //   272: aload 9
    //   274: aload 10
    //   276: iconst_0
    //   277: iload_1
    //   278: invokevirtual 154	org/apache/http/util/ByteArrayBuffer:append	([BII)V
    //   281: goto -92 -> 189
    //   284: astore 6
    //   286: aload 5
    //   288: astore 4
    //   290: aload 6
    //   292: invokevirtual 158	java/io/FileNotFoundException:printStackTrace	()V
    //   295: aload 8
    //   297: astore 4
    //   299: aload 5
    //   301: ifnull -44 -> 257
    //   304: aload 5
    //   306: invokevirtual 126	java/io/InputStream:close	()V
    //   309: aconst_null
    //   310: areturn
    //   311: astore 4
    //   313: aconst_null
    //   314: areturn
    //   315: aload 7
    //   317: astore 5
    //   319: aload 7
    //   321: astore 6
    //   323: aload 7
    //   325: astore 4
    //   327: aload 9
    //   329: invokevirtual 162	org/apache/http/util/ByteArrayBuffer:toByteArray	()[B
    //   332: astore 9
    //   334: aload 9
    //   336: astore 5
    //   338: aload 5
    //   340: astore 4
    //   342: aload 7
    //   344: ifnull -87 -> 257
    //   347: aload 7
    //   349: invokevirtual 126	java/io/InputStream:close	()V
    //   352: aload 5
    //   354: areturn
    //   355: astore 4
    //   357: aload 5
    //   359: areturn
    //   360: aload 8
    //   362: astore 4
    //   364: aload 7
    //   366: ifnull -109 -> 257
    //   369: aload 7
    //   371: invokevirtual 126	java/io/InputStream:close	()V
    //   374: aconst_null
    //   375: areturn
    //   376: astore 4
    //   378: aconst_null
    //   379: areturn
    //   380: astore 5
    //   382: aload 6
    //   384: astore 4
    //   386: aload 5
    //   388: invokevirtual 163	java/io/IOException:printStackTrace	()V
    //   391: aload 8
    //   393: astore 4
    //   395: aload 6
    //   397: ifnull -140 -> 257
    //   400: aload 6
    //   402: invokevirtual 126	java/io/InputStream:close	()V
    //   405: aconst_null
    //   406: areturn
    //   407: astore 4
    //   409: aconst_null
    //   410: areturn
    //   411: astore 5
    //   413: aload 4
    //   415: ifnull +8 -> 423
    //   418: aload 4
    //   420: invokevirtual 126	java/io/InputStream:close	()V
    //   423: aload 5
    //   425: athrow
    //   426: astore 4
    //   428: aconst_null
    //   429: areturn
    //   430: astore 4
    //   432: goto -9 -> 423
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	435	0	this	UriImage
    //   0	435	1	paramInt1	int
    //   0	435	2	paramInt2	int
    //   0	435	3	paramInt3	int
    //   5	293	4	localObject1	Object
    //   311	1	4	localIOException1	IOException
    //   325	16	4	localObject2	Object
    //   355	1	4	localIOException2	IOException
    //   362	1	4	localObject3	Object
    //   376	1	4	localIOException3	IOException
    //   384	10	4	localObject4	Object
    //   407	12	4	localIOException4	IOException
    //   426	1	4	localIOException5	IOException
    //   430	1	4	localIOException6	IOException
    //   34	324	5	localObject5	Object
    //   380	7	5	localIOException7	IOException
    //   411	13	5	localObject6	Object
    //   28	239	6	localObject7	Object
    //   284	7	6	localFileNotFoundException	FileNotFoundException
    //   321	80	6	localObject8	Object
    //   50	320	7	localInputStream	InputStream
    //   1	391	8	localObject9	Object
    //   95	240	9	localObject10	Object
    //   67	208	10	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   36	52	284	java/io/FileNotFoundException
    //   64	69	284	java/io/FileNotFoundException
    //   81	97	284	java/io/FileNotFoundException
    //   109	117	284	java/io/FileNotFoundException
    //   161	170	284	java/io/FileNotFoundException
    //   182	189	284	java/io/FileNotFoundException
    //   201	209	284	java/io/FileNotFoundException
    //   226	232	284	java/io/FileNotFoundException
    //   272	281	284	java/io/FileNotFoundException
    //   327	334	284	java/io/FileNotFoundException
    //   304	309	311	java/io/IOException
    //   347	352	355	java/io/IOException
    //   369	374	376	java/io/IOException
    //   36	52	380	java/io/IOException
    //   64	69	380	java/io/IOException
    //   81	97	380	java/io/IOException
    //   109	117	380	java/io/IOException
    //   161	170	380	java/io/IOException
    //   182	189	380	java/io/IOException
    //   201	209	380	java/io/IOException
    //   226	232	380	java/io/IOException
    //   272	281	380	java/io/IOException
    //   327	334	380	java/io/IOException
    //   400	405	407	java/io/IOException
    //   36	52	411	finally
    //   64	69	411	finally
    //   81	97	411	finally
    //   109	117	411	finally
    //   161	170	411	finally
    //   182	189	411	finally
    //   201	209	411	finally
    //   226	232	411	finally
    //   272	281	411	finally
    //   290	295	411	finally
    //   327	334	411	finally
    //   386	391	411	finally
    //   248	253	426	java/io/IOException
    //   418	423	430	java/io/IOException
  }
  
  private void initFromContentUri(Context paramContext, Uri paramUri)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Cursor localCursor = SqliteWrapper.query(paramContext, localContentResolver, paramUri, null, null, null, null);
    if (localCursor == null) {
      throw new IllegalArgumentException("Query on " + paramUri + " returns null result.");
    }
    int i;
    try
    {
      if ((localCursor.getCount() != 1) || (!localCursor.moveToFirst())) {
        throw new IllegalArgumentException("Query on " + paramUri + " returns 0 or multiple rows.");
      }
    }
    finally
    {
      localCursor.close();
      throw paramContext;
      if (ImageModel.isMmsUri(paramUri))
      {
        paramUri = localCursor.getString(localCursor.getColumnIndexOrThrow("fn"));
        paramContext = paramUri;
        if (TextUtils.isEmpty(paramUri)) {
          paramContext = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
        }
        mContentType = localCursor.getString(localCursor.getColumnIndexOrThrow("ct"));
        mPath = paramContext;
        localCursor.close();
        return;
      }
      paramContext = paramUri.getPath();
    }
  }
  
  private void initFromFile(Context paramContext, Uri paramUri)
  {
    mPath = paramUri.getPath();
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    String str = MimeTypeMap.getFileExtensionFromUrl(mPath);
    paramContext = str;
    if (TextUtils.isEmpty(str))
    {
      int i = mPath.lastIndexOf('.');
      paramContext = str;
      if (i >= 0) {
        paramContext = mPath.substring(i + 1);
      }
    }
    if (paramContext == null) {}
    for (paramContext = null;; paramContext = localMimeTypeMap.getMimeTypeFromExtension(paramContext.toLowerCase()))
    {
      mContentType = paramContext;
      try
      {
        mRotation = exifOrientationToDegrees(new ExifInterface(paramUri.getPath()).getAttributeInt("Orientation", 1));
        return;
      }
      catch (Exception paramContext) {}
    }
  }
  
  public Bitmap getBitmap(int paramInt)
  {
    int j = 1;
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    Object localObject2 = null;
    int i = paramInt;
    paramInt = j;
    for (;;)
    {
      Object localObject1 = null;
      try
      {
        InputStream localInputStream = mContext.getContentResolver().openInputStream(mUri);
        localObject1 = localInputStream;
        if (localObject1 == null) {
          return null;
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        for (;;)
        {
          LogTag.error("Cannot open bitmap %s", new Object[] { mUri.toString() });
        }
        inSampleSize = i;
        try
        {
          Bitmap localBitmap = BitmapFactory.decodeStream((InputStream)localObject1, null, localOptions);
          localObject2 = localBitmap;
          IOUtils.closeQuietly((InputStream)localObject1);
          localObject1 = localObject2;
          return (Bitmap)localObject1;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          for (;;)
          {
            Log.w("Mms/image", "getResizedImageData: img too large to decode (OutOfMemoryError), may try with larger sampleSize. Curr sampleSize=" + i);
            i *= 2;
            j = paramInt + 1;
            IOUtils.closeQuietly((InputStream)localObject1);
            localObject1 = localObject2;
            if (0 == 0)
            {
              paramInt = j;
              if (j < 8) {
                break;
              }
              localObject1 = localObject2;
            }
          }
        }
        finally
        {
          IOUtils.closeQuietly((InputStream)localObject1);
        }
      }
    }
  }
  
  public String getContentType()
  {
    return mContentType;
  }
  
  public int getHeight()
  {
    return mHeight;
  }
  
  public PduPart getResizedImageAsPart(int paramInt1, int paramInt2, int paramInt3)
  {
    PduPart localPduPart = new PduPart();
    byte[] arrayOfByte = getGifImageData(paramInt1, paramInt2, paramInt3);
    if (arrayOfByte != null)
    {
      localPduPart.setData(arrayOfByte);
      localPduPart.setContentType("image/gif".getBytes());
      return localPduPart;
    }
    arrayOfByte = getResizedImageData(paramInt1, paramInt2, paramInt3);
    if (arrayOfByte == null) {
      return null;
    }
    localPduPart.setData(arrayOfByte);
    localPduPart.setContentType("image/jpeg".getBytes());
    return localPduPart;
  }
  
  /* Error */
  public byte[] getResizedImageData(int paramInt1, int paramInt2, int paramInt3)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 116	com/android/mms/ui/UriImage:mWidth	I
    //   4: istore 10
    //   6: aload_0
    //   7: getfield 121	com/android/mms/ui/UriImage:mHeight	I
    //   10: istore 11
    //   12: fconst_1
    //   13: fstore 4
    //   15: iload 10
    //   17: i2f
    //   18: fload 4
    //   20: fmul
    //   21: iload_1
    //   22: i2f
    //   23: fcmpl
    //   24: ifgt +15 -> 39
    //   27: iload 11
    //   29: i2f
    //   30: fload 4
    //   32: fmul
    //   33: iload_2
    //   34: i2f
    //   35: fcmpl
    //   36: ifle +14 -> 50
    //   39: fload 4
    //   41: ldc_w 333
    //   44: fmul
    //   45: fstore 4
    //   47: goto -32 -> 15
    //   50: aconst_null
    //   51: astore 16
    //   53: aconst_null
    //   54: astore 17
    //   56: aconst_null
    //   57: astore 13
    //   59: aconst_null
    //   60: astore 12
    //   62: aconst_null
    //   63: astore 22
    //   65: aconst_null
    //   66: astore 23
    //   68: aconst_null
    //   69: astore 21
    //   71: iconst_1
    //   72: istore 7
    //   74: iconst_1
    //   75: istore 8
    //   77: aload 21
    //   79: astore 18
    //   81: aload 22
    //   83: astore 19
    //   85: aload 23
    //   87: astore 14
    //   89: new 98	android/graphics/BitmapFactory$Options
    //   92: dup
    //   93: invokespecial 99	android/graphics/BitmapFactory$Options:<init>	()V
    //   96: astore 24
    //   98: bipush 95
    //   100: istore 9
    //   102: aconst_null
    //   103: astore 20
    //   105: aload 12
    //   107: astore 13
    //   109: aload 13
    //   111: astore 16
    //   113: aload 21
    //   115: astore 18
    //   117: aload 13
    //   119: astore 17
    //   121: aload 22
    //   123: astore 19
    //   125: aload 23
    //   127: astore 14
    //   129: aload_0
    //   130: getfield 69	com/android/mms/ui/UriImage:mContext	Landroid/content/Context;
    //   133: invokevirtual 90	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   136: aload_0
    //   137: getfield 71	com/android/mms/ui/UriImage:mUri	Landroid/net/Uri;
    //   140: invokevirtual 96	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   143: astore 15
    //   145: aload 15
    //   147: astore 16
    //   149: aload 21
    //   151: astore 18
    //   153: aload 15
    //   155: astore 17
    //   157: aload 22
    //   159: astore 19
    //   161: aload 15
    //   163: astore 13
    //   165: aload 23
    //   167: astore 14
    //   169: aload 24
    //   171: iload 8
    //   173: putfield 291	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   176: aload 15
    //   178: aconst_null
    //   179: aload 24
    //   181: invokestatic 109	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   184: astore 12
    //   186: aload 12
    //   188: astore 20
    //   190: aload 20
    //   192: ifnonnull +226 -> 418
    //   195: aconst_null
    //   196: astore 20
    //   198: aload 15
    //   200: astore 12
    //   202: aload 15
    //   204: ifnull +35 -> 239
    //   207: aload 15
    //   209: astore 16
    //   211: aload 21
    //   213: astore 18
    //   215: aload 15
    //   217: astore 17
    //   219: aload 22
    //   221: astore 19
    //   223: aload 15
    //   225: astore 13
    //   227: aload 23
    //   229: astore 14
    //   231: aload 15
    //   233: invokevirtual 126	java/io/InputStream:close	()V
    //   236: aconst_null
    //   237: astore 12
    //   239: aload 12
    //   241: ifnull +8 -> 249
    //   244: aload 12
    //   246: invokevirtual 126	java/io/InputStream:close	()V
    //   249: aload 20
    //   251: astore 12
    //   253: iconst_0
    //   254: ifeq +11 -> 265
    //   257: new 335	java/lang/NullPointerException
    //   260: dup
    //   261: invokespecial 336	java/lang/NullPointerException:<init>	()V
    //   264: athrow
    //   265: aload 12
    //   267: areturn
    //   268: astore 12
    //   270: aload 15
    //   272: astore 16
    //   274: aload 21
    //   276: astore 18
    //   278: aload 15
    //   280: astore 17
    //   282: aload 22
    //   284: astore 19
    //   286: aload 15
    //   288: astore 13
    //   290: aload 23
    //   292: astore 14
    //   294: ldc -128
    //   296: aload 12
    //   298: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   301: aload 12
    //   303: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   306: pop
    //   307: goto -71 -> 236
    //   310: astore 15
    //   312: aload 16
    //   314: astore 12
    //   316: aload 12
    //   318: astore 13
    //   320: aload 18
    //   322: astore 14
    //   324: ldc -128
    //   326: aload 15
    //   328: invokevirtual 340	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   331: aload 15
    //   333: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   336: pop
    //   337: aconst_null
    //   338: astore 13
    //   340: aload 12
    //   342: ifnull +8 -> 350
    //   345: aload 12
    //   347: invokevirtual 126	java/io/InputStream:close	()V
    //   350: aload 13
    //   352: astore 12
    //   354: aload 18
    //   356: ifnull -91 -> 265
    //   359: aload 18
    //   361: invokevirtual 343	java/io/ByteArrayOutputStream:close	()V
    //   364: aconst_null
    //   365: areturn
    //   366: astore 12
    //   368: ldc -128
    //   370: aload 12
    //   372: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   375: aload 12
    //   377: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   380: pop
    //   381: aconst_null
    //   382: areturn
    //   383: astore 12
    //   385: ldc -128
    //   387: aload 12
    //   389: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   392: aload 12
    //   394: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   397: pop
    //   398: goto -149 -> 249
    //   401: astore 12
    //   403: ldc -128
    //   405: aload 12
    //   407: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   410: aload 12
    //   412: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   415: pop
    //   416: aconst_null
    //   417: areturn
    //   418: iload 7
    //   420: istore 5
    //   422: aload 20
    //   424: astore 14
    //   426: aload 15
    //   428: astore 12
    //   430: iload 8
    //   432: istore 6
    //   434: aload 15
    //   436: ifnull +47 -> 483
    //   439: aload 15
    //   441: astore 16
    //   443: aload 21
    //   445: astore 18
    //   447: aload 15
    //   449: astore 17
    //   451: aload 22
    //   453: astore 19
    //   455: aload 15
    //   457: astore 13
    //   459: aload 23
    //   461: astore 14
    //   463: aload 15
    //   465: invokevirtual 126	java/io/InputStream:close	()V
    //   468: aconst_null
    //   469: astore 12
    //   471: iload 8
    //   473: istore 6
    //   475: aload 20
    //   477: astore 14
    //   479: iload 7
    //   481: istore 5
    //   483: aload 14
    //   485: ifnonnull +26 -> 511
    //   488: iload 5
    //   490: istore 7
    //   492: aload 14
    //   494: astore 20
    //   496: aload 12
    //   498: astore 13
    //   500: iload 6
    //   502: istore 8
    //   504: iload 5
    //   506: bipush 8
    //   508: if_icmplt -399 -> 109
    //   511: aload 14
    //   513: ifnonnull +491 -> 1004
    //   516: aload 12
    //   518: astore 16
    //   520: aload 21
    //   522: astore 18
    //   524: aload 12
    //   526: astore 17
    //   528: aload 22
    //   530: astore 19
    //   532: aload 12
    //   534: astore 13
    //   536: aload 23
    //   538: astore 14
    //   540: ldc -128
    //   542: ldc_w 345
    //   545: invokestatic 348	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   548: pop
    //   549: aconst_null
    //   550: astore 13
    //   552: aload 12
    //   554: ifnull +8 -> 562
    //   557: aload 12
    //   559: invokevirtual 126	java/io/InputStream:close	()V
    //   562: aload 13
    //   564: astore 12
    //   566: iconst_0
    //   567: ifeq -302 -> 265
    //   570: new 335	java/lang/NullPointerException
    //   573: dup
    //   574: invokespecial 336	java/lang/NullPointerException:<init>	()V
    //   577: athrow
    //   578: astore 12
    //   580: ldc -128
    //   582: aload 12
    //   584: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   587: aload 12
    //   589: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   592: pop
    //   593: aconst_null
    //   594: areturn
    //   595: astore 12
    //   597: aload 15
    //   599: astore 16
    //   601: aload 21
    //   603: astore 18
    //   605: aload 15
    //   607: astore 17
    //   609: aload 22
    //   611: astore 19
    //   613: aload 15
    //   615: astore 13
    //   617: aload 23
    //   619: astore 14
    //   621: ldc -128
    //   623: aload 12
    //   625: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   628: aload 12
    //   630: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   633: pop
    //   634: goto -166 -> 468
    //   637: astore 12
    //   639: aload 17
    //   641: astore 13
    //   643: aload 19
    //   645: astore 14
    //   647: ldc -128
    //   649: aload 12
    //   651: invokevirtual 349	java/lang/OutOfMemoryError:getMessage	()Ljava/lang/String;
    //   654: aload 12
    //   656: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   659: pop
    //   660: aconst_null
    //   661: astore 12
    //   663: aload 17
    //   665: ifnull +8 -> 673
    //   668: aload 17
    //   670: invokevirtual 126	java/io/InputStream:close	()V
    //   673: aload 19
    //   675: ifnull -410 -> 265
    //   678: aload 19
    //   680: invokevirtual 343	java/io/ByteArrayOutputStream:close	()V
    //   683: aconst_null
    //   684: areturn
    //   685: astore 12
    //   687: ldc -128
    //   689: aload 12
    //   691: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   694: aload 12
    //   696: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   699: pop
    //   700: aconst_null
    //   701: areturn
    //   702: astore 12
    //   704: ldc -128
    //   706: new 171	java/lang/StringBuilder
    //   709: dup
    //   710: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   713: ldc_w 351
    //   716: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   719: iload 8
    //   721: invokevirtual 302	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   724: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   727: invokestatic 306	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   730: pop
    //   731: iload 8
    //   733: iconst_2
    //   734: imul
    //   735: istore 8
    //   737: iload 7
    //   739: iconst_1
    //   740: iadd
    //   741: istore 7
    //   743: iload 7
    //   745: istore 5
    //   747: aload 20
    //   749: astore 14
    //   751: aload 15
    //   753: astore 12
    //   755: iload 8
    //   757: istore 6
    //   759: aload 15
    //   761: ifnull -278 -> 483
    //   764: aload 15
    //   766: astore 16
    //   768: aload 21
    //   770: astore 18
    //   772: aload 15
    //   774: astore 17
    //   776: aload 22
    //   778: astore 19
    //   780: aload 15
    //   782: astore 13
    //   784: aload 23
    //   786: astore 14
    //   788: aload 15
    //   790: invokevirtual 126	java/io/InputStream:close	()V
    //   793: aconst_null
    //   794: astore 12
    //   796: iload 7
    //   798: istore 5
    //   800: aload 20
    //   802: astore 14
    //   804: iload 8
    //   806: istore 6
    //   808: goto -325 -> 483
    //   811: astore 12
    //   813: aload 15
    //   815: astore 16
    //   817: aload 21
    //   819: astore 18
    //   821: aload 15
    //   823: astore 17
    //   825: aload 22
    //   827: astore 19
    //   829: aload 15
    //   831: astore 13
    //   833: aload 23
    //   835: astore 14
    //   837: ldc -128
    //   839: aload 12
    //   841: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   844: aload 12
    //   846: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   849: pop
    //   850: goto -57 -> 793
    //   853: astore 12
    //   855: aload 13
    //   857: ifnull +8 -> 865
    //   860: aload 13
    //   862: invokevirtual 126	java/io/InputStream:close	()V
    //   865: aload 14
    //   867: ifnull +8 -> 875
    //   870: aload 14
    //   872: invokevirtual 343	java/io/ByteArrayOutputStream:close	()V
    //   875: aload 12
    //   877: athrow
    //   878: astore 12
    //   880: aload 15
    //   882: astore 13
    //   884: aload 15
    //   886: ifnull +35 -> 921
    //   889: aload 15
    //   891: astore 16
    //   893: aload 21
    //   895: astore 18
    //   897: aload 15
    //   899: astore 17
    //   901: aload 22
    //   903: astore 19
    //   905: aload 15
    //   907: astore 13
    //   909: aload 23
    //   911: astore 14
    //   913: aload 15
    //   915: invokevirtual 126	java/io/InputStream:close	()V
    //   918: aconst_null
    //   919: astore 13
    //   921: aload 13
    //   923: astore 16
    //   925: aload 21
    //   927: astore 18
    //   929: aload 13
    //   931: astore 17
    //   933: aload 22
    //   935: astore 19
    //   937: aload 23
    //   939: astore 14
    //   941: aload 12
    //   943: athrow
    //   944: astore 20
    //   946: aload 15
    //   948: astore 16
    //   950: aload 21
    //   952: astore 18
    //   954: aload 15
    //   956: astore 17
    //   958: aload 22
    //   960: astore 19
    //   962: aload 15
    //   964: astore 13
    //   966: aload 23
    //   968: astore 14
    //   970: ldc -128
    //   972: aload 20
    //   974: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   977: aload 20
    //   979: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   982: pop
    //   983: goto -65 -> 918
    //   986: astore 12
    //   988: ldc -128
    //   990: aload 12
    //   992: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   995: aload 12
    //   997: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1000: pop
    //   1001: goto -439 -> 562
    //   1004: iconst_1
    //   1005: istore 7
    //   1007: aconst_null
    //   1008: astore 13
    //   1010: iload 9
    //   1012: istore 5
    //   1014: aload 14
    //   1016: astore 15
    //   1018: aload 15
    //   1020: astore 14
    //   1022: aload 13
    //   1024: astore 16
    //   1026: iload 5
    //   1028: istore 6
    //   1030: aload 13
    //   1032: astore 18
    //   1034: aload 13
    //   1036: astore 20
    //   1038: aload 24
    //   1040: getfield 114	android/graphics/BitmapFactory$Options:outWidth	I
    //   1043: iload_1
    //   1044: if_icmpgt +74 -> 1118
    //   1047: aload 15
    //   1049: astore 14
    //   1051: aload 13
    //   1053: astore 16
    //   1055: iload 5
    //   1057: istore 6
    //   1059: aload 13
    //   1061: astore 18
    //   1063: aload 13
    //   1065: astore 20
    //   1067: aload 24
    //   1069: getfield 119	android/graphics/BitmapFactory$Options:outHeight	I
    //   1072: iload_2
    //   1073: if_icmpgt +45 -> 1118
    //   1076: aload 15
    //   1078: astore 17
    //   1080: aload 13
    //   1082: ifnull +350 -> 1432
    //   1085: aload 15
    //   1087: astore 17
    //   1089: aload 15
    //   1091: astore 14
    //   1093: aload 13
    //   1095: astore 16
    //   1097: iload 5
    //   1099: istore 6
    //   1101: aload 13
    //   1103: astore 18
    //   1105: aload 13
    //   1107: astore 20
    //   1109: aload 13
    //   1111: invokevirtual 354	java/io/ByteArrayOutputStream:size	()I
    //   1114: iload_3
    //   1115: if_icmple +317 -> 1432
    //   1118: aload 15
    //   1120: astore 14
    //   1122: aload 13
    //   1124: astore 16
    //   1126: iload 5
    //   1128: istore 6
    //   1130: aload 13
    //   1132: astore 18
    //   1134: aload 13
    //   1136: astore 20
    //   1138: iconst_1
    //   1139: iload 10
    //   1141: i2f
    //   1142: fload 4
    //   1144: fmul
    //   1145: f2i
    //   1146: invokestatic 360	java/lang/Math:max	(II)I
    //   1149: istore 9
    //   1151: aload 15
    //   1153: astore 14
    //   1155: aload 13
    //   1157: astore 16
    //   1159: iload 5
    //   1161: istore 6
    //   1163: aload 13
    //   1165: astore 18
    //   1167: aload 13
    //   1169: astore 20
    //   1171: iconst_1
    //   1172: iload 11
    //   1174: i2f
    //   1175: fload 4
    //   1177: fmul
    //   1178: f2i
    //   1179: invokestatic 360	java/lang/Math:max	(II)I
    //   1182: istore 8
    //   1184: aload 15
    //   1186: astore 14
    //   1188: aload 13
    //   1190: astore 16
    //   1192: iload 5
    //   1194: istore 6
    //   1196: aload 13
    //   1198: astore 18
    //   1200: aload 13
    //   1202: astore 20
    //   1204: ldc_w 362
    //   1207: iconst_2
    //   1208: invokestatic 366	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1211: ifeq +61 -> 1272
    //   1214: aload 15
    //   1216: astore 14
    //   1218: aload 13
    //   1220: astore 16
    //   1222: iload 5
    //   1224: istore 6
    //   1226: aload 13
    //   1228: astore 18
    //   1230: aload 13
    //   1232: astore 20
    //   1234: ldc -128
    //   1236: new 171	java/lang/StringBuilder
    //   1239: dup
    //   1240: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1243: ldc_w 368
    //   1246: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1249: iload 9
    //   1251: invokevirtual 302	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1254: ldc_w 370
    //   1257: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1260: iload 8
    //   1262: invokevirtual 302	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1265: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1268: invokestatic 348	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1271: pop
    //   1272: aload 15
    //   1274: astore 14
    //   1276: aload 13
    //   1278: astore 16
    //   1280: iload 5
    //   1282: istore 6
    //   1284: aload 13
    //   1286: astore 18
    //   1288: aload 13
    //   1290: astore 20
    //   1292: aload 15
    //   1294: iload 9
    //   1296: iload 8
    //   1298: iconst_0
    //   1299: invokestatic 376	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   1302: astore 19
    //   1304: aload 15
    //   1306: aload 19
    //   1308: if_acmpeq +28 -> 1336
    //   1311: aload 19
    //   1313: astore 14
    //   1315: aload 13
    //   1317: astore 16
    //   1319: iload 5
    //   1321: istore 6
    //   1323: aload 13
    //   1325: astore 18
    //   1327: aload 13
    //   1329: astore 20
    //   1331: aload 15
    //   1333: invokevirtual 379	android/graphics/Bitmap:recycle	()V
    //   1336: aload 19
    //   1338: astore 17
    //   1340: aload 19
    //   1342: ifnonnull +90 -> 1432
    //   1345: aload 19
    //   1347: astore 14
    //   1349: aload 13
    //   1351: astore 16
    //   1353: iload 5
    //   1355: istore 6
    //   1357: aload 13
    //   1359: astore 18
    //   1361: aload 13
    //   1363: astore 20
    //   1365: ldc -128
    //   1367: ldc_w 381
    //   1370: invokestatic 348	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1373: pop
    //   1374: aload 12
    //   1376: ifnull +8 -> 1384
    //   1379: aload 12
    //   1381: invokevirtual 126	java/io/InputStream:close	()V
    //   1384: aload 13
    //   1386: ifnull +8 -> 1394
    //   1389: aload 13
    //   1391: invokevirtual 343	java/io/ByteArrayOutputStream:close	()V
    //   1394: aconst_null
    //   1395: areturn
    //   1396: astore 12
    //   1398: ldc -128
    //   1400: aload 12
    //   1402: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   1405: aload 12
    //   1407: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1410: pop
    //   1411: goto -27 -> 1384
    //   1414: astore 12
    //   1416: ldc -128
    //   1418: aload 12
    //   1420: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   1423: aload 12
    //   1425: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1428: pop
    //   1429: goto -35 -> 1394
    //   1432: aload 13
    //   1434: ifnull +28 -> 1462
    //   1437: aload 17
    //   1439: astore 14
    //   1441: aload 13
    //   1443: astore 16
    //   1445: iload 5
    //   1447: istore 6
    //   1449: aload 13
    //   1451: astore 18
    //   1453: aload 13
    //   1455: astore 20
    //   1457: aload 13
    //   1459: invokevirtual 343	java/io/ByteArrayOutputStream:close	()V
    //   1462: aload 17
    //   1464: astore 14
    //   1466: aload 13
    //   1468: astore 16
    //   1470: iload 5
    //   1472: istore 6
    //   1474: aload 13
    //   1476: astore 18
    //   1478: aload 13
    //   1480: astore 20
    //   1482: new 342	java/io/ByteArrayOutputStream
    //   1485: dup
    //   1486: invokespecial 382	java/io/ByteArrayOutputStream:<init>	()V
    //   1489: astore 19
    //   1491: aload 12
    //   1493: astore 16
    //   1495: aload 19
    //   1497: astore 18
    //   1499: aload 12
    //   1501: astore 13
    //   1503: aload 19
    //   1505: astore 14
    //   1507: iload 5
    //   1509: istore 6
    //   1511: aload 17
    //   1513: getstatic 388	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   1516: iload 5
    //   1518: aload 19
    //   1520: invokevirtual 392	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   1523: pop
    //   1524: aload 12
    //   1526: astore 16
    //   1528: aload 19
    //   1530: astore 18
    //   1532: aload 12
    //   1534: astore 13
    //   1536: aload 19
    //   1538: astore 14
    //   1540: iload 5
    //   1542: istore 6
    //   1544: aload 19
    //   1546: invokevirtual 354	java/io/ByteArrayOutputStream:size	()I
    //   1549: istore 8
    //   1551: aload 17
    //   1553: astore 20
    //   1555: aload 19
    //   1557: astore 15
    //   1559: iload 5
    //   1561: istore 6
    //   1563: iload 8
    //   1565: iload_3
    //   1566: if_icmple +224 -> 1790
    //   1569: aload 12
    //   1571: astore 16
    //   1573: aload 19
    //   1575: astore 18
    //   1577: aload 12
    //   1579: astore 13
    //   1581: aload 19
    //   1583: astore 14
    //   1585: iload 5
    //   1587: istore 6
    //   1589: iload 5
    //   1591: iload_3
    //   1592: imul
    //   1593: iload 8
    //   1595: idiv
    //   1596: istore 8
    //   1598: iload 8
    //   1600: istore 5
    //   1602: iload 8
    //   1604: bipush 50
    //   1606: if_icmpge +7 -> 1613
    //   1609: bipush 50
    //   1611: istore 5
    //   1613: aload 12
    //   1615: astore 16
    //   1617: aload 19
    //   1619: astore 18
    //   1621: aload 12
    //   1623: astore 13
    //   1625: aload 19
    //   1627: astore 14
    //   1629: iload 5
    //   1631: istore 6
    //   1633: ldc_w 362
    //   1636: iconst_2
    //   1637: invokestatic 366	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1640: ifeq +50 -> 1690
    //   1643: aload 12
    //   1645: astore 16
    //   1647: aload 19
    //   1649: astore 18
    //   1651: aload 12
    //   1653: astore 13
    //   1655: aload 19
    //   1657: astore 14
    //   1659: iload 5
    //   1661: istore 6
    //   1663: ldc -128
    //   1665: new 171	java/lang/StringBuilder
    //   1668: dup
    //   1669: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1672: ldc_w 394
    //   1675: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1678: iload 5
    //   1680: invokevirtual 302	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1683: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1686: invokestatic 348	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1689: pop
    //   1690: aload 19
    //   1692: ifnull +28 -> 1720
    //   1695: aload 12
    //   1697: astore 16
    //   1699: aload 19
    //   1701: astore 18
    //   1703: aload 12
    //   1705: astore 13
    //   1707: aload 19
    //   1709: astore 14
    //   1711: iload 5
    //   1713: istore 6
    //   1715: aload 19
    //   1717: invokevirtual 343	java/io/ByteArrayOutputStream:close	()V
    //   1720: aload 12
    //   1722: astore 16
    //   1724: aload 19
    //   1726: astore 18
    //   1728: aload 12
    //   1730: astore 13
    //   1732: aload 19
    //   1734: astore 14
    //   1736: iload 5
    //   1738: istore 6
    //   1740: new 342	java/io/ByteArrayOutputStream
    //   1743: dup
    //   1744: invokespecial 382	java/io/ByteArrayOutputStream:<init>	()V
    //   1747: astore 15
    //   1749: aload 17
    //   1751: astore 14
    //   1753: aload 15
    //   1755: astore 16
    //   1757: iload 5
    //   1759: istore 6
    //   1761: aload 15
    //   1763: astore 18
    //   1765: aload 15
    //   1767: astore 20
    //   1769: aload 17
    //   1771: getstatic 388	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   1774: iload 5
    //   1776: aload 15
    //   1778: invokevirtual 392	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   1781: pop
    //   1782: iload 5
    //   1784: istore 6
    //   1786: aload 17
    //   1788: astore 20
    //   1790: aload 12
    //   1792: astore 16
    //   1794: aload 15
    //   1796: astore 18
    //   1798: aload 12
    //   1800: astore 17
    //   1802: aload 15
    //   1804: astore 19
    //   1806: aload 12
    //   1808: astore 13
    //   1810: aload 15
    //   1812: astore 14
    //   1814: ldc_w 362
    //   1817: iconst_2
    //   1818: invokestatic 366	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1821: ifeq +153 -> 1974
    //   1824: aload 12
    //   1826: astore 16
    //   1828: aload 15
    //   1830: astore 18
    //   1832: aload 12
    //   1834: astore 17
    //   1836: aload 15
    //   1838: astore 19
    //   1840: aload 12
    //   1842: astore 13
    //   1844: aload 15
    //   1846: astore 14
    //   1848: new 171	java/lang/StringBuilder
    //   1851: dup
    //   1852: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1855: ldc_w 396
    //   1858: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1861: iload 7
    //   1863: invokevirtual 302	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1866: ldc_w 398
    //   1869: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1872: astore 21
    //   1874: aload 15
    //   1876: ifnonnull +388 -> 2264
    //   1879: iconst_0
    //   1880: istore 5
    //   1882: aload 12
    //   1884: astore 16
    //   1886: aload 15
    //   1888: astore 18
    //   1890: aload 12
    //   1892: astore 17
    //   1894: aload 15
    //   1896: astore 19
    //   1898: aload 12
    //   1900: astore 13
    //   1902: aload 15
    //   1904: astore 14
    //   1906: ldc -128
    //   1908: aload 21
    //   1910: iload 5
    //   1912: invokevirtual 302	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1915: ldc_w 400
    //   1918: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1921: iload 10
    //   1923: i2f
    //   1924: fload 4
    //   1926: fmul
    //   1927: invokevirtual 403	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   1930: ldc_w 405
    //   1933: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1936: iload 11
    //   1938: i2f
    //   1939: fload 4
    //   1941: fmul
    //   1942: invokevirtual 403	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   1945: ldc_w 407
    //   1948: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1951: fload 4
    //   1953: invokevirtual 403	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   1956: ldc_w 409
    //   1959: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1962: iload 6
    //   1964: invokevirtual 302	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1967: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1970: invokestatic 348	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1973: pop
    //   1974: fload 4
    //   1976: ldc_w 333
    //   1979: fmul
    //   1980: fstore 4
    //   1982: iload 7
    //   1984: iconst_1
    //   1985: iadd
    //   1986: istore 7
    //   1988: aload 15
    //   1990: ifnull +477 -> 2467
    //   1993: aload 12
    //   1995: astore 16
    //   1997: aload 15
    //   1999: astore 18
    //   2001: aload 12
    //   2003: astore 17
    //   2005: aload 15
    //   2007: astore 19
    //   2009: aload 12
    //   2011: astore 13
    //   2013: aload 15
    //   2015: astore 14
    //   2017: aload 15
    //   2019: invokevirtual 354	java/io/ByteArrayOutputStream:size	()I
    //   2022: iload_3
    //   2023: if_icmple +462 -> 2485
    //   2026: goto +441 -> 2467
    //   2029: aload 12
    //   2031: astore 16
    //   2033: aload 15
    //   2035: astore 18
    //   2037: aload 12
    //   2039: astore 17
    //   2041: aload 15
    //   2043: astore 19
    //   2045: aload 12
    //   2047: astore 13
    //   2049: aload 15
    //   2051: astore 14
    //   2053: aload 20
    //   2055: invokevirtual 379	android/graphics/Bitmap:recycle	()V
    //   2058: iload 5
    //   2060: ifeq +238 -> 2298
    //   2063: aconst_null
    //   2064: astore 13
    //   2066: aload 12
    //   2068: ifnull +8 -> 2076
    //   2071: aload 12
    //   2073: invokevirtual 126	java/io/InputStream:close	()V
    //   2076: aload 13
    //   2078: astore 12
    //   2080: aload 15
    //   2082: ifnull -1817 -> 265
    //   2085: aload 15
    //   2087: invokevirtual 343	java/io/ByteArrayOutputStream:close	()V
    //   2090: aload 13
    //   2092: areturn
    //   2093: astore 12
    //   2095: ldc -128
    //   2097: aload 12
    //   2099: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2102: aload 12
    //   2104: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2107: pop
    //   2108: aload 13
    //   2110: areturn
    //   2111: astore 15
    //   2113: aload 17
    //   2115: astore 14
    //   2117: aload 13
    //   2119: astore 16
    //   2121: iload 5
    //   2123: istore 6
    //   2125: aload 13
    //   2127: astore 18
    //   2129: aload 13
    //   2131: astore 20
    //   2133: ldc -128
    //   2135: aload 15
    //   2137: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2140: aload 15
    //   2142: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2145: pop
    //   2146: goto -684 -> 1462
    //   2149: astore 13
    //   2151: aload 16
    //   2153: astore 15
    //   2155: aload 14
    //   2157: astore 20
    //   2159: aload 12
    //   2161: astore 16
    //   2163: aload 15
    //   2165: astore 18
    //   2167: aload 12
    //   2169: astore 17
    //   2171: aload 15
    //   2173: astore 19
    //   2175: aload 12
    //   2177: astore 13
    //   2179: aload 15
    //   2181: astore 14
    //   2183: ldc -128
    //   2185: new 171	java/lang/StringBuilder
    //   2188: dup
    //   2189: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   2192: ldc_w 411
    //   2195: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2198: fload 4
    //   2200: invokevirtual 403	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   2203: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2206: invokestatic 306	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   2209: pop
    //   2210: goto -420 -> 1790
    //   2213: astore 15
    //   2215: aload 12
    //   2217: astore 16
    //   2219: aload 19
    //   2221: astore 18
    //   2223: aload 12
    //   2225: astore 13
    //   2227: aload 19
    //   2229: astore 14
    //   2231: iload 5
    //   2233: istore 6
    //   2235: ldc -128
    //   2237: aload 15
    //   2239: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2242: aload 15
    //   2244: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2247: pop
    //   2248: goto -528 -> 1720
    //   2251: astore 13
    //   2253: aload 17
    //   2255: astore 20
    //   2257: aload 19
    //   2259: astore 15
    //   2261: goto -102 -> 2159
    //   2264: aload 12
    //   2266: astore 16
    //   2268: aload 15
    //   2270: astore 18
    //   2272: aload 12
    //   2274: astore 17
    //   2276: aload 15
    //   2278: astore 19
    //   2280: aload 12
    //   2282: astore 13
    //   2284: aload 15
    //   2286: astore 14
    //   2288: aload 15
    //   2290: invokevirtual 354	java/io/ByteArrayOutputStream:size	()I
    //   2293: istore 5
    //   2295: goto -413 -> 1882
    //   2298: aload 12
    //   2300: astore 16
    //   2302: aload 15
    //   2304: astore 18
    //   2306: aload 12
    //   2308: astore 17
    //   2310: aload 15
    //   2312: astore 19
    //   2314: aload 12
    //   2316: astore 13
    //   2318: aload 15
    //   2320: astore 14
    //   2322: aload 15
    //   2324: invokevirtual 412	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   2327: astore 20
    //   2329: aload 20
    //   2331: astore 13
    //   2333: goto -267 -> 2066
    //   2336: astore 12
    //   2338: ldc -128
    //   2340: aload 12
    //   2342: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2345: aload 12
    //   2347: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2350: pop
    //   2351: goto -275 -> 2076
    //   2354: astore 12
    //   2356: ldc -128
    //   2358: aload 12
    //   2360: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2363: aload 12
    //   2365: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2368: pop
    //   2369: goto -2019 -> 350
    //   2372: astore 13
    //   2374: ldc -128
    //   2376: aload 13
    //   2378: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2381: aload 13
    //   2383: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2386: pop
    //   2387: goto -1714 -> 673
    //   2390: astore 13
    //   2392: ldc -128
    //   2394: aload 13
    //   2396: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2399: aload 13
    //   2401: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2404: pop
    //   2405: goto -1540 -> 865
    //   2408: astore 13
    //   2410: ldc -128
    //   2412: aload 13
    //   2414: invokevirtual 339	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2417: aload 13
    //   2419: invokestatic 136	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2422: pop
    //   2423: goto -1548 -> 875
    //   2426: astore 15
    //   2428: aload 18
    //   2430: astore 14
    //   2432: aload 12
    //   2434: astore 13
    //   2436: aload 15
    //   2438: astore 12
    //   2440: goto -1585 -> 855
    //   2443: astore 15
    //   2445: aload 20
    //   2447: astore 18
    //   2449: goto -2133 -> 316
    //   2452: aload 15
    //   2454: astore 13
    //   2456: aload 20
    //   2458: astore 15
    //   2460: iload 6
    //   2462: istore 5
    //   2464: goto -1446 -> 1018
    //   2467: iconst_1
    //   2468: istore 5
    //   2470: iload 5
    //   2472: ifeq -443 -> 2029
    //   2475: iload 7
    //   2477: bipush 8
    //   2479: if_icmplt -27 -> 2452
    //   2482: goto -453 -> 2029
    //   2485: iconst_0
    //   2486: istore 5
    //   2488: goto -18 -> 2470
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2491	0	this	UriImage
    //   0	2491	1	paramInt1	int
    //   0	2491	2	paramInt2	int
    //   0	2491	3	paramInt3	int
    //   13	2186	4	f	float
    //   420	2067	5	i	int
    //   432	2029	6	j	int
    //   72	2408	7	k	int
    //   75	1532	8	m	int
    //   100	1195	9	n	int
    //   4	1918	10	i1	int
    //   10	1927	11	i2	int
    //   60	206	12	localObject1	Object
    //   268	34	12	localIOException1	IOException
    //   314	39	12	localObject2	Object
    //   366	10	12	localIOException2	IOException
    //   383	10	12	localIOException3	IOException
    //   401	10	12	localIOException4	IOException
    //   428	137	12	localObject3	Object
    //   578	10	12	localIOException5	IOException
    //   595	34	12	localIOException6	IOException
    //   637	18	12	localOutOfMemoryError1	OutOfMemoryError
    //   661	1	12	localObject4	Object
    //   685	10	12	localIOException7	IOException
    //   702	1	12	localOutOfMemoryError2	OutOfMemoryError
    //   753	42	12	localObject5	Object
    //   811	34	12	localIOException8	IOException
    //   853	23	12	localObject6	Object
    //   878	64	12	localObject7	Object
    //   986	394	12	localIOException9	IOException
    //   1396	10	12	localIOException10	IOException
    //   1414	658	12	localIOException11	IOException
    //   2078	1	12	localObject8	Object
    //   2093	222	12	localIOException12	IOException
    //   2336	10	12	localIOException13	IOException
    //   2354	79	12	localIOException14	IOException
    //   2438	1	12	localObject9	Object
    //   57	2073	13	localObject10	Object
    //   2149	1	13	localOutOfMemoryError3	OutOfMemoryError
    //   2177	49	13	localObject11	Object
    //   2251	1	13	localOutOfMemoryError4	OutOfMemoryError
    //   2282	50	13	localObject12	Object
    //   2372	10	13	localIOException15	IOException
    //   2390	10	13	localIOException16	IOException
    //   2408	10	13	localIOException17	IOException
    //   2434	21	13	localObject13	Object
    //   87	2344	14	localObject14	Object
    //   143	144	15	localInputStream	InputStream
    //   310	653	15	localFileNotFoundException1	FileNotFoundException
    //   1016	1070	15	localObject15	Object
    //   2111	30	15	localIOException18	IOException
    //   2153	27	15	localObject16	Object
    //   2213	30	15	localIOException19	IOException
    //   2259	64	15	localObject17	Object
    //   2426	11	15	localObject18	Object
    //   2443	10	15	localFileNotFoundException2	FileNotFoundException
    //   2458	1	15	localObject19	Object
    //   51	2250	16	localObject20	Object
    //   54	2255	17	localObject21	Object
    //   79	2369	18	localObject22	Object
    //   83	2230	19	localObject23	Object
    //   103	698	20	localObject24	Object
    //   944	34	20	localIOException20	IOException
    //   1036	1421	20	localObject25	Object
    //   69	1840	21	localStringBuilder	StringBuilder
    //   63	896	22	localObject26	Object
    //   66	901	23	localObject27	Object
    //   96	972	24	localOptions	BitmapFactory.Options
    // Exception table:
    //   from	to	target	type
    //   231	236	268	java/io/IOException
    //   89	98	310	java/io/FileNotFoundException
    //   129	145	310	java/io/FileNotFoundException
    //   169	176	310	java/io/FileNotFoundException
    //   231	236	310	java/io/FileNotFoundException
    //   294	307	310	java/io/FileNotFoundException
    //   463	468	310	java/io/FileNotFoundException
    //   540	549	310	java/io/FileNotFoundException
    //   621	634	310	java/io/FileNotFoundException
    //   788	793	310	java/io/FileNotFoundException
    //   837	850	310	java/io/FileNotFoundException
    //   913	918	310	java/io/FileNotFoundException
    //   941	944	310	java/io/FileNotFoundException
    //   970	983	310	java/io/FileNotFoundException
    //   1511	1524	310	java/io/FileNotFoundException
    //   1544	1551	310	java/io/FileNotFoundException
    //   1589	1598	310	java/io/FileNotFoundException
    //   1633	1643	310	java/io/FileNotFoundException
    //   1663	1690	310	java/io/FileNotFoundException
    //   1715	1720	310	java/io/FileNotFoundException
    //   1740	1749	310	java/io/FileNotFoundException
    //   1814	1824	310	java/io/FileNotFoundException
    //   1848	1874	310	java/io/FileNotFoundException
    //   1906	1974	310	java/io/FileNotFoundException
    //   2017	2026	310	java/io/FileNotFoundException
    //   2053	2058	310	java/io/FileNotFoundException
    //   2183	2210	310	java/io/FileNotFoundException
    //   2235	2248	310	java/io/FileNotFoundException
    //   2288	2295	310	java/io/FileNotFoundException
    //   2322	2329	310	java/io/FileNotFoundException
    //   359	364	366	java/io/IOException
    //   244	249	383	java/io/IOException
    //   257	265	401	java/io/IOException
    //   570	578	578	java/io/IOException
    //   463	468	595	java/io/IOException
    //   89	98	637	java/lang/OutOfMemoryError
    //   129	145	637	java/lang/OutOfMemoryError
    //   169	176	637	java/lang/OutOfMemoryError
    //   231	236	637	java/lang/OutOfMemoryError
    //   294	307	637	java/lang/OutOfMemoryError
    //   463	468	637	java/lang/OutOfMemoryError
    //   540	549	637	java/lang/OutOfMemoryError
    //   621	634	637	java/lang/OutOfMemoryError
    //   788	793	637	java/lang/OutOfMemoryError
    //   837	850	637	java/lang/OutOfMemoryError
    //   913	918	637	java/lang/OutOfMemoryError
    //   941	944	637	java/lang/OutOfMemoryError
    //   970	983	637	java/lang/OutOfMemoryError
    //   1814	1824	637	java/lang/OutOfMemoryError
    //   1848	1874	637	java/lang/OutOfMemoryError
    //   1906	1974	637	java/lang/OutOfMemoryError
    //   2017	2026	637	java/lang/OutOfMemoryError
    //   2053	2058	637	java/lang/OutOfMemoryError
    //   2183	2210	637	java/lang/OutOfMemoryError
    //   2288	2295	637	java/lang/OutOfMemoryError
    //   2322	2329	637	java/lang/OutOfMemoryError
    //   678	683	685	java/io/IOException
    //   176	186	702	java/lang/OutOfMemoryError
    //   788	793	811	java/io/IOException
    //   89	98	853	finally
    //   129	145	853	finally
    //   169	176	853	finally
    //   231	236	853	finally
    //   294	307	853	finally
    //   324	337	853	finally
    //   463	468	853	finally
    //   540	549	853	finally
    //   621	634	853	finally
    //   647	660	853	finally
    //   788	793	853	finally
    //   837	850	853	finally
    //   913	918	853	finally
    //   941	944	853	finally
    //   970	983	853	finally
    //   1511	1524	853	finally
    //   1544	1551	853	finally
    //   1589	1598	853	finally
    //   1633	1643	853	finally
    //   1663	1690	853	finally
    //   1715	1720	853	finally
    //   1740	1749	853	finally
    //   1814	1824	853	finally
    //   1848	1874	853	finally
    //   1906	1974	853	finally
    //   2017	2026	853	finally
    //   2053	2058	853	finally
    //   2183	2210	853	finally
    //   2235	2248	853	finally
    //   2288	2295	853	finally
    //   2322	2329	853	finally
    //   176	186	878	finally
    //   704	731	878	finally
    //   913	918	944	java/io/IOException
    //   557	562	986	java/io/IOException
    //   1379	1384	1396	java/io/IOException
    //   1389	1394	1414	java/io/IOException
    //   2085	2090	2093	java/io/IOException
    //   1457	1462	2111	java/io/IOException
    //   1038	1047	2149	java/lang/OutOfMemoryError
    //   1067	1076	2149	java/lang/OutOfMemoryError
    //   1109	1118	2149	java/lang/OutOfMemoryError
    //   1138	1151	2149	java/lang/OutOfMemoryError
    //   1171	1184	2149	java/lang/OutOfMemoryError
    //   1204	1214	2149	java/lang/OutOfMemoryError
    //   1234	1272	2149	java/lang/OutOfMemoryError
    //   1292	1304	2149	java/lang/OutOfMemoryError
    //   1331	1336	2149	java/lang/OutOfMemoryError
    //   1365	1374	2149	java/lang/OutOfMemoryError
    //   1457	1462	2149	java/lang/OutOfMemoryError
    //   1482	1491	2149	java/lang/OutOfMemoryError
    //   1769	1782	2149	java/lang/OutOfMemoryError
    //   2133	2146	2149	java/lang/OutOfMemoryError
    //   1715	1720	2213	java/io/IOException
    //   1511	1524	2251	java/lang/OutOfMemoryError
    //   1544	1551	2251	java/lang/OutOfMemoryError
    //   1589	1598	2251	java/lang/OutOfMemoryError
    //   1633	1643	2251	java/lang/OutOfMemoryError
    //   1663	1690	2251	java/lang/OutOfMemoryError
    //   1715	1720	2251	java/lang/OutOfMemoryError
    //   1740	1749	2251	java/lang/OutOfMemoryError
    //   2235	2248	2251	java/lang/OutOfMemoryError
    //   2071	2076	2336	java/io/IOException
    //   345	350	2354	java/io/IOException
    //   668	673	2372	java/io/IOException
    //   860	865	2390	java/io/IOException
    //   870	875	2408	java/io/IOException
    //   1038	1047	2426	finally
    //   1067	1076	2426	finally
    //   1109	1118	2426	finally
    //   1138	1151	2426	finally
    //   1171	1184	2426	finally
    //   1204	1214	2426	finally
    //   1234	1272	2426	finally
    //   1292	1304	2426	finally
    //   1331	1336	2426	finally
    //   1365	1374	2426	finally
    //   1457	1462	2426	finally
    //   1482	1491	2426	finally
    //   1769	1782	2426	finally
    //   2133	2146	2426	finally
    //   1038	1047	2443	java/io/FileNotFoundException
    //   1067	1076	2443	java/io/FileNotFoundException
    //   1109	1118	2443	java/io/FileNotFoundException
    //   1138	1151	2443	java/io/FileNotFoundException
    //   1171	1184	2443	java/io/FileNotFoundException
    //   1204	1214	2443	java/io/FileNotFoundException
    //   1234	1272	2443	java/io/FileNotFoundException
    //   1292	1304	2443	java/io/FileNotFoundException
    //   1331	1336	2443	java/io/FileNotFoundException
    //   1365	1374	2443	java/io/FileNotFoundException
    //   1457	1462	2443	java/io/FileNotFoundException
    //   1482	1491	2443	java/io/FileNotFoundException
    //   1769	1782	2443	java/io/FileNotFoundException
    //   2133	2146	2443	java/io/FileNotFoundException
  }
  
  public String getSrc()
  {
    return mSrc;
  }
  
  public int getWidth()
  {
    return mWidth;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.UriImage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */