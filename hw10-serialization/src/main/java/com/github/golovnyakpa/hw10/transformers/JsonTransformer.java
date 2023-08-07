package com.github.golovnyakpa.hw10.transformers;

import com.github.golovnyakpa.hw10.dto.Dto;

import static com.github.golovnyakpa.hw10.utils.Utils.gson;

public class JsonTransformer implements DtoTransformer {
    @Override
    public String transform(Dto dto) {
        return gson.toJson(dto);
    }
}
