package mapInterfaces;

import exceptions.InvalidKeyException;

public interface KeyValidator<K> {
   boolean validateKey(K key) 
   throws InvalidKeyException; 
}
