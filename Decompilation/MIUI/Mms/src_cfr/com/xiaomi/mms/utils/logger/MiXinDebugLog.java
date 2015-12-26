/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Environment
 *  android.util.Log
 *  android.util.Pair
 *  com.google.code.microlog4android.Logger
 *  com.google.code.microlog4android.LoggerFactory
 *  com.google.code.microlog4android.appender.Appender
 *  com.google.code.microlog4android.appender.FileAppender
 *  com.google.code.microlog4android.config.PropertyConfigurator
 *  java.io.File
 *  java.io.PrintWriter
 *  java.io.StringWriter
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Date
 *  miui.os.Build
 */
package com.xiaomi.mms.utils.logger;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.appender.Appender;
import com.google.code.microlog4android.appender.FileAppender;
import com.google.code.microlog4android.config.PropertyConfigurator;
import com.xiaomi.mms.utils.logger.LoggerInterface;
import com.xiaomi.mms.utils.logger.SDCardUtils;
import com.xiaomi.mms.utils.logger.SerializedAsyncTaskProcessor;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import miui.os.Build;

public class MiXinDebugLog
implements LoggerInterface {
    private static int EFFECTIVE_DAY;
    public static String LOG_ROOT;
    private static SimpleDateFormat dateFormatter;
    private static List<Pair<String, Throwable>> logs;
    private static SerializedAsyncTaskProcessor mAsyncProcessor;
    private static SimpleDateFormat mFormatter;
    private static Logger sLogger;
    private Context mContext;
    private String mTag;

    static {
        dateFormatter = new SimpleDateFormat("MM-dd HH:mm:ss aaa");
        mFormatter = new SimpleDateFormat("yyyy-MM-dd");
        EFFECTIVE_DAY = 2;
        logs = Collections.synchronizedList((List)new ArrayList());
    }

    static /* synthetic */ List access$000() {
        return logs;
    }

    static /* synthetic */ Logger access$100() {
        return sLogger;
    }

    static /* synthetic */ String access$400(MiXinDebugLog miXinDebugLog) {
        return miXinDebugLog.mTag;
    }

    public void initialize(Context context, String string2, int n, String string3) {
        this.mContext = context.getApplicationContext();
        LOG_ROOT = string2;
        PropertyConfigurator.getConfigurator((Context)this.mContext).configure(2131165185);
        sLogger = LoggerFactory.getLogger((String)string3);
        mAsyncProcessor = new SerializedAsyncTaskProcessor(true);
        EFFECTIVE_DAY = n;
        this.mTag = string3;
    }

    @Override
    public void log(String string2) {
        this.log(string2, null);
    }

    @Override
    public void log(String string2, Throwable throwable) {
        if (mAsyncProcessor == null) {
            return;
        }
        logs.add((Pair)new Pair((Object)String.format((String)"%1$s %2$s", (Object[])new Object[]{dateFormatter.format(new Date()), string2}), (Object)throwable));
        mAsyncProcessor.addNewTask(new SerializedAsyncTaskProcessor.SerializedAsyncTask(){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            @Override
            public void process() {
                block12 : {
                    if (!SDCardUtils.isSDCardBusy() && !SDCardUtils.isSDCardFull() && !Build.IS_CTS_BUILD) break block12;
                    while (MiXinDebugLog.access$000().isEmpty() == false) {
                        var5_1 = (Pair)MiXinDebugLog.access$000().remove(0);
                        if (var5_1.second == null) {
                            MiXinDebugLog.access$100().info(var5_1.first, (Throwable)var5_1.second);
                            continue;
                        }
                        MiXinDebugLog.access$100().error(var5_1.first, (Throwable)var5_1.second);
                    }
                    return;
                }
                try {
                    block13 : {
                        var3_5 = System.currentTimeMillis();
                        var5_2 = new File[](Environment.getExternalStorageDirectory(), MiXinDebugLog.LOG_ROOT);
                        if (!var5_2.isDirectory()) ** GOTO lbl18
                        if ((var5_2 = var5_2.listFiles(new FilenameFilter(){

                            /*
                             * Enabled aggressive block sorting
                             * Enabled unnecessary exception pruning
                             * Enabled aggressive exception aggregation
                             * Lifted jumps to return sites
                             */
                            @Override
                            public boolean accept(File file, String string2) {
                                int n;
                                if (!string2.endsWith(".txt")) {
                                    return false;
                                }
                                try {}
                                catch (ParseException var1_2) {
                                    return false;
                                }
                                long l = var3_5;
                                long l2 = mFormatter.parse(string2.substring(0, string2.length() - 4)).getTime();
                                if (l - l2 <= 86400000 * (long)(n = EFFECTIVE_DAY)) return false;
                                return true;
                            }
                        })) == null) ** GOTO lbl19
                        ** GOTO lbl24
lbl18: // 1 sources:
                        var5_2.mkdirs();
lbl19: // 3 sources:
                        do {
                            break block13;
                            break;
                        } while (true);
                        catch (Exception var5_4) {
                            // empty catch block
                        }
                        return;
lbl24: // 1 sources:
                        var2_6 = var5_2.length;
                        var1_7 = 0;
                        do {
                            if (var1_7 >= var2_6) ** continue;
                            var5_2[var1_7].delete();
                            ++var1_7;
                        } while (true);
                    }
                    var5_2 = MiXinDebugLog.access$200().format(new Date(var3_5));
                    var5_2 = MiXinDebugLog.LOG_ROOT + (String)var5_2 + ".txt";
                    var6_8 = (FileAppender)MiXinDebugLog.access$100().getAppender(0);
                    var6_8.setFileName((String)var5_2);
                    var6_8.setAppend(true);
                    while (MiXinDebugLog.access$000().isEmpty() == false) {
                        var6_8 = (Pair)MiXinDebugLog.access$000().remove(0);
                        if (var6_8.second != null) {
                            var7_9 = (Throwable)var6_8.second;
                            var5_2 = new StringWriter();
                            var7_9.printStackTrace(new PrintWriter((Writer)var5_2));
                            var6_8 = new StringBuilder((String)var6_8.first);
                            var6_8.append("\n");
                            var6_8.append(var5_2.toString());
                            MiXinDebugLog.access$100().debug((Object)var6_8.toString());
                            continue;
                        }
                        MiXinDebugLog.access$100().debug(var6_8.first, (Throwable)var6_8.second);
                    }
                    return;
                }
                catch (Exception var5_3) {
                    Log.e((String)MiXinDebugLog.access$400(MiXinDebugLog.this), (String)null, (Throwable)var5_3);
                    return;
                }
            }

        });
    }

}

