/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  android.util.Log
 *  android.webkit.MimeTypeMap
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v4.provider;

import android.net.Uri;
import android.support.v4.provider.DocumentFile;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class RawDocumentFile
extends DocumentFile {
    private File mFile;

    RawDocumentFile(DocumentFile documentFile, File file) {
        super(documentFile);
        this.mFile = file;
    }

    private static boolean deleteContents(File arrfile) {
        arrfile = arrfile.listFiles();
        boolean bl2 = true;
        boolean bl3 = true;
        if (arrfile != null) {
            int n2 = arrfile.length;
            int n3 = 0;
            do {
                bl2 = bl3;
                if (n3 >= n2) break;
                File file = arrfile[n3];
                bl2 = bl3;
                if (file.isDirectory()) {
                    bl2 = bl3 & RawDocumentFile.deleteContents(file);
                }
                bl3 = bl2;
                if (!file.delete()) {
                    Log.w((String)"DocumentFile", (String)("Failed to delete " + (Object)file));
                    bl3 = false;
                }
                ++n3;
            } while (true);
        }
        return bl2;
    }

    private static String getTypeForName(String string2) {
        int n2 = string2.lastIndexOf(46);
        if (n2 >= 0) {
            string2 = string2.substring(n2 + 1).toLowerCase();
            string2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(string2);
            if (string2 != null) {
                return string2;
            }
        }
        return "application/octet-stream";
    }

    @Override
    public boolean canRead() {
        return this.mFile.canRead();
    }

    @Override
    public boolean canWrite() {
        return this.mFile.canWrite();
    }

    @Override
    public DocumentFile createDirectory(String string2) {
        if ((string2 = new File(this.mFile, string2)).isDirectory() || string2.mkdir()) {
            return new RawDocumentFile(this, (File)string2);
        }
        return null;
    }

    @Override
    public DocumentFile createFile(String object, String string2) {
        String string3 = MimeTypeMap.getSingleton().getExtensionFromMimeType((String)object);
        object = string2;
        if (string3 != null) {
            object = string2 + "." + string3;
        }
        object = new File(this.mFile, (String)object);
        try {
            object.createNewFile();
            object = new RawDocumentFile(this, (File)object);
            return object;
        }
        catch (IOException var1_2) {
            Log.w((String)"DocumentFile", (String)("Failed to createFile: " + (Object)((Object)var1_2)));
            return null;
        }
    }

    @Override
    public boolean delete() {
        RawDocumentFile.deleteContents(this.mFile);
        return this.mFile.delete();
    }

    @Override
    public boolean exists() {
        return this.mFile.exists();
    }

    @Override
    public String getName() {
        return this.mFile.getName();
    }

    @Override
    public String getType() {
        if (this.mFile.isDirectory()) {
            return null;
        }
        return RawDocumentFile.getTypeForName(this.mFile.getName());
    }

    @Override
    public Uri getUri() {
        return Uri.fromFile((File)this.mFile);
    }

    @Override
    public boolean isDirectory() {
        return this.mFile.isDirectory();
    }

    @Override
    public boolean isFile() {
        return this.mFile.isFile();
    }

    @Override
    public long lastModified() {
        return this.mFile.lastModified();
    }

    @Override
    public long length() {
        return this.mFile.length();
    }

    @Override
    public DocumentFile[] listFiles() {
        ArrayList arrayList = new ArrayList();
        File[] arrfile = this.mFile.listFiles();
        if (arrfile != null) {
            int n2 = arrfile.length;
            for (int i2 = 0; i2 < n2; ++i2) {
                arrayList.add((Object)new RawDocumentFile(this, arrfile[i2]));
            }
        }
        return (DocumentFile[])arrayList.toArray((Object[])new DocumentFile[arrayList.size()]);
    }

    @Override
    public boolean renameTo(String string2) {
        string2 = new File(this.mFile.getParentFile(), string2);
        if (this.mFile.renameTo((File)string2)) {
            this.mFile = string2;
            return true;
        }
        return false;
    }
}

