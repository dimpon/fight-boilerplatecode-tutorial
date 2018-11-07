package com.dimpon.tutorals.lombok.npe;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Dmitrii Ponomarev
 */

@Setter

public class NonNullDto {

	@NonNull
	private String name;

    @NonNull
	public String pass( String p) {
		return p.toLowerCase();
	}

    @NonNull
    public String getName() {
        return name;
    }
}
