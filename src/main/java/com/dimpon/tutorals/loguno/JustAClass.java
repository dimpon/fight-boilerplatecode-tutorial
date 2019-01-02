package com.dimpon.tutorals.loguno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.loguno.Loguno;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
@Loguno.Logger

@NoArgsConstructor(onConstructor_ = @Loguno)

public class JustAClass {

	@Setter(onMethod_ = @Loguno("Setter for {} in called. New value:{}"),onParam_ = @Loguno)
	String name;

	private String convertToUpperCase(@Loguno String s) throws @Loguno NullPointerException {

		/*for (@Loguno int i = 0; i < 10; i++) {
			s = s + i;
		}*/



		@Loguno("custom message1")
		Object $loguno_val1=null;

		LOG.info("bla-bla");




		Object $loguno_val2;


		List<String> l = Stream.<String> builder().add("A").add("B").add("C").build().collect(Collectors.toList());

		for (@Loguno("ZZZZZ {}={}") String ll : l) {

		}

		return s.toUpperCase();
	}

	public static void main(String[] args) {
		JustAClass c = new JustAClass();

		c.setName("Vasya Pupkin");
		String small = c.convertToUpperCase(null);

	}
}
