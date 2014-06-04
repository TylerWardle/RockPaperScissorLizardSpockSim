package ca.bcit.comp2613.a00913377.util;

public class TeamWithSelfException extends RuntimeException{

	public String toString(){
		return "Cannot form a team with oneself";
	}
}
