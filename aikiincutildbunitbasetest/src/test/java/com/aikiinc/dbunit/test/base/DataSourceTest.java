package com.aikiinc.dbunit.test.base;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aikiinc.util.jdbc.JDBCCloseUtil;
import com.aikiinc.util.jdbc.JDBCUtil;
import com.aikiinc.util.jdbc.JDBCUtil.ConnectionInfo;

/**
 *
 * @Copyright (C) Aiki Innovations Inc 2013-2015 - http://www.aikiinc.com
 * @Author Philip Jahmani Chauvet.
 * @Dated Sep 23, 2013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-connection.xml" })
public class DataSourceTest extends DbUnitBaseTest {
	private static Logger LOG = Logger.getLogger(DataSourceTest.class);

	@Test
	public void getDataSource() {
		Assert.assertNotNull(dataSource);
	}

	@Test
	public void getConnection() {
		try {
			Connection con = dataSource.getConnection();
			Assert.assertNotNull(con);
			JDBCCloseUtil.close(con);
		} catch (Exception e) {
		}
	}

	@Test
	public void getConnectionInfo() {
		try {
			Connection con = dataSource.getConnection();
			Assert.assertNotNull(con);

			JDBCUtil.ConnectionInfo info = JDBCUtil.getConnectionInfo(con);
			Assert.assertNotNull(info);
			// LOG.info(".");
			// LOG.info(info);
			// LOG.info(".");

			//assertMYSQL(info);
			//assertHSQLDB(info);

			JDBCCloseUtil.close(con);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void assertMYSQL(ConnectionInfo info) {
		Assert.assertEquals("MySQL", info.getDatabaseProductName());
		Assert.assertEquals("MySQL Connector Java", info.getDriverName());
		Assert.assertTrue(info.getURL().contains(
				"jdbc:mysql://localhost/testdb"));
		Assert.assertEquals("root@localhost", info.getUserName().toLowerCase());
	}

	private void assertHSQLDB(JDBCUtil.ConnectionInfo info) {
		Assert.assertEquals("HSQL Database Engine",
				info.getDatabaseProductName());
		Assert.assertEquals("HSQL Database Engine Driver", info.getDriverName());
		Assert.assertTrue(info.getURL().contains(
				"jdbc:hsqldb:file:hsqldbsetup/db/testdb"));
		Assert.assertEquals("sa", info.getUserName().toLowerCase());
	}

	@Test
	public void getDataProperty() {
		Assert.assertNotNull(hibernateProperties);

		getHibernateProperty("hibernate.hbm2ddl.auto");
	}

	@Test
	public void getHibernateProperty() {
		try {
			Assert.assertNull(getHibernateProperty("hibernate.hbm2ddl.bad"));
			Assert.assertNotNull(getHibernateProperty("hibernate.hbm2ddl.auto"));
			// LOG.info("Hibernate property(hibernate.hbm2ddl.auto):  "
			// + getHibernateProperty("hibernate.hbm2ddl.auto"));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
