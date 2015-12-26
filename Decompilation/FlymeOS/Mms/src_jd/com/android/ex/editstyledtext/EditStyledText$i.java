package com.android.ex.editstyledtext;

import android.util.Log;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

public class EditStyledText$i
  extends InputConnectionWrapper
{
  EditStyledText a;
  
  public EditStyledText$i(InputConnection paramInputConnection, EditStyledText paramEditStyledText)
  {
    super(paramInputConnection, true);
    a = paramEditStyledText;
  }
  
  public boolean commitText(CharSequence paramCharSequence, int paramInt)
  {
    Log.d("EditStyledText", "--- commitText:");
    EditStyledText.e(a).f();
    return super.commitText(paramCharSequence, paramInt);
  }
  
  public boolean finishComposingText()
  {
    Log.d("EditStyledText", "--- finishcomposing:");
    if ((!a.n()) && (!a.a()) && (!a.l())) {
      a.c();
    }
    return super.finishComposingText();
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */