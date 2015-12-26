package com.android.mms.dom;

import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListImpl
  implements NodeList
{
  private boolean mDeepSearch;
  private Node mRootNode;
  private ArrayList<Node> mSearchNodes;
  private ArrayList<Node> mStaticNodes;
  private String mTagName;
  
  public NodeListImpl(ArrayList<Node> paramArrayList)
  {
    mStaticNodes = paramArrayList;
  }
  
  public NodeListImpl(Node paramNode, String paramString, boolean paramBoolean)
  {
    mRootNode = paramNode;
    mTagName = paramString;
    mDeepSearch = paramBoolean;
  }
  
  private void fillList(Node paramNode)
  {
    if (paramNode == mRootNode)
    {
      mSearchNodes = new ArrayList();
      paramNode = paramNode.getFirstChild();
      label26:
      if (paramNode == null) {
        return;
      }
      if (!mDeepSearch) {
        break label87;
      }
      fillList(paramNode);
    }
    for (;;)
    {
      paramNode = paramNode.getNextSibling();
      break label26;
      if ((mTagName != null) && (!paramNode.getNodeName().equals(mTagName))) {
        break;
      }
      mSearchNodes.add(paramNode);
      break;
      label87:
      if ((mTagName == null) || (paramNode.getNodeName().equals(mTagName))) {
        mSearchNodes.add(paramNode);
      }
    }
  }
  
  public int getLength()
  {
    if (mStaticNodes == null)
    {
      fillList(mRootNode);
      return mSearchNodes.size();
    }
    return mStaticNodes.size();
  }
  
  public Node item(int paramInt)
  {
    if (mStaticNodes == null) {
      fillList(mRootNode);
    }
    for (;;)
    {
      Node localNode;
      try
      {
        localNode = (Node)mSearchNodes.get(paramInt);
        return localNode;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException2) {}
      try
      {
        localNode = (Node)mStaticNodes.get(paramInt);
        return localNode;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException1)
      {
        return null;
      }
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.NodeListImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */