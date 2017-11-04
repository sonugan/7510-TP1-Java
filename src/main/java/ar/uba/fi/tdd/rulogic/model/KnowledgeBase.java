package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KnowledgeBase {

	private List<Sentence> db;
	public KnowledgeBase(String path) throws IOException{
		List<String> lines = new ArrayList<String>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);

		String line;
		while((line = br.readLine()) != null){
			lines.add(line);
		}
		parseDB(lines);
	}

	public boolean answer(String query) {
		QueryParser queryParser = new QueryParser(query);
        if(!queryParser.hasValidFormat()){
            //TODO: EXCEPCION
        }
        String name = queryParser.getName();
        List<String> params = queryParser.getParameters();
        Sentence expression = findByName(name);
        if(expression == null){
            return false;
        }
        return expression.evaluate(params);
	}

	private void parseDB(List<String> db){
		Parser parser = new Parser(db);
        this.db = parser.parse();
	}

	private Sentence findByName(String name){
		for(Sentence s : this.db){
			if(s.getName().equals(name)){
				return s;
			}
		}
		return null;
	}

}
