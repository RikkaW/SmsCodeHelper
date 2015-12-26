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

public class asp
implements aod {
    final /* synthetic */ YellowPageEngine.d a;
    private final /* synthetic */ asr b;
    private final /* synthetic */ String c;

    public asp(YellowPageEngine.d d2, asr asr2, String string2) {
        this.a = d2;
        this.b = asr2;
        this.c = string2;
    }

    @Override
    public void a(aod.a a2, NumItem numItem) {
        this.b.a(this.c, numItem);
        YellowPageEngine.d.a(this.a, numItem);
    }

    @Override
    public void a(aod.a a2, List<NumItem> list) {
    }

    @Override
    public void a(Throwable throwable) {
        Log.e((String)YellowPageEngine.access$0(), (String)("Get item from network failed. Number is " + YellowPageEngine.d.a(this.a).getNumber()));
        if (YellowPageEngine.d.b(this.a) != null) {
            YellowPageEngine.d.b(this.a).onFail();
        }
    }
}

