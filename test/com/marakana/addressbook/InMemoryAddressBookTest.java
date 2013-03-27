
package com.marakana.addressbook;

public class InMemoryAddressBookTest extends AbstractAddressBookTest {
    @Override
    protected AddressBook buildAddressBook() {
        return new InMemoryAddressBook();
    }
}
