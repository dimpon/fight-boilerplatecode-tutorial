package com.dimpon.tutorals.validation.dto;

import com.dimpon.tutorals.validation.custom.ValidateAutoAndOwner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Dmitrii Ponomarev
 */
@ValidateAutoAndOwner
@Getter
@AllArgsConstructor
@ToString
public class OwnerAndAutoPair {
	final Owner owner;
	final Auto auto;
}
