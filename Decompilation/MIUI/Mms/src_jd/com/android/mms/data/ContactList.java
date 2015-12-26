package com.android.mms.data;

import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.AddressUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ContactList
  extends ArrayList<Contact>
{
  private static final long serialVersionUID = 1L;
  private Set<Contact> mContactSet = new HashSet();
  
  public static ContactList blockingGetByUris(Parcelable[] paramArrayOfParcelable)
  {
    ContactList localContactList = new ContactList();
    if ((paramArrayOfParcelable != null) && (paramArrayOfParcelable.length > 0))
    {
      int j = paramArrayOfParcelable.length;
      int i = 0;
      while (i < j)
      {
        Uri localUri = (Uri)paramArrayOfParcelable[i];
        if ("tel".equals(localUri.getScheme())) {
          localContactList.add(Contact.get(localUri.getSchemeSpecificPart()));
        }
        i += 1;
      }
      paramArrayOfParcelable = Contact.getByPhoneUris(paramArrayOfParcelable);
      if (paramArrayOfParcelable != null) {
        localContactList.addAll(paramArrayOfParcelable);
      }
    }
    return localContactList;
  }
  
  public static ContactList getByIds(String paramString)
  {
    ContactList localContactList = new ContactList();
    paramString = RecipientIdCache.getAddresses(paramString).iterator();
    while (paramString.hasNext())
    {
      RecipientIdCache.Entry localEntry = (RecipientIdCache.Entry)paramString.next();
      if ((localEntry != null) && (!TextUtils.isEmpty(number)))
      {
        Contact localContact = Contact.get(number);
        localContact.setRecipientId(id);
        localContactList.add(localContact);
      }
    }
    return localContactList;
  }
  
  public static ContactList getByNumbers(Iterable<String> paramIterable)
  {
    ContactList localContactList = new ContactList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      String str = (String)paramIterable.next();
      if (!TextUtils.isEmpty(str)) {
        localContactList.add(Contact.get(str));
      }
    }
    return localContactList;
  }
  
  public static ContactList getByNumbers(String paramString, boolean paramBoolean)
  {
    ContactList localContactList = new ContactList();
    paramString = paramString.split(";");
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      CharSequence localCharSequence = paramString[i];
      if (!TextUtils.isEmpty(localCharSequence))
      {
        Contact localContact = Contact.get(localCharSequence);
        if (paramBoolean) {
          localContact.setNumber(localCharSequence);
        }
        localContactList.add(localContact);
      }
      i += 1;
    }
    return localContactList;
  }
  
  public void add(int paramInt, Contact paramContact)
  {
    mContactSet.add(paramContact);
    super.add(paramInt, paramContact);
  }
  
  public boolean add(Contact paramContact)
  {
    mContactSet.add(paramContact);
    return super.add(paramContact);
  }
  
  public boolean addAll(int paramInt, Collection<? extends Contact> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Contact localContact = (Contact)localIterator.next();
      mContactSet.add(localContact);
    }
    return super.addAll(paramInt, paramCollection);
  }
  
  public boolean addAll(Collection<? extends Contact> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Contact localContact = (Contact)localIterator.next();
      mContactSet.add(localContact);
    }
    return super.addAll(paramCollection);
  }
  
  public void clear()
  {
    mContactSet.clear();
    super.clear();
  }
  
  public boolean containsEmail()
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      if (((Contact)localIterator.next()).isEmail()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject == null) || (getClass() != paramObject.getClass())) {
      return false;
    }
    paramObject = (ContactList)paramObject;
    return mContactSet.equals(mContactSet);
  }
  
  public int formatNames(char[] paramArrayOfChar, String paramString)
  {
    int i = 0;
    Iterator localIterator = iterator();
    int j;
    do
    {
      j = i;
      if (localIterator.hasNext())
      {
        localObject = (Contact)localIterator.next();
        j = i;
        if (i > 0)
        {
          k = paramString.length();
          j = k;
          if (i + k > paramArrayOfChar.length) {
            j = paramArrayOfChar.length - i;
          }
          paramString.getChars(0, j, paramArrayOfChar, i);
          i += j;
          j = i;
          if (i == paramArrayOfChar.length) {
            j = i;
          }
        }
      }
      else
      {
        return j;
      }
      Object localObject = ((Contact)localObject).getName();
      int k = ((String)localObject).length();
      i = k;
      if (j + k > paramArrayOfChar.length) {
        i = paramArrayOfChar.length - j;
      }
      ((String)localObject).getChars(0, i, paramArrayOfChar, j);
      j += i;
      i = j;
    } while (j != paramArrayOfChar.length);
    return j;
  }
  
  public String formatNames(String paramString)
  {
    String[] arrayOfString = new String[size()];
    int i = 0;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      arrayOfString[i] = ((Contact)localIterator.next()).getName();
      i += 1;
    }
    return TextUtils.join(paramString, arrayOfString);
  }
  
  public int formatTags(char[] paramArrayOfChar)
  {
    if (size() != 1)
    {
      paramArrayOfChar[0] = '\000';
      return 0;
    }
    String str = ((Contact)get(0)).getDisplayTag();
    int i = str.length();
    if (i == 0)
    {
      paramArrayOfChar[0] = '\000';
      return 0;
    }
    paramArrayOfChar[0] = '|';
    paramArrayOfChar[1] = ' ';
    str.getChars(0, i, paramArrayOfChar, 2);
    paramArrayOfChar[(i + 2)] = '\000';
    return i + 2;
  }
  
  public String[] getNumbers()
  {
    return getNumbers(false);
  }
  
  public String[] getNumbers(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      String str2 = ((Contact)localIterator.next()).getNumber();
      String str1 = str2;
      if (paramBoolean) {
        str1 = MessageUtils.parseMmsAddress(str2);
      }
      if ((!TextUtils.isEmpty(str1)) && (!localArrayList.contains(str1))) {
        localArrayList.add(str1);
      }
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  public ArrayList<String> getSortedCompareKeys()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < size())
    {
      localArrayList.add(((Contact)get(i)).getCompareKey());
      i += 1;
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  public int hashCode()
  {
    return mContactSet.hashCode();
  }
  
  public void load(boolean paramBoolean1, boolean paramBoolean2)
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      ((Contact)localIterator.next()).load(paramBoolean1, paramBoolean2);
    }
  }
  
  public Contact remove(int paramInt)
  {
    Contact localContact = (Contact)super.remove(paramInt);
    mContactSet.remove(localContact);
    return localContact;
  }
  
  public boolean remove(Object paramObject)
  {
    mContactSet.remove(paramObject);
    return super.remove(paramObject);
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    mContactSet.removeAll(paramCollection);
    return super.removeAll(paramCollection);
  }
  
  public String serialize()
  {
    return TextUtils.join(";", getNumbers());
  }
  
  public Contact set(int paramInt, Contact paramContact)
  {
    Contact localContact = (Contact)super.set(paramInt, paramContact);
    mContactSet.remove(localContact);
    mContactSet.add(paramContact);
    return localContact;
  }
  
  public String toString()
  {
    ArrayList localArrayList = getSortedCompareKeys();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    int i = 0;
    while (i < size())
    {
      if (i != 0) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append(AddressUtils.cutPhoneNumberTail((String)localArrayList.get(i)));
      i += 1;
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.ContactList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */