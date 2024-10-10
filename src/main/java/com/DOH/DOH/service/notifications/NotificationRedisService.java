package com.DOH.DOH.service.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationRedisService {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public NotificationRedisService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void saveNotification(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getNotification(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
