/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.view.View;
import com.android.mms.view.MeizuSearchListItem;

public class acz
implements View.OnClickListener {
    final /* synthetic */ MeizuSearchListItem a;

    public acz(MeizuSearchListItem meizuSearchListItem) {
        this.a = meizuSearchListItem;
    }

    public void onClick(View object) {
        object = MeizuSearchListItem.a(this.a).h();
        if (object != null && object.size() > 0) {
            object = (gm)object.get(0);
            wd.a(MeizuSearchListItem.b(this.a), object.d(), object.k(), object.n(), false, object.p());
        }
    }
}

