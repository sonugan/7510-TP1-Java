package ar.uba.fi.tdd.rulogic.model;
import java.util.*;

public class TrueFunction {

	private List<String> args;
	private Sentence evalFunc;

	public TrueFunction(List<String> args, Sentence evalFunc){
		this.args = args;
		this.evalFunc = evalFunc;
	}

	public boolean evaluate(HashMap<String, String> params){
		System.out.println(this.evalFunc.getName());
        List<String> paramsToEval = new ArrayList<String>();
		for(String a : this.args){
			System.out.println(params.get(a));
			paramsToEval.add(params.get(a));
		}
        return this.evalFunc.evaluate(paramsToEval);
    }
}
