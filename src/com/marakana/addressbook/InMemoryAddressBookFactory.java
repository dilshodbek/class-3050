
package com.marakana.addressbook;

public class InMemoryAddressBookFactory implements AddressBookFactory {

    @Override
    public AddressBook getAddressBook() throws AddressBookException {
        return new InMemoryAddressBook();
    }

}
