package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.net.util.h;
import cn.com.xy.sms.sdk.util.XyUtil;
import org.json.JSONObject;

final class g
  implements XyCallBack
{
  g(XyCallBack paramXyCallBack, String paramString1, String paramString2, String paramString3, String paramString4) {}
  
  public final void execute(Object... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length != 2) || (!paramVarArgs[0].toString().equals("0")))
    {
      XyUtil.doXycallBackResult(a, new Object[] { b });
      return;
    }
    ContentValues localContentValues = null;
    Object localObject3 = null;
    SQLiteDatabase localSQLiteDatabase = null;
    Object localObject2 = localContentValues;
    Object localObject1 = localObject3;
    try
    {
      JSONObject localJSONObject = h.g(paramVarArgs[1].toString());
      paramVarArgs = localSQLiteDatabase;
      if (localJSONObject != null)
      {
        paramVarArgs = localSQLiteDatabase;
        localObject2 = localContentValues;
        localObject1 = localObject3;
        if (localJSONObject.length() > 0)
        {
          localObject2 = localContentValues;
          localObject1 = localObject3;
          localSQLiteDatabase = DBManager.getSQLiteDatabase();
          localObject2 = localSQLiteDatabase;
          localObject1 = localSQLiteDatabase;
          localJSONObject.put("cc", c);
          localObject2 = localSQLiteDatabase;
          localObject1 = localSQLiteDatabase;
          localContentValues = TrainManager.getContentValues(localJSONObject);
          paramVarArgs = localSQLiteDatabase;
          if (localContentValues != null)
          {
            localObject2 = localSQLiteDatabase;
            localObject1 = localSQLiteDatabase;
            BaseManager.saveOrUpdate(localSQLiteDatabase, "tb_train", localContentValues, "train_num = ? ", new String[] { c });
            localObject2 = localSQLiteDatabase;
            localObject1 = localSQLiteDatabase;
            XyUtil.doXycallBackResult(a, new Object[] { b, localJSONObject, c, d, e, Boolean.valueOf(true) });
            DBManager.close(localSQLiteDatabase);
            return;
          }
        }
      }
      localObject2 = paramVarArgs;
      localObject1 = paramVarArgs;
      XyUtil.doXycallBackResult(a, new Object[] { b });
      DBManager.close(paramVarArgs);
      return;
    }
    catch (Throwable paramVarArgs)
    {
      localObject1 = localObject2;
      paramVarArgs.printStackTrace();
      localObject1 = localObject2;
      XyUtil.doXycallBackResult(a, new Object[] { b });
      return;
    }
    finally
    {
      DBManager.close((SQLiteDatabase)localObject1);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */