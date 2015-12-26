/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.provider.DocumentsContractApi19;
import android.support.v4.provider.DocumentsContractApi21;
import android.support.v4.provider.RawDocumentFile;
import android.support.v4.provider.SingleDocumentFile;
import android.support.v4.provider.TreeDocumentFile;
import java.io.File;

public abstract class DocumentFile {
    static final String TAG = "DocumentFile";
    private final DocumentFile mParent;

    DocumentFile(DocumentFile documentFile) {
        this.mParent = documentFile;
    }

    public static DocumentFile fromFile(File file) {
        return new RawDocumentFile(null, file);
    }

    public static DocumentFile fromSingleUri(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return new SingleDocumentFile(null, context, uri);
        }
        return null;
    }

    public static DocumentFile fromTreeUri(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new TreeDocumentFile(null, context, DocumentsContractApi21.prepareTreeUri(uri));
        }
        return null;
    }

    public static boolean isDocumentUri(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return DocumentsContractApi19.isDocumentUri(context, uri);
        }
        return false;
    }

    public abstract boolean canRead();

    public abstract boolean canWrite();

    public abstract DocumentFile createDirectory(String var1);

    public abstract DocumentFile createFile(String var1, String var2);

    public abstract boolean delete();

    public abstract boolean exists();

    public DocumentFile findFile(String string2) {
        for (DocumentFile documentFile : this.listFiles()) {
            if (!string2.equals((Object)documentFile.getName())) continue;
            return documentFile;
        }
        return null;
    }

    public abstract String getName();

    public DocumentFile getParentFile() {
        return this.mParent;
    }

    public abstract String getType();

    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract long lastModified();

    public abstract long length();

    public abstract DocumentFile[] listFiles();

    public abstract boolean renameTo(String var1);
}

