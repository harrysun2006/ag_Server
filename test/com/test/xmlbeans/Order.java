/*
 * XML Type:  order
 * Namespace: http://test.com/xmlbeans
 * Java type: com.test.xmlbeans.Order
 *
 * Automatically generated - do not modify.
 */
package com.test.xmlbeans;


/**
 * An XML order(@http://test.com/xmlbeans).
 *
 * This is a complex type.
 */
public interface Order extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Order.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s71FB12658565494B6DA1FB273C1D8CFE").resolveHandle("order833ftype");
    
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
     * Gets the "price" attribute
     */
    double getPrice();
    
    /**
     * Gets (as xml) the "price" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetPrice();
    
    /**
     * True if has "price" attribute
     */
    boolean isSetPrice();
    
    /**
     * Sets the "price" attribute
     */
    void setPrice(double price);
    
    /**
     * Sets (as xml) the "price" attribute
     */
    void xsetPrice(org.apache.xmlbeans.XmlDouble price);
    
    /**
     * Unsets the "price" attribute
     */
    void unsetPrice();
    
    /**
     * Gets the "createDate" attribute
     */
    java.util.Calendar getCreateDate();
    
    /**
     * Gets (as xml) the "createDate" attribute
     */
    org.apache.xmlbeans.XmlDateTime xgetCreateDate();
    
    /**
     * True if has "createDate" attribute
     */
    boolean isSetCreateDate();
    
    /**
     * Sets the "createDate" attribute
     */
    void setCreateDate(java.util.Calendar createDate);
    
    /**
     * Sets (as xml) the "createDate" attribute
     */
    void xsetCreateDate(org.apache.xmlbeans.XmlDateTime createDate);
    
    /**
     * Unsets the "createDate" attribute
     */
    void unsetCreateDate();
    
    String getInfo();

    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.test.xmlbeans.Order newInstance() {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.test.xmlbeans.Order newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.test.xmlbeans.Order parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.test.xmlbeans.Order parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.test.xmlbeans.Order parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.test.xmlbeans.Order parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.test.xmlbeans.Order parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.test.xmlbeans.Order parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.test.xmlbeans.Order parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.test.xmlbeans.Order parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.test.xmlbeans.Order parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.test.xmlbeans.Order parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.test.xmlbeans.Order parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.test.xmlbeans.Order parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.test.xmlbeans.Order parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.test.xmlbeans.Order parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.test.xmlbeans.Order parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.test.xmlbeans.Order parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.test.xmlbeans.Order) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
