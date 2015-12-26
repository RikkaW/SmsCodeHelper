import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.meizu.update.UpdateInfo;
import com.meizu.update.display.KeyguardHelperActivity;

public abstract class aks
{
  protected Context a;
  protected UpdateInfo b;
  protected boolean c;
  protected Dialog d;
  private BroadcastReceiver e = new aky(this);
  
  protected aks(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    if ((paramContext == null) || (paramUpdateInfo == null)) {
      throw new IllegalArgumentException("params cant be null!");
    }
    a = paramContext;
    b = paramUpdateInfo;
  }
  
  private TextView a(AlertDialog paramAlertDialog)
  {
    try
    {
      Object localObject = aji.a("com.android.internal.R$id", "alertTitle");
      if ((localObject != null) && ((localObject instanceof Integer)))
      {
        paramAlertDialog = (TextView)paramAlertDialog.findViewById(((Integer)localObject).intValue());
        return paramAlertDialog;
      }
    }
    catch (Exception paramAlertDialog) {}
    return null;
  }
  
  private akg c()
  {
    Object localObject1 = a();
    int i;
    Object localObject2;
    Object localObject3;
    if (ani.b())
    {
      i = 5;
      localObject2 = new AlertDialog.Builder(a, i);
      if (TextUtils.isEmpty(a)) {
        break label608;
      }
      if (TextUtils.isEmpty(b)) {
        break label598;
      }
      i = 1;
      localObject3 = new SpannableString(a + "\n" + b);
      ((SpannableString)localObject3).setSpan(new AbsoluteSizeSpan(a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_sub_title_text_size)), a.length(), ((SpannableString)localObject3).length(), 33);
      ((SpannableString)localObject3).setSpan(new ForegroundColorSpan(a.getResources().getColor(akq.a.mzuc_dialog_sub_title_text_color)), a.length(), ((SpannableString)localObject3).length(), 33);
      ((AlertDialog.Builder)localObject2).setTitle((CharSequence)localObject3);
      label173:
      ((AlertDialog.Builder)localObject2).setMessage(c);
      ((AlertDialog.Builder)localObject2).setPositiveButton(d, new akt(this, (aks.a)localObject1));
      if (TextUtils.isEmpty(e)) {
        break label613;
      }
      ((AlertDialog.Builder)localObject2).setNegativeButton(e, new aku(this, (aks.a)localObject1));
    }
    for (;;)
    {
      if (!TextUtils.isEmpty(f)) {
        ((AlertDialog.Builder)localObject2).setNeutralButton(f, new akv(this, (aks.a)localObject1));
      }
      ((AlertDialog.Builder)localObject2).setOnCancelListener(new akw(this, (aks.a)localObject1));
      localObject2 = ((AlertDialog.Builder)localObject2).create();
      d = ((Dialog)localObject2);
      if (c)
      {
        ((AlertDialog)localObject2).getWindow().setType(2003);
        g();
      }
      ((AlertDialog)localObject2).setCanceledOnTouchOutside(false);
      ((AlertDialog)localObject2).setOnDismissListener(new akx(this));
      f();
      d();
      ((AlertDialog)localObject2).show();
      localObject3 = ((AlertDialog)localObject2).getButton(-1);
      Button localButton1 = ((AlertDialog)localObject2).getButton(-2);
      Button localButton2 = ((AlertDialog)localObject2).getButton(-3);
      if ((localObject3 != null) && (localButton1 != null) && (localButton2 != null) && (!TextUtils.isEmpty(f)) && (!TextUtils.isEmpty(e)))
      {
        int j = a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_btn_text_size_small);
        ((Button)localObject3).setTextSize(0, j);
        localButton1.setTextSize(0, j);
        localButton2.setTextSize(0, j);
      }
      if (i != 0)
      {
        localObject1 = a((AlertDialog)localObject2);
        if (localObject1 != null) {
          ((TextView)localObject1).setLineSpacing(a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_title_line_spacing), 1.0F);
        }
        localObject1 = (TextView)((AlertDialog)localObject2).findViewById(16908299);
        if (localObject1 != null)
        {
          ((TextView)localObject1).setTextSize(0, a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_msg_text_size));
          ((TextView)localObject1).setTextColor(a.getResources().getColor(akq.a.mzuc_dialog_msg_text_color));
          ((TextView)localObject1).setLineSpacing(a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_msg_line_spacing), 1.0F);
          ((TextView)localObject1).setPadding(((TextView)localObject1).getPaddingLeft() + 8, ((TextView)localObject1).getPaddingTop(), 8 + ((TextView)localObject1).getPaddingRight(), ((TextView)localObject1).getPaddingBottom());
        }
      }
      return new aln((AlertDialog)localObject2, b.mNeedUpdate, c);
      i = 3;
      break;
      label598:
      ((AlertDialog.Builder)localObject2).setTitle(a);
      label608:
      i = 0;
      break label173;
      label613:
      ((AlertDialog.Builder)localObject2).setCancelable(false);
    }
  }
  
  private void d()
  {
    anf.b("register broadcast:" + d);
    IntentFilter localIntentFilter = new IntentFilter("com.meizu.update.component.dialog_show");
    a.getApplicationContext().registerReceiver(e, localIntentFilter);
  }
  
  private void e()
  {
    try
    {
      anf.b("unregister broadcast:" + d);
      a.getApplicationContext().unregisterReceiver(e);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void f()
  {
    Intent localIntent = new Intent("com.meizu.update.component.dialog_show");
    localIntent.setPackage(a.getPackageName());
    a.sendBroadcast(localIntent);
  }
  
  private void g()
  {
    try
    {
      anf.b("check keyguard state");
      Object localObject;
      if (ani.d())
      {
        localObject = (KeyguardManager)a.getSystemService("keyguard");
        if ((((KeyguardManager)localObject).isKeyguardLocked()) && (!((KeyguardManager)localObject).isKeyguardSecure())) {
          anf.d("need unlock keyguard");
        }
      }
      for (int i = 1; i != 0; i = 0)
      {
        localObject = new Intent(a, KeyguardHelperActivity.class);
        ((Intent)localObject).addFlags(268435456);
        a.startActivity((Intent)localObject);
        return;
        anf.d("need not unlock keyguard");
      }
      return;
    }
    catch (Exception localException)
    {
      anf.d("unlock keyguard exception");
      localException.printStackTrace();
    }
  }
  
  public abstract aks.a a();
  
  public void a(boolean paramBoolean)
  {
    c = paramBoolean;
  }
  
  public akg b()
  {
    try
    {
      akg localakg = c();
      return localakg;
    }
    catch (Exception localException)
    {
      anf.d("display dialog exception!");
      localException.printStackTrace();
    }
    return null;
  }
  
  public static class a
  {
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    aks.a.a g;
    
    public a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, aks.a.a parama)
    {
      a = paramString1;
      b = paramString2;
      c = paramString3;
      d = paramString4;
      e = paramString5;
      f = paramString6;
      g = parama;
    }
    
    public static abstract interface a
    {
      public abstract void a(aks.a.a.a parama);
      
      public static enum a
      {
        private a() {}
      }
    }
  }
}

/* Location:
 * Qualified Name:     aks
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */