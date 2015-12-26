/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.support.v4.provider.DocumentFile;
import android.support.v4.provider.DocumentsContractApi19;
import android.support.v4.provider.DocumentsContractApi21;

class TreeDocumentFile
extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    TreeDocumentFile(DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.mContext = context;
        this.mUri = uri;
    }

    @Override
    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    @Override
    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
    }

    @Override
    public DocumentFile createDirectory(String string2) {
        if ((string2 = DocumentsContractApi21.createDirectory(this.mContext, this.mUri, string2)) != null) {
            return new TreeDocumentFile(this, this.mContext, (Uri)string2);
        }
        return null;
    }

    @Override
    public DocumentFile createFile(String string2, String string3) {
        if ((string2 = DocumentsContractApi21.createFile(this.mContext, this.mUri, string2, string3)) != null) {
            return new TreeDocumentFile(this, this.mContext, (Uri)string2);
        }
        return null;
    }

    @Override
    public boolean delete() {
        return DocumentsContractApi19.delete(this.mContext, this.mUri);
    }

    @Override
    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
    }

    @Override
    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    @Override
    public String getType() {
        return DocumentsContractApi19.getType(this.mContext, this.mUri);
    }

    @Override
    public Uri getUri() {
        return this.mUri;
    }

    @Override
    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    @Override
    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    @Override
    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    @Override
    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    @Override
    public DocumentFile[] listFiles() {
        Uri[] arruri = DocumentsContractApi21.listFiles(this.mContext, this.mUri);
        DocumentFile[] arrdocumentFile = new DocumentFile[arruri.length];
        for (int i2 = 0; i2 < arruri.length; ++i2) {
            arrdocumentFile[i2] = new TreeDocumentFile(this, this.mContext, arruri[i2]);
        }
        return arrdocumentFile;
    }

    @Override
    public boolean renameTo(String string2) {
        if ((string2 = DocumentsContractApi21.renameTo(this.mContext, this.mUri, string2)) != null) {
            this.mUri = string2;
            return true;
        }
        return false;
    }
}

