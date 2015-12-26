package com.google.protobuf.micro;

import java.io.IOException;

public class CodedOutputStreamMicro$OutOfSpaceException
  extends IOException
{
  private static final long serialVersionUID = -6947486886997889499L;
  
  CodedOutputStreamMicro$OutOfSpaceException()
  {
    super("CodedOutputStream was writing to a flat byte array and ran out of space.");
  }
}

/* Location:
 * Qualified Name:     com.google.protobuf.micro.CodedOutputStreamMicro.OutOfSpaceException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */