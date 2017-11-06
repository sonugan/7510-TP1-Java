package ar.uba.fi.tdd.rulogic.model;

public class RegexCollection {
	public static final String strNoisyCharacters = "[ \\. \\t]";
    public static final String strregFormatSentenceName = "[^(]{1,}";
    public static final String strFormatRuleParamsName = "[A-Z]{1,}[_\\0-9A-Z]*";
    public static final String strFormatRuleParams = "\\(" + strFormatRuleParamsName + "(," + strFormatRuleParamsName + "){0,}\\)";
    public static final String strFormatFactQueryParams = "\\([^\\( :-]{1,}(,[^\\( :-]{1,}){0,}\\)";
    public static final String strRule = "^[^\\(]{1,}\\([A-Z0-9]{1,}(,[A-Z0-9]{1,})*\\):-[^\\(]{1,}\\([A-Z0-9]{1,}(,[A-Z0-9]{1,})*\\)([^\\(]{1,}\\([A-Z0-9]{1,}(,[A-Z0-9]{1,})*\\))*$";
    public static final String strFact = "^" + strregFormatSentenceName + strFormatFactQueryParams + "$";
	public static final String regValidSentence = "(^[a-zA-Z]{1,}[a-zA-Z0-9]{1,}\\([a-z0-9]{1,}(,[a-z0-9]{1,})*\\)$)|(^[a-zA-Z]{1,}[a-zA-Z0-9]{1,}\\([A-Z0-9]{1,}(,[A-Z0-9]{1,})*\\):-[a-zA-Z]{1,}[a-zA-Z0-9]{1,}\\([A-Z0-9]{1,}(,[A-Z0-9]{1,})*\\)(,[a-zA-Z]{1,}[a-zA-Z0-9]{1,}\\([A-Z0-9]{1,}(,[A-Z0-9]{1,})*\\))*$)";
}
