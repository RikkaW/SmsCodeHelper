/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.smil.ElementParallelTimeContainerImpl;
import com.android.mms.dom.smil.SmilDocumentImpl;
import com.android.mms.dom.smil.SmilElementImpl;
import com.android.mms.dom.smil.TimeListImpl;
import java.util.ArrayList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementParallelTimeContainer;
import org.w3c.dom.smil.ElementSequentialTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILParElement;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public class SmilParElementImpl
extends SmilElementImpl
implements SMILParElement {
    ElementParallelTimeContainer mParTimeContainer;

    SmilParElementImpl(SmilDocumentImpl smilDocumentImpl, String string) {
        super(smilDocumentImpl, string.toUpperCase());
        this.mParTimeContainer = new ElementParallelTimeContainerImpl(this){

            @Override
            public boolean beginElement() {
                Event event = ((DocumentEvent)((Object)SmilParElementImpl.this.getOwnerDocument())).createEvent("Event");
                event.initEvent("SmilSlideStart", false, false);
                SmilParElementImpl.this.dispatchEvent(event);
                return true;
            }

            @Override
            public boolean endElement() {
                Event event = ((DocumentEvent)((Object)SmilParElementImpl.this.getOwnerDocument())).createEvent("Event");
                event.initEvent("SmilSlideEnd", false, false);
                SmilParElementImpl.this.dispatchEvent(event);
                return true;
            }

            @Override
            public TimeList getBegin() {
                TimeList timeList;
                TimeList timeList2 = timeList = super.getBegin();
                if (timeList.getLength() > 1) {
                    timeList2 = new ArrayList();
                    timeList2.add((Object)timeList.item(0));
                    timeList2 = new TimeListImpl((ArrayList<Time>)timeList2);
                }
                return timeList2;
            }

            @Override
            ElementTime getParentElementTime() {
                return ((SmilDocumentImpl)this.mSmilElement.getOwnerDocument()).mSeqTimeContainer;
            }

            @Override
            public NodeList getTimeChildren() {
                return SmilParElementImpl.this.getChildNodes();
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
    }

    @Override
    public boolean beginElement() {
        return this.mParTimeContainer.beginElement();
    }

    @Override
    public boolean endElement() {
        return this.mParTimeContainer.endElement();
    }

    @Override
    public NodeList getActiveChildrenAt(float f2) {
        return this.mParTimeContainer.getActiveChildrenAt(f2);
    }

    @Override
    public TimeList getBegin() {
        return this.mParTimeContainer.getBegin();
    }

    @Override
    public float getDur() {
        return this.mParTimeContainer.getDur();
    }

    @Override
    public TimeList getEnd() {
        return this.mParTimeContainer.getEnd();
    }

    @Override
    public short getFill() {
        return this.mParTimeContainer.getFill();
    }

    @Override
    public NodeList getTimeChildren() {
        return this.mParTimeContainer.getTimeChildren();
    }

    @Override
    public void pauseElement() {
        this.mParTimeContainer.pauseElement();
    }

    @Override
    public void resumeElement() {
        this.mParTimeContainer.resumeElement();
    }

    @Override
    public void seekElement(float f2) {
        this.mParTimeContainer.seekElement(f2);
    }

    @Override
    public void setDur(float f2) throws DOMException {
        this.mParTimeContainer.setDur(f2);
    }

}

