package android.support.v4.view;

import android.view.LayoutInflater;

class LayoutInflaterCompatLollipop
{
  static void setFactory(LayoutInflater paramLayoutInflater, LayoutInflaterFactory paramLayoutInflaterFactory)
  {
    if (paramLayoutInflaterFactory != null) {}
    for (paramLayoutInflaterFactory = new LayoutInflaterCompatHC.FactoryWrapperHC(paramLayoutInflaterFactory);; paramLayoutInflaterFactory = null)
    {
      paramLayoutInflater.setFactory2(paramLayoutInflaterFactory);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.LayoutInflaterCompatLollipop
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */