/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Vector
 */
package com.android.mms.dom;

import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NamedNodeMapImpl
implements NamedNodeMap {
    private Vector<Node> mNodes = new Vector();

    @Override
    public int getLength() {
        return this.mNodes.size();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public Node getNamedItem(String string) {
        Node node = null;
        int n = 0;
        do {
            Node node2 = node;
            if (n >= this.mNodes.size()) return node2;
            if (string.equals((Object)((Node)this.mNodes.elementAt(n)).getNodeName())) {
                return (Node)this.mNodes.elementAt(n);
            }
            ++n;
        } while (true);
    }

    @Override
    public Node getNamedItemNS(String string, String string2) {
        return null;
    }

    @Override
    public Node item(int n) {
        if (n < this.mNodes.size()) {
            return (Node)this.mNodes.elementAt(n);
        }
        return null;
    }

    @Override
    public Node removeNamedItem(String object) throws DOMException {
        if ((object = this.getNamedItem((String)object)) == null) {
            throw new DOMException(8, "Not found");
        }
        this.mNodes.remove(object);
        return object;
    }

    @Override
    public Node removeNamedItemNS(String string, String string2) throws DOMException {
        return null;
    }

    @Override
    public Node setNamedItem(Node node) throws DOMException {
        Node node2 = this.getNamedItem(node.getNodeName());
        if (node2 != null) {
            this.mNodes.remove((Object)node2);
        }
        this.mNodes.add((Object)node);
        return node2;
    }

    @Override
    public Node setNamedItemNS(Node node) throws DOMException {
        return null;
    }
}

