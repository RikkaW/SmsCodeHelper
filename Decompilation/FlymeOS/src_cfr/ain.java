/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.widget.ListAdapter
 *  java.lang.Object
 *  java.lang.String
 */
import android.text.TextUtils;
import android.widget.ListAdapter;
import com.meizu.commonwidget.RecipientEdit;

public class ain
implements Runnable {
    final /* synthetic */ RecipientEdit a;

    public ain(RecipientEdit recipientEdit) {
        this.a = recipientEdit;
    }

    @Override
    public void run() {
        for (int i2 = 0; i2 < RecipientEdit.i(this.a).size(); ++i2) {
            String string2 = (String)RecipientEdit.i(this.a).get(i2);
            String string3 = ((aii)RecipientEdit.a(this.a).getAdapter()).a(string2);
            if (TextUtils.equals((CharSequence)string3, (CharSequence)((String)RecipientEdit.h(this.a).get((Object)string2)))) continue;
            this.a.post((Runnable)new RecipientEdit.n(this.a, string2, string3));
        }
    }
}

