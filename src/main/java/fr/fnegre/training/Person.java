package fr.fnegre.training;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Person {

	private String firstname;
	private String lastname;
	private int age;
	
}