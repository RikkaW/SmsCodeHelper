import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.transaction.MessagePopupService.b;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.view.MessagePopupGroupView;
import com.android.mms.view.MessagePopupGroupView.a;

public class aej
  implements MessagePopupGroupView.a
{
  public static WindowManager a = null;
  public static WindowManager.LayoutParams b = null;
  int c = 17;
  private aej.a d = aej.a.b;
  private final int e = ((Integer)aau.a("android.view.MeizuLayoutParams", "TYPE_MMS_POP_WINDOW")).intValue();
  private MessagePopupGroupView f;
  private volatile boolean g = false;
  private MessagePopupService h;
  private AsyncQueryHandler i;
  private MessagePopupService.b j;
  private boolean k = true;
  
  public aej(MessagePopupService paramMessagePopupService, AsyncQueryHandler paramAsyncQueryHandler)
  {
    h = paramMessagePopupService;
    i = paramAsyncQueryHandler;
  }
  
  private void a(MessagePopupService.b paramb, boolean paramBoolean)
  {
    if ((paramBoolean) && (j != null))
    {
      h.b(20000L);
      if (!k()) {
        break label58;
      }
      h.a(j);
      a(false);
    }
    for (;;)
    {
      j = paramb;
      f.a(paramb);
      return;
      label58:
      MessagingNotification.a(h, j.b, false, false);
    }
  }
  
  private void b(MessagePopupService.b paramb)
  {
    a(paramb, true);
  }
  
  private final void m()
  {
    b = new WindowManager.LayoutParams();
    WindowManager.LayoutParams localLayoutParams = b;
    width = -2;
    height = -2;
    flags = 520;
    format = -3;
    windowAnimations = 16973828;
    type = e;
    localLayoutParams.setTitle("MessagePopup");
    aau.a(localLayoutParams, 2048);
  }
  
  private final void n()
  {
    if (f != null) {
      return;
    }
    h.setTheme(2131624160);
    f = ((MessagePopupGroupView)LayoutInflater.from(h).inflate(2130968753, null));
    f.setGroupViewCallback(h);
    f.setAnimatorObjectCallback(this);
  }
  
  private void o()
  {
    Object localObject = f.getContext().getApplicationContext();
    if (localObject == null) {}
    for (a = (WindowManager)f.getContext().getSystemService("window");; a = (WindowManager)((Context)localObject).getSystemService("window"))
    {
      if (f.getParent() != null) {
        a.removeView(f);
      }
      a.addView(f, b);
      a(j, false);
      localObject = f.getContext().getResources().getConfiguration();
      int m = Gravity.getAbsoluteGravity(c, ((Configuration)localObject).getLayoutDirection());
      bgravity = m;
      MessagePopupService.a(false, 2, "PopupMessageService.MessagePopupWindowHelper", "prepareShowView-->wmParams.gravity = " + m);
      if ((m & 0x7) == 7) {
        bhorizontalWeight = 1.0F;
      }
      if ((m & 0x70) == 112) {
        bverticalWeight = 1.0F;
      }
      btype = e;
      bflags = 512;
      bx = h.getResources().getDimensionPixelSize(2131559378);
      by = 0;
      return;
    }
  }
  
  private void p()
  {
    f.d();
    bx = h.getResources().getDimensionPixelSize(2131559378);
    by = 0;
    bgravity = c;
    a.updateViewLayout(f, b);
  }
  
  private void q()
  {
    if (zv.a)
    {
      if (j != null)
      {
        Log.i("MessagePopupWindowHelper", "setIsCurrentSlotOnBind mMessageBean.mSlotId = " + j.o + ", mMessageBean.mImsi = " + j.p);
        j.q = zv.a(zv.c(h), j.p);
      }
      return;
    }
    j.q = true;
  }
  
  public int a()
  {
    return h.getResources().getDimensionPixelSize(2131559377);
  }
  
  public void a(float paramFloat)
  {
    if (f == null) {
      return;
    }
    bx = ((int)paramFloat);
    a.updateViewLayout(f, b);
  }
  
  public void a(long paramLong, String paramString)
  {
    if ((j == null) || (paramLong != j.b)) {
      return;
    }
    if (!TextUtils.isEmpty(paramString)) {
      j.m = true;
    }
    f.a(paramLong, paramString);
  }
  
  public void a(Configuration paramConfiguration)
  {
    p();
  }
  
  public final void a(MessagePopupService.b paramb)
  {
    h.a(true);
    int m;
    if ((j != null) && (j.a() != paramb.a()))
    {
      m = 1;
      j = paramb;
      q();
      if (b != null) {
        break label164;
      }
      m();
    }
    label164:
    while ((!g) || (f == null))
    {
      if (f == null) {
        n();
      }
      o();
      paramb = Message.obtain(i);
      what = 6;
      obj = j;
      paramb.sendToTarget();
      MessagePopupService.a(true, 2, "PopupMessageService.MessagePopupWindowHelper", "show popup window-->");
      i.removeMessages(3);
      f.getContext().getResources().getConfiguration();
      bsoftInputMode = 19;
      a.updateViewLayout(f, b);
      g = true;
      h.b();
      return;
      m = 0;
      break;
    }
    if (m != 0) {
      p();
    }
    b(paramb);
  }
  
  public void a(boolean paramBoolean)
  {
    if ((f == null) || (f.getVisibility() == 8)) {
      return;
    }
    f.setUserTouch(paramBoolean);
  }
  
  public void a(boolean paramBoolean, String paramString)
  {
    if ((g) && (f != null))
    {
      q();
      f.a(j, paramBoolean, paramString);
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    if (paramBoolean1) {
      h.a(j);
    }
    boolean bool = k();
    MessagePopupService.a(true, 2, "PopupMessageService.MessagePopupWindowHelper", "preparehideView(), markAsReadBeforeHidden = " + paramBoolean1 + ", autoHidden = " + paramBoolean2 + ", hasUserTouch = " + bool + ", method = " + paramString + ", " + d);
    i.removeMessages(3);
    if (f != null)
    {
      if (f.getParent() != null) {
        a.removeView(f);
      }
      f = null;
    }
    if (b != null) {
      balpha = 1.0F;
    }
    d = aej.a.b;
    g = false;
    h.a(j, paramBoolean2, bool, paramBoolean1, paramString);
    j = null;
  }
  
  public int b()
  {
    return h.getResources().getDimensionPixelSize(2131559337);
  }
  
  public void b(float paramFloat)
  {
    if (f == null) {
      return;
    }
    by = ((int)paramFloat);
    a.updateViewLayout(f, b);
  }
  
  public float c()
  {
    return bx;
  }
  
  public void c(float paramFloat)
  {
    if (f == null)
    {
      balpha = 1.0F;
      return;
    }
    balpha = paramFloat;
    a.updateViewLayout(f, b);
  }
  
  public float d()
  {
    return by;
  }
  
  public void e()
  {
    if ((i != null) && (g)) {
      i.removeMessages(3);
    }
  }
  
  public void f()
  {
    if ((i != null) && (g)) {
      h.b(20000L);
    }
  }
  
  public final boolean g()
  {
    return (f != null) && (f.getVisibility() == 0) && (g);
  }
  
  public boolean h()
  {
    return (f != null) && (d == aej.a.b);
  }
  
  public final MessagePopupService.b i()
  {
    if ((f == null) || (f.getVisibility() == 8)) {
      j = null;
    }
    return j;
  }
  
  public final String j()
  {
    if (!h()) {}
    while ((f == null) || (f.getVisibility() == 8)) {
      return null;
    }
    return f.getEditText();
  }
  
  public boolean k()
  {
    if ((f == null) || (f.getVisibility() == 8)) {
      return false;
    }
    return f.a();
  }
  
  public void l()
  {
    if ((g) && (f != null))
    {
      q();
      f.a(j.j, j.q, j.e);
    }
  }
  
  public static enum a
  {
    private a() {}
  }
}

/* Location:
 * Qualified Name:     aej
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */