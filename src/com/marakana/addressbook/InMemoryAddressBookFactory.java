
package com.marakana.addressbook;

import java.util.Properties;

public class InMemoryAddressBookFactory implements AddressBookFactory {

    @Override
    public AddressBook getAddressBook(Properties properties) throws AddressBookException {
        return new InMemoryAddressBook();
    }

}
