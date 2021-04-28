package com.example.demo.services.queryServices;

public class CreateEdgeQueryBuilder {

    private static final String OPEN_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";
    private static final String TO_WORD = "TO";

    private StringBuilder builder;

    public CreateEdgeQueryBuilder() {
        builder = new StringBuilder();
    }

    public CreateEdgeQueryBuilder withCreateClause(String edge) {
        builder.append("CREATE EDGE ").append(edge).append(" ");
        return this;
    }

    public CreateEdgeQueryBuilder withUpsertClause() {
        builder.append("UPSERT ");
        return this;
    }

    public CreateEdgeQueryBuilder withFromClause() {
        builder.append("FROM ");
        return this;
    }

    public CreateEdgeQueryBuilder appendOpenBracket() {
        builder.append(OPEN_BRACKET);
        return this;
    }

    public CreateEdgeQueryBuilder withSelectClause(String classType) {
        builder.append("SELECT FROM ").append(classType).append(" ");
        return this;
    }

    public CreateEdgeQueryBuilder withWhereClause(String property, String value) {
        builder.append("WHERE ").append(property).append(" = '").append(value).append("'");
        return this;
    }

    public CreateEdgeQueryBuilder appendCloseBracket() {
        builder.append(CLOSING_BRACKET);
        return this;
    }

    public CreateEdgeQueryBuilder appendToWord() {
        builder.append(" ").append(TO_WORD).append(" ");
        return this;
    }

    public String build() {
        return this.builder.toString();
    }
}