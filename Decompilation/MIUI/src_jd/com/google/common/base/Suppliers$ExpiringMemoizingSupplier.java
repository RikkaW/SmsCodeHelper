package com.google.common.base;

import java.io.Serializable;

class Suppliers$ExpiringMemoizingSupplier<T>
  implements Supplier<T>, Serializable
{
  private static final long serialVersionUID = 0L;
  final Supplier<T> delegate;
  final long durationNanos;
}

/* Location:
 * Qualified Name:     com.google.common.base.Suppliers.ExpiringMemoizingSupplier
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */