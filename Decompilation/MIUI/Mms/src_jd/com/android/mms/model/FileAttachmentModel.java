package com.android.mms.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.PduPart;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public abstract class FileAttachmentModel
  extends Model
{
  protected String mContentType;
  protected Context mContext;
  protected byte[] mData;
  protected String mFileName;
  protected int mSize;
  protected Uri mUri;
  
  public FileAttachmentModel() {}
  
  public FileAttachmentModel(Context paramContext, Uri paramUri, String paramString)
    throws MmsException
  {
    mContext = paramContext;
    mContentType = paramString;
    mUri = paramUri;
    initModelFromUri(paramContext, paramUri);
    initAttachmentSize();
  }
  
  public FileAttachmentModel(Context paramContext, String paramString1, String paramString2, Uri paramUri)
    throws MmsException
  {
    mContext = paramContext;
    mContentType = paramString1;
    mFileName = paramString2;
    mUri = paramUri;
    initAttachmentSize();
  }
  
  private void initAttachmentSize()
    throws MmsException
  {
    Object localObject2 = mContext.getContentResolver();
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        localObject2 = ((ContentResolver)localObject2).openInputStream(mUri);
        localObject1 = localObject2;
        if ((localObject2 instanceof FileInputStream))
        {
          localObject1 = localObject2;
          mSize = ((int)((FileInputStream)localObject2).getChannel().size());
          if (localObject2 == null) {}
        }
      }
      finally
      {
        if (localObject1 != null) {
          ((InputStream)localObject1).close();
        }
      }
      try
      {
        ((InputStream)localObject2).close();
        return;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        Log.e("Mms/file_attach", "initAttachmentSize, file is not found??");
        throw new MmsException("FileAttachmentModel#initAttachmentSize() " + localFileNotFoundException.getMessage());
      }
      catch (IOException localIOException)
      {
        Log.e("Mms/file_attach", "initAttachmentSize, other exceptions");
      }
      localObject1 = localObject2;
      if (-1 != ((InputStream)localObject2).read())
      {
        localObject1 = localObject2;
        mSize += 1;
      }
    }
  }
  
  private void initFromContentUri(Context paramContext, Uri paramUri)
  {
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramUri, null, null, null, null);
    if (paramContext == null) {
      throw new IllegalArgumentException("Query on " + paramUri + " returns null result.");
    }
    try
    {
      if ((paramContext.getCount() != 1) || (!paramContext.moveToFirst())) {
        throw new IllegalArgumentException("Query on " + paramUri + " returns 0 or multiple rows.");
      }
    }
    finally
    {
      paramContext.close();
      throw paramUri;
    }
  }
  
  private void initFromFile(Context paramContext, Uri paramUri)
  {
    mFileName = paramUri.getPath();
    if (TextUtils.isEmpty(mContentType))
    {
      int i = mFileName.lastIndexOf(".");
      if (i != -1)
      {
        paramContext = mFileName.substring(i + 1, mFileName.length());
        mContentType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramContext.toLowerCase());
      }
      if (TextUtils.isEmpty(mContentType)) {
        mContentType = "unknown_type";
      }
    }
  }
  
  private void initModelFromUri(Context paramContext, Uri paramUri)
    throws MmsException
  {
    try
    {
      if (paramUri.getScheme().equals("content")) {
        initFromContentUri(paramContext, paramUri);
      }
      for (;;)
      {
        mFileName = mFileName.substring(mFileName.lastIndexOf('/') + 1);
        if ((!mFileName.startsWith(".")) || (mFileName.length() <= 1)) {
          break;
        }
        mFileName = mFileName.substring(1);
        return;
        if (paramUri.getScheme().equals("file")) {
          initFromFile(paramContext, paramUri);
        }
      }
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      Log.d("Mms/file_attach", "IllegalArgumentException caught while opening or reading stream", paramContext);
      throw new MmsException("Type of vcard is unknown.");
    }
    catch (NullPointerException paramContext)
    {
      Log.d("Mms/file_attach", "FileName is null", paramContext);
      throw new MmsException("Type of vcard is unknown.");
    }
  }
  
  public static boolean isVCard(PduPart paramPduPart)
  {
    boolean bool2 = false;
    String str = new String(paramPduPart.getContentType());
    byte[] arrayOfByte1 = paramPduPart.getContentLocation();
    byte[] arrayOfByte2 = paramPduPart.getName();
    byte[] arrayOfByte3 = paramPduPart.getContentId();
    paramPduPart = paramPduPart.getFilename();
    boolean bool1;
    if (arrayOfByte1 != null)
    {
      paramPduPart = new String(arrayOfByte1);
      if (!"text/x-vCard".equalsIgnoreCase(str))
      {
        if (!str.equals("application/oct-stream"))
        {
          bool1 = bool2;
          if (!str.equals("application/octet-stream")) {}
        }
        else
        {
          bool1 = bool2;
          if (!paramPduPart.endsWith(".vcf")) {}
        }
      }
      else {
        bool1 = true;
      }
    }
    label157:
    label159:
    label217:
    label219:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return bool1;
                  if (arrayOfByte2 == null) {
                    break label159;
                  }
                  paramPduPart = new String(arrayOfByte2);
                  if ("text/x-vCard".equalsIgnoreCase(str)) {
                    break label157;
                  }
                  if (str.equals("application/oct-stream")) {
                    break;
                  }
                  bool1 = bool2;
                } while (!str.equals("application/octet-stream"));
                bool1 = bool2;
              } while (!paramPduPart.endsWith(".vcf"));
              return true;
              if (arrayOfByte3 == null) {
                break label219;
              }
              paramPduPart = new String(arrayOfByte3);
              if ("text/x-vCard".equalsIgnoreCase(str)) {
                break label217;
              }
              if (str.equals("application/oct-stream")) {
                break;
              }
              bool1 = bool2;
            } while (!str.equals("application/octet-stream"));
            bool1 = bool2;
          } while (!paramPduPart.endsWith(".vcf"));
          return true;
          bool1 = bool2;
        } while (paramPduPart == null);
        paramPduPart = new String(paramPduPart);
        if ("text/x-vCard".equalsIgnoreCase(str)) {
          break label277;
        }
        if (str.equals("application/oct-stream")) {
          break;
        }
        bool1 = bool2;
      } while (!str.equals("application/octet-stream"));
      bool1 = bool2;
    } while (!paramPduPart.endsWith(".vcf"));
    label277:
    return true;
  }
  
  public int getAttachSize()
  {
    return mSize;
  }
  
  public String getContentType()
  {
    return mContentType;
  }
  
  public String getSrc()
  {
    return mFileName;
  }
  
  public Uri getUri()
  {
    return mUri;
  }
  
  public boolean isVCard()
  {
    if (mContentType == null) {
      return mFileName.toLowerCase().endsWith(".vcf");
    }
    return mContentType.equalsIgnoreCase("text/x-vCard");
  }
  
  void setData(byte[] paramArrayOfByte)
  {
    mData = paramArrayOfByte;
  }
  
  void setUri(Uri paramUri)
  {
    mUri = paramUri;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.FileAttachmentModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */