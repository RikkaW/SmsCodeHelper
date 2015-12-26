/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.widget.Filter
 *  android.widget.Filter$FilterResults
 *  android.widget.Filterable
 *  java.io.DataInputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
import android.content.Context;
import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filterable;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class aii
extends aif
implements Filterable {
    public static final String[] b = new String[]{"display_name", "data1", "organization_note", "_id"};
    private int a;

    public aii(Context context, int n2) {
        super(context, n2);
        this.a = n2;
    }

    protected abstract Object a(CharSequence var1);

    protected abstract Object a(CharSequence var1, long var2);

    public abstract String a(String var1);

    protected abstract void a(CharSequence var1, long var2, Cursor var4);

    protected abstract void a(CharSequence var1, Cursor var2, Cursor var3, Cursor var4);

    public abstract void a(String var1, String var2);

    public abstract boolean b(String var1);

    public abstract boolean c(String var1);

    public abstract boolean d(String var1);

    public boolean g(int n2) {
        if (this.c(this.e(n2)) instanceof e) {
            return true;
        }
        return false;
    }

    public Filter getFilter() {
        return new a();
    }

    public final class a
    extends Filter {
        public CharSequence convertResultToString(Object object) {
            return null;
        }

        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            filterResults.values = aii.this.a(charSequence);
            return filterResults;
        }

        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            if (filterResults.values != null) {
                Cursor[] arrcursor = (Cursor[])filterResults.values;
                aii.this.a(charSequence, arrcursor[0], arrcursor[1], arrcursor[2]);
            }
            filterResults.count = aii.this.getCount();
        }
    }

    public class b
    extends aif.a {
        public long f;
        public String g;
        public String h;
        public String i;
        public String j;
        public CharSequence k;
        public c l;

        public b() {
            super(false, false);
        }
    }

    public final class c
    extends Filter {
        private final int b;
        private final long c;

        public c(int n2, long l2) {
            this.b = n2;
            this.c = l2;
        }

        public CharSequence convertResultToString(Object object) {
            return null;
        }

        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            filterResults.values = aii.this.a(charSequence, this.c);
            return filterResults;
        }

        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            Cursor cursor = (Cursor)filterResults.values;
            aii.this.a(charSequence, this.c, cursor);
            filterResults.count = aii.this.getCount();
        }
    }

    public class d
    extends aif.a {
        public d() {
            super(true, true);
        }
    }

    public class e
    extends aif.a {
        public e() {
            super(false, false);
        }
    }

    public static class f {
        public String a;
        public String b;

        private static f a(DataInputStream dataInputStream) {
            f f2 = new f();
            f2.a = dataInputStream.readUTF();
            f2.b = dataInputStream.readUTF();
            return f2;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public static ArrayList<f> a(byte[] var0) {
            var1_4 = new ArrayList();
            if (var0 == null) return var1_4;
            var0 = new ByteArrayInputStream((byte[])var0);
            var2_6 = new DataInputStream((InputStream)var0);
            try {
                try {
                    while (var2_6.available() > 0) {
                        var1_4.add((Object)f.a(var2_6));
                    }
                    ** GOTO lbl22
                }
                catch (IOException var3_7) {
                    var3_7.printStackTrace();
                    if (var0 == null) ** GOTO lbl16
                    try {
                        var0.close();
lbl16: // 2 sources:
                        if (var2_6 == null) return var1_4;
                        var2_6.close();
                        return var1_4;
                    }
                    catch (IOException var0_2) {
                        var0_2.printStackTrace();
                        return var1_4;
                    }
lbl22: // 1 sources:
                    if (var0 == null) ** GOTO lbl25
                    try {
                        var0.close();
lbl25: // 2 sources:
                        if (var2_6 == null) return var1_4;
                        var2_6.close();
                        return var1_4;
                    }
                    catch (IOException var0_1) {
                        var0_1.printStackTrace();
                        return var1_4;
                    }
                }
            }
            catch (Throwable var1_5) {
                if (var0 == null) ** GOTO lbl35
                try {
                    var0.close();
lbl35: // 2 sources:
                    if (var2_6 == null) throw var1_5;
                    var2_6.close();
                }
                catch (IOException var0_3) {
                    var0_3.printStackTrace();
                    throw var1_5;
                }
                throw var1_5;
            }
        }
    }

}

