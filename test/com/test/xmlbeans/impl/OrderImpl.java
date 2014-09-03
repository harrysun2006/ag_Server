/*
 * XML Type:  order
 * Namespace: http://test.com/xmlbeans
 * Java type: com.test.xmlbeans.Order
 *
 * Automatically generated - do not modify.
 */
package com.test.xmlbeans.impl;
/**
 * An XML order(@http://test.com/xmlbeans).
 *
 * This is a complex type.
 */
public class OrderImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.test.xmlbeans.Order
{
    
    public OrderImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ID$0 = 
        new javax.xml.namespace.QName("", "id");
    private static final javax.xml.namespace.QName PRICE$2 = 
        new javax.xml.namespace.QName("", "price");
    private static final javax.xml.namespace.QName CREATEDATE$4 = 
        new javax.xml.namespace.QName("", "createDate");
    
    
    /**
     * Gets the "id" attribute
     */
    public long getId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$0);
            if (target == null)
            {
                return 0L;
            }
            return target.getLongValue();
        }
    }
    
    /**
     * Gets (as xml) the "id" attribute
     */
    public org.apache.xmlbeans.XmlLong xgetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlLong target = null;
            target = (org.apache.xmlbeans.XmlLong)get_store().find_attribute_user(ID$0);
            return target;
        }
    }
    
    /**
     * True if has "id" attribute
     */
    public boolean isSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ID$0) != null;
        }
    }
    
    /**
     * Sets the "id" attribute
     */
    public void setId(long id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$0);
            }
            target.setLongValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "id" attribute
     */
    public void xsetId(org.apache.xmlbeans.XmlLong id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlLong target = null;
            target = (org.apache.xmlbeans.XmlLong)get_store().find_attribute_user(ID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlLong)get_store().add_attribute_user(ID$0);
            }
            target.set(id);
        }
    }
    
    /**
     * Unsets the "id" attribute
     */
    public void unsetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ID$0);
        }
    }
    
    /**
     * Gets the "price" attribute
     */
    public double getPrice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PRICE$2);
            if (target == null)
            {
                return 0.0;
            }
            return target.getDoubleValue();
        }
    }
    
    /**
     * Gets (as xml) the "price" attribute
     */
    public org.apache.xmlbeans.XmlDouble xgetPrice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_attribute_user(PRICE$2);
            return target;
        }
    }
    
    /**
     * True if has "price" attribute
     */
    public boolean isSetPrice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(PRICE$2) != null;
        }
    }
    
    /**
     * Sets the "price" attribute
     */
    public void setPrice(double price)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PRICE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(PRICE$2);
            }
            target.setDoubleValue(price);
        }
    }
    
    /**
     * Sets (as xml) the "price" attribute
     */
    public void xsetPrice(org.apache.xmlbeans.XmlDouble price)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_attribute_user(PRICE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDouble)get_store().add_attribute_user(PRICE$2);
            }
            target.set(price);
        }
    }
    
    /**
     * Unsets the "price" attribute
     */
    public void unsetPrice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(PRICE$2);
        }
    }
    
    /**
     * Gets the "createDate" attribute
     */
    public java.util.Calendar getCreateDate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CREATEDATE$4);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "createDate" attribute
     */
    public org.apache.xmlbeans.XmlDateTime xgetCreateDate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_attribute_user(CREATEDATE$4);
            return target;
        }
    }
    
    /**
     * True if has "createDate" attribute
     */
    public boolean isSetCreateDate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(CREATEDATE$4) != null;
        }
    }
    
    /**
     * Sets the "createDate" attribute
     */
    public void setCreateDate(java.util.Calendar createDate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CREATEDATE$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(CREATEDATE$4);
            }
            target.setCalendarValue(createDate);
        }
    }
    
    /**
     * Sets (as xml) the "createDate" attribute
     */
    public void xsetCreateDate(org.apache.xmlbeans.XmlDateTime createDate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_attribute_user(CREATEDATE$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_attribute_user(CREATEDATE$4);
            }
            target.set(createDate);
        }
    }
    
    /**
     * Unsets the "createDate" attribute
     */
    public void unsetCreateDate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(CREATEDATE$4);
        }
    }

    public String getInfo()
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append("(").append(this.getId())
    		.append(", ").append(this.getPrice())
    		.append(", ").append(this.getCreateDate())
    		.append(")");
    	return sb.toString();
    }
}
