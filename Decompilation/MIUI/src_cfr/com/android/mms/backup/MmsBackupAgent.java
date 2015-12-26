/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.OperationApplicationException
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.ParcelFileDescriptor
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.collect.Lists
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Comparator
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.Vector
 *  miui.app.backup.BackupManager
 *  miui.app.backup.BackupMeta
 *  miui.app.backup.FullBackupAgent
 */
package com.android.mms.backup;

import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.backup.MmsManager;
import com.android.mms.backup.MmsProtos;
import com.android.mms.backup.SmsManager;
import com.android.mms.backup.SmsProtos;
import com.android.mms.backup.SyncRootProtos;
import com.google.android.collect.Lists;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import miui.app.backup.BackupManager;
import miui.app.backup.BackupMeta;
import miui.app.backup.FullBackupAgent;

public class MmsBackupAgent
extends FullBackupAgent {
    private HashMap<String, Uri> mAttach2Uri;
    private BackupManager mBackupManager;
    private MmsManager mMmsManager;
    private SmsManager mSmsManager;

    private void applyMms(ContentResolver intent, ArrayList<MmsProtos.Pdu> uri, ArrayList<ContentProviderOperation> object) throws RemoteException, OperationApplicationException {
        int n;
        ContentProviderResult[] arrcontentProviderResult = intent.applyBatch("mms", object);
        ArrayList arrayList = Lists.newArrayList();
        ArrayList arrayList2 = Lists.newArrayList();
        object = new long[arrcontentProviderResult.length];
        for (n = 0; n < arrcontentProviderResult.length; ++n) {
            long l;
            Object object2 = arrcontentProviderResult[n].uri.getPathSegments();
            Object object3 = (String)object2.get(0);
            try {
                l = Long.valueOf((String)((String)object2.get(1)));
            }
            catch (NumberFormatException var1_2) {
                Log.e((String)"SmsController", (String)"NumberFormatException", (Throwable)var1_2);
                return;
            }
            object[n] = l;
            if (object3.equals((Object)"restored")) {
                object2 = ContentProviderOperation.newDelete((Uri)Uri.withAppendedPath((Uri)Telephony.Mms.CONTENT_URI, (String)("" + l + "/part"))).build();
                arrayList2.add((Object)null);
                arrayList.add(object2);
            }
            if (!object3.equals((Object)"inserted") && !object3.equals((Object)"restored")) continue;
            object3 = (MmsProtos.Pdu)uri.get(n);
            for (int i = 0; i < object3.getPduPartsCount(); ++i) {
                object2 = object3.getPduParts(i);
                arrayList2.add(object2);
                arrayList.add((Object)this.mMmsManager.restorePduPart(l, (MmsProtos.PduPart)object2));
            }
            continue;
        }
        intent = intent.applyBatch("mms", arrayList);
        arrayList.clear();
        for (n = 0; n < intent.length; ++n) {
            if (arrayList2.get(n) == null) continue;
            uri = intent[n].uri.buildUpon().appendQueryParameter("supress_making_mms_preview", "1").build();
            this.mMmsManager.restorePduPartFile(uri, (MmsProtos.PduPart)arrayList2.get(n));
        }
        intent = new Intent("android.provider.Telephony.MAKE_MMS_PREVIEW");
        intent.putExtra("_id", (long[])object);
        intent.setPackage("com.android.providers.telephony");
        this.startService(intent);
    }

    private void applySms() {
        ContentProviderResult[] arrcontentProviderResult = this.mSmsManager.apply();
        for (int i = 0; i < arrcontentProviderResult.length; ++i) {
            String string = (String)arrcontentProviderResult[i].uri.getPathSegments().get(0);
            if (!string.equals((Object)"inserted") && !string.equals((Object)"restored")) continue;
        }
    }

    protected int getVersion(int n) {
        return 1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected int onAttachRestore(BackupMeta var1_1, ParcelFileDescriptor var2_11, String var3_15) {
        block36 : {
            var4_25 = 0;
            var1_1 = (Uri)this.mAttach2Uri.get((Object)var3_15 /* !! */ );
            var3_16 = null;
            var8_26 = null;
            var7_30 = null;
            var9_32 = null;
            var11_33 = null;
            var10_34 = null;
            var6_35 = var11_33;
            var1_1 = this.getContentResolver().openOutputStream((Uri)var1_1, "w");
            if (var1_1 != null) break block36;
            var4_25 = 1;
            ** if (var1_1 == null) goto lbl17
lbl-1000: // 1 sources:
            {
                var1_1.close();
            }
lbl17: // 2 sources:
            ** GOTO lbl44
        }
        var7_30 = var1_1;
        var6_35 = var11_33;
        var3_17 = var1_1;
        var8_26 = var1_1;
        var2_11 = new FileInputStream(var2_11.getFileDescriptor());
        try {
            var3_18 = new byte[8192];
            while ((var5_36 = var2_11.read(var3_18)) > 0) {
                var1_1.write(var3_18, 0, var5_36);
            }
            ** GOTO lbl35
        }
        catch (IllegalArgumentException var3_19) {
            var8_26 = var1_1;
            var1_1 = var2_11;
            ** GOTO lbl91
lbl35: // 1 sources:
            if (var1_1 != null) {
                var1_1.close();
            }
            ** GOTO lbl50
            catch (IOException var8_27) {
                var1_1 = var7_30;
                var2_11 = var9_32;
                ** GOTO lbl62
                catch (IOException var1_6) {}
lbl44: // 2 sources:
                if (false == false) return var4_25;
                try {
                    throw new NullPointerException();
                }
                catch (IOException var1_7) {
                    return 1;
                }
                catch (IOException var1_8) {}
lbl50: // 2 sources:
                if (var2_11 == null) return 0;
                try {
                    var2_11.close();
                    return 0;
                }
                catch (IOException var1_3) {
                    return 0;
                }
                catch (Throwable var7_31) {
                    var6_35 = var2_11;
                    var3_24 = var1_1;
                    var1_1 = var7_31;
                    ** GOTO lbl-1000
                }
                catch (IOException var8_29) {}
lbl62: // 2 sources:
                var6_35 = var2_11;
                var3_21 = var1_1;
                try {
                    var8_28.printStackTrace();
                    ** if (var1_1 == null) goto lbl69
                }
                catch (Throwable var1_5) lbl-1000: // 2 sources:
                {
                    if (var3_23 != null) {
                        var3_23.close();
                    }
                    ** GOTO lbl83
                    catch (IOException var1_9) {}
lbl76: // 2 sources:
                    if (var2_11 == null) return var4_25;
                    try {
                        var2_11.close();
                        return 0;
                    }
                    catch (IOException var1_4) {
                        return 0;
                    }
                    catch (IOException var2_13) {}
lbl83: // 2 sources:
                    if (var6_35 == null) throw var1_1;
                    try {
                        var6_35.close();
                    }
                    catch (IOException var2_14) {
                        throw var1_1;
                    }
                    throw var1_1;
                }
lbl-1000: // 1 sources:
                {
                    var1_1.close();
                }
lbl69: // 2 sources:
                ** GOTO lbl76
            }
            catch (IllegalArgumentException var1_10) {
                var1_1 = var10_34;
            }
lbl91: // 2 sources:
            if (var8_26 != null) {
                try {
                    var8_26.close();
                }
                catch (IOException var2_12) {}
            }
            if (var1_1 == null) return var4_25;
            try {
                var1_1.close();
                return 0;
            }
            catch (IOException var1_2) {
                return 0;
            }
        }
    }

    /*
     * Exception decompiling
     */
    protected int onDataRestore(BackupMeta var1_1, ParcelFileDescriptor var2_3) throws IOException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 44[SIMPLE_IF_TAKEN]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected int onFullBackup(ParcelFileDescriptor var1_1, int var2_2) throws IOException {
        block17 : {
            this.mBackupManager = BackupManager.getBackupManager((Context)this);
            if (var2_2 == 1) ** GOTO lbl15
            if (var2_2 != 2) return 0;
            var4_6 = SyncRootProtos.SyncRoot.newBuilder();
            this.mMmsManager = new MmsManager(this.getApplicationContext());
            var5_12 = MmsProtos.MmsCollection.newBuilder();
            var6_14 = this.mMmsManager.prepareAllMmsIds();
            this.mAttach2Uri = new HashMap();
            this.mMmsManager.setMmsAttach(this.mAttach2Uri);
            var2_2 = 0;
            var3_16 = var6_14.size();
            this.mBackupManager.setCustomProgress(1, 0, var3_16);
            var6_14 = var6_14.iterator();
            ** GOTO lbl51
lbl15: // 1 sources:
            var4_3 = SyncRootProtos.SyncRoot.newBuilder();
            this.mSmsManager = new SmsManager(this.getApplicationContext());
            var5_11 = SmsProtos.MmsSms.newBuilder();
            var6_13 = this.mSmsManager.prepareAllSmsIds();
            var2_2 = 0;
            var3_15 = var6_13.size();
            this.mBackupManager.setCustomProgress(1, 0, var3_15);
            var6_13 = var6_13.iterator();
            while (var6_13.hasNext()) {
                var7_17 = (String)var6_13.next();
                try {
                    var7_17 = this.mSmsManager.load(Long.parseLong((String)var7_17));
                    if (var7_17 != null) {
                        var5_11.addSms((SmsProtos.Sms)var7_17);
                    }
                }
                catch (Exception var7_18) {
                    Log.e((String)"SmsController", (String)"Cannot load sms ", (Throwable)var7_18);
                }
                var7_17 = this.mBackupManager;
                var7_17.setCustomProgress(1, ++var2_2, var3_15);
            }
            var4_3.setMmsSms(var5_11.build());
            var5_11 = null;
            try {
                var1_1 = new FileOutputStream(var1_1.getFileDescriptor());
            }
            catch (Throwable var4_4) {
                var1_1 = var5_11;
                if (var1_1 == null) throw var4_5;
                var1_1.close();
                throw var4_5;
            }
            var4_3.build().writeTo((OutputStream)var1_1);
            if (var1_1 == null) return 0;
            {
                catch (Throwable var4_10) {}
            }
            var1_1.close();
            return 0;
lbl51: // 2 sources:
            while (var6_14.hasNext()) {
                var7_19 = (String)var6_14.next();
                try {
                    var7_19 = this.mMmsManager.backupToProtocolBuffer(Long.parseLong((String)var7_19));
                    if (var7_19 != null) {
                        var5_12.addPdus((MmsProtos.Pdu)var7_19);
                    }
                }
                catch (Exception var7_20) {
                    Log.e((String)"SmsController", (String)"Cannot load mms ", (Throwable)var7_20);
                }
                var7_19 = this.mBackupManager;
                var7_19.setCustomProgress(1, ++var2_2, var3_16);
            }
            var4_6.setMmsCollection(var5_12.build());
            var5_12 = null;
            var1_1 = new FileOutputStream(var1_1.getFileDescriptor());
            var4_6.build().writeTo((OutputStream)var1_1);
            if (var1_1 == null) break block17;
            var1_1.close();
        }
        var1_1 = this.mAttach2Uri.entrySet().iterator();
        while (var1_1.hasNext() != false) {
            var4_6 = (Map.Entry)var1_1.next();
            var5_12 = (String)var4_6.getKey();
            this.addAttachedFile((Uri)var4_6.getValue(), (String)var5_12);
        }
        return 0;
        catch (Throwable var4_7) {
            var1_1 = var5_12;
            ** GOTO lbl83
            catch (Throwable var4_9) {}
lbl83: // 2 sources:
            if (var1_1 == null) throw var4_8;
            var1_1.close();
            throw var4_8;
        }
    }

}

