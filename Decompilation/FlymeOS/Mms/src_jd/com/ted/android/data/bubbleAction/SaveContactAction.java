package com.ted.android.data.bubbleAction;

import android.content.Context;
import com.ted.android.data.BubbleEntity;
import com.ted.android.message.TextMessageParser.BusinessCardItem;

public class SaveContactAction
  extends CommonAction
{
  public TextMessageParser.BusinessCardItem item;
  
  public SaveContactAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
    action = 11;
  }
  
  public boolean doAction(Context paramContext)
  {
    return super.doAction(paramContext);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.SaveContactAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */