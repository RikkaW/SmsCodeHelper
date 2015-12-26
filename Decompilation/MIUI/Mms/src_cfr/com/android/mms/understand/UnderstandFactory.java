/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.content.pm.ResolveInfo
 *  android.net.Uri
 *  android.text.SpannableString
 *  android.text.TextUtils
 *  android.util.Log
 *  android.widget.EditText
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.io.File
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashSet
 *  miui.os.Build
 */
package com.android.mms.understand;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.ui.ConversationBase;
import com.android.mms.ui.TextSizeAdjustSpan;
import com.android.mms.understand.TemplateUpdate;
import com.android.mms.understand.UnderstandAction;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.understand.UnderstandMessage;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.smsunderstand.AttrInfo;
import com.xiaomi.smsunderstand.CardIndex;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.OntologyResults;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import miui.os.Build;

public class UnderstandFactory {
    private static final int ROM_VERSION;
    private static Object sInitLockObj;
    private static boolean sInitialized;

    static {
        sInitialized = false;
        sInitLockObj = new Object();
        ROM_VERSION = Build.IS_STABLE_VERSION ? 4 : (Build.IS_DEVELOPMENT_VERSION ? 2 : 1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String convertValue(String string2, UnderstandMessage object, int n) {
        Object object2 = string2;
        if (TextUtils.isEmpty((CharSequence)string2)) return object2;
        object2 = string2;
        if (object == null) return object2;
        object2 = string2;
        if (string2.length() <= 2) return object2;
        object2 = string2;
        if (!string2.startsWith("#")) return object2;
        object2 = string2;
        if (!string2.endsWith("#")) return object2;
        int n2 = Integer.parseInt((String)string2.substring(1, string2.length() - 1));
        if (n2 == -1) {
            return "" + n + "";
        }
        object = object.mItems.iterator();
        do {
            object2 = string2;
            if (!object.hasNext()) return object2;
            object2 = (UnderstandMessage.Item)object.next();
        } while (object2.mType != n2);
        return object2.mValue;
    }

    public static void copyResults(UnderstandMessage understandMessage, OntologyResults iterator) {
        if (iterator != null && understandMessage != null) {
            understandMessage.mActionID = iterator.getActionID();
            understandMessage.mFrameName = iterator.getOntologyName();
            iterator = iterator.getAttrInfos();
            understandMessage.mItems = new ArrayList();
            iterator = iterator.iterator();
            while (iterator.hasNext()) {
                AttrInfo attrInfo = (AttrInfo)iterator.next();
                if (attrInfo == null) continue;
                UnderstandMessage.Item item = new UnderstandMessage.Item();
                item.mValue = attrInfo.getValue();
                item.mHas = attrInfo.getHas();
                item.mEndPosition = attrInfo.getEndPosition();
                item.mStartPosition = attrInfo.getStartPosition();
                item.mType = attrInfo.getType();
                understandMessage.mItems.add((Object)item);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Intent createIntent(UnderstandAction understandAction, int n) {
        if (!TextUtils.isEmpty((CharSequence)understandAction.mAction) && !TextUtils.isEmpty((CharSequence)understandAction.mPackageName)) {
            Intent intent = new Intent(understandAction.mAction);
            if (TextUtils.isEmpty((CharSequence)understandAction.mClassName)) {
                intent.setPackage(understandAction.mPackageName);
            } else {
                intent.setClassName(understandAction.mPackageName, understandAction.mClassName);
            }
            if (understandAction.mFlag != 0) {
                intent.addFlags(understandAction.mFlag);
            }
            String string2 = MSimUtils.SLOT_ID;
            if (!TextUtils.isEmpty((CharSequence)understandAction.mSlotIdName)) {
                string2 = understandAction.mSlotIdName;
            }
            intent.putExtra(string2, n);
            return intent;
        }
        return null;
    }

    private static Intent createMainIntent(Context object, String string2, int n, String string3) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            string2 = object.getPackageManager().getLaunchIntentForPackage(string2);
            if (string2 != null) {
                object = MSimUtils.SLOT_ID;
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    object = string3;
                }
                string2.putExtra((String)object, n);
            }
            return string2;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void doAction(Context context, List<UnderstandAction> list, UnderstandMessage understandMessage, int n) {
        if (list == null) return;
        {
            int n2 = 0;
            int n3 = list.size() > 5 ? 5 : list.size();
            block17 : for (int i = 0; i < n3 && n2 == 0; ++i) {
                UnderstandAction understandAction = list.get(i);
                if (understandAction == null) continue;
                switch (understandAction.mCallType) {
                    String string2;
                    default: {
                        Log.e((String)"UnderstandFactory", (String)(" unknown type id configured: " + understandAction.mCallType));
                        return;
                    }
                    case 1: {
                        string2 = UnderstandFactory.prepareIntent(understandAction, understandMessage, n);
                        if (string2 != null && context.getPackageManager().resolveActivity((Intent)string2, 0) != null) {
                            try {
                                context.startActivity((Intent)string2);
                                n2 = 1;
                                continue block17;
                            }
                            catch (Exception var7_8) {
                                var7_8.printStackTrace();
                            }
                            continue block17;
                        }
                        string2 = UnderstandFactory.prepareMainIntent(context, understandAction, understandMessage, n);
                        if (string2 != null) {
                            try {
                                context.startActivity((Intent)string2);
                                n2 = 1;
                                continue block17;
                            }
                            catch (Exception var7_9) {
                                var7_9.printStackTrace();
                            }
                            continue block17;
                        }
                        if (!TextUtils.isEmpty((CharSequence)understandAction.mFallback)) {
                            Log.v((String)"UnderstandFactory", (String)" Trying to fallback");
                            string2 = Uri.parse((String)UnderstandFactory.findUriValue(understandAction.mFallback, understandMessage));
                            try {
                                context.startActivity(new Intent("android.intent.action.VIEW", (Uri)string2));
                                n2 = 1;
                                continue block17;
                            }
                            catch (Exception var7_10) {
                                var7_10.printStackTrace();
                            }
                            continue block17;
                        }
                        Log.w((String)"UnderstandFactory", (String)" no action!");
                        n2 = 0;
                        continue block17;
                    }
                    case 2: {
                        string2 = UnderstandFactory.prepareIntent(understandAction, understandMessage, n);
                        if (string2 != null) {
                            try {
                                context.startService((Intent)string2);
                                n2 = 1;
                                continue block17;
                            }
                            catch (Exception var7_11) {
                                var7_11.printStackTrace();
                            }
                            continue block17;
                        }
                        n2 = 0;
                        continue block17;
                    }
                    case 3: {
                        string2 = ((UnderstandMessage.Item)understandMessage.mItems.get((int)0)).mValue;
                        ((ClipboardManager)context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)null, (CharSequence)string2));
                        Toast.makeText((Context)context, (CharSequence)context.getString(2131361821), (int)0).show();
                        n2 = 1;
                        continue block17;
                    }
                    case 4: {
                        if (context instanceof ConversationBase) {
                            ((ConversationBase)context).getTextEditor().setText((CharSequence)((UnderstandMessage.Item)understandMessage.mItems.get((int)0)).mValue);
                            n2 = ((UnderstandMessage.Item)understandMessage.mItems.get((int)0)).mValue == null ? 0 : ((UnderstandMessage.Item)understandMessage.mItems.get((int)0)).mValue.length();
                            ((ConversationBase)context).getTextEditor().setSelection(n2);
                        }
                        n2 = 1;
                        continue block17;
                    }
                    case 5: {
                        String string3;
                        string2 = string3 = "";
                        if (understandAction.mUriBase != null) {
                            string2 = string3;
                            if (understandAction.mUriBase.length() > 0) {
                                string2 = UnderstandFactory.findUriValue(understandAction.mUriBase, understandMessage);
                            }
                        }
                        if (TextUtils.isEmpty((CharSequence)string2)) {
                            Log.e((String)"UnderstandFactory", (String)" empty uri got");
                            return;
                        }
                        string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)string2));
                        string2.putExtra("com.android.browser.application_id", context.getPackageName());
                        string2.setFlags(524288);
                        try {
                            context.startActivity((Intent)string2);
                            n2 = 1;
                            continue block17;
                        }
                        catch (Exception var7_12) {
                            var7_12.printStackTrace();
                        }
                    }
                }
            }
            if (n2 != 0) return;
            {
                Toast.makeText((Context)context, (CharSequence)context.getString(2131362136), (int)0).show();
                return;
            }
        }
    }

    public static String findUriValue(String string2, UnderstandMessage object) {
        if (!TextUtils.isEmpty((CharSequence)string2) && object != null && string2.length() > 2 && string2.startsWith("#") && string2.endsWith("#")) {
            int n = Integer.parseInt((String)string2.substring(1, string2.length() - 1));
            for (UnderstandMessage.Item item : object.mItems) {
                if (item.mType != n) continue;
                return item.mValue;
            }
        }
        Log.w((String)"UnderstandFactory", (String)"Can't find matched value, fallback to original string.");
        return string2;
    }

    public static void freeAllResourcesForResident() {
        if (!UnderstandFactory.isInitialized()) {
            Log.e((String)"UnderstandFactory", (String)"not init understand");
            return;
        }
        try {
            SMSUnderstand.freeAllResource();
        }
        catch (Exception var0) {
            var0.printStackTrace();
            return;
        }
        UnderstandFactory.setInitialized(false);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void freeResourceForResident(String string2, String string3) {
        if (!UnderstandFactory.isInitialized()) {
            Log.e((String)"UnderstandFactory", (String)"not init understand");
            return;
        }
        if (TextUtils.isEmpty((CharSequence)string2)) return;
        try {
            SMSUnderstand.freeResource(string2, string3);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<String> getButtonActions(int n, int n2) {
        int n3;
        ArrayList arrayList = null;
        if (!UnderstandFactory.isInitialized()) {
            Log.w((String)"UnderstandFactory", (String)"not init understand");
            return arrayList;
        }
        ArrayList arrayList2 = arrayList;
        if (n < 0) return arrayList2;
        int n4 = 0;
        try {
            n4 = n3 = SMSUnderstand.getActionCount(n, n2);
        }
        catch (Exception var4_4) {
            var4_4.printStackTrace();
        }
        arrayList2 = arrayList;
        if (n4 <= 0) return arrayList2;
        arrayList = new ArrayList();
        n3 = 0;
        do {
            arrayList2 = arrayList;
            if (n3 >= n4) return arrayList2;
            try {
                arrayList.add(SMSUnderstand.getBtnAction(n, n2, n3));
            }
            catch (Exception var4_5) {
                var4_5.printStackTrace();
            }
            ++n3;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getButtonName(int n, int n2) {
        if (!UnderstandFactory.isInitialized()) {
            Log.w((String)"UnderstandFactory", (String)"not init understand");
            return "";
        }
        if (n < 0) return "";
        try {
            return SMSUnderstand.getBtnTitle(n, n2);
        }
        catch (Exception var2_3) {
            var2_3.printStackTrace();
            return "";
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getButtonNumber(int n) {
        if (!UnderstandFactory.isInitialized()) {
            Log.w((String)"UnderstandFactory", (String)"not init understand");
            return 0;
        }
        if (n < 0) return 0;
        try {
            return SMSUnderstand.getBtnNumber(n);
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
            return 0;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static SpannableString getTextWithUnderstand(Context context, String string2, List<UnderstandMessage> iterator, int n, int n2, String object) {
        int n3 = 0;
        Object object2 = (UnderstandMessage.Item)((UnderstandMessage)iterator.get((int)0)).mItems.get(0);
        int n4 = 0;
        String string3 = string2;
        if (object2 != null) {
            int n5 = object2.mStartPosition;
            int n6 = object2.mEndPosition;
            n4 = n6 - n2;
            if (n6 > n2 && n6 > n5 && n4 < n5) {
                string3 = (String)object + string2.substring(object.length() + n4, string2.length());
            } else {
                n4 = 0;
                string3 = string2;
            }
        }
        string2 = new SpannableString((CharSequence)string3);
        iterator = iterator.iterator();
        n2 = n3;
        block0 : while (iterator.hasNext()) {
            object2 = ((UnderstandMessage)iterator.next()).mItems.iterator();
            do {
                if (!object2.hasNext()) continue block0;
                object = (UnderstandMessage.Item)object2.next();
            } while (object.mHas <= 0 || object.mEndPosition <= object.mStartPosition || object.mEndPosition >= string3.length());
            object2 = new TextSizeAdjustSpan(context, n);
            object2.needUnderline();
            string2.setSpan(object2, object.mStartPosition - n4, object.mEndPosition - n4, 33);
            n2 = 1;
        }
        return string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<UnderstandMessage> getUnderstandMessageList(String object, String string2, String object2, long l) {
        String string3 = null;
        if (!UnderstandFactory.isInitialized()) {
            Log.w((String)"UnderstandFactory", (String)"not init understand");
            return string3;
        }
        Object object3 = string3;
        if (TextUtils.isEmpty((CharSequence)object)) return object3;
        object3 = string3;
        if (TextUtils.isEmpty((CharSequence)object2)) return object3;
        object = new SMSUnderstand((String)object, string2);
        if (l > 0) {
            object = object.understand((String)object2, l);
            while (object != null && object.size() > 0) {
                Log.v((String)"UnderstandFactory", (String)(" OntologyResults size are " + object.size()));
                string2 = new ArrayList();
                object = object.iterator();
                do {
                    object3 = string2;
                    if (!object.hasNext()) return object3;
                    object2 = (OntologyResults)object.next();
                    object3 = new UnderstandMessage();
                    UnderstandFactory.copyResults((UnderstandMessage)object3, (OntologyResults)object2);
                    string2.add(object3);
                } while (true);
            }
        } else {
            try {
                object = object.understand((String)object2);
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                return null;
            }
        }
        Log.v((String)"UnderstandFactory", (String)" no OntologyResults");
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static List<UnderstandMessage> getUnderstandMessageList(String var0, String var1_2, String var2_3, long var3_4, int var5_5) {
        if (!UnderstandFactory.isInitialized()) {
            Log.w((String)"UnderstandFactory", (String)"not init understand");
            return null;
        }
        if (TextUtils.isEmpty((CharSequence)var0) != false) return null;
        if (TextUtils.isEmpty(var2_3) != false) return null;
        var6_6 = new HashSet();
        if ((var5_5 & 1) != 0) {
            var6_6.add(EntityType.TIME);
        }
        if ((var5_5 & 2) != 0) {
            var6_6.add(EntityType.VERIFICATIONCODE);
        }
        var0 = new SMSUnderstand((String)var0, var1_2, (Set<EntityType>)var6_6);
        if (var3_4 <= 0) ** GOTO lbl16
        try {
            var0 = var0.understand((String)var2_3, var3_4);
            ** GOTO lbl17
lbl16: // 1 sources:
            var0 = var0.understand((String)var2_3);
lbl17: // 2 sources:
            if (var0 == null) return null;
            if (var0.size() <= 0) return null;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        Log.v((String)"UnderstandFactory", (String)("mask: " + var5_5 + " OntologyResults size are " + var0.size()));
        var1_2 = new ArrayList();
        var2_3 = var0.iterator();
        do {
            var0 = var1_2;
            if (var2_3.hasNext() == false) return var0;
            var0 = var2_3.next();
            var6_6 = new UnderstandMessage();
            UnderstandFactory.copyResults((UnderstandMessage)var6_6, (OntologyResults)var0);
            var1_2.add(var6_6);
        } while (true);
    }

    /*
     * Exception decompiling
     */
    public static long getVersion() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [9[TRYBLOCK]], but top level block is 21[CATCHBLOCK]
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

    public static void initUnderstand() {
        SMSUnderstand.setPlatform(ROM_VERSION);
        try {
            if (!SMSUnderstand.initial("/data/data/com.android.mms/app_understand/smsUnderstand.config")) {
                Log.e((String)"UnderstandFactory", (String)"init understand failed");
                UnderstandFactory.setInitialized(false);
                return;
            }
        }
        catch (Exception var0) {
            var0.printStackTrace();
            return;
        }
        Log.v((String)"UnderstandFactory", (String)" understand initial done!");
        UnderstandFactory.setInitialized(true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void initUnderstandFiles() {
        try {
            Log.v((String)"UnderstandFactory", (String)"init understand files");
            File file = new File(TemplateUpdate.getVersionFile());
            boolean bl = false;
            if (file.exists()) {
                long l = UnderstandFactory.getVersion();
                if (!Build.IS_INTERNATIONAL_BUILD) {
                    Log.d((String)"UnderstandFactory", (String)("current using version is " + l + ", latest version is: " + 17));
                    if (l >= 17) {
                        MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_version", Long.toString((long)l));
                        return;
                    }
                    MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_version", Long.toString((long)l));
                    boolean bl2 = true;
                    file = new File(TemplateUpdate.getUpdatedFile());
                    bl = bl2;
                    if (file.exists()) {
                        file.delete();
                        bl = bl2;
                    }
                } else {
                    Log.d((String)"UnderstandFactory", (String)("current using version is " + l + ", latest version is: " + 1));
                    if (l == 1) return;
                    boolean bl3 = true;
                    file = new File(TemplateUpdate.getUpdatedFile());
                    bl = bl3;
                    if (file.exists()) {
                        file.delete();
                        bl = bl3;
                    }
                }
            }
            UnderstandFactory.initUnderstandFiles("/data/data/com.android.mms/app_understand");
            if (bl) {
                UnderstandLoader.update();
            }
            if (new File(TemplateUpdate.getVersionFile()).exists()) return;
            UnderstandFactory.updateVersionFile(0);
            return;
        }
        catch (Exception var4_1) {
            Log.e((String)"UnderstandFactory", (String)(" Copy files failed! " + (Object)((Object)var4_1)));
            return;
        }
    }

    /*
     * Exception decompiling
     */
    private static void initUnderstandFiles(String var0) throws IOException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 4 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
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
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean isInitialized() {
        Object object = sInitLockObj;
        synchronized (object) {
            return sInitialized;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean loadResourceForResident(String string2, String string3) {
        if (!UnderstandFactory.isInitialized()) {
            Log.w((String)"UnderstandFactory", (String)"not init understand");
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)string2)) return false;
        try {
            return SMSUnderstand.loadResourceForResident(string2, string3);
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }

    public static UnderstandAction parseActionString(String string2) {
        UnderstandAction understandAction;
        if (!TextUtils.isEmpty((CharSequence)string2) && (understandAction = new UnderstandAction()).parseActionString(string2)) {
            return understandAction;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void prepareDataIntent(Intent intent, UnderstandAction understandAction, UnderstandMessage understandMessage) {
        if (understandAction.mUriBase != null && understandAction.mUriBase.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(understandAction.mUriBase);
            Object object = UnderstandFactory.prepareUrlParam(understandAction, understandMessage);
            boolean bl = false;
            if (!TextUtils.isEmpty((CharSequence)object)) {
                stringBuilder.append("?").append((String)object);
                bl = true;
            }
            if (understandAction.mParamCount > 0 && (object = understandAction.mParams) != null) {
                boolean bl2 = !bl;
                for (int i = 0; i < understandAction.mParamCount; ++i) {
                    boolean bl3;
                    UnderstandAction.Params params = (UnderstandAction.Params)object.get(i);
                    if (bl && "url".equals((Object)params.mName)) {
                        bl3 = bl2;
                    } else {
                        String string2 = UnderstandFactory.findUriValue(params.mValue, understandMessage);
                        bl3 = bl2;
                        if (!TextUtils.isEmpty((CharSequence)string2)) {
                            if (bl2) {
                                stringBuilder.append('?');
                                bl2 = false;
                            } else {
                                stringBuilder.append('&');
                            }
                            stringBuilder.append(params.mName).append('=').append(string2);
                            bl3 = bl2;
                        }
                    }
                    bl2 = bl3;
                }
            }
            intent.setData(Uri.parse((String)stringBuilder.toString()));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Intent prepareIntent(UnderstandAction iterator, UnderstandMessage understandMessage, int n) {
        Intent intent;
        int n2;
        if (iterator == null) return null;
        int n3 = n2 = iterator.mSlotId;
        if (n2 < 0) {
            n3 = n;
        }
        Object object = intent = UnderstandFactory.createIntent(iterator, n3);
        if (intent == null) return object;
        UnderstandFactory.prepareDataIntent(intent, iterator, understandMessage);
        object = intent;
        if (iterator.mCount <= 0) return object;
        object = intent;
        if (iterator.mLists == null) return object;
        object = intent;
        if (iterator.mLists.size() <= 0) return object;
        iterator = iterator.mLists.iterator();
        do {
            object = intent;
            if (!iterator.hasNext()) return object;
            object = iterator.next();
            intent.putExtra(object.mName, UnderstandFactory.convertValue(object.mValue, understandMessage, n));
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Intent prepareMainIntent(Context object, UnderstandAction iterator, UnderstandMessage understandMessage, int n) {
        int n2;
        Intent intent;
        if (iterator == null) return null;
        if (!iterator.mEnterMain) return null;
        int n3 = n2 = iterator.mSlotId;
        if (n2 < 0) {
            n3 = n;
        }
        object = intent = UnderstandFactory.createMainIntent((Context)object, iterator.mPackageName, n3, iterator.mSlotIdName);
        if (intent == null) return object;
        UnderstandFactory.prepareDataIntent(intent, iterator, understandMessage);
        object = intent;
        if (iterator.mCount <= 0) return object;
        object = intent;
        if (iterator.mLists == null) return object;
        object = intent;
        if (iterator.mLists.size() <= 0) return object;
        iterator = iterator.mLists.iterator();
        do {
            object = intent;
            if (!iterator.hasNext()) return object;
            object = iterator.next();
            intent.putExtra(object.mName, UnderstandFactory.convertValue(object.mValue, understandMessage, n));
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String prepareUrlParam(UnderstandAction understandAction, UnderstandMessage understandMessage) {
        List<String> list;
        if (understandAction.mUrlPartCount <= 0 || (list = understandAction.mUrlParts) == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("url=");
        int n = 0;
        while (n < understandAction.mUrlPartCount) {
            String string2 = list.get(n);
            if (string2 != null && string2.startsWith("#")) {
                stringBuilder.append(UnderstandFactory.findUriValue(string2, understandMessage));
            } else {
                stringBuilder.append(string2);
            }
            ++n;
        }
        return stringBuilder.toString();
    }

    public static void reStartInitUnderstand() {
        UnderstandFactory.setInitialized(false);
        SMSUnderstand.freeOntology();
        SMSUnderstand.setPlatform(ROM_VERSION);
        try {
            if (!SMSUnderstand.initial("/data/data/com.android.mms/app_understand/smsUnderstand.config")) {
                Log.e((String)"UnderstandFactory", (String)"restart understand failed");
                return;
            }
        }
        catch (Exception var0) {
            var0.printStackTrace();
            return;
        }
        UnderstandFactory.setInitialized(true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setInitialized(boolean bl) {
        Object object = sInitLockObj;
        synchronized (object) {
            sInitialized = bl;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setLocalHostNumber(String string2, int n) {
        CardIndex cardIndex = n == 0 ? CardIndex.CARD1 : CardIndex.CARD2;
        SMSUnderstand.setLocalHostPhoneNumber(string2, cardIndex);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setLocalHostPlace(String string2, int n) {
        CardIndex cardIndex = n == 0 ? CardIndex.CARD1 : CardIndex.CARD2;
        SMSUnderstand.setLocalHostPlace(string2, cardIndex);
    }

    private static void showTextWithHighlight(TextView textView, SpannableString spannableString, String string2, String arrstring, int n) {
        if (!TextUtils.isEmpty((CharSequence)arrstring)) {
            string2 = string2.toLowerCase();
            for (String string3 : arrstring.toLowerCase().split(" ")) {
                int n2 = - string3.length();
                while ((n2 = string2.indexOf(string3, string3.length() + n2)) != -1) {
                    spannableString.setSpan((Object)new TextSizeAdjustSpan(textView.getContext(), n), n2, string3.length() + n2, 33);
                }
            }
        }
    }

    public static void showTextWithUnderstand(TextView textView, String string2, List<UnderstandMessage> object, String string3, int n, int n2) {
        boolean bl = false;
        SpannableString spannableString = new SpannableString((CharSequence)string2);
        object = object.iterator();
        block0 : while (object.hasNext()) {
            Iterator iterator = ((UnderstandMessage)object.next()).mItems.iterator();
            boolean bl2 = bl;
            do {
                bl = bl2;
                if (!iterator.hasNext()) continue block0;
                UnderstandMessage.Item item = (UnderstandMessage.Item)iterator.next();
                if (item.mHas <= 0 || item.mEndPosition <= item.mStartPosition || item.mEndPosition >= string2.length()) continue;
                TextSizeAdjustSpan textSizeAdjustSpan = new TextSizeAdjustSpan(textView.getContext(), n);
                textSizeAdjustSpan.needUnderline();
                spannableString.setSpan((Object)textSizeAdjustSpan, item.mStartPosition, item.mEndPosition, 33);
                bl2 = true;
            } while (true);
        }
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            UnderstandFactory.showTextWithHighlight(textView, spannableString, string2, string3, n2);
            bl = true;
        }
        if (bl) {
            textView.setText((CharSequence)spannableString);
            return;
        }
        textView.setText((CharSequence)string2);
    }

    /*
     * Exception decompiling
     */
    public static boolean unzipFiles() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl45 : TryStatement: try { 7[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
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

    public static void updateVersion(long l) {
        UnderstandFactory.updateVersionFile(l);
        MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_version", Long.toString((long)l));
    }

    /*
     * Exception decompiling
     */
    public static void updateVersionFile(long var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 3 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
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
}

