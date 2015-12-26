import android.text.TextUtils;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.CommonAction;
import com.ted.android.message.BubbleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class aqb
  extends aqc
{
  private static final String a = aqb.class.getSimpleName();
  private static aqb b;
  
  public static aqb a()
  {
    if (b == null) {}
    try
    {
      b = new aqb();
      return b;
    }
    finally {}
  }
  
  protected List<BubbleEntity> a(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = BubbleUtils.getAddressFromSmsBodyRegex(paramString1);
    if ((localObject1 == null) || (((List)localObject1).size() <= 0)) {
      return localArrayList;
    }
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext()) {
        return localArrayList;
      }
      Object localObject2 = (String)((Iterator)localObject1).next();
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        BubbleEntity localBubbleEntity = new BubbleEntity();
        localBubbleEntity.setMatchedWords((String)localObject2);
        localBubbleEntity.setIndex(paramString1.indexOf((String)localObject2));
        localBubbleEntity.setId("-3");
        localObject2 = new CommonAction(localBubbleEntity);
        icon = "http://img.teddymobile.cn/2015/01/31/1589eaf61e688465f85897fc29cadb52_60X60.png";
        number = paramString2;
        buttonText = "地图导航";
        action = 10;
        localBubbleEntity.addAction((ActionBase)localObject2);
        localArrayList.add(localBubbleEntity);
      }
    }
  }
}

/* Location:
 * Qualified Name:     aqb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */