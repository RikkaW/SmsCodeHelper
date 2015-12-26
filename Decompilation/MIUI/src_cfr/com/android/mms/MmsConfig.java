/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 *  miui.os.Build
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package com.android.mms;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MmsConfig {
    private static boolean mAliasEnabled;
    private static int mAliasRuleMaxChars;
    private static int mAliasRuleMinChars;
    private static boolean mAllowAttachAudio;
    private static int mDefaultMMSMessagesPerThread;
    private static int mDefaultSMSMessagesPerThread;
    private static String mEmailGateway;
    private static boolean mEnableMMSDeliveryReports;
    private static boolean mEnableMMSReadReports;
    private static boolean mEnableMultipartSMS;
    private static boolean mEnableSMSDeliveryReports;
    private static boolean mEnableSlideDuration;
    private static String mHttpParams;
    private static String mHttpParamsLine1Key;
    private static int mHttpSocketTimeout;
    private static String mLogPath;
    private static int mMaxImageHeight;
    private static int mMaxImageWidth;
    private static int mMaxMessageCountPerThread;
    private static int mMaxMessageSize;
    private static int mMaxSizeScaleForPendingMmsAllowed;
    private static int mMaxSubjectLength;
    private static int mMaxTextLength;
    private static int mMinMessageCountPerThread;
    private static int mMinimumSlideElementDuration;
    private static int mMmsEnabled;
    private static boolean mNotifyWapMMSC;
    private static int mRecipientLimit;
    private static int mSmsToMmsTextThreshold;
    private static boolean mTransIdEnabled;
    private static String mUaProfTagName;
    private static String mUaProfUrl;
    private static String mUserAgent;

    static {
        mTransIdEnabled = false;
        mMmsEnabled = 1;
        mMaxMessageSize = 307200;
        mUserAgent = "Android-Mms/2.0";
        mUaProfTagName = "x-wap-profile";
        mLogPath = "/MIUI/debug_log/Mms/";
        mUaProfUrl = null;
        mHttpParams = null;
        mHttpParamsLine1Key = null;
        mEmailGateway = null;
        mMaxImageHeight = 1920;
        mMaxImageWidth = 1920;
        mRecipientLimit = Integer.MAX_VALUE;
        mDefaultSMSMessagesPerThread = 500;
        mDefaultMMSMessagesPerThread = 50;
        mMinMessageCountPerThread = 10;
        mMaxMessageCountPerThread = 5000;
        mHttpSocketTimeout = 60000;
        mMinimumSlideElementDuration = 7;
        mNotifyWapMMSC = false;
        mAllowAttachAudio = true;
        mSmsToMmsTextThreshold = 9;
        mEnableMultipartSMS = false;
        mEnableSlideDuration = true;
        mEnableMMSReadReports = true;
        mEnableSMSDeliveryReports = true;
        mEnableMMSDeliveryReports = true;
        mMaxTextLength = -1;
        mMaxSizeScaleForPendingMmsAllowed = 4;
        mAliasEnabled = false;
        mAliasRuleMinChars = 2;
        mAliasRuleMaxChars = 48;
        mMaxSubjectLength = 40;
    }

    public static final void beginDocument(XmlPullParser xmlPullParser, String string) throws XmlPullParserException, IOException {
        int n;
        while ((n = xmlPullParser.next()) != 2 && n != 1) {
        }
        if (n != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        if (!xmlPullParser.getName().equals((Object)string)) {
            throw new XmlPullParserException("Unexpected start tag: found " + xmlPullParser.getName() + ", expected " + string);
        }
    }

    public static int getAliasMaxChars() {
        return mAliasRuleMaxChars;
    }

    public static int getAliasMinChars() {
        return mAliasRuleMinChars;
    }

    public static boolean getAllowAttachAudio() {
        return mAllowAttachAudio;
    }

    private static String getDeviceName() {
        if ("2014817".equalsIgnoreCase(miui.os.Build.MODEL)) {
            return " Redmi2";
        }
        return miui.os.Build.MODEL;
    }

    public static String getEmailGateway() {
        return mEmailGateway;
    }

    public static String getHttpParams() {
        return mHttpParams;
    }

    public static String getHttpParamsLine1Key() {
        return mHttpParamsLine1Key;
    }

    public static int getHttpSocketTimeout() {
        return mHttpSocketTimeout;
    }

    private static String getLocalString() {
        Object object = Locale.getDefault();
        String string = object.getLanguage().toLowerCase();
        String string2 = object.getCountry().toLowerCase();
        object = object.getVariant().toLowerCase();
        if (string.length() == 0 && string2.length() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(11);
        stringBuilder.append(string);
        if (string2.length() > 0 || object.length() > 0) {
            stringBuilder.append('-');
        }
        stringBuilder.append(string2);
        if (object.length() > 0) {
            stringBuilder.append('-');
        }
        stringBuilder.append((String)object);
        return stringBuilder.toString();
    }

    public static String getLogPath() {
        return mLogPath;
    }

    public static int getMaxImageHeight() {
        return mMaxImageHeight;
    }

    public static int getMaxImageWidth() {
        return mMaxImageWidth;
    }

    public static int getMaxMessageSize() {
        Log.v((String)"MmsConfig", (String)("MmsConfig.getMaxMessageSize(): " + mMaxMessageSize));
        return mMaxMessageSize;
    }

    public static int getMaxSizeScaleForPendingMmsAllowed() {
        return mMaxSizeScaleForPendingMmsAllowed;
    }

    public static int getMaxTextLimit() {
        if (mMaxTextLength > -1) {
            return mMaxTextLength;
        }
        return Integer.MAX_VALUE;
    }

    public static int getMinimumSlideElementDuration() {
        return mMinimumSlideElementDuration;
    }

    public static boolean getMmsEnabled() {
        if (mMmsEnabled == 1) {
            return true;
        }
        return false;
    }

    public static boolean getMultipartSmsEnabled() {
        return mEnableMultipartSMS;
    }

    public static boolean getNotifyWapMMSC() {
        return mNotifyWapMMSC;
    }

    public static int getRecipientLimit() {
        return mRecipientLimit;
    }

    public static boolean getSlideDurationEnabled() {
        return mEnableSlideDuration;
    }

    public static int getSmsToMmsTextThreshold() {
        return mSmsToMmsTextThreshold;
    }

    public static boolean getTransIdEnabled() {
        return mTransIdEnabled;
    }

    public static String getUaProfTagName() {
        return mUaProfTagName;
    }

    public static String getUaProfUrl() {
        return mUaProfUrl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String getUserAgent() {
        String string = miui.os.Build.checkRegion((String)"SG") ? MmsConfig.getDeviceName() + " " + mUserAgent + " (Linux; U; Android " + Build.VERSION.RELEASE + "; " + MmsConfig.getLocalString() + "; " + miui.os.Build.BRAND + " " + MmsConfig.getDeviceName() + " Build/" + miui.os.Build.ID + ")" : mUserAgent + " (Linux; U; Android " + Build.VERSION.RELEASE + "; " + MmsConfig.getLocalString() + "; " + miui.os.Build.BRAND + " " + MmsConfig.getDeviceName() + " Build/" + miui.os.Build.ID + ")";
        Log.v((String)"MmsConfig", (String)string);
        return string;
    }

    public static void init(Context context) {
        Log.v((String)"MmsConfig", (String)"MmsConfig.init()");
        MmsConfig.loadMmsSettings(context);
    }

    public static boolean isAliasEnabled() {
        return mAliasEnabled;
    }

    /*
     * Exception decompiling
     */
    private static void loadMmsSettings(Context var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [3] lbl39 : TryStatement: try { 4[TRYBLOCK]

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

    public static final void nextElement(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int n;
        while ((n = xmlPullParser.next()) != 2 && n != 1) {
        }
    }
}

