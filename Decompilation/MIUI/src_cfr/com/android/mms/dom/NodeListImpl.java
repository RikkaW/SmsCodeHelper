/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.dom;

import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListImpl
implements NodeList {
    private boolean mDeepSearch;
    private Node mRootNode;
    private ArrayList<Node> mSearchNodes;
    private ArrayList<Node> mStaticNodes;
    private String mTagName;

    public NodeListImpl(ArrayList<Node> arrayList) {
        this.mStaticNodes = arrayList;
    }

    public NodeListImpl(Node node, String string, boolean bl) {
        this.mRootNode = node;
        this.mTagName = string;
        this.mDeepSearch = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void fillList(Node node) {
        if (node == this.mRootNode) {
            this.mSearchNodes = new ArrayList();
        } else if (this.mTagName == null || node.getNodeName().equals((Object)this.mTagName)) {
            this.mSearchNodes.add((Object)node);
        }
        node = node.getFirstChild();
        while (node != null) {
            if (this.mDeepSearch) {
                this.fillList(node);
            } else if (this.mTagName == null || node.getNodeName().equals((Object)this.mTagName)) {
                this.mSearchNodes.add((Object)node);
            }
            node = node.getNextSibling();
        }
        return;
    }

    @Override
    public int getLength() {
        if (this.mStaticNodes == null) {
            this.fillList(this.mRootNode);
            return this.mSearchNodes.size();
        }
        return this.mStaticNodes.size();
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public Node item(int n) {
        if (this.mStaticNodes == null) {
            this.fillList(this.mRootNode);
            return (Node)this.mSearchNodes.get(n);
        }
        try {
            return (Node)this.mStaticNodes.get(n);
        }
        catch (IndexOutOfBoundsException var2_4) {
            return null;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return null;
        }
    }
}

