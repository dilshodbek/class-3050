
package com.marakana.addressbook;

import java.io.File;

public class FileBasedAddressBookFactory implements AddressBookFactory {

    private File dir;

    public void setDir(String dir) {
        this.dir = new File(dir);
    }

    @Override
    public AddressBook getAddressBook() throws AddressBookException {
        return new FileBasedAddressBook(this.dir);
    }
}
