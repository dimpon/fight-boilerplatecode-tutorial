package com.dimpon.tutorals.lombok.dto.sample1;

import lombok.*;

import java.util.Map;

/**
 * @author Dmitrii Ponomarev
 *
 */

// @Getter
// @Setter
@ToString
@EqualsAndHashCode
// @Data
public class SimpleDTO {

	private String name, familyName;
	private int age;

	//@Getter(value = AccessLevel.PUBLIC, onMethod_ = { @Deprecated })
	// @Setter(value = AccessLevel.PROTECTED, onMethod = @__({@Deprecated}))
	private String position;

}
