package duplicatefilefinder.app.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * Created by Ravi Nain on 4/15/2018.
 */
public final class DataBaseUtils {

    private DataBaseUtils() {
    }

    public static LinkedList<String> splitAndReplace(String name) {
        LinkedList<String> result = new LinkedList<>();
        Stream.of(StringUtils.splitByCharacterTypeCamelCase(name))
                .filter(part -> !StringUtils.isEmpty(part) && !part.trim().isEmpty())
                .forEach(part -> result.add(part.toLowerCase(Locale.ROOT)));
        return result;
    }

    public static String join(List<String> parts) {
        boolean firstPass = true;
        String separator = "";
        StringBuilder joined = new StringBuilder();
        for (String part : parts) {
            joined.append(separator).append(part);
            if (firstPass) {
                firstPass = false;
                separator = "_";
            }
        }
        return joined.toString();
    }
}
