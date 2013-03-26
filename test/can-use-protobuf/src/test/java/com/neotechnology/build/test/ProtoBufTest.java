/*
 * Copyright (C) 2013 Neo Technology
 * All rights reserved
 */

package com.neotechnology.build.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProtoBufTest
{
    @Test
    public void testIt()
    {
        Hello.Greeting greeting = Hello.Greeting.newBuilder()
                .setGreetee( "World" ).build();
        assertEquals( "World", greeting.getGreetee() );
    }

}
