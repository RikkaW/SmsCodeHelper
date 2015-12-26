/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  java.io.FileNotFoundException
 *  java.io.PrintStream
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.Hashtable
 *  java.util.Map$Entry
 *  java.util.Properties
 *  java.util.Vector
 *  javax.xml.parsers.DocumentBuilder
 *  javax.xml.parsers.DocumentBuilderFactory
 */
package com.android.mms.jwap_port;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TokenRepository {
    private static byte CODEPAGE_DEFAULT;
    private static byte currentCodepage;
    private static Properties urnMappings;
    private Hashtable[][] codepages;
    Context mContext;
    private Document tokenDoc;

    static {
        currentCodepage = TokenRepository.CODEPAGE_DEFAULT = 0;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public TokenRepository(String object, Context context) {
        Object object2;
        Object object3;
        InputStream inputStream2;
        InputStream inputStream;
        block17 : {
            InputStream inputStream3 = null;
            InputStream inputStream4 = null;
            InputStream inputStream5 = null;
            InputStream inputStream6 = null;
            inputStream2 = inputStream4;
            object3 = inputStream6;
            inputStream = inputStream3;
            object2 = inputStream5;
            this.mContext = context;
            inputStream2 = inputStream4;
            object3 = inputStream6;
            inputStream = inputStream3;
            object2 = inputStream5;
            Properties properties = new Properties();
            inputStream2 = inputStream4;
            object3 = inputStream6;
            inputStream = inputStream3;
            object2 = inputStream5;
            inputStream2 = inputStream4 = context.getAssets().open("jwap_port/tokenRepositoryMappings.properties");
            object3 = inputStream6;
            inputStream = inputStream4;
            object2 = inputStream5;
            properties.load(inputStream4);
            inputStream2 = inputStream4;
            object3 = inputStream6;
            inputStream = inputStream4;
            object2 = inputStream5;
            object = properties.getProperty((String)object);
            inputStream2 = inputStream4;
            object3 = inputStream6;
            inputStream = inputStream4;
            object2 = inputStream5;
            object = context.getAssets().open((String)object);
            inputStream2 = inputStream4;
            object3 = object;
            inputStream = inputStream4;
            object2 = object;
            this.tokenDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse((InputStream)object);
            inputStream2 = inputStream4;
            object3 = object;
            inputStream = inputStream4;
            object2 = object;
            this.initializeURNMappings();
            inputStream2 = inputStream4;
            object3 = object;
            inputStream = inputStream4;
            object2 = object;
            this.initializeHeaderInfo();
            inputStream2 = inputStream4;
            object3 = object;
            inputStream = inputStream4;
            object2 = object;
            currentCodepage = CODEPAGE_DEFAULT;
            if (inputStream4 == null) break block17;
            try {
                inputStream4.close();
            }
            catch (Exception var2_9) {
                var2_9.printStackTrace();
            }
        }
        if (object == null) return;
        try {
            object.close();
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
        catch (Exception exception) {
            block18 : {
                inputStream = inputStream2;
                object2 = object3;
                try {
                    exception.printStackTrace();
                    if (inputStream2 == null) break block18;
                }
                catch (Throwable var1_7) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Exception var2_10) {
                            var2_10.printStackTrace();
                        }
                    }
                    if (object2 == null) throw var1_7;
                    try {
                        object2.close();
                    }
                    catch (Exception var2_11) {
                        var2_11.printStackTrace();
                        throw var1_7;
                    }
                    throw var1_7;
                }
                try {
                    inputStream2.close();
                }
                catch (Exception var1_6) {
                    var1_6.printStackTrace();
                }
            }
            if (object3 == null) return;
            try {
                object3.close();
                return;
            }
            catch (Exception var1_5) {
                var1_5.printStackTrace();
                return;
            }
        }
    }

    private byte getByteValue(String string) {
        try {
            byte by = Integer.valueOf((String)string, (int)16).byteValue();
            return by;
        }
        catch (Exception var1_2) {
            System.out.println("token not found!!!, returning literal");
            return 4;
        }
    }

    private Element getCodepage(int n) {
        NodeList nodeList = this.tokenDoc.getElementsByTagName("codepage");
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Element element = (Element)nodeList.item(i);
            if (Integer.parseInt((String)element.getAttribute("number").trim()) != n) continue;
            return element;
        }
        return null;
    }

    private String getKeyFromValue(Iterator iterator, byte by) {
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            if (this.getByteValue(entry.getValue().toString()) != by) continue;
            return entry.getKey().toString();
        }
        return null;
    }

    private void initializeAttributeNameTokens(Element element, int n) {
        element = new Hashtable();
        NodeList nodeList = this.tokenDoc.getElementsByTagName("attribute-start");
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Object object = (Element)nodeList.item(i);
            byte by = this.getByteValue(object.getAttribute("token-value"));
            String string = object.getAttribute("name");
            object = object.getAttribute("prefix");
            element.put((Object)String.valueOf((int)by).toLowerCase(), (Object)new String[]{string, object});
        }
        this.codepages[n][4] = element;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initializeAttributeNames(Element object, int n) {
        Hashtable hashtable = new Hashtable();
        Hashtable hashtable2 = new Hashtable();
        object = object.getElementsByTagName("attribute-start");
        int n2 = 0;
        do {
            if (n2 >= object.getLength()) {
                this.codepages[n][1] = hashtable;
                this.codepages[n][2] = hashtable2;
                return;
            }
            Element element = (Element)object.item(n2);
            String string = element.getAttribute("name");
            String string2 = element.getAttribute("prefix");
            hashtable.put((Object)(string + string2).toLowerCase(), (Object)element.getAttribute("token-value"));
            if (hashtable2.containsKey((Object)string)) {
                ((Vector)hashtable2.get((Object)string)).addElement((Object)string2);
            } else {
                element = new Vector();
                element.addElement((Object)string2);
                hashtable2.put((Object)string, (Object)element);
            }
            ++n2;
        } while (true);
    }

    private void initializeAttributeValues(Element object, int n) {
        Hashtable hashtable = new Hashtable();
        object = object.getElementsByTagName("attribute-value");
        for (int i = 0; i < object.getLength(); ++i) {
            Element element = (Element)object.item(i);
            hashtable.put((Object)element.getAttribute("name").toLowerCase(), (Object)element.getAttribute("token-value"));
        }
        this.codepages[n][3] = hashtable;
    }

    private void initializeHeaderInfo() {
        this.codepages = (Hashtable[][])Array.newInstance(Hashtable.class, this.tokenDoc.getElementsByTagName("codepage").getLength(), 5);
        for (int i = 0; i < this.codepages.length; ++i) {
            Element element = this.getCodepage(i);
            this.initializeTags(element, i);
            this.initializeAttributeNames(element, i);
            this.initializeAttributeNameTokens(element, i);
            this.initializeAttributeValues(element, i);
        }
    }

    private void initializeTags(Element object, int n) {
        Hashtable hashtable = new Hashtable();
        object = object.getElementsByTagName("tag");
        for (int i = 0; i < object.getLength(); ++i) {
            Element element = (Element)object.item(i);
            hashtable.put((Object)element.getAttribute("name").toLowerCase(), (Object)element.getAttribute("token-value"));
        }
        this.codepages[n][0] = hashtable;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void initializeURNMappings() {
        InputStream inputStream;
        urnMappings = new Properties();
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        InputStream inputStream4 = null;
        inputStream4 = inputStream = this.mContext.getAssets().open("jwap_port/urnCodepageMappings.properties");
        inputStream2 = inputStream;
        inputStream3 = inputStream;
        urnMappings.load(inputStream);
        if (inputStream == null) return;
        try {
            inputStream.close();
            return;
        }
        catch (Exception var1_4) {
            var1_4.printStackTrace();
            return;
        }
        catch (FileNotFoundException fileNotFoundException) {
            inputStream3 = inputStream4;
            fileNotFoundException.printStackTrace();
            if (inputStream4 == null) return;
            {
                catch (Throwable throwable) {
                    if (inputStream3 == null) throw throwable;
                    try {
                        inputStream3.close();
                    }
                    catch (Exception var1_7) {
                        var1_7.printStackTrace();
                        throw throwable;
                    }
                    throw throwable;
                }
            }
            try {
                inputStream4.close();
                return;
            }
            catch (Exception var1_5) {
                var1_5.printStackTrace();
                return;
            }
            catch (IOException iOException) {
                inputStream3 = inputStream2;
                iOException.printStackTrace();
                if (inputStream2 == null) return;
                try {
                    inputStream2.close();
                    return;
                }
                catch (Exception var1_6) {
                    var1_6.printStackTrace();
                    return;
                }
            }
        }
    }

    public static void setCurrentCodepage(byte by) {
        currentCodepage = by;
    }

    public String[] getAttributeNameAndPrefix(byte by) {
        return (String[])this.codepages[currentCodepage][4].get((Object)String.valueOf((int)by));
    }

    public String getAttributeValue(byte by) {
        return this.getKeyFromValue(this.codepages[currentCodepage][3].entrySet().iterator(), by);
    }

    public String getTagName(byte by) {
        return this.getKeyFromValue(this.codepages[currentCodepage][0].entrySet().iterator(), by);
    }
}

