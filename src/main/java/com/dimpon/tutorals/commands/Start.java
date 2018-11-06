package com.dimpon.tutorals.commands;

import com.dimpon.tutorals.validation.sample8.ServiceX;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Slf4j
public class Start {

	private static final Logger LOG = LoggerFactory.getLogger(Start.class);

	public enum TypeMe {
		ONE, TWO, THREE;
	}

	public <T, P> P getSumo(
			Class<T> type,
			Function<? extends T, P> property) {
		return null;
	}

	public <T, P> void setSumo(
			Class<T> type,
			Function<? super T, P> property,
			P value) {
	}

	public static void main(String[] args) {


        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        dt = c.getTime();

        System.out.printf(""+dt);

        Map<String, String> maap = new HashMap<>();

        maap.put("A1","A");
        maap.put("A2","A");
        maap.put("A3","A");
        maap.put("A4","A");
        maap.put("B1","B");
        maap.put("B2","B");
        maap.put("B3","B");
        maap.put("B4","B");


        Set<String> toRemove = new HashSet<>();

        for (Map.Entry<String, String> entity : maap.entrySet()) {

            if(entity.getValue().equals("A"))
                toRemove.add(entity.getKey());
        }


        toRemove.forEach(maap::remove);







		LinkedHashMap<String, String> params = new LinkedHashMap<>();

		params.put("a", "A");
		params.put("b", "B");
		params.put("c", "C");
		params.put("d", "D");

		params.forEach((s1, s2) -> log.info(s1 + " " + s2));

		Start start = new Start();
		/*
		 * start.setSumo(SumoDTO.class, SumoDTO::getAge, 12);
		 * 
		 * start.setSumo(SumoDTO.class, SumoDTO::getName, "xsxs");
		 * 
		 * String sumo = start.getSumo(SumoDTO.class, SumoDTO::getName);
		 */

		String s = "aaa.bb.ccc";
		String[] split = s.split("\\.");

		LOG.info("hi bro {} !", "Chuvi");

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
