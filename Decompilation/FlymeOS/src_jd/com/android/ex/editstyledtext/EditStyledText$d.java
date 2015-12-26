package com.android.ex.editstyledtext;

import android.content.Context;
import android.text.Editable;
import android.text.Layout.Alignment;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

class EditStyledText$d
{
  private boolean b;
  private boolean c;
  private boolean d;
  private boolean e;
  private boolean f;
  private int g;
  private int h;
  private int i;
  private int j;
  private int k;
  private int l;
  private int m;
  private BackgroundColorSpan n;
  private EditStyledText o;
  private EditStyledText.a p;
  private EditStyledText.SoftKeyReceiver q;
  private SpannableStringBuilder r;
  
  private int a(Editable paramEditable, int paramInt)
  {
    int i1 = paramInt;
    for (;;)
    {
      if ((i1 <= 0) || (paramEditable.charAt(i1 - 1) == '\n'))
      {
        Log.d("EditStyledText.EditorManager", "--- findLineStart:" + paramInt + "," + paramEditable.length() + "," + i1);
        return i1;
      }
      i1 -= 1;
    }
  }
  
  private SpannableStringBuilder a(SpannableStringBuilder paramSpannableStringBuilder)
  {
    int i1 = 0;
    paramSpannableStringBuilder = new SpannableStringBuilder(paramSpannableStringBuilder);
    DynamicDrawableSpan[] arrayOfDynamicDrawableSpan = (DynamicDrawableSpan[])paramSpannableStringBuilder.getSpans(0, paramSpannableStringBuilder.length(), DynamicDrawableSpan.class);
    int i2 = arrayOfDynamicDrawableSpan.length;
    while (i1 < i2)
    {
      DynamicDrawableSpan localDynamicDrawableSpan = arrayOfDynamicDrawableSpan[i1];
      if (((localDynamicDrawableSpan instanceof EditStyledText.c.b)) || ((localDynamicDrawableSpan instanceof EditStyledText.c.d))) {
        paramSpannableStringBuilder.replace(paramSpannableStringBuilder.getSpanStart(localDynamicDrawableSpan), paramSpannableStringBuilder.getSpanEnd(localDynamicDrawableSpan), "");
      }
      i1 += 1;
    }
    return paramSpannableStringBuilder;
  }
  
  private void a(Object paramObject)
  {
    int i3 = Math.min(i, j);
    int i2 = Math.max(i, j);
    int i1 = o.getSelectionStart();
    i3 = a(o.getText(), i3);
    i2 = b(o.getText(), i2);
    if (i3 == i2)
    {
      o.getText().insert(i2, "\n");
      a(paramObject, i3, i2 + 1);
    }
    for (;;)
    {
      Selection.setSelection(o.getText(), i1);
      return;
      a(paramObject, i3, i2);
    }
  }
  
  private void a(Object paramObject, int paramInt1, int paramInt2)
  {
    Log.d("EditStyledText.EditorManager", "--- setStyledTextSpan:" + g + "," + paramInt1 + "," + paramInt2);
    int i1 = Math.min(paramInt1, paramInt2);
    paramInt1 = Math.max(paramInt1, paramInt2);
    o.getText().setSpan(paramObject, i1, paramInt1, 33);
    Selection.setSelection(o.getText(), paramInt1);
  }
  
  private int b(Editable paramEditable, int paramInt)
  {
    int i1 = paramInt;
    for (;;)
    {
      int i2 = i1;
      if (i1 < paramEditable.length())
      {
        if (paramEditable.charAt(i1) == '\n') {
          i2 = i1 + 1;
        }
      }
      else
      {
        Log.d("EditStyledText.EditorManager", "--- findLineEnd:" + paramInt + "," + paramEditable.length() + "," + i2);
        return i2;
      }
      i1 += 1;
    }
  }
  
  private void b(Layout.Alignment paramAlignment)
  {
    a(new AlignmentSpan.Standard(paramAlignment));
  }
  
  private void d(int paramInt)
  {
    if (i != j)
    {
      a(new AbsoluteSizeSpan(paramInt), i, j);
      return;
    }
    Log.e("EditStyledText.EditorManager", "---changeSize: Size of the span is zero");
  }
  
  private void e(int paramInt)
  {
    if (i != j)
    {
      a(new ForegroundColorSpan(paramInt), i, j);
      return;
    }
    Log.e("EditStyledText.EditorManager", "---changeColor: Size of the span is zero");
  }
  
