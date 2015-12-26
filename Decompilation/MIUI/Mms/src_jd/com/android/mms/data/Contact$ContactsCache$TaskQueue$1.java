package com.android.mms.data;

import java.util.ArrayList;

class Contact$ContactsCache$TaskQueue$1
  implements Runnable
{
  Contact$ContactsCache$TaskQueue$1(Contact.ContactsCache.TaskQueue paramTaskQueue) {}
  
  public void run()
  {
    for (;;)
    {
      Contact localContact = null;
      synchronized (Contact.ContactsCache.TaskQueue.access$600(this$0))
      {
        int i = Contact.ContactsCache.TaskQueue.access$600(this$0).size();
        if (i == 0) {}
        try
        {
          Contact.ContactsCache.TaskQueue.access$600(this$0).wait();
          if (Contact.ContactsCache.TaskQueue.access$600(this$0).size() > 0) {
            localContact = (Contact)Contact.ContactsCache.TaskQueue.access$600(this$0).remove(0);
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
}

/* Location:
 * Qualified Name:     com.android.mms.data.Contact.ContactsCache.TaskQueue.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */