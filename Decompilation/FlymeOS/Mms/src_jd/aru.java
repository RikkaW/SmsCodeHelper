import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aru
  extends aqm
{
  protected static final Pattern c = Pattern.compile(anv.b("92165915dda3bda903f99d9148aad8cfeba4e93cccc34f5c749e2c8e75af6337c17b9d04940a31f3cbcb97080c1b35140f850ff027ce76e5ad1712a8e718c399b94b5c6e8d10e661eece9b5bce9c08116f21694b299c05fcfa39217d607cdb58b800a553e07e68ba9c0fa20f4e356b59e8300702627281a1ec5ffc17d3dcac2fdc7dd65f66ae8aad8dcb7f12b78bd499199b3bf07ea18fff5e242897f31e14289825ddf26bcbaa85cf7ea7a406279616a862a271dbf0f0ab6e13d33a5a7b1e715eceaa194862219915eee00f51357c666abfa792bfb48cdf5f13eb8952fc2cb17281a7bd0afb8d53e2eeecc247036efa795ec049deea1968ffd09100001682d767b731b0272fa569665e319b65f52096d1ed68e0e9e69ecf6c8f224111f8a26e489f989222b1a22f37cb37899b13f4dee6511558e2a9d09e79983aebecb2375657a5f5252beb5c8b5899992412a49703100bdf83fbf84d4e0a263cda5748be8bd692641b3dfa0308bb6b518b44c29710db71a683e488d7779420e82b1cdd691e5d9c8bfdd05fa9d13fb59b85e5df5867175eace080fc36ab9f2d9a5d155c4665c4f62ddd5582d400db2ddef92b4c8fb13fb59b85e5df5867cf4b0871c62c0a89d6b9e5269e8a46d506d66f85270f5183348dcf6ea8ff5cb3a6867664bf50f46d57791cffe00a4d533815747fb1fdbcdd178e0aca6f2359ad2f2b4439b28636eb536cc750b7a08d967e697d3c22e0a777e5e302b93767fb425bf69430bf3460888ec7c3ce204d54f9914122b9f72f924f749e2c8e75af6337325b15c23a8cf6977420a77bec82c6e18bcacc4c448b7c990bbceb8137d1e891bfc6223a6c6f763013e4785e2bb8832aa1cfca30ad7c09d8fa2a39cd8c484769795ec049deea1968ffd09100001682d7d3d93c801a55a9f9e02e0d32710c165f325b15c23a8cf6977420a77bec82c6e18bcacc4c448b7c99f7b430499c1c8804429411283225e898ddad84469de0841a38c7e2ae3eafffd7c96e4e9cfecb8c2372e0db48f6b9e568ec5ffc17d3dcac2fdc7dd65f66ae8aad8dcb7f12b78bd499199b3bf07ea18fff5e242897f31e14289825ddf26bcbaa85cf7ea7a406279616a862a271dbf0f0ab6e13d33a5a7b1e715eceaa194862219915eee00f51357c666abfa792bfb48cdf5f13eb8952fc2cb17281a7bd0afb8d53e2eeecc247036efa795ec049deea1968ffd09100001682d767b731b0272fa569665e319b65f52096d1ed68e0e9e69ecf6c8f224111f8a26e489f989222b1a22f37cb37899b13f4dee6511558e2a9d09e79983aebecb2375657a5f5252beb5c8b5899992412a49703100bdf83fbf84d4e0a263cda5748be8bd692641b3dfa0308bb6b518b44c29710db71a683e488d7779420e82b1cdd691e5d9c8bfdd05fa9d13fb59b85e5df5867175eace080fc36ab9f2d9a5d155c4665c4f62ddd5582d400db2ddef92b4c8fb13fb59b85e5df5867cf4b0871c62c0a89d6b9e5269e8a46d506d66f85270f5183348dcf6ea8ff5cb3a6867664bf50f46d57791cffe00a4d53353a84c8712715ea083f30c69aca8ddfc604aa6579f8a9ba146fab27a3aac2bdb3c12581b2b09bc82c37de0433e847c9b19ae56f3a727f2a1719ee8045834ef5795ec049deea19687ab63c88d0a9dc2a665e319b65f52096d1ed68e0e9e69ecf00da8bb2ee35f56a5ef5ca4805e69d6179c6cd2dabe79293efed9b29fcfe4d8d", DataBus.FILE_MASK));
  
  public String a(String paramString, List<BubbleEntity> paramList)
  {
    Matcher localMatcher = c.matcher(paramString);
    for (;;)
    {
      if (!localMatcher.find()) {
        return paramString;
      }
      if (localMatcher.groupCount() >= 9)
      {
        String str = localMatcher.group(1);
        if (!a(str))
        {
          Object localObject = new aru.a();
          a = localMatcher.group(2);
          b = localMatcher.group(3);
          c = localMatcher.group(4);
          d = localMatcher.group(5);
          e = localMatcher.group(6);
          f = localMatcher.group(7);
          g = localMatcher.group(8);
          h = localMatcher.group(9);
          localObject = ((aru.a)localObject).a(a);
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
    
    public DateReminderAction a(long paramLong)
    {
      boolean bool = true;
      if (aru.b(a)) {
        return null;
      }
      if ((aru.b(c)) && (aru.b(d))) {
        return null;
      }
      int j = aqn.b(paramLong);
      Object localObject = aqn.b(a, paramLong);
      int m = a;
      int i = j;
      if (b) {
        i = j + 1;
      }
      int k;
      if (!aru.b(c))
      {
        localObject = aqn.a(c, paramLong, m);
        k = a;
        j = m;
        if (!b) {
          break label415;
        }
        m += 1;
        j = m;
        if (m <= 12) {
          break label415;
        }
        i += 1;
        j = 1;
      }
      label409:
      label415:
      for (;;)
      {
        long l = aqn.a(i, j, k);
        if (l == -1L)
        {
          return null;
          k = aqn.a(d, i, m, paramLong);
          j = m;
        }
        else
        {
          k = j;
          j = i;
          if (!aru.b(e))
          {
            localObject = aqn.b(e, paramLong);
            m = a;
            k = m;
            j = i;
            if (b)
            {
              j = i + 1;
              k = m;
            }
          }
          int n;
          if (!aru.b(g))
          {
            localObject = aqn.a(g, paramLong, k);
            int i1 = a;
            n = i1;
            i = k;
            m = j;
            if (b)
            {
              k += 1;
              n = i1;
              i = k;
              m = j;
              if (k > 12)
              {
                m = j + 1;
                i = 1;
                n = i1;
              }
            }
            paramLong = aqn.a(m, i, n);
            if (paramLong != -1L) {
              break label409;
            }
            paramLong = l;
          }
          for (;;)
          {
            localObject = new DateReminderAction(null);
            isAllDay = bool;
            startTime = l;
            endTime = (paramLong + 86400000L - 1000L);
            return (DateReminderAction)localObject;
            n = aqn.a(h, j, k, paramLong);
            i = k;
            m = j;
            break;
            bool = false;
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     aru
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */