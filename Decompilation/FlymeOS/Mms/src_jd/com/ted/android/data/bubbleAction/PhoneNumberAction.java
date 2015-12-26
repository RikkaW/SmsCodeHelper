package com.ted.android.data.bubbleAction;

import com.ted.android.data.BubbleEntity;

public class PhoneNumberAction
  extends CommonAction
{
  public PhoneNumberAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
    showType = 2;
    action = 22;
    buttonText = "拨打电话";
  }
  
  public PhoneNumberAction(BubbleEntity paramBubbleEntity, String paramString)
  {
    super(paramBubbleEntity, paramString);
    showType = 2;
  }
  
  public static CommonAction fromJSON(BubbleEntity paramBubbleEntity, String paramString)
  {
    return new PhoneNumberAction(paramBubbleEntity, paramString);
  }
  
  public String getNumber()
  {
    if (parent != null) {
      return parent.getMatchedWords();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.PhoneNumberAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */