import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView.BufferType;
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

public class abu
{
  public static int a = 0;
  public static int b = 1;
  private static String c = "UnderlineHelper";
  private static final String[] h = { "_id" };
  private static int j = -1;
  private SmsEntityLoader d;
  private vv e;
  private Context f;
  private MmsFoldableTextView g;
  private AlertDialog i = null;
  private boolean k = false;
  
  public abu(Context paramContext, vv paramvv, MmsFoldableTextView paramMmsFoldableTextView, int paramInt)
  {
    e = paramvv;
    f = paramContext;
    g = paramMmsFoldableTextView;
    j = paramInt;
  }
  
  private SpannableStringBuilder a(SmsEntity paramSmsEntity, String paramString)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    localSpannableStringBuilder.append(paramString);
    if (paramSmsEntity == null) {
      return localSpannableStringBuilder;
    }
    paramSmsEntity.setBody(paramString);
    if ((paramSmsEntity.getAllEntities() == null) || (paramSmsEntity.getAllEntities().size() == 0)) {
      return localSpannableStringBuilder;
    }
    paramSmsEntity = paramSmsEntity.getAllEntities(2).iterator();
    BubbleEntity localBubbleEntity;
    String str;
    int m;
    while (paramSmsEntity.hasNext())
    {
      localBubbleEntity = (BubbleEntity)paramSmsEntity.next();
      str = localBubbleEntity.getMatchedWords();
      if (!TextUtils.isEmpty(str))
      {
        m = localBubbleEntity.getIndex();
        if (m <= 1) {
          break label172;
        }
        m = paramString.indexOf(str, m);
        if (m == -1) {
          break label172;
        }
      }
    }
    for (;;)
    {
      int n = m;
      if (m < 0) {
        n = paramString.indexOf(str);
      }
      if (n < 0) {
        break;
      }
      localSpannableStringBuilder.setSpan(new abx(this, localBubbleEntity), n, str.length() + n, 0);
      break;
      return localSpannableStringBuilder;
      label172:
      m = -1;
    }
  }
  
  public static void a(MmsApp paramMmsApp)
  {
    Log.d(c, "init() begin: isUnderlineOpen() = " + a());
    if (a())
    {
      SmsParserEngine.getInstance(paramMmsApp).initialise(paramMmsApp, null);
      b(paramMmsApp);
      Updater.init(paramMmsApp);
    }
  }
  
  private void a(BubbleEntity paramBubbleEntity)
  {
    Object localObject = paramBubbleEntity.getActions();
    if (localObject != null)
    {
      if (((List)localObject).size() != 1) {
        break label122;
      }
      localObject = (ActionBase)((List)localObject).get(0);
      if ((localObject instanceof PhoneNumberAction)) {
        a((PhoneNumberAction)localObject);
      }
    }
    else
    {
      return;
    }
    if (action == 19)
    {
      paramBubbleEntity = ((QuickReplyAction)localObject).getItem();
      a(message, number);
      return;
    }
    if (action == 9)
    {
      a((ActionBase)localObject, paramBubbleEntity.getMatchedWords());
      return;
    }
    if ((localObject instanceof DateReminderAction))
    {
      b((ActionBase)localObject, paramBubbleEntity.getMatchedWords());
      return;
    }
    ((ActionBase)localObject).doAction(f);
    return;
    label122:
    a((List)localObject, paramBubbleEntity.getMatchedWords());
  }
  
  private void a(SmsEntity paramSmsEntity)
  {
    g.setAutoLinkMask(0);
    g.setText(a(paramSmsEntity, e.o), TextView.BufferType.SPANNABLE);
  }
  
  private void a(ActionBase paramActionBase, String paramString)
  {
    Log.d(c, "doEmailAction" + paramString);
    if ((i != null) && (i.isShowing()))
    {
      i.hide();
      i = null;
    }
    String str1 = f.getString(2131493843);
    String str2 = f.getString(2131493845);
    String str3 = f.getString(2131493839);
    AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(f);
    AlertDialog.Builder localBuilder2 = localBuilder1.setTitle(paramString);
    paramActionBase = new acd(this, paramActionBase, paramString);
    localBuilder2.setItems(new String[] { str1, str2, str3 }, paramActionBase);
    i = localBuilder1.create();
    i.show();
    new Thread(new ace(this, paramString)).start();
  }
  
  private void a(PhoneNumberAction paramPhoneNumberAction)
  {
    Log.d(c, "doPhoneNumberAction" + number);
    if ((i != null) && (i.isShowing()))
    {
      i.hide();
      i = null;
    }
    paramPhoneNumberAction = number;
    String str1 = f.getString(2131493841);
    String str2 = f.getString(2131493854);
    String str3 = f.getString(2131493845);
    String str4 = f.getString(2131493839);
    AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(f);
    AlertDialog.Builder localBuilder2 = localBuilder1.setTitle(paramPhoneNumberAction);
    abz localabz = new abz(this, paramPhoneNumberAction);
    localBuilder2.setItems(new String[] { str1, str2, str3, str4 }, localabz);
    i = localBuilder1.create();
    i.show();
    new Thread(new aca(this, paramPhoneNumberAction)).start();
  }
  
  private void a(String paramString, int paramInt)
  {
    new Thread(new abw(this, paramString, paramInt)).start();
  }
  
  private void a(String paramString1, String paramString2)
  {
    boolean bool = true;
    if ((i != null) && (i.isShowing()))
    {
      i.hide();
      i = null;
    }
    String str = f.getString(2131493853, new Object[] { paramString1, paramString2 });
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(f);
    localBuilder.setTitle(str).setPositiveButton(2131493166, new acc(this, paramString2, paramString1)).setNegativeButton(2131493022, null);
    i = localBuilder.create();
    i.show();
    paramString1 = i.getButton(-1);
    if ((ga.C()) && (zv.m())) {}
    for (;;)
    {
      paramString1.setEnabled(bool);
      return;
      bool = false;
    }
  }
  
  private void a(List<ActionBase> paramList, String paramString)
  {
    String[] arrayOfString = new String[paramList.size()];
    int m = 0;
    while (m < paramList.size())
    {
      arrayOfString[m] = getbuttonText;
      m += 1;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(f);
    localBuilder.setTitle(paramString);
    localBuilder.setItems(arrayOfString, new aby(this, paramList));
    localBuilder.create().show();
  }
  
  public static boolean a()
  {
    return (!MmsApp.d) && (!wd.c(MmsApp.c().getContentResolver()));
  }
  
  private static void b(MmsApp paramMmsApp)
  {
    try
    {
      InputStream localInputStream = paramMmsApp.getAssets().open("sms_core.db");
      paramMmsApp = new File(paramMmsApp.getFilesDir(), "sms_core.db");
      if ((!paramMmsApp.exists()) || (paramMmsApp.length() != localInputStream.available())) {
        FileUtil.copyToFile(localInputStream, paramMmsApp);
      }
      return;
    }
    catch (Exception paramMmsApp)
    {
      paramMmsApp.printStackTrace();
    }
  }
  
  private void b(ActionBase paramActionBase, String paramString)
  {
    Log.d(c, "doDateAction: matchedWords = " + paramString);
    if ((i != null) && (i.isShowing()))
    {
      i.hide();
      i = null;
    }
    String str1 = f.getString(2131493842);
    String str2 = f.getString(2131493844);
    String str3 = f.getString(2131493845);
    DateReminderAction localDateReminderAction = (DateReminderAction)paramActionBase;
    AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(f);
    AlertDialog.Builder localBuilder2 = localBuilder1.setTitle(paramString);
    paramActionBase = new acg(this, paramActionBase, localDateReminderAction, paramString);
    localBuilder2.setItems(new String[] { str1, str2, str3 }, paramActionBase);
    i = localBuilder1.create();
    i.show();
  }
  
  private static String c(String paramString, Context paramContext)
  {
    Object localObject = null;
    paramString = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));
    paramContext = paramContext.getContentResolver().query(paramString, new String[] { "display_name" }, null, null, null);
    paramString = (String)localObject;
    if (paramContext != null) {}
    try
    {
      if (paramContext.moveToNext())
      {
        paramString = paramContext.getString(0);
        return paramString;
      }
      return null;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  private static String d(String paramString, Context paramContext)
  {
    Object localObject = null;
    paramString = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI, Uri.encode(paramString));
    paramContext = paramContext.getContentResolver().query(paramString, new String[] { "display_name" }, null, null, null);
    paramString = (String)localObject;
    if (paramContext != null) {}
    try
    {
      if (paramContext.moveToNext())
      {
        paramString = paramContext.getString(0);
        return paramString;
      }
      return null;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public void a(Runnable paramRunnable)
  {
    Log.d(c, "handleSmsLink() begin ~~~~ mUnderlineFuncCheckState = " + j);
    if ((e == null) || (j != b)) {}
    do
    {
      return;
      d = SmsEntityLoader.getInstance(f);
      paramRunnable = d.loadSmsEntity(e.ab * 1000L + e.e, e.o, e.m, e.ab, new abv(this, paramRunnable));
    } while (paramRunnable == null);
    a(paramRunnable);
  }
  
  public void a(boolean paramBoolean)
  {
    k = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     abu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */