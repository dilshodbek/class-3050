
package com.marakana.addressbook;

import junit.framework.Assert;

import org.junit.Test;

public class ContactTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullEmail() {
        new Contact(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyEmail() {
        new Contact("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEmail() {
        new Contact("invalid email address");
    }

    @Test
    public void testConstructorWithRegularEmail() {
        String email = "valid@email.address.com";
        Contact contact = new Contact(email);
        Assert.assertEquals(email, contact.getEmail());
    }
}
