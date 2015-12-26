package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;

class SingleDocumentFile
  extends DocumentFile
{
  private Context mContext;
  private Uri mUri;
  
  SingleDocumentFile(DocumentFile paramDocumentFile, Context paramContext, Uri paramUri)
  {
    super(paramDocumentFile);
    mContext = paramContext;
    mUri = paramUri;
  }
  
  public boolean canRead()
  {
    return DocumentsContractApi19.canRead(mContext, mUri);
  }
  
  public boolean canWrite()
  {
    return DocumentsContractApi19.canWrite(mContext, mUri);
  }
  
  public DocumentFile createDirectory(String paramString)
  {
    throw new UnsupportedOperationException();
  }
  
  public DocumentFile createFile(String paramString1, String paramString2)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean delete()
  {
    return DocumentsContractApi19.delete(mContext, mUri);
  }
  
  public boolean exists()
  {
    return DocumentsContractApi19.exists(mContext, mUri);
  }
  
  public String getName()
  {
    return DocumentsContractApi19.getName(mContext, mUri);
  }
  
  public String getType()
  {
    return DocumentsContractApi19.getType(mContext, mUri);
  }
  
  public Uri getUri()
  {
    return mUri;
  }
  
  public boolean isDirectory()
  {
    return DocumentsContractApi19.isDirectory(mContext, mUri);
  }
  
  public boolean isFile()
  {
    return DocumentsContractApi19.isFile(mContext, mUri);
  }
  
  public long lastModified()
  {
    return DocumentsContractApi19.lastModified(mContext, mUri);
  }
  
  public long length()
  {
    return DocumentsContractApi19.length(mContext, mUri);
  }
  
  public DocumentFile[] listFiles()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean renameTo(String paramString)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.provider.SingleDocumentFile
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */