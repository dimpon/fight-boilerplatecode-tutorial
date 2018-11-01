package com.dimpon.tutorals.validation.sample2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true,fluent = true)
@RequiredArgsConstructor(staticName = "of")
public class Carport {

    @NotNull
    private String model;

    @NotBlank(message = "Engine code cannot be blank")
    private String engine;

}
