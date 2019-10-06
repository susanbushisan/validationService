package top.mao196.sms.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.mao196.sms.entity.Return;
import top.mao196.sms.service.ex.*;


/**
 * 全局处理异常
 *
 * @author susanbushisan
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({CommonException.class})
    public Return handleCommonException(CommonException e) {

        log.info("known exception," + e.getMessage());
        if (e instanceof PhoneFormatException || e instanceof CodeFormatException) {
            //参数有误
            return new Return(Return.STATUS_PARMERR, e.getMessage());
        } else if (e instanceof TokenException) {
            //权限验证不通过
            return new Return(Return.STATUS_AUTHORIZATION, e.getMessage());
        } else if (e instanceof ApiInvokeException) {
            //调用接口返回结果失败
            return new Return(Return.STATUS_APIERR, e.getMessage());
        } else {
            //其他未知异常
            return new Return(Return.STATUS_SERVICEERR, e.getMessage());
        }

    }

    @ExceptionHandler({Exception.class})
    public Return handleException(Exception e) {

        log.warn("unknown exception," + e.getMessage(), e);
        return new Return(Return.STATUS_SERVICEERR, e.getMessage());
    }

}
