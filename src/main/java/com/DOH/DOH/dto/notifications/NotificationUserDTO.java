package com.DOH.DOH.dto.notifications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationUserDTO {
    private int id;                // 관계 ID
    private int notificationId;    // 알림 ID
    private int userNum;           // 사용자 ID
    private boolean isRead;        // 읽음 상태
    private String readAt;         // 읽음 시간
}