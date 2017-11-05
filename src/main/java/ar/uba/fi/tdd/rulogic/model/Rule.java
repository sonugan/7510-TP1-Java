package ar.uba.fi.tdd.rulogic.model;
import java.util.*;

public class Rule extends Sentence {
	private String name;
	private List<String> args;
	private List<TrueFunction> trueFuncs;

	public Rule(String name, List<String> args, List<TrueFunction> trueFuncs){
		super(name);
		this.args = args;
		this.trueFuncs = trueFuncs;
	}

	public boolean evaluate(List<String> params){
		if(params == null || this.args.size() != params.size()){
			return false;
		}
		HashMap<String,String> paramsToEval = new HashMap<String,String>();
		int i = 0;
		for(String a : this.args){
			paramsToEval.put(a, params.get(i));
			i++;
		}
		for(TrueFunction f : this.trueFuncs){
			System.out.println(f);
			if(!f.evaluate(paramsToEval)){
				return false;
			}
		}
		return true;
    }
}
