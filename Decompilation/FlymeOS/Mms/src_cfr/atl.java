/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedHashMap;

public class atl {
    public static LinkedHashMap<String, String> a(String string2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        File file = new File(string2);
        if (!atl.a(file)) {
            return atl.b(string2);
        }
        String string3 = null;
        string2 = null;
        try {
            file = atl.b(file);
        }
        catch (IOException var2_4) {
            string3 = string2;
            try {
                atm.a("readDict , IOException: ", var2_4);
            }
            catch (Throwable var0_1) {
                atl.a((Reader)string3);
                throw var0_1;
            }
            atl.a((Reader)string2);
            return linkedHashMap;
        }
        do {
            String[] arrstring;
            block10 : {
                string2 = file;
                string3 = file;
                arrstring = file.readLine();
                if (arrstring != null) break block10;
                atl.a((Reader)file);
                return linkedHashMap;
            }
            string2 = file;
            string3 = file;
            if ((arrstring = arrstring.split("\t")) == null) continue;
            string2 = file;
            string3 = file;
            if (arrstring.length < 2) continue;
            string2 = file;
            string3 = file;
            linkedHashMap.put((Object)arrstring[0], (Object)arrstring[1]);
            continue;
            break;
        } while (true);
    }

    private static void a(Reader reader) {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
            return;
        }
        catch (IOException var0_1) {
            atm.a("closeReader , IOException: ", var0_1);
            return;
        }
    }

    public static boolean a(File file) {
        if (file != null && file.exists() && file.isFile() && file.canRead()) {
            return true;
        }
        return false;
    }

    private static BufferedReader b(File file) {
        return new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(file), "UTF-8"));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static LinkedHashMap<String, String> b(String string2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        string2 = new BufferedReader((Reader)new InputStreamReader(atl.class.getClassLoader().getResourceAsStream(string2)));
        do {
            String[] arrstring = string2.readLine();
            if (arrstring == null) {
                return linkedHashMap;
            }
            try {
                if ((arrstring = arrstring.split("\t")) == null || arrstring.length < 2) continue;
                linkedHashMap.put((Object)arrstring[0], (Object)arrstring[1]);
                continue;
            }
            catch (IOException iOException) {
                atm.a("readDict , IOException: ", iOException);
                return linkedHashMap;
            }
        } while (true);
        finally {
            atl.a((Reader)string2);
        }
    }
}

