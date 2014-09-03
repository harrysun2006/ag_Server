package com.agloco.util;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.mapping.ForeignKey;
import org.hibernate.mapping.Index;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Table;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.agloco.spring.SpringUtil;

public class HibernateUtil {

	public static final String DYNA_POCLASS_PREFIX = "com.dynapo.";
	public static final String[] EMPTY_STRING_ARRAY = {};
	private static final String VIEWBAR_DATASOURCE = "viewbarDataSource";
	private static final String SPRING_HIBERNATE_SESSION_FACTORY = "&viewbarSessionFactory";

	private static final Log _log = LogFactory.getLog(HibernateUtil.class);

	private HibernateUtil() {
		
	}

	public static SessionFactoryImplementor getSessionFactory() {
		return getSessionFactory(SPRING_HIBERNATE_SESSION_FACTORY);
	}

	public static SessionFactoryImplementor getSessionFactory(String sessionFactoryName) {
		ApplicationContext ctx = SpringUtil.getContext();
		LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean)ctx.getBean(sessionFactoryName);
		return (SessionFactoryImplementor)lsfb.getObject();
	}

	public static String[] generateSchemaCreationScript(String className) {
		return generateSchemaCreationScript(null, className, null, null);
	}

	public static String[] generateSchemaCreationScript(String className, String tableName) {
		return generateSchemaCreationScript(null, className, tableName, null);
	}

	public static String[] generateSchemaCreationScript(String className, String tableName, String options) {
		return generateSchemaCreationScript(null, className, tableName, options);
	}

	public static String[] generateSchemaCreationScript(Configuration config, String className, String options) {
		return generateSchemaCreationScript(config, className, null, options);
	}

	public static String[] generateSchemaCreationScript(Configuration config, String className, String tableName, String options) {
		if(config == null) {
			ApplicationContext ctx = SpringUtil.getContext();
			LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean)ctx.getBean(SPRING_HIBERNATE_SESSION_FACTORY);
			config = lsfb.getConfiguration();
		}
		return generateSchemaCreationScript2(config, className, tableName, options, false);
	}

	public static String[] createTable(String className) {
		return createTable(null, className, null, null);
	}

	public static String[] createTable(String className, String tableName) {
		return createTable(null, className, tableName, null);
	}

	public static String[] createTable(String className, String tableName, String options) {
		return createTable(null, className, tableName, options);
	}

	public static String[] createTable(Configuration config, String className, String options) {
		return createTable(config, className, null, options);
	}

	public static String[] createTable(Configuration config, String className, String tableName, String options) {
		if(config == null) {
			ApplicationContext ctx = SpringUtil.getContext();
			LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean)ctx.getBean(SPRING_HIBERNATE_SESSION_FACTORY);
			config = lsfb.getConfiguration();
		}
		return generateSchemaCreationScript2(config, className, tableName, options, true);
	}

	/**
	 * @param config
	 * @param className
	 * @param tableName
	 * @return
	 */
	/*
	private static String generateSchemaCreationScript1(Configuration config, String className, String tableName, boolean script) {
		String[] sqls = {};
		try {
			Properties props = config.getProperties();
//		props.remove(Environment.CONNECTION_PROVIDER);
//		props.put(Environment.DATASOURCE, "java:comp/env/jdbc/LiferayPool");
			props.put(Environment.HBM2DDL_AUTO, String.valueOf(script));
			PersistentClass clazz = config.getClassMapping(className);
			Table table = clazz.getTable();
			if(tableName == null) tableName = table.getName();
			Map tables = new Hashtable();
			tables.put(tableName, table);
			LocalSessionFactoryBean dynaLsfb = new DynaHibernateConfiguration(props, tables);
			dynaLsfb.afterPropertiesSet();
			Configuration dynaConfig = dynaLsfb.getConfiguration();
			Dialect dialect = Dialect.getDialect(dynaConfig.getProperties());
			sqls = dynaConfig.generateSchemaCreationScript(dialect);
			if(_log.isDebugEnabled()) {
				_log.debug("total " + sqls.length + " tables!");
				for(int i = 0; i < sqls.length; i++) {
					_log.debug("sqls[" + i + "] = " + sqls[i]);
				}
			}
			if(script) {
				SchemaExport se = new SchemaExport(dynaConfig);
				se.setDelimiter(";");
				se.create(script, false);
			}
		} catch(Exception e) {
			_log.error(e, e);
		}
		return (sqls.length > 0) ? sqls[0] : "";
	}
	*/

	/**
	 * @param config
	 * @param className
	 * @param tableName
	 * @return
	 */
	private static String[] generateSchemaCreationScript2(Configuration config, String className, String tableName, String options, boolean script) {
		List scripts = new ArrayList();
		String sql;
		DataSource ds = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			PersistentClass clazz = config.getClassMapping(className);
			Table table = clazz.getTable();
			String oldName = table.getName();
			if(tableName == null) tableName = table.getName();
			table.setName(tableName);
			Properties props = config.getProperties();
			Dialect dialect = Dialect.getDialect(props);
			String catalog = props.getProperty(Environment.DEFAULT_CATALOG);
			String schema = props.getProperty(Environment.DEFAULT_SCHEMA);
			// table create script
			sql = table.sqlCreateString(dialect, getSessionFactory(), catalog, schema);
			if(options != null) sql = sql + " " + options;
			scripts.add(sql);
			// table comment script
			Iterator comments = table.sqlCommentStrings(dialect, catalog, schema);
			while ( comments.hasNext() ) {
				scripts.add(comments.next());
			}
			// table index script
			Iterator subIter = table.getIndexIterator();
			while ( subIter.hasNext() ) {
				Index index = (Index) subIter.next();
				scripts.add(index.sqlCreateString(dialect, getSessionFactory(), catalog, schema));
			}
			// table foreign key script
			if ( dialect.hasAlterTable() ) {
				subIter = table.getForeignKeyIterator();
				while ( subIter.hasNext() ) {
					ForeignKey fk = (ForeignKey) subIter.next();
					if ( fk.isPhysicalConstraint() ) {
						scripts.add(fk.sqlCreateString(dialect, getSessionFactory(), catalog, schema));
					}
				}
			}
			table.setName(oldName);
			if(script) {
				ApplicationContext ctx = SpringUtil.getContext();
				ds = (DataSource) ctx.getBean(VIEWBAR_DATASOURCE);
				conn = ds.getConnection();
				stmt = conn.createStatement();
				for(Iterator it = scripts.iterator(); it.hasNext(); ) {
					sql = (String) it.next();
					stmt.executeUpdate(sql);
				}
			}
		} catch(Exception e) {
			_log.error(e, e);
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch ( Exception e ) {
				_log.error( "Could not close connection", e);
			}
		}
		return (String[]) scripts.toArray(EMPTY_STRING_ARRAY);
	}

	/**
	 * @deprecated Table.getCheckConstraintsIterator() and Table.getIdentifierValue() are not supported in Hibernate 3.1.3-
	 * @author harry_sun
	 *
	 */
	/*
	private static class DynaHibernateConfiguration extends LocalSessionFactoryBean {
		private Properties props;
		private Map tables;

		public DynaHibernateConfiguration(Properties props, Map tables) {
			this.props = props;
			this.tables = tables;
		}

		protected Configuration newConfiguration() throws HibernateException {
			Configuration config = super.newConfiguration();
			config.setProperties(props);

			DataSource ds = getConfigTimeDataSource();
			if(ds == null) {
				ApplicationContext ctx = SpringUtil.getContext();
				ds = (DataSource) ctx.getBean(LIFERAY_DATASOURCE);
				setDataSource(ds);
			}

			String catalog = props.getProperty(Environment.DEFAULT_CATALOG);
			String schema = props.getProperty(Environment.DEFAULT_SCHEMA);
			Mappings mappings = config.createMappings();
			if(tables != null) {
				String tableName;
				Table table, newTable;
				PrimaryKey pk, newPk;
				Column column;
				Constraint constraint;
				Index index;
				UniqueKey uk;
				for(Iterator keys = tables.keySet().iterator(); keys.hasNext(); ) {
					tableName = (String) keys.next();
					table = (Table) tables.get(tableName);
					// must addTable to generate ddl script
					newTable = mappings.addTable(schema, catalog, tableName, null, false);
					// add PK
					pk = table.getPrimaryKey();
					newPk = new PrimaryKey();
					newPk.setTable(newTable);
					for(Iterator it = pk.getColumnIterator(); it.hasNext(); ) {
						column = (Column) it.next();
						newPk.addColumn(column);
					}
					newTable.setPrimaryKey(newPk);
					// add columns
					for(Iterator it = table.getColumnIterator(); it.hasNext(); ) {
						column = (Column) it.next();
						newTable.addColumn(column);
					}
					// add constraints
					for(Iterator it = table.getCheckConstraintsIterator(); it.hasNext(); ) {
						constraint = (Constraint) it.next();
						newTable.addCheckConstraint(constraint.toString());
					}
					// add indexes
					for(Iterator it = table.getIndexIterator(); it.hasNext(); ) {
						index = (Index) it.next();
						newTable.addIndex(index);
					}
					// add unique keys
					for(Iterator it = table.getUniqueKeyIterator(); it.hasNext(); ) {
						uk = (UniqueKey) it.next();
						newTable.addUniqueKey(uk);
					}
					// add other attributes
					newTable.setAbstract(table.isAbstract());
					newTable.setCatalog(table.getCatalog());
					newTable.setComment(table.getComment());
					newTable.setName(tableName);
					newTable.setQuoted(table.isQuoted());
					newTable.setRowId(table.getRowId());
					newTable.setSchema(table.getSchema());
					newTable.setSubselect(table.getSubselect());
					newTable.setIdentifierValue(table.getIdentifierValue());
				}
			}
			return config;
		}
	}
	*/

}
