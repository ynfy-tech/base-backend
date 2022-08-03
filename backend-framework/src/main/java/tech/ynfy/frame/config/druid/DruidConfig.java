package tech.ynfy.frame.config.druid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ynfy.frame.constant.DataSourceTypeConstant;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * druid 配置多数据源
 * https://leehao.me/Spring-Cloud-MyBatis-Druid-%E5%8A%A8%E6%80%81%E6%95%B0%E6%8D%AE%E6%BA%90%E5%AE%9E%E7%8E%B0/
 */
@Configuration
public class DruidConfig {
    
    @Resource(name = DataSourceTypeConstant.MASTER)
    private DataSource masterDataSource;

    @Resource(name = DataSourceTypeConstant.SLAVE)
    private DataSource slaveDataSource;
    
    @Bean
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceTypeConstant.MASTER, masterDataSource);
        targetDataSources.put(DataSourceTypeConstant.SLAVE, slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }


}