  private void f(int paramInt)
  {
    Log.d("EditStyledText.EditorManager", "--- addMarquee:" + paramInt);
    a(new EditStyledText.c.c(paramInt, o.getBackgroundColor()));
  }
  
  private void r()
  {
    Log.d("EditStyledText.EditorManager", "--- handleCancel");
    g = 0;
    h = 0;
    b = false;
    k = 16777215;
    l = 0;
    e = false;
    c = false;
    d = false;
    f = false;
    v();
    o.setOnClickListener(null);
    q();
  }
  
  private void s()
  {
    Log.d("EditStyledText.EditorManager", "--- handleComplete:" + i + "," + j);
    if (!b) {
      return;
    }
    if (i == j)
    {
      Log.d("EditStyledText.EditorManager", "--- cancel handle complete:" + i);
      u();
      return;
    }
    if (h == 2) {
      h = 3;
    }
    p.b(g);
    EditStyledText.a(o, o.getText());
  }
  
  private void t()
  {
    if (!b) {
      return;
    }
    p.a(11);
  }
  
  private void u()
  {
    r();
    b = true;
    EditStyledText.a(o, g, h);
  }
  
  private void v()
  {
    Log.d("EditStyledText.EditorManager", "--- offSelect");
    EditStyledText.a(o, o.getText());
    int i1 = o.getSelectionStart();
    o.setSelection(i1, i1);
    h = 0;
  }
  
  private boolean w()
  {
    Log.d("EditStyledText.EditorManager", "--- waitingNext:" + i + "," + j + "," + h);
    if ((i == j) && (h == 3))
    {
      x();
      return true;
    }
    y();
    return false;
  }
  
  private void x()
  {
    Log.d("EditStyledText.EditorManager", "--- waitSelection");
    e = true;
    if (i == j) {}
    for (h = 1;; h = 2)
    {
      EditStyledText.b(o, o.getText());
      return;
    }
  }
  
  private void y()
  {
    Log.d("EditStyledText.EditorManager", "--- resumeSelection");
    e = false;
    h = 3;
    EditStyledText.a(o, o.getText());
  }
  
  public void a()
  {
    Log.d("EditStyledText.EditorManager", "--- onClickView");
    if ((h == 1) || (h == 2))
    {
      p.a();
      EditStyledText.a(o, g, h);
    }
  }
  
  public void a(int paramInt)
  {
    a(paramInt, true);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    Log.d("EditStyledText", "--- setTextComposingMask:" + paramInt1 + "," + paramInt2);
    int i1 = Math.min(paramInt1, paramInt2);
    paramInt2 = Math.max(paramInt1, paramInt2);
    if ((j()) && (k != 16777215)) {}
    for (paramInt1 = k;; paramInt1 = o.a(i1))
    {
      int i2 = o.getBackgroundColor();
      Log.d("EditStyledText", "--- fg:" + Integer.toHexString(paramInt1) + ",bg:" + Integer.toHexString(i2) + "," + j() + "," + "," + g);
      if (paramInt1 == i2)
      {
        paramInt1 = 0x80000000 | (i2 | 0xFF000000) ^ 0xFFFFFFFF;
        if ((n == null) || (n.getBackgroundColor() != paramInt1)) {
          n = new BackgroundColorSpan(paramInt1);
        }
        o.getText().setSpan(n, i1, paramInt2, 33);
      }
      return;
    }
  }
  
  public void a(int paramInt, boolean paramBoolean)
  {
    p.a(paramInt);
    if (paramBoolean) {
      EditStyledText.a(o, g, h);
    }
  }
  
  public void a(Editable paramEditable, int paramInt1, int paramInt2, int paramInt3)
  {
    Log.d("EditStyledText.EditorManager", "updateSpanPrevious:" + paramInt1 + "," + paramInt2 + "," + paramInt3);
    int i4 = paramInt1 + paramInt3;
    int i1 = Math.min(paramInt1, i4);
    int i2 = Math.max(paramInt1, i4);
    Object[] arrayOfObject = paramEditable.getSpans(i1, i1, Object.class);
    int i5 = arrayOfObject.length;
    i1 = 0;
    Object localObject;
    int i6;
    int i3;
    if (i1 < i5)
    {
      localObject = arrayOfObject[i1];
      if (((localObject instanceof ForegroundColorSpan)) || ((localObject instanceof AbsoluteSizeSpan)) || ((localObject instanceof EditStyledText.c.c)) || ((localObject instanceof AlignmentSpan)))
      {
        i6 = paramEditable.getSpanStart(localObject);
        i3 = paramEditable.getSpanEnd(localObject);
        Log.d("EditStyledText.EditorManager", "spantype:" + localObject.getClass() + "," + i6);
        if (((localObject instanceof EditStyledText.c.c)) || ((localObject instanceof AlignmentSpan))) {
          paramInt1 = b(o.getText(), i2);
        }
      }
    }
    for (;;)
    {
      label225:
      if (i3 < paramInt1)
      {
        Log.d("EditStyledText.EditorManager", "updateSpanPrevious: extend span");
        paramEditable.setSpan(localObject, i6, paramInt1, 33);
      }
      for (;;)
      {
        i1 += 1;
        break;
        if (!d) {
          break label389;
        }
        paramInt1 = i3;
        break label225;
        if ((localObject instanceof EditStyledText.c.b))
        {
          paramInt1 = paramEditable.getSpanStart(localObject);
          i3 = paramEditable.getSpanEnd(localObject);
          if (paramInt2 > paramInt3)
          {
            paramEditable.replace(paramInt1, i3, "");
            paramEditable.removeSpan(localObject);
          }
          else if ((i3 == i4) && (i4 < paramEditable.length()) && (o.getText().charAt(i4) != '\n'))
          {
            o.getText().insert(i4, "\n");
          }
        }
      }
      return;
      label389:
      paramInt1 = i2;
    }
  }
  
  public void a(Layout.Alignment paramAlignment)
  {
    if ((h == 2) || (h == 3))
    {
      b(paramAlignment);
      u();
    }
  }
  
  public void a(boolean paramBoolean)
  {
    Log.d("EditStyledText.EditorManager", "--- onClickSelect");
    g = 5;
    if (h == 0) {
      p.a();
    }
    for (;;)
    {
      if (paramBoolean) {
        EditStyledText.a(o, g, h);
      }
      return;
      v();
      p.a();
    }
  }
  
  public void b()
  {
    Log.d("EditStyledText.EditorManager", "--- onFixSelectedItem");
    s();
    EditStyledText.a(o, g, h);
  }
  
  public void b(int paramInt)
  {
    m = paramInt;
  }
  
  public void b(int paramInt1, int paramInt2)
  {
    Log.d("EditStyledText.EditorManager", "--- showsoftkey");
    if ((!o.isFocused()) || (i())) {}
    do
    {
      return;
      q.a = Selection.getSelectionStart(o.getText());
      q.b = Selection.getSelectionEnd(o.getText());
    } while ((!((InputMethodManager)a.getContext().getSystemService("input_method")).showSoftInput(o, 0, q)) || (q == null));
    Selection.setSelection(a.getText(), paramInt1, paramInt2);
  }
  
  public void b(int paramInt, boolean paramBoolean)
  {
    Log.d("EditStyledText.EditorManager", "--- setItemSize");
    if (w()) {
      l = paramInt;
    }
    do
    {
      do
      {
        return;
      } while ((h != 2) && (h != 3));
      if (paramInt > 0) {
        d(paramInt);
      }
    } while (!paramBoolean);
    u();
  }
  
  public void b(Editable paramEditable, int paramInt1, int paramInt2, int paramInt3)
  {
    Log.d("EditStyledText.EditorManager", "updateSpanNext:" + paramInt1 + "," + paramInt2 + "," + paramInt3);
    int i3 = paramInt1 + paramInt3;
    int i2 = Math.min(paramInt1, i3);
    paramInt1 = Math.max(paramInt1, i3);
    Object[] arrayOfObject = paramEditable.getSpans(paramInt1, paramInt1, Object.class);
    int i4 = arrayOfObject.length;
    paramInt1 = 0;
    Object localObject;
    int i5;
    int i6;
    if (paramInt1 < i4)
    {
      localObject = arrayOfObject[paramInt1];
      if (((localObject instanceof EditStyledText.c.c)) || ((localObject instanceof AlignmentSpan)))
      {
        i5 = paramEditable.getSpanStart(localObject);
        i6 = paramEditable.getSpanEnd(localObject);
        Log.d("EditStyledText.EditorManager", "spantype:" + localObject.getClass() + "," + i6);
        if ((!(localObject instanceof EditStyledText.c.c)) && (!(localObject instanceof AlignmentSpan))) {
          break label333;
        }
      }
    }
    label333:
    for (int i1 = a(o.getText(), i2);; i1 = i2)
    {
      if ((i1 < i5) && (paramInt2 > paramInt3)) {
        paramEditable.removeSpan(localObject);
      }
      for (;;)
      {
        paramInt1 += 1;
        break;
        if (i5 > i2)
        {
          paramEditable.setSpan(localObject, i2, i6, 33);
          continue;
          if (((localObject instanceof EditStyledText.c.b)) && (paramEditable.getSpanStart(localObject) == i3) && (i3 > 0) && (o.getText().charAt(i3 - 1) != '\n'))
          {
            o.getText().insert(i3, "\n");
            o.setSelection(i3);
          }
        }
      }
      return;
    }
  }
  
