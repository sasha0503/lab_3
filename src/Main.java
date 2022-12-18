import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException {
        String path = "/home/oleksandr/IdeaProjects/lab_3/test_python_regex.txt";
        String content = Files.readString(Path.of(path));
        
        String comment_pattern = "#.*";
        String identifier_pattern = "[a-zA-z_][\\w_]*";
        String integer_literal_pattern = "\\b\\d+\\b";
        String floating_literal_pattern = "\\d+\\.\\d*";
        String imaginary_literal_pattern = "\\d+\\.\\d+j";
        String string_literal_pattern = "(\'[^\']*\'|\"[^\"]*\")";
        String special_string_literal_pattern = "(r|f|rf|fr)(\'[^\']*\'|\"[^\"]*\")";
        String boolean_literal_pattern = "True|False";
        List<String> operators = List.of( "\\+", "\\-", "\\*", "\\/", "\\%", "\\*\\*", "\\/\\/", "\\<\\<", "\\>\\>", "\\&",
                                         "\\|", "\\^", "\\~", "\\<", "\\<\\=", "\\>", "\\>\\=", "\\<\\>", "\\!\\=", "\\=\\=");
        List<String> delimiters = List.of("\\(", "\\)", "\\[", "\\]", "\\{", "\\}", "\\,", "\\:", "\\.", "\\`", "\\=", "\\;", "\\+\\=",
                                           "\\-\\=", "\\*\\=", "\\/\\=", "\\/\\/\\=", "\\%\\=", "\\&\\=", "\\|\\=", "\\^\\=", "\\>\\>\\=", "\\<\\<\\=", "\\*\\*\\=");
        List<String> reserved_words = List.of(
                          "\\band\\b", "\\bdel\\b", "\\bfor\\b", "\\bis\\b", "\\braise\\b", "\\bassert\\b",
                                   "\\belif\\b", "\\bfrom\\b", "\\blambda\\b", "\\breturn\\b", "\\bbreak\\b", "\\belse\\b",
                                   "\\bglobal\\b", "\\bnot\\b", "\\btry\\b", "\\bclass\\b", "\\bexcept\\b", "\\bif\\b",
                                   "\\bor\\b", "\\bwhile\\b", "\\bcontinue\\b", "\\bexec\\b", "\\bimport\\b", "\\bpass\\b",
                                   "\\bwith\\b", "\\bdef\\b", "\\bfinally\\b", "\\bin\\b", "\\bprint\\b", "\\byield\\b");

        List<String> string_list = new ArrayList<>();
        List<String> special_string_list = new ArrayList<>();
        List<String> number_list = new ArrayList<>();
        List<String> operator_list = new ArrayList<>();
        List<String> delimiter_list = new ArrayList<>();
        List<String> comment_list = new ArrayList<>();
        List<String> identifier_list = new ArrayList<>();
        List<String> keyword_list = new ArrayList<>();
        List<String> boolean_list = new ArrayList<>();
        List<String> error_list = new ArrayList<>();

        Matcher m;
        m = Pattern.compile(special_string_literal_pattern).matcher(content);
        while (m.find()) {
            special_string_list.add(m.group());
            content = content.replace(m.group(), "");
        }

        m = Pattern.compile(string_literal_pattern).matcher(content);
        while (m.find()) {
            string_list.add(m.group());
            content = content.replace(m.group(), "");
        }

        m = Pattern.compile(comment_pattern).matcher(content);
        while (m.find()) {
            comment_list.add(m.group());
            content = content.replace(m.group(), "");
        }

        m = Pattern.compile(imaginary_literal_pattern).matcher(content);
        while (m.find()) {
            number_list.add(m.group());
            content = content.replace(m.group(), "");
        }

        m = Pattern.compile(floating_literal_pattern).matcher(content);
        while (m.find()) {
            number_list.add(m.group());
            content = content.replace(m.group(), "");
        }

        m = Pattern.compile(integer_literal_pattern).matcher(content);
        while (m.find()) {
            number_list.add(m.group());
            content = content.replace(m.group(), "");
        }

        m = Pattern.compile(boolean_literal_pattern).matcher(content);
        while (m.find()) {
            boolean_list.add(m.group());
            content = content.replace(m.group(), "");
        }

        for (String reserved_word : reserved_words) {
            m = Pattern.compile(reserved_word).matcher(content);
            while (m.find()) {
                keyword_list.add(m.group());
                content = content.replace(m.group(), "");
            }
        }

        for (String operator : operators) {
            m = Pattern.compile(operator).matcher(content);
            while (m.find()) {
                operator_list.add(m.group());
                content = content.replace(m.group(), " ");
            }
        }

        for (String delimiter : delimiters) {
            m = Pattern.compile(delimiter).matcher(content);
            while (m.find()) {
                delimiter_list.add(m.group());
                content = content.replace(m.group(), " ");
            }
        }

        m = Pattern.compile(identifier_pattern).matcher(content);
        while (m.find()) {
            identifier_list.add(m.group());
            content = content.replaceFirst(m.group(), "");
        }

        content = content.replace(" ", "");
        for (String error : content.split("\n")){
            if(!Objects.equals(error, "")){
                error_list.add(error);
            }
        }
        System.out.printf("error_list: %1$s%n", error_list);
        System.out.printf("string_list: %1$s%n", string_list);
        System.out.printf("special_string_list: %1$s%n", special_string_list);
        System.out.printf("number_list: %1$s%n", number_list);
        System.out.printf("identifier_list: %1$s%n", identifier_list);
        System.out.printf("delimiter_list: %1$s%n", delimiter_list);
        System.out.printf("operator_list: %1$s%n", operator_list);
        System.out.printf("comment_list: %1$s%n", comment_list);
        System.out.printf("keyword_list: %1$s%n", keyword_list);
        System.out.printf("boolean_list: %1$s%n", boolean_list);
    }
}