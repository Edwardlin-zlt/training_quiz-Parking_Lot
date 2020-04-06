import java.util.HashMap;
import java.util.Map;

public final class ParsedInitInfoUtil {
    private ParsedInitInfoUtil() {

    }

    public static Map<String, Integer> parseRawInfo(String rawInitInfo) {
        Map<String, Integer> initInfoMap = new HashMap<>();
        String[] rawEntrySets = rawInitInfo.split(",");
        for (String s : rawEntrySets) {
            String[] rawEntrySet = s.split(":");
            initInfoMap.put(rawEntrySet[0], Integer.parseInt(rawEntrySet[1]));
        }
        return initInfoMap;
    }
}
