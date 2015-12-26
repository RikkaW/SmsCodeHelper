/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.content.Context;
import com.ted.android.contacts.common.util.NovoFileUtil;
import java.io.InputStream;

public class bp {
    private final Context a;
    private final ans b = new ans();

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public bp(Context var1_1) {
        super();
        this.a = var1_1;
        var2_6 = null;
        try {
            var2_6 = var1_1 = NovoFileUtil.openLatestInputFile(this.a, "config.ini");
            this.b.a((InputStream)var1_1);
            if (var1_1 == null) return;
        }
        catch (Exception var1_2) {
            if (var2_6 == null) return;
            try {
                var2_6.close();
                return;
            }
            catch (Exception var1_3) {
                return;
            }
        }
        var1_1.close();
        return;
        {
            catch (Exception var1_5) {
                return;
            }
        }
        catch (Throwable var1_4) {
            var2_6 = null;
            ** GOTO lbl29
            catch (Throwable var3_8) {
                var2_6 = var1_1;
                var1_1 = var3_8;
            }
lbl29: // 2 sources:
            if (var2_6 == null) throw var1_1;
            try {
                var2_6.close();
            }
            catch (Exception var2_7) {
                throw var1_1;
            }
            throw var1_1;
        }
    }

    public String a(String string2, String string3) {
        return this.b.b(string2, string3);
    }
}

