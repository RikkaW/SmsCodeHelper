import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import com.android.mms.MmsApp;

public class ael
  extends LinkMovementMethod
{
  private static ael a;
  private boolean b = true;
  private ClickableSpan c;
  private TextView d;
  private Handler e = new Handler();
  private Runnable f = new aem(this);
  
  public static MovementMethod a()
  {
    if (a == null) {
      a = new ael();
    }
    return a;
  }
  
  public static void a(ClickableSpan paramClickableSpan, TextView paramTextView)
  {
    if ((paramClickableSpan instanceof URLSpan))
    {
      if (wd.c(MmsApp.c().getContentResolver()))
      {
        wd.m(MmsApp.c());
        return;
      }
      String str = ((URLSpan)paramClickableSpan).getURL();
      Log.i("MmsLinkMovementMethod", "customeClick tempURL = " + str);
      if ((str != null) && (kn.c(str)) && (wd.a("com.autonavi.minimap", paramTextView.getContext())))
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.addFlags(524288);
        localIntent.setPackage("com.autonavi.minimap");
        localIntent.setData(wd.l(str));
        try
        {
          paramTextView.getContext().startActivity(localIntent);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          paramClickableSpan.onClick(paramTextView);
          return;
        }
      }
    }
    paramClickableSpan.onClick(paramTextView);
  }
  
  private void b(ClickableSpan paramClickableSpan, TextView paramTextView)
  {
    c = paramClickableSpan;
    d = paramTextView;
    e.postDelayed(f, 200L);
  }
  
  public static void c()
  {
    if (a != null) {
      a = null;
    }
  }
  
  public void b()
  {
    e.removeCallbacks(f);
  }
  
  public boolean onTouchEvent(TextView paramTextView, Spannable paramSpannable, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i == 1) || (i == 0))
    {
      int j = (int)paramMotionEvent.getX();
      int k = (int)paramMotionEvent.getY();
      int m = paramTextView.getTotalPaddingLeft();
      int n = paramTextView.getTotalPaddingTop();
      int i1 = paramTextView.getScrollX();
      int i2 = paramTextView.getScrollY();
      Object localObject = paramTextView.getLayout();
      j = ((Layout)localObject).getOffsetForHorizontal(((Layout)localObject).getLineForVertical(k - n + i2), j - m + i1);
      localObject = (ClickableSpan[])paramSpannable.getSpans(j, j, ClickableSpan.class);
      if (localObject.length != 0)
      {
        if (i == 1) {
          b(localObject[0], paramTextView);
        }
        for (;;)
        {
          return true;
          if (i == 0) {
            Selection.setSelection(paramSpannable, paramSpannable.getSpanStart(localObject[0]), paramSpannable.getSpanEnd(localObject[0]));
          }
        }
      }
      Selection.removeSelection(paramSpannable);
    }
    return super.onTouchEvent(paramTextView, paramSpannable, paramMotionEvent);
  }
}

/* Location:
 * Qualified Name:     ael
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */