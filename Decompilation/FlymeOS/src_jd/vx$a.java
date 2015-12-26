import android.database.Cursor;
import android.util.Log;

public class vx$a
{
  public int A;
  public int B;
  public int C;
  public int D;
  public int E;
  public int F;
  public int G;
  public int H;
  public int I;
  public int J;
  public int K;
  public int L;
  public int a;
  public int b;
  public int c;
  public int d;
  public int e;
  public int f;
  public int g;
  public int h;
  public int i;
  public int j;
  public int k;
  public int l;
  public int m;
  public int n;
  public int o;
  public int p;
  public int q;
  public int r;
  public int s;
  public int t;
  public int u;
  public int v;
  public int w;
  public int x;
  public int y;
  public int z;
  
  public vx$a()
  {
    a = 0;
    b = 1;
    c = 2;
    d = 3;
    e = 4;
    f = 5;
    g = 6;
    h = 8;
    i = 9;
    j = 10;
    k = 11;
    l = 12;
    m = 13;
    n = 14;
    o = 18;
    p = 19;
    q = 20;
    r = 21;
    s = 22;
    t = 23;
    u = 24;
    v = 25;
    w = 26;
    x = 27;
    y = 10;
    z = 28;
    A = 29;
    B = 30;
    C = 31;
    D = 32;
    E = 33;
    F = 34;
    G = 35;
    H = 36;
    I = 37;
    J = 38;
    K = 39;
    L = 40;
  }
  
