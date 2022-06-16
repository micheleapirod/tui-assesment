package com.tui.proof.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TuiUserException extends TuiException {

    public final static String message = "User not authorized";

}
