package com.android.mms.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public abstract class DocumentImpl
  extends NodeImpl
  implements Document
{
  public DocumentImpl()
  {
    super(null);
  }
  
  public Node adoptNode(Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)9, null);
  }
  
  public Attr createAttribute(String paramString)
    throws DOMException
  {
    return new AttrImpl(this, paramString);
  }
  
  public Attr createAttributeNS(String paramString1, String paramString2)
    throws DOMException
  {
    return null;
  }
  
  public CDATASection createCDATASection(String paramString)
    throws DOMException
  {
    return null;
  }
  
  public Comment createComment(String paramString)
  {
    return null;
  }
  
  public DocumentFragment createDocumentFragment()
  {
    return null;
  }
  
  public Element createElementNS(String paramString1, String paramString2)
    throws DOMException
  {
    return null;
  }
  
  public EntityReference createEntityReference(String paramString)
    throws DOMException
  {
    return null;
  }
  
  public ProcessingInstruction createProcessingInstruction(String paramString1, String paramString2)
    throws DOMException
  {
    return null;
  }
  
  public Text createTextNode(String paramString)
  {
    return null;
  }
  
  public DocumentType getDoctype()
  {
    return null;
  }
  
  public String getDocumentURI()
  {
    return null;
  }
  
  public DOMConfiguration getDomConfig()
  {
    throw new DOMException((short)9, null);
  }
  
  public Element getElementById(String paramString)
  {
    return null;
  }
  
  public NodeList getElementsByTagName(String paramString)
  {
    return null;
  }
  
  public NodeList getElementsByTagNameNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public DOMImplementation getImplementation()
  {
    return null;
  }
  
  public String getInputEncoding()
  {
    return null;
  }
  
  public String getNodeName()
  {
    return "#document";
  }
  
  public short getNodeType()
  {
    return 9;
  }
  
  public boolean getStrictErrorChecking()
  {
    return true;
  }
  
  public String getXmlEncoding()
  {
    return null;
  }
  
  public boolean getXmlStandalone()
  {
    return false;
  }
  
  public String getXmlVersion()
  {
    return null;
  }
  
  public Node importNode(Node paramNode, boolean paramBoolean)
    throws DOMException
  {
    return null;
  }
  
  public void normalizeDocument()
  {
    throw new DOMException((short)9, null);
  }
  
  public Node renameNode(Node paramNode, String paramString1, String paramString2)
    throws DOMException
  {
    throw new DOMException((short)9, null);
  }
  
  public void setDocumentURI(String paramString) {}
  
  public void setStrictErrorChecking(boolean paramBoolean) {}
  
  public void setXmlStandalone(boolean paramBoolean)
    throws DOMException
  {}
  
  public void setXmlVersion(String paramString)
    throws DOMException
  {}
}

/* Location:
 * Qualified Name:     com.android.mms.dom.DocumentImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */