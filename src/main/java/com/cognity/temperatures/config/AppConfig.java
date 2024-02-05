package com.cognity.temperatures.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean("processEnginePrimary")
    @Primary
    public ProcessEngine processEngine() {
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setDataSource(camundaDataSource())
                .setJobExecutorActivate(true)
                .setHistory(ProcessEngineConfiguration.HISTORY_FULL)
                .setMetricsEnabled(false);

        return configuration.buildProcessEngine();
    }

    @Bean("cds")
    public DataSource camundaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/temperatures");
        dataSource.setUsername("root");
        dataSource.setPassword("nkbojack123");
        return dataSource;
    }

    @Bean("ptm")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(camundaDataSource());
    }

    @Bean("cmProcessEngineConfiguration")
    public ProcessEngineConfiguration processEngineConfiguration() {
        // Your configuration here
        return ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/temperatures")
                .setJdbcUsername("root")
                .setJdbcPassword("nkbojack123")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    }

    @Bean("rs")
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean("rns")
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean("ts")
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean("hs")
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean("ms")
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    @Bean("is")
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManagerTwo(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Resource[] getResources(String locationPattern) throws IOException {
        return new PathMatchingResourcePatternResolver().getResources(locationPattern);
    }
}
