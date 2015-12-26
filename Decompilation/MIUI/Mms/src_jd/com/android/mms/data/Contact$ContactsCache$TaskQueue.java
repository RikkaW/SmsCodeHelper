package com.android.mms.data;

import java.util.ArrayList;

class Contact$ContactsCache$TaskQueue
{
  private final ArrayList<Contact> mThingsToLoad = new ArrayList();
  Thread mWorkerThread = new Thread(new Runnable()
  {
    public void run()
    {
      for (;;)
      {
        Contact localContact = null;
        synchronized (mThingsToLoad)
        {
          int i = mThingsToLoad.size();
          if (i == 0) {}
          try
          {
            mThingsToLoad.wait();
            if (mThingsToLoad.size() > 0) {
              localContact = (Contact)mThingsToLoad.remove(0);
            }
            if (localContact == null) {
              continue;
            }
            Contact.ContactsCache.access$700(Contact.access$300(), localContact);
          }
          catch (InterruptedException localInterruptedException) {}
        }
      }
    }
  });
  
  public Contact$ContactsCache$TaskQueue()
  {
    mWorkerThread.setName("SingleContactLoader");
    mWorkerThread.setPriority(1);
    mWorkerThread.start();
  }
  
  public void push(Contact paramContact)
  {
    synchronized (mThingsToLoad)
    {
      if (!mThingsToLoad.contains(paramContact))
      {
        mThingsToLoad.add(paramContact);
        mThingsToLoad.notify();
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Contact.ContactsCache.TaskQueue
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */