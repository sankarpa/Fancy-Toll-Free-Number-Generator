package encoders;

import com.google.common.collect.Sets;
import dictionary.Dictionary;
import models.PhoneNumber;
import utils.StringHelper;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PhoneNumberEncoder {

	private Dictionary dictionary = Dictionary.getInstance();

	public  List<String> allPossibleEncodingsForPhoneNumber(PhoneNumber phoneNumber) {
		return Sets.cartesianProduct(encode(phoneNumber))
				.stream().
						map(((Function<List<String>, String>) StringHelper::convertListOfStringstoString)
								.andThen(StringHelper::replaceDotWithHyphen))
				.collect(Collectors.toList());
	}

	private List<Set<String>> encode(PhoneNumber phoneNumber) {
		return phoneNumber.
				characters().stream().
				map(this::possibleEncodings).
				collect(Collectors.toList());
	}


	private Set<String> possibleEncodings(String number) {
		String mapping = dictionary.getDictionaryData().getProperty(number);
		return mapping != null ?
				Sets.newHashSet(mapping.toUpperCase().split("\\s*,\\s*")) : Sets.newHashSet(number);
	}
}
