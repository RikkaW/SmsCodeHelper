package com.amap.api.mapcore2d;

public class fd
{
  private static double[][] a;
  
  private static double a(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return Math.sin(paramDouble2 * paramDouble3 * 0.0174532925199433D) * paramDouble1;
  }
  
  public static double[] a(double paramDouble1, double paramDouble2)
  {
    int i = (int)((paramDouble1 - 71.0D) / 3.0D) * 19 + (int)(paramDouble2 / 3.0D);
    double d1 = a[i][0];
    double d2 = a[i][1];
    double d3 = a[i][2];
    double d7 = a[i][3];
    double d8 = a[i][4];
    double d4 = a[i][5];
    double d5 = a[i][6];
    double d6 = a[i][7];
    double d14 = a[i][8];
    d7 = d7 * paramDouble2 / 1.0E9D;
    d8 /= 1000000.0D;
    double d13 = paramDouble1 - 105.0D;
    double d9 = a(150.0D, 15.0D, d13);
    double d10 = a(40.0D, 60.0D, d13);
    double d11 = a(20.0D, 180.0D, d13);
    double d12 = a(20.0D, 360.0D, d13);
    d13 = a(20.0D, 1080.0D, d13);
    d14 = d14 * paramDouble2 / 1.0E9D;
    double d15 = paramDouble1 - 105.0D;
    double d18 = paramDouble2 - 35.0D;
    double d16 = a(160.0D, 15.0D, d18);
    double d17 = a(40.0D, 60.0D, d18);
    d18 = a(20.0D, 180.0D, d18);
    double d19 = a(20.0D, 360.0D, d15);
    return new double[] { paramDouble1 + ((d13 + (d9 + d10 + d11 + d12)) * 0.6666666666666666D + (d3 + (d1 * paramDouble1 + d2 * paramDouble2))) * (d7 + d8), ((a(20.0D, 1080.0D, d15) + (d16 + d17 + d18 + d19)) * 0.6666666666666666D + (d4 * paramDouble1 + d5 * paramDouble2 + d6)) * (d14 + 9.0539664E-6D) + paramDouble2 };
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.fd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */