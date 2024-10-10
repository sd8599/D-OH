package com.DOH.DOH.mapper.notifications;

import com.DOH.DOH.dto.notifications.NotificationDTO;
import com.DOH.DOH.dto.notifications.NotificationUserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {

    // 전체 알림 조회
    List<NotificationDTO> selectAllNotifications();

    // 특정 사용자의 알림 조회
    List<NotificationUserDTO> selectNotificationsByUser(int userNum);

    // 알림 생성
    void insertNotification(NotificationDTO notificationDTO);

    // 특정 사용자에게 알림 추가
    void insertNotificationForUser(NotificationUserDTO notificationUserDTO);

    // 읽음 상태 업데이트
    void updateReadStatus(NotificationUserDTO notificationUserDTO);
}