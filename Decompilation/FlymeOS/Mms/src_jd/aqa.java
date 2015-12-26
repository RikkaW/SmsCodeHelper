import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.CommonAction;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aqa
  extends aqc
{
  private static final Pattern a = Pattern.compile(anv.b("f5f1bd25d46e755118708f42cdf0655cf8bd061e5afee12f118ae26d076f9e03f5f1bd25d46e7551350c7ada387309c9e78b62a2ce9fb28a265efd853e75322395ab322304fd867e1d2b35d5a61673f7ef07fcf00a43626c64197971969d530c84908bd2dad73086", DataBus.FILE_MASK));
  private static aqa b;
  
  public static aqa a()
  {
    if (b == null) {}
    try
    {
      b = new aqa();
      return b;
    }
    finally {}
  }
  
  protected List<BubbleEntity> a(String paramString1, String paramString2)
  {
    paramString2 = new ArrayList();
    if (paramString1 != null) {
      paramString1 = a.matcher(paramString1);
    }
    for (;;)
    {
      if (!paramString1.find()) {
        return paramString2;
      }
      BubbleEntity localBubbleEntity = new BubbleEntity();
      localBubbleEntity.setId("-8");
      localBubbleEntity.setMatchedWords(paramString1.group(0));
      localBubbleEntity.setIndex(paramString1.start(0));
      localBubbleEntity.setShowType(2);
      CommonAction localCommonAction = new CommonAction(localBubbleEntity);
      action = 9;
      showType = 2;
      buttonText = "发送邮件";
      url = paramString1.group(0);
      localBubbleEntity.addAction(localCommonAction);
      paramString2.add(localBubbleEntity);
    }
  }
}

/* Location:
 * Qualified Name:     aqa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */