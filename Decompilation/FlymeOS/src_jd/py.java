import android.text.Editable;
import android.text.TextWatcher;
import com.android.mms.ui.BasicSlideEditorView;
import com.android.mms.ui.BasicSlideEditorView.a;

public class py
  implements TextWatcher
{
  public py(BasicSlideEditorView paramBasicSlideEditorView) {}
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((BasicSlideEditorView.a(a)) && (BasicSlideEditorView.b(a) != null)) {
      BasicSlideEditorView.b(a).a(paramCharSequence.toString());
    }
  }
}

/* Location:
 * Qualified Name:     py
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */