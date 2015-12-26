package com.google.common.base;

import java.io.Serializable;

class Suppliers$MemoizingSupplier<T>
  implements Supplier<T>, Serializable
{
  private static final long serialVersionUID = 0L;
  final Supplier<T> delegate;
}

/* Location:
 * Qualified Name:     com.google.common.base.Suppliers.MemoizingSupplier
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */