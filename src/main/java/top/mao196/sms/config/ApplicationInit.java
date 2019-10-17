package top.mao196.sms.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author susanbushisan
 */
@Component
public class ApplicationInit implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private DependencySwitch dependencySwitch;

    @Override
    public void run(String... args) {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Consumer.class);
        if(dependencySwitch.isUsedMq()){
            defaultListableBeanFactory.registerBeanDefinition("consumer",definitionBuilder.getBeanDefinition());
            applicationContext.getBean(Consumer.class);
        }

    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
