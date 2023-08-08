package com.github.golovnyakpa.hw10.dto.sms;

import com.github.golovnyakpa.hw10.dto.ChatsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public class ChatSessions {
    public List<ChatSession> chatSessions;

    public ChatsDto toChatsDto() {
        return new ChatsDto(
                chatSessions.stream().map(ChatSession::toChatDto).collect(Collectors.toList())
        );
    }
}
