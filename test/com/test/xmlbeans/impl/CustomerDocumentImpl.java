/*
 * An XML document type.
 * Localname: customer
 * Namespace: http://test.com/xmlbeans
 * Java type: com.test.xmlbeans.CustomerDocument
 *
 * Automatically generated - do not modify.
 */
package com.test.xmlbeans.impl;

import com.test.xmlbeans.Order;

/**
 * A document containing one customer(@http://test.com/xmlbeans) element.
 *
 * This is a complex type.
 */
public class CustomerDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.test.xmlbeans.CustomerDocument
{
    
    public CustomerDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CUSTOMER$0 = 
        new javax.xml.namespace.QName("http://test.com/xmlbeans/customer", "customer");
    
    
    /**
     * Gets the "customer" element
     */
    public com.test.xmlbeans.CustomerDocument.Customer getCustomer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.test.xmlbeans.CustomerDocument.Customer target = null;
            target = (com.test.xmlbeans.CustomerDocument.Customer)get_store().find_element_user(CUSTOMER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "customer" element
     */
    public void setCustomer(com.test.xmlbeans.CustomerDocument.Customer customer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.test.xmlbeans.CustomerDocument.Customer target = null;
            target = (com.test.xmlbeans.CustomerDocument.Customer)get_store().find_element_user(CUSTOMER$0, 0);
            if (target == null)
            {
                target = (com.test.xmlbeans.CustomerDocument.Customer)get_store().add_element_user(CUSTOMER$0);
            }
            target.set(customer);
        }
    }
    
    /**
     * Appends and returns a new empty "customer" element
     */
    public com.test.xmlbeans.CustomerDocument.Customer addNewCustomer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.test.xmlbeans.CustomerDocument.Customer target = null;
            target = (com.test.xmlbeans.CustomerDocument.Customer)get_store().add_element_user(CUSTOMER$0);
            return target;
        }
    }
    /**
     * An XML customer(@http://test.com/xmlbeans).
     *
     * This is a complex type.
     */
    public static class CustomerImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.test.xmlbeans.CustomerDocument.Customer
    {
        
        public CustomerImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ORDER$0 = 
            new javax.xml.namespace.QName("http://test.com/xmlbeans/order", "order");
        private static final javax.xml.namespace.QName ID$2 = 
            new javax.xml.namespace.QName("", "id");
        private static final javax.xml.namespace.QName NAME$4 = 
            new javax.xml.namespace.QName("", "name");
        private static final javax.xml.namespace.QName CODE$6 = 
            new javax.xml.namespace.QName("", "code");
        
        
        /**
         * Gets array of all "order" elements
         */
        public com.test.xmlbeans.Order[] getOrderArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(ORDER$0, targetList);
                com.test.xmlbeans.Order[] result = new com.test.xmlbeans.Order[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "order" element
         */
        public com.test.xmlbeans.Order getOrderArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.test.xmlbeans.Order target = null;
                target = (com.test.xmlbeans.Order)get_store().find_element_user(ORDER$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "order" element
         */
        public int sizeOfOrderArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ORDER$0);
            }
        }
        
        /**
         * Sets array of all "order" element
         */
        public void setOrderArray(com.test.xmlbeans.Order[] orderArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(orderArray, ORDER$0);
            }
        }
        
        /**
         * Sets ith "order" element
         */
        public void setOrderArray(int i, com.test.xmlbeans.Order order)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.test.xmlbeans.Order target = null;
                target = (com.test.xmlbeans.Order)get_store().find_element_user(ORDER$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(order);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "order" element
         */
        public com.test.xmlbeans.Order insertNewOrder(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.test.xmlbeans.Order target = null;
                target = (com.test.xmlbeans.Order)get_store().insert_element_user(ORDER$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "order" element
         */
        public com.test.xmlbeans.Order addNewOrder()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.test.xmlbeans.Order target = null;
                target = (com.test.xmlbeans.Order)get_store().add_element_user(ORDER$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "order" element
         */
        public void removeOrder(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ORDER$0, i);
            }
        }
        
        /**
         * Gets the "id" attribute
         */
        public long getId()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$2);
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
                target = (org.apache.xmlbeans.XmlLong)get_store().find_attribute_user(ID$2);
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
                return get_store().find_attribute_user(ID$2) != null;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$2);
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
                target = (org.apache.xmlbeans.XmlLong)get_store().find_attribute_user(ID$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlLong)get_store().add_attribute_user(ID$2);
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
                get_store().remove_attribute(ID$2);
            }
        }
        
        /**
         * Gets the "name" attribute
         */
        public java.lang.String getName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$4);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "name" attribute
         */
        public org.apache.xmlbeans.XmlString xgetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$4);
                return target;
            }
        }
        
        /**
         * True if has "name" attribute
         */
        public boolean isSetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(NAME$4) != null;
            }
        }
        
        /**
         * Sets the "name" attribute
         */
        public void setName(java.lang.String name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$4);
                }
                target.setStringValue(name);
            }
        }
        
        /**
         * Sets (as xml) the "name" attribute
         */
        public void xsetName(org.apache.xmlbeans.XmlString name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$4);
                }
                target.set(name);
            }
        }
        
        /**
         * Unsets the "name" attribute
         */
        public void unsetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(NAME$4);
            }
        }
        
        /**
         * Gets the "code" attribute
         */
        public java.lang.String getCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CODE$6);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "code" attribute
         */
        public org.apache.xmlbeans.XmlString xgetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(CODE$6);
                return target;
            }
        }
        
        /**
         * True if has "code" attribute
         */
        public boolean isSetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(CODE$6) != null;
            }
        }
        
        /**
         * Sets the "code" attribute
         */
        public void setCode(java.lang.String code)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CODE$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(CODE$6);
                }
                target.setStringValue(code);
            }
        }
        
        /**
         * Sets (as xml) the "code" attribute
         */
        public void xsetCode(org.apache.xmlbeans.XmlString code)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(CODE$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(CODE$6);
                }
                target.set(code);
            }
        }
        
        /**
         * Unsets the "code" attribute
         */
        public void unsetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(CODE$6);
            }
        }
        
        public String getInfo()
        {
        	StringBuffer sb = new StringBuffer();
        	sb.append("[id = ").append(this.getId())
        		.append(", name = ").append(this.getName())
        		.append(", code = ").append(this.getCode())
        		.append(", po = {");
        	Object[] pos = getOrderArray();
        	Order po;
        	if(pos != null) {
        		for(int i = 0; i < pos.length; i++) {
        			po = (Order) pos[i];
        			sb.append(po.getInfo()).append(", ");
        		}
        	}
        	sb.append("}");
        	return sb.toString();
        }
    }
}
