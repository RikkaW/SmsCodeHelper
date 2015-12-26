/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Vector
 */
package com.android.mms.dom;

import com.android.mms.dom.DocumentImpl;
import com.android.mms.dom.NodeListImpl;
import com.android.mms.dom.events.EventTargetImpl;
import java.util.NoSuchElementException;
import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public abstract class NodeImpl
implements Node,
EventTarget {
    private final Vector<Node> mChildNodes = new Vector();
    private final EventTarget mEventTarget;
    DocumentImpl mOwnerDocument;
    private Node mParentNode;

    protected NodeImpl(DocumentImpl documentImpl) {
        this.mEventTarget = new EventTargetImpl(this);
        this.mOwnerDocument = documentImpl;
    }

    private void setParentNode(Node node) {
        this.mParentNode = node;
    }

    @Override
    public void addEventListener(String string, EventListener eventListener, boolean bl) {
        this.mEventTarget.addEventListener(string, eventListener, bl);
    }

    @Override
    public Node appendChild(Node node) throws DOMException {
        ((NodeImpl)node).setParentNode(this);
        this.mChildNodes.remove((Object)node);
        this.mChildNodes.add((Object)node);
        return node;
    }

    @Override
    public Node cloneNode(boolean bl) {
        return null;
    }

    @Override
    public short compareDocumentPosition(Node node) throws DOMException {
        throw new DOMException(9, null);
    }

    @Override
    public boolean dispatchEvent(Event event) throws EventException {
        return this.mEventTarget.dispatchEvent(event);
    }

    @Override
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override
    public String getBaseURI() {
        return null;
    }

    @Override
    public NodeList getChildNodes() {
        return new NodeListImpl(this, null, false);
    }

    @Override
    public Object getFeature(String string, String string2) {
        return null;
    }

    @Override
    public Node getFirstChild() {
        try {
            Node node = (Node)this.mChildNodes.firstElement();
            return node;
        }
        catch (NoSuchElementException var1_2) {
            return null;
        }
    }

    @Override
    public Node getLastChild() {
        try {
            Node node = (Node)this.mChildNodes.lastElement();
            return node;
        }
        catch (NoSuchElementException var1_2) {
            return null;
        }
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getNamespaceURI() {
        return null;
    }

    @Override
    public Node getNextSibling() {
        if (this.mParentNode != null && this != this.mParentNode.getLastChild()) {
            Vector<Node> vector = ((NodeImpl)this.mParentNode).mChildNodes;
            return (Node)vector.elementAt(vector.indexOf((Object)this) + 1);
        }
        return null;
    }

    @Override
    public String getNodeValue() throws DOMException {
        return null;
    }

    @Override
    public Document getOwnerDocument() {
        return this.mOwnerDocument;
    }

    @Override
    public Node getParentNode() {
        return this.mParentNode;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public Node getPreviousSibling() {
        if (this.mParentNode != null && this != this.mParentNode.getFirstChild()) {
            Vector<Node> vector = ((NodeImpl)this.mParentNode).mChildNodes;
            return (Node)vector.elementAt(vector.indexOf((Object)this) - 1);
        }
        return null;
    }

    @Override
    public String getTextContent() throws DOMException {
        throw new DOMException(9, null);
    }

    @Override
    public Object getUserData(String string) {
        return null;
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }

    @Override
    public boolean hasChildNodes() {
        if (!this.mChildNodes.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Node insertBefore(Node node, Node node2) throws DOMException {
        return null;
    }

    @Override
    public boolean isDefaultNamespace(String string) {
        throw new DOMException(9, null);
    }

    @Override
    public boolean isEqualNode(Node node) {
        throw new DOMException(9, null);
    }

    @Override
    public boolean isSameNode(Node node) {
        throw new DOMException(9, null);
    }

    @Override
    public boolean isSupported(String string, String string2) {
        return false;
    }

    @Override
    public String lookupNamespaceURI(String string) {
        return null;
    }

    @Override
    public String lookupPrefix(String string) {
        return null;
    }

    @Override
    public void normalize() {
    }

    @Override
    public Node removeChild(Node node) throws DOMException {
        if (this.mChildNodes.contains((Object)node)) {
            this.mChildNodes.remove((Object)node);
            ((NodeImpl)node).setParentNode(null);
            return null;
        }
        throw new DOMException(8, "Child does not exist");
    }

    @Override
    public void removeEventListener(String string, EventListener eventListener, boolean bl) {
        this.mEventTarget.removeEventListener(string, eventListener, bl);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public Node replaceChild(Node node, Node node2) throws DOMException {
        if (!this.mChildNodes.contains((Object)node2)) {
            throw new DOMException(8, "Old child does not exist");
        }
        try {
            this.mChildNodes.remove((Object)node);
        }
        catch (DOMException var3_3) {}
        this.mChildNodes.setElementAt((Object)node, this.mChildNodes.indexOf((Object)node2));
        ((NodeImpl)node).setParentNode(this);
        ((NodeImpl)node2).setParentNode(null);
        return node2;
    }

    @Override
    public void setNodeValue(String string) throws DOMException {
    }

    @Override
    public void setPrefix(String string) throws DOMException {
    }

    @Override
    public void setTextContent(String string) throws DOMException {
        throw new DOMException(9, null);
    }

    @Override
    public Object setUserData(String string, Object object, UserDataHandler userDataHandler) {
        throw new DOMException(9, null);
    }
}

