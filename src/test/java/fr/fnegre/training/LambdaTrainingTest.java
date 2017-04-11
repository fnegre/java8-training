package fr.fnegre.training;

import static org.assertj.core.api.Assertions.entry;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class LambdaTrainingTest {

	private static final Person ELODIE = Person.builder().age(28).firstname("Elodie").lastname("Kuentz").build();
	private static final Person ISABELLE = Person.builder().age(55).firstname("Isabelle").lastname("Kuentz").build();
	private static final Person ERIK = Person.builder().age(61).firstname("Erik").lastname("Kuentz").build();
	private static final Person ALINE = Person.builder().age(30).firstname("Aline").lastname("Kuentz").build();
	private static final Person ERWAN = Person.builder().age(1).firstname("Erwan").lastname("Kuentz").build();
	private static final Person FLORIAN = Person.builder().age(30).firstname("Florian").lastname("Nègre").build();
	List<Integer> numbers = Arrays.asList(7, 4, 15, 1, 3, 5, 8, 10);
	List<String> fruits = Arrays.asList("orange", "banane", "orange", "cerise", "fraise", "cerise", "pomme", "cerise");
	public static final List<Person> persons = Arrays.asList(FLORIAN,
			ERWAN,
			ALINE,
			ERIK,
			ISABELLE,
			ELODIE);

	@Test
	public void testSumNumbersLessOrEqualsTo5WithMethodReference() {
		int sumNumbersLessOrEqualsTo5 = numbers.stream().filter(i -> i.intValue() <= 5).mapToInt(i -> i.intValue()).sum();
		Assertions.assertThat(sumNumbersLessOrEqualsTo5).isEqualTo(4+1+3+5);
	}

	@Test
	public void testRemoveNumberGreatherThan5() throws Exception {
		List<Integer> filterNumberGreatherThan5 = numbers.stream().filter(e -> e.intValue() <=5).collect(Collectors.toList());
		Assertions.assertThat(filterNumberGreatherThan5).containsExactly(4,1,3,5);
	}

	@Test
	public void testKeepOnlyNumberGreatherOrEqualThan5() throws Exception {
		int min = 5;
		List<Integer> filterNumberGreatherThan5 = numbers.stream().filter(e ->  e.intValue() >= min).collect(Collectors.toList());
		Assertions.assertThat(filterNumberGreatherThan5).containsExactly(7,15, 5, 8,10);
	}
	
	@Test
	public void testKeepPersonBetween20And30() {
		List<Person> result = persons.stream().filter(e -> e.getAge() >= 20 && e.getAge() <= 30).collect(Collectors.toList());
		Assertions.assertThat(result).containsExactly(FLORIAN, ALINE, ELODIE);
	}
	
	@Test
	public void testKeepPersonWithNameBeginningWithLetterESortedByAge() {
		List<Person> result = persons.stream().filter(e -> e.getFirstname().charAt(0) == 'E').sorted((Person p1,Person p2) -> p1.getAge() - p2.getAge()).collect(Collectors.toList());
		Assertions.assertThat(result).containsExactly(ERWAN, ELODIE, ERIK);
	}
	
	@Test 
	public void groupByFruit() {
		Map<String, Long> result = fruits.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Assertions.assertThat(result).containsOnly(entry("orange", 2l),
				entry("banane",1l),
				entry("cerise",3l),
				entry("fraise",1l),
				entry("pomme",1l));
	}

	@Test
	public void transformPersonLastNameToUpperCase() {
		Set<String> lastNameInUpperCase = persons.stream().map(e->e.getLastname().toUpperCase()).collect(Collectors.toSet());
		Assertions.assertThat(lastNameInUpperCase).hasSize(2).containsExactlyInAnyOrder("NÈGRE", "KUENTZ");		
	}

	@Test
	public void getPersonNameSeparatedByComma() {
		String reduce = persons.stream().map(b->b.getFirstname() + " " + b.getLastname()).reduce("", (a,b) -> a + "," + b);
		System.out.println(reduce);
		
	}

	@Test
	public void getPersonNameSeparatedByCommaWithJoin() {
		String reduce = persons.stream().map(b->b.getFirstname() + " " + b.getLastname()).collect(Collectors.joining(","));
		System.out.println(reduce);		
	}
	
	@Test
	public void getMax() {
		Integer max = numbers.stream().reduce(0, Math::max);
		Assertions.assertThat(max).isEqualTo(15);
	}	
	
	@Test
	public void getAverage() {
		Double average = numbers.stream().mapToDouble(Number::doubleValue).average().getAsDouble();
		Assertions.assertThat(average).isEqualTo(Double.valueOf(7+4+15+1+3+5+8+10)/8);
	}
	
	@Test
	public void add1AndMultiplyBy2() {
		Function<Integer, Integer> f = Function.<Integer>identity().andThen(i->i+1).andThen(i->i*2);
		numbers.forEach(e->System.out.println(f.apply(e)));
	}

}
