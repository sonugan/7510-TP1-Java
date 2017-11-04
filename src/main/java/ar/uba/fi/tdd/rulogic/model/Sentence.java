package ar.uba.fi.tdd.rulogic.model;
import java.util.*;

public class Sentence {

	private String name;
	public Sentence(List<String> args, Sentence evalFunc){
		
	}

	public Sentence(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public boolean evaluate(List<String> params){
		return true;
    }
}
