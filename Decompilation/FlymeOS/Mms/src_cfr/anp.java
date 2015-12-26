/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  java.lang.Object
 *  java.lang.String
 */
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface anp
extends IInterface {
    public void a();

    public void b();

    public void c();

    public static abstract class anp$a
    extends Binder
    implements anp {
        public static anp a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
            if (iInterface != null && iInterface instanceof anp) {
                return (anp)iInterface;
            }
            return new a(iBinder);
        }

        public boolean onTransact(int n2, Parcel parcel, Parcel parcel2, int n3) {
            switch (n2) {
                default: {
                    return super.onTransact(n2, parcel, parcel2, n3);
                }
                case 1598968902: {
                    parcel2.writeString("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
                    this.a();
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
                    this.b();
                    parcel2.writeNoException();
                    return true;
                }
                case 3: 
            }
            parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
            this.c();
            parcel2.writeNoException();
            return true;
        }

        static class a
        implements anp {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override
            public void a() {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
                    this.a.transact(1, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            public IBinder asBinder() {
                return this.a;
            }

            @Override
            public void b() {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
                    this.a.transact(2, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void c() {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantSpeakCallback");
                    this.a.transact(3, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }
        }

    }

}

