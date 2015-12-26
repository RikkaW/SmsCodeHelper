package com.ted.android.data;

import android.text.TextUtils;
import com.ted.android.core.ReplyMsgItem;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.QuickReplyAction;
import com.ted.android.data.bubbleAction.QuickReplyGroupAction;
import com.ted.android.message.BubbleUtils;
import com.ted.android.smscard.CardBase;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SmsEntity
{
  private static final String BODY_KEY = "body";
  private static final String CARD_BASE_KEY = "cardBase";
  private static final String DATE_KEY = "date";
  private static final String ENTITY_LIST_KEY = "entities";
  private static final String MSG_ID_KEY = "msgId";
  private static final String NUMBER_KEY = "number";
  private static final String TAG = SmsEntity.class.getSimpleName();
  private List<ActionBase> actions;
  private String body;
  private CardBase cardBase;
  private long date;
  private List<BubbleEntity> entities;
  private String msgId;
  private String number;
  
  private boolean filterOut(BubbleEntity paramBubbleEntity, int paramInt)
  {
    int i = paramBubbleEntity.getShowType();
    if ((paramInt == 0) && (i != 0)) {}
    while (((paramInt == 1) && (i != 0) && (i != 1)) || ((paramInt == 2) && (i != 0) && (i != 2))) {
      return true;
    }
    return false;
  }
  
  public static SmsEntity fromJSON(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      long l1 = System.currentTimeMillis();
      Object localObject = new JSONObject(paramString);
      paramString = new SmsEntity();
      msgId = ((JSONObject)localObject).getString("msgId");
      date = ((JSONObject)localObject).getLong("date");
      if (((JSONObject)localObject).has("entities")) {
        entities = BubbleEntity.fromJSONArray(((JSONObject)localObject).getString("entities"));
      }
      localObject = BubbleUtils.getStringWithIgnore((JSONObject)localObject, "cardBase");
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        paramString.setCardBase(CardBase.fromJSON((String)localObject));
      }
      long l2 = System.currentTimeMillis();
      TedSDKLog.d(TAG, "SmsEntity parse startTime: " + (l2 - l1));
      paramString.updateBubbleParent();
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public void addBubbleEntities(List<BubbleEntity> paramList)
  {
    if (entities == null) {
      entities = new ArrayList();
    }
    entities.addAll(paramList);
  }
  
  public List<ActionBase> getAllActions()
  {
    if ((entities == null) || (entities.size() == 0)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = entities.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      BubbleEntity localBubbleEntity = (BubbleEntity)localIterator.next();
      if ((localBubbleEntity.getActions() != null) && (localBubbleEntity.getActions().size() > 0)) {
        localArrayList.addAll(localBubbleEntity.getActions());
      }
    }
  }
  
  public List<ActionBase> getAllActions(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    if ((entities == null) || (entities.size() == 0)) {
      return localArrayList;
    }
    Iterator localIterator = entities.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      BubbleEntity localBubbleEntity = (BubbleEntity)localIterator.next();
      if ((!filterOut(localBubbleEntity, paramInt)) && (localBubbleEntity.getActions() != null) && (localBubbleEntity.getActions().size() > 0)) {
        localArrayList.addAll(localBubbleEntity.getActions());
      }
    }
  }
  
  public List<ActionBase> getAllActionsWithQuickReplyGroup()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if ((entities == null) || (entities.size() == 0)) {
      return localArrayList1;
    }
    Object localObject1 = entities.iterator();
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        if (localArrayList2.size() > 0)
        {
          localObject1 = new QuickReplyGroupAction(new BubbleEntity());
          ((QuickReplyGroupAction)localObject1).setItems(localArrayList2);
          localArrayList1.add(localObject1);
        }
        return localArrayList1;
      }
      Iterator localIterator = ((BubbleEntity)((Iterator)localObject1).next()).getActions().iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = (ActionBase)localIterator.next();
        if (action == 19)
        {
          localObject2 = ((QuickReplyAction)localObject2).getItem();
          if (TextUtils.isEmpty(number)) {
            number = number;
          }
          localArrayList2.add(localObject2);
        }
        else
        {
          localArrayList1.add(localObject2);
        }
      }
    }
  }
  
  public List<ActionBase> getAllActionsWithQuickReplyGroup(int paramInt)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if ((entities == null) || (entities.size() == 0)) {
      return localArrayList1;
    }
    Object localObject1 = entities.iterator();
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        if (localArrayList2.size() > 0)
        {
          localObject1 = new QuickReplyGroupAction(new BubbleEntity());
          ((QuickReplyGroupAction)localObject1).setItems(localArrayList2);
          localArrayList1.add(localObject1);
        }
        return localArrayList1;
      }
      Object localObject2 = (BubbleEntity)((Iterator)localObject1).next();
      if (!filterOut((BubbleEntity)localObject2, paramInt))
      {
        localObject2 = ((BubbleEntity)localObject2).getActions().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Object localObject3 = (ActionBase)((Iterator)localObject2).next();
          if (action == 19)
          {
            localObject3 = ((QuickReplyAction)localObject3).getItem();
            if (TextUtils.isEmpty(number)) {
              number = number;
            }
            localArrayList2.add(localObject3);
          }
          else
          {
            localArrayList1.add(localObject3);
          }
        }
      }
    }
  }
  
  public List<BubbleEntity> getAllEntities()
  {
    return entities;
  }
  
  public List<BubbleEntity> getAllEntities(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    if ((entities == null) || (entities.size() == 0)) {
      return null;
    }
    Iterator localIterator = entities.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      BubbleEntity localBubbleEntity = (BubbleEntity)localIterator.next();
      if (!filterOut(localBubbleEntity, paramInt)) {
        localArrayList.add(localBubbleEntity);
      }
    }
  }
  
  public String getBody()
  {
    return body;
  }
  
  public CardBase getCardBase()
  {
    return cardBase;
  }
  
  public long getDate()
  {
    return date;
  }
  
  public BubbleEntity getEntityByMatchedWords(String paramString)
  {
    if ((entities == null) || (entities.size() == 0)) {
      return null;
    }
    Iterator localIterator = entities.iterator();
    BubbleEntity localBubbleEntity;
    do
    {
      if (!localIterator.hasNext()) {
        return null;
      }
      localBubbleEntity = (BubbleEntity)localIterator.next();
    } while (!TextUtils.equals(localBubbleEntity.getMatchedWords(), paramString));
    return localBubbleEntity;
  }
  
  public String getMsgId()
  {
    return msgId;
  }
  
  public String getNumber()
  {
    return number;
  }
  
  public void setBody(String paramString)
  {
    body = paramString;
  }
  
  public void setCardBase(CardBase paramCardBase)
  {
    cardBase = paramCardBase;
    if (paramCardBase != null) {
      cardBase.setParent(this);
    }
  }
  
  public void setDate(long paramLong)
  {
    date = paramLong;
  }
  
  public void setMsgId(long paramLong)
  {
    msgId = String.valueOf(paramLong);
  }
  
  public void setNumber(String paramString)
  {
    number = paramString;
  }
  
  public String toComparedBatchResults()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(number).append("\t").append(body).append("\t id:");
    if (cardBase == null)
    {
      localStringBuilder.append("-1").append("\n");
      localStringBuilder.append("[Teddy]").append("\t");
      localStringBuilder.append("No Card Data");
    }
    for (;;)
    {
      localStringBuilder.append("\n");
      return localStringBuilder.toString();
      localStringBuilder.append(cardBase.getMatchedId()).append("\n");
      localStringBuilder.append("[Teddy]").append("\t");
      localStringBuilder.append(cardBase.toFormedString());
    }
  }
  
  public String toFormedString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(number).append(" | ");
    localStringBuilder.append(body).append(" | ");
    int i;
    if (cardBase == null)
    {
      localStringBuilder.append("-1").append(" | ").append("null").append(" | ");
      if ((entities != null) && (entities.size() > 0)) {
        i = 0;
      }
    }
    for (;;)
    {
      if (i >= entities.size())
      {
        return localStringBuilder.toString();
        localStringBuilder.append(cardBase.toFormedString()).append(" | ");
        break;
      }
      localStringBuilder.append(((BubbleEntity)entities.get(i)).toFormedString());
      i += 1;
    }
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("msgId", msgId);
      localJSONObject.put("date", date);
      if ((entities != null) && (entities.size() > 0)) {
        localJSONObject.put("entities", BubbleEntity.toJSONArray(entities));
      }
      if (cardBase != null) {
        localJSONObject.put("cardBase", cardBase.toJSON());
      }
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      TedSDKLog.e(TAG, "JSON Generator failed!");
    }
    return localJSONObject;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SmsEntity: \n");
    localStringBuilder.append("Body: ").append(body);
    localStringBuilder.append("\nNumber: ").append(number).append("\n");
    int i;
    if ((entities != null) && (entities.size() > 0))
    {
      i = 0;
      if (i >= entities.size())
      {
        label86:
        if (cardBase == null) {
          break label172;
        }
        localStringBuilder.append("Found Cardbase: " + cardBase);
      }
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("Entity (").append(i).append(") ").append(entities.get(i));
      i += 1;
      break;
      localStringBuilder.append("Found bubble : ( 0 ) \n");
      break label86;
      label172:
      localStringBuilder.append("Found Cardbase ( 0 ) \n");
    }
  }
  
  public void updateBubbleParent()
  {
    Iterator localIterator;
    if ((entities != null) && (entities.size() > 0)) {
      localIterator = entities.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((BubbleEntity)localIterator.next()).setParent(this);
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.SmsEntity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */