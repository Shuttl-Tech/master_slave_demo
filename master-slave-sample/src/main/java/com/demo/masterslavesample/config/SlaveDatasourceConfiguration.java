package com.demo.masterslavesample.config;

import com.demo.masterslavesample.utils.Constants;
import com.demo.masterslavesample.utils.IniUtils;
import jakarta.persistence.EntityManagerFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.demo.masterslavesample.repository.slave"},
        entityManagerFactoryRef = "slaveEntityManagerFactory",
        transactionManagerRef = "slaveTransactionManager"
)
public class SlaveDatasourceConfiguration {

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() throws Exception {
        String slaveUrl =
                IniUtils.getInstance().getValue(Constants.DB_SECTION_NAME,
                        Constants.SLAVE_DB_URL);
        String slaveUsername =
                IniUtils.getInstance().getValue(Constants.DB_SECTION_NAME,
                Constants.SLAVE_DB_USERNAME);
        String slavePassword =
                IniUtils.getInstance().getValue(Constants.DB_SECTION_NAME,
                        Constants.SLAVE_DB_PASSWORD);

        PoolProperties slavePoolProperties = configurePoolProperties(slaveUrl,
                slaveUsername, slavePassword);

        DataSource slaveDataSource = new DataSource();

        slaveDataSource.setPoolProperties(slavePoolProperties);

        return slaveDataSource;
    }

    @Bean(name = "slaveEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("slaveDataSource") DataSource slaveDataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(slaveDataSource);
        factoryBean.setPackagesToScan("com.demo.masterslavesample");
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJpaPropertyMap(properties);

        return factoryBean;
    }

    @Bean(name = "slaveTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("slaveEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    private PoolProperties configurePoolProperties(String url, String username, String password) throws IOException {
        int maxPoolSize = Integer.parseInt(IniUtils.getInstance().getValue(Constants.DB_SECTION_NAME, Constants.DB_MAX_CONNECTION_POOL_SIZE));
        int minPoolSize = Integer.parseInt(IniUtils.getInstance().getValue(Constants.DB_SECTION_NAME, Constants.DB_MIN_CONNECTION_POOL_SIZE));
        int validationInterval = Integer.parseInt(IniUtils.getInstance().getValue(Constants.DB_SECTION_NAME, Constants.DB_CONN_VALID_INTERVAL_MS));
        int maxWait = Integer.parseInt(IniUtils.getInstance().getValue(Constants.DB_SECTION_NAME, Constants.DB_MAX_WAIT_CONN_MS));
        int slowQueryDuration = Integer.parseInt(IniUtils.getInstance().getValue(Constants.DB_SECTION_NAME, Constants.DB_SLOW_QUERY_DURATION_MS));

        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setDriverClassName("org.postgresql.Driver");
        poolProperties.setUrl(url);
        poolProperties.setUsername(username);
        poolProperties.setPassword(password);
        poolProperties.setMaxIdle(maxPoolSize);
        poolProperties.setMaxActive(maxPoolSize);
        poolProperties.setInitialSize(maxPoolSize);
        poolProperties.setMinIdle(minPoolSize);
        poolProperties.setMaxWait(maxWait);

        //connection validation
        poolProperties.setTestOnBorrow(true);
        poolProperties.setTestOnConnect(true);
        poolProperties.setTestOnReturn(true);
        poolProperties.setValidationQuery("SELECT 1");
        poolProperties.setLogAbandoned(true);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setValidationInterval(validationInterval);


        return poolProperties;
    }
}
