package com.android.ex.editstyledtext;

import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Layout.Alignment;
import android.text.NoCopySpan.Concrete;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import fi;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EditStyledText
  extends EditText
{
  private static CharSequence a;
  private static CharSequence b;
  private static CharSequence c;
  private static final NoCopySpan.Concrete k = new NoCopySpan.Concrete();
  private float d;
  private ArrayList<b> e;
  private Drawable f;
  private d g;
  private InputConnection h;
  private f i;
  private g j;
  
  private void a(int paramInt1, int paramInt2)
  {
    if (e != null)
    {
      Iterator localIterator = e.iterator();
      while (localIterator.hasNext()) {
        ((b)localIterator.next()).a(paramInt1, paramInt2);
      }
    }
  }
  
  private void a(MotionEvent paramMotionEvent)
  {
    if (e != null)
    {
      Iterator localIterator = e.iterator();
      while (localIterator.hasNext()) {
        ((b)localIterator.next()).a(paramMotionEvent);
      }
    }
  }
  
  private int b(int paramInt)
  {
    if (d <= 0.0F) {
      d = getContextgetResourcesgetDisplayMetricsdensity;
    }
    return (int)(paramInt * getPaddingScale() + 0.5D);
  }
  
  private static void c(View paramView, Spannable paramSpannable)
  {
    paramSpannable.setSpan(k, 0, 0, 16777233);
  }
  
  private static void d(View paramView, Spannable paramSpannable)
  {
    paramSpannable.removeSpan(k);
  }
  
  private int getMaxImageWidthDip()
  {
    return 300;
  }
  
  private int getMaxImageWidthPx()
  {
    return b(300);
  }
  
  private float getPaddingScale()
  {
    if (d <= 0.0F) {
      d = getContextgetResourcesgetDisplayMetricsdensity;
    }
    return d;
  }
  
  private void o()
  {
    g.e();
  }
  
  private void p()
  {
    g.d();
  }
  
  public int a(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > getText().length())) {
      return -16777216;
    }
    ForegroundColorSpan[] arrayOfForegroundColorSpan = (ForegroundColorSpan[])getText().getSpans(paramInt, paramInt, ForegroundColorSpan.class);
    if (arrayOfForegroundColorSpan.length > 0) {
      return arrayOfForegroundColorSpan[0].getForegroundColor();
    }
    return -16777216;
  }
  
  public boolean a()
  {
    if (e != null)
    {
      Iterator localIterator = e.iterator();
      for (boolean bool1 = false;; bool1 = ((b)localIterator.next()).a() | bool1)
      {
        bool2 = bool1;
        if (!localIterator.hasNext()) {
          break;
        }
      }
    }
    boolean bool2 = false;
    return bool2;
  }
  
  public void b()
  {
    g.a(20);
  }
  
  public void c()
  {
    g.a(21);
  }
  
  public void d()
  {
    g.a(1);
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    if (g != null) {
      g.e();
    }
  }
  
  public void e()
  {
    g.a(7);
  }
  
  public void f()
  {
    g.a(2);
  }
  
  public void g()
  {
    g.a(true);
  }
  
  public int getBackgroundColor()
  {
    return g.k();
  }
  
  public int getEditMode()
  {
    return g.l();
  }
  
  public d getEditStyledTextManager()
  {
    return g;
  }
  
  public String getHtml()
  {
    return i.a(true);
  }
  
  public String getPreviewHtml()
  {
    return i.a();
  }
  
  public int getSelectState()
  {
    return g.m();
  }
  
  public void h()
  {
    g.b(true);
  }
  
  public void i()
  {
    g.b();
  }
  
  public void j()
  {
    g.a(12);
  }
  
  public void k()
  {
    g.c();
  }
  
  public boolean l()
  {
    return g.g();
  }
  
  public boolean m()
  {
    return g.h();
  }
  
  public boolean n()
  {
    return g.i();
  }
  
  protected void onCreateContextMenu(ContextMenu paramContextMenu)
  {
    super.onCreateContextMenu(paramContextMenu);
    e locale = new e(null);
    if (a != null) {
      paramContextMenu.add(0, 16776961, 0, a).setOnMenuItemClickListener(locale);
    }
    if ((m()) && (b != null)) {
      paramContextMenu.add(0, 16776962, 0, b).setOnMenuItemClickListener(locale);
    }
    if (g.n()) {
      paramContextMenu.add(0, 16908322, 0, c).setOnMenuItemClickListener(locale).setAlphabeticShortcut('v');
    }
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    h = new i(super.onCreateInputConnection(paramEditorInfo), this);
    return h;
  }
  
  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    super.onFocusChanged(paramBoolean, paramInt, paramRect);
    if (paramBoolean) {
      b();
    }
    while (a()) {
      return;
    }
    c();
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedStyledTextState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedStyledTextState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setBackgroundColor(a);
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedStyledTextState localSavedStyledTextState = new SavedStyledTextState(super.onSaveInstanceState());
    a = g.k();
    return localSavedStyledTextState;
  }
  
  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (g != null)
    {
      g.b(getText(), paramInt1, paramInt2, paramInt3);
      g.a(getText(), paramInt1, paramInt2, paramInt3);
      if (paramInt3 <= paramInt2) {
        break label92;
      }
      g.a(paramInt1, paramInt1 + paramInt3);
      if (g.j())
      {
        if (paramInt3 <= paramInt2) {
          break label108;
        }
        g.a();
        i();
      }
    }
    for (;;)
    {
      super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
      return;
      label92:
      if (paramInt2 >= paramInt3) {
        break;
      }
      g.f();
      break;
      label108:
      if (paramInt3 < paramInt2) {
        g.a(22);
      }
    }
  }
  
  public boolean onTextContextMenuItem(int paramInt)
  {
    int m;
    if (getSelectionStart() != getSelectionEnd()) {
      m = 1;
    }
    switch (paramInt)
    {
    default: 
    case 16908319: 
    case 16908328: 
    case 16908329: 
      for (;;)
      {
        return super.onTextContextMenuItem(paramInt);
        m = 0;
        break;
        h();
        return true;
        g();
        g.p();
        continue;
        i();
      }
    case 16908322: 
      f();
      return true;
    case 16908321: 
      if (m != 0)
      {
        d();
        return true;
      }
      g.b(false);
      d();
      return true;
    case 16908320: 
      if (m != 0)
      {
        e();
        return true;
      }
      g.b(false);
      e();
      return true;
    case 16776961: 
      j();
      return true;
    case 16776962: 
      k();
      return true;
    case 16776963: 
      b();
      return true;
    }
    c();
    return true;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int m;
    int n;
    boolean bool1;
    if (paramMotionEvent.getAction() == 1)
    {
      cancelLongPress();
      boolean bool2 = l();
      if (!bool2) {
        b();
      }
      m = Selection.getSelectionStart(getText());
      n = Selection.getSelectionEnd(getText());
      bool1 = super.onTouchEvent(paramMotionEvent);
      if ((isFocused()) && (getSelectState() == 0))
      {
        if (bool2) {
          g.b(Selection.getSelectionStart(getText()), Selection.getSelectionEnd(getText()));
        }
      }
      else
      {
        g.a();
        g.f();
      }
    }
    for (;;)
    {
      a(paramMotionEvent);
      return bool1;
      g.b(m, n);
      break;
      bool1 = super.onTouchEvent(paramMotionEvent);
    }
  }
  
  public void setAlignment(Layout.Alignment paramAlignment)
  {
    g.a(paramAlignment);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    if (paramInt != 16777215) {
      super.setBackgroundColor(paramInt);
    }
    for (;;)
    {
      g.b(paramInt);
      o();
      return;
      setBackgroundDrawable(f);
    }
  }
  
  public void setBuilder(AlertDialog.Builder paramBuilder)
  {
    j.a(paramBuilder);
  }
  
  public void setHtml(String paramString)
  {
    i.a(paramString);
  }
  
  public void setItemColor(int paramInt)
  {
    g.c(paramInt, true);
  }
  
  public void setItemSize(int paramInt)
  {
    g.b(paramInt, true);
  }
  
  public void setMarquee(int paramInt)
  {
    g.c(paramInt);
  }
  
  public void setStyledTextHtmlConverter(h paramh)
  {
    i.a(paramh);
  }
  
  public static class SavedStyledTextState
    extends View.BaseSavedState
  {
    public int a;
    
    SavedStyledTextState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      return "EditStyledText.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " bgcolor=" + a + "}";
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(a);
    }
  }
  
  static class SoftKeyReceiver
    extends ResultReceiver
  {
    int a;
    int b;
    EditStyledText c;
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      if (paramInt != 2) {
        Selection.setSelection(c.getText(), a, b);
      }
    }
  }
  
  public class a
  {
    private EditStyledText.d a;
    private int b;
    private HashMap<Integer, a> c;
    
    private a c(int paramInt)
    {
      if (c.containsKey(Integer.valueOf(paramInt))) {
        return (a)c.get(Integer.valueOf(paramInt));
      }
      return null;
    }
    
    public void a()
    {
      b(5);
    }
    
    public void a(int paramInt)
    {
      a(paramInt, null);
    }
    
    public void a(int paramInt, Object[] paramArrayOfObject)
    {
      c(paramInt).a(paramArrayOfObject);
      b = paramInt;
      b(paramInt);
    }
    
    public boolean b(int paramInt)
    {
      Log.d("EditModeActions", "--- do the next action: " + paramInt + "," + a.m());
      a locala = c(paramInt);
      if (locala == null)
      {
        Log.e("EditModeActions", "--- invalid action error.");
        return false;
      }
      switch (a.m())
      {
      default: 
        return false;
      case 0: 
        return locala.a();
      case 1: 
        return locala.b();
      case 2: 
        return locala.c();
      }
      if (a.j()) {
        return locala.e();
      }
      return locala.d();
    }
    
    public class a
    {
      private Object[] a;
      
      protected void a(Object[] paramArrayOfObject)
      {
        a = paramArrayOfObject;
      }
      
      protected boolean a()
      {
        return false;
      }
      
      protected boolean b()
      {
        return a();
      }
      
      protected boolean c()
      {
        return b();
      }
      
      protected boolean d()
      {
        return c();
      }
      
      protected boolean e()
      {
        return c();
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(int paramInt1, int paramInt2);
    
    public abstract boolean a();
    
    public abstract boolean a(MotionEvent paramMotionEvent);
  }
  
  public static class c
  {
    public static class a
      extends ShapeDrawable
    {
      private static boolean c = false;
      private Spannable a;
      private int b;
      
      private void a()
      {
        Object localObject = b();
        Spannable localSpannable = a;
        localObject = (ForegroundColorSpan[])localSpannable.getSpans(localSpannable.getSpanStart(localObject), localSpannable.getSpanEnd(localObject), ForegroundColorSpan.class);
        if (c) {
          Log.d("EditStyledTextSpan", "--- renewColor:" + localObject.length);
        }
        if (localObject.length > 0) {
          b(localObject[(localObject.length - 1)].getForegroundColor());
        }
      }
      
      private EditStyledText.c.b b()
      {
        Object localObject = a;
        localObject = (EditStyledText.c.b[])((Spannable)localObject).getSpans(0, ((Spannable)localObject).length(), EditStyledText.c.b.class);
        if (localObject.length > 0)
        {
          int j = localObject.length;
          int i = 0;
          while (i < j)
          {
            EditStyledText.c.b localb = localObject[i];
            if (localb.getDrawable() == this) {
              return localb;
            }
            i += 1;
          }
        }
        Log.e("EditStyledTextSpan", "---renewBounds: Couldn't find");
        return null;
      }
      
      private void b(int paramInt)
      {
        if (c) {
          Log.d("EditStyledTextSpan", "--- renewColor:" + paramInt);
        }
        getPaint().setColor(paramInt);
      }
      
      public void a(int paramInt)
      {
        if (c) {
          Log.d("EditStyledTextSpan", "--- renewBounds:" + paramInt);
        }
        int i = paramInt;
        if (paramInt > 20) {
          i = paramInt - 20;
        }
        b = i;
        setBounds(0, 0, i, 20);
      }
      
      public void draw(Canvas paramCanvas)
      {
        a();
        paramCanvas.drawRect(new Rect(0, 9, b, 11), getPaint());
      }
    }
    
    public static class b
      extends DynamicDrawableSpan
    {
      EditStyledText.c.a a;
      
      public void a(int paramInt)
      {
        a.a(paramInt);
      }
      
      public Drawable getDrawable()
      {
        return a;
      }
    }
    
    public static class c
      extends CharacterStyle
    {
      private int a;
      private int b;
      
      public c(int paramInt1, int paramInt2)
      {
        a = paramInt1;
        b(paramInt1);
        b = a(paramInt1, paramInt2);
      }
      
      private int a(int paramInt1, int paramInt2)
      {
        int m = Color.alpha(paramInt2);
        int j = Color.red(paramInt2);
        int k = Color.green(paramInt2);
        int n = Color.blue(paramInt2);
        int i = m;
        if (m == 0) {
          i = 128;
        }
        switch (paramInt1)
        {
        default: 
          Log.e("EditStyledText", "--- getMarqueeColor: got illigal marquee ID.");
          return 16777215;
        case 0: 
          if (j > 128)
          {
            paramInt2 = j / 2;
            paramInt1 = k;
          }
        case 1: 
          for (;;)
          {
            return Color.argb(i, paramInt2, paramInt1, n);
            paramInt2 = (255 - j) / 2;
            paramInt1 = k;
            continue;
            if (k > 128)
            {
              paramInt1 = k / 2;
              paramInt2 = j;
            }
            else
            {
              paramInt1 = (255 - k) / 2;
              paramInt2 = j;
            }
          }
        }
        return 16777215;
      }
      
      private boolean b(int paramInt)
      {
        if ((paramInt == 0) || (paramInt == 1)) {
          return true;
        }
        Log.e("EditStyledTextSpan", "--- Invalid type of MarqueeSpan");
        return false;
      }
      
      public void a(int paramInt)
      {
        b = a(a, paramInt);
      }
      
      public void updateDrawState(TextPaint paramTextPaint)
      {
        bgColor = b;
      }
    }
    
    public static class d
      extends ImageSpan
    {
      Uri a;
      public int b;
      public int c;
      private Drawable d;
      private Context e;
      private final int f;
      
      private void a(Drawable paramDrawable)
      {
        Log.d("EditStyledTextSpan", "--- rescaleBigImage:");
        if (f < 0) {
          return;
        }
        int m = paramDrawable.getIntrinsicWidth();
        int k = paramDrawable.getIntrinsicHeight();
        Log.d("EditStyledTextSpan", "--- rescaleBigImage:" + m + "," + k + "," + f);
        int j = k;
        int i = m;
        if (m > f)
        {
          i = f;
          j = k * f / i;
        }
        paramDrawable.setBounds(0, 0, i, j);
      }
      
      public Drawable getDrawable()
      {
        if (d != null) {
          return d;
        }
        if (a != null) {
          System.gc();
        }
        for (;;)
        {
          try
          {
            InputStream localInputStream = e.getContentResolver().openInputStream(a);
            Object localObject = new BitmapFactory.Options();
            inJustDecodeBounds = true;
            BitmapFactory.decodeStream(localInputStream, null, (BitmapFactory.Options)localObject);
            localInputStream.close();
            localInputStream = e.getContentResolver().openInputStream(a);
            int j = outWidth;
            int i = outHeight;
            b = j;
            c = i;
            if (outWidth > f)
            {
              j = f;
              i = i * f / outWidth;
              localObject = BitmapFactory.decodeStream(localInputStream, new Rect(0, 0, j, i), null);
              d = new BitmapDrawable(e.getResources(), (Bitmap)localObject);
              d.setBounds(0, 0, j, i);
              localInputStream.close();
              return d;
            }
            localObject = BitmapFactory.decodeStream(localInputStream);
            continue;
            d = super.getDrawable();
          }
          catch (Exception localException)
          {
            Log.e("EditStyledTextSpan", "Failed to loaded content " + a, localException);
            return null;
          }
          catch (OutOfMemoryError localOutOfMemoryError)
          {
            Log.e("EditStyledTextSpan", "OutOfMemoryError");
            return null;
          }
          a(d);
          b = d.getIntrinsicWidth();
          c = d.getIntrinsicHeight();
        }
      }
    }
  }
  
  class d
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
  
  class e
    implements MenuItem.OnMenuItemClickListener
  {
    private e() {}
    
    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      return onTextContextMenuItem(paramMenuItem.getItemId());
    }
  }
  
  public class f
  {
    private EditStyledText b;
    private EditStyledText.h c;
    
    public String a()
    {
      b.clearComposingText();
      EditStyledText.c(b);
      String str = c.a(b.getText(), true, EditStyledText.b(a), EditStyledText.d(a));
      int i = b.getBackgroundColor();
      str = String.format("<body bgcolor=\"#%02X%02X%02X\">%s</body>", new Object[] { Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)), str });
      Log.d("EditStyledText", "--- getPreviewHtml:" + str + "," + b.getWidth());
      return str;
    }
    
    public String a(boolean paramBoolean)
    {
      b.clearComposingText();
      EditStyledText.c(b);
      String str = c.a(b.getText(), paramBoolean);
      Log.d("EditStyledText", "--- getHtml:" + str);
      return str;
    }
    
    public void a(EditStyledText.h paramh)
    {
      c = paramh;
    }
    
    public void a(String paramString)
    {
      paramString = c.a(paramString, new fi(this), null);
      b.setText(paramString);
    }
  }
  
  static class g
  {
    private AlertDialog.Builder a;
    
    public void a(AlertDialog.Builder paramBuilder)
    {
      a = paramBuilder;
    }
  }
  
  public static abstract interface h
  {
    public abstract Spanned a(String paramString, Html.ImageGetter paramImageGetter, Html.TagHandler paramTagHandler);
    
    public abstract String a(Spanned paramSpanned, boolean paramBoolean);
    
    public abstract String a(Spanned paramSpanned, boolean paramBoolean, int paramInt, float paramFloat);
  }
  
  public static class i
    extends InputConnectionWrapper
  {
    EditStyledText a;
    
    public i(InputConnection paramInputConnection, EditStyledText paramEditStyledText)
    {
      super(true);
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
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */