/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Environment
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 */
import android.os.Environment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ash {
    private static final String a = ash.class.getSimpleName();
    private Writer b;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2, String string3) {
        File file = new File(String.valueOf((Object)Environment.getExternalStorageDirectory().getAbsolutePath()) + File.separator + "ted_logs");
        if (!file.isDirectory()) {
            file.mkdir();
        }
        try {
            if (!file.isDirectory()) {
                throw new IOException("Unable to create directory ted_logs. Maybe the SD card is mounted?");
            }
            if (!(string2 = new File(file, string2)).exists()) {
                string2.createNewFile();
            }
            this.b = new BufferedWriter((Writer)new FileWriter((File)string2, true));
            this.b.write(string3);
            this.b.flush();
            return;
        }
        catch (IOException var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

