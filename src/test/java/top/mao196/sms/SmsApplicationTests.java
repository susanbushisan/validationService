package top.mao196.sms;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.mao196.sms.entity.QueryCode;
import top.mao196.sms.mapper.SmsMapper;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsApplicationTests {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    SmsMapper smsMapper;

    @Test
    public void contextLoads() {
        // 获取配置的数据源
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        // 查看配置数据源信息
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
        System.out.println(dataSourceProperties);
        //执行SQL,输出查到的数据
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<?> resultList = jdbcTemplate.queryForList("select * from sms");
        System.out.println("===>>>>>>>>>>>" + JSON.toJSONString(resultList));
    }
    @Test
    public void testSmsInfoService() {

        QueryCode queryCode = smsMapper.selectSmsInfoByPhone("15211287956");
        System.out.println(queryCode);
    }

}
