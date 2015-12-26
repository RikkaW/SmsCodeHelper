package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.string.Base64Coder;
import java.io.PrintStream;

public class RC4Cryption
{
  private static int keylength = 8;
  private byte[] S = new byte['Ā'];
  private int next_j = 64870;
  private int the_i = 0;
  private int the_j = 0;
  
  public static byte[] decrypt(byte[] paramArrayOfByte, String paramString)
  {
    return encrypt(paramArrayOfByte, Base64Coder.decode(paramString));
  }
  
  public static String encrypt(byte[] paramArrayOfByte, String paramString)
  {
    return String.valueOf(Base64Coder.encode(encrypt(paramArrayOfByte, paramString.getBytes())));
  }
  
  public static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte2.length];
    RC4Cryption localRC4Cryption = new RC4Cryption();
    localRC4Cryption.ksa(paramArrayOfByte1);
    localRC4Cryption.init();
    int i = 0;
    while (i < paramArrayOfByte2.length)
    {
      arrayOfByte[i] = ((byte)(paramArrayOfByte2[i] ^ localRC4Cryption.nextVal()));
      i += 1;
    }
    return arrayOfByte;
  }
  
  public static byte[] generateKeyForRC4(String paramString1, String paramString2)
  {
    paramString1 = Base64Coder.decode(paramString1);
    paramString2 = paramString2.getBytes();
    byte[] arrayOfByte = new byte[paramString1.length + 1 + paramString2.length];
    int i = 0;
    while (i < paramString1.length)
    {
      arrayOfByte[i] = paramString1[i];
      i += 1;
    }
    arrayOfByte[paramString1.length] = 95;
    i = 0;
    while (i < paramString2.length)
    {
      arrayOfByte[(paramString1.length + 1 + i)] = paramString2[i];
      i += 1;
    }
    return arrayOfByte;
  }
  
  private void init()
  {
    the_j = 0;
    the_i = 0;
  }
  
  private void ksa(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < 256)
    {
      S[i] = ((byte)i);
      i += 1;
    }
    the_j = 0;
    for (the_i = 0; the_i < paramInt; the_i += 1)
    {
      the_j = ((the_j + posify(S[the_i]) + posify(paramArrayOfByte[(the_i % j)])) % 256);
      sswap(S, the_i, the_j);
    }
    if (paramInt != 256) {
      next_j = ((the_j + posify(S[paramInt]) + posify(paramArrayOfByte[(paramInt % j)])) % 256);
    }
    if (paramBoolean)
    {
      System.out.print("S_" + (paramInt - 1) + ":");
      i = 0;
      while (i <= paramInt)
      {
        System.out.print(" " + posify(S[i]));
        i += 1;
      }
      System.out.print("   j_" + (paramInt - 1) + "=" + the_j);
      System.out.print("   j_" + paramInt + "=" + next_j);
      System.out.print("   S_" + (paramInt - 1) + "[j_" + (paramInt - 1) + "]=" + posify(S[the_j]));
      System.out.print("   S_" + (paramInt - 1) + "[j_" + paramInt + "]=" + posify(S[next_j]));
      if (S[1] != 0) {
        System.out.print("   S[1]!=0");
      }
      System.out.println();
    }
  }
  
  private void ksa(byte[] paramArrayOfByte)
  {
    ksa(256, paramArrayOfByte, false);
  }
  
  public static int posify(byte paramByte)
  {
    if (paramByte >= 0) {
      return paramByte;
    }
    return paramByte + 256;
  }
  
  private static void sswap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfByte[paramInt1];
    paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
    paramArrayOfByte[paramInt2] = i;
  }
  
  byte nextVal()
  {
    the_i = ((the_i + 1) % 256);
    the_j = ((the_j + posify(S[the_i])) % 256);
    sswap(S, the_i, the_j);
    return S[((posify(S[the_i]) + posify(S[the_j])) % 256)];
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.RC4Cryption
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */