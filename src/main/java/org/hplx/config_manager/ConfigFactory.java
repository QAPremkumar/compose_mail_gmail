package org.hplx.config_manager;

import org.aeonbits.owner.ConfigCache;

public class ConfigFactory { //no one can extend this

    private ConfigFactory(){
        // Prevent instantiation
    }
    public static FrameworkConfig getConfig(){
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }
}