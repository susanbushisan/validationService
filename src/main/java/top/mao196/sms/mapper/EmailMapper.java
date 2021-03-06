package top.mao196.sms.mapper;

import top.mao196.sms.entity.Email;
import top.mao196.sms.entity.QueryCode;

/**
 * @author susanbushisan
 */
public interface EmailMapper {

    /**
     * 插入邮箱验证码数据
     * @param email 包含email信息
     * @return 受影响的行数
     */
    int insert(Email email);

    /**
     * 查询最近一次邮箱验证码
     * @param email
     * @return
     */
    QueryCode selectEmailInfoByEmail(String email);

}
