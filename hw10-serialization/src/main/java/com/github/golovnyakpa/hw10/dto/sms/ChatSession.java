package com.github.golovnyakpa.hw10.dto.sms;

import com.github.golovnyakpa.hw10.dto.ChatDto;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class ChatSession {
    private long chatId;
    private String chatIdentifier;
    private String displayName;
    private int isDeleted;
    @Getter
    private List<Member> members;
    private List<Message> messages;

    public ChatDto toChatDto() {
        List<String> lasts = getLasts();
        List<ChatDto.MemberMessages> membersMessages = getMembersMessages();
        return new ChatDto(chatIdentifier, lasts, membersMessages);
    }

    private List<ChatDto.MemberMessages> getMembersMessages() {
        Map<String, List<ChatDto.Message>> membersMessages = new HashMap<>();
        for (Message msg : messages) {
            var newMsg = new ChatDto.Message(msg.getSendDate(), msg.getText());
            membersMessages.compute(msg.getBelongNumber(), (k, v) -> (v == null) ?
                    List.of(newMsg) : addMsg(v, newMsg)
            );
        }
        return membersMessages.entrySet()
                .stream()
                .map(x -> new ChatDto.MemberMessages(x.getKey(), orderMessages(x.getValue())))
                .collect(Collectors.toList());
    }

    private List<ChatDto.Message> orderMessages(List<ChatDto.Message> membersMessages) {
        return membersMessages
                .stream()
                .sorted(Comparator.comparingLong(x -> x.getSendDate().getTime())).collect(Collectors.toList());
    }

    private List<ChatDto.Message> addMsg(List<ChatDto.Message> lst, ChatDto.Message msg) {
        ArrayList<ChatDto.Message> arr = new ArrayList<>(lst);
        arr.add(msg);
        return arr;
    }

    private List<String> getLasts() {
        return members.stream().map(Member::getLast).collect(Collectors.toList());
    }
}
