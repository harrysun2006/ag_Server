/*
 * An XML document type.
 * Localname: customer
 * Namespace: http://test.com/xmlbeans
 * Java type: com.test.xmlbeans.CustomerDocument
 *
 * Automatically generated - do not modify.
 */
package com.test.xmlbeans;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.xml.stream.XMLInputStream;


/**
 * A document containing one customer(@http://test.com/xmlbeans) element.
 *
 * This is a complex type.
 */
public interface CustomerDocument extends org.apache.xmlbeans.XmlObject
{
  
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CustomerDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s71FB12658565494B6DA1FB273C1D8CFE").resolveHandle("customer4aaddoctype");
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//    	org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CustomerDocument.class.getClassLoader(), "org.apache.xmlbeans.impl.schema.SchemaTypeImpl").resolveHandle("customer4aaddoctype");
//		public static final org.apache.xmlbeans.SchemaType type = XmlAnySimpleType.type;
		public static final SchemaType type = XmlBeans.typeLoaderForClassLoader(CustomerDocument.class.getClassLoader()).typeForClassname("com.test.xmlbeans.Customer");

    /**
     * Gets the "customer" element
     */
    com.test.xmlbeans.CustomerDocument.Customer getCustomer();
    
    /**
     * Sets the "customer" element
     */
    void setCustomer(com.test.xmlbeans.CustomerDocument.Customer customer);
    
    /**
     * Appends and returns a new empty "customer" element
     */
    com.test.xmlbeans.CustomerDocument.Customer addNewCustomer();
    
    /**
     * An XML customer(@http://test.com/xmlbeans).
     *
     * This is a complex type.
     */
    public interface Customer extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Customer.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s71FB12658565494B6DA1FB273C1D8CFE").resolveHandle("customer8627elemtype");
        
        /**
         * Gets array of all "order" elements
         */
        com.test.xmlbeans.Order[] getOrderArray();
        
        /**
         * Gets ith "order" element
         */
        com.test.xmlbeans.Order getOrderArray(int i);
        
        /**
         * Returns number of "order" element
         */
        int sizeOfOrderArray();
        
        /**
         * Sets array of all "order" element
         */
        void setOrderArray(com.test.xmlbeans.Order[] orderArray);
        
        /**
         * Sets ith "order" element
         */
        void setOrderArray(int i, com.test.xmlbeans.Order order);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "order" element
         */
        com.test.xmlbeans.Order insertNewOrder(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "order" element
         */
        com.test.xmlbeans.Order addNewOrder();
        
        /**
         * Removes the ith "order" element
         */
        void removeOrder(int i);
        
        /**
         * Gets the "id" attribute
         */
        long getId();
        
        /**
         * Gets (as xml) the "id" attribute
         */
        org.apache.xmlbeans.XmlLong xgetId();
        
        /**
         * True if has "id" attribute
         */
        boolean isSetId();
        
        /**
         * Sets the "id" attribute
         */
        void setId(long id);
        
        /**
         * Sets (as xml) the "id" attribute
         */
        void xsetId(org.apache.xmlbeans.XmlLong id);
        
        /**
         * Unsets the "id" attribute
         */
        void unsetId();
        
        /**
         * Gets the "name" attribute
         */
        java.lang.String getName();
        
        /**
         * Gets (as xml) the "name" attribute
         */
        org.apache.xmlbeans.XmlString xgetName();
        
        /**
         * True if has "name" attribute
         */
        boolean isSetName();
        
        /**
         * Sets the "name" attribute
         */
        void setName(java.lang.String name);
        
        /**
         * Sets (as xml) the "name" attribute
         */
        void xsetName(org.apache.xmlbeans.XmlString name);
        
        /**
         * Unsets the "name" attribute
         */
        void unsetName();
        
        /**
         * Gets the "code" attribute
         */
        java.lang.String getCode();
        
        /**
         * Gets (as xml) the "code" attribute
         */
        org.apache.xmlbeans.XmlString xgetCode();
        
        /**
         * True if has "code" attribute
         */
        boolean isSetCode();
        
        /**
         * Sets the "code" attribute
         */
        void setCode(java.lang.String code);
        
        /**
         * Sets (as xml) the "code" attribute
         */
        void xsetCode(org.apache.xmlbeans.XmlString code);
        
        /**
         * Unsets the "code" attribute
         */
        void unsetCode();

        String getInfo();

        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static com.test.xmlbeans.CustomerDocument.Customer newInstance() {
              return (com.test.xmlbeans.CustomerDocument.Customer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static com.test.xmlbeans.CustomerDocument.Customer newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (com.test.xmlbeans.CustomerDocument.Customer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.test.xmlbeans.CustomerDocument newInstance() {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.test.xmlbeans.CustomerDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.test.xmlbeans.CustomerDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.test.xmlbeans.CustomerDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.test.xmlbeans.CustomerDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.test.xmlbeans.CustomerDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.test.xmlbeans.CustomerDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.test.xmlbeans.CustomerDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
