/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.NodeListImpl;
import com.android.mms.dom.smil.ElementTimeContainerImpl;
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.smil.ElementSequentialTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;

public abstract class ElementSequentialTimeContainerImpl
extends ElementTimeContainerImpl
implements ElementSequentialTimeContainer {
    ElementSequentialTimeContainerImpl(SMILElement sMILElement) {
        super(sMILElement);
    }

    @Override
    public NodeList getActiveChildrenAt(float f2) {
        NodeList nodeList = this.getTimeChildren();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            if ((f2 -= ((ElementTime)((Object)nodeList.item(i))).getDur()) >= 0.0f) continue;
            arrayList.add((Object)nodeList.item(i));
            return new NodeListImpl(arrayList);
        }
        return new NodeListImpl(arrayList);
    }

    @Override
    public float getDur() {
        float f2;
        float f3 = f2 = super.getDur();
        if (f2 == 0.0f) {
            NodeList nodeList = this.getTimeChildren();
            int n = 0;
            do {
                f3 = f2;
                if (n >= nodeList.getLength()) break;
                ElementTime elementTime = (ElementTime)((Object)nodeList.item(n));
                if (elementTime.getDur() < 0.0f) {
                    return -1.0f;
                }
                f2 += elementTime.getDur();
                ++n;
            } while (true);
        }
        return f3;
    }
}

