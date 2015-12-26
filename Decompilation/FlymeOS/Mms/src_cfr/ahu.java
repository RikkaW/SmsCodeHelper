/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedInputStream
 *  java.io.File
 *  java.io.FilterOutputStream
 *  java.io.ObjectInputStream
 *  java.io.ObjectOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.math.BigInteger
 *  java.security.MessageDigest
 *  java.util.ArrayList
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ahu {
    private static List<File> a = new ArrayList();
    private ahl b;
    private int c;

    private ahu(File file, int n2, long l2) {
        this.c = n2;
        this.b = ahl.a(file, n2, 1, l2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ahu a(File object, int n2, long l2) {
        synchronized (ahu.class) {
            if (a.contains(object)) {
                throw new IllegalStateException("Cache dir " + object.getAbsolutePath() + " was used before.");
            }
            a.add((File)object);
            return new ahu((File)object, n2, l2);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private Map<String, Serializable> a(ahl.c var1_1) {
        var1_1 = var2_3 = new ObjectInputStream((InputStream)new BufferedInputStream(var1_1.a(0)));
        try {
            var3_4 = (Map)var2_3.readObject();
            if (var2_3 == null) return var3_4;
        }
        catch (ClassNotFoundException var3_8) {
            var1_1 = var2_3;
            ** continue;
        }
        var2_3.close();
        return var3_4;
        catch (ClassNotFoundException var3_5) {
            var1_1 = null;
            ** GOTO lbl14
            catch (Throwable var1_2) {
                var2_3 = null;
                ** GOTO lbl20
            }
lbl14: // 2 sources:
            do {
                try {
                    throw new RuntimeException((Throwable)var3_6);
                }
                catch (Throwable var3_7) {
                    var2_3 = var1_1;
                    var1_1 = var3_7;
lbl20: // 2 sources:
                    if (var2_3 == null) throw var1_1;
                    var2_3.close();
                    throw var1_1;
                }
                break;
            } while (true);
        }
    }

    private String b(String string2) {
        return this.c(string2);
    }

    private String c(String string2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance((String)"MD5");
            messageDigest.update(string2.getBytes("UTF-8"));
            string2 = new BigInteger(1, messageDigest.digest()).toString(16);
            return string2;
        }
        catch (NoSuchAlgorithmException var1_2) {
            throw new AssertionError();
        }
        catch (UnsupportedEncodingException var1_3) {
            throw new AssertionError();
        }
    }

    public OutputStream a(String object, Map<String, ? extends Serializable> object2) {
        if ((object = this.b.b(this.b((String)object))) == null) {
            return null;
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(object.a(0));
            objectOutputStream.writeObject(object2);
            object2 = new a((OutputStream)objectOutputStream, (ahl.a)object);
            return object2;
        }
        catch (IOException var2_3) {
            object.b();
            throw var2_3;
        }
    }

    public Map<String, Serializable> a(String object) {
        if ((object = this.b.a(this.b((String)object))) == null) {
            return null;
        }
        try {
            Map<String, Serializable> map = this.a((ahl.c)object);
            return map;
        }
        finally {
            object.close();
        }
    }

    public void a() {
        try {
            if (a != null) {
                a.clear();
            }
            if (this.b != null) {
                this.b.close();
            }
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    public void b(String string2, Map<String, ? extends Serializable> map) {
        block3 : {
            try {
                string2 = this.a(string2, map);
                if (string2 == null) break block3;
            }
            catch (Throwable var1_2) {
                if (false) {
                    throw new NullPointerException();
                }
                throw var1_2;
            }
            string2.close();
        }
    }

    static class a
    extends FilterOutputStream {
        private final ahl.a a;
        private boolean b = false;

        private a(OutputStream outputStream, ahl.a a2) {
            super(outputStream);
            this.a = a2;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void close() {
            Object var1_1;
            var1_1 = null;
            try {
                super.close();
            }
            catch (IOException var1_2) {}
            if (this.b) {
                this.a.b();
            } else {
                this.a.a();
            }
            if (var1_1 != null) {
                throw var1_1;
            }
        }

        public void flush() {
            try {
                super.flush();
                return;
            }
            catch (IOException var1_1) {
                this.b = true;
                throw var1_1;
            }
        }

        public void write(int n2) {
            try {
                super.write(n2);
                return;
            }
            catch (IOException var2_2) {
                this.b = true;
                throw var2_2;
            }
        }

        public void write(byte[] arrby) {
            try {
                super.write(arrby);
                return;
            }
            catch (IOException var1_2) {
                this.b = true;
                throw var1_2;
            }
        }

        public void write(byte[] arrby, int n2, int n3) {
            try {
                super.write(arrby, n2, n3);
                return;
            }
            catch (IOException var1_2) {
                this.b = true;
                throw var1_2;
            }
        }
    }

}

