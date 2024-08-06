package org.com.common;


import org.aeonbits.owner.Config;

@Config.Sources({"classpath:app.properties"})
public interface AppConfig extends Config {

    @Key("resourceLink")
    String resourceLink();

    @Key("maxWaitTime")
    int MAX_WAIT_TIME();

    @Key("baseEndpoint")
    String BASE_ENDPOINT();
}
