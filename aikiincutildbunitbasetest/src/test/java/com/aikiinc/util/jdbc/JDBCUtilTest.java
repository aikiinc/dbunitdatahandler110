package com.aikiinc.util.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aikiinc.dbunit.test.base.DbUnitBaseTest;
import com.aikiinc.util.jdbc.JDBCUtil.ConnectionInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-connection.xml" })
public class JDBCUtilTest extends DbUnitBaseTest {
	private static Logger LOG = Logger.getLogger(JDBCUtilTest.class);

	@Test
	public void getConnection() {
		try {
			Connection con = dataSource.getConnection();
			Assert.assertNotNull(con);
			JDBCCloseUtil.close(con);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Test
	public void getConnectionInfo() {
		try {
			Connection con = dataSource.getConnection();
			Assert.assertNotNull(con);

			JDBCUtil.ConnectionInfo info = JDBCUtil.getConnectionInfo(con);
			Assert.assertNotNull(info);

			//assertMYSQL(info);
			assertHSQLDB(info);

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
		Assert.assertEquals("root", info.getUserName().toLowerCase());
	}

	private void assertHSQLDB(JDBCUtil.ConnectionInfo info) {
		Assert.assertEquals("HSQL Database Engine", info.getDatabaseProductName());
		Assert.assertEquals("HSQL Database Engine Driver", info.getDriverName());
		LOG.info(info.getURL());
		Assert.assertTrue(info.getURL().contains(
				"jdbc:hsqldb:file:hsqldbsetup/db/testdb"));
		Assert.assertEquals("sa", info.getUserName().toLowerCase());
	}

	@Test
	public void showMetaData() {
		try {
			Connection con = dataSource.getConnection();
			Assert.assertNotNull(con);

			JDBCUtil.showMetaData(con);
			JDBCCloseUtil.close(con);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Test
	public void getConnectionMetaData() {
		try {
			Connection con = dataSource.getConnection();
			Assert.assertNotNull(con);

			DatabaseMetaData dmd = JDBCUtil.getConnectionMetaData(con);
			Assert.assertNotNull(dmd);

			JDBCCloseUtil.close(con);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

}
