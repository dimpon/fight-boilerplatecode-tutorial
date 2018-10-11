package com.dimpon.tutorals.lombok.ulility;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * @author Dmitrii Ponomarev
 */
@UtilityClass
public class LambdaHolder {
    Function<Class<?>,Logger> logger = LoggerFactory::getLogger;
}
