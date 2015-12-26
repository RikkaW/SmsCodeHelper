/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import com.android.mms.view.MzContactHeaderWidget;

public class aes
implements Runnable {
    final /* synthetic */ MzContactHeaderWidget a;

    public aes(MzContactHeaderWidget mzContactHeaderWidget) {
        this.a = mzContactHeaderWidget;
    }

    @Override
    public void run() {
        if (MzContactHeaderWidget.a(this.a) != null) {
            String[] arrstring = (String[])MzContactHeaderWidget.b(this.a).toArray((Object[])new String[MzContactHeaderWidget.b(this.a).size()]);
            for (int i2 = 0; i2 < MzContactHeaderWidget.b(this.a).size(); ++i2) {
                arrstring[i2] = arrstring[i2].replaceAll(" ", "");
            }
            MzContactHeaderWidget.a(this.a).a(arrstring);
        }
    }
}

