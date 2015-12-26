/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  android.webkit.MimeTypeMap
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.PduPart
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.android.mms.model.Model;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.PduPart;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public abstract class FileAttachmentModel
extends Model {
    protected String mContentType;
    protected Context mContext;
    protected byte[] mData;
    protected String mFileName;
    protected int mSize;
    protected Uri mUri;

    public FileAttachmentModel() {
    }

    public FileAttachmentModel(Context context, Uri uri, String string) throws MmsException {
        this.mContext = context;
        this.mContentType = string;
        this.mUri = uri;
        this.initModelFromUri(context, uri);
        this.initAttachmentSize();
    }

    public FileAttachmentModel(Context context, String string, String string2, Uri uri) throws MmsException {
        this.mContext = context;
        this.mContentType = string;
        this.mFileName = string2;
        this.mUri = uri;
        this.initAttachmentSize();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void initAttachmentSize() throws MmsException {
        block9 : {
            var2_1 = this.mContext.getContentResolver();
            var1_3 = null;
            try {
                var1_3 = var2_1 = var2_1.openInputStream(this.mUri);
                if (!(var2_1 instanceof FileInputStream)) break block9;
                var1_3 = var2_1;
                this.mSize = (int)((FileInputStream)var2_1).getChannel().size();
            }
            catch (Throwable var2_2) {
                if (var1_3 == null) throw var2_2;
                try {
                    var1_3.close();
                    throw var2_2;
                }
                catch (FileNotFoundException var1_4) {
                    Log.e((String)"Mms/file_attach", (String)"initAttachmentSize, file is not found??");
                    throw new MmsException("FileAttachmentModel#initAttachmentSize() " + var1_4.getMessage());
                }
                catch (IOException var1_5) {
                    Log.e((String)"Mms/file_attach", (String)"initAttachmentSize, other exceptions");
                    return;
                }
            }
            do {
                if (var2_1 == null) return;
                var2_1.close();
                return;
                break;
            } while (true);
        }
        do {
            var1_3 = var2_1;
            if (-1 == var2_1.read()) ** continue;
            var1_3 = var2_1;
            ++this.mSize;
            continue;
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void initFromContentUri(Context context, Uri uri) {
        if ((context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (String[])null, (String)null, (String[])null, (String)null)) == null) {
            throw new IllegalArgumentException("Query on " + (Object)uri + " returns null result.");
        }
        try {
            if (context.getCount() != 1 || !context.moveToFirst()) {
                throw new IllegalArgumentException("Query on " + (Object)uri + " returns 0 or multiple rows.");
            }
            this.mFileName = context.getString(context.getColumnIndexOrThrow("_display_name"));
            return;
        }
        finally {
            context.close();
        }
    }

    private void initFromFile(Context object, Uri uri) {
        this.mFileName = uri.getPath();
        if (TextUtils.isEmpty((CharSequence)this.mContentType)) {
            int n = this.mFileName.lastIndexOf(".");
            if (n != -1) {
                object = this.mFileName.substring(n + 1, this.mFileName.length());
                this.mContentType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(object.toLowerCase());
            }
            if (TextUtils.isEmpty((CharSequence)this.mContentType)) {
                this.mContentType = "unknown_type";
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void initModelFromUri(Context context, Uri uri) throws MmsException {
        try {
            if (uri.getScheme().equals((Object)"content")) {
                this.initFromContentUri(context, uri);
            } else if (uri.getScheme().equals((Object)"file")) {
                this.initFromFile(context, uri);
            }
            this.mFileName = this.mFileName.substring(this.mFileName.lastIndexOf(47) + 1);
            if (!this.mFileName.startsWith(".") || this.mFileName.length() <= 1) return;
            {
                this.mFileName = this.mFileName.substring(1);
                return;
            }
        }
        catch (IllegalArgumentException var1_2) {
            Log.d((String)"Mms/file_attach", (String)"IllegalArgumentException caught while opening or reading stream", (Throwable)var1_2);
            throw new MmsException("Type of vcard is unknown.");
        }
        catch (NullPointerException var1_3) {
            Log.d((String)"Mms/file_attach", (String)"FileName is null", (Throwable)var1_3);
            throw new MmsException("Type of vcard is unknown.");
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isVCard(PduPart object) {
        boolean bl;
        boolean bl2 = false;
        String string = new String(object.getContentType());
        byte[] arrby = object.getContentLocation();
        byte[] arrby2 = object.getName();
        byte[] arrby3 = object.getContentId();
        object = object.getFilename();
        if (arrby != null) {
            object = new String(arrby);
            if ("text/x-vCard".equalsIgnoreCase(string)) return true;
            if (!string.equals((Object)"application/oct-stream")) {
                bl = bl2;
                if (!string.equals((Object)"application/octet-stream")) return bl;
            }
            bl = bl2;
            if (!object.endsWith(".vcf")) return bl;
            return true;
        }
        if (arrby2 != null) {
            object = new String(arrby2);
            if ("text/x-vCard".equalsIgnoreCase(string)) return true;
            if (!string.equals((Object)"application/oct-stream")) {
                bl = bl2;
                if (!string.equals((Object)"application/octet-stream")) return bl;
            }
            bl = bl2;
            if (!object.endsWith(".vcf")) return bl;
            return true;
        }
        if (arrby3 != null) {
            object = new String(arrby3);
            if ("text/x-vCard".equalsIgnoreCase(string)) return true;
            if (!string.equals((Object)"application/oct-stream")) {
                bl = bl2;
                if (!string.equals((Object)"application/octet-stream")) return bl;
            }
            bl = bl2;
            if (!object.endsWith(".vcf")) return bl;
            return true;
        }
        bl = bl2;
        if (object == null) return bl;
        object = new String((byte[])object);
        if ("text/x-vCard".equalsIgnoreCase(string)) return true;
        if (!string.equals((Object)"application/oct-stream")) {
            bl = bl2;
            if (!string.equals((Object)"application/octet-stream")) return bl;
        }
        bl = bl2;
        if (!object.endsWith(".vcf")) return bl;
        return true;
    }

    public int getAttachSize() {
        return this.mSize;
    }

    public String getContentType() {
        return this.mContentType;
    }

    public String getSrc() {
        return this.mFileName;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean isVCard() {
        if (this.mContentType == null) {
            return this.mFileName.toLowerCase().endsWith(".vcf");
        }
        return this.mContentType.equalsIgnoreCase("text/x-vCard");
    }

    void setData(byte[] arrby) {
        this.mData = arrby;
    }

    void setUri(Uri uri) {
        this.mUri = uri;
    }
}

