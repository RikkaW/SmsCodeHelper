package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;

class TreeDocumentFile
  extends DocumentFile
{
  private Context mContext;
  private Uri mUri;
  
  TreeDocumentFile(DocumentFile paramDocumentFile, Context paramContext, Uri paramUri)
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
    paramString = DocumentsContractApi21.createDirectory(mContext, mUri, paramString);
    if (paramString != null) {
      return new TreeDocumentFile(this, mContext, paramString);
    }
    return null;
  }
  
  public DocumentFile createFile(String paramString1, String paramString2)
  {
    paramString1 = DocumentsContractApi21.createFile(mContext, mUri, paramString1, paramString2);
    if (paramString1 != null) {
      return new TreeDocumentFile(this, mContext, paramString1);
    }
    return null;
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
    Uri[] arrayOfUri = DocumentsContractApi21.listFiles(mContext, mUri);
    DocumentFile[] arrayOfDocumentFile = new DocumentFile[arrayOfUri.length];
    int i = 0;
    while (i < arrayOfUri.length)
    {
      arrayOfDocumentFile[i] = new TreeDocumentFile(this, mContext, arrayOfUri[i]);
      i += 1;
    }
    return arrayOfDocumentFile;
  }
  
  public boolean renameTo(String paramString)
  {
    paramString = DocumentsContractApi21.renameTo(mContext, mUri, paramString);
    if (paramString != null)
    {
      mUri = paramString;
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.provider.TreeDocumentFile
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */