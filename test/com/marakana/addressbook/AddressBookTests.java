
package com.marakana.addressbook;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ContactTest.class, FileBasedAddressBookTest.class, InMemoryAddressBookTest.class,
        RemoteAddressBookTest.class
})
public class AddressBookTests {

}
