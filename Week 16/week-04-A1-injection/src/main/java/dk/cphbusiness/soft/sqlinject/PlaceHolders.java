package dk.cphbusiness.soft.sqlinject;

import static java.lang.String.join;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaceHolders {

    public static String field(String name, String... whitelist) {
        for (String whiteitem : whitelist) {
            if (name == null) {
                for (int i = 0; i < whitelist.length - 1; i++) {
                    String next = whitelist[i];
                    return next;
                }
            }
            if (name.equals(whiteitem)) {
                return name;
            }
        }
        throw new IllegalArgumentException();
    }

    // \0   An ASCII NUL (X'00') character	An ASCII NUL (X'00') character
    // \'   A single quote (') character
    // \"   A double quote (") character
    // \b   A backspace character
    // \n   A newline (linefeed) character
    // \r   A carriage return character
    // \t   A tab character
    // \Z   ASCII 26 (Control+Z); see note following the table
    // \\   A backslash (\) character
    // \%   A % character
    // \_   A _ character
    //
    // Used in sql = "SELECT * FROM T WHERE name = "+string(someName)
    // 
    // name = "Jens' or '' = '"
    // string(name) == "'Jens\' or \'\' = \''"
    public static String string(String value) {
        value = value.replaceAll("'", "\\\\'");
        return "'" + value.replaceAll("\"", "\\\\\"") + "'";
//        MySQL:
//        return "'" + value.replace("'", "\\'").replace("\"", "\\\"") + "'";
//        SQLite:
//        return "'" + value.replace("'", "''") + "'";
    }

    private static String list(Function<String, String> f, String... values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = f.apply(values[i]);
        }
        return "(" + String.join(",", values) + ")";
    }

    public static String stringList(String... values) {
//    return list(
//        new Function<String,String>() {
//          @Override
//          public String apply(String v) {
//            return string(v);
//            }
//          },
//        values
//        );

//        return list(v -> string(v), values);
        for (int i = 0; i < values.length; i++) {
            values[i] = string(values[i]);
        }
        return "(" + String.join(", ", values) + ")";
//    return "("+
//        Stream.of(values)
//          .map(v -> string(v))
//          .collect(Collectors.joining(","))+
//        ")";
    }

    public static String integer(String value) {
        try {
            Integer.parseInt(value);
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static String integerList(String... values) {
        return list(v -> integer(v), values);
//    for (int i = 0; i < values.length; i++) values[i] = integer(values[i]);
//    return "("+String.join(",", values)+")";
    }

    public static void main(String[] args) {
        System.out.println(string("Kurt' or '' = '"));
        System.out.println(stringList("AKA", "LAM", "Kurt' or '' = '"));
        System.out.println(integerList("7", "9", "13"));
        System.out.println(integerList("7", "9", "13 or 1 = 1"));

    }

}
