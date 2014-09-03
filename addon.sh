#!/bin/sh
# CLASSPATH="$JAVA_HOME"/lib/
JAVA_OPTS="-Dfile.encoding=UTF8"
JAVA_HOME="/usr/java/jdk1.5.0_08"
_RUNJAVA="$JAVA_HOME"/bin/java
AMAXGS_HOME=`pwd`
CLASSES="$AMAXGS_HOME"/addon
LIB="$AMAXGS_HOME"/lib
WLIB="$AMAXGS_HOME"/ROOT/WEB-INF/lib
WCLASSES="$AMAXGS_HOME"/ROOT/WEB-INF/classes
MAINCLASS=com.viewbar.addon.Unzip
MAINARGS=$1
CLASSPATH=.:"$CLASSES":"$WCLASSES":"$WLIB"/antlr.jar:"$WLIB"/c3p0.jar:"$WLIB"/castor-1.1-codegen.jar:"$WLIB"/castor-1.1.jar:"$WLIB"/cglib.jar:"$WLIB"/commons-beanutils.jar:"$WLIB"/commons-codec-1.3.jar:"$WLIB"/commons-collections.jar:"$WLIB"/commons-fileupload.jar:"$WLIB"/commons-httpclient.jar:"$WLIB"/commons-io.jar:"$WLIB"/commons-lang.jar:"$WLIB"/commons-logging-1.0.4.jar:"$WLIB"/concurrent.jar:"$WLIB"/dom4j.jar:"$WLIB"/easyconf.jar:"$WLIB"/ehcache-1.2.jar:"$WLIB"/hibernate3.jar:"$WLIB"/jdom-1.0.jar:"$WLIB"/jgroups-all.jar:"$WLIB"/jta.jar:"$WLIB"/log4j.jar:"$WLIB"/lucene-core-2.0.0.jar:"$WLIB"/oscache-2.3.2.jar:"$WLIB"/quartz.jar:"$WLIB"/soap.jar:"$WLIB"/spring.jar:"$WLIB"/xbean-2.2.0.jar:"$WLIB"/xbean-spring-2.7.jar:"$WLIB"/xerces-2.6.2.jar:"$WLIB"/xml-apis.jar:"$WLIB"/XmlSchema-1.1.jar:"$WLIB"/xmlsec-1.3.0.jar:"$LIB"/activation-1.1.jar:"$LIB"/axis.jar:"$LIB"/commons-discovery-0.2.jar:"$LIB"/commons-discovery-0.2.jar:"$LIB"/javax.servlet.jar:"$LIB"/javax.servlet.jsp.jar:"$LIB"/jaxrpc.jar:"$LIB"/junit-3.8.1.jar:"$LIB"/mysql.jar:"$LIB"/naming-factory-dbcp.jar:"$LIB"/naming-factory.jar:"$LIB"/naming-resources.jar:"$LIB"/saaj.jar:
echo "Using CLASSPATH:  $CLASSPATH"
echo "Using MAINCLASS:  $MAINCLASS"
$_RUNJAVA $JAVA_OPTS -classpath $CLASSPATH $MAINCLASS $MAINARGS