  public void b(boolean paramBoolean)
  {
    Log.d("EditStyledText.EditorManager", "--- onClickSelectAll");
    t();
    if (paramBoolean) {
      EditStyledText.a(o, g, h);
    }
  }
  
  public void c()
  {
    p.a(14);
  }
  
  public void c(int paramInt)
  {
    if ((h == 2) || (h == 3))
    {
      f(paramInt);
      u();
    }
  }
  
  public void c(int paramInt, boolean paramBoolean)
  {
    Log.d("EditStyledText.EditorManager", "--- setItemColor");
    if (w()) {
      k = paramInt;
    }
    do
    {
      do
      {
        return;
      } while ((h != 2) && (h != 3));
      if (paramInt != 16777215) {
        e(paramInt);
      }
    } while (!paramBoolean);
    u();
  }
  
  public void d()
  {
    Editable localEditable = o.getText();
    int i2;
    for (int i1 = 0; i1 < localEditable.length(); i1 = i2 + 1)
    {
      i2 = i1;
      if (localEditable.charAt(i1) == '⁠')
      {
        localEditable.replace(i1, i1 + 1, "");
        i2 = i1 - 1;
      }
    }
  }
  
  public void e()
  {
    Log.d("EditStyledText.EditorManager", "--- onRefreshStyles");
    Editable localEditable = o.getText();
    int i2 = localEditable.length();
    int i3 = o.getWidth();
    EditStyledText.c.b[] arrayOfb = (EditStyledText.c.b[])localEditable.getSpans(0, i2, EditStyledText.c.b.class);
    int i4 = arrayOfb.length;
    int i1 = 0;
    while (i1 < i4)
    {
      arrayOfb[i1].a(i3);
      i1 += 1;
    }
    EditStyledText.c.c[] arrayOfc = (EditStyledText.c.c[])localEditable.getSpans(0, i2, EditStyledText.c.c.class);
    i2 = arrayOfc.length;
    i1 = 0;
    while (i1 < i2)
    {
      arrayOfc[i1].a(o.getBackgroundColor());
      i1 += 1;
    }
    if (arrayOfb.length > 0) {
      localEditable.replace(0, 1, "" + localEditable.charAt(0));
    }
  }
  
  public void f()
  {
    Log.d("EditStyledText", "--- unsetTextComposingMask");
    if (n != null)
    {
      o.getText().removeSpan(n);
      n = null;
    }
  }
  
  public boolean g()
  {
    return b;
  }
  
  public boolean h()
  {
    Editable localEditable = o.getText();
    int i1 = localEditable.length();
    return (((ParagraphStyle[])localEditable.getSpans(0, i1, ParagraphStyle.class)).length > 0) || (((QuoteSpan[])localEditable.getSpans(0, i1, QuoteSpan.class)).length > 0) || (((CharacterStyle[])localEditable.getSpans(0, i1, CharacterStyle.class)).length > 0) || (m != 16777215);
  }
  
  public boolean i()
  {
    return c;
  }
  
  public boolean j()
  {
    return e;
  }
  
  public int k()
  {
    return m;
  }
  
  public int l()
  {
    return g;
  }
  
  public int m()
  {
    return h;
  }
  
  public boolean n()
  {
    return (r != null) && (r.length() > 0) && (a(r).length() == 0);
  }
  
  public void o()
  {
    Log.d("EditStyledText.EditorManager", "--- hidesoftkey");
    if (!o.isFocused()) {
      return;
    }
    q.a = Selection.getSelectionStart(o.getText());
    q.b = Selection.getSelectionEnd(o.getText());
    ((InputMethodManager)o.getContext().getSystemService("input_method")).hideSoftInputFromWindow(o.getWindowToken(), 0, q);
  }
  
  public void p()
  {
    Log.d("EditStyledText.EditorManager", "--- blockSoftKey:");
    o();
    c = true;
  }
  
  public void q()
  {
    Log.d("EditStyledText.EditorManager", "--- unblockSoftKey:");
    c = false;
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */