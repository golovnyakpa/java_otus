package com.github.golovnyakpa.hw10.transformers;

import com.github.golovnyakpa.hw10.dto.Dto;

public interface DtoTransformer {
    String transform(Dto dto); // ideally pass here an object for conversion
}
