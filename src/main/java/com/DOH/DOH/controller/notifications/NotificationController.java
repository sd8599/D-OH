package com.DOH.DOH.controller.notifications;

import com.DOH.DOH.dto.notifications.NotificationDTO;
import com.DOH.DOH.dto.notifications.NotificationUserDTO;
import com.DOH.DOH.service.notifications.NotificationService;
import com.DOH.DOH.service.user.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserSessionService userSessionService;

    @Autowired
    public NotificationController(NotificationService notificationService, UserSessionService userSessionService) {
        this.notificationService = notificationService;
        this.userSessionService = userSessionService;
    }

    // 전체 알림 목록 조회
    @GetMapping
    public List<NotificationDTO> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // 특정 사용자 알림 조회
    @GetMapping("/user/{userNum}")
    public List<NotificationUserDTO> getNotificationsForUser(@PathVariable int userNum) {
        return notificationService.getNotificationsForUser(userNum);
    }

    // 알림 생성 (관리자 또는 시스템에 의해 호출)
    @PostMapping
    public void createNotification(@RequestBody NotificationDTO notificationDTO) {
        notificationService.createNotification(notificationDTO);

        // 실시간 알림 전송
        notificationService.sendRealTimeNotification(notificationDTO);
    }

    // 특정 사용자에게 알림 추가 (알림 - 사용자 관계 생성)
    @PostMapping("/user")
    public void createNotificationForUser(@RequestBody NotificationUserDTO notificationUserDTO) {
        notificationService.createNotificationForUser(notificationUserDTO);

        // 특정 사용자에게 실시간 알림 전송
        notificationService.sendRealTimeNotificationForUser(notificationUserDTO);
    }

    // 특정 알림의 읽음 상태 업데이트
    @PutMapping("/read")
    public void updateReadStatus(@RequestBody NotificationUserDTO notificationUserDTO) {
        notificationService.updateReadStatus(notificationUserDTO);
    }
}
