import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributeListImpl;

public class ajh
{
  private String[] a;
  private String[] b;
  private String[] c;
  private byte[] d;
  
  private AttributeList a(ajg paramajg)
  {
    AttributeListImpl localAttributeListImpl = new AttributeListImpl();
    int i = paramajg.b();
    while (i != 1)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      String str2 = a(paramajg, b, i);
      int k = str2.indexOf('=');
      String str1 = str2;
      if (k != -1)
      {
        localStringBuffer.append(str2.substring(k + 1));
        str1 = str2.substring(0, k);
      }
      i = paramajg.b();
      if (((i & 0x80) != 0) || (i == 2) || (i == 3) || (i == -125) || ((i >= 64) && (i <= 66)) || ((i >= -128) && (i <= -126)))
      {
        switch (i)
        {
        default: 
          localStringBuffer.append(a(paramajg, c, i));
        }
        for (;;)
        {
          int j = paramajg.b();
          break;
          localStringBuffer.append((char)paramajg.b());
          continue;
          localStringBuffer.append(paramajg.d());
          continue;
          localStringBuffer.append(paramajg.e());
          continue;
          throw new SAXException("Unsupported token " + j);
          int m = paramajg.b();
          if (m < 0) {
            throw new SAXException("Unsupported token OPAQUE" + j);
          }
          k = 0;
          while (k < m)
          {
            paramajg.b();
            k += 1;
          }
        }
      }
      localAttributeListImpl.addAttribute(str1, null, localStringBuffer.toString());
    }
    return localAttributeListImpl;
  }
  
  private void a(ajg paramajg, byte paramByte, DocumentHandler paramDocumentHandler, Stack<String> paramStack)
  {
    byte b1 = (byte)(paramByte & 0x3F);
    String str = a(paramajg, a, b1);
    Object localObject = new AttributeListImpl();
    if ((paramByte & 0x80) != 0) {
      localObject = a(paramajg);
    }
    paramDocumentHandler.startElement(str, (AttributeList)localObject);
    if ((paramByte & 0x40) != 0)
    {
      paramStack.addElement(str);
      return;
    }
    paramDocumentHandler.endElement(str);
  }
  
  public String a(int paramInt)
  {
    InputStreamReader localInputStreamReader = new InputStreamReader(new ByteArrayInputStream(d, paramInt, d.length), "UTF-8");
    StringBuffer localStringBuffer = new StringBuffer();
    for (;;)
    {
      paramInt = localInputStreamReader.read();
      if (paramInt == -1) {
        throw new IOException("Unexpected stream EOF met");
      }
      if (paramInt == 0) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append((char)paramInt);
    }
  }
  
  String a(ajg paramajg, String[] paramArrayOfString, byte paramByte)
  {
    int i = (paramByte & 0x7F) - 5;
    if (i == -1) {
      return paramajg.d();
    }
    if ((i < 0) || (paramArrayOfString == null) || (i >= paramArrayOfString.length) || (paramArrayOfString[i] == null)) {
      throw new SAXException("Undefined Token " + paramByte);
    }
    return paramArrayOfString[i];
  }
  
  public void a(InputStream paramInputStream, ajf paramajf, DocumentHandler paramDocumentHandler)
  {
    char[] arrayOfChar = new char[1];
    Stack localStack = new Stack();
    ajg localajg = new ajg(paramInputStream);
    a = localajg.b();
    b = localajg.b();
    if (b == 0) {
      localajg.c();
    }
    c = localajg.b();
    int j = localajg.b();
    d = new byte[j];
    int i = 0;
    while (i < j)
    {
      d[i] = localajg.b();
      i += 1;
    }
    paramDocumentHandler.startDocument();
    byte b1;
    for (;;)
    {
      i = localajg.a();
      if (i == -1)
      {
        if (localStack.isEmpty()) {
          break;
        }
        throw new SAXException("Inconsistent WAP nodes");
      }
      b1 = (byte)i;
      switch (b1)
      {
      default: 
        a(localajg, b1, paramDocumentHandler, localStack);
        break;
      case 0: 
        i = localajg.b();
        if (i != 0) {
          throw new SAXException("Unsupported Code Page " + i);
        }
        break;
      case 1: 
        if (localStack.isEmpty()) {
          throw new SAXException("Parse Error, two many end flag");
        }
        paramDocumentHandler.endElement((String)localStack.pop());
        break;
      case 2: 
        arrayOfChar[0] = ((char)localajg.c());
        paramDocumentHandler.characters(arrayOfChar, 0, 1);
        break;
      case 3: 
        paramInputStream = localajg.d();
        paramDocumentHandler.characters(paramInputStream.toCharArray(), 0, paramInputStream.length());
        break;
      case -125: 
        paramajf = a(localajg.c());
        paramInputStream = paramajf;
        if (paramajf == null) {
          paramInputStream = "";
        }
        paramDocumentHandler.characters(paramInputStream.toCharArray(), 0, paramInputStream.length());
      }
    }
    throw new SAXException("Not support token " + b1);
  }
  
  public void a(String[] paramArrayOfString)
  {
    a = paramArrayOfString;
  }
  
  public void b(String[] paramArrayOfString)
  {
    b = paramArrayOfString;
  }
  
  public void c(String[] paramArrayOfString)
  {
    c = paramArrayOfString;
  }
}

/* Location:
 * Qualified Name:     ajh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */