/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedWriter
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.dom.smil.parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;

public class SmilXmlSerializer {
    public static void serialize(SMILDocument sMILDocument, OutputStream outputStream) {
        try {
            outputStream = new BufferedWriter((Writer)new OutputStreamWriter(outputStream, "UTF-8"), 2048);
            SmilXmlSerializer.writeElement((Writer)outputStream, sMILDocument.getDocumentElement());
            outputStream.flush();
            return;
        }
        catch (UnsupportedEncodingException var0_1) {
            var0_1.printStackTrace();
            return;
        }
        catch (IOException var0_2) {
            var0_2.printStackTrace();
            return;
        }
    }

    private static void writeElement(Writer writer, Element element) throws IOException {
        Attr attr;
        Object object;
        writer.write(60);
        writer.write(element.getTagName());
        if (element.hasAttributes()) {
            object = element.getAttributes();
            for (int i = 0; i < object.getLength(); ++i) {
                attr = (Attr)object.item(i);
                writer.write(" " + attr.getName());
                writer.write("=\"" + attr.getValue() + "\"");
            }
        }
        if ((object = (SMILElement)element.getFirstChild()) != null) {
            writer.write(62);
            do {
                SmilXmlSerializer.writeElement(writer, (Element)object);
                object = attr = (SMILElement)object.getNextSibling();
            } while (attr != null);
            writer.write("</");
            writer.write(element.getTagName());
            writer.write(62);
            return;
        }
        writer.write("/>");
    }
}

