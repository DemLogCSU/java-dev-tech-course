package com.fileprocessing.FileProcessing.modules;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.Resource;
import java.util.EnumMap;
import java.util.Map;

@Configuration
public class ModuleController {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Resource(name="textModule")
    private AbstractModule textModule;
    @Resource(name="imageModule")
    private AbstractModule imageModule;
    @Resource(name="musicModule")
    private AbstractModule musicModule;
    @Resource(name="dirModule")
    private AbstractModule dirModule;
    @Bean
    public Map<TypeModules, AbstractModule> modulesMap() {
        Map<TypeModules, AbstractModule> map = new EnumMap<>(TypeModules.class);
        map.put(TypeModules.txt, textModule);
        map.put(TypeModules.png, imageModule);
        map.put(TypeModules.mp3, musicModule);
        map.put(TypeModules.dir, dirModule);
        return map;
    }
}
