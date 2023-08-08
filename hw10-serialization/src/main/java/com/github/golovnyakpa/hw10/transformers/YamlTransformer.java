package com.github.golovnyakpa.hw10.transformers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.github.golovnyakpa.hw10.dto.Dto;

public class YamlTransformer implements DtoTransformer {
    @Override
    public String transform(Dto dto) {
        YAMLMapper mapper = new YAMLMapper(
                new YAMLFactory()
                        .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                        .disable(YAMLGenerator.Feature.USE_NATIVE_TYPE_ID)
        );
        try {
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
