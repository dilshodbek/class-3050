
package com.marakana.addressbook;

public class AddressBookException extends Exception {

    private static final long serialVersionUID = -3290268082791056316L;

    public AddressBookException(String message) {
        super(message);
    }

    public AddressBookException(String message, Throwable cause) {
        super(message, cause);
    }

}
