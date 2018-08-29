package com.dimpon.tutorals.lombok.npe;

import lombok.Data;
import lombok.NonNull;

/**
 * @author Dmitrii Ponomarev
 */

public class NonNullDto {

    @NonNull
    private String name;

    public void pass(@NonNull String p){

    }


}
