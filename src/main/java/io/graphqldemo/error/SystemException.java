package io.graphqldemo.error;

import graphql.ErrorType;
import graphql.GraphQLError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemException extends RuntimeException implements GraphQLError {

    private final List<Object> path;
    private final Map<String, Object> extensions = new HashMap<>();

    public SystemException(GraphQLError error) {
        this.path = error.getPath();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override public List<Object> getPath() {
        return path;
    }

    @Override
    public List getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }
}

