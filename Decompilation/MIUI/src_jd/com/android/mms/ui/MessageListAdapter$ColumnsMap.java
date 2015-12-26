package com.android.mms.ui;

import android.database.Cursor;
import android.util.Log;

public class MessageListAdapter$ColumnsMap
{
  public int mColumnCount;
  public int mColumnMmsBlockType;
  public int mColumnMmsContentLocation;
  public int mColumnMmsDate;
  public int mColumnMmsDateMsPart;
  public int mColumnMmsDateSent;
  public int mColumnMmsDeliveryReport;
  public int mColumnMmsErrorType;
  public int mColumnMmsExpiry;
  public int mColumnMmsLocked;
  public int mColumnMmsMessageBox;
  public int mColumnMmsMessageSize;
  public int mColumnMmsMessageType;
  public int mColumnMmsMxType;
  public int mColumnMmsNeedDownload;
  public int mColumnMmsPreviewDataTs;
  public int mColumnMmsPreviewType;
  public int mColumnMmsReadReport;
  public int mColumnMmsSnippet;
  public int mColumnMmsStatus;
  public int mColumnMmsSubject;
  public int mColumnMmsSubjectCharset;
  public int mColumnMmsTimed;
  public int mColumnMsgId;
  public int mColumnMsgType;
  public int mColumnMx2Type;
  public int mColumnMxExtension;
  public int mColumnSimId;
  public int mColumnSmsAddress;
  public int mColumnSmsBlockType;
  public int mColumnSmsBody;
  public int mColumnSmsDate;
  public int mColumnSmsDateSent;
  public int mColumnSmsErrorCode;
  public int mColumnSmsLocked;
  public int mColumnSmsMxType;
  public int mColumnSmsStatus;
  public int mColumnSmsTimed;
  public int mColumnSmsType;
  public int mColumnThreadId;
  
  public MessageListAdapter$ColumnsMap()
  {
    mColumnMsgType = 0;
    mColumnMsgId = 1;
    mColumnThreadId = 2;
    mColumnSmsAddress = 3;
    mColumnSmsBody = 4;
    mColumnSmsDate = 5;
    mColumnSmsDateSent = 6;
    mColumnSmsType = 8;
    mColumnSmsStatus = 9;
    mColumnSmsLocked = 10;
    mColumnSmsErrorCode = 11;
    mColumnSmsTimed = 12;
    mColumnSmsMxType = 13;
    mColumnSmsBlockType = 14;
    mColumnMmsSubject = 15;
    mColumnMmsSubjectCharset = 16;
    mColumnMmsDate = 17;
    mColumnMmsDateSent = 18;
    mColumnMmsMessageType = 20;
    mColumnMmsMessageBox = 21;
    mColumnMmsDeliveryReport = 22;
    mColumnMmsReadReport = 23;
    mColumnMmsErrorType = 24;
    mColumnMmsLocked = 25;
    mColumnMmsContentLocation = 26;
    mColumnMmsMessageSize = 27;
    mColumnMmsExpiry = 28;
    mColumnMmsTimed = 29;
    mColumnMmsDateMsPart = 30;
    mColumnMmsMxType = 31;
    mColumnMmsNeedDownload = 32;
    mColumnMmsSnippet = 33;
    mColumnMmsPreviewType = 34;
    mColumnMmsPreviewDataTs = 35;
    mColumnMmsBlockType = 38;
    mColumnSimId = 36;
    mColumnMmsStatus = 37;
    mColumnMx2Type = 39;
    mColumnCount = 41;
    mColumnMxExtension = 40;
  }
  
  public MessageListAdapter$ColumnsMap(Cursor paramCursor)
  {
    try
    {
      mColumnMsgType = paramCursor.getColumnIndexOrThrow("transport_type");
    }
    catch (IllegalArgumentException localIllegalArgumentException37)
    {
      try
      {
        mColumnMsgId = paramCursor.getColumnIndexOrThrow("_id");
      }
      catch (IllegalArgumentException localIllegalArgumentException37)
      {
        try
        {
          mColumnThreadId = paramCursor.getColumnIndexOrThrow("thread_id");
        }
        catch (IllegalArgumentException localIllegalArgumentException37)
        {
          try
          {
            mColumnSmsAddress = paramCursor.getColumnIndexOrThrow("address");
          }
          catch (IllegalArgumentException localIllegalArgumentException37)
          {
            try
            {
              mColumnSmsBody = paramCursor.getColumnIndexOrThrow("body");
            }
            catch (IllegalArgumentException localIllegalArgumentException37)
            {
              try
              {
                mColumnSmsDate = paramCursor.getColumnIndexOrThrow("date");
              }
              catch (IllegalArgumentException localIllegalArgumentException37)
              {
                try
                {
                  mColumnSmsDateSent = paramCursor.getColumnIndexOrThrow("date_sent");
                }
                catch (IllegalArgumentException localIllegalArgumentException37)
                {
                  try
                  {
                    mColumnSmsType = paramCursor.getColumnIndexOrThrow("type");
                  }
                  catch (IllegalArgumentException localIllegalArgumentException37)
                  {
                    try
                    {
                      mColumnSmsStatus = paramCursor.getColumnIndexOrThrow("status");
                    }
                    catch (IllegalArgumentException localIllegalArgumentException37)
                    {
                      try
                      {
                        mColumnSmsLocked = paramCursor.getColumnIndexOrThrow("locked");
                      }
                      catch (IllegalArgumentException localIllegalArgumentException37)
                      {
                        try
                        {
                          mColumnSmsErrorCode = paramCursor.getColumnIndexOrThrow("error_code");
                        }
                        catch (IllegalArgumentException localIllegalArgumentException37)
                        {
                          try
                          {
                            mColumnSmsTimed = paramCursor.getColumnIndexOrThrow("timed");
                          }
                          catch (IllegalArgumentException localIllegalArgumentException37)
                          {
                            try
                            {
                              mColumnSmsMxType = paramCursor.getColumnIndexOrThrow("mx_status");
                            }
                            catch (IllegalArgumentException localIllegalArgumentException37)
                            {
                              try
                              {
                                mColumnSmsBlockType = paramCursor.getColumnIndexOrThrow("block_type");
                              }
                              catch (IllegalArgumentException localIllegalArgumentException37)
                              {
                                try
                                {
                                  mColumnMmsSubject = paramCursor.getColumnIndexOrThrow("sub");
                                }
                                catch (IllegalArgumentException localIllegalArgumentException37)
                                {
                                  try
                                  {
                                    mColumnMmsSubjectCharset = paramCursor.getColumnIndexOrThrow("sub_cs");
                                  }
                                  catch (IllegalArgumentException localIllegalArgumentException37)
                                  {
                                    try
                                    {
                                      mColumnMmsDate = paramCursor.getColumnIndexOrThrow("date");
                                    }
                                    catch (IllegalArgumentException localIllegalArgumentException37)
                                    {
                                      try
                                      {
                                        mColumnMmsDateSent = paramCursor.getColumnIndexOrThrow("date_sent");
                                      }
                                      catch (IllegalArgumentException localIllegalArgumentException37)
                                      {
                                        try
                                        {
                                          mColumnMmsMessageType = paramCursor.getColumnIndexOrThrow("m_type");
                                        }
                                        catch (IllegalArgumentException localIllegalArgumentException37)
                                        {
                                          try
                                          {
                                            mColumnMmsMessageBox = paramCursor.getColumnIndexOrThrow("msg_box");
                                          }
                                          catch (IllegalArgumentException localIllegalArgumentException37)
                                          {
                                            try
                                            {
                                              mColumnMmsDeliveryReport = paramCursor.getColumnIndexOrThrow("d_rpt");
                                            }
                                            catch (IllegalArgumentException localIllegalArgumentException37)
                                            {
                                              try
                                              {
                                                mColumnMmsReadReport = paramCursor.getColumnIndexOrThrow("rr");
                                              }
                                              catch (IllegalArgumentException localIllegalArgumentException37)
                                              {
                                                try
                                                {
                                                  mColumnMmsErrorType = paramCursor.getColumnIndexOrThrow("err_type");
                                                }
                                                catch (IllegalArgumentException localIllegalArgumentException37)
                                                {
                                                  try
                                                  {
                                                    mColumnMmsLocked = paramCursor.getColumnIndexOrThrow("locked");
                                                  }
                                                  catch (IllegalArgumentException localIllegalArgumentException37)
                                                  {
                                                    try
                                                    {
                                                      mColumnMmsContentLocation = paramCursor.getColumnIndexOrThrow("ct_l");
                                                    }
                                                    catch (IllegalArgumentException localIllegalArgumentException37)
                                                    {
                                                      try
                                                      {
                                                        mColumnMmsMessageSize = paramCursor.getColumnIndexOrThrow("m_size");
                                                      }
                                                      catch (IllegalArgumentException localIllegalArgumentException37)
                                                      {
                                                        try
                                                        {
                                                          mColumnMmsExpiry = paramCursor.getColumnIndexOrThrow("exp");
                                                        }
                                                        catch (IllegalArgumentException localIllegalArgumentException37)
                                                        {
                                                          try
                                                          {
                                                            mColumnMmsTimed = paramCursor.getColumnIndexOrThrow("timed");
                                                          }
                                                          catch (IllegalArgumentException localIllegalArgumentException37)
                                                          {
                                                            try
                                                            {
                                                              mColumnMmsDateMsPart = paramCursor.getColumnIndexOrThrow("date_ms_part");
                                                            }
                                                            catch (IllegalArgumentException localIllegalArgumentException37)
                                                            {
                                                              try
                                                              {
                                                                mColumnMmsMxType = paramCursor.getColumnIndexOrThrow("mx_status");
                                                              }
                                                              catch (IllegalArgumentException localIllegalArgumentException37)
                                                              {
                                                                try
                                                                {
                                                                  mColumnMmsNeedDownload = paramCursor.getColumnIndexOrThrow("need_download");
                                                                }
                                                                catch (IllegalArgumentException localIllegalArgumentException37)
                                                                {
                                                                  try
                                                                  {
                                                                    mColumnMmsSnippet = paramCursor.getColumnIndexOrThrow("snippet");
                                                                  }
                                                                  catch (IllegalArgumentException localIllegalArgumentException37)
                                                                  {
                                                                    try
                                                                    {
                                                                      mColumnMmsPreviewType = paramCursor.getColumnIndexOrThrow("preview_type");
                                                                    }
                                                                    catch (IllegalArgumentException localIllegalArgumentException37)
                                                                    {
                                                                      try
                                                                      {
                                                                        mColumnMmsPreviewDataTs = paramCursor.getColumnIndexOrThrow("preview_data_ts");
                                                                      }
                                                                      catch (IllegalArgumentException localIllegalArgumentException37)
                                                                      {
                                                                        try
                                                                        {
                                                                          mColumnMmsBlockType = paramCursor.getColumnIndexOrThrow("block_type");
                                                                        }
                                                                        catch (IllegalArgumentException localIllegalArgumentException37)
                                                                        {
                                                                          try
                                                                          {
                                                                            mColumnSimId = paramCursor.getColumnIndexOrThrow("sim_id");
                                                                          }
                                                                          catch (IllegalArgumentException localIllegalArgumentException37)
                                                                          {
                                                                            try
                                                                            {
                                                                              mColumnCount = paramCursor.getColumnIndexOrThrow("count(*)");
                                                                            }
                                                                            catch (IllegalArgumentException localIllegalArgumentException37)
                                                                            {
                                                                              try
                                                                              {
                                                                                for (;;)
                                                                                {
                                                                                  mColumnMx2Type = paramCursor.getColumnIndexOrThrow("mx_type");
                                                                                  try
                                                                                  {
                                                                                    mColumnMxExtension = paramCursor.getColumnIndexOrThrow("mx_extension");
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
                                                                                  continue;
                                                                                  localIllegalArgumentException34 = localIllegalArgumentException34;
                                                                                  Log.w("colsMap", localIllegalArgumentException34.getMessage());
                                                                                  continue;
                                                                                  localIllegalArgumentException35 = localIllegalArgumentException35;
                                                                                  Log.w("colsMap", localIllegalArgumentException35.getMessage());
                                                                                  continue;
                                                                                  localIllegalArgumentException36 = localIllegalArgumentException36;
                                                                                  Log.w("colsMap", localIllegalArgumentException36.getMessage());
                                                                                  continue;
                                                                                  localIllegalArgumentException37 = localIllegalArgumentException37;
                                                                                  Log.w("colsMap", localIllegalArgumentException37.getMessage());
                                                                                }
                                                                              }
                                                                              catch (IllegalArgumentException localIllegalArgumentException38)
                                                                              {
                                                                                for (;;)
                                                                                {
                                                                                  Log.w("colsMap", localIllegalArgumentException38.getMessage());
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
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListAdapter.ColumnsMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */