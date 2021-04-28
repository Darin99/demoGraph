package com.example.demo.services.queryServices;

public class UpdateQueryBuilder {

    private StringBuilder builder;

    public UpdateQueryBuilder() {
        builder = new StringBuilder();
    }

    public UpdateQueryBuilder withUpdateClause(String classType) {
        builder.append("UPDATE ").append(classType);
        return this;
    }

    public UpdateQueryBuilder withSetClause(String property, String value) {
        builder.append(" SET ").append(property).append(" = '").append(value).append("'");
        return this;
    }

    public UpdateQueryBuilder withWhereClause(String property, String value) {
        builder.append(" UPSERT WHERE ").append(property).append(" = '").append(value).append("'");
        return this;
    }

    public String build() {
        return builder.toString();
    }
}