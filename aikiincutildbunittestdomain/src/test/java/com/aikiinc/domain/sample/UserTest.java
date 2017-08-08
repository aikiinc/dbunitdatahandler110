package com.aikiinc.domain.sample;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;

/**
 * Base test user
 * 
 * @Copyright (C) Aiki Innovations Inc 2013-2017 - http://www.aikiinc.com
 * @Author: Philip Jahmani Chauvet.
 * @Dated Aug 7, 2017
 */
public class UserTest {
	private static Logger LOG = Logger.getLogger(UserTest.class);

	@Test
	public void createUser() {
		User user = new User();
		user.setId(999L);
		user.setUsername("User999");

		// LOG.info(user);
		Assert.assertEquals(new Long(999), user.getId());
		Assert.assertEquals("User999", user.getUsername());
	}

}
