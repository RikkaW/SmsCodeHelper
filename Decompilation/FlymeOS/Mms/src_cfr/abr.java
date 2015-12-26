/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.format.DateFormat
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.Calendar
 *  java.util.Date
 */
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.TextView;
import com.android.mms.util.TimerMessageHelpler;
import com.android.mms.view.CustomTimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class abr
implements Runnable {
    final /* synthetic */ CustomTimePicker a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ TextView e;

    public abr(CustomTimePicker customTimePicker, Context context, String string2, String string3, TextView textView) {
        this.a = customTimePicker;
        this.b = context;
        this.c = string2;
        this.d = string3;
        this.e = textView;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        this.a.a(TimerMessageHelpler.a());
        calendar.set(TimerMessageHelpler.a()[0], TimerMessageHelpler.a()[1] - 1, TimerMessageHelpler.a()[2], TimerMessageHelpler.a()[3], TimerMessageHelpler.a()[4], 0);
        String string2 = DateFormat.is24HourFormat((Context)this.b) ? this.c : this.d;
        string2 = new SimpleDateFormat(string2);
        this.e.setText((CharSequence)string2.format(calendar.getTime()));
    }
}

