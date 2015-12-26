package com.ted.android.data.bubbleAction;

import android.content.Context;
import com.ted.android.core.ReplyMsgItem;
import com.ted.android.data.BubbleEntity;
import org.json.JSONObject;

public class QuickReplyAction
  extends CommonAction
{
  private static final String KEY_ITEM = "item";
  private ReplyMsgItem item;
  
  public QuickReplyAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
  }
  
  public QuickReplyAction(BubbleEntity paramBubbleEntity, String paramString)
  {
    super(paramBubbleEntity, paramString);
  }
  
  public static CommonAction fromJSON(BubbleEntity paramBubbleEntity, String paramString)
  {
    String str = new JSONObject(paramString).getString("item");
    paramBubbleEntity = new QuickReplyAction(paramBubbleEntity, paramString);
    paramBubbleEntity.setItem(ReplyMsgItem.fromJson(str));
    return paramBubbleEntity;
  }
  
  public boolean doAction(Context paramContext)
  {
    return super.doAction(paramContext);
  }
  
  public ReplyMsgItem getItem()
  {
    return item;
  }
  
  public void setItem(ReplyMsgItem paramReplyMsgItem)
  {
    item = paramReplyMsgItem;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = super.toJSON();
    localJSONObject.put("item", item.toJSONObject());
    return localJSONObject;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("buttonText: ").append(buttonText).append("  ").append(" Action:").append(action);
    if (item != null) {
      localStringBuilder.append(" Message: ").append(item.message).append(" Number: ").append(item.number);
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.QuickReplyAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */