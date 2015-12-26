/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package android.support.v4.util;

import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
    private final File mBackupName;
    private final File mBaseName;

    public AtomicFile(File file) {
        this.mBaseName = file;
        this.mBackupName = new File(file.getPath() + ".bak");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static boolean sync(FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) return true;
        try {
            fileOutputStream.getFD().sync();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    public void delete() {
        this.mBaseName.delete();
        this.mBackupName.delete();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void failWrite(FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) return;
        AtomicFile.sync(fileOutputStream);
        try {
            fileOutputStream.close();
            this.mBaseName.delete();
            this.mBackupName.renameTo(this.mBaseName);
            return;
        }
        catch (IOException var1_2) {
            Log.w((String)"AtomicFile", (String)"failWrite: Got exception:", (Throwable)var1_2);
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void finishWrite(FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) return;
        AtomicFile.sync(fileOutputStream);
        try {
            fileOutputStream.close();
            this.mBackupName.delete();
            return;
        }
        catch (IOException var1_2) {
            Log.w((String)"AtomicFile", (String)"finishWrite: Got exception:", (Throwable)var1_2);
            return;
        }
    }

    public File getBaseFile() {
        return this.mBaseName;
    }

    public FileInputStream openRead() {
        if (this.mBackupName.exists()) {
            this.mBaseName.delete();
            this.mBackupName.renameTo(this.mBaseName);
        }
        return new FileInputStream(this.mBaseName);
    }

    public byte[] readFully() {
        int n2;
        byte[] arrby;
        int n3 = 0;
        FileInputStream fileInputStream = this.openRead();
        try {
            arrby = new byte[fileInputStream.available()];
            do {
                if ((n2 = fileInputStream.read(arrby, n3, arrby.length - n3)) > 0) break block4;
                break;
            } while (true);
        }
        catch (Throwable var3_4) {
            fileInputStream.close();
            throw var3_4;
        }
        {
            block4 : {
                fileInputStream.close();
                return arrby;
            }
            n3 = n2 + n3;
            n2 = fileInputStream.available();
            if (n2 <= arrby.length - n3) continue;
            byte[] arrby2 = new byte[n2 + n3];
            System.arraycopy((Object)arrby, (int)0, (Object)arrby2, (int)0, (int)n3);
            arrby = arrby2;
            continue;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public FileOutputStream startWrite() {
        if (this.mBaseName.exists()) {
            if (!this.mBackupName.exists()) {
                if (!this.mBaseName.renameTo(this.mBackupName)) {
                    Log.w((String)"AtomicFile", (String)("Couldn't rename file " + (Object)this.mBaseName + " to backup file " + (Object)this.mBackupName));
                }
            } else {
                this.mBaseName.delete();
            }
        }
        try {
            return new FileOutputStream(this.mBaseName);
        }
        catch (FileNotFoundException var1_2) {
            if (!this.mBaseName.getParentFile().mkdir()) {
                throw new IOException("Couldn't create directory " + (Object)this.mBaseName);
            }
            try {
                return new FileOutputStream(this.mBaseName);
            }
            catch (FileNotFoundException var1_4) {
                throw new IOException("Couldn't create " + (Object)this.mBaseName);
            }
        }
    }
}

