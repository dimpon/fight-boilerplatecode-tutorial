package com.dimpon.tutorals.methodannprocessor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class HoundImpl implements Hound {

//    @LevelOfBarking(level = 5)
    @Override
    public void bark() {
      log.info("bark");

      throw new HoundException("this is hound");

    }

    public void bark(int level){
        log.info("bark with level "+level);
    }
}
