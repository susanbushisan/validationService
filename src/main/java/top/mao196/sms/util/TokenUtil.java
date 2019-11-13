package top.mao196.sms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import top.mao196.sms.config.DependencySwitch;

import java.util.List;
import java.util.Objects;

/**
 * token验证工具类
 * @author susanbushisan
 */
public class TokenUtil {

    public static final String HEADER_TOKEN_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String REDIS_SECRET_LIST = "SECRET";

    private static Logger log = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * 对token进行验证,密钥的配置方式有2中，一种写在配置文件当中称之为rootSecret安全级别高且为必须配置的选项，另外一种是存放在redis中，为非必要选项
     *
     * @param token token
     * @return true为验证通过
     */
    public static boolean tokenValidate(String token, String rootSecret, RedisTemplate<String, String> redisTemplate, DependencySwitch dependencySwitch) {

        token = token.trim();
        if (Objects.equals(token, rootSecret)) {
            return true;
        }
        if (dependencySwitch.isUsedRedis()) {
            List<String> secretList = redisTemplate.opsForList().range(REDIS_SECRET_LIST, 0, -1);
            assert secretList != null;
            if (secretList.contains(token)) {
                return true;
            }
        }
        return false;
    }


}
