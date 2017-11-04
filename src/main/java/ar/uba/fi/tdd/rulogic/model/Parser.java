package ar.uba.fi.tdd.rulogic.model;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	public Parser(List<String> sentences){
		parsedDb = new ArrayList<Sentence>();
		this.db = new ArrayList<String>();
		for(String line : removeEmptySentences(sentences)){
			this.db.add(cleanSentence(line));
		}
	}

	private List<Sentence> parsedDb;
	private List<String> db;

	private List<String> removeEmptySentences(List<String> db){
		List<String> s = new ArrayList<String>();

		for(String line : db){
			if(line != null && !line.equals("")){
				s.add(line);
			}
		}

		return s;
	}

	private String cleanSentence(String sentence){
		return sentence.replaceAll(RegexCollection.strNoisyCharacters, "");
	}

	private boolean hasValidFormat(String sentence){
		return sentence.matches(RegexCollection.regValidSentence);
	}

	public String getSentenceName(String sentence){
		Matcher m = Pattern.compile(RegexCollection.strregFormatSentenceName)
			.matcher(sentence);
		if(m.find()){
			return m.group(0);
		}
		return null;
	}

	public List<String> getParameters(String sentence){
		Matcher m = Pattern.compile("\\([^,:\\-\\)\\(]{1,}(,[^,:\\-\\)\\(]{1,})*\\)")
			.matcher(sentence);
		if(m.find()){
			return Arrays.asList(m.group(0).replaceAll("/[\\(\\)]/", "").split(","));
		}
		return null;
	}

	public List<Sentence> parse(){
		if(hasInvalidSentences()){
			//TODO: EXCEPCION
		}
		for(String sentence : this.db){
			String name = getSentenceName(sentence);
			List<String> params = getParameters(sentence);			
			if(isRule(sentence)){
				parsedDb.add(new Rule(name, params, getRuleComponents(sentence)));
			}else{
				Fact fact = (Fact)findByName(name);
				if(fact != null){
					fact.addArguments(params);
				}else{
					fact = new Fact(name, params.size());
                    fact.addArguments(params);
                    parsedDb.add(fact);
				}
			}
		}
		return parsedDb;
	}
	
	private boolean hasInvalidSentences(){
		for(String sentence : this.db){
			if(!hasValidFormat(sentence)){
				return true;
			}
		}
		return false;
	}

	private boolean isRule(String sentence){
		return sentence.matches(".*:\\-.*");
	}

	private List<TrueFunction> getRuleComponents(String sentence){
		List<TrueFunction> trueFuncs = new ArrayList<TrueFunction>();

		String[] components = sentence.replaceAll("^([^:]*:\\-)", "")
				.replaceAll("\\),", ")|")
				.split("|");

		for( String component : components){
			String name = getSentenceName(component);
			List<String> params = getParameters(component);
			Sentence s = findByName(name);
			if(s == null){
				//TODO: EXCEPCION
			}					
			trueFuncs.add(new TrueFunction(params, s));	
		}

		return trueFuncs;
	}

	private Sentence findByName(String name){
		for(Sentence s : parsedDb){
			if(s.getName().equals(name)){
				return s;
			}
		}
		return null;
	}

}
