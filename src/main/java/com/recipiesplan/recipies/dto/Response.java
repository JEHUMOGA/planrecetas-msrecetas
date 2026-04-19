package com.recipiesplan.recipies.dto;

import lombok.Data;

@Data
public class Response <T>{
    private Meta meta;
    private T data;
}