  public vx$a(Cursor paramCursor)
  {
    try
    {
      a = paramCursor.getColumnIndexOrThrow("transport_type");
    }
    catch (IllegalArgumentException localIllegalArgumentException33)
    {
      try
      {
        b = paramCursor.getColumnIndexOrThrow("_id");
      }
      catch (IllegalArgumentException localIllegalArgumentException33)
      {
        try
        {
          c = paramCursor.getColumnIndexOrThrow("thread_id");
        }
        catch (IllegalArgumentException localIllegalArgumentException33)
        {
          try
          {
            d = paramCursor.getColumnIndexOrThrow("address");
          }
          catch (IllegalArgumentException localIllegalArgumentException33)
          {
            try
            {
              e = paramCursor.getColumnIndexOrThrow("body");
            }
            catch (IllegalArgumentException localIllegalArgumentException33)
            {
              try
              {
                f = paramCursor.getColumnIndexOrThrow("date");
              }
              catch (IllegalArgumentException localIllegalArgumentException33)
              {
                try
                {
                  g = paramCursor.getColumnIndexOrThrow("date_sent");
                }
                catch (IllegalArgumentException localIllegalArgumentException33)
                {
                  try
                  {
                    h = paramCursor.getColumnIndexOrThrow("type");
                  }
                  catch (IllegalArgumentException localIllegalArgumentException33)
                  {
                    try
                    {
                      i = paramCursor.getColumnIndexOrThrow("status");
                    }
                    catch (IllegalArgumentException localIllegalArgumentException33)
                    {
                      try
                      {
                        j = paramCursor.getColumnIndexOrThrow("locked");
                      }
                      catch (IllegalArgumentException localIllegalArgumentException33)
                      {
                        try
                        {
                          k = paramCursor.getColumnIndexOrThrow("error_code");
                        }
                        catch (IllegalArgumentException localIllegalArgumentException33)
                        {
                          try
                          {
                            m = paramCursor.getColumnIndexOrThrow("sub");
                          }
                          catch (IllegalArgumentException localIllegalArgumentException33)
                          {
                            try
                            {
                              n = paramCursor.getColumnIndexOrThrow("sub_cs");
                            }
                            catch (IllegalArgumentException localIllegalArgumentException33)
                            {
                              try
                              {
                                o = paramCursor.getColumnIndexOrThrow("m_type");
                              }
                              catch (IllegalArgumentException localIllegalArgumentException33)
                              {
                                try
                                {
                                  p = paramCursor.getColumnIndexOrThrow("msg_box");
                                }
                                catch (IllegalArgumentException localIllegalArgumentException33)
                                {
                                  try
                                  {
                                    q = paramCursor.getColumnIndexOrThrow("d_rpt");
                                  }
                                  catch (IllegalArgumentException localIllegalArgumentException33)
                                  {
                                    try
                                    {
                                      r = paramCursor.getColumnIndexOrThrow("rr");
                                    }
                                    catch (IllegalArgumentException localIllegalArgumentException33)
                                    {
                                      try
                                      {
                                        s = paramCursor.getColumnIndexOrThrow("err_type");
                                      }
                                      catch (IllegalArgumentException localIllegalArgumentException33)
                                      {
                                        try
                                        {
                                          t = paramCursor.getColumnIndexOrThrow("locked");
                                        }
                                        catch (IllegalArgumentException localIllegalArgumentException33)
                                        {
                                          try
                                          {
                                            u = paramCursor.getColumnIndexOrThrow("st");
                                          }
                                          catch (IllegalArgumentException localIllegalArgumentException33)
                                          {
                                            try
                                            {
                                              v = paramCursor.getColumnIndexOrThrow("text_only");
                                            }
                                            catch (IllegalArgumentException localIllegalArgumentException33)
                                            {
                                              try
                                              {
                                                w = paramCursor.getColumnIndexOrThrow("delivery_status");
                                              }
                                              catch (IllegalArgumentException localIllegalArgumentException33)
                                              {
                                                try
                                                {
                                                  x = paramCursor.getColumnIndexOrThrow("resp_st");
                                                }
                                                catch (IllegalArgumentException localIllegalArgumentException33)
                                                {
                                                  try
                                                  {
                                                    y = paramCursor.getColumnIndexOrThrow("is_status_report");
                                                  }
                                                  catch (IllegalArgumentException localIllegalArgumentException33)
                                                  {
                                                    try
                                                    {
                                                      z = paramCursor.getColumnIndexOrThrow("association_id");
                                                    }
                                                    catch (IllegalArgumentException localIllegalArgumentException33)
                                                    {
                                                      try
                                                      {
                                                        A = paramCursor.getColumnIndexOrThrow("port");
                                                      }
                                                      catch (IllegalArgumentException localIllegalArgumentException33)
                                                      {
                                                        try
                                                        {
                                                          B = paramCursor.getColumnIndexOrThrow("protocol");
                                                        }
                                                        catch (IllegalArgumentException localIllegalArgumentException33)
                                                        {
                                                          try
                                                          {
                                                            E = paramCursor.getColumnIndexOrThrow("file_link");
                                                          }
                                                          catch (IllegalArgumentException localIllegalArgumentException33)
                                                          {
                                                            try
                                                            {
                                                              F = paramCursor.getColumnIndexOrThrow("m_size");
                                                            }
                                                            catch (IllegalArgumentException localIllegalArgumentException33)
                                                            {
                                                              try
                                                              {
                                                                G = paramCursor.getColumnIndexOrThrow("exp");
                                                              }
                                                              catch (IllegalArgumentException localIllegalArgumentException33)
                                                              {
                                                                try
                                                                {
                                                                  H = paramCursor.getColumnIndexOrThrow("t_rate");
                                                                }
                                                                catch (IllegalArgumentException localIllegalArgumentException33)
                                                                {
                                                                  try
                                                                  {
                                                                    I = paramCursor.getColumnIndexOrThrow("group_msg_id");
                                                                  }
                                                                  catch (IllegalArgumentException localIllegalArgumentException33)
                                                                  {
                                                                    try
                                                                    {
                                                                      J = paramCursor.getColumnIndexOrThrow("is_favorite");
                                                                    }
                                                                    catch (IllegalArgumentException localIllegalArgumentException33)
                                                                    {
                                                                      try
                                                                      {
                                                                        for (;;)
                                                                        {
                                                                          K = paramCursor.getColumnIndexOrThrow("sim_id");
                                                                          try
                                                                          {
                                                                            L = paramCursor.getColumnIndexOrThrow("imsi");
                                                                            return;
                                                                          }
                                                                          catch (IllegalArgumentException paramCursor)
                                                                          {
                                                                            Log.w("colsMap", paramCursor.getMessage());
                                                                          }
                                                                          localIllegalArgumentException1 = localIllegalArgumentException1;
                                                                          Log.w("colsMap", localIllegalArgumentException1.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException2 = localIllegalArgumentException2;
                                                                          Log.w("colsMap", localIllegalArgumentException2.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException3 = localIllegalArgumentException3;
                                                                          Log.w("colsMap", localIllegalArgumentException3.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException4 = localIllegalArgumentException4;
                                                                          Log.w("colsMap", localIllegalArgumentException4.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException5 = localIllegalArgumentException5;
                                                                          Log.w("colsMap", localIllegalArgumentException5.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException6 = localIllegalArgumentException6;
                                                                          Log.w("colsMap", localIllegalArgumentException6.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException7 = localIllegalArgumentException7;
                                                                          Log.w("colsMap", localIllegalArgumentException7.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException8 = localIllegalArgumentException8;
                                                                          Log.w("colsMap", localIllegalArgumentException8.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException9 = localIllegalArgumentException9;
                                                                          Log.w("colsMap", localIllegalArgumentException9.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException10 = localIllegalArgumentException10;
                                                                          Log.w("colsMap", localIllegalArgumentException10.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException11 = localIllegalArgumentException11;
                                                                          Log.w("colsMap", localIllegalArgumentException11.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException12 = localIllegalArgumentException12;
                                                                          Log.w("colsMap", localIllegalArgumentException12.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException13 = localIllegalArgumentException13;
                                                                          Log.w("colsMap", localIllegalArgumentException13.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException14 = localIllegalArgumentException14;
                                                                          Log.w("colsMap", localIllegalArgumentException14.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException15 = localIllegalArgumentException15;
                                                                          Log.w("colsMap", localIllegalArgumentException15.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException16 = localIllegalArgumentException16;
                                                                          Log.w("colsMap", localIllegalArgumentException16.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException17 = localIllegalArgumentException17;
                                                                          Log.w("colsMap", localIllegalArgumentException17.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException18 = localIllegalArgumentException18;
                                                                          Log.w("colsMap", localIllegalArgumentException18.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException19 = localIllegalArgumentException19;
                                                                          Log.w("colsMap", localIllegalArgumentException19.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException20 = localIllegalArgumentException20;
                                                                          Log.w("colsMap", localIllegalArgumentException20.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException21 = localIllegalArgumentException21;
                                                                          Log.w("colsMap", localIllegalArgumentException21.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException22 = localIllegalArgumentException22;
                                                                          Log.w("colsMap", localIllegalArgumentException22.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException23 = localIllegalArgumentException23;
                                                                          Log.w("colsMap", localIllegalArgumentException23.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException24 = localIllegalArgumentException24;
                                                                          Log.w("colsMap", localIllegalArgumentException24.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException25 = localIllegalArgumentException25;
                                                                          Log.w("colsMap", localIllegalArgumentException25.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException26 = localIllegalArgumentException26;
                                                                          Log.w("colsMap", localIllegalArgumentException26.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException27 = localIllegalArgumentException27;
                                                                          Log.w("colsMap", localIllegalArgumentException27.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException28 = localIllegalArgumentException28;
                                                                          Log.w("colsMap", localIllegalArgumentException28.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException29 = localIllegalArgumentException29;
                                                                          Log.w("colsMap", localIllegalArgumentException29.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException30 = localIllegalArgumentException30;
                                                                          Log.w("colsMap", localIllegalArgumentException30.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException31 = localIllegalArgumentException31;
                                                                          Log.w("colsMap", localIllegalArgumentException31.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException32 = localIllegalArgumentException32;
                                                                          Log.w("colsMap", localIllegalArgumentException32.getMessage());
                                                                          continue;
                                                                          localIllegalArgumentException33 = localIllegalArgumentException33;
                                                                          Log.w("colsMap", localIllegalArgumentException33.getMessage());
                                                                        }
                                                                      }
                                                                      catch (IllegalArgumentException localIllegalArgumentException34)
                                                                      {
                                                                        for (;;)
                                                                        {
                                                                          Log.w("colsMap", localIllegalArgumentException34.getMessage());
                                                                        }
                                                                      }
                                                                    }
                                                                  }
                                                                }
                                                              }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     vx.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */