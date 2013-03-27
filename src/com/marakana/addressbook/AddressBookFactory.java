
package com.marakana.addressbook;

import java.util.Properties;

public interface AddressBookFactory {
    public AddressBook getAddressBook(Properties properties) throws AddressBookException;
}
