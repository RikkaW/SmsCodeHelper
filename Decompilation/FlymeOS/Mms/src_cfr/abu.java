/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.res.AssetManager
 *  android.database.Cursor
 *  android.net.Uri
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$CommonDataKinds
 *  android.provider.ContactsContract$CommonDataKinds$Email
 *  android.provider.ContactsContract$PhoneLookup
 *  android.text.SpannableStringBuilder
 *  android.text.TextUtils
 *  android.util.Log
 *  android.widget.Button
 *  android.widget.TextView
 *  android.widget.TextView$BufferType
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 */
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.view.MmsFoldableTextView;
import com.ted.android.contacts.common.util.FileUtil;
import com.ted.android.core.ReplyMsgItem;
import com.ted.android.core.SmsEntityLoader;
import com.ted.android.core.SmsParserEngine;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.SmsEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;
import com.ted.android.data.bubbleAction.PhoneNumberAction;
import com.ted.android.data.bubbleAction.QuickReplyAction;
import com.ted.sdk.yellow.update.Updater;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class abu {
    public static int a;
    public static int b;
    private static String c;
    private static final String[] h;
    private static int j;
    private SmsEntityLoader d;
    private vv e;
    private Context f;
    private MmsFoldableTextView g;
    private AlertDialog i = null;
    private boolean k = false;

    static {
        c = "UnderlineHelper";
        a = 0;
        b = 1;
        h = new String[]{"_id"};
        j = -1;
    }

    public abu(Context context, vv vv2, MmsFoldableTextView mmsFoldableTextView, int n2) {
        this.e = vv2;
        this.f = context;
        this.g = mmsFoldableTextView;
        j = n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private SpannableStringBuilder a(SmsEntity iterator, String string2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence)string2);
        if (iterator == null) {
            return spannableStringBuilder;
        }
        iterator.setBody(string2);
        if (iterator.getAllEntities() == null || iterator.getAllEntities().size() == 0) {
            return spannableStringBuilder;
        }
        iterator = iterator.getAllEntities(2).iterator();
        while (iterator.hasNext()) {
            BubbleEntity bubbleEntity = iterator.next();
            String string3 = bubbleEntity.getMatchedWords();
            if (TextUtils.isEmpty((CharSequence)string3)) continue;
            int n2 = bubbleEntity.getIndex();
            if (n2 <= 1 || (n2 = string2.indexOf(string3, n2)) == -1) {
                n2 = -1;
            }
            int n3 = n2;
            if (n2 < 0) {
                n3 = string2.indexOf(string3);
            }
            if (n3 < 0) continue;
            spannableStringBuilder.setSpan((Object)new abx(this, bubbleEntity), n3, string3.length() + n3, 0);
        }
        return spannableStringBuilder;
    }

    static /* synthetic */ String a(String string2, Context context) {
        return abu.c(string2, context);
    }

    static /* synthetic */ vv a(abu abu2) {
        return abu2.e;
    }

    static /* synthetic */ void a(abu abu2, BubbleEntity bubbleEntity) {
        abu2.a(bubbleEntity);
    }

    static /* synthetic */ void a(abu abu2, SmsEntity smsEntity) {
        abu2.a(smsEntity);
    }

    static /* synthetic */ void a(abu abu2, PhoneNumberAction phoneNumberAction) {
        abu2.a(phoneNumberAction);
    }

    static /* synthetic */ void a(abu abu2, String string2, int n2) {
        abu2.a(string2, n2);
    }

    static /* synthetic */ void a(abu abu2, String string2, String string3) {
        abu2.a(string2, string3);
    }

    public static void a(MmsApp mmsApp) {
        Log.d((String)c, (String)("init() begin: isUnderlineOpen() = " + abu.a()));
        if (abu.a()) {
            SmsParserEngine.getInstance((Context)mmsApp).initialise((Context)mmsApp, null);
            abu.b(mmsApp);
            Updater.init((Context)mmsApp);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a(BubbleEntity object) {
        List<ActionBase> list = object.getActions();
        if (list == null) return;
        if (list.size() == 1) {
            if ((list = list.get(0)) instanceof PhoneNumberAction) {
                this.a((PhoneNumberAction)((Object)list));
                return;
            }
            if (list.action == 19) {
                object = ((QuickReplyAction)((Object)list)).getItem();
                this.a(object.message, object.number);
                return;
            }
            if (list.action == 9) {
                this.a((ActionBase)((Object)list), object.getMatchedWords());
                return;
            }
            if (list instanceof DateReminderAction) {
                this.b((ActionBase)((Object)list), object.getMatchedWords());
                return;
            }
            list.doAction(this.f);
            return;
        }
        this.a(list, object.getMatchedWords());
    }

    private void a(SmsEntity smsEntity) {
        this.g.setAutoLinkMask(0);
        this.g.setText((CharSequence)this.a(smsEntity, this.e.o), TextView.BufferType.SPANNABLE);
    }

    private void a(ActionBase object, String string2) {
        Log.d((String)c, (String)("doEmailAction" + string2));
        if (this.i != null && this.i.isShowing()) {
            this.i.hide();
            this.i = null;
        }
        String string3 = this.f.getString(2131493843);
        String string4 = this.f.getString(2131493845);
        String string5 = this.f.getString(2131493839);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f);
        AlertDialog.Builder builder2 = builder.setTitle(string2);
        object = new acd(this, (ActionBase)object, string2);
        builder2.setItems(new String[]{string3, string4, string5}, (DialogInterface.OnClickListener)object);
        this.i = builder.create();
        this.i.show();
        new Thread((Runnable)new ace(this, string2)).start();
    }

    private void a(PhoneNumberAction object) {
        Log.d((String)c, (String)("doPhoneNumberAction" + object.number));
        if (this.i != null && this.i.isShowing()) {
            this.i.hide();
            this.i = null;
        }
        object = object.number;
        String string2 = this.f.getString(2131493841);
        String string3 = this.f.getString(2131493854);
        String string4 = this.f.getString(2131493845);
        String string5 = this.f.getString(2131493839);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f);
        AlertDialog.Builder builder2 = builder.setTitle((CharSequence)object);
        abz abz2 = new abz(this, (String)object);
        builder2.setItems(new String[]{string2, string3, string4, string5}, (DialogInterface.OnClickListener)abz2);
        this.i = builder.create();
        this.i.show();
        new Thread((Runnable)new aca(this, (String)object)).start();
    }

    private void a(String string2, int n2) {
        new Thread((Runnable)new abw(this, string2, n2)).start();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(String string2, String string3) {
        boolean bl2 = true;
        if (this.i != null && this.i.isShowing()) {
            this.i.hide();
            this.i = null;
        }
        String string4 = this.f.getString(2131493853, new Object[]{string2, string3});
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f);
        builder.setTitle(string4).setPositiveButton(2131493166, (DialogInterface.OnClickListener)new acc(this, string3, string2)).setNegativeButton(2131493022, null);
        this.i = builder.create();
        this.i.show();
        string2 = this.i.getButton(-1);
        if (!ga.C() || !zv.m()) {
            bl2 = false;
        }
        string2.setEnabled(bl2);
    }

    private void a(List<ActionBase> list, String string2) {
        CharSequence[] arrcharSequence = new String[list.size()];
        for (int i2 = 0; i2 < list.size(); ++i2) {
            arrcharSequence[i2] = list.get((int)i2).buttonText;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f);
        builder.setTitle(string2);
        builder.setItems(arrcharSequence, (DialogInterface.OnClickListener)new aby(this, list));
        builder.create().show();
    }

    public static boolean a() {
        if (!MmsApp.d && !wd.c(MmsApp.c().getContentResolver())) {
            return true;
        }
        return false;
    }

    static /* synthetic */ String b(String string2, Context context) {
        return abu.d(string2, context);
    }

    private static void b(MmsApp mmsApp) {
        try {
            InputStream inputStream = mmsApp.getAssets().open("sms_core.db");
            mmsApp = new File(mmsApp.getFilesDir(), "sms_core.db");
            if (!mmsApp.exists() || mmsApp.length() != (long)inputStream.available()) {
                FileUtil.copyToFile(inputStream, (File)mmsApp);
            }
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private void b(ActionBase object, String string2) {
        Log.d((String)c, (String)("doDateAction: matchedWords = " + string2));
        if (this.i != null && this.i.isShowing()) {
            this.i.hide();
            this.i = null;
        }
        String string3 = this.f.getString(2131493842);
        String string4 = this.f.getString(2131493844);
        String string5 = this.f.getString(2131493845);
        DateReminderAction dateReminderAction = (DateReminderAction)object;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f);
        AlertDialog.Builder builder2 = builder.setTitle(string2);
        object = new acg(this, (ActionBase)object, dateReminderAction, string2);
        builder2.setItems(new String[]{string3, string4, string5}, (DialogInterface.OnClickListener)object);
        this.i = builder.create();
        this.i.show();
    }

    static /* synthetic */ boolean b(abu abu2) {
        return abu2.k;
    }

    static /* synthetic */ String[] b() {
        return h;
    }

    static /* synthetic */ Context c(abu abu2) {
        return abu2.f;
    }

    static /* synthetic */ String c() {
        return c;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String c(String string2, Context context) {
        Object var2_3 = null;
        string2 = Uri.withAppendedPath((Uri)ContactsContract.PhoneLookup.CONTENT_FILTER_URI, (String)Uri.encode((String)string2));
        context = context.getContentResolver().query((Uri)string2, new String[]{"display_name"}, null, null, null);
        string2 = var2_3;
        if (context == null) return string2;
        if (!context.moveToNext()) return null;
        string2 = context.getString(0);
        return string2;
        finally {
            context.close();
        }
    }

    static /* synthetic */ AlertDialog d(abu abu2) {
        return abu2.i;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String d(String string2, Context context) {
        Object var2_3 = null;
        string2 = Uri.withAppendedPath((Uri)ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI, (String)Uri.encode((String)string2));
        context = context.getContentResolver().query((Uri)string2, new String[]{"display_name"}, null, null, null);
        string2 = var2_3;
        if (context == null) return string2;
        if (!context.moveToNext()) return null;
        string2 = context.getString(0);
        return string2;
        finally {
            context.close();
        }
    }

    static /* synthetic */ MmsFoldableTextView e(abu abu2) {
        return abu2.g;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void a(Runnable object) {
        Log.d((String)c, (String)("handleSmsLink() begin ~~~~ mUnderlineFuncCheckState = " + j));
        if (this.e == null) return;
        if (j != b) {
            return;
        }
        this.d = SmsEntityLoader.getInstance(this.f);
        if ((object = this.d.loadSmsEntity(this.e.ab * 1000 + this.e.e, this.e.o, this.e.m, this.e.ab, new abv(this, (Runnable)object))) == null) return;
        this.a((SmsEntity)object);
    }

    public void a(boolean bl2) {
        this.k = bl2;
    }
}

