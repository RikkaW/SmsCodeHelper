package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;

class LayoutInflaterCompatBase
{
  static void setFactory(LayoutInflater paramLayoutInflater, LayoutInflaterFactory paramLayoutInflaterFactory)
  {
    if (paramLayoutInflaterFactory != null) {}
    for (paramLayoutInflaterFactory = new FactoryWrapper(paramLayoutInflaterFactory);; paramLayoutInflaterFactory = null)
    {
      paramLayoutInflater.setFactory(paramLayoutInflaterFactory);
      return;
    }
  }
  
  static class FactoryWrapper
    implements LayoutInflater.Factory
  {
    final LayoutInflaterFactory mDelegateFactory;
    
    FactoryWrapper(LayoutInflaterFactory paramLayoutInflaterFactory)
    {
      mDelegateFactory = paramLayoutInflaterFactory;
    }
    
    public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
    {
      return mDelegateFactory.onCreateView(null, paramString, paramContext, paramAttributeSet);
    }
    
    public String toString()
    {
      return getClass().getName() + "{" + mDelegateFactory + "}";
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.LayoutInflaterCompatBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */