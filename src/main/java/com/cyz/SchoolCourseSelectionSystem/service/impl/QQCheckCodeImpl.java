package com.cyz.SchoolCourseSelectionSystem.service.impl;

import com.cyz.SchoolCourseSelectionSystem.service.QQCheckCode;
import com.cyz.SchoolCourseSelectionSystem.utils.SendCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @author cyz
 * @date 2020-07-21 13:35
 */
@Service
public class QQCheckCodeImpl implements QQCheckCode {
    @Autowired
    private SendCode sendCode;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public String createNum(String qq) {
        String msg=null;
        if(redisTemplate.opsForValue().get(qq)!=null){
            msg="60秒内不能发送两次";
        }else{
           String code= String.valueOf(QQCheckCodeImpl.sixrandomnum());
           sendCode.sendcode(qq,code);
            redisTemplate.opsForValue().set(qq,code);
            redisTemplate.expire(qq,120, TimeUnit.SECONDS);
        }
        return msg;
    }

    @Override
    public String checkNum(String qq, String num) {
        String num1 = (String) redisTemplate.opsForValue().get(qq);
        if(num1!=null){
                if (num.equals(num1)){
                    return "success";
                }
                return "error";
            }else {
                return null;
            }
    }
    private static int sixrandomnum(){
        return (int)((Math.random()*9+1)*100000);
    }

}
