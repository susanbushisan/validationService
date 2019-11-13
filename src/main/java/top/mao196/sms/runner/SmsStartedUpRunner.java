package top.mao196.sms.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.mao196.sms.config.DependencySwitch;
import top.mao196.sms.util.TokenUtil;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author mao
 */
@Component
public class SmsStartedUpRunner implements ApplicationRunner {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DependencySwitch dependencySwitch;

    @Autowired
    private DataSource dataSource;

    private static Logger log = LoggerFactory.getLogger(SmsStartedUpRunner.class);

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws SQLException on error
     */


    @Override
    public void run(ApplicationArguments args) throws SQLException {
        log.info(dependencySwitch.toString());
        databaseTest();
        redisTest();

    }

    private void databaseTest() throws SQLException {

        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        log.info("当前数据库为: {}",metaData.getDatabaseProductName());
        if (dependencySwitch.isUsedDatabase()){
            String innerDataBase = "H2";
            if(innerDataBase.equals(metaData.getDatabaseProductName())){
                log.warn("如不需要使用数据库请在配置文件中将usedDatabase设置为true");
            }
        }

    }

    private void redisTest(){
        try {
            //测试redis连接
            if (dependencySwitch.isUsedRedis()){
                log.info("Redis 配置为开启状态，正在测试redis连接");
                redisTemplate.opsForList().range(TokenUtil.REDIS_SECRET_LIST, 0, -1);
                log.info("Redis连接正常");
            }

        } catch (Exception e) {
            log.warn("Redis连接异常，自动关闭Redis相关功能");
            dependencySwitch.setUsedRedis(false);
        }
    }

}
