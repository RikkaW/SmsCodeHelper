package com.android.mms.transaction;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Observable
{
  private Iterator<Observer> mIterator;
  private final ArrayList<Observer> mObservers = new ArrayList();
  
  public void attach(Observer paramObserver)
  {
    mObservers.add(paramObserver);
  }
  
  public void detach(Observer paramObserver)
  {
    if (mIterator != null)
    {
      mIterator.remove();
      return;
    }
    mObservers.remove(paramObserver);
  }
  
  /* Error */
  public void notifyObservers()
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield 19	com/android/mms/transaction/Observable:mObservers	Ljava/util/ArrayList;
    //   5: invokevirtual 41	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   8: putfield 29	com/android/mms/transaction/Observable:mIterator	Ljava/util/Iterator;
    //   11: aload_0
    //   12: getfield 29	com/android/mms/transaction/Observable:mIterator	Ljava/util/Iterator;
    //   15: invokeinterface 45 1 0
    //   20: ifeq +32 -> 52
    //   23: aload_0
    //   24: getfield 29	com/android/mms/transaction/Observable:mIterator	Ljava/util/Iterator;
    //   27: invokeinterface 49 1 0
    //   32: checkcast 51	com/android/mms/transaction/Observer
    //   35: aload_0
    //   36: invokeinterface 55 2 0
    //   41: goto -30 -> 11
    //   44: astore_1
    //   45: aload_0
    //   46: aconst_null
    //   47: putfield 29	com/android/mms/transaction/Observable:mIterator	Ljava/util/Iterator;
    //   50: aload_1
    //   51: athrow
    //   52: aload_0
    //   53: aconst_null
    //   54: putfield 29	com/android/mms/transaction/Observable:mIterator	Ljava/util/Iterator;
    //   57: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	Observable
    //   44	7	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   11	41	44	finally
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.Observable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */