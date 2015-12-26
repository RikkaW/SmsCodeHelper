package com.android.mms.dom.smil.parser;

import com.google.android.mms.MmsException;
import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.smil.SMILDocument;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SmilXmlParser
{
  private SmilContentHandler mContentHandler;
  private XMLReader mXmlReader;
  
  public SmilXmlParser()
    throws MmsException
  {
    System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");
    try
    {
      mXmlReader = XMLReaderFactory.createXMLReader();
      mContentHandler = new SmilContentHandler();
      mXmlReader.setContentHandler(mContentHandler);
      return;
    }
    catch (SAXException localSAXException)
    {
      throw new MmsException(localSAXException);
    }
  }
  
  private void validateDocument(SMILDocument paramSMILDocument)
  {
    paramSMILDocument.getBody();
    paramSMILDocument.getLayout();
  }
  
  public SMILDocument parse(InputStream paramInputStream)
    throws IOException, SAXException
  {
    mContentHandler.reset();
    mXmlReader.parse(new InputSource(paramInputStream));
    paramInputStream = mContentHandler.getSmilDocument();
    validateDocument(paramInputStream);
    return paramInputStream;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.parser.SmilXmlParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */