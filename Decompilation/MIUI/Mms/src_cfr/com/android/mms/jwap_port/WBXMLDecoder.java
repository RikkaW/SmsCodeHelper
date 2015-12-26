/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.DataInputStream
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  javax.xml.parsers.DocumentBuilder
 *  javax.xml.parsers.DocumentBuilderFactory
 */
package com.android.mms.jwap_port;

import android.content.Context;
import com.android.mms.jwap_port.PublicIdentifiers;
import com.android.mms.jwap_port.TokenRepository;
import com.android.mms.jwap_port.TransTable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class WBXMLDecoder {
    private byte attributeBitMask = -128;
    private String encoding;
    Context mContext;
    private byte parentBitMask = 64;
    private String publicId = "UNKNOWN";
    private byte publicIdIndex = -1;
    private String stringTable;
    private TokenRepository tokenRepository;
    private DataInputStream wbxmlStream;
    private Document xmlDocument;

    public WBXMLDecoder(Context context) {
        this.mContext = context;
    }

    private void decodeBody() throws IOException {
        this.writeRootElement();
    }

    private byte getTokenValue(byte by) {
        return (byte)(by & 63);
    }

    private boolean hasAttributes(byte by) {
        if ((this.attributeBitMask & by) == this.attributeBitMask) {
            return true;
        }
        return false;
    }

    private boolean hasContent(byte by) {
        if ((this.parentBitMask & by) == this.parentBitMask) {
            return true;
        }
        return false;
    }

    private boolean isAttrNameToken(byte by) {
        if (by >= 0) {
            return true;
        }
        return false;
    }

    private boolean isAttrValueToken(byte by) {
        if (by < 0) {
            return true;
        }
        return false;
    }

    private boolean isEntityToken(byte by) {
        if (by == 2) {
            return true;
        }
        return false;
    }

    private boolean isInlineStrToken(byte by) {
        if (by == 3) {
            return true;
        }
        return false;
    }

    private boolean isStringTableReferenceToken(byte by) {
        if (by == -125) {
            return true;
        }
        return false;
    }

    private void readPublicID() throws IOException {
        String string;
        int n;
        Object object = new byte[4];
        int n2 = this.wbxmlStream.readByte();
        if (n2 == 0) {
            this.publicIdIndex = this.wbxmlStream.readByte();
            return;
        }
        object = (Object)new StringBuffer();
        while ((n2 & 128) == 128) {
            string = Integer.toBinaryString((int)(n2 & 127));
            if (string.length() < 7) {
                n = string.length();
                for (n2 = 0; n2 < 7 - n; ++n2) {
                    object.append('0');
                }
            }
            object.append(string);
            n2 = this.wbxmlStream.readByte();
        }
        string = Integer.toBinaryString((int)(n2 & 127));
        if (string.length() < 7) {
            n = string.length();
            for (n2 = 0; n2 < 7 - n; ++n2) {
                object.append('0');
            }
        }
        object.append(string);
        n2 = Integer.valueOf((String)object.toString(), (int)2);
        this.publicId = PublicIdentifiers.getInstance().getPublicIdentifier(n2);
    }

    private void readPublicIDFromStringTable() {
        int n = this.stringTable.toString().indexOf(new String(new char[]{'\u0000'}), (int)this.publicIdIndex);
        if (n == -1) {
            this.publicId = this.stringTable.substring((int)this.publicIdIndex);
            return;
        }
        this.publicId = this.stringTable.substring((int)this.publicIdIndex, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setAttributes(Element element) throws IOException {
        byte by = this.wbxmlStream.readByte();
        String string = "";
        while (by != 1) {
            String string2;
            if (this.isInlineStrToken(by)) {
                this.writeAttrValueAsInlineStr(element, string);
                string2 = string;
            } else if (this.isEntityToken(by)) {
                this.writeEntityAsAttribute(element, string);
                string2 = string;
            } else if (this.isStringTableReferenceToken(by)) {
                this.writeAttributeFromStrTable(element, string, this.wbxmlStream.readByte());
                string2 = string;
            } else if (this.isAttrNameToken(by)) {
                string2 = this.writeAttribute(element, by);
            } else {
                string2 = string;
                if (this.isAttrValueToken(by)) {
                    this.writeAttrValue(element, string, by);
                    string2 = string;
                }
            }
            by = this.wbxmlStream.readByte();
            string = string2;
        }
    }

    private void writeAttrValue(Element element, String string, byte by) throws IOException {
        String string2 = element.getAttribute(string);
        element.setAttribute(string, string2 + this.tokenRepository.getAttributeValue(by));
    }

    private void writeAttrValueAsInlineStr(Element element, String string) throws IOException {
        String string2;
        byte by = this.wbxmlStream.readByte();
        byte[] arrby = new byte[1024];
        int n = 0;
        while (by != 0) {
            arrby[n] = by;
            by = this.wbxmlStream.readByte();
            ++n;
        }
        String string3 = string2 = element.getAttribute(string);
        if (string2.equals((Object)"null")) {
            string3 = "";
        }
        element.setAttribute(string, new StringBuffer(string3).append(new String(arrby, 0, n, this.encoding)).toString());
    }

    private String writeAttribute(Element element, byte by) {
        boolean bl = false;
        Object object = this.tokenRepository.getAttributeNameAndPrefix(by);
        String string = "";
        String string2 = object[0].toString();
        if (object[1] != null) {
            bl = true;
        }
        if (bl) {
            string = object[1].trim();
        }
        object = this.xmlDocument.createAttribute(string2);
        object.setValue(string);
        element.setAttributeNode((Attr)object);
        return string2;
    }

    private void writeAttributeFromStrTable(Element element, String string, byte by) {
        String string2 = element.getAttribute(string);
        int n = this.stringTable.indexOf(new String(new char[]{'\u0000'}), (int)by);
        element.setAttribute(string, string2 + this.stringTable.substring((int)by, n));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void writeChildElement(Element element) throws IOException {
        byte by = this.wbxmlStream.readByte();
        while (by != 1) {
            byte by2 = this.getTokenValue(by);
            if (this.isInlineStrToken(by)) {
                this.writeContentAsInlineStr(element);
            } else if (this.isEntityToken(by2)) {
                this.writeEntityContent(element);
            } else if (by == -61) {
                this.writeOpaqueContent(element);
            } else if (this.isStringTableReferenceToken(by)) {
                this.writeContentFromStrTable(element, this.wbxmlStream.readByte());
            } else if (by2 == 0) {
                TokenRepository.setCurrentCodepage(this.wbxmlStream.readByte());
            } else {
                Object object = this.tokenRepository.getTagName(by2);
                object = this.xmlDocument.createElement((String)object);
                element.appendChild((Node)object);
                if (this.hasAttributes(by)) {
                    this.setAttributes((Element)object);
                }
                if (this.hasContent(by)) {
                    this.writeChildElement((Element)object);
                    by = this.wbxmlStream.readByte();
                    continue;
                }
            }
            by = this.wbxmlStream.readByte();
        }
        return;
    }

    private void writeContentAsInlineStr(Element element) throws IOException {
        byte by = this.wbxmlStream.readByte();
        Object object = new byte[1024];
        int n = 0;
        while (by != 0) {
            object[n] = by;
            by = this.wbxmlStream.readByte();
            ++n;
        }
        object = new String((byte[])object, 0, n, this.encoding);
        element.appendChild((Node)this.xmlDocument.createTextNode((String)object));
    }

    private void writeContentFromStrTable(Element element, int n) {
        int n2 = this.stringTable.indexOf(new String(new char[]{'\u0000'}), n);
        String string = this.stringTable.substring(n, n2);
        element.appendChild((Node)this.xmlDocument.createTextNode(string));
    }

    private void writeEntityAsAttribute(Element element, String string) throws IOException {
        String string2;
        String string3 = string2 = element.getAttribute(string);
        if (string2.equals((Object)"null")) {
            string3 = "";
        }
        string2 = new StringBuffer(string3);
        int n = this.wbxmlStream.readByte();
        while ((n & -128) == 128) {
            string2.append(Integer.toString((int)((byte)(n & 127)), (int)2));
            n = this.wbxmlStream.readByte();
        }
        string3 = Integer.toString((int)n, (int)2);
        while (string3.length() < 7) {
            string3 = "0" + string3;
        }
        string2.append(string3);
        n = Integer.parseInt((String)string2.toString(), (int)2);
        element.setAttribute(string, "&#" + n + ";");
    }

    private void writeEntityContent(Element element) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int n = this.wbxmlStream.readByte();
        while ((n & -128) == 128) {
            stringBuffer.append(Integer.toString((int)((byte)(n & 127)), (int)2));
            n = this.wbxmlStream.readByte();
        }
        String string = Integer.toString((int)n, (int)2);
        while (string.length() < 7) {
            string = "0" + string;
        }
        stringBuffer.append(string);
        n = Integer.parseInt((String)stringBuffer.toString(), (int)2);
        element.appendChild((Node)this.xmlDocument.createTextNode(Integer.toString((int)n)));
    }

    private void writeOpaqueContent(Element element) throws IOException {
        int n;
        int n2 = this.wbxmlStream.readByte();
        byte[] arrby = new byte[n2];
        for (n = 0; n < n2; ++n) {
            arrby[n] = this.wbxmlStream.readByte();
        }
        n = this.wbxmlStream.readByte();
        while (n != 1) {
            n = this.wbxmlStream.readByte();
        }
        new String(arrby, this.encoding);
        element.appendChild((Node)this.xmlDocument.createTextNode(""));
    }

    private void writeRootElement() throws IOException {
        byte by = this.wbxmlStream.readByte();
        byte by2 = this.getTokenValue(by);
        String string = this.tokenRepository.getTagName(by2);
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Object object = PublicIdentifiers.getInstance().getSystemIdentifier(this.publicId);
            object = documentBuilder.getDOMImplementation().createDocumentType(string, this.publicId, (String)object);
            this.xmlDocument = documentBuilder.getDOMImplementation().createDocument("", string, (DocumentType)object);
            if (this.hasAttributes(by)) {
                this.setAttributes(this.xmlDocument.getDocumentElement());
            }
            if (this.hasContent(by)) {
                this.writeChildElement(this.xmlDocument.getDocumentElement());
            }
            return;
        }
        catch (Exception var3_4) {
            var3_4.printStackTrace();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Document decode(InputStream inputStream) {
        this.wbxmlStream = new DataInputStream(inputStream);
        try {
            this.xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            if (!this.decodeProlog()) return null;
            this.decodeBody();
            do {
                return this.xmlDocument;
                break;
            } while (true);
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return this.xmlDocument;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean decodeProlog() throws IOException {
        byte[] arrby;
        this.wbxmlStream.readByte();
        this.readPublicID();
        byte by = this.wbxmlStream.readByte();
        this.encoding = TransTable.getTable(this.mContext, "jwap_port/charsets").code2str(by);
        by = this.wbxmlStream.readByte();
        if (by < 0 || this.wbxmlStream.read(arrby = new byte[by], 0, (int)by) < by) {
            return false;
        }
        try {
            this.stringTable = new String(arrby, this.encoding);
            if (this.publicIdIndex != -1) {
                this.readPublicIDFromStringTable();
            }
            this.tokenRepository = new TokenRepository(PublicIdentifiers.getInstance().getPublicIdentifierValueHex(this.publicId), this.mContext);
            return true;
        }
        catch (UnsupportedEncodingException var2_3) {
            return false;
        }
    }
}

