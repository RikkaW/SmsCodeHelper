import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ary
  extends aqm
{
  protected static final Pattern c = Pattern.compile(anv.b("92165915dda3bda99c0901c152811bf11b691aa2f9c5c28239db7c69f3d418f5a281a2bc3377ce90538fa9ecf6c576e4261bde7939c718ca612bc78214bda7d87cfe525dcd70a26712bc52e1a1ee37a29825ddf26bcbaa85c5ef89e12b0b02a57f60a7dc4b0ac8ebffd09100001682d7d3d93c801a55a9f9e02e0d32710c165f735cc67eae6d8e50e34400dc03e958989cf2d295cf9924bb4d7fa87fb1b27d0258cde88a2a2c6850325b15c23a8cf697555384d7923b03d34403d7328471e01bd406b887424e73107b6dc52c5d863a4e7ace3c6475271a85171eb08ea541c8e65bf3a21fd055ddded2bcb5f9de4787a9ac723f35a9819d851634394a1805dbfbd1fa62982158f45fe7fd9f2b7b996bea82b1c798e3810950400edcd0003ec48139363dd6b09bb62e0fe7f7cbf8d8563f70cedb226ca545dd34ecad41086fe1bd23dd141cf63136e5fd4d57d744689355cd26f20409631c5d0d6b5130f9d7a1e1b977a3e78bac5d5d4c307510ea3c2cbf2c676b255f32c32c420e60bd96634d9a265eecb569b5b0b40a166e367c74c6868c4948ddc55cb3497f45a81cad57662ea02afea667cdb617d49514a10e636c958b545ab6971fcae55e660adc235a2e1e1127db80bb6ad873fd5203e91b4d95454901189445151018f1f0820e686faccf69479b912f7c71a5", DataBus.FILE_MASK));
  
  public String a(String paramString, List<BubbleEntity> paramList)
  {
    Matcher localMatcher = c.matcher(paramString);
    for (;;)
    {
      if (!localMatcher.find()) {
        return paramString;
      }
      if (localMatcher.groupCount() >= 10)
      {
        String str = localMatcher.group(1);
        if (!a(str))
        {
          Object localObject = new ary.a();
          a = localMatcher.group(2);
          b = localMatcher.group(3);
          c = localMatcher.group(4);
          if (b(b)) {
            b = localMatcher.group(5);
          }
          if (b(c)) {
            c = localMatcher.group(6);
          }
          d = localMatcher.group(8);
          e = localMatcher.group(9);
          localObject = ((ary.a)localObject).a(a);
          if (localObject != null)
          {
            BubbleEntity localBubbleEntity = a();
            localBubbleEntity.setMatchedWords(str);
            ((DateReminderAction)localObject).setParent(localBubbleEntity);
            ((DateReminderAction)localObject).setBody(b);
            localBubbleEntity.addAction((ActionBase)localObject);
            paramList.add(localBubbleEntity);
            paramString = paramString.replace(str, "");
          }
        }
      }
    }
  }
  
  static class a
  {
    String a;
    String b;
    String c;
    String d;
    String e;
    
    public DateReminderAction a(long paramLong)
    {
      if (ary.b(a)) {
        return null;
      }
      paramLong = aqn.a(aqn.b(paramLong), aqn.c(paramLong), aqn.d(paramLong));
      int m;
      int i;
      int j;
      int k;
      label113:
      long l1;
      boolean bool;
      if (!ary.b(b))
      {
        m = aqn.a(b, a);
        i = 0;
        if (!ary.b(c)) {
          i = aqn.a(c);
        }
        if (!ary.b(d))
        {
          j = aqn.a(d, a);
          if (!ary.b(e))
          {
            k = aqn.a(e);
            long l2 = m;
            long l3 = i;
            l1 = j * 3600000L + paramLong + k * 60000L;
            paramLong = l2 * 3600000L + paramLong + l3 * 60000L;
            bool = false;
          }
        }
      }
      for (;;)
      {
        DateReminderAction localDateReminderAction = new DateReminderAction(null);
        isAllDay = bool;
        startTime = paramLong;
        endTime = l1;
        return localDateReminderAction;
        k = i;
        break label113;
        j = m;
        break;
        bool = true;
        l1 = paramLong;
      }
    }
  }
}

/* Location:
 * Qualified Name:     ary
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */