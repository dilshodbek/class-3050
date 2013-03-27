
package com.marakana.addressbook;

/**
 * Creates address books. Dependencies are resolved using setter methods. That
 * means, that if you want "foo", create a setter method called
 * "public void setFoo(String foo)"
 */
public interface AddressBookFactory {
    public AddressBook getAddressBook() throws AddressBookException;
}
