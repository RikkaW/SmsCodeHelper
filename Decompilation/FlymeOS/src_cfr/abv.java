/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Long
 *  java.lang.Object
 */
import com.ted.android.core.SmsEntityLoader;
import com.ted.android.data.SmsEntity;

class abv
implements SmsEntityLoader.SmsEntityLoaderCallback {
    final /* synthetic */ Runnable a;
    final /* synthetic */ abu b;

    abv(abu abu2, Runnable runnable) {
        this.b = abu2;
        this.a = runnable;
    }

    @Override
    public void onSmsEntityLoaded(Long l2, SmsEntity smsEntity) {
        if (abu.a((abu)this.b).ab * 1000 + abu.a((abu)this.b).e == l2 && smsEntity != null && abu.b(this.b)) {
            abu.a(this.b, smsEntity);
            if (this.a != null) {
                this.a.run();
            }
        }
    }
}

