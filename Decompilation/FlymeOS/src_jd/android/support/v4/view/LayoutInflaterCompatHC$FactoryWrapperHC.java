package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater.Factory2;
import android.view.View;

class LayoutInflaterCompatHC$FactoryWrapperHC
  extends LayoutInflaterCompatBase.FactoryWrapper
  implements LayoutInflater.Factory2
{
  LayoutInflaterCompatHC$FactoryWrapperHC(LayoutInflaterFactory paramLayoutInflaterFactory)
  {
    super(paramLayoutInflaterFactory);
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return mDelegateFactory.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.LayoutInflaterCompatHC.FactoryWrapperHC
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */