import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ae
{
  private static ae a = new ae();
  private HashMap<String, ae.b> b = new HashMap();
  
  private ae()
  {
    try
    {
      b();
      a();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      throw new IllegalArgumentException(localException);
    }
  }
  
  public static Object a(String paramString, Object[] paramArrayOfObject)
  {
    paramString = (ae.b)ab.get(paramString);
    if (paramString == null) {
      throw new NoSuchMethodException();
    }
    return paramString.a(paramArrayOfObject);
  }
  
  public static Method a(String paramString)
  {
    paramString = (ae.b)ab.get(paramString);
    if (paramString == null) {
      throw new NoSuchMethodException();
    }
    return a;
  }
  
  private List<ae.c> a(Node paramNode)
  {
    paramNode = paramNode.getChildNodes();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i >= paramNode.getLength()) {
        return localArrayList;
      }
      Node localNode = paramNode.item(i);
      if (localNode.getNodeName().equals("constructor-arg")) {
        localArrayList.add(new ae.c(localNode.getAttributes().getNamedItem("type").getNodeValue(), localNode.getTextContent()));
      }
      i += 1;
    }
  }
  
  private void a()
  {
    Object localObject = af.class.newInstance();
    Method[] arrayOfMethod = af.class.getDeclaredMethods();
    int k = arrayOfMethod.length;
    int i = 0;
    if (i >= k) {
      return;
    }
    Method localMethod = arrayOfMethod[i];
    Annotation[] arrayOfAnnotation = localMethod.getAnnotations();
    int m = arrayOfAnnotation.length;
    int j = 0;
    for (;;)
    {
      if (j >= m)
      {
        i += 1;
        break;
      }
      Annotation localAnnotation = arrayOfAnnotation[j];
      if ((localAnnotation instanceof j)) {
        b.put(((j)localAnnotation).a(), new ae.b(localMethod, localObject));
      }
      j += 1;
    }
  }
  
  private Class[] a(List<ae.c> paramList)
  {
    if (paramList == null) {
      return null;
    }
    Class[] arrayOfClass = new Class[paramList.size()];
    int i = 0;
    for (;;)
    {
      if (i >= paramList.size()) {
        return arrayOfClass;
      }
      arrayOfClass[i] = geta;
      i += 1;
    }
  }
  
  private ae.a b(Node paramNode)
  {
    ae.a locala = new ae.a(paramNode.getAttributes().getNamedItem("name").getNodeValue(), paramNode.getAttributes().getNamedItem("method").getNodeValue());
    paramNode = paramNode.getChildNodes();
    int i = 0;
    for (;;)
    {
      if (i >= paramNode.getLength()) {
        return locala;
      }
      Node localNode = paramNode.item(i);
      if (localNode.getNodeName().equals("parameter-type")) {
        locala.a(localNode.getTextContent());
      }
      i += 1;
    }
  }
  
  private void b()
  {
    Object localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    NodeList localNodeList;
    int i;
    try
    {
      localObject1 = ((DocumentBuilder)localObject1).parse(ae.class.getResourceAsStream("/IKExpression.cfg.xml"));
      if (localObject1 == null) {
        return;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      do
      {
        for (;;)
        {
          localObject2 = null;
        }
        localObject2 = ((Document)localObject2).getElementsByTagName("function-configuration");
      } while (((NodeList)localObject2).getLength() < 1);
      localNodeList = ((NodeList)localObject2).item(0).getChildNodes();
      i = 0;
    }
    label65:
    if (i < localNodeList.getLength())
    {
      localObject2 = localNodeList.item(i);
      if (((Node)localObject2).getNodeName().equals("bean")) {
        break label106;
      }
    }
    label106:
    Class localClass;
    Object localObject5;
    Object localObject4;
    int j;
    do
    {
      i += 1;
      break label65;
      break;
      localClass = Class.forName(((Node)localObject2).getAttributes().getNamedItem("class").getNodeValue());
      localObject5 = ((Node)localObject2).getChildNodes();
      localObject4 = new HashSet();
      j = 0;
      localObject2 = null;
      if (j < ((NodeList)localObject5).getLength()) {
        break label265;
      }
    } while (((HashSet)localObject4).size() <= 0);
    if ((localObject2 == null) || (((List)localObject2).size() <= 0)) {}
    Object localObject3;
    for (Object localObject2 = localClass.newInstance();; localObject2 = localClass.getConstructor((Class[])localObject3).newInstance((Object[])localObject2))
    {
      localObject3 = ((HashSet)localObject4).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (ae.a)((Iterator)localObject3).next();
        localObject5 = localClass.getMethod(b, a(c));
        b.put(a, new ae.b((Method)localObject5, localObject2));
      }
      break;
      label265:
      Node localNode = ((NodeList)localObject5).item(j);
      if ((localNode.getNodeName().equals("constructor-args")) && (localObject2 == null)) {
        localObject3 = a(localNode);
      }
      do
      {
        do
        {
          j += 1;
          localObject2 = localObject3;
          break;
          localObject3 = localObject2;
        } while (!localNode.getNodeName().equals("function"));
        localObject3 = localObject2;
      } while (((HashSet)localObject4).add(b(localNode)));
      throw new SAXException("方法名不能重复");
      localObject3 = a((List)localObject2);
      localObject2 = b((List)localObject2);
    }
  }
  
  private Object[] b(List<ae.c> paramList)
  {
    if (paramList == null) {
      return null;
    }
    Object[] arrayOfObject = new Object[paramList.size()];
    int i = 0;
    for (;;)
    {
      if (i >= paramList.size()) {
        return arrayOfObject;
      }
      arrayOfObject[i] = getb;
      i += 1;
    }
  }
  
  class a
  {
    String a;
    String b;
    List<ae.c> c;
    
    public a(String paramString1, String paramString2)
    {
      if ((paramString1 == null) || (paramString2 == null)) {
        throw new IllegalArgumentException();
      }
      a = paramString1;
      b = paramString2;
      c = new ArrayList();
    }
    
    public void a(String paramString)
    {
      c.add(new ae.c(ae.this, paramString));
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (this == paramObject) {
        bool1 = true;
      }
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramObject == null);
        bool1 = bool2;
      } while (getClass() != paramObject.getClass());
      paramObject = (a)paramObject;
      return a.equals(a);
    }
    
    public int hashCode()
    {
      return a.hashCode();
    }
  }
  
  class b
  {
    Method a;
    Object b;
    
    public b(Method paramMethod, Object paramObject)
    {
      a = paramMethod;
      b = paramObject;
    }
    
    public Object a(Object[] paramArrayOfObject)
    {
      return a.invoke(b, paramArrayOfObject);
    }
  }
  
  class c
  {
    Class a;
    Object b;
    
    public c(String paramString)
    {
      try
      {
        a = a(paramString);
        return;
      }
      catch (Exception this$1)
      {
        printStackTrace();
      }
    }
    
    public c(String paramString1, String paramString2)
    {
      try
      {
        a = a(paramString1);
        b = a.getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramString2 });
        return;
      }
      catch (Exception this$1)
      {
        printStackTrace();
      }
    }
    
    private Class a(String paramString)
    {
      if ("boolean".equals(paramString)) {
        return Boolean.TYPE;
      }
      if ("byte".equals(paramString)) {
        return Byte.TYPE;
      }
      if ("char".equals(paramString)) {
        return Character.TYPE;
      }
      if ("double".equals(paramString)) {
        return Double.TYPE;
      }
      if ("float".equals(paramString)) {
        return Float.TYPE;
      }
      if ("int".equals(paramString)) {
        return Integer.TYPE;
      }
      if ("long".equals(paramString)) {
        return Long.TYPE;
      }
      if ("short".equals(paramString)) {
        return Short.TYPE;
      }
      return Class.forName(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     ae
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */