package expression.exceptions;

import expression.TripleExpression;
import parser.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser {
    TripleExpression parse(String expression) throws ParseException, ParseException;
}
