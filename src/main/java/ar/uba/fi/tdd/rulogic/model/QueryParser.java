package ar.uba.fi.tdd.rulogic.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;


public class QueryParser {
	private String sentence;
	public QueryParser(String sentence){
		this.sentence = sentence;
		this.sentence = cleanSentence();
	}

	private String cleanSentence(){
		return sentence.replaceAll(RegexCollection.strNoisyCharacters, "");
	}

	public boolean hasValidFormat(){
		return sentence.matches(RegexCollection.regValidSentence);
	}

	public String getName(){
		Matcher m = Pattern.compile(RegexCollection.strregFormatSentenceName)
			.matcher(sentence);
		if(m.find()){
			return m.group(0);
		}
		return null;
	}

	public List<String> getParameters(){
		Matcher m = Pattern.compile("\\([^,:\\-\\)\\(]{1,}(,[^,:\\-\\)\\(]{1,})*\\)")
			.matcher(sentence);
		if(m.find()){
			return Arrays.asList(m.group(0).replaceAll("/[\\(\\)]/", "").split(","));
		}
		return null;
	}
}
