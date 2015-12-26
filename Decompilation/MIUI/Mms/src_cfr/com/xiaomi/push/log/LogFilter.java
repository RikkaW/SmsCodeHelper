/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.io.BufferedReader
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.InputStreamReader
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Date
 *  java.util.Locale
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.xiaomi.push.log;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.util.SystemUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LogFilter {
    private static String MIPUSH_LOG_PATH = "/MiPushLog";
    @SuppressLint(value={"SimpleDateFormat"})
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int mCurrentLen;
    private String mEndTime;
    private ArrayList<File> mFiles = new ArrayList();
    private String mFromTime;
    private int mMaxLen = 2097152;
    private boolean mStartFound;

    LogFilter() {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void doFilter(BufferedReader var1_1, BufferedWriter var2_2, Pattern var3_3) throws IOException {
        var11_4 = new char[4096];
        var6_5 = 0;
        var4_6 = var1_1.read(var11_4);
        while (var4_6 != -1) {
            block5 : {
                if (var6_5 == true) return;
                var12_12 = new String(var11_4, 0, var4_6);
                var13_13 = var3_3.matcher((CharSequence)var12_12);
                var5_7 = 0;
                var8_9 = 0;
                var7_8 = var4_6;
                do {
                    var10_11 = var7_8;
                    var9_10 = var6_5;
                    if (var5_7 >= var4_6) ** GOTO lbl36
                    var10_11 = var7_8;
                    var9_10 = var6_5;
                    if (!var13_13.find(var5_7)) ** GOTO lbl36
                    var5_7 = var13_13.start();
                    var14_14 = var12_12.substring(var5_7, this.mFromTime.length() + var5_7);
                    if (this.mStartFound) ** GOTO lbl26
                    var9_10 = var8_9;
                    if (var14_14.compareTo(this.mFromTime) >= 0) {
                        var9_10 = var5_7;
                        this.mStartFound = true;
                    }
                    ** GOTO lbl31
lbl26: // 1 sources:
                    var9_10 = var8_9;
                    if (var14_14.compareTo(this.mEndTime) <= 0) ** GOTO lbl31
                    var9_10 = 1;
                    var10_11 = var5_7;
                    ** GOTO lbl36
lbl31: // 2 sources:
                    if ((var8_9 = var12_12.indexOf(10, var5_7)) != -1) {
                        var5_7 += var8_9;
                        var8_9 = var9_10;
                        continue;
                    }
                    ** GOTO lbl43
lbl36: // 3 sources:
                    if (this.mStartFound) {
                        var4_6 = var10_11 - var8_9;
                        this.mCurrentLen += var4_6;
                        if (var9_10 == 0) break;
                        var2_2.write(var11_4, var8_9, var4_6);
                        return;
                    }
                    break block5;
lbl43: // 1 sources:
                    var5_7 += this.mFromTime.length();
                    var8_9 = var9_10;
                } while (true);
                var2_2.write(var11_4, var8_9, var4_6);
                if (this.mCurrentLen > this.mMaxLen) return;
            }
            var4_6 = var1_1.read(var11_4);
            var6_5 = var9_10;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void filter2File(File var1_1) {
        var12_3 = Pattern.compile((String)"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        var10_4 = null;
        var2_5 = null;
        var6_7 = null;
        var7_8 = null;
        var8_9 = null;
        var9_10 = null;
        var11_11 = null;
        var3_12 = null;
        var4_13 = null;
        var1_1 = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)new FileOutputStream((File)var1_1)));
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var3_12 = new StringBuilder();
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var3_12.append("model :").append(Build.MODEL);
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var3_12.append("; os :").append(Build.VERSION.INCREMENTAL);
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var3_12.append("; uid :").append(SystemUtils.getDeviceUUID());
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var3_12.append("; lng :").append(Locale.getDefault().toString());
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var3_12.append("; sdk :").append(7);
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var3_12.append("\n");
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var1_1.write(var3_12.toString());
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        this.mCurrentLen = 0;
        var2_5 = var6_7;
        var4_13 = var7_8;
        var5_16 = var8_9;
        var6_7 = this.mFiles.iterator();
        var2_5 = null;
        ** GOTO lbl63
        catch (FileNotFoundException var5_17) {
            var1_1 = var9_10;
            ** GOTO lbl116
            catch (IOException var5_18) {
                block17 : {
                    block16 : {
                        var4_13 = var11_11;
                        var1_1 = var10_4;
                        ** GOTO lbl96
lbl63: // 1 sources:
                        do {
                            if (!var6_7.hasNext()) break;
                            var2_5 = var3_12 = new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream((File)var6_7.next())));
                            var4_13 = var3_12;
                            var5_16 = var3_12;
                            this.doFilter((BufferedReader)var3_12, (BufferedWriter)var1_1, var12_3);
                            var2_5 = var3_12;
                            var4_13 = var3_12;
                            var5_16 = var3_12;
                            var3_12.close();
                            var2_5 = var3_12;
                            continue;
                            break;
                        } while (true);
                        IOUtils.closeQuietly((Writer)var1_1);
                        IOUtils.closeQuietly((Reader)var2_5);
                        return;
                        catch (Throwable var4_14) {
                            var3_12 = var1_1;
                            var1_1 = var4_14;
                            ** GOTO lbl-1000
                        }
                        catch (Throwable var4_15) {
                            var3_12 = var1_1;
                            var1_1 = var4_15;
                            ** GOTO lbl-1000
                        }
                        catch (IOException var5_20) {
                            var2_5 = var1_1;
                            var1_1 = var4_13;
                            var4_13 = var2_5;
                            break block16;
                        }
                        catch (IOException var5_21) {
                            var4_13 = var1_1;
                            var1_1 = var2_5;
                        }
                    }
                    var2_5 = var1_1;
                    var3_12 = var4_13;
                    try {
                        MyLog.v("LOG: filter error = " + var5_19.getMessage());
                    }
                    catch (Throwable var1_2) lbl-1000: // 3 sources:
                    {
                        IOUtils.closeQuietly((Writer)var3_12);
                        IOUtils.closeQuietly((Reader)var2_5);
                        throw var1_1;
                    }
                    IOUtils.closeQuietly((Writer)var4_13);
                    IOUtils.closeQuietly((Reader)var1_1);
                    return;
                    catch (FileNotFoundException var2_6) {
                        var4_13 = var1_1;
                        var1_1 = var5_16;
                        var5_16 = var2_6;
                        break block17;
                    }
                    catch (FileNotFoundException var5_22) {
                        var4_13 = var1_1;
                        var1_1 = var2_5;
                    }
                }
                var2_5 = var1_1;
                var3_12 = var4_13;
                MyLog.v("LOG: filter error = " + var5_16.getMessage());
                IOUtils.closeQuietly((Writer)var4_13);
                IOUtils.closeQuietly((Reader)var1_1);
                return;
            }
        }
    }

    LogFilter addFile(File file) {
        if (file.exists()) {
            this.mFiles.add((Object)file);
        }
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    File filter(Context context, Date date, Date date2, File file) {
        if ("com.xiaomi.xmsf".equalsIgnoreCase(context.getPackageName())) {
            context = context.getFilesDir();
            this.addFile(new File((File)context, "xmsf.log.1"));
            this.addFile(new File((File)context, "xmsf.log"));
        } else {
            context = new File((Object)context.getExternalFilesDir(null) + MIPUSH_LOG_PATH);
            this.addFile(new File((File)context, "log0.txt"));
            this.addFile(new File((File)context, "log1.txt"));
        }
        if (!context.isDirectory()) {
            return null;
        }
        File file2 = new File(file, "" + date.getTime() + "-" + date2.getTime() + ".zip");
        if (file2.exists()) {
            return null;
        }
        this.setRange(date, date2);
        long l = System.currentTimeMillis();
        context = new File(file, "log.txt");
        this.filter2File((File)context);
        MyLog.v("LOG: filter cost = " + (System.currentTimeMillis() - l));
        if (!context.exists()) return null;
        l = System.currentTimeMillis();
        IOUtils.zip(file2, (File)context);
        MyLog.v("LOG: zip cost = " + (System.currentTimeMillis() - l));
        context.delete();
        context = file2;
        if (file2.exists()) return context;
        return null;
    }

    void setMaxLen(int n) {
        if (n != 0) {
            this.mMaxLen = n;
        }
    }

    LogFilter setRange(Date date, Date date2) {
        if (date.after(date2)) {
            this.mFromTime = this.dateFormatter.format(date2);
            this.mEndTime = this.dateFormatter.format(date);
            return this;
        }
        this.mFromTime = this.dateFormatter.format(date);
        this.mEndTime = this.dateFormatter.format(date2);
        return this;
    }
}

