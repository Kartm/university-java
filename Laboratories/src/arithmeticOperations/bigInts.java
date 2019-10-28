package arithmeticOperations;
import java.math.BigInteger;

public class bigInts {
	public static void main(String[] args) {
		BigInteger bigInt = BigInteger.valueOf(123123123);
		System.out.println(bigInt);
		bigInt = bigInt.add(new BigInteger("31231231231231231253443513451345312222222"));
		System.out.println(bigInt);
		bigInt = bigInt.subtract(new BigInteger("131231231230131313131313131313131313131312313"));
		System.out.println(bigInt);
	}
}
