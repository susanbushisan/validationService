package top.mao196.sms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.mao196.sms.interceptor.TokenInterceptor;

/**
 * @author susanbushisan
 */

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Bean
    public TokenInterceptor getTokenInterceptor() {

        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/**");
    }

}
