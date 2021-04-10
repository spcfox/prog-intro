package expression.exceptions;

import expression.*;
import parser.*;

public class ExpressionParser implements Parser {
    public FullExpression parse(final String source) throws ParseException {
        return parse(new StringSource(source));
    }

    public FullExpression parse(CharSource source) throws ParseException {
        return new Parser(source).parseExpression();
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
            throw new UnexpectedEndException("End of expression expected");
        }

        public FullExpression parseSubExpression(boolean inBrackets) throws ParseException {
            FullExpression expression = parseGcdLcmOperand();
            while (true) {
                skipWhitespace();
                if (testAndSkip("gcd")) {
                    checkArgumentAfterKeyword();
                    expression = new CheckedGcd(expression, parseGcdLcmOperand());
                } else if (testAndSkip("lcm")) {
                    checkArgumentAfterKeyword();
                    expression = new CheckedLcm(expression, parseGcdLcmOperand());
                } else {
                    break;
                }
            }

            if (inBrackets) {
                expect(')');
            }

            return expression;
        }

        public FullExpression parseGcdLcmOperand() throws ParseException {
            FullExpression operand = parseTerm();
            while (true) {
                skipWhitespace();
                if (testAndSkip('+')) {
                    operand = new CheckedAdd(operand, parseTerm());
                } else if (testAndSkip('-')) {
                    operand = new CheckedSubtract(operand, parseTerm());
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
                    term = new CheckedMultiply(term, parseFactor());
                } else if (testAndSkip('/')) {
                    term = new CheckedDivide(term, parseFactor());
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
            } else if (testAndSkip("abs")) {
                checkArgumentAfterKeyword();
                return new CheckedAbs(parseFactor());
            } else if (testAndSkip("sqrt")) {
                checkArgumentAfterKeyword();
                return new CheckedSqrt(parseFactor());
            } else if (ch >= '0' && ch <= '9') {
                return parseConst();
            } else if (ch >= 'x' && ch <= 'z') {
                return parseVariable();
            }
            throw new UnexpectedSymbolException(
                    String.format("Unexpected symbol in at position %d: '%c' - %d", source.getPosition(), ch, (int) ch)
            );
        }

        private void checkArgumentAfterKeyword() throws ParseException {
            if (Character.isLetter(ch) || (ch >= '0' && ch <= '9')) {
                throw new UnexpectedEndException(
                        String.format("Unexpected symbol after keyword at position %d: '%c' - %d", source.getPosition(), ch, (int) ch)
                );
            }
        }

        private FullExpression parseUnaryMinus() throws ParseException {
            if (ch >= '0' && ch <= '9') {
                return parseConst(true);
            }
            skipWhitespace();
            return new CheckedNegate(parseFactor());
        }

        private FullExpression parseConst() throws ParseNumberException {
            return parseConst(false);
        }

        private FullExpression parseConst(boolean negative) throws ParseNumberException {
            final StringBuilder sb = new StringBuilder();
            if (negative) {
                sb.append('-');
            }

            while (ch >= '0' && ch <= '9') {
                sb.append(ch);
                nextChar();
            }
            skipWhitespace();
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw new ParseNumberException("Incorrect number: " + sb, e);
            }
        }

        private FullExpression parseVariable() {
            final String name = String.valueOf(ch);
            nextChar();
            skipWhitespace();
            return new Variable(name);
        }

        private void skipWhitespace() {
            while (testAndSkip(' ') || testAndSkip('\r') || testAndSkip('\n') || testAndSkip('\t')) {
            }
        }
    }
}
