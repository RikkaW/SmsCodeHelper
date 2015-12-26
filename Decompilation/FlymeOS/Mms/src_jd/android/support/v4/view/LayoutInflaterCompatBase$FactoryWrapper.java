package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater.Factory;
import android.view.View;

class LayoutInflaterCompatBase$FactoryWrapper
  implements LayoutInflater.Factory
{
  final LayoutInflaterFactory mDelegateFactory;
  
  LayoutInflaterCompatBase$FactoryWrapper(LayoutInflaterFactory paramLayoutInflaterFactory)
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

/* Location:
 * Qualified Name:     android.support.v4.view.LayoutInflaterCompatBase.FactoryWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */