package com.aum.spring;


import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.core.SpringVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * util class for {@link org.springframework.core.env.Environment}
 *
 * @author xiayx
 * @see ConfigFileApplicationListener
 */
public abstract class EnvironmentUtils {

    /** @see ConfigFileApplicationListener#DEFAULT_SEARCH_LOCATIONS */
    public static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/,classpath:/config/,file:./,file:./config/";
    private static final List<String> DEFAULT_SEARCH_LOCATION_LIST = Arrays.asList(DEFAULT_SEARCH_LOCATIONS.split(","));
    /** 服务器上配置文件约定的位置规则 */
    public static final String CONVENTIONAL_LOCATION = "file:/etc/aum/%s/";

    /** 设置配置文件位置 */
    public static void setConfigLocation(String configLocation) {
        if (isVersion4()) {
            System.setProperty(ConfigFileApplicationListener.CONFIG_NAME_PROPERTY, "<unknown>");
        }
        System.setProperty(ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY, configLocation);
    }

    private static boolean isVersion4() {
        return SpringVersion.getVersion().startsWith("4");
    }

    /** 获取配置文件位置 */
    public static String getConfigLocation() {
        String property = System.getProperty(ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY);
        if (property == null) return null;

        if (isVersion4()) {
            List<String> specials = Arrays.asList(property.split(","));
            ArrayList<String> properties = new ArrayList<>(DEFAULT_SEARCH_LOCATION_LIST);
            properties.removeAll(specials);
            properties.addAll(specials);
            return properties.stream().collect(Collectors.joining(","));
        }
        return property;
    }

    /** 设置附加配置文件位置：在默认位置的基础上添加一个新位置 */
    public static void setAdditionalConfigLocation(String location) {
        System.setProperty(ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY, DEFAULT_SEARCH_LOCATIONS + "," + location);
    }

    /** 设置约定的附加配置文件位置，路径规则相同，随项目而变化 */
    public static void setConventionalAdditionalConfigLocation(String projectName) {
        setAdditionalConfigLocation(String.format(CONVENTIONAL_LOCATION, projectName));
    }

    /** 设置附加配置文件位置，与指定Class在同一目录下 */
    public static void setAdditionalConfigLocation(Class<?> baseClass) {
        setAdditionalConfigLocation("file:" + baseClass.getResource("application.properties").getPath());
    }


}
