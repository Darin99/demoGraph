package com.example.demo.services.queryServices;

public class QueryBuilder {

    private static final String OPEN_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";
    private static final String TO_WORD = "TO";
    private static final String SINGLE_QUOTE = "'";
    private static final String EQUAL_SIGN = "=";
    private static final String SEPARATOR = " ";

    private final StringBuilder builder;

    public QueryBuilder() {
        builder = new StringBuilder();
    }

    public QueryBuilder withCreateClause() {
        builder.append("CREATE").append(SEPARATOR);
        return this;
    }

    public QueryBuilder appendEdgeWord() {
        builder.append("EDGE").append(SEPARATOR);
        return this;
    }

    public QueryBuilder withUpsertClause() {
        builder.append(SEPARATOR).append("UPSERT").append(SEPARATOR);
        return this;
    }

    public QueryBuilder withFromClause() {
        builder.append("FROM").append(SEPARATOR);
        return this;
    }

    public QueryBuilder withSelectClause() {
        builder.append("SELECT").append(SEPARATOR);
        return this;
    }

    public QueryBuilder withWhereClause() {
        builder.append("WHERE").append(SEPARATOR);
        return this;
    }

    public QueryBuilder withUpdateClause() {
        builder.append("UPDATE").append(SEPARATOR);
        return this;
    }

    public QueryBuilder withSetClause() {
        builder.append("SET").append(SEPARATOR);
        return this;
    }

    public QueryBuilder appendVertex(String vertex) {
        builder.append(vertex).append(SEPARATOR);
        return this;
    }

    public QueryBuilder appendEdge(String edge) {
        builder.append(edge);
        return this;
    }

    public QueryBuilder appendPropAndValue(String property, String value) {
        builder.append(property).append(SEPARATOR).append(EQUAL_SIGN)
                .append(SEPARATOR).append(SINGLE_QUOTE).append(value).append(SINGLE_QUOTE);
        return this;
    }

    public QueryBuilder appendOpenBracket() {
        builder.append(OPEN_BRACKET);
        return this;
    }

    public QueryBuilder appendCloseBracket() {
        builder.append(CLOSING_BRACKET);
        return this;
    }

    public QueryBuilder appendToWord() {
        builder.append(SEPARATOR).append(TO_WORD).append(SEPARATOR);
        return this;
    }

    public String build() {
        return this.builder.toString();
    }
}