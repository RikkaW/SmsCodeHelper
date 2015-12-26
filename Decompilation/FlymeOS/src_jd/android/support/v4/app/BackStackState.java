package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class BackStackState
  implements Parcelable
{
  public static final Parcelable.Creator<BackStackState> CREATOR = new BackStackState.1();
  final int mBreadCrumbShortTitleRes;
  final CharSequence mBreadCrumbShortTitleText;
  final int mBreadCrumbTitleRes;
  final CharSequence mBreadCrumbTitleText;
  final int mIndex;
  final String mName;
  final int[] mOps;
  final ArrayList<String> mSharedElementSourceNames;
  final ArrayList<String> mSharedElementTargetNames;
  final int mTransition;
  final int mTransitionStyle;
  
  public BackStackState(Parcel paramParcel)
  {
    mOps = paramParcel.createIntArray();
    mTransition = paramParcel.readInt();
    mTransitionStyle = paramParcel.readInt();
    mName = paramParcel.readString();
    mIndex = paramParcel.readInt();
    mBreadCrumbTitleRes = paramParcel.readInt();
    mBreadCrumbTitleText = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    mBreadCrumbShortTitleRes = paramParcel.readInt();
    mBreadCrumbShortTitleText = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    mSharedElementSourceNames = paramParcel.createStringArrayList();
    mSharedElementTargetNames = paramParcel.createStringArrayList();
  }
  
  public BackStackState(FragmentManagerImpl paramFragmentManagerImpl, BackStackRecord paramBackStackRecord)
  {
    paramFragmentManagerImpl = mHead;
    int j;
    for (int i = 0; paramFragmentManagerImpl != null; i = j)
    {
      j = i;
      if (removed != null) {
        j = i + removed.size();
      }
      paramFragmentManagerImpl = next;
    }
    mOps = new int[i + mNumOp * 7];
    if (!mAddToBackStack) {
      throw new IllegalStateException("Not on back stack");
    }
    paramFragmentManagerImpl = mHead;
    i = 0;
    if (paramFragmentManagerImpl != null)
    {
      int[] arrayOfInt = mOps;
      j = i + 1;
      arrayOfInt[i] = cmd;
      arrayOfInt = mOps;
      int k = j + 1;
      if (fragment != null) {}
      for (i = fragment.mIndex;; i = -1)
      {
        arrayOfInt[j] = i;
        arrayOfInt = mOps;
        i = k + 1;
        arrayOfInt[k] = enterAnim;
        arrayOfInt = mOps;
        j = i + 1;
        arrayOfInt[i] = exitAnim;
        arrayOfInt = mOps;
        i = j + 1;
        arrayOfInt[j] = popEnterAnim;
        arrayOfInt = mOps;
        j = i + 1;
        arrayOfInt[i] = popExitAnim;
        if (removed == null) {
          break label311;
        }
        k = removed.size();
        arrayOfInt = mOps;
        i = j + 1;
        arrayOfInt[j] = k;
        j = 0;
        while (j < k)
        {
          mOps[i] = removed.get(j)).mIndex;
          j += 1;
          i += 1;
        }
      }
      for (;;)
      {
        paramFragmentManagerImpl = next;
        break;
        label311:
        arrayOfInt = mOps;
        i = j + 1;
        arrayOfInt[j] = 0;
      }
    }
    mTransition = mTransition;
    mTransitionStyle = mTransitionStyle;
    mName = mName;
    mIndex = mIndex;
    mBreadCrumbTitleRes = mBreadCrumbTitleRes;
    mBreadCrumbTitleText = mBreadCrumbTitleText;
    mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
    mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
    mSharedElementSourceNames = mSharedElementSourceNames;
    mSharedElementTargetNames = mSharedElementTargetNames;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public BackStackRecord instantiate(FragmentManagerImpl paramFragmentManagerImpl)
  {
    BackStackRecord localBackStackRecord = new BackStackRecord(paramFragmentManagerImpl);
    int k = 0;
    int i = 0;
    while (i < mOps.length)
    {
      BackStackRecord.Op localOp = new BackStackRecord.Op();
      Object localObject = mOps;
      int j = i + 1;
      cmd = localObject[i];
      if (FragmentManagerImpl.DEBUG) {
        Log.v("FragmentManager", "Instantiate " + localBackStackRecord + " op #" + k + " base fragment #" + mOps[j]);
      }
      localObject = mOps;
      i = j + 1;
      j = localObject[j];
      if (j >= 0) {}
      for (fragment = ((Fragment)mActive.get(j));; fragment = null)
      {
        localObject = mOps;
        j = i + 1;
        enterAnim = localObject[i];
        localObject = mOps;
        i = j + 1;
        exitAnim = localObject[j];
        localObject = mOps;
        j = i + 1;
        popEnterAnim = localObject[i];
        localObject = mOps;
        i = j + 1;
        popExitAnim = localObject[j];
        localObject = mOps;
        j = i + 1;
        int n = localObject[i];
        i = j;
        if (n <= 0) {
          break;
        }
        removed = new ArrayList(n);
        int m = 0;
        for (;;)
        {
          i = j;
          if (m >= n) {
            break;
          }
          if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Instantiate " + localBackStackRecord + " set remove fragment #" + mOps[j]);
          }
          localObject = (Fragment)mActive.get(mOps[j]);
          removed.add(localObject);
          m += 1;
          j += 1;
        }
      }
      localBackStackRecord.addOp(localOp);
      k += 1;
    }
    mTransition = mTransition;
    mTransitionStyle = mTransitionStyle;
    mName = mName;
    mIndex = mIndex;
    mAddToBackStack = true;
    mBreadCrumbTitleRes = mBreadCrumbTitleRes;
    mBreadCrumbTitleText = mBreadCrumbTitleText;
    mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
    mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
    mSharedElementSourceNames = mSharedElementSourceNames;
    mSharedElementTargetNames = mSharedElementTargetNames;
    localBackStackRecord.bumpBackStackNesting(1);
    return localBackStackRecord;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeIntArray(mOps);
    paramParcel.writeInt(mTransition);
    paramParcel.writeInt(mTransitionStyle);
    paramParcel.writeString(mName);
    paramParcel.writeInt(mIndex);
    paramParcel.writeInt(mBreadCrumbTitleRes);
    TextUtils.writeToParcel(mBreadCrumbTitleText, paramParcel, 0);
    paramParcel.writeInt(mBreadCrumbShortTitleRes);
    TextUtils.writeToParcel(mBreadCrumbShortTitleText, paramParcel, 0);
    paramParcel.writeStringList(mSharedElementSourceNames);
    paramParcel.writeStringList(mSharedElementTargetNames);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.BackStackState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */