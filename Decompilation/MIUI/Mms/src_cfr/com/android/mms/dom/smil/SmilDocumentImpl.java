/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.DocumentImpl;
import com.android.mms.dom.events.EventImpl;
import com.android.mms.dom.smil.ElementSequentialTimeContainerImpl;
import com.android.mms.dom.smil.SmilElementImpl;
import com.android.mms.dom.smil.SmilLayoutElementImpl;
import com.android.mms.dom.smil.SmilMediaElementImpl;
import com.android.mms.dom.smil.SmilParElementImpl;
import com.android.mms.dom.smil.SmilRefElementImpl;
import com.android.mms.dom.smil.SmilRegionElementImpl;
import com.android.mms.dom.smil.SmilRegionMediaElementImpl;
import com.android.mms.dom.smil.SmilRootLayoutElementImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementSequentialTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.TimeList;

public class SmilDocumentImpl
extends DocumentImpl
implements DocumentEvent,
SMILDocument {
    ElementSequentialTimeContainer mSeqTimeContainer;

    @Override
    public boolean beginElement() {
        return this.mSeqTimeContainer.beginElement();
    }

    @Override
    public Element createElement(String string) throws DOMException {
        if ((string = string.toLowerCase()).equals((Object)"text") || string.equals((Object)"img") || string.equals((Object)"video")) {
            return new SmilRegionMediaElementImpl(this, string);
        }
        if (string.equals((Object)"audio")) {
            return new SmilMediaElementImpl(this, string);
        }
        if (string.equals((Object)"layout")) {
            return new SmilLayoutElementImpl(this, string);
        }
        if (string.equals((Object)"root-layout")) {
            return new SmilRootLayoutElementImpl(this, string);
        }
        if (string.equals((Object)"region")) {
            return new SmilRegionElementImpl(this, string);
        }
        if (string.equals((Object)"ref")) {
            return new SmilRefElementImpl(this, string);
        }
        if (string.equals((Object)"par")) {
            return new SmilParElementImpl(this, string);
        }
        return new SmilElementImpl(this, string);
    }

    @Override
    public Event createEvent(String string) throws DOMException {
        if ("Event".equals((Object)string)) {
            return new EventImpl();
        }
        throw new DOMException(9, "Not supported interface");
    }

    @Override
    public boolean endElement() {
        return this.mSeqTimeContainer.endElement();
    }

    @Override
    public NodeList getActiveChildrenAt(float f2) {
        return this.mSeqTimeContainer.getActiveChildrenAt(f2);
    }

    @Override
    public TimeList getBegin() {
        return this.mSeqTimeContainer.getBegin();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public SMILElement getBody() {
        var3_1 = this.getDocumentElement();
        var2_2 = this.getHead().getNextSibling();
        if (var2_2 == null) ** GOTO lbl-1000
        var1_3 = var2_2;
        if (!(var2_2 instanceof SMILElement)) lbl-1000: // 2 sources:
        {
            var1_3 = this.createElement("body");
            var3_1.appendChild(var1_3);
        }
        this.mSeqTimeContainer = new ElementSequentialTimeContainerImpl((SMILElement)var1_3){

            @Override
            public boolean beginElement() {
                Event event = SmilDocumentImpl.this.createEvent("Event");
                event.initEvent("SmilDocumentStart", false, false);
                SmilDocumentImpl.this.dispatchEvent(event);
                return true;
            }

            @Override
            public boolean endElement() {
                Event event = SmilDocumentImpl.this.createEvent("Event");
                event.initEvent("SimlDocumentEnd", false, false);
                SmilDocumentImpl.this.dispatchEvent(event);
                return true;
            }

            @Override
            ElementTime getParentElementTime() {
                return null;
            }

            @Override
            public NodeList getTimeChildren() {
                return SmilDocumentImpl.this.getBody().getElementsByTagName("par");
            }

            @Override
            public void pauseElement() {
            }

            @Override
            public void resumeElement() {
            }

            @Override
            public void seekElement(float f2) {
            }
        };
        return (SMILElement)var1_3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public SMILElement getDocumentElement() {
        Node node;
        Node node2 = this.getFirstChild();
        if (node2 != null) {
            node = node2;
            if (node2 instanceof SMILElement) return (SMILElement)node;
        }
        node = this.createElement("smil");
        this.appendChild(node);
        return (SMILElement)node;
    }

    @Override
    public float getDur() {
        return this.mSeqTimeContainer.getDur();
    }

    @Override
    public TimeList getEnd() {
        return this.mSeqTimeContainer.getEnd();
    }

    @Override
    public short getFill() {
        return this.mSeqTimeContainer.getFill();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public SMILElement getHead() {
        Node node;
        Element element = this.getDocumentElement();
        Node node2 = element.getFirstChild();
        if (node2 != null) {
            node = node2;
            if (node2 instanceof SMILElement) return (SMILElement)node;
        }
        node = this.createElement("head");
        element.appendChild(node);
        return (SMILElement)node;
    }

    @Override
    public SMILLayoutElement getLayout() {
        Node node;
        SMILElement sMILElement = this.getHead();
        for (node = sMILElement.getFirstChild(); node != null && !(node instanceof SMILLayoutElement); node = node.getNextSibling()) {
        }
        Node node2 = node;
        if (node == null) {
            node2 = new SmilLayoutElementImpl(this, "layout");
            sMILElement.appendChild(node2);
        }
        return (SMILLayoutElement)node2;
    }

    @Override
    public NodeList getTimeChildren() {
        return this.mSeqTimeContainer.getTimeChildren();
    }

    @Override
    public void pauseElement() {
        this.mSeqTimeContainer.pauseElement();
    }

    @Override
    public void resumeElement() {
        this.mSeqTimeContainer.resumeElement();
    }

    @Override
    public void seekElement(float f2) {
        this.mSeqTimeContainer.seekElement(f2);
    }

    @Override
    public void setDur(float f2) throws DOMException {
        this.mSeqTimeContainer.setDur(f2);
    }

}

