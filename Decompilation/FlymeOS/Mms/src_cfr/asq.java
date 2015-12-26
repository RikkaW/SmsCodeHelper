/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.sdk.yellow.YellowPageEngine;
import java.util.List;

public class asq
implements aod {
    final /* synthetic */ YellowPageEngine.e a;
    private final /* synthetic */ String b;

    public asq(YellowPageEngine.e e2, String string2) {
        this.a = e2;
        this.b = string2;
    }

    @Override
    public void a(aod.a a2, NumItem numItem) {
        Log.i((String)YellowPageEngine.access$0(), (String)("Get item from network onSuccess: " + this.b));
        asr.a().a(this.b, numItem);
        YellowPageEngine.e.a(this.a, numItem);
    }

    @Override
    public void a(aod.a a2, List<NumItem> list) {
    }

    @Override
    public void a(Throwable throwable) {
        Log.e((String)YellowPageEngine.access$0(), (String)"Get item from network failed.");
        if (YellowPageEngine.e.a(this.a) != null) {
            YellowPageEngine.e.a(this.a).onFail();
        }
    }
}

