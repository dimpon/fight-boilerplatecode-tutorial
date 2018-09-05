package com.dimpon.tutorals.lombok.builders.builderinbuilder;

import lombok.extern.slf4j.Slf4j;

import static com.dimpon.tutorals.lombok.builders.builderinbuilder.Garage.*;

import static com.dimpon.tutorals.lombok.builders.builderinbuilder.Garage.Auto.Brand.*;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {
    public static void main(String[] args) {

        Garage garage = Garage.builder()
                .owner("John Doe")
                .car(Auto.of()
                        .brand(VW)
                        .model("Polo")
                        .vin(342342342)
                        .create())
                .car(Auto.of()
                        .brand(SHKODA)
                        .model("Oktavia")
                        .vin(21321434)
                        .create())
                .build();

        log.info(garage.autosDetails());

    }
}
