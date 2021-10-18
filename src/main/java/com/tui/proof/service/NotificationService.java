package com.tui.proof.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class NotificationService {

    /**
     *
     */
    //here can be placed a notification as SMS/EMAIL. I choose for comodity a log;
    public void sendNotification() {
        log.info("Order is arrived");
    }


}
