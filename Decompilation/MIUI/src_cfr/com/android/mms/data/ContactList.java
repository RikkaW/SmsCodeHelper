/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  android.os.Parcelable
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.HashSet
 */
package com.android.mms.data;

import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.android.mms.data.RecipientIdCache;
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
extends ArrayList<Contact> {
    private static final long serialVersionUID = 1;
    private Set<Contact> mContactSet = new HashSet();

    public static ContactList blockingGetByUris(Parcelable[] object) {
        ContactList contactList = new ContactList();
        if (object != null && object.length > 0) {
            int n = object.length;
            for (int i = 0; i < n; ++i) {
                Uri uri = (Uri)object[i];
                if (!"tel".equals((Object)uri.getScheme())) continue;
                contactList.add(Contact.get(uri.getSchemeSpecificPart()));
            }
            if ((object = Contact.getByPhoneUris((Parcelable[])object)) != null) {
                contactList.addAll((Collection<? extends Contact>)object);
            }
        }
        return contactList;
    }

    public static ContactList getByIds(String object) {
        ContactList contactList = new ContactList();
        for (RecipientIdCache.Entry entry : RecipientIdCache.getAddresses((String)object)) {
            if (entry == null || TextUtils.isEmpty((CharSequence)entry.number)) continue;
            Contact contact = Contact.get(entry.number);
            contact.setRecipientId(entry.id);
            contactList.add(contact);
        }
        return contactList;
    }

    public static ContactList getByNumbers(Iterable<String> object) {
        ContactList contactList = new ContactList();
        object = object.iterator();
        while (object.hasNext()) {
            String string = (String)object.next();
            if (TextUtils.isEmpty((CharSequence)string)) continue;
            contactList.add(Contact.get(string));
        }
        return contactList;
    }

    public static ContactList getByNumbers(String arrstring, boolean bl) {
        ContactList contactList = new ContactList();
        for (String string : arrstring.split(";")) {
            if (TextUtils.isEmpty((CharSequence)string)) continue;
            Contact contact = Contact.get(string);
            if (bl) {
                contact.setNumber(string);
            }
            contactList.add(contact);
        }
        return contactList;
    }

    public void add(int n, Contact contact) {
        this.mContactSet.add(contact);
        super.add(n, (Object)contact);
    }

    public boolean add(Contact contact) {
        this.mContactSet.add(contact);
        return super.add((Object)contact);
    }

    public boolean addAll(int n, Collection<? extends Contact> collection) {
        for (Contact contact : collection) {
            this.mContactSet.add(contact);
        }
        return super.addAll(n, collection);
    }

    public boolean addAll(Collection<? extends Contact> collection) {
        for (Contact contact : collection) {
            this.mContactSet.add(contact);
        }
        return super.addAll(collection);
    }

    public void clear() {
        this.mContactSet.clear();
        super.clear();
    }

    public boolean containsEmail() {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!((Contact)iterator.next()).isEmail()) continue;
            return true;
        }
        return false;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        object = (ContactList)((Object)object);
        return this.mContactSet.equals(object.mContactSet);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int formatNames(char[] arrc, String string) {
        int n;
        int n2 = 0;
        Iterator iterator = this.iterator();
        do {
            int n3;
            n = n2;
            if (!iterator.hasNext()) return n;
            Object object = (Contact)iterator.next();
            n = n2;
            if (n2 > 0) {
                n = n3 = string.length();
                if (n2 + n3 > arrc.length) {
                    n = arrc.length - n2;
                }
                string.getChars(0, n, arrc, n2);
                n = n2 += n;
                if (n2 == arrc.length) {
                    return n2;
                }
            }
            object = object.getName();
            n2 = n3 = object.length();
            if (n + n3 > arrc.length) {
                n2 = arrc.length - n;
            }
            object.getChars(0, n2, arrc, n);
            n2 = n += n2;
        } while (n != arrc.length);
        return n;
    }

    public String formatNames(String string) {
        Object[] arrobject = new String[this.size()];
        int n = 0;
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            arrobject[n] = ((Contact)iterator.next()).getName();
            ++n;
        }
        return TextUtils.join((CharSequence)string, (Object[])arrobject);
    }

    public int formatTags(char[] arrc) {
        if (this.size() != 1) {
            arrc[0] = '\u0000';
            return 0;
        }
        String string = ((Contact)this.get(0)).getDisplayTag();
        int n = string.length();
        if (n == 0) {
            arrc[0] = '\u0000';
            return 0;
        }
        arrc[0] = 124;
        arrc[1] = 32;
        string.getChars(0, n, arrc, 2);
        arrc[n + 2] = '\u0000';
        return n + 2;
    }

    public String[] getNumbers() {
        return this.getNumbers(false);
    }

    public String[] getNumbers(boolean bl) {
        ArrayList arrayList = new ArrayList();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            String string;
            String string2 = string = ((Contact)iterator.next()).getNumber();
            if (bl) {
                string2 = MessageUtils.parseMmsAddress(string);
            }
            if (TextUtils.isEmpty((CharSequence)string2) || arrayList.contains(string2)) continue;
            arrayList.add(string2);
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public ArrayList<String> getSortedCompareKeys() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.size(); ++i) {
            arrayList.add((Object)((Contact)this.get(i)).getCompareKey());
        }
        Collections.sort((List)arrayList);
        return arrayList;
    }

    public int hashCode() {
        return this.mContactSet.hashCode();
    }

    public void load(boolean bl, boolean bl2) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            ((Contact)iterator.next()).load(bl, bl2);
        }
    }

    public Contact remove(int n) {
        Contact contact = (Contact)super.remove(n);
        this.mContactSet.remove(contact);
        return contact;
    }

    public boolean remove(Object object) {
        this.mContactSet.remove(object);
        return super.remove(object);
    }

    public boolean removeAll(Collection<?> collection) {
        this.mContactSet.removeAll(collection);
        return super.removeAll(collection);
    }

    public String serialize() {
        return TextUtils.join((CharSequence)";", (Object[])this.getNumbers());
    }

    public Contact set(int n, Contact contact) {
        Contact contact2 = (Contact)super.set(n, (Object)contact);
        this.mContactSet.remove(contact2);
        this.mContactSet.add(contact);
        return contact2;
    }

    public String toString() {
        ArrayList<String> arrayList = this.getSortedCompareKeys();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < this.size(); ++i) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(AddressUtils.cutPhoneNumberTail((String)arrayList.get(i)));
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

