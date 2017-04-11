package fr.fnegre.training;

import java.util.List;
import java.util.stream.Collectors;

public class LambdaTraining {

	public static void main(String[] args) {
//		testFilterNumberGreatherThan5(numbers).forEach(System.out::println);
//		System.out.println("Somme : " +sumNumbersLessOrEqualsTo5(numbers));
//		System.out.println("Somme : " +sumNumbersLessOrEqualsTo5WithMethodReference(numbers));
//		buildPersonWithAgeFromList(numbers).forEach(System.out::println);
		

	}
	public static int sumNumbersLessOrEqualsTo5WithdMethodReference(List<Integer> numbers) {
		return numbers.stream().filter(i -> i.intValue() <= 5).mapToInt(Integer::intValue).sum();
	}

}
