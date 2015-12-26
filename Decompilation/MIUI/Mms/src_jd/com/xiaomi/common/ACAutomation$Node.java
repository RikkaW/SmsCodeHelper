package com.xiaomi.common;

import java.util.HashMap;
import java.util.Map;

class ACAutomation$Node
{
  char c;
  Map<Character, Node> childs;
  int depth;
  Node fa;
  Node fail;
  Node lastPattern;
  int patternId;
  int type;
  
  ACAutomation$Node(ACAutomation paramACAutomation, int paramInt, char paramChar, Node paramNode)
  {
    type = paramInt;
    depth = -1;
    c = paramChar;
    childs = new HashMap();
    paramACAutomation = paramNode;
    if (paramNode == null) {
      paramACAutomation = this;
    }
    fa = paramACAutomation;
    fail = this;
    lastPattern = this;
  }
  
  public boolean isRoot()
  {
    return fa == this;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.ACAutomation.Node
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */