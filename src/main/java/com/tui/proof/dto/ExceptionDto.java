package com.tui.proof.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ExceptionDto extends TuiOrderDto {

    private List<String> messages;

}
