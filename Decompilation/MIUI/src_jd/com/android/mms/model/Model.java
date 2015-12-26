package com.android.mms.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Model
{
  protected ArrayList<IModelChangedObserver> mModelChangedObservers = new ArrayList();
  
  protected void notifyModelChanged(boolean paramBoolean)
  {
    Iterator localIterator = mModelChangedObservers.iterator();
    while (localIterator.hasNext()) {
      ((IModelChangedObserver)localIterator.next()).onModelChanged(this, paramBoolean);
    }
  }
  
  public void registerModelChangedObserver(IModelChangedObserver paramIModelChangedObserver)
  {
    if (!mModelChangedObservers.contains(paramIModelChangedObserver))
    {
      mModelChangedObservers.add(paramIModelChangedObserver);
      registerModelChangedObserverInDescendants(paramIModelChangedObserver);
    }
  }
  
  protected void registerModelChangedObserverInDescendants(IModelChangedObserver paramIModelChangedObserver) {}
  
  public void unregisterAllModelChangedObservers()
  {
    unregisterAllModelChangedObserversInDescendants();
    mModelChangedObservers.clear();
  }
  
  protected void unregisterAllModelChangedObserversInDescendants() {}
  
  public void unregisterModelChangedObserver(IModelChangedObserver paramIModelChangedObserver)
  {
    mModelChangedObservers.remove(paramIModelChangedObserver);
    unregisterModelChangedObserverInDescendants(paramIModelChangedObserver);
  }
  
  protected void unregisterModelChangedObserverInDescendants(IModelChangedObserver paramIModelChangedObserver) {}
}

/* Location:
 * Qualified Name:     com.android.mms.model.Model
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */