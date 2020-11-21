package com.mbouhda.reddit.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VoteException extends RuntimeException {

    private String message;
}
