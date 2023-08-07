package com.github.golovnyakpa.hw10.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatsDto implements Dto {
    private List<ChatDto> chats;
}
