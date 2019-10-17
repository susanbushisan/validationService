package top.mao196.sms.mapper;

import top.mao196.sms.entity.QueryCode;
import top.mao196.sms.entity.Sms;

/**
 * @author susanbushisan
 */
public interface SmsMapper {
    /**
     * 插入一条短信验证数据
     * @param sms 短信数据包含的内容
     * @return 受影响的行数
     */
    int insert(Sms sms);

    /**
     * 根据手机号查询最近有效发送
     * @param phone 手机号
     * @return 最近有效发送信息
     */
    QueryCode selectSmsInfoByPhone(String phone);

}
