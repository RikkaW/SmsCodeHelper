package com.meizu.commonwidget;

import aig;
import aih.a;
import aih.b;
import aih.c;
import aih.d;
import aih.e;
import aih.f;
import aih.g;
import aii;
import aij;
import aik;
import ail;
import aim;
import ain;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.Secure;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RecipientEdit
  extends FrameLayout
  implements View.OnClickListener, View.OnFocusChangeListener, View.OnLongClickListener
{
  private static c ac;
  private HashMap<String, String> A;
  private HashMap<String, aig> B;
  private boolean C;
  private boolean D;
  private int E;
  private String F;
  private int G = 0;
  private boolean H;
  private m I;
  private View.OnDragListener J;
  private h K;
  private boolean L;
  private boolean M;
  private boolean N = false;
  private int O;
  private int P;
  private boolean Q = false;
  private boolean R = true;
  private boolean S = false;
  private boolean T = false;
  private boolean U = false;
  private boolean V = false;
  private boolean W = false;
  public Context a;
  private ArrayList<String> aa = new ArrayList();
  private b ab;
  private Handler ad;
  private final ViewTreeObserver.OnScrollChangedListener ae = new aij(this);
  private boolean af = true;
  private TextWatcher ag = new ail(this);
  private Runnable ah = new ain(this);
  private String ai;
  private int aj;
  private boolean ak = false;
  public i b;
  public f c;
  public e d;
  public g e;
  public int f;
  HandlerThread g;
  Handler h;
  boolean i;
  Handler j = new aim(this);
  private RecipientEdit.RecipientAutoCompleteTextView.a k;
  private j l;
  private LinearLayout m;
  private ScrollView n;
  private AbsoluteLayout o;
  private TextView p;
  private RecipientAutoCompleteTextView q;
  private View r;
  private TextView s;
  private TextView t;
  private View u;
  private ContentResolver v;
  private int w;
  private int x;
  private String y;
  private ArrayList<String> z;
  
  public RecipientEdit(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public RecipientEdit(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public RecipientEdit(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a = paramContext;
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, aih.g.RecipientEdit, paramInt, 0);
    x = paramContext.getInt(aih.g.RecipientEdit_mzInputType, 3);
    y = paramContext.getString(aih.g.RecipientEdit_mzHint);
    w = paramContext.getDimensionPixelSize(aih.g.RecipientEdit_mzMaxHeight, 0);
    i = paramContext.getBoolean(aih.g.RecipientEdit_isEasyMode, false);
    paramContext.recycle();
    g = new HandlerThread("QueryThread");
    g.start();
    h = new k(g.getLooper());
    a();
  }
  
  private int a(b paramb, int paramInt1, int paramInt2)
  {
    int i2 = n.getLeft();
    int i1 = n.getTop();
    paramInt1 = paramInt1 - i2 - a;
    paramInt2 = paramInt2 - i1 - b;
    Object localObject1 = c;
    paramb = new Rect(paramInt1, paramInt2, ((View)localObject1).getWidth() + paramInt1, ((View)localObject1).getHeight() + paramInt2);
    int i3 = paramb.centerX();
    int i4 = paramb.centerY();
    i1 = o.indexOfChild((View)localObject1);
    Object localObject2 = new Rect();
    i2 = o.getChildCount();
    paramInt2 = 0;
    int i6;
    if (paramInt2 < i2)
    {
      o.getChildAt(paramInt2).getHitRect((Rect)localObject2);
      int i5 = ((Rect)localObject2).centerX();
      i6 = ((Rect)localObject2).centerY();
      if ((((Rect)localObject2).contains(i3, i4)) || (paramb.contains(i5, i6)))
      {
        paramInt1 = paramInt2;
        if (i5 < i3) {
          paramInt1 = paramInt2 + 1;
        }
      }
    }
    for (;;)
    {
      label180:
      if (paramInt1 == 0)
      {
        paramInt2 = 1;
        label186:
        paramInt1 = paramInt2;
        if (i1 > 0)
        {
          paramInt1 = paramInt2;
          if (i1 < paramInt2) {
            paramInt1 = paramInt2 - 1;
          }
        }
        paramInt2 = paramInt1;
        if (i1 < 0)
        {
          paramInt2 = paramInt1;
          if (paramInt1 < 0) {
            paramInt2 = i2 - 1;
          }
        }
        if ((paramInt2 <= 0) || (paramInt2 == i1)) {
          break label833;
        }
        paramb = (ViewGroup)((View)localObject1).getParent();
        if (paramb != null) {
          paramb.removeView((View)localObject1);
        }
        if ((paramInt2 <= 0) || (paramInt2 != 1)) {
          break label564;
        }
        s.measure(0, 0);
        paramInt1 = m.getMeasuredWidth() - s.getMeasuredWidth() - r.getMeasuredWidth() - m.getPaddingStart() - m.getPaddingEnd() - c((View)localObject1) / 2;
        label319:
        paramb = ((ItemView)localObject1).a();
        i3 = (int)paramb.getPaint().measureText(String.valueOf(paramb.getText()));
        i3 = c((View)localObject1) / 2 + i3;
        if (i3 <= paramInt1) {
          break label608;
        }
        localObject2 = (LinearLayout.LayoutParams)paramb.getLayoutParams();
        width = paramInt1;
        paramb.setLayoutParams((ViewGroup.LayoutParams)localObject2);
        label385:
        o.addView((View)localObject1, paramInt2);
        if (i2 > 3)
        {
          if (paramInt2 != 1) {
            break label677;
          }
          localObject2 = o.getChildAt(2);
          paramInt1 = m.getMeasuredWidth() - r.getMeasuredWidth() - m.getPaddingStart() - m.getPaddingEnd();
          localObject1 = ((ItemView)localObject2).a();
          i1 = (int)paramb.getPaint().measureText(String.valueOf(((TextView)localObject1).getText()));
          if (i1 <= paramInt1) {
            break label643;
          }
          paramb = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
          width = paramInt1;
          ((TextView)localObject1).setLayoutParams(paramb);
        }
      }
      for (;;)
      {
        return paramInt2;
        paramInt1 = paramInt2;
        if (paramb.contains(left, i6)) {
          break label180;
        }
        if (paramb.contains(right, i6))
        {
          paramInt1 = paramInt2 + 1;
          break label180;
        }
        paramInt2 += 1;
        break;
        paramInt2 = paramInt1;
        if (paramInt1 != i2) {
          break label186;
        }
        paramInt2 = i2 - 1;
        break label186;
        label564:
        paramInt1 = m.getMeasuredWidth() - r.getMeasuredWidth() - m.getPaddingStart() - m.getPaddingEnd() - c((View)localObject1) / 2;
        break label319;
        label608:
        if (i3 <= ((View)localObject1).getWidth()) {
          break label385;
        }
        localObject2 = (LinearLayout.LayoutParams)paramb.getLayoutParams();
        width = i3;
        paramb.setLayoutParams((ViewGroup.LayoutParams)localObject2);
        break label385;
        label643:
        if (i1 > ((View)localObject2).getWidth())
        {
          paramb = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
          width = i1;
          ((TextView)localObject1).setLayoutParams(paramb);
          continue;
          label677:
          if (i1 == 1)
          {
            localObject2 = o.getChildAt(1);
            s.measure(0, 0);
            paramInt1 = m.getMeasuredWidth() - s.getMeasuredWidth() - r.getMeasuredWidth() - m.getPaddingStart() - m.getPaddingEnd();
            localObject1 = ((ItemView)localObject2).a();
            i1 = (int)paramb.getPaint().measureText(String.valueOf(((TextView)localObject1).getText()));
            if (i1 > paramInt1)
            {
              paramb = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
              width = paramInt1;
              ((TextView)localObject1).setLayoutParams(paramb);
            }
            else if (i1 > ((View)localObject2).getWidth())
            {
              paramb = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
              width = i1;
              ((TextView)localObject1).setLayoutParams(paramb);
            }
          }
        }
      }
      label833:
      return i1;
      paramInt1 = -1;
    }
  }
  
  private int a(CharSequence paramCharSequence, String paramString)
  {
    ItemView localItemView;
    TextView localTextView;
    if (i)
    {
      localItemView = (ItemView)inflate(a, aih.e.mw_recipient_itemview_easymode, null);
      localTextView = localItemView.a();
      if ((!b(paramString)) && (R))
      {
        if (G != 2) {
          break label224;
        }
        localTextView.setTextColor(getResources().getColor(aih.a.mw_recipient_text_invalidate_calendar));
      }
    }
    for (;;)
    {
      localTextView.setText(paramCharSequence + "，");
      localItemView.setClickable(true);
      localItemView.setOnClickListener(this);
      localItemView.setLongClickable(true);
      localItemView.setOnLongClickListener(this);
      localItemView.setFocusable(false);
      localItemView.setFocusableInTouchMode(false);
      int i1 = o.getChildCount() - 1;
      o.addView(localItemView, i1);
      return i1;
      if (G == 1)
      {
        localItemView = (ItemView)inflate(a, aih.e.mw_recipient_itemview_mns, null);
        break;
      }
      if (G == 2)
      {
        localItemView = (ItemView)inflate(a, aih.e.mw_recipient_itemview_calendar, null);
        break;
      }
      localItemView = (ItemView)inflate(a, aih.e.mw_recipient_itemview, null);
      break;
      label224:
      localTextView.setTextColor(getResources().getColor(aih.a.mw_recipient_text_invalidate));
    }
  }
  
  private String a(String paramString)
  {
    Object localObject;
    if (TextUtils.isEmpty(paramString)) {
      localObject = "";
    }
    do
    {
      return (String)localObject;
      paramString = paramString.trim();
      if ((x & 0x4) != 4) {
        break;
      }
      localObject = paramString;
    } while (paramString.startsWith("@"));
    if (((x & 0x1) == 1) && (((aii)q.getAdapter()).c(paramString)))
    {
      int i2 = paramString.length();
      localObject = new StringBuilder(i2);
      int i1 = 0;
      if (i1 < i2)
      {
        char c1 = paramString.charAt(i1);
        if (c1 == ' ') {}
        for (;;)
        {
          i1 += 1;
          break;
          if ((c1 != '.') && (c1 != '-') && (c1 != '(') && (c1 != ')')) {
            ((StringBuilder)localObject).append(c1);
          }
        }
      }
      paramString = ((StringBuilder)localObject).toString();
    }
    for (;;)
    {
      return paramString;
      if (((aii)q.getAdapter()).d(paramString))
      {
        localObject = Rfc822Tokenizer.tokenize(paramString);
        if (localObject.length > 0) {
          paramString = localObject[0].getAddress();
        }
      }
    }
  }
  
  private void a()
  {
    v = a.getContentResolver();
    C = true;
    D = true;
    z = new ArrayList();
    A = new HashMap();
    B = new HashMap();
    setOnClickListener(this);
    if (i)
    {
      inflate(a, aih.e.mw_recipient_edit_layout_easymode, this);
      m = ((LinearLayout)findViewById(aih.d.mz_recipient_root));
      n = ((ScrollView)findViewById(aih.d.mz_recipient_scrollview));
      n.setOverScrollMode(1);
      o = ((AbsoluteLayout)findViewById(aih.d.mz_recipient_layout));
      p = ((TextView)findViewById(aih.d.mz_recipient_hint));
      q = ((RecipientAutoCompleteTextView)findViewById(aih.d.mz_recipient_edit));
      r = findViewById(aih.d.mz_recipient_add_btn);
      s = ((TextView)findViewById(aih.d.mz_recipient_hint2));
      t = ((TextView)findViewById(aih.d.mz_recipient_name));
      o.setClickable(true);
      o.setOnClickListener(this);
      if (TextUtils.isEmpty(y)) {
        y = getResources().getString(aih.f.mw_recipient_hint_str);
      }
      p.setText(y);
      s.setText(y);
      q.setDropDownAnchor(getId());
      q.setDropDownBackgroundResource(aih.c.mw_list_history_background);
      q.addTextChangedListener(ag);
      q.setOnClickListener(this);
      q.setOnFocusChangeListener(this);
      q.setOnKeyPreImeListener(new aik(this));
      if (x != 2) {
        break label394;
      }
      setBackgroundResource(aih.c.mw_recipient_divider_email_2px);
    }
    for (;;)
    {
      q.setInputType(33);
      F = Settings.Secure.getString(v, "default_input_method");
      super.setFocusable(false);
      super.setFocusableInTouchMode(false);
      setFocusable(true);
      setFocusableInTouchMode(true);
      return;
      inflate(a, aih.e.mw_recipient_edit_layout, this);
      break;
      label394:
      setBackgroundResource(aih.c.mw_recipient_divider_sms_2px);
    }
  }
  
  private void a(View paramView)
  {
    paramView = ((ItemView)paramView).a();
    Object localObject = String.valueOf(paramView.getText());
    String str = ((String)localObject).substring(0, ((String)localObject).length() - 1);
    float f1 = paramView.getPaint().measureText((String)localObject);
    float f2 = paramView.getPaint().measureText(str);
    int i1 = (int)((f1 - f2) / 2.0F);
    int i2 = (int)(f1 - f2 - i1);
    int i3 = i2 / 2;
    localObject = (LinearLayout.LayoutParams)paramView.getLayoutParams();
    width = (paramView.getMeasuredWidth() - i1);
    paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    paramView.setPadding(i3, 0, i2 - i3, 0);
    paramView.setText(str);
  }
  
  private boolean a(int paramInt, boolean paramBoolean)
  {
    int i1 = z.size();
    if ((paramInt > -1) && (paramInt < i1))
    {
      String str = (String)z.remove(paramInt);
      A.remove(str);
      if (((aii)q.getAdapter()).d(str)) {
        f &= 0xFFFFFFFD;
      }
      for (;;)
      {
        if (paramBoolean)
        {
          o.removeViewAt(paramInt + 1);
          b(hasFocus());
        }
        if (d != null) {
          d.a();
        }
        if (e != null) {
          e.a(this);
        }
        if (K != null) {
          K.a(str, 2);
        }
        return true;
        if (((aii)q.getAdapter()).b(str)) {
          f &= 0xFFFFFFFB;
        }
      }
    }
    return false;
  }
  
  private void b(View paramView)
  {
    TextView localTextView = ((ItemView)paramView).a();
    String str = String.valueOf(localTextView.getText());
    str = str + "，";
    int i1 = c(paramView) / 2;
    paramView = (LinearLayout.LayoutParams)localTextView.getLayoutParams();
    width = (i1 + localTextView.getWidth());
    localTextView.setLayoutParams(paramView);
    localTextView.setPadding(0, 0, 0, 0);
    localTextView.setText(str);
  }
  
  private void b(String paramString1, String paramString2)
  {
    S = true;
    Message localMessage = Message.obtain(h, 1);
    obj = new a(paramString1, paramString2);
    h.sendMessage(localMessage);
  }
  
  private boolean b()
  {
    return TextUtils.equals(Settings.Secure.getString(v, "default_input_method"), "com.baidu.input_mz/com.meizu.input.MzInputService");
  }
  
  private boolean b(String paramString)
  {
    boolean bool2 = false;
    if ((x & 0x1) == 1) {
      bool2 = ((aii)q.getAdapter()).c(paramString);
    }
    boolean bool1 = bool2;
    if (!bool2)
    {
      bool1 = bool2;
      if ((x & 0x2) == 2) {
        bool1 = ((aii)q.getAdapter()).d(paramString);
      }
    }
    bool2 = bool1;
    if (!bool1)
    {
      bool2 = bool1;
      if ((x & 0x4) == 4) {
        bool2 = ((aii)q.getAdapter()).b(paramString);
      }
    }
    return bool2;
  }
  
  private int c(View paramView)
  {
    return (int)((ItemView)paramView).a().getPaint().measureText("，");
  }
  
  private CharSequence c(CharSequence paramCharSequence)
  {
    int i6;
    int i2;
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      i6 = paramCharSequence.length();
      i2 = 0;
      while ((i2 < i6) && (paramCharSequence.charAt(i2) <= ' ')) {
        i2 += 1;
      }
      if (i2 < i6) {}
    }
    else
    {
      return paramCharSequence;
    }
    String str1 = paramCharSequence.toString();
    int i4 = str1.indexOf(" ", i2);
    int i1 = 0;
    int i3 = i2;
    label74:
    int i5;
    if ((i4 > 0) && (i4 <= i6))
    {
      i5 = str1.charAt(i4 - 1);
      if ((i5 == 44) || (i5 == 59))
      {
        a(str1.substring(i3, i4 - 1));
        i1 = 1;
      }
    }
    label261:
    for (;;)
    {
      i5 = i4 + 1;
      i4 = str1.indexOf(" ", i5);
      for (;;)
      {
        if (i5 == i4)
        {
          i5 = i4 + 1;
          i4 = str1.indexOf(" ", i5);
          continue;
          String str2 = str1.substring(i3, i4);
          if (!((aii)q.getAdapter()).d(str2)) {
            break label261;
          }
          a(str2);
          i1 = 1;
          break;
        }
      }
      if (i1 != 0) {
        i1 = 0;
      }
      for (;;)
      {
        i3 = i5;
        break label74;
        if (i3 >= i6) {
          return "";
        }
        if (i3 == i2) {
          break;
        }
        return str1.substring(i3);
        i5 = i3;
      }
    }
  }
  
  private void c()
  {
    if ((q.getText() instanceof Spannable))
    {
      localObject = q.getText();
      int i1 = BaseInputConnection.getComposingSpanStart((Spannable)localObject);
      int i2 = BaseInputConnection.getComposingSpanEnd((Spannable)localObject);
      if ((i1 >= 0) && (i2 >= 0))
      {
        localObject = ((Spannable)localObject).subSequence(0, i1).toString() + ((Spannable)localObject).subSequence(i2, ((Spannable)localObject).length()).toString();
        q.setText((CharSequence)localObject);
      }
    }
    Object localObject = q.getText();
    ParcelableImageSpan[] arrayOfParcelableImageSpan = (ParcelableImageSpan[])((Editable)localObject).getSpans(0, ((Editable)localObject).length(), ParcelableImageSpan.class);
    if (arrayOfParcelableImageSpan.length > 0) {
      ((Editable)localObject).delete(((Editable)localObject).getSpanStart(arrayOfParcelableImageSpan[0]), ((Editable)localObject).getSpanEnd(arrayOfParcelableImageSpan[0]));
    }
  }
  
  private void d(View paramView)
  {
    TextView localTextView = ((ItemView)paramView).a();
    String str = String.valueOf(localTextView.getText());
    str = str.substring(0, str.length() - 1);
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)localTextView.getLayoutParams();
    width = (localTextView.getWidth() - c(paramView) / 2);
    localTextView.setLayoutParams(localLayoutParams);
    localTextView.setText(str);
  }
  
  private void e()
  {
    if (getMeasuredWidth() == 0) {}
    ViewGroup.LayoutParams localLayoutParams;
    do
    {
      return;
      if (o.getMeasuredWidth() == 0) {
        measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
      }
      if (o.getMeasuredWidth() < t.getMeasuredWidth())
      {
        int i1 = View.MeasureSpec.makeMeasureSpec(t.getMeasuredWidth(), 1073741824);
        int i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        o.measure(i1, i2);
      }
      if (getLayoutDirection() == 1) {
        i();
      }
      for (;;)
      {
        localLayoutParams = getLayoutParams();
        if (!M) {
          break;
        }
        height = -2;
        return;
        f();
      }
    } while (w <= 0);
    measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
    if (getMeasuredHeight() >= w)
    {
      height = w;
      return;
    }
    height = -2;
  }
  
  private void f()
  {
    int i7 = o.getChildCount();
    int i4 = 0;
    int i1 = 0;
    int i2 = 0;
    if (i4 < i7)
    {
      localObject = o.getChildAt(i4);
      if (((View)localObject).getVisibility() == 8) {}
      for (;;)
      {
        i4 += 1;
        break;
        if ((i4 == 1) && (i7 > 2) && ((localObject instanceof ItemView))) {
          ((ItemView)localObject).a().setMaxWidth(o.getMeasuredWidth() - i2);
        }
        ((View)localObject).measure(0, 0);
        if (i4 == i7 - 1) {}
        int i3;
        int i5;
        for (int i6 = i2 + 60;; i6 = ((View)localObject).getMeasuredWidth() + i2)
        {
          i3 = i1;
          i5 = i2;
          if (i6 > o.getMeasuredWidth())
          {
            i3 = i1;
            i5 = i2;
            if (i4 > 1)
            {
              i3 = ((View)localObject).getMeasuredHeight() + i1;
              i5 = 0;
            }
          }
          AbsoluteLayout.LayoutParams localLayoutParams = (AbsoluteLayout.LayoutParams)((View)localObject).getLayoutParams();
          x = i5;
          y = i3;
          if ((!N) || (O != i4)) {
            break label228;
          }
          i2 = i5 + (((View)localObject).getMeasuredWidth() + c((View)localObject) / 2);
          i1 = i3;
          break;
        }
        label228:
        i2 = i5 + ((View)localObject).getMeasuredWidth();
        i1 = i3;
      }
    }
    Object localObject = (AbsoluteLayout.LayoutParams)q.getLayoutParams();
    E = (o.getMeasuredWidth() - x);
    if (E > 0) {}
    for (i1 = E;; i1 = 60)
    {
      E = i1;
      q.setWidth(E);
      return;
    }
  }
  
  private void i()
  {
    int i2 = o.getMeasuredWidth();
    int i5 = o.getChildCount();
    int i3 = 0;
    int i1 = 0;
    while (i3 < i5)
    {
      localObject = o.getChildAt(i3);
      int i4;
      if (((View)localObject).getVisibility() == 8)
      {
        i4 = i2;
        i2 = i1;
        i3 += 1;
        i1 = i2;
        i2 = i4;
      }
      else
      {
        if ((i3 == 1) && (i5 > 2) && ((localObject instanceof ItemView))) {
          ((ItemView)localObject).a().setMaxWidth(i2);
        }
        ((View)localObject).measure(0, 0);
        if (i3 == i5 - 1)
        {
          i4 = 60;
          label114:
          if (i2 - i4 >= 0) {
            break label201;
          }
          if (o.getMeasuredWidth() >= i4) {
            break label187;
          }
          i2 = 0;
          label135:
          i4 = i1 + ((View)localObject).getMeasuredHeight();
          i1 = i2;
          i2 = i4;
        }
        for (;;)
        {
          localObject = (AbsoluteLayout.LayoutParams)((View)localObject).getLayoutParams();
          x = i1;
          y = i2;
          i4 = i1;
          break;
          i4 = ((View)localObject).getMeasuredWidth();
          break label114;
          label187:
          i2 = o.getMeasuredWidth() - i4;
          break label135;
          label201:
          i4 = i2 - i4;
          i2 = i1;
          i1 = i4;
        }
      }
    }
    Object localObject = (AbsoluteLayout.LayoutParams)q.getLayoutParams();
    E = (x + 60);
    x = 0;
    q.setWidth(E);
  }
  
  public int a(ArrayList<String> paramArrayList)
  {
    if (paramArrayList == null)
    {
      i2 = 0;
      return i2;
    }
    int i3 = paramArrayList.size();
    int i2 = 0;
    boolean bool = false;
    int i1 = 0;
    label21:
    Object localObject;
    if (i2 < i3)
    {
      localObject = (String)paramArrayList.get(i2);
      if ((localObject == null) || (TextUtils.isEmpty((CharSequence)localObject))) {
        break label287;
      }
      localObject = ((String)localObject).split(";");
      if (2 == localObject.length)
      {
        bool = a(localObject[0], localObject[1]);
        label81:
        if (!bool) {
          break label281;
        }
        i1 += 1;
      }
    }
    label281:
    label287:
    for (;;)
    {
      i2 += 1;
      break label21;
      if (localObject.length == 1)
      {
        bool = a(localObject[0], null);
        break label81;
      }
      if (3 == localObject.length)
      {
        bool = a(localObject[0], localObject[1] + ";" + localObject[2]);
        break label81;
        i2 = i1;
        if (i1 <= 0) {
          break;
        }
        if (d != null) {
          d.a();
        }
        if (e != null) {
          e.a(this);
        }
        if ((z.size() > 1) && ((f & 0x6) > 0) && (b != null)) {
          b.a(f & x);
        }
        i2 = i1;
        if (D) {
          break;
        }
        i2 = i1;
        if (S) {
          break;
        }
        b(hasFocus());
        return i1;
        continue;
      }
      break label81;
    }
  }
  
  public boolean a(CharSequence paramCharSequence)
  {
    if (TextUtils.isEmpty(paramCharSequence)) {}
    ArrayList localArrayList;
    do
    {
      return false;
      localArrayList = new ArrayList();
      localArrayList.add(paramCharSequence.toString());
    } while (a(localArrayList) <= 0);
    return true;
  }
  
  public boolean a(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    if (TextUtils.isEmpty(paramCharSequence1)) {}
    for (;;)
    {
      return false;
      ArrayList localArrayList = new ArrayList();
      if (TextUtils.isEmpty(paramCharSequence2)) {
        localArrayList.add(paramCharSequence1.toString());
      }
      while (a(localArrayList) > 0)
      {
        return true;
        localArrayList.add(paramCharSequence1.toString() + ";" + paramCharSequence2.toString());
      }
    }
  }
  
  public boolean a(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2, -1);
  }
  
  public boolean a(String paramString1, String paramString2, int paramInt)
  {
    String str2 = a(paramString1);
    if (TextUtils.isEmpty(str2)) {}
    while (z.contains(str2)) {
      return false;
    }
    int i1;
    int i2;
    label55:
    label88:
    String str1;
    Object localObject;
    if ((f & 0x2) == 2)
    {
      i1 = 1;
      if ((f & 0x4) != 4) {
        break label259;
      }
      i2 = 1;
      if ((i1 != 0) || (!((aii)q.getAdapter()).d(str2))) {
        break label265;
      }
      f |= 0x2;
      str1 = "";
      if (TextUtils.isEmpty(paramString2)) {
        break label371;
      }
      localObject = paramString2.split(";");
      if (localObject.length <= 1) {
        break label371;
      }
      paramString1 = localObject[0];
      str1 = localObject[1];
    }
    for (;;)
    {
      localObject = paramString2;
      if (TextUtils.isEmpty(paramString1))
      {
        b(str2, paramString2);
        if (!TextUtils.isEmpty(paramString1)) {
          localObject = paramString1;
        }
      }
      else
      {
        label154:
        paramString2 = o.getChildAt(paramInt);
        if (paramString2 == null) {
          break label308;
        }
        paramString2.setOnClickListener(this);
        paramString2.setOnLongClickListener(this);
        label177:
        z.add(paramInt - 1, str2);
        if (TextUtils.isEmpty(str1)) {
          break label357;
        }
        A.put(str2, paramString1 + ";" + str1);
      }
      for (;;)
      {
        if (K != null) {
          K.a(str2, 1);
        }
        return true;
        i1 = 0;
        break;
        label259:
        i2 = 0;
        break label55;
        label265:
        if ((i2 != 0) || (!((aii)q.getAdapter()).b(str2))) {
          break label88;
        }
        f |= 0x4;
        break label88;
        localObject = str2;
        break label154;
        label308:
        if (!TextUtils.isEmpty(str1))
        {
          paramInt = a(paramString1 + str1, str2);
          break label177;
        }
        paramInt = a((CharSequence)localObject, str2);
        break label177;
        label357:
        A.put(str2, paramString1);
      }
      label371:
      paramString1 = paramString2;
    }
  }
  
  public ArrayList<String> b(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    int i1 = f;
    Object localObject = (aii)q.getAdapter();
    localObject = (aii)q.getAdapter();
    int i2;
    if ((i1 & 0x2 & paramInt) == 2)
    {
      i1 = 1;
      i2 = f;
      localObject = (aii)q.getAdapter();
      localObject = (aii)q.getAdapter();
      if ((i2 & 0x4 & paramInt) != 4) {
        break label308;
      }
    }
    label308:
    for (paramInt = 1;; paramInt = 0)
    {
      i2 = z.size();
      localObject = a(q.getText().toString());
      if ((i2 > 0) && (((i1 != 0) && (((aii)q.getAdapter()).d((String)localObject))) || ((paramInt != 0) && (((aii)q.getAdapter()).b((String)localObject))))) {
        q.setText("");
      }
      if (((i1 == 0) && (paramInt == 0)) || (i2 <= 1)) {
        break label496;
      }
      localObject = (ArrayList)z.clone();
      i2 -= 1;
      while (i2 > 0)
      {
        String str = (String)((ArrayList)localObject).get(i2);
        if (((i1 != 0) && (((aii)q.getAdapter()).d(str))) || ((paramInt != 0) && (((aii)q.getAdapter()).b(str))))
        {
          z.remove(i2);
          A.remove(str);
          o.removeViewAt(i2 + 1);
          localArrayList.add(str);
        }
        i2 -= 1;
      }
      i1 = 0;
      break;
    }
    if (z.size() > 1)
    {
      localObject = (String)z.get(0);
      if (((i1 != 0) && (((aii)q.getAdapter()).d((String)localObject))) || ((paramInt != 0) && (((aii)q.getAdapter()).b((String)localObject))))
      {
        z.remove(0);
        A.remove(localObject);
        o.removeViewAt(1);
        localArrayList.add(localObject);
      }
    }
    if ((i1 != 0) && (!((aii)q.getAdapter()).d((String)z.get(0))))
    {
      paramInt = f;
      localObject = (aii)q.getAdapter();
    }
    for (f = (paramInt & 0xFFFFFFFD);; f = (paramInt & 0xFFFFFFFB))
    {
      label496:
      do
      {
        b(hasFocus());
        if (d != null) {
          d.a();
        }
        return localArrayList;
      } while ((paramInt == 0) || (((aii)q.getAdapter()).b((String)z.get(0))));
      paramInt = f;
      localObject = (aii)q.getAdapter();
    }
  }
  
  public void b(boolean paramBoolean)
  {
    if ((paramBoolean) || (M))
    {
      r.setVisibility(0);
      n.setVisibility(0);
      s.setVisibility(8);
      t.setVisibility(8);
      e();
      return;
    }
    Object localObject1 = getLayoutParams();
    if (height != -2)
    {
      height = -2;
      setLayoutParams((ViewGroup.LayoutParams)localObject1);
    }
    StringBuffer localStringBuffer1 = new StringBuffer();
    StringBuffer localStringBuffer2 = new StringBuffer();
    int i4;
    ArrayList localArrayList3;
    ArrayList localArrayList4;
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    Object localObject2;
    int i3;
    int i2;
    String str1;
    String str2;
    int i5;
    label204:
    Object localObject4;
    Object localObject3;
    if (Q)
    {
      i4 = m.getMeasuredWidth() - r.getMeasuredWidth() - m.getPaddingStart() - m.getPaddingEnd();
      localArrayList3 = new ArrayList();
      localArrayList4 = new ArrayList();
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      Iterator localIterator = z.iterator();
      localObject2 = null;
      localObject1 = null;
      paramBoolean = true;
      i3 = 0;
      i2 = 1;
      str1 = null;
      str2 = null;
      i5 = 0;
      if (!localIterator.hasNext()) {
        break label923;
      }
      localObject4 = (String)localIterator.next();
      i3 += 1;
      localObject3 = (String)A.get(localObject4);
      if (!TextUtils.isEmpty((CharSequence)localObject3)) {
        break label594;
      }
      localObject3 = localObject4;
    }
    label258:
    label476:
    label594:
    label765:
    label923:
    label938:
    label1617:
    label1835:
    label1846:
    for (;;)
    {
      localStringBuffer1.append((String)localObject3).append("，");
      localStringBuffer2.append((String)localObject3).append("，");
      localObject2 = localStringBuffer2.substring(0, localStringBuffer2.length() - 1);
      String str3 = getResources().getString(aih.f.mw_recipient_others_displayname, new Object[] { Integer.valueOf(z.size() - i3) });
      if (i3 == 1)
      {
        str2 = String.valueOf(localStringBuffer1);
        paramBoolean = b((String)localObject4);
      }
      int i1;
      int i6;
      int i7;
      if ((i3 == 1) && (t.getPaint().measureText((String)localObject2) >= i4))
      {
        localObject5 = String.valueOf(localStringBuffer2);
        i1 = i2;
        localObject1 = localObject5;
        if (localObject5 != null)
        {
          i1 = i2;
          localObject1 = localObject5;
          if (!b((String)localObject4))
          {
            i1 = i2;
            localObject1 = localObject5;
            if (R)
            {
              i1 = ((String)localObject5).length();
              i6 = ((String)localObject3).length();
              i7 = ((String)localObject5).length();
              localArrayList1.add(Integer.valueOf(i1 - i6 - 1));
              localArrayList2.add(Integer.valueOf(i7));
              localObject1 = localObject5;
              i1 = i2;
            }
          }
        }
        if ((i3 != 1) || (t.getPaint().measureText(String.valueOf(localStringBuffer1) + str3) <= i4)) {
          break label765;
        }
        i2 = z.size() - i3;
      }
      for (;;)
      {
        i5 = i2;
        i2 = i1;
        break label204;
        s.measure(0, 0);
        i4 = m.getMeasuredWidth() - s.getMeasuredWidth() - r.getMeasuredWidth() - m.getPaddingStart() - m.getPaddingEnd();
        break;
        localObject2 = ((String)localObject3).split(";");
        if (localObject2.length <= 1) {
          break label1846;
        }
        localObject3 = localObject2[0] + localObject2[1];
        break label258;
        if (t.getPaint().measureText((String)localObject2) < i4)
        {
          localObject5 = String.valueOf(localStringBuffer2);
          i1 = i2;
          localObject1 = localObject5;
          if (localObject5 == null) {
            break label476;
          }
          i1 = i2;
          localObject1 = localObject5;
          if (b((String)localObject4)) {
            break label476;
          }
          i1 = i2;
          localObject1 = localObject5;
          if (!R) {
            break label476;
          }
          i1 = ((String)localObject5).length();
          i6 = ((String)localObject3).length();
          i7 = ((String)localObject5).length();
          localArrayList1.add(Integer.valueOf(i1 - i6 - 1));
          localArrayList2.add(Integer.valueOf(i7));
          i1 = i2;
          localObject1 = localObject5;
          break label476;
        }
        i1 = 0;
        break label476;
        if (t.getPaint().measureText(String.valueOf(localStringBuffer1) + str3) > i4)
        {
          bool = paramBoolean;
          i2 = i1;
          localObject5 = localObject2;
          localObject3 = str2;
          localObject4 = localObject1;
          if (i1 == 0) {
            break label938;
          }
          i2 = i1;
          break label204;
        }
        str1 = String.valueOf(localStringBuffer1);
        i2 = z.size();
        if ((str1 != null) && (!b((String)localObject4)) && (R))
        {
          i5 = str1.length();
          i6 = ((String)localObject3).length();
          i7 = str1.length();
          localArrayList3.add(Integer.valueOf(i5 - i6 - 1));
          localArrayList4.add(Integer.valueOf(i7));
        }
        i2 -= i3;
      }
      localObject4 = localObject1;
      localObject3 = str2;
      Object localObject5 = localObject2;
      boolean bool = paramBoolean;
      if ((localObject4 == null) && (localObject3 == null)) {
        if (ak)
        {
          t.setText(ai);
          t.setTextColor(aj);
          t.setVisibility(0);
          if (!Q) {
            break label1835;
          }
          s.setVisibility(8);
        }
      }
      for (;;)
      {
        r.setVisibility(0);
        n.setVisibility(8);
        return;
        t.setText("");
        break;
        if (i2 != 0)
        {
          if (ak) {
            t.setTextColor(getResources().getColor(aih.a.mw_recipient_text_red));
          }
          i1 = ((String)localObject5).length();
          localObject1 = new SpannableString((CharSequence)localObject5);
          if ((localArrayList1.size() > 0) && (localArrayList1.size() == localArrayList2.size()) && (R))
          {
            i2 = 0;
            if (i2 < localArrayList1.size())
            {
              if (G == 2)
              {
                localObject2 = new ForegroundColorSpan(getResources().getColor(aih.a.mw_recipient_text_invalidate_calendar));
                i4 = ((Integer)localArrayList1.get(i2)).intValue();
                if (((Integer)localArrayList2.get(i2)).intValue() > i1) {}
                for (i3 = i1;; i3 = ((Integer)localArrayList2.get(i2)).intValue())
                {
                  ((SpannableString)localObject1).setSpan(localObject2, i4, i3, 33);
                  i2 += 1;
                  break;
                }
              }
              localObject2 = new ForegroundColorSpan(getResources().getColor(aih.a.mw_recipient_text_invalidate));
              i4 = ((Integer)localArrayList1.get(i2)).intValue();
              if (((Integer)localArrayList2.get(i2)).intValue() > i1) {}
              for (i3 = i1;; i3 = ((Integer)localArrayList2.get(i2)).intValue())
              {
                ((SpannableString)localObject1).setSpan(localObject2, i4, i3, 33);
                break;
              }
            }
          }
          t.setText((CharSequence)localObject1);
          break;
        }
        if (i5 <= 0) {
          break;
        }
        if (ak) {
          t.setTextColor(getResources().getColor(aih.a.mw_recipient_text_red));
        }
        localObject1 = getResources().getString(aih.f.mw_recipient_others_displayname, new Object[] { Integer.valueOf(i5) });
        if (t.getPaint().measureText((String)localObject3 + (String)localObject1) > i4)
        {
          i1 = ((String)localObject3).length() - 1;
          while (t.getPaint().measureText(((String)localObject3).substring(0, i1) + "..，" + (String)localObject1) > i4) {
            if (i1 > 0) {
              i1 -= 1;
            } else {
              Log.d("refreshlayout:", "mFirstDisplayName:" + (String)localObject3 + "   mDisplayNamesElse:" + (String)localObject1 + "   maxContextWidth:" + i4);
            }
          }
          localObject1 = new SpannableString(((String)localObject3).substring(0, i1) + "..，" + (String)localObject1);
          if ((!bool) && (R))
          {
            if (G != 2) {
              break label1617;
            }
            ((SpannableString)localObject1).setSpan(new ForegroundColorSpan(getResources().getColor(aih.a.mw_recipient_text_invalidate_calendar)), 0, i1 + 3, 33);
          }
          for (;;)
          {
            t.setText((CharSequence)localObject1);
            break;
            ((SpannableString)localObject1).setSpan(new ForegroundColorSpan(getResources().getColor(aih.a.mw_recipient_text_invalidate)), 0, i1 + 3, 33);
          }
        }
        localObject1 = new SpannableString(str1 + (String)localObject1);
        if ((localArrayList3.size() > 0) && (localArrayList3.size() == localArrayList4.size()))
        {
          i1 = 0;
          if (i1 < localArrayList3.size())
          {
            if (G == 2) {
              ((SpannableString)localObject1).setSpan(new ForegroundColorSpan(getResources().getColor(aih.a.mw_recipient_text_invalidate_calendar)), ((Integer)localArrayList3.get(i1)).intValue(), ((Integer)localArrayList4.get(i1)).intValue(), 33);
            }
            for (;;)
            {
              i1 += 1;
              break;
              ((SpannableString)localObject1).setSpan(new ForegroundColorSpan(getResources().getColor(aih.a.mw_recipient_text_invalidate)), ((Integer)localArrayList3.get(i1)).intValue(), ((Integer)localArrayList4.get(i1)).intValue(), 33);
            }
          }
        }
        t.setText((CharSequence)localObject1);
        break;
        s.setVisibility(0);
      }
    }
  }
  
  public boolean b(CharSequence paramCharSequence)
  {
    String str = a(paramCharSequence.toString());
    if (TextUtils.isEmpty(str)) {
      return false;
    }
    B.remove(paramCharSequence);
    return a(z.indexOf(str), true);
  }
  
  public void d()
  {
    q.setText("");
    f = 0;
    int i1 = o.getChildCount() - 2;
    if (i1 > 0)
    {
      z.clear();
      B.clear();
      A.clear();
      o.removeViews(1, i1);
      b(hasFocus());
      if (d != null) {
        d.a();
      }
      if (e != null) {
        e.a(this);
      }
    }
  }
  
  public boolean dispatchDragEvent(DragEvent paramDragEvent)
  {
    if ((J != null) && (isEnabled()) && (J.onDrag(this, paramDragEvent))) {
      return true;
    }
    return onDragEvent(paramDragEvent);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    Object localObject;
    if ((paramKeyEvent.getKeyCode() == 66) && (q.getListSelection() == -1))
    {
      c();
      localObject = q.getText();
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        if (paramKeyEvent.getAction() == 1)
        {
          q.setText("");
          a((CharSequence)localObject);
        }
        return true;
      }
    }
    if ((paramKeyEvent.getKeyCode() == 67) && (paramKeyEvent.getAction() == 0))
    {
      if (u != null)
      {
        int i1 = o.indexOfChild(u);
        a(i1 - 1, true);
        if ((!Q) && (i1 == 1) && (o.getChildCount() > 2))
        {
          paramKeyEvent = o.getChildAt(1);
          if ((paramKeyEvent instanceof ItemView))
          {
            s.measure(0, 0);
            i1 = m.getMeasuredWidth() - s.getMeasuredWidth() - r.getMeasuredWidth() - m.getPaddingStart() - m.getPaddingEnd();
            paramKeyEvent = ((ItemView)paramKeyEvent).a();
            if ((int)paramKeyEvent.getPaint().measureText(String.valueOf(paramKeyEvent.getText())) > i1)
            {
              localObject = (LinearLayout.LayoutParams)paramKeyEvent.getLayoutParams();
              width = i1;
              paramKeyEvent.setLayoutParams((ViewGroup.LayoutParams)localObject);
            }
          }
        }
        u = null;
        q.setCursorVisible(true);
        n.scrollTo(0, o.getMeasuredHeight());
        return true;
      }
      if ((o.getChildCount() > 2) && (TextUtils.isEmpty(q.getText())))
      {
        u = o.getChildAt(o.getChildCount() - 2);
        u.setSelected(true);
        a(u);
        q.setCursorVisible(false);
        return true;
      }
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public boolean g()
  {
    Object localObject = z.iterator();
    while (((Iterator)localObject).hasNext()) {
      if (!b((String)((Iterator)localObject).next())) {
        return true;
      }
    }
    localObject = a(q.getText().toString());
    return (!TextUtils.isEmpty((CharSequence)localObject)) && (!b((String)localObject));
  }
  
  public aii getAdapter()
  {
    return (aii)q.getAdapter();
  }
  
  public List<String> getAllNumbers()
  {
    Object localObject2 = new ArrayList();
    Object localObject1 = localObject2;
    if (z != null)
    {
      localObject1 = localObject2;
      if (z.size() > 0) {
        localObject1 = (ArrayList)z.clone();
      }
    }
    localObject2 = a(q.getText().toString());
    if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!z.contains(localObject2))) {
      ((ArrayList)localObject1).add(localObject2);
    }
    if (((ArrayList)localObject1).size() > 0) {
      return (List<String>)localObject1;
    }
    return null;
  }
  
  public boolean getButtonVisibility()
  {
    return C;
  }
  
  public int getImeOptions()
  {
    return q.getImeOptions() & 0xFF00;
  }
  
  public String getInputText()
  {
    return a(q.getText().toString());
  }
  
  public int getMaxHeight()
  {
    return w;
  }
  
  public RecipientAutoCompleteTextView getRecipientAutoCompleteTextView()
  {
    return q;
  }
  
  public int getRecipientCount()
  {
    int i2 = z.size();
    String str = a(q.getText().toString());
    int i1 = i2;
    if (!TextUtils.isEmpty(str))
    {
      i1 = i2;
      if (!z.contains(str)) {
        i1 = i2 + 1;
      }
    }
    return i1;
  }
  
  public ArrayList<String> getRecipientDataList()
  {
    return z;
  }
  
  public HashMap<String, String> getRecipientMap()
  {
    return A;
  }
  
  public List<String> getRecipients()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = z.iterator();
    Object localObject1;
    if (((Iterator)localObject2).hasNext())
    {
      str = (String)((Iterator)localObject2).next();
      localObject1 = (String)A.get(str);
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        localObject1 = str;
      }
      for (;;)
      {
        localArrayList.add(str + ";" + (String)localObject1);
        break;
        String[] arrayOfString = ((String)localObject1).split(";");
        if (arrayOfString.length > 1) {
          localObject1 = arrayOfString[0];
        }
      }
    }
    String str = a(q.getText().toString());
    if ((!TextUtils.isEmpty(str)) && (!z.contains(str)))
    {
      localObject2 = ((aii)q.getAdapter()).a(str);
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject1 = str;
      }
      localArrayList.add(str + ";" + (String)localObject1);
    }
    if (localArrayList.size() > 0) {
      return localArrayList;
    }
    return null;
  }
  
  public HashMap<String, aig> getRecipientsInfo()
  {
    return B;
  }
  
  public List<String> getValidNumbers()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = z.iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (b(str)) {
        localArrayList.add(str);
      }
    }
    localObject = a(q.getText().toString());
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!z.contains(localObject)) && (b((String)localObject))) {
      localArrayList.add(localObject);
    }
    if (localArrayList.size() > 0) {
      return localArrayList;
    }
    return null;
  }
  
  public boolean getmIsFirstLayout()
  {
    return D;
  }
  
  public boolean h()
  {
    Object localObject = z.iterator();
    while (((Iterator)localObject).hasNext()) {
      if (b((String)((Iterator)localObject).next())) {
        return true;
      }
    }
    localObject = a(q.getText().toString());
    return (!TextUtils.isEmpty((CharSequence)localObject)) && (b((String)localObject));
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (ac == null) {
      ac = new c("mz_LocalUpdate_Thread");
    }
    if (ad == null)
    {
      c.a(ac);
      ad = new Handler(ac.a());
    }
    ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
    if (localViewTreeObserver != null) {
      localViewTreeObserver.addOnScrollChangedListener(ae);
    }
  }
  
  public void onClick(View paramView)
  {
    if ((paramView instanceof AutoCompleteTextView))
    {
      if (u != null)
      {
        u.setSelected(false);
        b(u);
        u = null;
        q.setCursorVisible(true);
      }
      return;
    }
    Object localObject;
    if ((paramView instanceof ItemView))
    {
      c();
      localObject = q.getText();
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        a((CharSequence)localObject);
        q.setText("");
      }
      if (u != null) {
        if (paramView == u)
        {
          int i1 = o.indexOfChild(u);
          localObject = (String)z.get(i1 - 1);
          paramView = (String)A.get(localObject);
          String[] arrayOfString = paramView.split(";");
          if (arrayOfString.length <= 1) {
            break label343;
          }
          paramView = arrayOfString[0];
        }
      }
    }
    label343:
    for (;;)
    {
      ((InputMethodManager)a.getSystemService("input_method")).hideSoftInputFromWindow(q.getWindowToken(), 0);
      ((aii)q.getAdapter()).a((String)localObject, paramView);
      return;
      u.setSelected(false);
      b(u);
      for (;;)
      {
        u = paramView;
        paramView.setSelected(true);
        a(paramView);
        ((InputMethodManager)a.getSystemService("input_method")).showSoftInput(q, 0);
        return;
        q.setCursorVisible(false);
      }
      if (u != null)
      {
        u.setSelected(false);
        b(u);
        u = null;
        q.setCursorVisible(true);
      }
      q.requestFocus();
      ((InputMethodManager)a.getSystemService("input_method")).showSoftInput(q, 1);
      if (!q.isPopupShowing()) {
        break;
      }
      return;
    }
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    D = true;
    paramConfiguration = findViewById(aih.d.mz_recipient_root);
    paramConfiguration.setPadding(getResources().getDimensionPixelSize(aih.b.mw_recipient_padding_left), paramConfiguration.getPaddingTop(), getResources().getDimensionPixelSize(aih.b.mw_recipient_padding_right), paramConfiguration.getPaddingBottom());
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    int i1 = q.getImeOptions() & 0xFF00;
    if (b()) {
      if (focusSearch(130) != null) {
        q.setImeOptions(i1 | 0x5);
      }
    }
    for (;;)
    {
      return super.onCreateInputConnection(paramEditorInfo);
      q.setImeOptions(i1 | 0x6);
      continue;
      q.setImeOptions(i1 | 0x1);
      q.setImeActionLabel(getResources().getString(aih.f.mw_recipient_edit_imeActionLabel), 1);
    }
  }
  
  public void onDetachedFromWindow()
  {
    if ((ac != null) && (c.b(ac) == 0))
    {
      ac.b();
      ac = null;
    }
    if (h != null) {
      g.quit();
    }
    ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
    if (localViewTreeObserver != null) {
      localViewTreeObserver.removeOnScrollChangedListener(ae);
    }
    super.onDetachedFromWindow();
  }
  
  public boolean onDragEvent(DragEvent paramDragEvent)
  {
    Object localObject1 = paramDragEvent.getLocalState();
    boolean bool2;
    if (!(localObject1 instanceof b))
    {
      bool2 = q.onDragEvent(paramDragEvent);
      return bool2;
    }
    int i1 = (int)paramDragEvent.getX();
    int i2 = (int)paramDragEvent.getY();
    Object localObject2 = (b)localObject1;
    localObject1 = c;
    switch (paramDragEvent.getAction())
    {
    }
    boolean bool1;
    label344:
    do
    {
      bool1 = false;
      for (;;)
      {
        bool2 = bool1;
        if (!paramDragEvent.getResult()) {
          break;
        }
        T = false;
        U = false;
        V = false;
        return bool1;
        U = true;
        W = false;
        aa = ((ArrayList)getRecipients());
        M = true;
        b(hasFocus());
        bool1 = true;
        continue;
        o.removeView((View)localObject1);
        b(hasFocus());
        bool1 = true;
        continue;
        requestFocus();
        bool1 = true;
        continue;
        int i3 = o.indexOfChild((View)localObject1);
        if (a((b)localObject2, i1, i2) != i3) {
          b(hasFocus());
        }
        bool1 = true;
      }
      T = true;
      i1 = a((b)localObject2, i1, i2);
      O = i1;
      N = true;
      if (i1 > 0)
      {
        if (P <= 0) {
          break label344;
        }
        if (i1 != P)
        {
          localObject2 = (String)z.remove(P - 1);
          z.add(i1 - 1, localObject2);
        }
        q.setCursorVisible(false);
        u = ((View)localObject1);
        ((ItemView)localObject1).setVisibility(0);
        P = 0;
      }
      for (;;)
      {
        bool1 = true;
        break;
        localObject2 = paramDragEvent.getClipData();
        if (((ClipData)localObject2).getItemCount() > 0)
        {
          localObject2 = Rfc822Tokenizer.tokenize(((ClipData)localObject2).getItemAt(0).coerceToText(a));
          if (localObject2.length > 0) {
            if (a(localObject2[0].getAddress(), localObject2[0].getName(), i1))
            {
              q.setCursorVisible(false);
              u = ((View)localObject1);
              ((ItemView)localObject1).setVisibility(0);
            }
            else
            {
              o.removeViewAt(i1);
              ((ItemView)localObject1).setVisibility(8);
              N = false;
            }
          }
        }
      }
      V = true;
    } while (W);
    if (P > 0)
    {
      if (((ItemView)localObject1).getVisibility() != 4) {
        break label591;
      }
      localObject2 = (ViewGroup)((ItemView)localObject1).getParent();
      if (localObject2 != null) {
        ((ViewGroup)localObject2).removeView((View)localObject1);
      }
      o.addView((View)localObject1, P);
      q.setCursorVisible(false);
      u = ((View)localObject1);
      ((ItemView)localObject1).setVisibility(0);
    }
    for (;;)
    {
      P = 0;
      M = false;
      b(hasFocus());
      N = false;
      if ((H) && (I != null))
      {
        I.b(this);
        H = false;
      }
      bool1 = true;
      break;
      label591:
      a(P - 1, false);
    }
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    c();
    Editable localEditable = q.getText();
    if ((paramView.getId() == aih.d.mz_recipient_edit) && (!paramBoolean) && (!TextUtils.isEmpty(localEditable)))
    {
      a(localEditable);
      q.setText("");
    }
    if (getOnFocusChangeListener() != null) {
      getOnFocusChangeListener().onFocusChange(this, paramBoolean);
    }
    if ((!paramBoolean) && (u != null))
    {
      u.setSelected(false);
      b(u);
      u = null;
      q.setCursorVisible(true);
    }
    b(paramBoolean);
    if (true == af)
    {
      o.invalidate();
      e();
      af = false;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    String str = Settings.Secure.getString(v, "default_input_method");
    int i1;
    if (!TextUtils.equals(str, F))
    {
      F = str;
      i1 = q.getImeOptions() & 0xFF00;
      if ((!b()) || (!TextUtils.isEmpty(q.getText()))) {
        break label155;
      }
      if (focusSearch(130) == null) {
        break label130;
      }
      q.setImeOptions(i1 | 0x5);
      q.setImeActionLabel(null, 5);
    }
    for (;;)
    {
      setInputType(x);
      if (q.isPopupShowing()) {
        q.a();
      }
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
      label130:
      q.setImeOptions(i1 | 0x6);
      q.setImeActionLabel(null, 6);
      continue;
      label155:
      q.setImeOptions(i1 | 0x1);
      q.setImeActionLabel(getResources().getString(aih.f.mw_recipient_edit_imeActionLabel), 1);
    }
  }
  
  public boolean onLongClick(View paramView)
  {
    if ((L) && ((paramView instanceof ItemView)))
    {
      c();
      Object localObject = q.getText();
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        a((CharSequence)localObject);
        q.setText("");
      }
      if (paramView != u) {
        d(paramView);
      }
      if (u != null)
      {
        u.setSelected(false);
        if (paramView != u) {
          b(u);
        }
        u = null;
        q.setCursorVisible(true);
      }
      paramView.setSelected(true);
      P = o.indexOfChild(paramView);
      String str1 = (String)z.get(P - 1);
      String str2 = (String)A.get(str1);
      localObject = str2;
      if (TextUtils.isEmpty(str2)) {
        localObject = str1;
      }
      localObject = ClipData.newPlainText(null, new Rfc822Token((String)localObject, str1, null).toString());
      if (ab == null) {
        ab = new b();
      }
      ab.c = ((ItemView)paramView);
      if (paramView.startDrag((ClipData)localObject, new l(paramView), ab, 0))
      {
        paramView.setVisibility(4);
        if (I != null)
        {
          I.a(this);
          H = true;
        }
      }
      return true;
    }
    return false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    q.measure(0, 0);
    if ((q.getLayout().getLineWidth(0) + q.getTotalPaddingLeft() + q.getTotalPaddingRight() >= E) && (E < o.getWidth()) && (o.indexOfChild(q) > 1))
    {
      AbsoluteLayout.LayoutParams localLayoutParams = (AbsoluteLayout.LayoutParams)q.getLayoutParams();
      if (localLayoutParams != null)
      {
        x = 0;
        y += q.getHeight();
        E = o.getWidth();
        q.setWidth(E);
      }
    }
    super.onMeasure(paramInt1, paramInt2);
    if (D)
    {
      D = false;
      b(hasFocus());
      super.onMeasure(paramInt1, paramInt2);
    }
  }
  
  public boolean requestFocus(int paramInt, Rect paramRect)
  {
    return q.requestFocus(paramInt, paramRect);
  }
  
  public void setAdapter(aii paramaii)
  {
    q.setAdapter(paramaii);
  }
  
  public void setButtonClickListener(View.OnClickListener paramOnClickListener)
  {
    r.setOnClickListener(paramOnClickListener);
  }
  
  public void setButtonVisibility(boolean paramBoolean)
  {
    C = paramBoolean;
    View localView = r;
    if (paramBoolean) {}
    for (int i1 = 0;; i1 = 8)
    {
      localView.setVisibility(i1);
      return;
    }
  }
  
  public void setDragWatcher(m paramm)
  {
    I = paramm;
  }
  
  public void setDropDownAnchor(int paramInt)
  {
    q.setDropDownAnchor(paramInt);
  }
  
  public void setEnabledDrag(boolean paramBoolean)
  {
    L = paramBoolean;
  }
  
  public void setFocusable(boolean paramBoolean)
  {
    q.setFocusable(paramBoolean);
  }
  
  public void setFocusableInTouchMode(boolean paramBoolean)
  {
    q.setFocusableInTouchMode(paramBoolean);
  }
  
  public void setHeaderText(String paramString) {}
  
  public void setHint(CharSequence paramCharSequence)
  {
    p.setText(paramCharSequence);
    s.setText(paramCharSequence);
  }
  
  public void setImeOptions(int paramInt)
  {
    int i1 = q.getImeOptions();
    q.setImeOptions(i1 & 0xFF | paramInt);
  }
  
  public void setInputType(int paramInt)
  {
    if (x != paramInt)
    {
      if (paramInt != 2) {
        break label26;
      }
      setBackgroundResource(aih.c.mw_recipient_divider_email_2px);
    }
    for (;;)
    {
      x = paramInt;
      return;
      label26:
      setBackgroundResource(aih.c.mw_recipient_divider_sms_2px);
    }
  }
  
  public void setIsValidateRecognition(boolean paramBoolean)
  {
    R = paramBoolean;
  }
  
  public void setMaxHeight(int paramInt)
  {
    w = paramInt;
  }
  
  public void setNoBackground(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue()) {
      setBackground(null);
    }
  }
  
  public void setOnDragListener(View.OnDragListener paramOnDragListener)
  {
    J = paramOnDragListener;
  }
  
  public void setOnDropDownListener(d paramd)
  {
    q.a(this, paramd);
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    q.setOnItemClickListener(paramOnItemClickListener);
  }
  
  public void setOnKeyPreImeListener(RecipientEdit.RecipientAutoCompleteTextView.a parama)
  {
    k = parama;
  }
  
  public void setOnRecipientChangedListener(e parame)
  {
    d = parame;
  }
  
  public void setOnRecipientFirstAddListener(f paramf)
  {
    c = paramf;
  }
  
  public void setOnRecipientSimpleChangedListener(g paramg)
  {
    e = paramg;
  }
  
  public void setOnSingleRecipientAddWhenGroupListener(i parami)
  {
    b = parami;
  }
  
  public void setOnTextChangedListener(j paramj)
  {
    l = paramj;
  }
  
  public void setRecipientColorType(int paramInt)
  {
    G = paramInt;
    if (G == 1)
    {
      q.setTextColor(getResources().getColor(aih.a.mw_recipient_text_green));
      t.setTextColor(getResources().getColor(aih.a.mw_recipient_text_green));
    }
    while (G != 2) {
      return;
    }
    q.setTextColor(getResources().getColor(aih.a.mw_recipient_text_red));
    t.setTextColor(getResources().getColor(aih.a.mw_recipient_text_red));
  }
  
  public void setRecipientSipStateCheckListener(h paramh)
  {
    K = paramh;
  }
  
  public void setScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    q.setOnScrollListener(paramOnScrollListener);
  }
  
  public static class ItemView
    extends LinearLayout
  {
    int a;
    int b;
    private TextView c;
    
    public ItemView(Context paramContext)
    {
      super();
    }
    
    public ItemView(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public ItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
    }
    
    public TextView a()
    {
      return c;
    }
    
    public void a(CharSequence paramCharSequence)
    {
      c.setText(paramCharSequence);
    }
    
    protected void onFinishInflate()
    {
      super.onFinishInflate();
      c = ((TextView)findViewById(aih.d.text));
    }
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      if (paramMotionEvent.getAction() == 0)
      {
        a = ((int)paramMotionEvent.getX());
        b = ((int)paramMotionEvent.getY());
      }
      return super.onTouchEvent(paramMotionEvent);
    }
    
    public void setSelected(boolean paramBoolean)
    {
      c.setSelected(paramBoolean);
    }
  }
  
  public static class RecipientAutoCompleteTextView
    extends AutoCompleteTextView
  {
    private a a;
    private RecipientEdit.d b;
    private View c;
    private b d;
    private ListAdapter e;
    private AbsListView.OnScrollListener f;
    private Drawable g;
    private Drawable h;
    private Drawable i;
    
    public RecipientAutoCompleteTextView(Context paramContext)
    {
      super();
      b();
    }
    
    public RecipientAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      b();
    }
    
    public RecipientAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
      b();
    }
    
    private void b()
    {
      h = getResources().getDrawable(aih.c.mw_list_history_background_noshadow);
      i = getResources().getDrawable(aih.c.mw_list_history_background);
    }
    
    public void a()
    {
      ListPopupWindow localListPopupWindow = getPopup();
      Object localObject2 = localListPopupWindow.getAnchorView();
      Object localObject1 = localObject2;
      int j;
      if (localObject2 == null)
      {
        if (getDropDownAnchor() != -1) {
          localObject1 = getRootView().findViewById(getDropDownAnchor());
        }
      }
      else
      {
        localObject2 = c.a(localListPopupWindow);
        if (((PopupWindow)localObject2).getMaxAvailableHeight((View)localObject1, getDropDownVerticalOffset()) >= getResources().getDimensionPixelSize(aih.b.mw_recipient_list_item_height) * getAdapter().getCount() + 0) {
          break label126;
        }
        if (g == h) {
          break label159;
        }
        g = h;
        j = 1;
      }
      for (;;)
      {
        if (j != 0)
        {
          localObject1 = c.a((PopupWindow)localObject2);
          if (localObject1 == null) {
            setDropDownBackgroundDrawable(g);
          }
        }
        else
        {
          return;
          localObject1 = this;
          break;
          label126:
          if (g == i) {
            break label159;
          }
          g = i;
          j = 1;
          continue;
        }
        ((View)localObject1).setBackground(g);
        return;
        label159:
        j = 0;
      }
    }
    
    public void a(View paramView, RecipientEdit.d paramd)
    {
      c = paramView;
      b = paramd;
    }
    
    public void a(boolean paramBoolean)
    {
      boolean bool = isPopupShowing();
      super.dismissDropDown();
      if (paramBoolean) {
        ((aii)getAdapter()).d();
      }
      if ((b != null) && (bool)) {
        b.a(c, false);
      }
    }
    
    public void dismissDropDown()
    {
      a(true);
    }
    
    public ListPopupWindow getPopup()
    {
      return c.a(this);
    }
    
    public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
    {
      if ((a != null) && (a.a(this, paramInt, paramKeyEvent))) {
        return true;
      }
      return super.onKeyPreIme(paramInt, paramKeyEvent);
    }
    
    protected void performFiltering(CharSequence paramCharSequence, int paramInt)
    {
      if (((ParcelableImageSpan[])((Editable)paramCharSequence).getSpans(0, paramCharSequence.length(), ParcelableImageSpan.class)).length == 0)
      {
        Object localObject1 = paramCharSequence;
        if ((paramCharSequence instanceof Spannable))
        {
          Object localObject2 = (Spannable)paramCharSequence;
          int j = BaseInputConnection.getComposingSpanStart((Spannable)localObject2);
          int k = BaseInputConnection.getComposingSpanEnd((Spannable)localObject2);
          localObject1 = paramCharSequence;
          if (j >= 0)
          {
            localObject1 = paramCharSequence;
            if (k >= 0)
            {
              localObject1 = ((Spannable)localObject2).subSequence(j, k);
              localObject2 = ((CharSequence)localObject1).toString().replace("'", "");
              localObject1 = paramCharSequence.toString().replace((CharSequence)localObject1, (CharSequence)localObject2);
            }
          }
        }
        super.performFiltering((CharSequence)localObject1, paramInt);
      }
    }
    
    public <T extends ListAdapter,  extends Filterable> void setAdapter(T paramT)
    {
      super.setAdapter(paramT);
      if (d == null) {
        d = new b(null);
      }
      for (;;)
      {
        e = paramT;
        if (e != null) {
          paramT.registerDataSetObserver(d);
        }
        return;
        if (e != null) {
          e.unregisterDataSetObserver(d);
        }
      }
    }
    
    public void setOnKeyPreImeListener(a parama)
    {
      a = parama;
    }
    
    public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
    {
      f = paramOnScrollListener;
    }
    
    public void showDropDown()
    {
      boolean bool = isPopupShowing();
      super.showDropDown();
      if ((b != null) && (!bool)) {
        b.a(c, true);
      }
      if ((f != null) && (!bool)) {
        getPopup().getListView().setOnScrollListener(f);
      }
    }
    
    public static abstract interface a
    {
      public abstract boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent);
    }
    
    class b
      extends DataSetObserver
    {
      private b() {}
      
      public void onChanged()
      {
        a();
      }
    }
    
    static class c
    {
      public static View a(PopupWindow paramPopupWindow)
      {
        return (View)a(paramPopupWindow, "android.widget.PopupWindow", "mPopupView");
      }
      
      public static ListPopupWindow a(AutoCompleteTextView paramAutoCompleteTextView)
      {
        return (ListPopupWindow)a(paramAutoCompleteTextView, "android.widget.AutoCompleteTextView", "mPopup");
      }
      
      public static PopupWindow a(ListPopupWindow paramListPopupWindow)
      {
        return (PopupWindow)a(paramListPopupWindow, "android.widget.ListPopupWindow", "mPopup");
      }
      
      public static Object a(Object paramObject, String paramString1, String paramString2)
      {
        try
        {
          paramString1 = Class.forName(paramString1).getDeclaredField(paramString2);
          paramString1.setAccessible(true);
          paramObject = paramString1.get(paramObject);
          return paramObject;
        }
        catch (Exception paramObject)
        {
          ((Exception)paramObject).printStackTrace();
        }
        return null;
      }
    }
  }
  
  public static class a
  {
    public String a;
    public String b;
    
    public a(String paramString1, String paramString2)
    {
      a = paramString1;
      b = paramString2;
    }
  }
  
  static class b
  {
    public int a;
    public int b;
    public RecipientEdit.ItemView c;
  }
  
  static class c
    implements Runnable
  {
    private final Object a = new Object();
    private Looper b;
    private int c;
    
    public c(String arg1)
    {
      new Thread(this, ???).start();
      synchronized (a)
      {
        for (;;)
        {
          Looper localLooper = b;
          if (localLooper != null) {
            break;
          }
          try
          {
            a.wait();
          }
          catch (InterruptedException localInterruptedException) {}
        }
        return;
      }
    }
    
    public Looper a()
    {
      return b;
    }
    
    public void b()
    {
      b.quit();
    }
    
    public void run()
    {
      synchronized (a)
      {
        Looper.prepare();
        b = Looper.myLooper();
        a.notifyAll();
        Looper.loop();
        return;
      }
    }
  }
  
  public static abstract interface d
  {
    public abstract void a(View paramView, boolean paramBoolean);
  }
  
  public static abstract interface e
  {
    public abstract void a();
  }
  
  public static abstract interface f
  {
    public abstract void a();
  }
  
  public static abstract interface g
  {
    public abstract void a(RecipientEdit paramRecipientEdit);
  }
  
  public static abstract interface h
  {
    public abstract void a(String paramString, int paramInt);
    
    public abstract void a(String paramString, int paramInt1, int paramInt2, int paramInt3);
    
    public abstract void b(String paramString, int paramInt1, int paramInt2, int paramInt3);
  }
  
  public static abstract interface i
  {
    public abstract void a(int paramInt);
  }
  
  public static abstract interface j
  {
    public abstract void a(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3);
  }
  
  class k
    extends Handler
  {
    public k(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      Object localObject1 = obj).b;
      String str = obj).a;
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject2 = ((String)localObject1).split(";");
        if (localObject2.length > 1) {
          paramMessage = localObject2[0];
        }
      }
      for (Object localObject2 = localObject2[1];; localObject2 = "")
      {
        localObject1 = paramMessage;
        if (TextUtils.isEmpty(paramMessage)) {
          localObject1 = ((aii)RecipientEdit.a(RecipientEdit.this).getAdapter()).a(str);
        }
        paramMessage = (Message)localObject1;
        if (!TextUtils.isEmpty((CharSequence)localObject2)) {
          paramMessage = (String)localObject1 + ";" + (String)localObject2;
        }
        paramMessage = new RecipientEdit.a(str, paramMessage);
        localObject1 = Message.obtain(j, 1);
        obj = paramMessage;
        j.sendMessage((Message)localObject1);
        return;
        paramMessage = (Message)localObject1;
      }
    }
  }
  
  class l
    extends View.DragShadowBuilder
  {
    public l(View paramView)
    {
      super();
    }
    
    public void onProvideShadowMetrics(Point paramPoint1, Point paramPoint2)
    {
      RecipientEdit.ItemView localItemView = (RecipientEdit.ItemView)getView();
      if (localItemView != null)
      {
        paramPoint1.set(localItemView.getWidth(), localItemView.getHeight());
        paramPoint2.set(a, y - 10);
        ga = a;
        gb = (y - 10 + 20);
        return;
      }
      Log.e("RecipientEdit", "Asked for drag thumb metrics but no view");
    }
  }
  
  public static abstract interface m
  {
    public abstract void a(View paramView);
    
    public abstract void b(View paramView);
  }
  
  public final class n
    implements Runnable
  {
    private String b;
    private String c;
    
    public n(String paramString1, String paramString2)
    {
      b = paramString1;
      c = paramString2;
    }
    
    public void run()
    {
      int i = RecipientEdit.i(RecipientEdit.this).indexOf(b);
      String str;
      if (i > -1)
      {
        RecipientEdit.h(RecipientEdit.this).put(b, c);
        str = c;
        if (!TextUtils.isEmpty(c)) {
          break label94;
        }
        str = b;
      }
      label94:
      for (;;)
      {
        ((RecipientEdit.ItemView)RecipientEdit.j(RecipientEdit.this).getChildAt(i + 1)).a(str);
        b(hasFocus());
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.RecipientEdit
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */