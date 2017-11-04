package ar.uba.fi.tdd.rulogic.model;
import java.util.*;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidFormatException;;

public class Fact extends Sentence{
	private int argsCount;
	private List<List<String>> args;

	public Fact(String name, int argsCount){
		super(name);
		this.argsCount = argsCount;
		this.args = new ArrayList<List<String>>();
	}

	public void addArguments(List<String> args) throws InvalidFormatException{
		if(args.size() != argsCount){
			throw new InvalidFormatException("La cantidad de argumentos no es la indicada en la definición de la Fact");
		}
		this.args.add(args);
	}

	public boolean evaluate(List<String> params){
		if(params == null || this.argsCount != params.size()){
			return false;
		}
		for(List<String> arg : this.args){
			int i = 0;
			boolean isTrue = true;
			for(String a : arg){
				if(!a.equals(params.get(i))){
					isTrue = false;
				}
				i++;
			}
			if(isTrue){
				return true;
			}
		}
		return false;
    }
}
