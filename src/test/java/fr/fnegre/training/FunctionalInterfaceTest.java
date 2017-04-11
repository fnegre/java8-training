package fr.fnegre.training;

import java.util.function.Function;

import org.junit.Test;

public class FunctionalInterfaceTest {
	
	@Test
	public void getLoginPerson() {
		Function<Person, String> loginPersonFunction = (person) -> { return (person.getFirstname().charAt(0) + person.getLastname()).toLowerCase();};
		LambdaTrainingTest.persons.forEach(p->System.out.println(loginPersonFunction.apply(p)));
	}
}
