package utils;

import java.util.List;
import java.util.stream.Collectors;

public class StringHelper {

	public static String convertListOfStringstoString(List<String> list) {
		return list.stream().collect(Collectors.joining());
	}

	public static String replaceDotWithHyphen(String string) {
		return string.replaceAll("[.]", "-");
	}

	public static String replaceSpacewithHyphen(String string) {
		return string.replaceAll("\\s", "-");
	}
	
	public static String removeEveryPunctuation(String string) {
		return string.replaceAll("[^a-zA-Z0-9]", "");
	}

}
