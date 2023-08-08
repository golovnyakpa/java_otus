package com.github.golovnyakpa.hw10.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class ChatDto implements Dto {
    private String chatIdentifier;
    private List<String> last;
    private List<MemberMessages> membersMessages;

    public ChatDto(String chatIdentifier, List<String> lastMember, List<MemberMessages> membersMessages) {
        this.chatIdentifier = chatIdentifier;
        this.last = lastMember;
        this.membersMessages = membersMessages;
    }

    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MemberMessages {
        private String number;
        private List<Message> messages;
    }

    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Message {
        private Date sendDate;
        private String text;
    }
}
