package com.DOH.DOH.controller.chat;

import com.DOH.DOH.dto.chat.ChatRoomDTO;
import com.DOH.DOH.dto.chat.MessageDto;
import com.DOH.DOH.service.chat.ChatRoomService;
import com.DOH.DOH.service.chat.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatApiController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final ChatPageController chatPageController;

    public ChatApiController(SimpMessagingTemplate messagingTemplate, ChatRoomService chatRoomService, MessageService messageService, ChatPageController chatPageController) {
        this.messagingTemplate = messagingTemplate;
        this.chatRoomService = chatRoomService;
        this.messageService = messageService;
        this.chatPageController = chatPageController;
    }


    //채팅방 입장
    @PostMapping("/room")
    public ResponseEntity createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO){
        //todo:chatDTO 받아서 Is채팅방 있는지. 서비스 로직만들기
        //있으면 그 채팅방 아이디 저장
        //없으면 생성하고 아이디 저장
        //이거 반환값 없음
        log.info("넘어온DTO:{}",chatRoomDTO);
        if (chatRoomService.findChatRoomByRoomId(chatRoomDTO).isEmpty()){
            chatRoomService.createChatRoom(chatRoomDTO);
        }

        return ResponseEntity.ok().build();
    }

    //채팅 메시지 전송
    @MessageMapping("/message")  // WebSocket을 통해 /app/message 경로로 메시지를 받음
    public void sendMessage(MessageDto messageDto) {

        log.info("받은 메시지 :{}",messageDto.getContent());
        /*// 메시지를 DB에 저장
        messageService.saveMessage(messageDto);*/

        // 메시지를 해당 수신자에게 전송 (WebSocket)
        /*messagingTemplate.convertAndSendToUser(
                messageDto.getReceiver(),
                "/queue/messages" + messageDto.getRoomId(),
                messageDto
        );*/
    }
}
