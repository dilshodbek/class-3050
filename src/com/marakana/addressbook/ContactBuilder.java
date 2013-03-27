
package com.marakana.addressbook;

import java.util.Date;

public class ContactBuilder {
    public final Contact contact;

    public ContactBuilder(String email) {
        this.contact = new Contact(email);
    }

    public ContactBuilder withFirstName(String firstName) {
        this.contact.setFirstName(firstName);
        return this;
    }

    public ContactBuilder withLastName(String lastName) {
        this.contact.setLastName(lastName);
        return this;
    }

    public ContactBuilder withPhone(String phone) {
        this.contact.setPhone(phone);
        return this;
    }

    public ContactBuilder withDateOfBirth(Date dateOfBirth) {
        this.contact.setDateOfBirth(dateOfBirth);
        return this;
    }

    public Contact build() {
        return this.contact;
    }
}
