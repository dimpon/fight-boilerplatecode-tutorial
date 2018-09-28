package com.dimpon.tutorals.lombok.npe;

import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Dmitrii Ponomarev
 */

@Setter
public class NonNullDto {

    @NonNull
    private String name;

    public String pass(@NonNull String p){
        return p.toLowerCase();
    }


}
