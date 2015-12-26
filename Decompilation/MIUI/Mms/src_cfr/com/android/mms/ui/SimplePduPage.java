/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.ui;

import com.android.mms.ui.SimplePduPart;
import java.util.ArrayList;

public class SimplePduPage {
    private ArrayList<SimplePduPart> mParts = new ArrayList();

    public void addPart(SimplePduPart simplePduPart) {
        if (simplePduPart != null) {
            this.mParts.add((Object)simplePduPart);
        }
    }

    public SimplePduPart getPageAppearancePart() {
        SimplePduPart simplePduPart = null;
        for (SimplePduPart simplePduPart2 : this.mParts) {
            if (simplePduPart2.getAttachmentType() == 0) {
                simplePduPart = simplePduPart2;
                continue;
            }
            if (simplePduPart2.getAttachmentType() != 3 && simplePduPart2.getAttachmentType() != 2 && simplePduPart2.getAttachmentType() != 1 && simplePduPart2.getAttachmentType() != 4 && simplePduPart2.getAttachmentType() != 5) continue;
            return simplePduPart2;
        }
        return simplePduPart;
    }

    public SimplePduPart getPart(int n) {
        if (n < this.mParts.size()) {
            return (SimplePduPart)((Object)this.mParts.get(n));
        }
        return null;
    }

    public int getPartSize() {
        return this.mParts.size();
    }

    public String getText() {
        SimplePduPart simplePduPart = this.getTypedPart(0);
        if (simplePduPart != null) {
            return simplePduPart.getText();
        }
        return null;
    }

    public SimplePduPart getTypedPart(int n) {
        for (SimplePduPart simplePduPart : this.mParts) {
            if (simplePduPart.getAttachmentType() != n) continue;
            return simplePduPart;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.mParts.isEmpty();
    }
}

