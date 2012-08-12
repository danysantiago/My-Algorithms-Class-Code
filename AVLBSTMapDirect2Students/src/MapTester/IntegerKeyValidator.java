package MapTester;

import exceptions.InvalidKeyException;
import mapInterfaces.KeyValidator;

public class IntegerKeyValidator implements KeyValidator<Integer>
{
	public boolean validateKey(Integer key) throws InvalidKeyException {
		return key != null;
	}

}
