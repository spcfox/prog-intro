package expression.parser;

import expression.*;
import parser.BaseParser;
import parser.CharSource;
import parser.ParseException;
import parser.StringSource;

public class ExpressionParser implements Parser {
    public FullExpression parse(final String source) {
        return parse(new StringSource(source));
    }

    public FullExpression parse(CharSource source) {
        try {
            return new Parser(source).parseExpression();
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static class Parser extends BaseParser {
        protected Parser(CharSource source) {
            super(source);
            nextChar();
        }

        public FullExpression parseExpression() throws ParseException {
            final FullExpression result = parseSubExpression(false);
            if (eof()) {
                return result;
            }
            throw new ParseException("End of expression expected");
        }

        public FullExpression parseSubExpression(boolean inBrackets) throws ParseException {
            FullExpression expression = parseOrOperand();
            while (true) {
                skipWhitespace();
                if (testAndSkip('|')) {
                    expression = new Or(expression, parseOrOperand());
                } else {
                    break;
                }
            }

            if (inBrackets) {
                expect(')');
            }

            return expression;
        }

        public FullExpression parseOrOperand() throws ParseException {
            FullExpression operand = parseXorOperand();
            while (true) {
                skipWhitespace();
                if (testAndSkip('^')) {
                    operand = new Xor(operand, parseXorOperand());
                } else {
                    break;
                }
            }
            return operand;
        }

        public FullExpression parseXorOperand() throws ParseException {
            FullExpression operand = parseAndOperand();
            while (true) {
                skipWhitespace();
                if (testAndSkip('&')) {
                    operand = new And(operand, parseAndOperand());
                } else {
                    break;
                }
            }
            return operand;
        }

        public FullExpression parseAndOperand() throws ParseException {
            FullExpression operand = parseTerm();
            while (true) {
                skipWhitespace();
                if (testAndSkip('+')) {
                    operand = new Add(operand, parseTerm());
                } else if (testAndSkip('-')) {
                    operand = new Subtract(operand, parseTerm());
                } else {
                    break;
                }
            }
            return operand;
        }

        public FullExpression parseTerm() throws ParseException {
            FullExpression term = parseFactor();
            while (true) {
                skipWhitespace();
                if (testAndSkip('*')) {
                    term = new Multiply(term, parseFactor());
                } else if (testAndSkip('/')) {
                    term = new Divide(term, parseFactor());
                } else {
                    break;
                }
            }
            return term;
        }

        public FullExpression parseFactor() throws ParseException {
            skipWhitespace();
            if (testAndSkip('(')) {
                return parseSubExpression(true);
            } else if (testAndSkip('-')) {
                return parseUnaryMinus();
            } else if (testAndSkip("flip")) {
                checkArgumentAfterKeyword();
                return new Flip(parseFactor());
            } else if (testAndSkip("low")) {
                checkArgumentAfterKeyword();
                return new Low(parseFactor());
            } else if (ch >= '0' && ch <= '9') {
                return parseConst();
            } else if (Character.isLetter(ch)) {
                return parseVariable();
            }
            throw new ParseException("Unexpected symbol in expression: '" + source.getPosition() + "' - " + (int) ch);
        }

        private void checkArgumentAfterKeyword() throws ParseException {
            if (Character.isLetter(ch) || (ch >= '0' && ch <= '9')) {
                throw new ParseException("Unexpected symbol after keyword: '" + source.getPosition() + "' - " + (int) ch);
            }
        }

        private FullExpression parseUnaryMinus() throws ParseException {
            if (ch >= '0' && ch <= '9') {
                return parseConst(true);
            }
            skipWhitespace();
            return new Negate(parseFactor());
        }

        private FullExpression parseConst() {
            return parseConst(false);
        }

        private FullExpression parseConst(boolean negative) {
            final StringBuilder sb = new StringBuilder();
            if (negative) {
                sb.append('-');
            }

            while (ch >= '0' && ch <= '9') {
                sb.append(ch);
                nextChar();
            }
            skipWhitespace();
            return new Const(Integer.parseInt(sb.toString()));
        }

        private FullExpression parseVariable() {
            final StringBuilder sb = new StringBuilder();
            while (Character.isLetter(ch)) {
                sb.append(ch);
                nextChar();
            }
            skipWhitespace();
            return new Variable(sb.toString());
        }

        private void skipWhitespace() {
            while (testAndSkip(' ') || testAndSkip('\r') || testAndSkip('\n') || testAndSkip('\t')) {
            }
        }
    }
}
