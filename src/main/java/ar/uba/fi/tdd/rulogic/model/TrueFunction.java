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
		List<String> paramsToEval = new ArrayList<String>();
		for(String a : this.args){
			paramsToEval.add(params.get(a));
		}
        return this.evalFunc.evaluate(paramsToEval);
    }
}
