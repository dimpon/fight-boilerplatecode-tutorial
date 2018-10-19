package com.dimpon.tutorals.lombok.miscellaneous;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class HandMadeCleanup {


    public void dispose() throws IOException {
        log.info("close me");
        if(true)
            throw new IOException();
    }

    public void doSomeAction() throws IOException{
        log.info("do action");
        if(true)
            throw new IOException();
    }


    @SneakyThrows(IOException.class)
    public static void main(String[] args) {

        @Cleanup("dispose")
        HandMadeCleanup cleanup = new HandMadeCleanup();
        cleanup.doSomeAction();

    }

}
