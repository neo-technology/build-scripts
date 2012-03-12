package com.neotechnology.build.test;

class JarFile {
    public static String name(Class<?> cls) {
        try {
            return new java.io.File( cls.getProtectionDomain()
                                        .getCodeSource()
                                        .getLocation()
                                        .toURI() ).getName();
        } catch (java.net.URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}