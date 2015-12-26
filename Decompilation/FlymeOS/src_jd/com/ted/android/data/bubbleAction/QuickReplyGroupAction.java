package com.ted.android.data.bubbleAction;

import android.content.Context;
import com.ted.android.core.ReplyMsgItem;
import com.ted.android.data.BubbleEntity;
import java.util.List;
import org.json.JSONObject;

public class QuickReplyGroupAction
  extends CommonAction
{
  private static final String KEY_ITMES = "items";
  private static final String REPLY_ICON = "http://img.teddymobile.cn/2015/03/23/ba7b8e8596c6d0dbfd91796f8dd3aa35_60X60.png";
  private List<ReplyMsgItem> items;
  
  public QuickReplyGroupAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
    action = 20;
    buttonText = "快捷回复";
    icon = "http://img.teddymobile.cn/2015/03/23/ba7b8e8596c6d0dbfd91796f8dd3aa35_60X60.png";
  }
  
  public boolean doAction(Context paramContext)
  {
    return super.doAction(paramContext);
  }
  
  public List<ReplyMsgItem> getAllReplyMsgItem()
  {
    return items;
  }
  
  public void setItems(List<ReplyMsgItem> paramList)
  {
    items = paramList;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = super.toJSON();
    localJSONObject.put("items", ReplyMsgItem.toJSONArrayString(items));
    return localJSONObject;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.QuickReplyGroupAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */