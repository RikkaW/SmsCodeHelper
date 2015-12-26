import android.accounts.Account;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class aey
{
  private static final Map<String, Integer> a = new HashMap();
  private static final List<String> s = Collections.unmodifiableList(new ArrayList(0));
  private final aey.i b = new aey.i();
  private List<aey.m> c;
  private List<aey.d> d;
  private List<aey.o> e;
  private List<aey.l> f;
  private List<aey.h> g;
  private List<aey.n> h;
  private List<aey.r> i;
  private List<aey.p> j;
  private List<aey.j> k;
  private List<aey.k> l;
  private List<aey.a> m;
  private aey.c n;
  private aey.b o;
  private final int p;
  private final Account q;
  private List<aey> r;
  
  static
  {
    a.put("X-AIM", Integer.valueOf(0));
    a.put("X-MSN", Integer.valueOf(1));
    a.put("X-YAHOO", Integer.valueOf(2));
    a.put("X-ICQ", Integer.valueOf(6));
    a.put("X-JABBER", Integer.valueOf(7));
    a.put("X-SKYPE-USERNAME", Integer.valueOf(3));
    a.put("X-GOOGLE-TALK", Integer.valueOf(5));
    a.put("X-GOOGLE TALK", Integer.valueOf(5));
  }
  
  public aey()
  {
    this(-1073741824);
  }
  
  public aey(int paramInt)
  {
    this(paramInt, null);
  }
  
  public aey(int paramInt, Account paramAccount)
  {
    p = paramInt;
    q = paramAccount;
  }
  
  private String a(Map<String, Collection<String>> paramMap)
  {
    paramMap = (Collection)paramMap.get("SORT-AS");
    if ((paramMap != null) && (paramMap.size() != 0))
    {
      if (paramMap.size() > 1) {
        Log.w("vCard", "Incorrect multiple SORT_AS parameters detected: " + Arrays.toString(paramMap.toArray()));
      }
      Object localObject = afl.a((String)paramMap.iterator().next(), p);
      paramMap = new StringBuilder();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramMap.append((String)((Iterator)localObject).next());
      }
      return paramMap.toString();
    }
    return null;
  }
  
  private void a(int paramInt1, String paramString1, String paramString2, int paramInt2, boolean paramBoolean)
  {
    if (g == null) {
      g = new ArrayList();
    }
    g.add(new aey.h(paramInt1, paramString1, paramString2, paramInt2, paramBoolean));
  }
  
  private void a(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    int i3 = 0;
    if (c == null) {
      c = new ArrayList();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramString1 = paramString1.trim();
    if ((paramInt == 6) || (aex.f(p))) {}
    for (;;)
    {
      paramString1 = new aey.m(paramString1, paramInt, paramString2, paramBoolean);
      c.add(paramString1);
      return;
      int i4 = paramString1.length();
      int i2 = 0;
      int i1;
      if (i2 < i4)
      {
        char c1 = paramString1.charAt(i2);
        if ((c1 == 'p') || (c1 == 'P'))
        {
          localStringBuilder.append(',');
          i1 = 1;
        }
        for (;;)
        {
          i2 += 1;
          i3 = i1;
          break;
          if ((c1 == 'w') || (c1 == 'W'))
          {
            localStringBuilder.append(';');
            i1 = 1;
          }
          else if (('0' > c1) || (c1 > '9'))
          {
            i1 = i3;
            if (i2 == 0)
            {
              i1 = i3;
              if (c1 != '+') {}
            }
          }
          else
          {
            localStringBuilder.append(c1);
            i1 = i3;
          }
        }
      }
      if (i3 == 0)
      {
        i1 = afl.a(p);
        paramString1 = afl.b.a(localStringBuilder.toString(), i1);
      }
      else
      {
        paramString1 = localStringBuilder.toString();
      }
    }
  }
  
  private void a(int paramInt, List<String> paramList, String paramString, boolean paramBoolean)
  {
    if (e == null) {
      e = new ArrayList(0);
    }
    e.add(aey.o.a(paramList, paramInt, paramString, paramBoolean, p));
  }
  
  private void a(int paramInt, List<String> paramList, Map<String, Collection<String>> paramMap, boolean paramBoolean)
  {
    String str = a(paramMap);
    paramMap = paramList;
    if (paramList == null) {
      paramMap = s;
    }
    int i2 = paramMap.size();
    int i1;
    switch (i2)
    {
    default: 
      paramList = (String)paramMap.get(0);
      localObject = new StringBuilder();
      i1 = 1;
    case 0: 
      while (i1 < i2)
      {
        if (i1 > 1) {
          ((StringBuilder)localObject).append(' ');
        }
        ((StringBuilder)localObject).append((String)paramMap.get(i1));
        i1 += 1;
        continue;
        paramMap = null;
        paramList = "";
      }
    }
    while (f == null)
    {
      a(paramList, paramMap, null, str, paramInt, paramBoolean);
      return;
      paramList = (String)paramMap.get(0);
      paramMap = null;
      continue;
      paramMap = ((StringBuilder)localObject).toString();
    }
    Object localObject = f.iterator();
    while (((Iterator)localObject).hasNext())
    {
      aey.l locall = (aey.l)((Iterator)localObject).next();
      if ((aey.l.a(locall) == null) && (aey.l.b(locall) == null))
      {
        aey.l.a(locall, paramList);
        aey.l.b(locall, paramMap);
        aey.l.a(locall, paramBoolean);
        return;
      }
    }
    a(paramList, paramMap, null, str, paramInt, paramBoolean);
  }
  
  private void a(String paramString)
  {
    if (k == null) {
      k = new ArrayList();
    }
    k.add(new aey.j(paramString));
  }
  
  private void a(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    if (j == null) {
      j = new ArrayList();
    }
    j.add(new aey.p(paramString1, paramInt, paramString2, paramBoolean));
  }
  
  private void a(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, boolean paramBoolean)
  {
    if (f == null) {
      f = new ArrayList();
    }
    f.add(new aey.l(paramString1, paramString2, paramString3, paramString4, paramInt, paramBoolean));
  }
  
  private void a(String paramString, Collection<String> paramCollection)
  {
    if (TextUtils.isEmpty(paramString)) {}
    String str1;
    do
    {
      return;
      str1 = paramString;
      if (!paramString.startsWith("sip:")) {
        break;
      }
      str1 = paramString.substring(4);
    } while (str1.length() == 0);
    int i1 = -1;
    Iterator localIterator = null;
    paramString = null;
    boolean bool;
    if (paramCollection != null)
    {
      localIterator = paramCollection.iterator();
      bool = false;
      if (localIterator.hasNext())
      {
        paramCollection = (String)localIterator.next();
        String str2 = paramCollection.toUpperCase();
        if (str2.equals("PREF")) {
          bool = true;
        }
        for (;;)
        {
          break;
          if (str2.equals("HOME"))
          {
            i1 = 1;
          }
          else if (str2.equals("WORK"))
          {
            i1 = 2;
          }
          else
          {
            if (i1 >= 0) {
              break label184;
            }
            paramString = paramCollection;
            if (str2.startsWith("X-")) {
              paramString = paramCollection.substring(2);
            }
            i1 = 0;
          }
        }
      }
    }
    for (;;)
    {
      int i2 = i1;
      if (i1 < 0) {
        i2 = 3;
      }
      a(str1, i2, paramString, bool);
      return;
      label184:
      break;
      bool = false;
      i1 = -1;
      paramString = localIterator;
    }
  }
  
  private void a(String paramString, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (h == null) {
      h = new ArrayList(1);
    }
    paramString = new aey.n(paramString, paramArrayOfByte, paramBoolean);
    h.add(paramString);
  }
  
  private void a(List<String> paramList)
  {
    if ((!TextUtils.isEmpty(aey.i.a(b))) || (!TextUtils.isEmpty(aey.i.b(b))) || (!TextUtils.isEmpty(aey.i.c(b)))) {}
    int i1;
    do
    {
      do
      {
        return;
      } while (paramList == null);
      i1 = paramList.size();
    } while (i1 < 1);
    if (i1 > 3) {
      i1 = 3;
    }
    for (;;)
    {
      if (((String)paramList.get(0)).length() > 0)
      {
        i2 = 1;
        if (i2 >= i1) {
          break label310;
        }
        if (((String)paramList.get(i2)).length() <= 0) {}
      }
      label310:
      for (int i2 = 0;; i2 = 1)
      {
        if (i2 != 0)
        {
          String[] arrayOfString = ((String)paramList.get(0)).split(" ");
          i1 = arrayOfString.length;
          if (i1 == 3)
          {
            aey.i.c(b, arrayOfString[0]);
            aey.i.a(b, arrayOfString[1]);
            aey.i.b(b, arrayOfString[2]);
            return;
            i2 += 1;
            break;
          }
          if (i1 == 2)
          {
            aey.i.c(b, arrayOfString[0]);
            aey.i.b(b, arrayOfString[1]);
            return;
          }
          aey.i.b(b, (String)paramList.get(0));
          return;
        }
        switch (i1)
        {
        }
        for (;;)
        {
          aey.i.c(b, (String)paramList.get(0));
          return;
          aey.i.a(b, (String)paramList.get(2));
          aey.i.b(b, (String)paramList.get(1));
        }
      }
    }
  }
  
  private void a(List<? extends aey.e> paramList, aey.f paramf)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      paramf.a(((aey.e)paramList.get(0)).a());
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        paramf.a((aey.e)paramList.next());
      }
      paramf.c();
    }
  }
  
  private void a(List<String> paramList, Map<String, Collection<String>> paramMap)
  {
    int i1 = 5;
    b(paramMap);
    int i2;
    if (paramList != null)
    {
      i2 = paramList.size();
      if (i2 >= 1) {}
    }
    else
    {
      return;
    }
    if (i2 > 5) {}
    for (;;)
    {
      switch (i1)
      {
      }
      for (;;)
      {
        aey.i.h(b, (String)paramList.get(0));
        return;
        aey.i.d(b, (String)paramList.get(4));
        aey.i.e(b, (String)paramList.get(3));
        aey.i.f(b, (String)paramList.get(2));
        aey.i.g(b, (String)paramList.get(1));
      }
      i1 = i2;
    }
  }
  
  private void b(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (d == null) {
      d = new ArrayList();
    }
    d.add(new aey.d(paramString1, paramInt, paramString2, paramBoolean));
  }
  
  private void b(String paramString)
  {
    if (f == null)
    {
      a(null, null, paramString, null, 1, false);
      return;
    }
    Iterator localIterator = f.iterator();
    while (localIterator.hasNext())
    {
      aey.l locall = (aey.l)localIterator.next();
      if (aey.l.c(locall) == null)
      {
        aey.l.c(locall, paramString);
        return;
      }
    }
    a(null, null, paramString, null, 1, false);
  }
  
  private void b(List<String> paramList)
  {
    if (m == null) {
      m = new ArrayList();
    }
    m.add(aey.a.a(paramList));
  }
  
  private void b(Map<String, Collection<String>> paramMap)
  {
    if ((aex.b(p)) && ((!TextUtils.isEmpty(aey.i.a(b))) || (!TextUtils.isEmpty(aey.i.b(b))) || (!TextUtils.isEmpty(aey.i.c(b))))) {}
    do
    {
      return;
      paramMap = (Collection)paramMap.get("SORT-AS");
    } while ((paramMap == null) || (paramMap.size() == 0));
    if (paramMap.size() > 1) {
      Log.w("vCard", "Incorrect multiple SORT_AS parameters detected: " + Arrays.toString(paramMap.toArray()));
    }
    paramMap = afl.a((String)paramMap.iterator().next(), p);
    int i2 = paramMap.size();
    int i1 = i2;
    if (i2 > 3) {
      i1 = 3;
    }
    switch (i1)
    {
    }
    for (;;)
    {
      aey.i.c(b, (String)paramMap.get(0));
      return;
      aey.i.a(b, (String)paramMap.get(2));
      aey.i.b(b, (String)paramMap.get(1));
    }
  }
  
  private String c(List<String> paramList)
  {
    int i1 = paramList.size();
    if (i1 > 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        localStringBuilder.append((String)paramList.next());
        if (i1 - 1 < 0) {
          localStringBuilder.append(";");
        }
      }
      return localStringBuilder.toString();
    }
    if (i1 == 1) {
      return (String)paramList.get(0);
    }
    return "";
  }
  
  private void c(String paramString)
  {
    if (l == null) {
      l = new ArrayList(1);
    }
    l.add(new aey.k(paramString));
  }
  
  private String l()
  {
    Object localObject2 = null;
    Object localObject1;
    if (!TextUtils.isEmpty(aey.i.d(b))) {
      localObject1 = aey.i.d(b);
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "";
      }
      return (String)localObject2;
      if (!b.b())
      {
        localObject1 = afl.a(p, aey.i.e(b), aey.i.f(b), aey.i.g(b), aey.i.h(b), aey.i.i(b));
      }
      else if (!b.c())
      {
        localObject1 = afl.b(p, aey.i.a(b), aey.i.b(b), aey.i.c(b));
      }
      else if ((d != null) && (d.size() > 0))
      {
        localObject1 = aey.d.a((aey.d)d.get(0));
      }
      else if ((c != null) && (c.size() > 0))
      {
        localObject1 = aey.m.a((aey.m)c.get(0));
      }
      else if ((e != null) && (e.size() > 0))
      {
        localObject1 = ((aey.o)e.get(0)).a(p);
      }
      else
      {
        localObject1 = localObject2;
        if (f != null)
        {
          localObject1 = localObject2;
          if (f.size() > 0) {
            localObject1 = ((aey.l)f.get(0)).b();
          }
        }
      }
    }
  }
  
  public void a()
  {
    b.a = l();
  }
  
  public final void a(aey.f paramf)
  {
    paramf.a();
    paramf.a(b.a());
    paramf.a(b);
    paramf.c();
    a(c, paramf);
    a(d, paramf);
    a(e, paramf);
    a(f, paramf);
    a(g, paramf);
    a(h, paramf);
    a(i, paramf);
    a(j, paramf);
    a(k, paramf);
    a(l, paramf);
    a(m, paramf);
    if (n != null)
    {
      paramf.a(n.a());
      paramf.a(n);
      paramf.c();
    }
    if (o != null)
    {
      paramf.a(o.a());
      paramf.a(o);
      paramf.c();
    }
    paramf.b();
  }
  
  public void a(aey paramaey)
  {
    if (r == null) {
      r = new ArrayList();
    }
    r.add(paramaey);
  }
  
  public void a(afj paramafj)
  {
    int i1 = -1;
    Object localObject5 = null;
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject2 = null;
    String str1 = null;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool1 = false;
    boolean bool5 = true;
    boolean bool6 = true;
    int i4 = 1;
    int i3 = 1;
    String str2 = paramafj.a();
    Map localMap = paramafj.b();
    List localList = paramafj.d();
    byte[] arrayOfByte = paramafj.e();
    if (((localList == null) || (localList.size() == 0)) && (arrayOfByte == null)) {}
    label141:
    label425:
    label650:
    label948:
    label1011:
    label1218:
    label1742:
    label1750:
    label1753:
    label1767:
    label1783:
    for (;;)
    {
      return;
      if (localList != null) {}
      for (Object localObject1 = c(localList).trim(); !str2.equals("VERSION"); localObject1 = null)
      {
        if (!str2.equals("FN")) {
          break label141;
        }
        aey.i.i(b, (String)localObject1);
        return;
      }
      continue;
      if (str2.equals("NAME"))
      {
        if (TextUtils.isEmpty(aey.i.d(b))) {
          aey.i.i(b, (String)localObject1);
        }
      }
      else
      {
        if (str2.equals("N"))
        {
          a(localList, localMap);
          return;
        }
        if (str2.equals("SORT-STRING"))
        {
          aey.i.j(b, (String)localObject1);
          return;
        }
        if ((str2.equals("NICKNAME")) || (str2.equals("X-NICKNAME")))
        {
          a((String)localObject1);
          return;
        }
        if (str2.equals("SOUND"))
        {
          paramafj = (Collection)localMap.get("TYPE");
          if ((paramafj != null) && (paramafj.contains("X-IRMC-N"))) {
            a(afl.a((String)localObject1, p));
          }
        }
        else
        {
          if (str2.equals("ADR"))
          {
            paramafj = localList.iterator();
            do
            {
              if (!paramafj.hasNext()) {
                break;
              }
            } while (TextUtils.isEmpty((String)paramafj.next()));
          }
          for (int i2 = 0;; i2 = 1)
          {
            if (i2 != 0) {
              break label1783;
            }
            paramafj = (Collection)localMap.get("TYPE");
            if (paramafj != null)
            {
              localObject2 = paramafj.iterator();
              bool1 = false;
              paramafj = null;
              if (((Iterator)localObject2).hasNext())
              {
                localObject1 = (String)((Iterator)localObject2).next();
                localObject3 = ((String)localObject1).toUpperCase();
                if (((String)localObject3).equals("PREF")) {
                  bool1 = true;
                }
                for (;;)
                {
                  break;
                  if (((String)localObject3).equals("HOME"))
                  {
                    i1 = 1;
                    paramafj = null;
                  }
                  else if ((((String)localObject3).equals("WORK")) || (((String)localObject3).equalsIgnoreCase("COMPANY")))
                  {
                    i1 = 2;
                    paramafj = null;
                  }
                  else
                  {
                    if ((((String)localObject3).equals("PARCEL")) || (((String)localObject3).equals("DOM"))) {
                      break label1767;
                    }
                    if (!((String)localObject3).equals("INTL"))
                    {
                      if (i1 >= 0) {
                        break label1767;
                      }
                      if (((String)localObject3).startsWith("X-"))
                      {
                        paramafj = ((String)localObject1).substring(2);
                        i1 = 0;
                      }
                      else
                      {
                        i1 = 0;
                        paramafj = (afj)localObject1;
                      }
                    }
                  }
                }
              }
            }
            for (;;)
            {
              if (i1 < 0) {
                i1 = i3;
              }
              for (;;)
              {
                a(i1, localList, paramafj, bool1);
                return;
                if (str2.equals("EMAIL"))
                {
                  paramafj = (Collection)localMap.get("TYPE");
                  if (paramafj == null) {
                    break label1753;
                  }
                  localObject3 = paramafj.iterator();
                  bool1 = false;
                  paramafj = str1;
                  if (((Iterator)localObject3).hasNext())
                  {
                    localObject2 = (String)((Iterator)localObject3).next();
                    str1 = ((String)localObject2).toUpperCase();
                    if (str1.equals("PREF")) {
                      bool1 = true;
                    }
                    for (;;)
                    {
                      break;
                      if (str1.equals("HOME"))
                      {
                        i1 = 1;
                      }
                      else if (str1.equals("WORK"))
                      {
                        i1 = 2;
                      }
                      else if (str1.equals("CELL"))
                      {
                        i1 = 4;
                      }
                      else
                      {
                        if (i1 >= 0) {
                          break label1750;
                        }
                        paramafj = (afj)localObject2;
                        if (str1.startsWith("X-")) {
                          paramafj = ((String)localObject2).substring(2);
                        }
                        i1 = 0;
                      }
                    }
                  }
                }
                for (;;)
                {
                  i2 = i1;
                  if (i1 < 0) {
                    i2 = 3;
                  }
                  b(i2, (String)localObject1, paramafj, bool1);
                  return;
                  if (str2.equals("ORG"))
                  {
                    paramafj = (Collection)localMap.get("TYPE");
                    if (paramafj != null)
                    {
                      paramafj = paramafj.iterator();
                      for (;;)
                      {
                        bool2 = bool1;
                        if (!paramafj.hasNext()) {
                          break;
                        }
                        if (((String)paramafj.next()).equals("PREF")) {
                          bool1 = true;
                        }
                      }
                    }
                    a(1, localList, localMap, bool2);
                    return;
                  }
                  if (str2.equals("TITLE"))
                  {
                    b((String)localObject1);
                    return;
                  }
                  if (str2.equals("ROLE")) {
                    break;
                  }
                  if ((str2.equals("PHOTO")) || (str2.equals("LOGO")))
                  {
                    paramafj = (Collection)localMap.get("VALUE");
                    if ((paramafj != null) && (paramafj.contains("URL"))) {
                      break;
                    }
                    paramafj = (Collection)localMap.get("TYPE");
                    if (paramafj != null)
                    {
                      localObject2 = paramafj.iterator();
                      bool1 = false;
                      paramafj = (afj)localObject3;
                      bool2 = bool1;
                      localObject1 = paramafj;
                      if (!((Iterator)localObject2).hasNext()) {
                        break label1011;
                      }
                      localObject1 = (String)((Iterator)localObject2).next();
                      if ("PREF".equals(localObject1)) {
                        bool1 = true;
                      }
                    }
                  }
                  for (;;)
                  {
                    break label948;
                    if (paramafj == null)
                    {
                      paramafj = (afj)localObject1;
                      continue;
                      bool2 = false;
                      localObject1 = localObject4;
                      a((String)localObject1, arrayOfByte, bool2);
                      return;
                      if (str2.equals("TEL"))
                      {
                        if (aex.c(p)) {
                          if (((String)localObject1).startsWith("sip:"))
                          {
                            i1 = 1;
                            paramafj = null;
                          }
                        }
                        while (i1 != 0)
                        {
                          a((String)localObject1, (Collection)localMap.get("TYPE"));
                          return;
                          if (((String)localObject1).startsWith("tel:"))
                          {
                            paramafj = ((String)localObject1).substring(4);
                            i1 = 0;
                          }
                          else
                          {
                            i1 = 0;
                            paramafj = (afj)localObject1;
                            continue;
                            i1 = 0;
                            paramafj = (afj)localObject1;
                          }
                        }
                        if (((String)localObject1).length() == 0) {
                          break;
                        }
                        localObject3 = (Collection)localMap.get("TYPE");
                        localObject1 = afl.a((Collection)localObject3, paramafj);
                        if ((localObject1 instanceof Integer))
                        {
                          i1 = ((Integer)localObject1).intValue();
                          localObject1 = localObject2;
                          if ((localObject3 == null) || (!((Collection)localObject3).contains("PREF"))) {
                            break label1218;
                          }
                        }
                        for (bool1 = bool5;; bool1 = false)
                        {
                          a(i1, paramafj, (String)localObject1, bool1);
                          return;
                          localObject1 = localObject1.toString();
                          i1 = 0;
                          break;
                        }
                      }
                      if (str2.equals("X-SKYPE-PSTNNUMBER"))
                      {
                        paramafj = (Collection)localMap.get("TYPE");
                        if ((paramafj != null) && (paramafj.contains("PREF"))) {}
                        for (bool1 = bool6;; bool1 = false)
                        {
                          a(7, (String)localObject1, null, bool1);
                          return;
                        }
                      }
                      if (a.containsKey(str2))
                      {
                        i3 = ((Integer)a.get(str2)).intValue();
                        paramafj = (Collection)localMap.get("TYPE");
                        i2 = i1;
                        bool2 = bool4;
                        if (paramafj != null)
                        {
                          paramafj = paramafj.iterator();
                          bool1 = bool3;
                          for (;;)
                          {
                            i2 = i1;
                            bool2 = bool1;
                            if (!paramafj.hasNext()) {
                              break;
                            }
                            localObject2 = (String)paramafj.next();
                            if (((String)localObject2).equals("PREF")) {
                              bool1 = true;
                            } else if (i1 < 0) {
                              if (((String)localObject2).equalsIgnoreCase("HOME")) {
                                i1 = 1;
                              } else if (((String)localObject2).equalsIgnoreCase("WORK")) {
                                i1 = 2;
                              }
                            }
                          }
                        }
                        if (i2 >= 0) {
                          break label1742;
                        }
                      }
                      for (i1 = i4;; i1 = i2)
                      {
                        a(i3, null, (String)localObject1, i1, bool2);
                        return;
                        if (str2.equals("NOTE"))
                        {
                          c((String)localObject1);
                          return;
                        }
                        if (str2.equals("URL"))
                        {
                          if (i == null) {
                            i = new ArrayList(1);
                          }
                          i.add(new aey.r((String)localObject1));
                          return;
                        }
                        if (str2.equals("BDAY"))
                        {
                          n = new aey.c((String)localObject1);
                          return;
                        }
                        if (str2.equals("ANNIVERSARY"))
                        {
                          o = new aey.b((String)localObject1);
                          return;
                        }
                        if (str2.equals("X-PHONETIC-FIRST-NAME"))
                        {
                          aey.i.b(b, (String)localObject1);
                          return;
                        }
                        if (str2.equals("X-PHONETIC-MIDDLE-NAME"))
                        {
                          aey.i.a(b, (String)localObject1);
                          return;
                        }
                        if (str2.equals("X-PHONETIC-LAST-NAME"))
                        {
                          aey.i.c(b, (String)localObject1);
                          return;
                        }
                        if (str2.equals("IMPP"))
                        {
                          if (!((String)localObject1).startsWith("sip:")) {
                            break;
                          }
                          a((String)localObject1, (Collection)localMap.get("TYPE"));
                          return;
                        }
                        if (str2.equals("X-SIP"))
                        {
                          if (TextUtils.isEmpty((CharSequence)localObject1)) {
                            break;
                          }
                          a((String)localObject1, (Collection)localMap.get("TYPE"));
                          return;
                        }
                        if (!str2.equals("X-ANDROID-CUSTOM")) {
                          break;
                        }
                        b(afl.a((String)localObject1, p));
                        return;
                      }
                    }
                  }
                  break label650;
                  bool1 = false;
                  i1 = -1;
                  paramafj = (afj)localObject5;
                }
              }
              break label425;
              bool1 = false;
              paramafj = null;
              i1 = -1;
            }
          }
        }
      }
    }
  }
  
  public final List<aey.j> b()
  {
    return k;
  }
  
  public final String c()
  {
    if (n != null) {
      return aey.c.a(n);
    }
    return null;
  }
  
  public final List<aey.k> d()
  {
    return l;
  }
  
  public final List<aey.m> e()
  {
    return c;
  }
  
  public final List<aey.d> f()
  {
    return d;
  }
  
  public final List<aey.o> g()
  {
    return e;
  }
  
  public final List<aey.l> h()
  {
    return f;
  }
  
  public final List<aey.h> i()
  {
    return g;
  }
  
  public final List<aey.r> j()
  {
    return i;
  }
  
  public String k()
  {
    if (b.a == null) {
      b.a = l();
    }
    return b.a;
  }
  
  public String toString()
  {
    aey.q localq = new aey.q(null);
    a(localq);
    return localq.toString();
  }
  
  public static class a
    implements aey.e
  {
    private final String a;
    private final List<String> b;
    
    public a(String paramString, List<String> paramList)
    {
      a = paramString;
      b = paramList;
    }
    
    public static a a(List<String> paramList)
    {
      String str = null;
      if (paramList == null) {}
      for (paramList = null;; paramList = null)
      {
        return new a(str, paramList);
        if (paramList.size() >= 2) {
          break;
        }
        str = (String)paramList.get(0);
      }
      if (paramList.size() < 16) {}
      for (int i = paramList.size();; i = 16)
      {
        str = (String)paramList.get(0);
        paramList = paramList.subList(1, i);
        break;
      }
    }
    
    public aey.g a()
    {
      return aey.g.n;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (this == paramObject) {
        bool1 = true;
      }
      int j;
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (!(paramObject instanceof a));
          paramObject = (a)paramObject;
          bool1 = bool2;
        } while (!TextUtils.equals(a, a));
        if (b == null)
        {
          if (b == null) {}
          for (bool1 = true;; bool1 = false) {
            return bool1;
          }
        }
        j = b.size();
        bool1 = bool2;
      } while (j != b.size());
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label151;
        }
        bool1 = bool2;
        if (!TextUtils.equals((CharSequence)b.get(i), (CharSequence)b.get(i))) {
          break;
        }
        i += 1;
      }
      label151:
      return true;
    }
    
    public int hashCode()
    {
      int i;
      label32:
      String str;
      if (a != null)
      {
        i = a.hashCode();
        if (b == null) {
          break label85;
        }
        Iterator localIterator = b.iterator();
        j = i;
        if (!localIterator.hasNext()) {
          return j;
        }
        str = (String)localIterator.next();
        if (str == null) {
          break label80;
        }
      }
      label80:
      for (int j = str.hashCode();; j = 0)
      {
        i = j + i * 31;
        break label32;
        i = 0;
        break;
      }
      label85:
      j = i;
      return j;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("android-custom: " + a + ", data: ");
      if (b == null) {}
      for (String str = "null";; str = Arrays.toString(b.toArray()))
      {
        localStringBuilder.append(str);
        return localStringBuilder.toString();
      }
    }
  }
  
  public static class b
    implements aey.e
  {
    private final String a;
    
    public b(String paramString)
    {
      a = paramString;
    }
    
    public aey.g a()
    {
      return aey.g.m;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof b)) {
        return false;
      }
      paramObject = (b)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "anniversary: " + a;
    }
  }
  
  public static class c
    implements aey.e
  {
    private final String a;
    
    public c(String paramString)
    {
      a = paramString;
    }
    
    public aey.g a()
    {
      return aey.g.l;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof c)) {
        return false;
      }
      paramObject = (c)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "birthday: " + a;
    }
  }
  
  public static class d
    implements aey.e
  {
    private final String a;
    private final int b;
    private final String c;
    private final boolean d;
    
    public d(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
    {
      b = paramInt;
      a = paramString1;
      c = paramString2;
      d = paramBoolean;
    }
    
    public final aey.g a()
    {
      return aey.g.c;
    }
    
    public String b()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof d)) {
          return false;
        }
        paramObject = (d)paramObject;
      } while ((b == b) && (TextUtils.equals(a, a)) && (TextUtils.equals(c, c)) && (d == d));
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int m = b;
      int i;
      if (a != null)
      {
        i = a.hashCode();
        if (c != null) {
          j = c.hashCode();
        }
        if (!d) {
          break label72;
        }
      }
      label72:
      for (int k = 1231;; k = 1237)
      {
        return k + ((i + m * 31) * 31 + j) * 31;
        i = 0;
        break;
      }
    }
    
    public String toString()
    {
      return String.format("type: %d, data: %s, label: %s, isPrimary: %s", new Object[] { Integer.valueOf(b), a, c, Boolean.valueOf(d) });
    }
  }
  
  public static abstract interface e
  {
    public abstract aey.g a();
  }
  
  public static abstract interface f
  {
    public abstract void a();
    
    public abstract void a(aey.g paramg);
    
    public abstract boolean a(aey.e parame);
    
    public abstract void b();
    
    public abstract void c();
  }
  
  public static enum g
  {
    private g() {}
  }
  
  public static class h
    implements aey.e
  {
    private final String a;
    private final int b;
    private final String c;
    private final int d;
    private final boolean e;
    
    public h(int paramInt1, String paramString1, String paramString2, int paramInt2, boolean paramBoolean)
    {
      b = paramInt1;
      c = paramString1;
      d = paramInt2;
      a = paramString2;
      e = paramBoolean;
    }
    
    public final aey.g a()
    {
      return aey.g.f;
    }
    
    public String b()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof h)) {
          return false;
        }
        paramObject = (h)paramObject;
      } while ((d == d) && (b == b) && (TextUtils.equals(c, c)) && (TextUtils.equals(a, a)) && (e == e));
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int m = d;
      int n = b;
      int i;
      if (c != null)
      {
        i = c.hashCode();
        if (a != null) {
          j = a.hashCode();
        }
        if (!e) {
          break label84;
        }
      }
      label84:
      for (int k = 1231;; k = 1237)
      {
        return k + ((i + (m * 31 + n) * 31) * 31 + j) * 31;
        i = 0;
        break;
      }
    }
    
    public String toString()
    {
      return String.format("type: %d, protocol: %d, custom_protcol: %s, data: %s, isPrimary: %s", new Object[] { Integer.valueOf(d), Integer.valueOf(b), c, a, Boolean.valueOf(e) });
    }
  }
  
  public static class i
    implements aey.e
  {
    public String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    
    public final aey.g a()
    {
      return aey.g.a;
    }
    
    public boolean b()
    {
      return (TextUtils.isEmpty(b)) && (TextUtils.isEmpty(c)) && (TextUtils.isEmpty(d)) && (TextUtils.isEmpty(e)) && (TextUtils.isEmpty(f));
    }
    
    public boolean c()
    {
      return (TextUtils.isEmpty(h)) && (TextUtils.isEmpty(i)) && (TextUtils.isEmpty(j));
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof i)) {
          return false;
        }
        paramObject = (i)paramObject;
      } while ((TextUtils.equals(b, b)) && (TextUtils.equals(d, d)) && (TextUtils.equals(c, c)) && (TextUtils.equals(e, e)) && (TextUtils.equals(f, f)) && (TextUtils.equals(g, g)) && (TextUtils.equals(h, h)) && (TextUtils.equals(j, j)) && (TextUtils.equals(i, i)) && (TextUtils.equals(k, k)));
      return false;
    }
    
    public int hashCode()
    {
      String[] arrayOfString = new String[10];
      arrayOfString[0] = b;
      arrayOfString[1] = d;
      arrayOfString[2] = c;
      arrayOfString[3] = e;
      arrayOfString[4] = f;
      arrayOfString[5] = g;
      arrayOfString[6] = h;
      arrayOfString[7] = j;
      arrayOfString[8] = i;
      arrayOfString[9] = k;
      int i2 = arrayOfString.length;
      int n = 0;
      int m = 0;
      if (n < i2)
      {
        String str = arrayOfString[n];
        if (str != null) {}
        for (int i1 = str.hashCode();; i1 = 0)
        {
          n += 1;
          m = m * 31 + i1;
          break;
        }
      }
      return m;
    }
    
    public String toString()
    {
      return String.format("family: %s, given: %s, middle: %s, prefix: %s, suffix: %s", new Object[] { b, c, d, e, f });
    }
  }
  
  public static class j
    implements aey.e
  {
    private final String a;
    
    public j(String paramString)
    {
      a = paramString;
    }
    
    public aey.g a()
    {
      return aey.g.j;
    }
    
    public String b()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof j)) {
        return false;
      }
      paramObject = (j)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "nickname: " + a;
    }
  }
  
  public static class k
    implements aey.e
  {
    public final String a;
    
    public k(String paramString)
    {
      a = paramString;
    }
    
    public aey.g a()
    {
      return aey.g.k;
    }
    
    public String b()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof k)) {
        return false;
      }
      paramObject = (k)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "note: " + a;
    }
  }
  
  public static class l
    implements aey.e
  {
    private String a;
    private String b;
    private String c;
    private final String d;
    private final int e;
    private boolean f;
    
    public l(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, boolean paramBoolean)
    {
      e = paramInt;
      a = paramString1;
      b = paramString2;
      c = paramString3;
      d = paramString4;
      f = paramBoolean;
    }
    
    public final aey.g a()
    {
      return aey.g.e;
    }
    
    public String b()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (!TextUtils.isEmpty(a)) {
        localStringBuilder.append(a);
      }
      if (!TextUtils.isEmpty(b))
      {
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append(b);
      }
      if (!TextUtils.isEmpty(c))
      {
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append(c);
      }
      return localStringBuilder.toString();
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof l)) {
          return false;
        }
        paramObject = (l)paramObject;
      } while ((e == e) && (TextUtils.equals(a, a)) && (TextUtils.equals(b, b)) && (TextUtils.equals(c, c)) && (f == f));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int n = e;
      int i;
      int j;
      if (a != null)
      {
        i = a.hashCode();
        if (b == null) {
          break label94;
        }
        j = b.hashCode();
        label38:
        if (c != null) {
          k = c.hashCode();
        }
        if (!f) {
          break label99;
        }
      }
      label94:
      label99:
      for (int m = 1231;; m = 1237)
      {
        return m + ((j + (i + n * 31) * 31) * 31 + k) * 31;
        i = 0;
        break;
        j = 0;
        break label38;
      }
    }
    
    public String toString()
    {
      return String.format("type: %d, organization: %s, department: %s, title: %s, isPrimary: %s", new Object[] { Integer.valueOf(e), a, b, c, Boolean.valueOf(f) });
    }
  }
  
  public static class m
    implements aey.e
  {
    private final String a;
    private final int b;
    private final String c;
    private boolean d;
    
    public m(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
    {
      a = paramString1;
      b = paramInt;
      c = paramString2;
      d = paramBoolean;
    }
    
    public final aey.g a()
    {
      return aey.g.b;
    }
    
    public String b()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof m)) {
          return false;
        }
        paramObject = (m)paramObject;
      } while ((b == b) && (TextUtils.equals(a, a)) && (TextUtils.equals(c, c)) && (d == d));
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int m = b;
      int i;
      if (a != null)
      {
        i = a.hashCode();
        if (c != null) {
          j = c.hashCode();
        }
        if (!d) {
          break label72;
        }
      }
      label72:
      for (int k = 1231;; k = 1237)
      {
        return k + ((i + m * 31) * 31 + j) * 31;
        i = 0;
        break;
      }
    }
    
    public String toString()
    {
      return String.format("type: %d, data: %s, label: %s, isPrimary: %s", new Object[] { Integer.valueOf(b), a, c, Boolean.valueOf(d) });
    }
  }
  
  public static class n
    implements aey.e
  {
    private final String a;
    private final boolean b;
    private final byte[] c;
    private Integer d = null;
    
    public n(String paramString, byte[] paramArrayOfByte, boolean paramBoolean)
    {
      a = paramString;
      c = paramArrayOfByte;
      b = paramBoolean;
    }
    
    public final aey.g a()
    {
      return aey.g.g;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof n)) {
          return false;
        }
        paramObject = (n)paramObject;
      } while ((TextUtils.equals(a, a)) && (Arrays.equals(c, c)) && (b == b));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      if (d != null) {
        return d.intValue();
      }
      if (a != null) {}
      int j;
      for (int i = a.hashCode();; i = 0)
      {
        i *= 31;
        j = i;
        if (c == null) {
          break;
        }
        byte[] arrayOfByte = c;
        int m = arrayOfByte.length;
        for (;;)
        {
          j = i;
          if (k >= m) {
            break;
          }
          i += arrayOfByte[k];
          k += 1;
        }
      }
      if (b) {}
      for (i = 1231;; i = 1237)
      {
        i += j * 31;
        d = Integer.valueOf(i);
        return i;
      }
    }
    
    public String toString()
    {
      return String.format("format: %s: size: %d, isPrimary: %s", new Object[] { a, Integer.valueOf(c.length), Boolean.valueOf(b) });
    }
  }
  
  public static class o
    implements aey.e
  {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final int h;
    private final String i;
    private boolean j;
    private int k;
    
    public o(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, String paramString8, boolean paramBoolean, int paramInt2)
    {
      h = paramInt1;
      a = paramString1;
      b = paramString2;
      c = paramString3;
      d = paramString4;
      e = paramString5;
      f = paramString6;
      g = paramString7;
      i = paramString8;
      j = paramBoolean;
      k = paramInt2;
    }
    
    public static o a(List<String> paramList, int paramInt1, String paramString, boolean paramBoolean, int paramInt2)
    {
      String[] arrayOfString = new String[7];
      int n = paramList.size();
      if (n > 7) {
        n = 7;
      }
      for (;;)
      {
        paramList = paramList.iterator();
        int m = 0;
        if (paramList.hasNext())
        {
          arrayOfString[m] = ((String)paramList.next());
          m += 1;
          if (m < n) {}
        }
        for (;;)
        {
          if (m < 7)
          {
            arrayOfString[m] = null;
            m += 1;
            continue;
            break;
          }
          return new o(arrayOfString[0], arrayOfString[1], arrayOfString[2], arrayOfString[3], arrayOfString[4], arrayOfString[5], arrayOfString[6], paramInt1, paramString, paramBoolean, paramInt2);
        }
      }
    }
    
    public final aey.g a()
    {
      return aey.g.d;
    }
    
    public String a(int paramInt)
    {
      int m = 6;
      int n = 1;
      int i1 = 1;
      StringBuilder localStringBuilder = new StringBuilder();
      String[] arrayOfString = new String[7];
      arrayOfString[0] = a;
      arrayOfString[1] = b;
      arrayOfString[2] = c;
      arrayOfString[3] = d;
      arrayOfString[4] = e;
      arrayOfString[5] = f;
      arrayOfString[6] = g;
      String str;
      if (aex.e(paramInt))
      {
        paramInt = i1;
        if (m >= 0)
        {
          str = arrayOfString[m];
          n = paramInt;
          if (!TextUtils.isEmpty(str))
          {
            if (paramInt != 0) {
              break label142;
            }
            localStringBuilder.append(' ');
          }
          for (;;)
          {
            localStringBuilder.append(str);
            n = paramInt;
            m -= 1;
            paramInt = n;
            break;
            label142:
            paramInt = 0;
          }
        }
      }
      else
      {
        m = 0;
        paramInt = n;
        if (m < 7)
        {
          str = arrayOfString[m];
          n = paramInt;
          if (!TextUtils.isEmpty(str))
          {
            if (paramInt != 0) {
              break label204;
            }
            localStringBuilder.append(' ');
          }
          for (;;)
          {
            localStringBuilder.append(str);
            n = paramInt;
            m += 1;
            paramInt = n;
            break;
            label204:
            paramInt = 0;
          }
        }
      }
      return localStringBuilder.toString().trim();
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof o)) {
          return false;
        }
        paramObject = (o)paramObject;
      } while ((h == h) && ((h != 0) || (TextUtils.equals(i, i))) && (j == j) && (TextUtils.equals(a, a)) && (TextUtils.equals(b, b)) && (TextUtils.equals(c, c)) && (TextUtils.equals(d, d)) && (TextUtils.equals(e, e)) && (TextUtils.equals(f, f)) && (TextUtils.equals(g, g)));
      return false;
    }
    
    public int hashCode()
    {
      int i1 = h;
      int m;
      int n;
      label31:
      label114:
      String str;
      if (i != null)
      {
        m = i.hashCode();
        if (!j) {
          break label156;
        }
        n = 1231;
        m = n + (m + i1 * 31) * 31;
        String[] arrayOfString = new String[7];
        arrayOfString[0] = a;
        arrayOfString[1] = b;
        arrayOfString[2] = c;
        arrayOfString[3] = d;
        arrayOfString[4] = e;
        arrayOfString[5] = f;
        arrayOfString[6] = g;
        int i2 = arrayOfString.length;
        n = 0;
        if (n >= i2) {
          return m;
        }
        str = arrayOfString[n];
        if (str == null) {
          break label163;
        }
      }
      label156:
      label163:
      for (i1 = str.hashCode();; i1 = 0)
      {
        n += 1;
        m = m * 31 + i1;
        break label114;
        m = 0;
        break;
        n = 1237;
        break label31;
      }
      return m;
    }
    
    public String toString()
    {
      return String.format("type: %d, label: %s, isPrimary: %s, pobox: %s, extendedAddress: %s, street: %s, localty: %s, region: %s, postalCode %s, country: %s", new Object[] { Integer.valueOf(h), i, Boolean.valueOf(j), a, b, c, d, e, f, g });
    }
  }
  
  public static class p
    implements aey.e
  {
    private final String a;
    private final int b;
    private final String c;
    private final boolean d;
    
    public p(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
    {
      if (paramString1.startsWith("sip:")) {}
      for (a = paramString1.substring(4);; a = paramString1)
      {
        b = paramInt;
        c = paramString2;
        d = paramBoolean;
        return;
      }
    }
    
    public aey.g a()
    {
      return aey.g.i;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof p)) {
          return false;
        }
        paramObject = (p)paramObject;
      } while ((b == b) && (TextUtils.equals(c, c)) && (TextUtils.equals(a, a)) && (d == d));
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int m = b;
      int i;
      if (c != null)
      {
        i = c.hashCode();
        if (a != null) {
          j = a.hashCode();
        }
        if (!d) {
          break label72;
        }
      }
      label72:
      for (int k = 1231;; k = 1237)
      {
        return k + ((i + m * 31) * 31 + j) * 31;
        i = 0;
        break;
      }
    }
    
    public String toString()
    {
      return "sip: " + a;
    }
  }
  
  class q
    implements aey.f
  {
    private StringBuilder b;
    private boolean c;
    
    private q() {}
    
    public void a()
    {
      b = new StringBuilder();
      b.append("[[hash: " + hashCode() + "\n");
    }
    
    public void a(aey.g paramg)
    {
      b.append(paramg.toString() + ": ");
      c = true;
    }
    
    public boolean a(aey.e parame)
    {
      if (!c)
      {
        b.append(", ");
        c = false;
      }
      b.append("[").append(parame.toString()).append("]");
      return true;
    }
    
    public void b()
    {
      b.append("]]\n");
    }
    
    public void c()
    {
      b.append("\n");
    }
    
    public String toString()
    {
      return b.toString();
    }
  }
  
  public static class r
    implements aey.e
  {
    private final String a;
    
    public r(String paramString)
    {
      a = paramString;
    }
    
    public aey.g a()
    {
      return aey.g.h;
    }
    
    public String b()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof r)) {
        return false;
      }
      paramObject = (r)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "website: " + a;
    }
  }
}

/* Location:
 * Qualified Name:     aey
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */