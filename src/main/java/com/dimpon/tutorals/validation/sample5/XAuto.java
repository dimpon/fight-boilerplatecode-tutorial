package com.dimpon.tutorals.validation.sample5;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
@ToString
public class XAuto {
    @Size(max = 17, min = 17, message = "Vin must have 17 symbols")
    private String vin;

    @NotNull(message = "Mileage is required")
    private Long mileage;

    private boolean broken = false;

}
