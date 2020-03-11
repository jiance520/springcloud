package com.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configf8xn { //只能在非Bean里注入！少添加不用，或不常用的属性，万一不能注入会报错！
    @Value("${server.servlet.context-path}")
    private String contextPath;//projectName工程名访问路径中的autof8:http://localhost:8081/autof8/
//    @Value("${server.port}")
//    private String serverPort;
//    @Value("${spring.application.name}")
//    private String applicationName;
//    @Value("${spring.datasource.type}")
//    private String datasourceType;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.name}")
    private String datasourceName;//表名
    @Value("${spring.datasource.url}")
    private String connectionURL;
    @Value("${spring.datasource.username}")
    private String userId;

    @Override
    public String toString() {
        return "Configf8xn{" +
                "contextPath='" + contextPath + '\'' +
                ", driverClass='" + driverClass + '\'' +
                ", datasourceName='" + datasourceName + '\'' +
                ", connectionURL='" + connectionURL + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${spring.datasource.password}")
    private String password;
}
