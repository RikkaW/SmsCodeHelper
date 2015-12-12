
package me.gitai.library.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log增强工具
 * <p>
 * 你只需要填入想输出的内容,繁琐的东西自动帮你补全<br>
 * 1.自动用类名.方法名填充TAG<br>
 * 2.自动填充文件名,行号<br>
 * 3.Eclipse里面双击Logcat的输出,能转跳到Java文件相应行<br>
 * 4.能读取配置文件决定是否输出Logcat和Log文件,无需重新编译工程.<br>
 * 5.可以在配置文件配置密码,如果密码不符,则无法更改Log设置
 * <p>
 * 性能方面,耗时是{@link Log}的20倍
 * <p>
 * 使用示例:
 * 
 * <pre>
 * L.i(&quot;闪电狗&quot;);
 * 
 * LogCat输出:
 * 09-06 16:21:24.262: I/TestLogActivity$2.onClick(3212): 闪电狗
 * 09-06 16:21:24.262: I/TestLogActivity$2.onClick(3212):  at me.fantouch.demo.TestLogActivity$2.onClick(TestLogActivity.java:48)
 * </pre>
 * <p>
 * 
 * @author Fantouch
 */
@SuppressLint("SimpleDateFormat")
public class L {
    private static final String TAG = L.class.getSimpleName();

    private L() {/* 禁止实例化 */
    }

    public static void v() {
        log(Log.VERBOSE, null, null);
    }

    public static void v(String msg) {
        log(Log.VERBOSE, msg, null);
    }

    public static void v(Throwable throwable) {
        log(Log.VERBOSE, null, throwable);
    }

    public static void v(String msg, Throwable throwable) {
        log(Log.VERBOSE, msg, throwable);
    }

    public static void i() {
        log(Log.INFO, null, null);
    }

    public static void i(String msg) {
        log(Log.INFO, msg, null);
    }

    public static void i(Throwable throwable) {
        log(Log.INFO, null, throwable);
    }

    public static void i(String msg, Throwable throwable) {
        log(Log.INFO, msg, throwable);
    }

    public static void d() {
        log(Log.DEBUG, null, null);
    }

    public static void d(String msg) {
        log(Log.DEBUG, msg, null);
    }

    public static void d(Throwable throwable) {
        log(Log.DEBUG, null, throwable);
    }

    public static void d(String msg, Throwable throwable) {
        log(Log.DEBUG, msg, throwable);
    }

    public static void w() {
        log(Log.WARN, null, null);
    }

    public static void w(String msg) {
        log(Log.WARN, msg, null);
    }

    public static void w(Throwable throwable) {
        log(Log.WARN, null, throwable);
    }

    public static void w(String msg, Throwable throwable) {
        log(Log.WARN, msg, throwable);
    }

    public static void e() {
        log(Log.ERROR, null, null);
    }

    public static void e(String msg) {
        log(Log.ERROR, msg, null);
    }

    public static void e(Throwable throwable) {
        log(Log.ERROR, null, throwable);
    }

    public static void e(Throwable throwable,String msg) {
        log(Log.ERROR, msg, throwable);
    }

    public static void e(String msg, Throwable throwable) {
        log(Log.ERROR, msg, throwable);
    }

    private static final String LOG_FILE_EXTENSION = ".log";

    private static boolean isToLogcat = false;

    private static boolean isToFile = false;

