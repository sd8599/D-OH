package com.DOH.DOH.dto.notifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private int id;                 // 알림 ID
    private String title;           // 알림 제목
    private String message;         // 알림 메시지
    private String notificationType; // 알림 유형 (INFO, ALERT, TASK)
    private String createdAt;       // 생성 시간
    private boolean isActive;       // 알림 활성 상태
}