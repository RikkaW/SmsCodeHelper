import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class g
{
  private e a = null;
  
  public g(e parame)
  {
    a = parame;
  }
  
  private h a(h paramh, Stack<h> paramStack)
  {
    ai localai = paramh.d();
    int j = localai.c();
    l[] arrayOfl = new l[j];
    int i = 0;
    if (i >= j) {
      if (!a.b()) {
        break label211;
      }
    }
    for (paramh = localai.a(paramh.g(), arrayOfl);; paramh = new m(l.a.f, Float.valueOf(0.0F)))
    {
      return h.a(paramh);
      if (!paramStack.empty())
      {
        h localh = (h)paramStack.pop();
        if (h.a.a == localh.a()) {
          arrayOfl[i] = localh.b();
        }
        for (;;)
        {
          i += 1;
          break;
          if (h.a.b != localh.a()) {
            break label121;
          }
          arrayOfl[i] = localh.c();
        }
        label121:
        throw new i("表达式不合法，操作符\"" + localai.a() + "\"参数错误;位置：" + localh.g(), paramh.toString(), paramh.g());
      }
      throw new i("表达式不合法，操作符\"" + localai.a() + "\"找不到相应的参数，或参数个数不足;", paramh.toString(), paramh.g());
      label211:
      if (localai.c() != arrayOfl.length) {
        throw new IllegalArgumentException("运算操作符参数为空:" + localai.a() + ", type:" + localai.c() + ", cur:" + arrayOfl.length);
      }
    }
  }
  
  private h b(h paramh, Stack<h> paramStack)
  {
    if (!paramStack.empty())
    {
      ArrayList localArrayList = new ArrayList();
      int i = 1;
      h localh;
      for (;;)
      {
        if ((i == 0) || (paramStack.empty()))
        {
          if ((i == 0) || (!paramStack.empty())) {
            break label237;
          }
          throw new i("表达式不合法，函数\"" + paramh.e() + "\"缺少\"(\"；位置:" + (paramh.g() + paramh.toString().length()), paramh.toString(), paramh.g());
        }
        localh = (h)paramStack.pop();
        if (h.a.a == localh.a())
        {
          localArrayList.add(localh.b());
        }
        else if (h.a.b == localh.a())
        {
          localArrayList.add(localh.c());
        }
        else
        {
          if (!"(".equals(localh.f())) {
            break;
          }
          i = 0;
        }
      }
      throw new i("表达式不合法，函数\"" + paramh.e() + "\"遇到非法参数" + localh.toString() + ";位置:" + localh.g(), paramh.toString(), paramh.g());
      label237:
      paramStack = (l[])localArrayList.toArray(new l[localArrayList.size()]);
      return h.a(ad.a(paramh.e(), paramh.g(), paramStack));
    }
    throw new i("表达式不合法，函数\"" + paramh.e() + "\"不完整", paramh.toString(), paramh.g());
  }
  
  public List<h> a(String paramString)
  {
    q localq = new q();
    try
    {
      paramString = localq.b(paramString);
      return paramString;
    }
    catch (s paramString)
    {
      paramString.printStackTrace();
      throw new i(paramString.getMessage());
    }
  }
  
  public List<h> a(List<h> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      throw new IllegalArgumentException("无法转化空的表达式");
    }
    ArrayList localArrayList = new ArrayList();
    Stack localStack2 = new Stack();
    Stack localStack1 = new Stack();
    Iterator localIterator = paramList.iterator();
    paramList = null;
    if (!localIterator.hasNext())
    {
      label70:
      if (!localStack2.empty()) {
        break label1313;
      }
      if (localStack1.size() == 1) {
        break label1501;
      }
      paramList = new StringBuffer("\r\n");
    }
    for (;;)
    {
      if (localStack1.empty())
      {
        throw new i("表达式不完整.\r\n 校验栈状态异常:" + paramList);
        localh1 = (h)localIterator.next();
        if (h.a.a == localh1.a())
        {
          localArrayList.add(localh1);
          localStack1.push(localh1);
          break;
        }
        Object localObject;
        if (h.a.b == localh1.a())
        {
          localObject = a.a(localh1.c().q());
          if (localObject == null) {
            localh1.c().a(l.a.a);
          }
          for (;;)
          {
            localArrayList.add(localh1);
            localStack1.push(localh1);
            break;
            if (((o)localObject).a() == null) {
              throw new i("表达式不合法，变量\"" + localh1.toString() + "\"缺少定义;位置:" + localh1.g(), localh1.toString(), localh1.g());
            }
            localh1.c().a(((o)localObject).a());
          }
        }
        if (h.a.c == localh1.a())
        {
          if (localStack2.empty())
          {
            if (ai.u == localh1.d()) {
              throw new i("在读入\"：\"时，操作栈中找不到对应的\"？\" ", localh1.toString(), localh1.g());
            }
            localStack2.push(localh1);
            break;
          }
          i = 1;
          for (;;)
          {
            if ((localStack2.empty()) || (i == 0))
            {
              if ((i == 0) || (!localStack2.empty())) {
                break;
              }
              if (ai.u != localh1.d()) {
                break label793;
              }
              throw new i("在读入\"：\"时，操作栈中找不到对应的\"？\"", localh1.toString(), localh1.g());
            }
            localObject = (h)localStack2.peek();
            if (h.a.d == ((h)localObject).a())
            {
              if (ai.u == localh1.d()) {
                throw new i("在读入\"：\"时，操作栈中找不到对应的\"？\"", localh1.toString(), localh1.g());
              }
              localStack2.push(localh1);
              i = 0;
            }
            else if ((h.a.e == ((h)localObject).a()) && ("(".equals(((h)localObject).f())))
            {
              if (ai.u == localh1.d()) {
                throw new i("在读入\"：\"时，操作栈中找不到对应的\"？\"", localh1.toString(), localh1.g());
              }
              localStack2.push(localh1);
              i = 0;
            }
            else if (h.a.c == ((h)localObject).a())
            {
              if (localh1.d().b() > ((h)localObject).d().b())
              {
                if (ai.u != localh1.d())
                {
                  localStack2.push(localh1);
                  i = 0;
                }
              }
              else if (localh1.d().b() == ((h)localObject).d().b())
              {
                if (ai.t == localh1.d())
                {
                  localStack2.push(localh1);
                  i = 0;
                }
                else if (ai.u == localh1.d())
                {
                  if (ai.t == ((h)localObject).d())
                  {
                    localStack2.pop();
                    h localh2 = h.a(ai.v);
                    localh2.a(((h)localObject).g());
                    localStack2.push(localh2);
                    i = 0;
                  }
                  else if (ai.v == ((h)localObject).d())
                  {
                    localStack1.push(a((h)localObject, localStack1));
                    localStack2.pop();
                    localArrayList.add(localObject);
                  }
                }
                else
                {
                  localStack1.push(a((h)localObject, localStack1));
                  localStack2.pop();
                  localArrayList.add(localObject);
                }
              }
              else
              {
                localStack1.push(a((h)localObject, localStack1));
                localStack2.pop();
                localArrayList.add(localObject);
              }
            }
          }
          label793:
          localStack2.push(localh1);
          break;
        }
        if (h.a.d == localh1.a())
        {
          paramList = localh1;
          break;
        }
        if (h.a.e != localh1.a()) {
          break;
        }
        if ("(".equals(localh1.f()))
        {
          if (paramList != null)
          {
            localArrayList.add(localh1);
            localStack1.push(localh1);
            localStack2.push(localh1);
            localStack2.push(paramList);
            paramList = null;
            break;
          }
          localStack2.push(localh1);
          break;
        }
        if (")".equals(localh1.f()))
        {
          i = 1;
          for (;;)
          {
            if ((i == 0) || (localStack2.empty()))
            {
              if ((i == 0) || (!localStack2.empty())) {
                break;
              }
              throw new i("在读入\")\"时，操作栈中找不到对应的\"(\" ", localh1.f(), localh1.g());
            }
            localObject = (h)localStack2.pop();
            if (h.a.c == ((h)localObject).a())
            {
              if (ai.t == ((h)localObject).d()) {
                throw new i("在读入\")\"时，操作栈中遇到\"？\" ,缺少\":\"号", ((h)localObject).toString(), ((h)localObject).g());
              }
              localStack1.push(a((h)localObject, localStack1));
              localArrayList.add(localObject);
            }
            else if (h.a.d == ((h)localObject).a())
            {
              localStack1.push(b((h)localObject, localStack1));
              localArrayList.add(localh1);
              localArrayList.add(localObject);
            }
            else if ("(".equals(((h)localObject).f()))
            {
              i = 0;
            }
          }
        }
        if (!",".equals(localh1.f())) {
          break;
        }
        int i = 1;
        label1252:
        do
        {
          for (;;)
          {
            if ((localStack2.empty()) || (i == 0))
            {
              if ((i == 0) || (!localStack2.empty())) {
                break;
              }
              throw new i("在读入\",\"时，操作符栈弹空，没有找到相应的函数词元 ", localh1.f(), localh1.g());
            }
            localObject = (h)localStack2.peek();
            if (h.a.c == ((h)localObject).a())
            {
              if (ai.t == ((h)localObject).d()) {
                throw new i("在读入\",\"时，操作栈中遇到\"？\" ,缺少\":\"号", ((h)localObject).toString(), ((h)localObject).g());
              }
              localStack2.pop();
              localStack1.push(a((h)localObject, localStack1));
              localArrayList.add(localObject);
            }
            else
            {
              if (h.a.d != ((h)localObject).a()) {
                break label1252;
              }
              i = 0;
            }
          }
        } while ((h.a.e != ((h)localObject).a()) || (!"(".equals(((h)localObject).f())));
        throw new i("在读入\",\"时，操作符栈顶为\"(\",,(函数丢失) 位置：" + ((h)localObject).g(), localh1.f(), localh1.g());
        label1313:
        paramList = (h)localStack2.pop();
        if (h.a.c == paramList.a())
        {
          if (ai.t == paramList.d()) {
            throw new i("操作栈中遇到剩余的\"？\" ,缺少\":\"号", paramList.toString(), paramList.g());
          }
          localStack1.push(a(paramList, localStack1));
          localArrayList.add(paramList);
          break label70;
        }
        if (h.a.d == paramList.a()) {
          throw new i("函数" + paramList.e() + "缺少\")\"", paramList.e(), paramList.g());
        }
        if (!"(".equals(paramList.f())) {
          break label70;
        }
        throw new i("左括号\"(\"缺少配套的右括号\")\"", paramList.e(), paramList.g());
      }
      h localh1 = (h)localStack1.pop();
      paramList.append("\t").append(localh1.toString()).append("\r\n");
    }
    label1501:
    return localArrayList;
  }
  
  public m b(List<h> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      throw new IllegalArgumentException("无法执行空的逆波兰式队列");
    }
    Object localObject1 = new Stack();
    paramList = paramList.iterator();
    Object localObject2;
    for (;;)
    {
      if (!paramList.hasNext())
      {
        localObject2 = new ag(a.a());
        if (((Stack)localObject1).size() != 1) {
          break;
        }
        localObject1 = ((h)((Stack)localObject1).pop()).b();
        paramList = (List<h>)localObject1;
        if (((m)localObject1).p()) {
          paramList = ((n)((m)localObject1).b()).a((d)localObject2);
        }
        return paramList;
      }
      localObject2 = (h)paramList.next();
      if (h.a.a == ((h)localObject2).a())
      {
        ((Stack)localObject1).push(localObject2);
      }
      else if (h.a.b == ((h)localObject2).a())
      {
        localObject2 = a.a(((h)localObject2).c().q());
        if (localObject2 != null) {
          ((Stack)localObject1).push(h.a(((o)localObject2).a(), ((o)localObject2).b()));
        } else {
          ((Stack)localObject1).push(h.a(l.a.a, null));
        }
      }
      else
      {
        Object localObject3;
        Object localObject4;
        int i;
        if (h.a.c == ((h)localObject2).a())
        {
          localObject3 = ((h)localObject2).d();
          int j = ((ai)localObject3).c();
          localObject4 = new m[j];
          i = 0;
          for (;;)
          {
            if (i >= j)
            {
              ((Stack)localObject1).push(h.a(new n((h)localObject2, (m[])localObject4, a.b())));
              break;
            }
            if (((Stack)localObject1).empty()) {
              break label373;
            }
            h localh = (h)((Stack)localObject1).pop();
            if (h.a.a != localh.a()) {
              break label330;
            }
            localObject4[i] = localh.b();
            i += 1;
          }
          label330:
          throw new IllegalStateException("操作符" + ((ai)localObject3).a() + "找不到相应的参数，或参数个数不足;位置：" + ((h)localObject2).g());
          label373:
          throw new IllegalStateException("操作符" + ((ai)localObject3).a() + "找不到相应的参数，或参数个数不足;位置：" + ((h)localObject2).g());
        }
        if (h.a.d == ((h)localObject2).a())
        {
          if (!((Stack)localObject1).empty())
          {
            if (")".equals(((h)((Stack)localObject1).pop()).f()))
            {
              localObject3 = new ArrayList();
              i = 1;
              for (;;)
              {
                if ((i == 0) || (((Stack)localObject1).empty()))
                {
                  if ((i == 0) || (!((Stack)localObject1).empty())) {
                    break label623;
                  }
                  throw new IllegalStateException("函数" + ((h)localObject2).e() + "执行时没有找到应有的\"(\"");
                }
                localObject4 = (h)((Stack)localObject1).pop();
                if (h.a.a == ((h)localObject4).a())
                {
                  ((List)localObject3).add(((h)localObject4).b());
                }
                else
                {
                  if (!"(".equals(((h)localObject4).f())) {
                    break;
                  }
                  i = 0;
                }
              }
              throw new IllegalStateException("函数" + ((h)localObject2).e() + "执行时遇到非法参数" + ((h)localObject4).toString());
              label623:
              ((Stack)localObject1).push(h.a(new n((h)localObject2, (m[])((List)localObject3).toArray(new m[((List)localObject3).size()]))));
            }
            else
            {
              throw new IllegalStateException("函数" + ((h)localObject2).e() + "执行时没有找到应有的\")\"");
            }
          }
          else {
            throw new IllegalStateException("函数" + ((h)localObject2).e() + "执行时没有找到应有的\")\"");
          }
        }
        else if (h.a.e == ((h)localObject2).a()) {
          ((Stack)localObject1).push(localObject2);
        }
      }
    }
    paramList = new StringBuffer("\r\n");
    for (;;)
    {
      if (((Stack)localObject1).empty()) {
        throw new IllegalStateException("表达式不完整.\r\n 结果状态异常:" + paramList);
      }
      localObject2 = (h)((Stack)localObject1).pop();
      paramList.append("\t").append(((h)localObject2).toString()).append("\r\n");
    }
  }
}

/* Location:
 * Qualified Name:     g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */