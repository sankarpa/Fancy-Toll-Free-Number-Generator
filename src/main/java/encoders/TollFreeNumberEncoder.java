package encoders;

import directory.PhoneDirectory;
import models.PhoneNumber;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TollFreeNumberEncoder {

	private PhoneNumberEncoder phoneNumberEncoder = new PhoneNumberEncoder();
	private PhoneDirectory phoneDirectory = PhoneDirectory.getInstance();

	public void process(){
		try {
			encodeAndPrintPhoneNumbersOfDirectory();
			printNonEncodeableNumbers();
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	private void encodeAndPrintPhoneNumbersOfDirectory(){
		print(encodePhoneNumbersOfDirectory());
	}

	private Map<String, List<String>> encodePhoneNumbersOfDirectory(){
		return getEncodeablePhoneNumbers()
				.stream()
				.collect(Collectors.toMap(PhoneNumber::getNumber,
						phoneNumberEncoder::allPossibleEncodingsForPhoneNumber));
	}

	private List<PhoneNumber> getEncodeablePhoneNumbers(){
		return collectPhoneNumber(true);
	}

	private List<PhoneNumber> getNonEncodeablePhoneNumbers(){
		return collectPhoneNumber(false);
	}

	private  List<PhoneNumber> collectPhoneNumber(Boolean encodeable) {
		return phoneDirectory.getListOfNumbers().stream().filter(
				phoneNumber -> phoneNumber.canBeEncoded() == encodeable
		).collect(Collectors.toList());
	}

	private void print(Map<String,List<String>> map) {
		map.entrySet().stream().forEach(
				entry->{
					System.out.println("--------------------------");
					System.out.println("Possible Encodings for : " + entry.getKey() );
					System.out.println("--------------------------");

					entry.getValue().forEach(encoding ->{
						System.out.print("|");
						System.out.print("\t");
						System.out.print(encoding);
						System.out.print("\t");
						System.out.print("|");
						System.out.println();
					});

					System.out.print("------------------");
				}
		);
	}

	private void printNonEncodeableNumbers(){
		List<PhoneNumber> nonEncodeableNumbers = getNonEncodeablePhoneNumbers();

		if(nonEncodeableNumbers.size() > 0){
			System.out.println();
			System.out.println("--------------------------");
			System.out.println("Below Numbers Cannot be Encoded:");
			nonEncodeableNumbers.stream().map(PhoneNumber::getNumber).forEach(System.out::println);
		}
	}
}
