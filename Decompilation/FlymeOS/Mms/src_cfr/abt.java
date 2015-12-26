/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  java.lang.Object
 *  java.util.Calendar
 */
import android.content.DialogInterface;
import com.android.mms.util.TimerMessageHelpler;
import com.android.mms.view.CustomTimePicker;
import java.util.Calendar;

public final class abt
implements DialogInterface.OnClickListener {
    final /* synthetic */ CustomTimePicker a;
    final /* synthetic */ TimerMessageHelpler.a b;

    public abt(CustomTimePicker customTimePicker, TimerMessageHelpler.a a2) {
        this.a = customTimePicker;
        this.b = a2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        dialogInterface = Calendar.getInstance();
        this.a.a(TimerMessageHelpler.a());
        dialogInterface.set(TimerMessageHelpler.a()[0], TimerMessageHelpler.a()[1] - 1, TimerMessageHelpler.a()[2], TimerMessageHelpler.a()[3], TimerMessageHelpler.a()[4], 0);
        long l2 = dialogInterface.getTimeInMillis();
        this.b.a(l2);
    }
}

