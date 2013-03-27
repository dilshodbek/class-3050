
package com.marakana.addressbook;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractAddressBookTest {
    private AddressBook addressBook;

    protected abstract AddressBook buildAddressBook() throws Exception;

    protected void tearDown(AddressBook addressBook) throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        this.addressBook = buildAddressBook();
    }

    @After
    public void tearDown() throws Exception {
        this.tearDown(this.addressBook);
        this.addressBook.close();
        this.addressBook = null;
    }

    @Test
    public void testStore() throws AddressBookException {
        String email = "john@smith.com";
        Contact contact = new Contact(email);
        this.addressBook.store(contact);
        Contact other = this.addressBook.getByEmail(email);
        Assert.assertEquals(contact, other);
        Assert.assertEquals(1, this.addressBook.getAll().size());
    }

    @Test(expected = NullPointerException.class)
    public void testStoreNullEmail() throws AddressBookException {
        this.addressBook.store(null);
    }

    @Test
    public void testStoreNullFirstName() throws AddressBookException {
        Contact contact = new ContactBuilder("john@smith.com").withLastName("Smith")
                .withPhone("1234").build();
        this.addressBook.store(contact);
        testEqualsComplete(contact, this.addressBook.getByEmail(contact.getEmail()));
    }

    @Test
    public void testStoreNullLastName() throws AddressBookException {
        Contact contact = new ContactBuilder("john@smith.com").withFirstName("John")
                .withPhone("1234").build();
        this.addressBook.store(contact);
        testEqualsComplete(contact, this.addressBook.getByEmail(contact.getEmail()));
    }

    private void testEqualsComplete(Contact expected, Contact actual) {
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getPhone(), actual.getPhone());
        Assert.assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
    }

    @Test
    public void testDeleteByEmail() throws AddressBookException {
        String email = "john@smith.com";
        Contact contact = new Contact(email);
        this.addressBook.store(contact);
        this.addressBook.deleteByEmail(email);
        Assert.assertNull(this.addressBook.getByEmail(email));
        Assert.assertEquals(0, this.addressBook.getAll().size());
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteByEmailWithNull() throws AddressBookException {
        this.addressBook.deleteByEmail(null);
    }
}
