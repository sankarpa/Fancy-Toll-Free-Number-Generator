package models;

import dictionary.Dictionary;
import utils.StringHelper;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PhoneNumber {

	private String phoneNumber;

	public PhoneNumber(String inputPhoneNumber) {
		phoneNumber = inputPhoneNumber;
	}

	public List<String> characters() {
		return Stream.of(phoneNumber.split("")).collect(Collectors.toList());
	}

	public String getNumber() {
		return phoneNumber;
	}

	public Boolean canBeEncoded() {
		String[] phoneNumberWithoutPunctuation =
				StringHelper.removeEveryPunctuation(phoneNumber).split("");

		AtomicReference<Boolean> canBeEncoded = new AtomicReference<>(true);

		IntStream.range(1, phoneNumberWithoutPunctuation.length).forEach(
				index -> {
					if (!(Dictionary.getInstance().hasEntry(phoneNumberWithoutPunctuation[index])) &&
							!(Dictionary.getInstance().hasEntry(phoneNumberWithoutPunctuation[index = 1]))) {
						canBeEncoded.set(false);
					}
				}
		);

		return canBeEncoded.get();
	}
}
