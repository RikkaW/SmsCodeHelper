/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.smil.SmilDocumentImpl;
import com.android.mms.dom.smil.SmilElementImpl;
import com.android.mms.layout.LayoutManager;
import com.android.mms.layout.LayoutParameters;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.SMILRootLayoutElement;

public class SmilLayoutElementImpl
extends SmilElementImpl
implements SMILLayoutElement {
    SmilLayoutElementImpl(SmilDocumentImpl smilDocumentImpl, String string) {
        super(smilDocumentImpl, string);
    }

    @Override
    public NodeList getRegions() {
        return this.getElementsByTagName("region");
    }

    @Override
    public SMILRootLayoutElement getRootLayout() {
        Object object = this.getChildNodes();
        SMILRootLayoutElement sMILRootLayoutElement = null;
        int n = object.getLength();
        for (int i = 0; i < n; ++i) {
            if (!object.item(i).getNodeName().equals((Object)"root-layout")) continue;
            sMILRootLayoutElement = (SMILRootLayoutElement)object.item(i);
        }
        object = sMILRootLayoutElement;
        if (sMILRootLayoutElement == null) {
            object = (SMILRootLayoutElement)this.getOwnerDocument().createElement("root-layout");
            object.setWidth(LayoutManager.getInstance().getLayoutParameters().getWidth());
            object.setHeight(LayoutManager.getInstance().getLayoutParameters().getHeight());
            this.appendChild((Node)object);
        }
        return object;
    }
}

