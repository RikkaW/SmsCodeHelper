/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public interface ann
extends IInterface {
    public void a(Intent var1);

    public void b(Intent var1);

    public static abstract class ann$a
    extends Binder
    implements ann {
        public ann$a() {
            this.attachInterface((IInterface)this, "com.meizu.voiceassistant.support.IVoiceAssistantCallback");
        }

        public static ann a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.meizu.voiceassistant.support.IVoiceAssistantCallback");
            if (iInterface != null && iInterface instanceof ann) {
                return (ann)iInterface;
            }
            return new a(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int n2, Parcel parcel, Parcel parcel2, int n3) {
            Intent intent = null;
            Intent intent2 = null;
            switch (n2) {
                default: {
                    return super.onTransact(n2, parcel, parcel2, n3);
                }
                case 1598968902: {
                    parcel2.writeString("com.meizu.voiceassistant.support.IVoiceAssistantCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantCallback");
                    if (parcel.readInt() != 0) {
                        intent2 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                    }
                    this.a(intent2);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: 
            }
            parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantCallback");
            intent2 = intent;
            if (parcel.readInt() != 0) {
                intent2 = (Intent)Intent.CREATOR.createFromParcel(parcel);
            }
            this.b(intent2);
            parcel2.writeNoException();
            return true;
        }

        static class a
        implements ann {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void a(Intent intent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantCallback");
                    if (intent != null) {
                        parcel.writeInt(1);
                        intent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
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

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void b(Intent intent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantCallback");
                    if (intent != null) {
                        parcel.writeInt(1);
                        intent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    this.a.transact(2, parcel, parcel2, 0);
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

