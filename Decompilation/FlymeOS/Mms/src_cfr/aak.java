/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.AsyncTask
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.os.AsyncTask;
import com.meizu.common.app.SlideNotice;

class aak
extends AsyncTask<Void, Void, String> {
    final /* synthetic */ aaj a;

    aak(aaj aaj2) {
        this.a = aaj2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected /* varargs */ String a(Void ... object) {
        object = null;
        if (this.a.a.equalsIgnoreCase("android.intent.action.INCALL_BLOCK_LIST")) {
            return aai.a.a(this.a.b);
        }
        if (!this.a.a.equalsIgnoreCase("android.intent.action.MMS_BLOCK_LIST")) return object;
        return aai.a.b(this.a.b);
    }

    protected void a(String object) {
        if (object == null) {
            return;
        }
        object = SlideNotice.makeNotice(this.a.b, (CharSequence)object, 1, 1);
        object.setActionBarToTop(true);
        object.setOnClickNoticeListener(new aal(this));
        object.show();
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a((Void[])arrobject);
    }

    protected /* synthetic */ void onPostExecute(Object object) {
        this.a((String)object);
    }
}

