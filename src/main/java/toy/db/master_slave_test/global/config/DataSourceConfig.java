package toy.db.master_slave_test.global.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@Configuration
//@Profile("mysql")
public class DataSourceConfig {

  public static final String MASTER_DATASOURCE = "masterDataSource";
  public static final String SLAVE_DATASOURCE = "slaveDataSource";

  @Bean(MASTER_DATASOURCE)
  @ConfigurationProperties(prefix = "spring.datasource.master.hikari")
  public DataSource masterDataSource() {
    return DataSourceBuilder.create()
      .type(HikariDataSource.class)
      .build();
  }

  @Bean(SLAVE_DATASOURCE)
  @ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
  public DataSource slaveDataSource() {
    return DataSourceBuilder.create()
      .type(HikariDataSource.class)
      .build();
  }

  @Bean
  @DependsOn({MASTER_DATASOURCE, SLAVE_DATASOURCE})
  public DataSource routingDataSource(
    @Qualifier(MASTER_DATASOURCE) DataSource masterDataSource,
    @Qualifier(SLAVE_DATASOURCE) DataSource slaveDataSource) {
    RoutingDataSource routingDataSource = new RoutingDataSource();
    Map<Object, Object> datasource = new HashMap<>();
    datasource.put("master", masterDataSource);
    datasource.put("slave", slaveDataSource);
    routingDataSource.setTargetDataSources(datasource);
    routingDataSource.setDefaultTargetDataSource(masterDataSource);
    return routingDataSource;
  }

  @Primary
  @Bean
  @DependsOn("routingDataSource")
  public LazyConnectionDataSourceProxy dataSource(DataSource routingDataSource){
    return new LazyConnectionDataSourceProxy(routingDataSource);
  }
}