package Utils;

import exception.InvalidInitInfoException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParsedInitInfoUtil {
    private final static Pattern PATTERN = Pattern.compile("^([A-Z]:\\d+)(,[A-Z]:\\d+)*");

    private ParsedInitInfoUtil() {

    }


    public static Map<String, Integer> parseRawInfo(String rawInitInfo) {
        if (!isValidated(rawInitInfo)) {
            throw new InvalidInitInfoException();
        }
        Map<String, Integer> initInfoMap = new HashMap<>();
        String[] rawEntrySets = rawInitInfo.split(",");
        for (String s : rawEntrySets) {
            String[] rawEntrySet = s.split(":");
            initInfoMap.put(rawEntrySet[0], Integer.parseInt(rawEntrySet[1]));
        }
        return initInfoMap;
    }

    private static boolean isValidated(String rawInitInfo) {
        Matcher matcher = PATTERN.matcher(rawInitInfo);
        return matcher.matches();
    }
}
