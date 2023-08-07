package com.github.golovnyakpa.hw10;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.github.golovnyakpa.hw10.conf.Conf;
import com.github.golovnyakpa.hw10.dto.ChatsDto;
import com.github.golovnyakpa.hw10.dto.sms.ChatSessions;
import com.github.golovnyakpa.hw10.transformers.DtoTransformer;
import com.github.golovnyakpa.hw10.transformers.JsonTransformer;
import com.github.golovnyakpa.hw10.transformers.YamlTransformer;
import com.github.golovnyakpa.hw10.utils.Utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.golovnyakpa.hw10.utils.Utils.buildPath;
import static com.github.golovnyakpa.hw10.utils.Utils.gson;

public class Main {
    public static void main(String[] args) {
        String json = Utils.readFileToString(Conf.sourceFile);
        ChatSessions chatSessions = gson.fromJson(json, ChatSessions.class);

        ChatsDto chatsDto = chatSessions.toChatsDto();

        for (WritingDesc wd : getWritingDescriptions()) {
            Utils.writeStringToFile(wd.transformer.transform(chatsDto), wd.fileName);
        }

        parseResFromJson();
        parseResFromYaml();
        // output:
        // ChatsDto(chats=[ChatDto(chatIdentifier=Apple, last=[Saint-Petersburg], membersMessages=[ChatDto.MemberMessages(number=+79215553267, messages=[ChatDto.Message(sendDate=Fri Mar 31 14:53:34 MSK 2023, text=PROBLEM WARNING - No more free space on the disc), ChatDto.Message(sendDate=Fri Mar 31 15:09:15 MSK 2023, text=PROBLEM CRITICAL - The weather is not good for today)]), ChatDto.MemberMessages(number=+79219213267, messages=[ChatDto.Message(sendDate=Fri Mar 31 14:09:19 MSK 2023, text=PROBLEM CRITICAL - There is hight price of the order), ChatDto.Message(sendDate=Fri Mar 31 15:53:28 MSK 2023, text=PROBLEM WARNING - It's raining like dogs and cats)])])])
    }


    record WritingDesc(DtoTransformer transformer, Path fileName) {
    }

    private static List<WritingDesc> getWritingDescriptions() {
        return new ArrayList<>(Arrays.asList(
                new WritingDesc(new JsonTransformer(), buildPath(Conf.outFilePath, "res.json")),
                new WritingDesc(new YamlTransformer(), buildPath(Conf.outFilePath, "res.yaml"))
        )
        );
    }

    private static void parseResFromJson() {
        String js = Utils.readFileToString(buildPath(Conf.outFilePath, "res.json").toString());
        System.out.println(gson.fromJson(js, ChatsDto.class));
    }

    private static void parseResFromYaml() {
        String yml = Utils.readFileToString(buildPath(Conf.outFilePath, "res.yaml").toString());
        YAMLMapper mapper = new YAMLMapper();
        try {
            System.out.println(mapper.readValue(yml, ChatsDto.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
