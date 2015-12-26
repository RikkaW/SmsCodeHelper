package com.android.mms.smartmessage;

import aau;
import aaw.a;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.meizu.common.widget.ActionMenuItemView;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import mc;
import nh;
import ni;

public class SmartMessageViewContainer
  extends LinearLayout
{
  public static boolean a;
  Object b = null;
  private aaw.a c;
  private Context d;
  private View.OnClickListener e = new nh(this);
  
  public SmartMessageViewContainer(Context paramContext)
  {
    super(paramContext);
    d = paramContext;
  }
  
  public SmartMessageViewContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    d = paramContext;
  }
  
  public SmartMessageViewContainer(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    d = paramContext;
  }
  
  public SmartMessageViewContainer(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    d = paramContext;
  }
  
  private Object a()
  {
    Object localObject = aau.c("com.android.internal.view.menu.MenuBuilder");
    try
    {
      localObject = ((Class)localObject).getConstructor(new Class[] { Context.class }).newInstance(new Object[] { d });
      return localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
    catch (InstantiationException localInstantiationException)
    {
      for (;;)
      {
        localInstantiationException.printStackTrace();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        localIllegalAccessException.printStackTrace();
      }
    }
  }
  
  private Object a(Object paramObject, View paramView)
  {
    Class localClass = aau.c("com.android.internal.view.menu.MenuPopupHelper");
    try
    {
      paramObject = localClass.getConstructor(new Class[] { Context.class, paramObject.getClass(), View.class }).newInstance(new Object[] { d, paramObject, paramView });
      return paramObject;
    }
    catch (NoSuchMethodException paramObject)
    {
      ((NoSuchMethodException)paramObject).printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;)
      {
        ((InvocationTargetException)paramObject).printStackTrace();
      }
    }
    catch (InstantiationException paramObject)
    {
      for (;;)
      {
        ((InstantiationException)paramObject).printStackTrace();
      }
    }
    catch (IllegalAccessException paramObject)
    {
      for (;;)
      {
        ((IllegalAccessException)paramObject).printStackTrace();
      }
    }
  }
  
  private void a(Object paramObject)
  {
    try
    {
      paramObject.getClass().getDeclaredMethod("adjustWindowPositionForMz", new Class[] { Boolean.TYPE }).invoke(paramObject, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (NoSuchMethodException paramObject)
    {
      ((NoSuchMethodException)paramObject).printStackTrace();
      return;
    }
    catch (InvocationTargetException paramObject)
    {
      ((InvocationTargetException)paramObject).printStackTrace();
      return;
    }
    catch (IllegalAccessException paramObject)
    {
      ((IllegalAccessException)paramObject).printStackTrace();
    }
  }
  
  private void b(Object paramObject)
  {
    try
    {
      paramObject.getClass().getDeclaredMethod("show", new Class[0]).invoke(paramObject, new Object[0]);
      return;
    }
    catch (NoSuchMethodException paramObject)
    {
      ((NoSuchMethodException)paramObject).printStackTrace();
      return;
    }
    catch (InvocationTargetException paramObject)
    {
      ((InvocationTargetException)paramObject).printStackTrace();
      return;
    }
    catch (IllegalAccessException paramObject)
    {
      ((IllegalAccessException)paramObject).printStackTrace();
    }
  }
  
  private boolean c(Object paramObject)
  {
    try
    {
      boolean bool = ((Boolean)paramObject.getClass().getDeclaredMethod("isShowing", new Class[0]).invoke(paramObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramObject)
    {
      ((NoSuchMethodException)paramObject).printStackTrace();
      return false;
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;)
      {
        ((InvocationTargetException)paramObject).printStackTrace();
      }
    }
    catch (IllegalAccessException paramObject)
    {
      for (;;)
      {
        ((IllegalAccessException)paramObject).printStackTrace();
      }
    }
  }
  
  public void a(aaw.a parama, boolean paramBoolean)
  {
    c = parama;
    ArrayList localArrayList = c.d;
    if ((localArrayList == null) || (localArrayList.isEmpty())) {
      return;
    }
    removeAllViews();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    weight = 1.0F;
    int i = 0;
    label52:
    if (i < localArrayList.size())
    {
      if (getd == null) {
        break label176;
      }
      parama = new ActionMenuItemView(d);
      ((ActionMenuItemView)parama).setTitle(geta);
      ((ActionMenuItemView)parama).setBackgroundResource(2130838313);
      ((TextView)parama.findViewById(2131886489)).setTextSize(16.0F);
    }
    for (;;)
    {
      addView(parama, localLayoutParams);
      parama.setTag(c.d.get(i));
      parama.setClickable(paramBoolean);
      parama.setOnClickListener(e);
      a = paramBoolean;
      i += 1;
      break label52;
      break;
      label176:
      parama = (Button)LayoutInflater.from(d).inflate(2130968816, null);
      ((Button)parama).setText(geta);
    }
  }
  
  public void a(View paramView)
  {
    aaw.a locala = (aaw.a)paramView.getTag();
    if (d != null)
    {
      a(paramView, d);
      return;
    }
    locala.a((Activity)d);
  }
  
  public void a(View paramView, ArrayList<aaw.a> paramArrayList)
  {
    if ((b != null) && (c(b))) {
      return;
    }
    Object localObject = a();
    ni localni = new ni(this, localObject, paramView);
    int i = 0;
    while (i < paramArrayList.size())
    {
      localni.a(i, i, 0, geta);
      localni.a();
      i += 1;
    }
    b = a(localObject, paramView);
    a(b);
    b(b);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.smartmessage.SmartMessageViewContainer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */