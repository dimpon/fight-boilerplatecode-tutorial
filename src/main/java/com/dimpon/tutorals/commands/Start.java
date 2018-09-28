package com.dimpon.tutorals.commands;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static com.dimpon.tutorals.commands.Start.TypeMe.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {

	private static final Logger LOG = LoggerFactory.getLogger(Start.class);

	public enum TypeMe {
		ONE, TWO, THREE;
	}

	public static void main(String[] args) {


		String s = "aaa.bb.ccc";
		String[] split = s.split("\\.");

		LOG.info("hi bro {} !","Chuvi");




		IntStream.of(1, 0, 1, 1, 1, 0);

		Supplier<Integer> supplier = () -> {
			return new Integer(0);
		};


		ObjIntConsumer<Integer> accumulator = (integer, value) -> {
			value = integer | value;
		};
		BiConsumer<Integer, Integer> combiner = (integer, integer2) -> {
			integer = integer | integer2;
		};

		Person p1 = new Person("Dmitrii", "360t", "int", "1");
		Person p2 = new Person("Christian", "360t", "int", "1");
		Person p3 = new Person("Maurice", "360t", "int", "1");
		Person p4 = new Person("James", "360t", "int", "1");

		List<Person> collect = Arrays.asList(p1, p2, p3, p4);// .filter(distinctByKey(Person::getCompany)).collect(Collectors.toList());

		Map<String, Map<String, List<Person>>> grouped = collect.stream().collect(
				Collectors.groupingBy(Person::getCompany,
						Collectors.groupingBy(Person::getDepartment)));

		List<Person> collectOfPerson = collect.stream()
				.map(PersonWraper::new)
				.distinct()
				.map(PersonWraper::getPerson)
				.collect(Collectors.toList());

		/*
		 * TreeSet<Person> collect1 = collect.stream()
		 * .collect(Collectors.toCollection(
		 * () -> new TreeSet<>((x1, x2) -> x1.getCompany().compareTo(x2.getCompany())
		 * | x1.getDepartment().compareTo(x2.getDepartment())
		 * | x1.getPlace().compareTo(x2.getPlace()))));
		 */
		TreeSet<Person> collect1 = collect.stream()
				.collect(Collectors.toCollection(
						() -> new TreeSet<>((x1, x2) -> compare(x1, x2,
								Person::getCompany,
								Person::getPlace,
								Person::getPlace))));

		System.out.printf("");
	}

	private static <T, U extends Comparable<U>> int compare(final T o1, final T o2, Function<T, U>... getters) {

		return 0;

		/*
		 * Arrays.stream(getters)
		 * .mapToInt(g -> g.apply(o1).compareTo(g.apply(o2)))
		 * .collect(
		 * ()->{new Integer(0)},
		 * (a,b)->{a|b},
		 * (a,b)->{a|b}
		 * 
		 * 
		 * );
		 */

	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	@Getter
	@AllArgsConstructor
	public static class Person {
		private String name, company, department, place;
	}


	@AllArgsConstructor
	@EqualsAndHashCode(of = { "company", "department" })
	@Getter
	public static class PersonWraper {
		@Delegate
		private final Person person;
	}
}
