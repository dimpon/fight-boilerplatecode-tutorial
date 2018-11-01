package com.dimpon.tutorals.methodannprocessor;

import java.util.function.Function;

/**
 * @author Dmitrii Ponomarev
 */
public class UtiUti {




	public <T extends Throwable> String getGobo(String val, T ex) throws T {
		if (val == null)

			throw ex;
		return val;
	}


	public <T extends Throwable> String getGobo1(String val, Function<String, T> ex) throws T {

		if (val == null)
			throw ex.apply(val);

		return val;
	}

	public static void main(String[] args) {
		UtiUti u = new UtiUti();
		String x = "zzz";
		u.getGobo(null, new NullPointerException("  this is null" + x));

        u.getGobo1("xxx", HoundException::new);
	}
}
