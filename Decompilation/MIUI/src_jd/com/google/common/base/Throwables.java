package com.google.common.base;

public final class Throwables
{
  public static <X extends Throwable> void propagateIfInstanceOf(Throwable paramThrowable, Class<X> paramClass)
    throws Throwable
  {
    if ((paramThrowable != null) && (paramClass.isInstance(paramThrowable))) {
      throw ((Throwable)paramClass.cast(paramThrowable));
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Throwables
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */