import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class anp$a
  extends Binder
  implements anp
{
  public static anp a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
    if ((localIInterface != null) && ((localIInterface instanceof anp))) {
      return (anp)localIInterface;
    }
    return new anp.a.a(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
      a();
      paramParcel2.writeNoException();
      return true;
    case 2: 
      paramParcel1.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
      b();
      paramParcel2.writeNoException();
      return true;
    }
    paramParcel1.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
    c();
    paramParcel2.writeNoException();
    return true;
  }
  
  static class a
    implements anp
  {
    private IBinder a;
    
    a(IBinder paramIBinder)
    {
      a = paramIBinder;
    }
    
    public void a()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
        a.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public IBinder asBinder()
    {
      return a;
    }
    
    public void b()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
        a.transact(2, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void c()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
        a.transact(3, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
  }
}

/* Location:
 * Qualified Name:     anp.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */