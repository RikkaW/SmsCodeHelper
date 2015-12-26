import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.CommonAction;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aqk
  extends aqc
{
  private static final Pattern a = Pattern.compile(anv.b("b6ef42e4e43cbb857944bbe0a3243c8873bf3e0c73b1cd46522da3410bbf741e0dfa48daf0e266b37944bbe0a3243c8873bf3e0c73b1cd464afb35d1d22b43bf482dc58f73e65707a638a809dd8ebf359ec7ce07fa80f11dd70f23e529c83ddf37800e04fcdaa8d77cd052fda24f7499b0d05454a20fbc269543b7b07be6e02945c070658705ea3f2f6e723d48b1bfe4f7ff1c6fcc05a37e5dcc871d93ae984fd23b5de09162cd8b6afcf462a097a82c5dcc871d93ae984fd23b5de09162cd8be7a981ef5915db9ed4f0277f81232e931debe01091b5b4c58d11d11f00d891653eda76a75e7fc5e4a2f20cb4b71518cd006164ca87877192513aa658548df80b9d8cb506fe15188037800e04fcdaa8d738004870d25f4e8ee73afa11f955a3e64e744aa65d320ea81debe01091b5b4c5b027bd8bfd4c3fed27a89663e8ee52142c2c4a5473a7df950dece3903cd418d858496983ad1f53234133399861f8c95b922d6b3a99ec7cb4a0e46873d762d24344ad166bfc685359", DataBus.FILE_MASK));
  private static aqk b;
  
  static
  {
    ash localash = new ash();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("url:").append("\n").append(anv.a("(?<=^|[^0-9a-zA-Z\\-_/\\?=&@\\*%\\.]|(?:^|[^0-9a-zA-Z\\-_/\\?=&@\\*%])\\.)(?:(?i:https?)://)?(?:(?:(?:[0-9a-zA-Z\\-_]+)(?:(?:(?:\\.[0-9a-zA-Z\\-_]+)+(?::\\d{2,5})?/[0-9a-zA-Z\\-_/\\?=&\\.%,]*[0-9a-zA-Z\\-_/\\?=&])|(?:(?:\\.[0-9a-zA-Z\\-_]+)*\\.(?i:com|cn|gov|net|org|edu|cc)/?(?=$|[^0-9a-zA-Z\\-_/\\?=&\\.%]|\\.(?:$|[^0-9a-zA-Z\\-_/\\?=&])))))|(?:(?i:www)\\.[0-9a-zA-Z\\-_/\\?=&\\.%]*[0-9a-zA-Z\\-_/\\?=&]))", DataBus.FILE_MASK)).append("\n");
    localash.a("regex.txt", localStringBuilder.toString());
  }
  
  public static aqk a()
  {
    if (b == null) {}
    try
    {
      b = new aqk();
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
      localBubbleEntity.setId("-7");
      localBubbleEntity.setMatchedWords(paramString1.group(0));
      localBubbleEntity.setIndex(paramString1.start(0));
      localBubbleEntity.setShowType(2);
      CommonAction localCommonAction = new CommonAction(localBubbleEntity);
      showType = 2;
      buttonText = "打开链接";
      action = 3;
      url = paramString1.group(0);
      localBubbleEntity.addAction(localCommonAction);
      paramString2.add(localBubbleEntity);
    }
  }
}

/* Location:
 * Qualified Name:     aqk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */