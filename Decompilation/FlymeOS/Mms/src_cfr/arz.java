/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
import android.content.Context;
import android.view.View;
import com.ted.android.data.bubbleAction.ActionBase;

public class arz
implements View.OnClickListener {
    final /* synthetic */ ActionBase a;
    private final /* synthetic */ Context b;

    public arz(ActionBase actionBase, Context context) {
        this.a = actionBase;
        this.b = context;
    }

    public void onClick(View view) {
        this.a.doAction(this.b);
    }
}

