/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import com.meizu.update.UpdateInfo;

public class akj {
    private Context a;
    private akn b;
    private long c;

    protected akj(Context context, akn akn2, long l2) {
        if (context == null || akn2 == null) {
            throw new IllegalArgumentException("listener and context cant be null");
        }
        this.b = akn2;
        this.a = context;
        this.c = l2;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected UpdateInfo a(boolean bl2) {
        int n2;
        aki.b(this.a);
        anc.a(this.a);
        boolean bl3 = anl.i(this.a);
        if (anl.d() || !akk.a(this.a, this.c)) {
            anf.d("check interval interrupt");
            return UpdateInfo.generateNoUpdateInfo();
        }
        if (!bl3) {
            anf.c("request check no network : " + this.a.getPackageName());
            return null;
        }
        anf.a(this.a, "start check update for :" + this.a.getPackageName());
        UpdateInfo updateInfo = akf.a(this.a);
        if (updateInfo == null) {
            anf.a(this.a, "check update return null");
            return updateInfo;
        }
        anf.a(this.a, "check update result :" + updateInfo.mExistsUpdate + "," + updateInfo.mNeedUpdate + "," + updateInfo.mVersionName);
        if (updateInfo.mExistsUpdate) {
            n2 = updateInfo.mNeedUpdate ? 1 : 2;
        } else {
            akk.b(this.a);
            n2 = 3;
            aki.a(this.a);
        }
        akk.a(this.a, n2);
        if (!updateInfo.mExistsUpdate) return updateInfo;
        if (updateInfo.mNeedUpdate) return updateInfo;
        if (!amx.c(this.a, updateInfo.mVersionName)) return updateInfo;
        if (!bl2) {
            anf.c("skip version: " + updateInfo.mVersionName);
            updateInfo.mExistsUpdate = false;
            akk.b(this.a);
            return updateInfo;
        }
        anf.c("manual check while skip version: " + updateInfo.mVersionName);
        return updateInfo;
    }

    protected void a() {
        this.b.a(2, null);
    }

    protected void a(UpdateInfo updateInfo) {
        this.b.a(0, updateInfo);
    }
}

