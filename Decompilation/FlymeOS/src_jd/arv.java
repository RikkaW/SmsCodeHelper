import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class arv
  extends aqm
{
  protected static final Pattern c = Pattern.compile(anv.b("92165915dda3bda9c4f854b8700936f75176edea99afaa5be57480bb49ef2a269c52d2df5dd8ea506218dfc0f63485bde41647cb04d60c179072e51a40d5848917a64f809b8d6318dc7dd65f66ae8aaddf1e0eda53ae0ffae259acf6cc4108ba0061675b4967176b665e319b65f52096d1ed68e0e9e69ecf6c8f224111f8a26e489f989222b1a22f37cb37899b13f4dee6511558e2a9d09e1bf877fef55cb4e4d3f52fe10946b8db4be8feeef1190a501dc883405f0e8605aceb78fe4e8f256e325b15c23a8cf69732813b3a6e6dd022fe7fc767ad980b9375a76ba2a26f471ee02e0d32710c165f735cc67eae6d8e50e34400dc03e958989cf2d295cf9924bb4d7fa87fb1b27d02137d75ead8d58d51110bb11429ba806135de7b9f990c9d7c9df83edecc64fd6bde2239fac8b2a0ab43b47a148e9d6e63f17583d64fdc37cd146fab27a3aac2bd21707d45766801edcdd04fbd425ed8914e89dab0b0064423a349f005b9aaf943745ead4b640003d53bff6e694437b7c3decd9181503e62550207bf7819f40a59183ee581cd47b5d85bf52733204640c40d1c6de974e9a60f99cbceb596c24a4ebc5bb644c7821a20219049306946b4020fa389eafd21a3eed62ae65e236c4b5ddb9e972eb3b10d94ffb41db547ec1f6fdcd99fb05b11a262cf8a6bf863a5dc280b79a184ac641870db9e972eb3b10d9426f77849476576f6955257e7117fe4652b8216b6106aee12a2f96b22b65148033270bc62c2130f4ded4ed76f9df6f22cf0c7ac5d4b7be8c88efe6b244c2d18f19eacb6c2b4a4ba6486fea26f496b40743b6994d19dcf186e79b1a5d786f641a4b659d0e670930e01dc7dd65f66ae8aad6a38a22882a492ea536cc750b7a08d96da118b3b71e3f43d4c6faf4739cc102d6b867da8ca0ecd4234cdefe7577594d89c0901c152811bf11b691aa2f9c5c28239db7c69f3d418f5a281a2bc3377ce90538fa9ecf6c576e4261bde7939c718ca612bc78214bda7d87cfe525dcd70a267a230e606404e8b600a17306e864cf47b17a64f809b8d63182d028da6fb3b0765749e2c8e75af6337c17b9d04940a31f3cbcb97080c1b35140cd38f4c8b59e770042fc2ab733a3589d5bc8eecc0937212adedde332d5876b2e16376e7204929840f850ff027ce76e5a7746d1f0ec9d2d321707d45766801edcdd04fbd425ed8914e89dab0b0064423a349f005b9aaf943745ead4b640003d53bff6e694437b7c3decd9181503e62550207bf7819f40a59183ee581cd47b5d85bf52733204640c40d1c6de974e9a60f99cbceb596c24a4ebc5bb644c7821a20219049306946b4020fa389eafd21a3eed62ae65e236c4b5ddb9e972eb3b10d949cdaa8db1e9347a1aa1cc2ac47b1245f3f9f2e6c759b9c07ab24e24c61633a72ad54d53dac899657a01dcc27feab95618e2b60ebba9c7ab063335b25f01195b76209e86fc40fa30e5e660adc235a2e1e132f33a1825ebb5842c8663e0f3f2cb397adef790aeb77ec49011894451510184c307510ea3c2cbf2c676b255f32c32c420e60bd96634d9a265eecb569b5b0b40a166e367c74c6868c4948ddc55cb34927faaa3f2867ee8c303c95d96e53ff39", DataBus.FILE_MASK));
  
  public String a(String paramString, List<BubbleEntity> paramList)
  {
    Matcher localMatcher = c.matcher(paramString);
    for (;;)
    {
      if (!localMatcher.find()) {
        return paramString;
      }
      if (localMatcher.groupCount() >= 15)
      {
        String str = localMatcher.group(1);
        if (!a(str))
        {
          Object localObject = new arv.a();
          a = localMatcher.group(2);
          b = localMatcher.group(3);
          c = localMatcher.group(4);
          d = localMatcher.group(5);
          e = localMatcher.group(6);
          f = localMatcher.group(7);
          g = localMatcher.group(8);
          h = localMatcher.group(9);
          if (b(g)) {
            g = localMatcher.group(10);
          }
          if (b(h)) {
            h = localMatcher.group(11);
          }
          i = localMatcher.group(13);
          j = localMatcher.group(14);
          localObject = ((arv.a)localObject).a(a);
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
    String f;
    String g;
    String h;
    String i;
    String j;
    
    public DateReminderAction a(long paramLong)
    {
      if (arv.b(a)) {
        return null;
      }
      if ((arv.b(d)) && (arv.b(e))) {
        return null;
      }
      int m = aqn.a(a, paramLong);
      Object localObject = aqn.b(b, paramLong);
      int i1 = a;
      int k = m;
      if (b) {
        k = m + 1;
      }
      int n;
      if (!arv.b(d))
      {
        localObject = aqn.a(d, paramLong, i1);
        n = a;
        m = i1;
        if (!b) {
          break label382;
        }
        i1 += 1;
        m = i1;
        if (i1 <= 12) {
          break label382;
        }
        i1 = k + 1;
        m = 1;
        k = n;
        n = i1;
      }
      for (;;)
      {
        paramLong = aqn.a(n, m, k);
        if (paramLong == -1L)
        {
          return null;
          int i2 = aqn.a(e, k, i1, paramLong);
          m = i1;
          n = k;
          k = i2;
        }
        else
        {
          label280:
          long l1;
          if (!arv.b(g))
          {
            i1 = aqn.a(g, f);
            k = 0;
            if (!arv.b(h)) {
              k = aqn.a(h);
            }
            if (!arv.b(i))
            {
              m = aqn.a(i, f);
              if (!arv.b(j))
              {
                n = aqn.a(j);
                long l2 = i1;
                long l3 = k;
                l1 = m * 3600000L + paramLong + n * 60000L;
                paramLong = l2 * 3600000L + paramLong + l3 * 60000L;
              }
            }
          }
          for (boolean bool = false;; bool = true)
          {
            localObject = new DateReminderAction(null);
            isAllDay = bool;
            startTime = paramLong;
            endTime = l1;
            return (DateReminderAction)localObject;
            n = k;
            break label280;
            m = i1;
            break;
            l1 = paramLong;
          }
          label382:
          i1 = k;
          k = n;
          n = i1;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     arv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */