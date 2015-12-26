/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.NodeListImpl;
import com.android.mms.dom.smil.ElementTimeContainerImpl;
import java.util.ArrayList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.smil.ElementParallelTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public abstract class ElementParallelTimeContainerImpl
extends ElementTimeContainerImpl
implements ElementParallelTimeContainer {
    ElementParallelTimeContainerImpl(SMILElement sMILElement) {
        super(sMILElement);
    }

    @Override
    public NodeList getActiveChildrenAt(float f2) {
        ArrayList arrayList = new ArrayList();
        NodeList nodeList = this.getTimeChildren();
        int n = nodeList.getLength();
        for (int i = 0; i < n; ++i) {
            double d2;
            boolean bl;
            int n2;
            Time time;
            double d3;
            double d4 = 0.0;
            boolean bl2 = false;
            ElementTime elementTime = (ElementTime)((Object)nodeList.item(i));
            TimeList timeList = elementTime.getBegin();
            int n3 = timeList.getLength();
            for (n2 = 0; n2 < n3; ++n2) {
                time = timeList.item(n2);
                bl = bl2;
                d2 = d4;
                if (time.getResolved()) {
                    d3 = time.getResolvedOffset() * 1000.0;
                    bl = bl2;
                    d2 = d4;
                    if (d3 <= (double)f2) {
                        bl = bl2;
                        d2 = d4;
                        if (d3 >= d4) {
                            d2 = d3;
                            bl = true;
                        }
                    }
                }
                bl2 = bl;
                d4 = d2;
            }
            timeList = elementTime.getEnd();
            n3 = timeList.getLength();
            for (n2 = 0; n2 < n3; ++n2) {
                time = timeList.item(n2);
                bl = bl2;
                d2 = d4;
                if (time.getResolved()) {
                    d3 = time.getResolvedOffset() * 1000.0;
                    bl = bl2;
                    d2 = d4;
                    if (d3 <= (double)f2) {
                        bl = bl2;
                        d2 = d4;
                        if (d3 >= d4) {
                            d2 = d3;
                            bl = false;
                        }
                    }
                }
                bl2 = bl;
                d4 = d2;
            }
            if (!bl2) continue;
            arrayList.add((Object)((Node)((Object)elementTime)));
        }
        return new NodeListImpl(arrayList);
    }

    @Override
    public float getDur() {
        float f2;
        float f3 = f2 = super.getDur();
        if (f2 == 0.0f) {
            f3 = this.getImplicitDuration();
        }
        return f3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String getEndSync() {
        String string = this.mSmilElement.getAttribute("endsync");
        if (string == null || string.length() == 0) {
            this.setEndSync("last");
            return "last";
        }
        String string2 = string;
        if ("first".equals((Object)string)) return string2;
        string2 = string;
        if ("last".equals((Object)string)) return string2;
        string2 = string;
        if ("all".equals((Object)string)) return string2;
        string2 = string;
        if ("media".equals((Object)string)) return string2;
        this.setEndSync("last");
        return "last";
    }

    public float getImplicitDuration() {
        float f2;
        float f3 = f2 = -1.0f;
        if ("last".equals((Object)this.getEndSync())) {
            NodeList nodeList = this.getTimeChildren();
            int n = 0;
            do {
                f3 = f2;
                if (n >= nodeList.getLength()) break;
                TimeList timeList = ((ElementTime)((Object)nodeList.item(n))).getEnd();
                for (int i = 0; i < timeList.getLength(); ++i) {
                    Time time = timeList.item(i);
                    if (time.getTimeType() == 0) {
                        return -1.0f;
                    }
                    f3 = f2;
                    if (time.getResolved()) {
                        float f4 = (float)time.getResolvedOffset();
                        f3 = f2;
                        if (f4 > f2) {
                            f3 = f4;
                        }
                    }
                    f2 = f3;
                }
                ++n;
            } while (true);
        }
        return f3;
    }

    public void setEndSync(String string) throws DOMException {
        if ("first".equals((Object)string) || "last".equals((Object)string) || "all".equals((Object)string) || "media".equals((Object)string)) {
            this.mSmilElement.setAttribute("endsync", string);
            return;
        }
        throw new DOMException(9, "Unsupported endsync value" + string);
    }
}

