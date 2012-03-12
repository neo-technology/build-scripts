package com.neotechnology.build.test;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProtoBufTest {
    @Test public void testIt() {
	Hello.Greeting greeting = Hello.Greeting.newBuilder()
	                          .setGreetee("World").build();
	assertEquals("World", greeting.getGreetee());
    }

}
