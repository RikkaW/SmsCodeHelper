import android.text.Selection;
import android.text.Spannable;
import android.text.method.ArrowKeyMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.widget.TextView;
import com.android.mms.view.MmsFoldableTextView;

class sk
  implements Runnable
{
  sk(sj paramsj, TextView paramTextView, me paramme) {}
  
  public void run()
  {
    if (!a.isFocusable()) {
      a.setFocusable(true);
    }
    if (!a.isFocusableInTouchMode()) {
      a.setFocusableInTouchMode(true);
    }
    if (!(a.getMovementMethod() instanceof ArrowKeyMovementMethod)) {
      a.setMovementMethod(ArrowKeyMovementMethod.getInstance());
    }
    Object localObject = (MmsFoldableTextView)a;
    int j = ((MmsFoldableTextView)localObject).getOffsetForPosition(((MmsFoldableTextView)localObject).getTouchX(), ((MmsFoldableTextView)localObject).getTouchY());
    Log.d("Mms/compose", "before offset : " + j);
    int i;
    if (j <= 0)
    {
      i = 0;
      if (i > 0) {
        break label325;
      }
      i = 0;
    }
    label182:
    label325:
    for (;;)
    {
      Log.d("Mms/compose", "after offset : " + i);
      localObject = (Spannable)a.getText();
      URLSpan[] arrayOfURLSpan = (URLSpan[])((Spannable)localObject).getSpans(0, ((Spannable)localObject).length(), URLSpan.class);
      j = 0;
      int k;
      if (j < arrayOfURLSpan.length)
      {
        k = ((Spannable)localObject).getSpanStart(arrayOfURLSpan[j]);
        int m = ((Spannable)localObject).getSpanEnd(arrayOfURLSpan[j]);
        if ((i >= k) && (i <= m)) {
          j = m;
        }
      }
      for (;;)
      {
        if ((k == 0) && (j == 0))
        {
          j = i + 1;
          k = i;
        }
        for (i = j;; i = j)
        {
          Selection.setSelection((Spannable)a.getText(), k, i);
          b.a();
          return;
          i = j;
          if (j < a.getText().length() - 1) {
            break;
          }
          i = a.getText().length() - 2;
          break;
          j += 1;
          break label182;
        }
        j = 0;
        k = 0;
      }
    }
  }
}

/* Location:
 * Qualified Name:     sk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */