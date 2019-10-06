package top.mao196.sms.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.mao196.sms.config.DependencySwitch;
import top.mao196.sms.service.ex.TokenException;
import top.mao196.sms.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token验证，必须有带有相应权限的人才能访问
 * @author susanbushisan
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DependencySwitch dependencySwitch;

    @Value("${sms.rootSecret}")
    private String rootSecret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(TokenUtil.HEADER_TOKEN_NAME);
        if(token == null ||!token.startsWith(TokenUtil.TOKEN_PREFIX)){
            log.info("can not resolving token header");
            throw new TokenException("can not resolving token header");
        }
        String reallyToken = token.substring(token.indexOf(" ") + 1);
        if(!TokenUtil.tokenValidate(reallyToken,rootSecret,redisTemplate,dependencySwitch)){
            log.info("token verification fail, token = " + token);
            throw new TokenException("token verification fail");
        }
        log.info("token verification success, token = " + token);
        return true;
    }

}
