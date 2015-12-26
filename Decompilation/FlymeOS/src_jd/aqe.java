import android.text.TextUtils;
import com.ted.android.core.ReplyMsgItem;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.QuickReplyAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class aqe
  extends aqc
{
  private static final String a = aqe.class.getSimpleName();
  private static aqe b;
  private ata c = new ata();
  
  public static aqe a()
  {
    if (b == null) {}
    try
    {
      b = new aqe();
      return b;
    }
    finally {}
  }
  
  protected List<BubbleEntity> a(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    if (TextUtils.isEmpty(paramString1)) {
      return localArrayList;
    }
    Iterator localIterator = c.a(paramString1).iterator();
    label68:
    label294:
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      Object localObject1 = (asu)localIterator.next();
      Object localObject2 = localArrayList.iterator();
      int i;
      if (!((Iterator)localObject2).hasNext()) {
        i = 0;
      }
      for (;;)
      {
        if (i != 0) {
          break label294;
        }
        BubbleEntity localBubbleEntity = new BubbleEntity();
        localBubbleEntity.setId("-5");
        localBubbleEntity.setMatchedWords(((asu)localObject1).b());
        localBubbleEntity.setIndex(paramString1.indexOf(((asu)localObject1).b()));
        localObject1 = ReplyMsgItem.fromReply((asu)localObject1, paramString2);
        if (localObject1 == null) {
          break;
        }
        localBubbleEntity.addAction(((ReplyMsgItem)localObject1).toAction(localBubbleEntity));
        localArrayList.add(localBubbleEntity);
        break;
        localBubbleEntity = (BubbleEntity)((Iterator)localObject2).next();
        if (!TextUtils.equals(((asu)localObject1).b(), localBubbleEntity.getMatchedWords())) {
          break label68;
        }
        localObject2 = localBubbleEntity.getActions();
        Object localObject3;
        if ((localObject2 != null) && (((List)localObject2).size() > 0))
        {
          localObject3 = (QuickReplyAction)((List)localObject2).get(0);
          get0buttonText = getItemdescription;
        }
        localObject2 = ReplyMsgItem.fromReply((asu)localObject1, paramString2);
        if (localObject2 != null)
        {
          localObject3 = ((ReplyMsgItem)localObject2).toAction(localBubbleEntity);
          buttonText = description;
          localBubbleEntity.addAction((ActionBase)localObject3);
          i = 1;
        }
        else
        {
          i = 1;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     aqe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */