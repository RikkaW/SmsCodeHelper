import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import com.meizu.commonwidget.ParcelableImageSpan;
import com.meizu.commonwidget.RecipientEdit;
import com.meizu.commonwidget.RecipientEdit.RecipientAutoCompleteTextView;
import com.meizu.commonwidget.RecipientEdit.e;
import com.meizu.commonwidget.RecipientEdit.h;
import com.meizu.commonwidget.RecipientEdit.j;

public class ail
  implements TextWatcher
{
  private int b;
  
  public ail(RecipientEdit paramRecipientEdit) {}
  
  public void afterTextChanged(Editable paramEditable)
  {
    if (((ParcelableImageSpan[])paramEditable.getSpans(0, paramEditable.length(), ParcelableImageSpan.class)).length > 0) {}
    label242:
    label272:
    label412:
    CharSequence localCharSequence;
    do
    {
      do
      {
        do
        {
          return;
          int i;
          if (RecipientEdit.f(a))
          {
            i = RecipientEdit.a(a).getImeOptions() & 0xFF00;
            if ((!TextUtils.isEmpty(paramEditable)) || ((RecipientEdit.a(a).getImeOptions() & 0xFF) != 1)) {
              break label272;
            }
            if (a.focusSearch(130) == null) {
              break label242;
            }
            RecipientEdit.a(a).setImeOptions(i | 0x5);
            RecipientEdit.a(a).setImeActionLabel(null, 5);
          }
          for (;;)
          {
            if (a.d != null) {
              a.d.a();
            }
            i = paramEditable.length();
            if ((b != 1) || (paramEditable.charAt(i - 1) != ' ') || (i <= 1)) {
              break label412;
            }
            int j = paramEditable.charAt(i - 2);
            if ((j != 44) && (j != 59)) {
              break;
            }
            a.a(paramEditable.subSequence(0, i - 2).toString().trim());
            RecipientEdit.a(a).setText("");
            a.b(a.hasFocus());
            return;
            RecipientEdit.a(a).setImeOptions(i | 0x6);
            RecipientEdit.a(a).setImeActionLabel(null, 6);
            continue;
            if (((RecipientEdit.a(a).getImeOptions() & 0xFF) != 1) && (!TextUtils.isEmpty(paramEditable)))
            {
              RecipientEdit.a(a).setImeOptions(i | 0x1);
              RecipientEdit.a(a).setImeActionLabel(a.getResources().getString(aih.f.mw_recipient_edit_imeActionLabel), 1);
            }
          }
          paramEditable = paramEditable.subSequence(0, i - 1).toString().trim();
        } while (!((aii)RecipientEdit.a(a).getAdapter()).d(paramEditable));
        a.a(paramEditable);
        RecipientEdit.a(a).setText("");
        a.b(a.hasFocus());
        return;
      } while ((b <= 1) || (BaseInputConnection.getComposingSpanStart(paramEditable) >= 0));
      localCharSequence = RecipientEdit.a(a, paramEditable);
    } while (TextUtils.equals(paramEditable, localCharSequence));
    RecipientEdit.a(a).setText(localCharSequence);
    RecipientEdit.a(a).setSelection(localCharSequence.length());
    a.b(a.hasFocus());
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (RecipientEdit.e(a) != null)
    {
      RecipientEdit.e(a).setSelected(false);
      RecipientEdit.a(a, RecipientEdit.e(a));
      RecipientEdit.b(a, null);
      RecipientEdit.a(a).setCursorVisible(true);
    }
    if (RecipientEdit.d(a) != null) {
      RecipientEdit.d(a).a(paramCharSequence.toString(), paramInt1, paramInt2, paramInt3);
    }
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    b = paramInt3;
    if (RecipientEdit.c(a) != null) {
      RecipientEdit.c(a).a(paramCharSequence, paramInt1, paramInt2, paramInt3);
    }
    if (RecipientEdit.d(a) != null) {
      RecipientEdit.d(a).b(paramCharSequence.toString(), paramInt1, paramInt2, paramInt3);
    }
  }
}

/* Location:
 * Qualified Name:     ail
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */