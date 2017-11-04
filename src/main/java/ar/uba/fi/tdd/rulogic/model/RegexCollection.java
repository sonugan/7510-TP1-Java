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

    // //Se declaran regular expressions con los formatos que deben cumplir las sentencias y consultas
    // //Nombre de las sentencias: facts, rules y queries
    // this.regFormatSentenceName = new RegExp(strregFormatSentenceName)
    // this.formatRuleParamsName = new RegExp(strFormatRuleParamsName)
    // //Parametros de las rules
    // this.formatRuleParams = new RegExp(strFormatRuleParams)
    // this.formatFactQueryParams = new RegExp(strFormatFactQueryParams)
    // //Componentes de las rules
    // this.formatComponents = new RegExp(strregFormatSentenceName + strFormatRuleParams + "(," + strregFormatSentenceName + strFormatRuleParams + "){0,}")
    // //Regular expression que define el formato válido de una Rule
    // this.regRule = new RegExp(strRule)
    // this.regFact = new RegExp(strFact)
    // this.regValidSentece = new RegExp("[a-zA-Z]*")
}
