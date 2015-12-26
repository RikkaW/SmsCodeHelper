/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import com.ted.android.core.SmsParserEngine;
import com.ted.android.data.BubbleEntity;
import java.util.List;
import java.util.concurrent.Callable;

public class aqj
implements Callable<List<BubbleEntity>> {
    final /* synthetic */ SmsParserEngine a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;

    public aqj(SmsParserEngine smsParserEngine, String string2, String string3) {
        this.a = smsParserEngine;
        this.b = string2;
        this.c = string3;
    }

    public List<BubbleEntity> a() {
        return SmsParserEngine.a(this.a, this.b, this.c);
    }

    @Override
    public /* synthetic */ Object call() {
        return this.a();
    }
}

