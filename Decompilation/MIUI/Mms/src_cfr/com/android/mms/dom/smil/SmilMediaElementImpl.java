/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.dom.smil;

import android.util.Log;
import com.android.mms.dom.events.EventImpl;
import com.android.mms.dom.smil.ElementTimeImpl;
import com.android.mms.dom.smil.SmilDocumentImpl;
import com.android.mms.dom.smil.SmilElementImpl;
import com.android.mms.dom.smil.SmilParElementImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementParallelTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILMediaElement;
import org.w3c.dom.smil.TimeList;

public class SmilMediaElementImpl
extends SmilElementImpl
implements SMILMediaElement {
    ElementTime mElementTime;

    SmilMediaElementImpl(SmilDocumentImpl smilDocumentImpl, String string) {
        super(smilDocumentImpl, string);
        this.mElementTime = new ElementTimeImpl(this){

            private Event createEvent(String string) {
                Event event = ((DocumentEvent)((Object)SmilMediaElementImpl.this.getOwnerDocument())).createEvent("Event");
                event.initEvent(string, false, false);
                return event;
            }

            private Event createEvent(String string, int n) {
                EventImpl eventImpl = (EventImpl)((DocumentEvent)((Object)SmilMediaElementImpl.this.getOwnerDocument())).createEvent("Event");
                eventImpl.initEvent(string, false, false, n);
                return eventImpl;
            }

            @Override
            public boolean beginElement() {
                Event event = this.createEvent("SmilMediaStart");
                SmilMediaElementImpl.this.dispatchEvent(event);
                return true;
            }

            @Override
            public boolean endElement() {
                Event event = this.createEvent("SmilMediaEnd");
                SmilMediaElementImpl.this.dispatchEvent(event);
                return true;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public float getDur() {
                float f2;
                float f3 = f2 = super.getDur();
                if (f2 != 0.0f) return f3;
                String string = SmilMediaElementImpl.this.getTagName();
                if (string.equals((Object)"video")) return -1.0f;
                if (string.equals((Object)"audio")) {
                    return -1.0f;
                }
                if (string.equals((Object)"text")) return 0.0f;
                if (string.equals((Object)"img")) {
                    return 0.0f;
                }
                Log.w((String)"Mms:smil", (String)"Unknown media type");
                return f2;
            }

            @Override
            ElementTime getParentElementTime() {
                return ((SmilParElementImpl)this.mSmilElement.getParentNode()).mParTimeContainer;
            }

            @Override
            public void pauseElement() {
                Event event = this.createEvent("SmilMediaPause");
                SmilMediaElementImpl.this.dispatchEvent(event);
            }

            @Override
            public void resumeElement() {
                Event event = this.createEvent("SmilMediaStart");
                SmilMediaElementImpl.this.dispatchEvent(event);
            }

            @Override
            public void seekElement(float f2) {
                Event event = this.createEvent("SmilMediaSeek", (int)f2);
                SmilMediaElementImpl.this.dispatchEvent(event);
            }
        };
    }

    @Override
    public boolean beginElement() {
        return this.mElementTime.beginElement();
    }

    @Override
    public boolean endElement() {
        return this.mElementTime.endElement();
    }

    @Override
    public TimeList getBegin() {
        return this.mElementTime.getBegin();
    }

    @Override
    public float getDur() {
        return this.mElementTime.getDur();
    }

    @Override
    public TimeList getEnd() {
        return this.mElementTime.getEnd();
    }

    @Override
    public short getFill() {
        return this.mElementTime.getFill();
    }

    @Override
    public String getSrc() {
        return this.getAttribute("src");
    }

    @Override
    public void pauseElement() {
        this.mElementTime.pauseElement();
    }

    @Override
    public void resumeElement() {
        this.mElementTime.resumeElement();
    }

    @Override
    public void seekElement(float f2) {
        this.mElementTime.seekElement(f2);
    }

    @Override
    public void setDur(float f2) throws DOMException {
        this.mElementTime.setDur(f2);
    }

    @Override
    public void setSrc(String string) throws DOMException {
        this.setAttribute("src", string);
    }

}

