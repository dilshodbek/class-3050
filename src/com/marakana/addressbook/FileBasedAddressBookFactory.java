
package com.marakana.addressbook;

import java.io.File;
import java.util.Properties;

public class FileBasedAddressBookFactory implements AddressBookFactory {

    @Override
    public AddressBook getAddressBook(Properties properties) throws AddressBookException {
        return new FileBasedAddressBook(new File((String) properties.get("dir")));
    }
}
