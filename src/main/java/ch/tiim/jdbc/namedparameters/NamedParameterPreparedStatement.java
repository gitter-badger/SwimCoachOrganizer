package ch.tiim.jdbc.namedparameters;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NamedParameterPreparedStatement extends DelegatingPreparedStatement {
    private final List<String> orderedParameters;

    /**
     * Class to hold the results of SQL parsing (used by factory methods)
     */
    static class ParseResult {
        private String sql;
        private List<String> orderedParameters;

        public ParseResult(String sql, List<String> orderedParameters) {
            this.sql = sql;
            this.orderedParameters = orderedParameters;
        }

        public String getSql() {
            return sql;
        }

        public List<String> getOrderedParameters() {
            return orderedParameters;
        }
    }

    /**
     * Parse the query string containing named parameters and result a parse result, which holds
     * the parsed sql (named parameters replaced by standard '?' parameters and an ordered list of the
     * named parameters.
     * <p>
     * SQL parsing code borrowed from Adam Crume. Thanks Adam.
     * See <a href="http://www.javaworld.com/article/2077706/core-java/named-parameters-for-preparedstatement.html?page=2">http://www.javaworld.com/article/2077706/core-java/named-parameters-for-preparedstatement.html?page=2</a>
     *
     * @param query Query containing named parameters
     * @return ParseResult
     */
    public static ParseResult parse(String query) {
        List<String> orderedParameters = new ArrayList<String>();
        int length = query.length();
        StringBuffer parsedQuery = new StringBuffer(length);
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;
        boolean inSingleLineComment = false;
        boolean inMultiLineComment = false;

        for (int i = 0; i < length; i++) {
            char c = query.charAt(i);
            if (inSingleQuote) {
                if (c == '\'') {
                    inSingleQuote = false;
                }
            } else if (inDoubleQuote) {
                if (c == '"') {
                    inDoubleQuote = false;
                }
            } else if (inMultiLineComment) {
                if (c == '*' && query.charAt(i + 1) == '/') {
                    inMultiLineComment = false;
                }
            } else if (inSingleLineComment) {
                if (c == '\n') {
                    inSingleLineComment = false;
                }
            } else {
                if (c == '\'') {
                    inSingleQuote = true;
                } else if (c == '"') {
                    inDoubleQuote = true;
                } else if (c == '/' && query.charAt(i + 1) == '*') {
                    inMultiLineComment = true;
                } else if (c == '-' && query.charAt(i + 1) == '-') {
                    inSingleLineComment = true;
                } else if (c == ':' && i + 1 < length && Character.isJavaIdentifierStart(query.charAt(i + 1))) {
                    int j = i + 2;
                    while (j < length && Character.isJavaIdentifierPart(query.charAt(j))) {
                        j++;
                    }
                    String name = query.substring(i + 1, j);
                    orderedParameters.add(name);
                    c = '?'; // replace the parameter with a question mark
                    i += name.length(); // skip past the end if the parameter
                }
            }
            parsedQuery.append(c);
        }
        return new ParseResult(parsedQuery.toString(), orderedParameters);
    }

    //factory methods for all possible PreparedStatement constructors
    public static NamedParameterPreparedStatement createNamedParameterPreparedStatement(Connection conn, String sql) throws SQLException {
        ParseResult parseResult = parse(sql);
        return new NamedParameterPreparedStatement(conn.prepareStatement(parseResult.getSql()), parseResult.getOrderedParameters());
    }

    public static NamedParameterPreparedStatement createNamedParameterPreparedStatement(Connection conn, String sql,
                                                                                        int autoGeneratedKeys) throws SQLException {
        ParseResult parseResult = parse(sql);
        return new NamedParameterPreparedStatement(conn.prepareStatement(parseResult.getSql(), autoGeneratedKeys),
                parseResult.getOrderedParameters());
    }

    public static NamedParameterPreparedStatement createNamedParameterPreparedStatement(Connection conn, String sql,
                                                                                        int[] columnIndexes) throws SQLException {
        ParseResult parseResult = parse(sql);
        return new NamedParameterPreparedStatement(conn.prepareStatement(parseResult.getSql(), columnIndexes),
                parseResult.getOrderedParameters());
    }

    public static NamedParameterPreparedStatement createNamedParameterPreparedStatement(Connection conn, String sql,
                                                                                        String[] columnNames) throws SQLException {
        ParseResult parseResult = parse(sql);
        return new NamedParameterPreparedStatement(conn.prepareStatement(parseResult.getSql(), columnNames),
                parseResult.getOrderedParameters());
    }

    public static NamedParameterPreparedStatement createNamedParameterPreparedStatement(Connection conn, String sql,
                                                                                        int resultSetType, int resultSetConcurrency) throws SQLException {
        ParseResult parseResult = parse(sql);
        return new NamedParameterPreparedStatement(conn.prepareStatement(parseResult.getSql(), resultSetType, resultSetConcurrency),
                parseResult.getOrderedParameters());
    }

    public static NamedParameterPreparedStatement createNamedParameterPreparedStatement(Connection conn, String sql,
                                                                                        int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        ParseResult parseResult = parse(sql);
        return new NamedParameterPreparedStatement(conn.prepareStatement(parseResult.getSql(), resultSetType, resultSetConcurrency, resultSetHoldability),
                parseResult.getOrderedParameters());
    }

    /**
     * Private constructor (use factory methods)
     *
     * @param delegate          PreparedStatement delegate
     * @param orderedParameters Ordered list of named parameters produced during parsing
     */
    private NamedParameterPreparedStatement(PreparedStatement delegate, List<String> orderedParameters) {
        super(delegate);
        this.orderedParameters = orderedParameters;
    }

    public boolean hasNamedParameters() {
        return !orderedParameters.isEmpty();
    }

    private Collection<Integer> getParameterIndexes(String parameter) {
        Collection<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < orderedParameters.size(); i++) {
            if (orderedParameters.get(i).equals(parameter)) {
                //add i + 1, since all indexes ever are 0 based EXCEPT JDBC PARAMS WHYYYYY
                indexes.add(i + 1);
            }
        }
        if (indexes.isEmpty()) {
            throw new IllegalArgumentException(String.format("SQL statement doesn't contain the parameter '%s'",
                    parameter));
        }
        return indexes;
    }

    public void setNull(String parameter, int sqlType) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setNull(i, sqlType);
        }
    }

    public void setBoolean(String parameter, boolean x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setBoolean(i, x);
        }
    }

    public void setByte(String parameter, byte x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setByte(i, x);
        }
    }

    public void setShort(String parameter, short x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setShort(i, x);
        }
    }

    public void setInt(String parameter, int x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setInt(i, x);
        }
    }

    public void setLong(String parameter, long x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setLong(i, x);
        }
    }

    public void setFloat(String parameter, float x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setFloat(i, x);
        }
    }

    public void setDouble(String parameter, float x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setDouble(i, x);
        }
    }

    public void setBigDecimal(String parameter, BigDecimal x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setBigDecimal(i, x);
        }
    }

    public void setString(String parameter, String x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setString(i, x);
        }
    }

    public void setBytes(String parameter, byte[] x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setBytes(i, x);
        }
    }

    public void setDate(String parameter, Date x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setDate(i, x);
        }
    }

    public void setTime(String parameter, Time x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setTime(i, x);
        }
    }

    public void setTimestamp(String parameter, Timestamp x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setTimestamp(i, x);
        }
    }

    public void setAsciiStream(String parameter, InputStream x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setAsciiStream(i, x);
        }
    }

    @Deprecated
    public void setUnicodeStream(String parameter, InputStream x, int length) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setUnicodeStream(i, x, length);
        }
    }

    public void setBinaryStream(String parameter, InputStream x, int length) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setBinaryStream(i, x, length);
        }
    }

    public void setObject(String parameter, Object x, int targetSqlType, int scale) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setObject(i, x, targetSqlType, scale);
        }
    }

    public void setObject(String parameter, Object x, int targetSqlType) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setObject(i, x, targetSqlType);
        }
    }

    public void setObject(String parameter, Object x) throws SQLException {
        for (Integer i : getParameterIndexes(parameter)) {
            getDelegate().setObject(i, x);
        }
    }
}