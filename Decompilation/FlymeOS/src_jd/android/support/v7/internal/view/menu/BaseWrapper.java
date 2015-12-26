package android.support.v7.internal.view.menu;

class BaseWrapper<T>
{
  final T mWrappedObject;
  
  BaseWrapper(T paramT)
  {
    if (paramT == null) {
      throw new IllegalArgumentException("Wrapped Object can not be null.");
    }
    mWrappedObject = paramT;
  }
  
  public T getWrappedObject()
  {
    return (T)mWrappedObject;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.BaseWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */