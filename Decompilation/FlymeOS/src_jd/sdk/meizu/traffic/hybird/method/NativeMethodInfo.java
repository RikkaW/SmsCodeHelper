package sdk.meizu.traffic.hybird.method;

import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sdk.meizu.traffic.hybird.exception.NativeMethodError;
import sdk.meizu.traffic.hybird.exception.NativeParseError;

public class NativeMethodInfo
{
  private String callback;
  private boolean hasCallback = false;
  private Method method;
  private Object[] parameters;
  private INativeInterface target;
  
  public NativeMethodInfo(INativeInterface paramINativeInterface, Method paramMethod, String paramString)
  {
    target = paramINativeInterface;
    method = paramMethod;
    callback = paramString;
    hasCallback = hasCallBack(paramString);
  }
  
  private boolean hasCallBack(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(paramString))
    {
      bool1 = bool2;
      if (!"undefined".equals(paramString)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void parseParameters(String paramString)
  {
    Class[] arrayOfClass = method.getParameterTypes();
    Annotation[][] arrayOfAnnotation = method.getParameterAnnotations();
    int i = arrayOfClass.length;
    parameters = new Object[i];
    if (hasCallback)
    {
      parameters[(i - 1)] = callback;
      i -= 1;
    }
    for (;;)
    {
      if (i > 0)
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        Object localObject1 = new Annotation[i];
        int j = 0;
        while (j < i)
        {
          localObject1 = arrayOfClass[j];
          Annotation[] arrayOfAnnotation1 = arrayOfAnnotation[j];
          if ((arrayOfAnnotation1 != null) && (arrayOfAnnotation1.length != 0))
          {
            int m = arrayOfAnnotation1.length;
            int k = 0;
            if (k < m)
            {
              Object localObject2 = arrayOfAnnotation1[k];
              if (localObject2 != null)
              {
                Class localClass = ((Annotation)localObject2).annotationType();
                if (localClass != Parameter.class) {
                  throw new NativeParseError("The Annotation Type of the Parameter can't be " + localClass.getSimpleName());
                }
                localObject2 = ((Parameter)localObject2).value();
                if (localJSONObject.has((String)localObject2)) {
                  if (localObject1 == String.class) {
                    parameters[j] = localJSONObject.getString((String)localObject2);
                  }
                }
                for (;;)
                {
                  k += 1;
                  break;
                  if (localObject1 == Boolean.TYPE)
                  {
                    parameters[j] = Boolean.valueOf(localJSONObject.getBoolean((String)localObject2));
                  }
                  else if (localObject1 == JSONObject.class)
                  {
                    parameters[j] = localJSONObject.getJSONObject((String)localObject2);
                  }
                  else if (localObject1 == JSONArray.class)
                  {
                    parameters[j] = localJSONObject.getJSONArray((String)localObject2);
                    continue;
                    parameters[j] = null;
                  }
                }
              }
              throw new NativeParseError("The Annotation Type of the Parameter required!");
            }
          }
          else
          {
            parameters[j] = paramString;
          }
          j += 1;
        }
      }
      return;
    }
  }
  
  public void invoke(String paramString)
  {
    try
    {
      parseParameters(paramString);
      if ((parameters == null) || (parameters.length == 0))
      {
        method.invoke(target, new Object[0]);
        return;
      }
      method.invoke(target, parameters);
      return;
    }
    catch (JSONException paramString)
    {
      throw new NativeMethodError(paramString);
    }
    catch (IllegalAccessException paramString)
    {
      throw new NativeMethodError(paramString);
    }
    catch (InvocationTargetException paramString)
    {
      throw new NativeMethodError(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.NativeMethodInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */