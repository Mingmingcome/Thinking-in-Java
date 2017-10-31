//: strings/IntegerMatch.java

public class IntegerMatch {
	public static void main(String[] args) {
		System.out.println("-1234".matches("-?\\d+"));
		System.out.println("1234".matches("-?\\d+"));
		System.out.println("+1234".matches("-?\\d+"));
		System.out.println("+1234".matches("(-|\\+)?\\d+"));
	}
}