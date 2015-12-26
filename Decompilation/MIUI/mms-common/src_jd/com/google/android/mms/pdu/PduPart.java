package com.google.android.mms.pdu;

import android.net.Uri;
import java.util.Map;

public class PduPart
{
  public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
  static final byte[] DISPOSITION_ATTACHMENT = "attachment".getBytes();
  static final byte[] DISPOSITION_FROM_DATA = "from-data".getBytes();
  static final byte[] DISPOSITION_INLINE = "inline".getBytes();
  public static final String P_7BIT = "7bit";
  public static final String P_8BIT = "8bit";
  public static final String P_BASE64 = "base64";
  public static final String P_BINARY = "binary";
  public static final int P_CHARSET = 129;
  public static final int P_COMMENT = 155;
  public static final int P_CONTENT_DISPOSITION = 197;
  public static final int P_CONTENT_ID = 192;
  public static final int P_CONTENT_LOCATION = 142;
  public static final int P_CONTENT_TRANSFER_ENCODING = 200;
  public static final int P_CONTENT_TYPE = 145;
  public static final int P_CREATION_DATE = 147;
  public static final int P_CT_MR_TYPE = 137;
  public static final int P_DEP_COMMENT = 140;
  public static final int P_DEP_CONTENT_DISPOSITION = 174;
  public static final int P_DEP_DOMAIN = 141;
  public static final int P_DEP_FILENAME = 134;
  public static final int P_DEP_NAME = 133;
  public static final int P_DEP_PATH = 143;
  public static final int P_DEP_START = 138;
  public static final int P_DEP_START_INFO = 139;
  public static final int P_DIFFERENCES = 135;
  public static final int P_DISPOSITION_ATTACHMENT = 129;
  public static final int P_DISPOSITION_FROM_DATA = 128;
  public static final int P_DISPOSITION_INLINE = 130;
  public static final int P_DOMAIN = 156;
  public static final int P_FILENAME = 152;
  public static final int P_LEVEL = 130;
  public static final int P_MAC = 146;
  public static final int P_MAX_AGE = 142;
  public static final int P_MODIFICATION_DATE = 148;
  public static final int P_NAME = 151;
  public static final int P_PADDING = 136;
  public static final int P_PATH = 157;
  public static final int P_Q = 128;
  public static final String P_QUOTED_PRINTABLE = "quoted-printable";
  public static final int P_READ_DATE = 149;
  public static final int P_SEC = 145;
  public static final int P_SECURE = 144;
  public static final int P_SIZE = 150;
  public static final int P_START = 153;
  public static final int P_START_INFO = 154;
  public static final int P_TYPE = 131;
  private static final String TAG = "PduPart";
  private byte[] mPartData = null;
  private Map<Integer, Object> mPartHeader = null;
  private Uri mUri = null;
  
  public String generateLocation()
  {
    byte[] arrayOfByte2 = (byte[])mPartHeader.get(Integer.valueOf(151));
    byte[] arrayOfByte1 = arrayOfByte2;
    if (arrayOfByte2 == null)
    {
      arrayOfByte2 = (byte[])mPartHeader.get(Integer.valueOf(152));
      arrayOfByte1 = arrayOfByte2;
      if (arrayOfByte2 == null) {
        arrayOfByte1 = (byte[])mPartHeader.get(Integer.valueOf(142));
      }
    }
    if (arrayOfByte1 == null)
    {
      arrayOfByte1 = (byte[])mPartHeader.get(Integer.valueOf(192));
      return "cid:" + new String(arrayOfByte1);
    }
    return new String(arrayOfByte1);
  }
  
  public int getCharset()
  {
    Integer localInteger = (Integer)mPartHeader.get(Integer.valueOf(129));
    if (localInteger == null) {
      return 0;
    }
    return localInteger.intValue();
  }
  
  public byte[] getContentDisposition()
  {
    return (byte[])mPartHeader.get(Integer.valueOf(197));
  }
  
  public byte[] getContentId()
  {
    return (byte[])mPartHeader.get(Integer.valueOf(192));
  }
  
  public byte[] getContentLocation()
  {
    return (byte[])mPartHeader.get(Integer.valueOf(142));
  }
  
  public byte[] getContentTransferEncoding()
  {
    return (byte[])mPartHeader.get(Integer.valueOf(200));
  }
  
  public byte[] getContentType()
  {
    return (byte[])mPartHeader.get(Integer.valueOf(145));
  }
  
  public byte[] getData()
  {
    if (mPartData == null) {
      return null;
    }
    byte[] arrayOfByte = new byte[mPartData.length];
    System.arraycopy(mPartData, 0, arrayOfByte, 0, mPartData.length);
    return arrayOfByte;
  }
  
  public Uri getDataUri()
  {
    return mUri;
  }
  
  public byte[] getFilename()
  {
    return (byte[])mPartHeader.get(Integer.valueOf(152));
  }
  
  public byte[] getName()
  {
    return (byte[])mPartHeader.get(Integer.valueOf(151));
  }
  
  public void setCharset(int paramInt)
  {
    mPartHeader.put(Integer.valueOf(129), Integer.valueOf(paramInt));
  }
  
  public void setContentDisposition(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("null content-disposition");
    }
    mPartHeader.put(Integer.valueOf(197), paramArrayOfByte);
  }
  
  public void setContentId(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      throw new IllegalArgumentException("Content-Id may not be null or empty.");
    }
    if ((paramArrayOfByte.length > 1) && ((char)paramArrayOfByte[0] == '<') && ((char)paramArrayOfByte[(paramArrayOfByte.length - 1)] == '>'))
    {
      mPartHeader.put(Integer.valueOf(192), paramArrayOfByte);
      return;
    }
    byte[] arrayOfByte = new byte[paramArrayOfByte.length + 2];
    arrayOfByte[0] = 60;
    arrayOfByte[(arrayOfByte.length - 1)] = 62;
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 1, paramArrayOfByte.length);
    mPartHeader.put(Integer.valueOf(192), arrayOfByte);
  }
  
  public void setContentLocation(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("null content-location");
    }
    mPartHeader.put(Integer.valueOf(142), paramArrayOfByte);
  }
  
  public void setContentTransferEncoding(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("null content-transfer-encoding");
    }
    mPartHeader.put(Integer.valueOf(200), paramArrayOfByte);
  }
  
  public void setContentType(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("null content-type");
    }
    mPartHeader.put(Integer.valueOf(145), paramArrayOfByte);
  }
  
  public void setData(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return;
    }
    mPartData = new byte[paramArrayOfByte.length];
    System.arraycopy(paramArrayOfByte, 0, mPartData, 0, paramArrayOfByte.length);
  }
  
  public void setDataUri(Uri paramUri)
  {
    mUri = paramUri;
  }
  
  public void setFilename(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("null content-id");
    }
    mPartHeader.put(Integer.valueOf(152), paramArrayOfByte);
  }
  
  public void setName(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("null content-id");
    }
    mPartHeader.put(Integer.valueOf(151), paramArrayOfByte);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.PduPart
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */