package net.slipp.moim;

public enum DefaultConstant {

    DEFAULT_LIBRARY("spring-boot"),
    TITLE("title"),
    CONFIG_PACKAGE("configPackage"),
    BASE_PACKAGE("basePackage"),
    INTERFACE_ONLY("interfaceOnly"),
    JAVA_8("java8"),
    ASYNC("async"),
    RESPONSE_WRAPPER("responseWrapper"),
    USE_TAGS("useTags"),
    IMPLICIT_HEADERS("implicitHeaders"),
    HELLO("hello");

    private final String value;

    DefaultConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
