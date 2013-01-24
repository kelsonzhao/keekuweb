package com.kelson.keeku;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(value = {"classpath:spring/app-context.xml"})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

}
