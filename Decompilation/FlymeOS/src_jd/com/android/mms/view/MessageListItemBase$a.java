package com.android.mms.view;

import abm.a;
import vv;
import zy;

public class MessageListItemBase$a
  implements zy<abm.a>
{
  protected long a;
  protected final MessageListItemBase b;
  
  public MessageListItemBase$a(MessageListItemBase paramMessageListItemBase)
  {
    b = paramMessageListItemBase;
    a = paramMessageListItemBase.getMessageItem().M();
  }
  
  public void a(abm.a parama, Throwable paramThrowable)
  {
    paramThrowable = b.M;
    if ((paramThrowable != null) && (paramThrowable.M() == a))
    {
      if (b) {
        b.b(null, a);
      }
    }
    else {
      return;
    }
    b.a(null, a);
  }
  
  public void a(MessageListItemBase paramMessageListItemBase)
  {
    a = paramMessageListItemBase.getMessageItem().M();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessageListItemBase.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */