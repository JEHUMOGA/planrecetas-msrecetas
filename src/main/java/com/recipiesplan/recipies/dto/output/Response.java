package com.recipiesplan.recipies.dto.output;

import lombok.Data;

@Data
public class Response <T>{
    private Meta meta;
    private T data;
}
