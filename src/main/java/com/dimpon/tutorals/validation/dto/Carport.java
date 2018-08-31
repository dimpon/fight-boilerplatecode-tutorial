package com.dimpon.tutorals.validation.dto;

import com.dimpon.tutorals.validation.custom.Equals;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
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

    @Equals(value = "Deutschland",message = "Wrong country")
    private String country;

}
