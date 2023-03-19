package io.graphqldemo.error;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.DefaultGraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class GraphQLExceptionHandler extends DefaultGraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(final List<GraphQLError> errors) {
        return errors.stream()
                .map(e ->{
                    findRealError((ExceptionWhileDataFetching) e);
                    if (super.isClientError(e)) {
                        return e;
                    } else if (isDuplicateKeyException((ExceptionWhileDataFetching) e)) {
                        return new DuplicateKeyException(e);
                    }
                    return new SystemException(e);
                })
                .collect(Collectors.toList());
    }

    private void findRealError(ExceptionWhileDataFetching error) {
//        var temp = error.getException().getCause();
//        var temp2 = temp;
//        while (temp != null) {
//            temp2 = temp;
//            temp = temp.getCause();
//            log.info(temp2.getClass().getName());
//        }
        Throwable rootCause = NestedExceptionUtils.getRootCause(error.getException());
        log.info("--------> {}, messages {}", rootCause.getClass().getName(), ExceptionUtils.getRootCauseMessage(error.getException()));
        log.info("?????? {}", NestedExceptionUtils.getMostSpecificCause(error.getException()).getClass().getName());
    }
    private boolean isDuplicateKeyException(final ExceptionWhileDataFetching error) {
        if (error.getException() instanceof DataIntegrityViolationException) {
            final DataIntegrityViolationException dataIntegrityViolationException = (DataIntegrityViolationException) error.getException();
            if (dataIntegrityViolationException.getCause() instanceof ConstraintViolationException) {
                final ConstraintViolationException cause = (ConstraintViolationException) dataIntegrityViolationException.getCause();
                return cause.getErrorCode() == 1062;
            }
        }
        return false;
    }

}