    /**
     * log文件路径
     */
    private static String filePath = "";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat DATE_TIME_FORMAT =
            new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss] ");

    private static final String DATE_TIME_PLACEHOLDER = "                      ";

    private static void log(int logLevel, String msg, Throwable throwable) {

        if (isLogcatEnabled() || isLogToFileEnabled()) {

            StackTraceElement element = Thread.currentThread().getStackTrace()[4];
            String tag = getTag(element);
            String codeLocation = getCodeLocation(element);

            /** 根据内容处理打印排版 */
            if (msg == null && throwable == null) {
                msg = tag + " => " + codeLocation;
            } else if (msg == null && throwable != null) {
                msg = "";
            } else if (msg != null && throwable == null) {
                if (TextUtils.isEmpty(msg)) {
                    msg = "\"\"";
                }
                msg = msg + " => " + codeLocation;
            } else if (msg != null && throwable != null) {
                // Nothing
            }

            if (isLogcatEnabled()) {
                switch (logLevel) {
                    case Log.VERBOSE:
                        Log.v(tag, msg, throwable);
                        // com.readystatesoftware.notificationlog.Log.v(tag, msg, throwable);
                        break;

                    case Log.INFO:
                        Log.i(tag, msg, throwable);
                        // com.readystatesoftware.notificationlog.Log.i(tag, msg, throwable);
                        break;

                    case Log.DEBUG:
                        Log.d(tag, msg, throwable);
                        // com.readystatesoftware.notificationlog.Log.d(tag, msg, throwable);
                        break;

                    case Log.WARN:
                        Log.w(tag, msg, throwable);
                        // com.readystatesoftware.notificationlog.Log.w(tag, msg, throwable);
                        break;

                    case Log.ERROR:
                        Log.e(tag, msg, throwable);
                        // com.readystatesoftware.notificationlog.Log.e(tag, msg, throwable);
                        break;

                    default:
                        break;
                }
            }

            if (isLogToFileEnabled()) {
                String time = DATE_TIME_FORMAT.format(new Date(System.currentTimeMillis()));
                writeFile(time, tag, msg + "\n" + Log.getStackTraceString(throwable));
            }

        }

    }

    /** @return 格式类似于: TestLogActivity.onClick */
    private static String getTag(StackTraceElement stackTraceElement) {
        return stackTraceElement.getClassName().substring(
                stackTraceElement.getClassName().lastIndexOf(".") + 1) + "."
                + stackTraceElement.getMethodName() + "()";
    }

    /**
     * @return 格式类似于: at me.fantouch.demo.TestLogActivity$2.onClick(TestLogActivity.java:47) <br>
     *         这样的格式可以实现eclipse双击转跳到源码相应位置
     */
    private static String getCodeLocation(StackTraceElement stackTraceElement) {
        return "at "
                + stackTraceElement.getClassName()
                + "."
                + stackTraceElement.getMethodName()
                + "("
                + stackTraceElement.getFileName()
                + ":"
                + stackTraceElement.getLineNumber()
                + ")";
    }


    /**
     * 设置是否把日志输出到Logcat,建议在Application里面设置
     * 
     * @param enable 缺省false
     */
    public static void setLogcatEnable(Context ctx, boolean enable) {
        if (enable) {
            if (!isToLogcat) {
                isToLogcat = true;
                // com.readystatesoftware.notificationlog.Log.initialize(ctx);
                Log.v(TAG, "Logcat Enabled");
            } else {
                Log.w(TAG, "Logcat Already Enabled ");
            }
        } else {
            if (isToLogcat) {
                isToLogcat = false;
                // com.readystatesoftware.notificationlog.Log.setNotificationsEnabled(false);
                Log.v(TAG, "Logcat Disabled");
            } else {
                Log.w(TAG, "Logcat Already Disabled");
            }
        }
    }

    public static boolean isLogcatEnabled() {
        return isToLogcat;
    }

    /**
     * 设置是否保存日志到文件,文件所在目录示例/data/data/packageName/file/xx.log
     * 
     * @param enable 缺省false
     */
    public static void setLogToFileEnable(boolean enable, Context ctx) {
        setLogToFileEnable(enable, ctx, null);
    }

    /**
     * 设置是否保存日志到文件,并指定文件路径
     * 
     * @param enable 缺省false
     */
    public static void setLogToFileEnable(boolean enable, Context ctx, String path) {
        if (enable) {
            if (!isToFile) {
                isToFile = true;
                if (!TextUtils.isEmpty(path)) {
                    filePath = path;
                } else {
                    filePath = ctx.getFilesDir().getAbsolutePath();
                }
                Log.v(TAG, "Save Log To File Enabled");
            } else {
                Log.w(TAG, "Save To File Already Enabled");
            }
        } else {
            if (isToFile) {
                isToFile = false;
                Log.v(TAG, "Save Log To File Disabled");
            } else {
                Log.w(TAG, "Save To File Already Disabled");
            }
        }
    }

    public static boolean isLogToFileEnabled() {
        return isToFile;
    }

    /**
     * 根据配置文件,决定是否输出Logcat 和 Log文件
     * <p>
     * 配置文件示例如下:<br>
     * 
     * <pre>
     * 路径: /sdcard/log.cfg
     * 
     * logcat=true
     * file=true
     * </pre>
     */
    public static void cfgFromFile(Context ctx) {
        cfgFromFile(ctx, null);
    }
    
    /**
     * 根据配置文件,并验证密码,决定是否输出Logcat,是否输出Log文件
     * <p>
     * 配置文件示例如下:<br>
     * 
     * <pre>
     * 路径: /sdcard/log.cfg
     * 
     * password=123456
     * logcat=true
     * file=true
     * saveLogFileToSD=true
     * </pre>
     * 
     * @param password 密码,如果配置文件的与此不一致,则认为文件无效并忽略<br>
     *            如果null,则跳过密码校验
     */
    public static void cfgFromFile(Context ctx, String password) {
        File cfgFile = null;
        try {
            cfgFile = getCfgFile();
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        creatDefaultCfgFileIfNecessary(password);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(cfgFile));
            String line;
            boolean isFirstLoop = true;
            while ((line = in.readLine()) != null) {

                if (isFirstLoop && password != null) {// 需要验证密码
                    if (!line.contains("password")) {// 没声明密码,中断
                        Log.e(TAG, "Password NOT DEFINE in first line of config file");
                        break;
                    }
                    if (line.split("=").length == 1 || !line.split("=")[1].equals(password)) {// 密码错误,中断
                        Log.e(TAG, "Password in config file NOT CORRECT");
                        break;
                    }
                }

                if (line.contains("logcat")) {
                    try {
                        if (line.split("=")[1].equals("true")) {
                            setLogcatEnable(ctx, true);
                        } else if (line.split("=")[1].equals("false")) {
                            setLogcatEnable(ctx, false);
                        } else {
                            Log.e(TAG, "Illegal cfg file, logcat must a boolean value");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        Log.e(TAG, "Illegal cfg file");
                    }
                }

                if (line.contains("logfile")) {
                    try {
                        if (line.split("=")[1].equals("true")) {
                            setLogToFileEnable(true, ctx);
                        } else if (line.split("=")[1].equals("false")) {
                            setLogToFileEnable(false, ctx);
                        } else {
                            Log.e(TAG, "Illegal cfg file, logfile must a boolean value");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        Log.e(TAG, "Illegal cfg file");
                    }
                }

                if (line.contains("saveLogFileToSD")) {
                    try {
                        if (line.split("=")[1].equals("true")) {
                            setLogToFileEnable(true, ctx, Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                        } else if (line.split("=")[1].equals("false")) {
                            setLogToFileEnable(false, ctx);
                        } else {
                            Log.e(TAG, "Illegal cfg file, saveLogFileToSD must a boolean value");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        Log.e(TAG, "Illegal cfg file");
                    }
                }

                isFirstLoop = false;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

    }

    private static void creatDefaultCfgFileIfNecessary(String password) {
        File file = null;
        try {
            file = getCfgFile();
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        if (file.length() > 0)
            return;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file.getAbsolutePath(), false));
            if (password != null) {
                writer.append("password=\n");
            }
            writer.append("logcat=false\n");
            writer.append("logfile=false\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }
    }

    // TODO 异步操作
    private static void writeFile(String time, String tag, String msg) {
        File file = null;
        try {
            file = getLogFile();
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            writer = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
            reader = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(msg.getBytes())));

            boolean isFirstLoop = true;
            String line;
            while ((line = reader.readLine()) != null) {
                if (isFirstLoop) {
                    isFirstLoop = false;
                    writer.append(time);
                    writer.append(tag);
                    writer.append("\n");

                }

                if (!TextUtils.isEmpty(line)) {
                    writer.append(DATE_TIME_PLACEHOLDER);
                }

                writer.append(line);

                if (!TextUtils.isEmpty(line)) {
                    writer.append("\n");
                }

            }
            writer.append("\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private static File getLogFile() throws IOException {
        File file = new File(filePath,
                DATE_FORMAT.format(new Date(System.currentTimeMillis())) + LOG_FILE_EXTENSION);
        return creatFileIfNotExists(file);
    }

    private static File getCfgFile() throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "log.cfg");
        return creatFileIfNotExists(file);
    }

    /**
     * @return null if IOException
     */
    private static File creatFileIfNotExists(File file) throws IOException {
        if (!file.exists()) {
            new File(filePath).mkdirs();
            file.createNewFile();
        }
        return file;
    }

    /**
     * 发送日志文件到服务器
     */
    /*public static void sendLogFiles(Context ctx,
            Class<? extends AbsSendFileService> sendService) {
        Intent intent = new Intent(ctx, sendService);
        intent.putExtra(AbsSendFileService.INTENT_DIR, filePath);
        intent.putExtra(AbsSendFileService.INTENT_EXTENSION, LOG_FILE_EXTENSION);
        ctx.startService(intent);
    }*/

}
