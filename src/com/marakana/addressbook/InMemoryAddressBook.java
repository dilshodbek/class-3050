
package com.marakana.addressbook;

import static com.marakana.addressbook.Util.notNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryAddressBook implements AddressBook {

    private static final Comparator<Contact> GET_ALL_COMPARATOR;

    static {
        Collection<Comparator<Contact>> comparators = new LinkedList<>();
        comparators.add(Contact.FIRST_NAME_COMPARATOR);
        comparators.add(Contact.LAST_NAME_COMPARATOR);
        comparators.add(Contact.EMAIL_COMPARATOR);
        GET_ALL_COMPARATOR = new CombinedComparator<Contact>(comparators);
    }

    private final Map<String, Contact> contacts = new HashMap<>();

    @Override
    public Contact getByEmail(String email) throws AddressBookException {
        email = notNull(email, "Email");
        return this.contacts.get(email);
    }

    @Override
    public List<Contact> getAll() throws AddressBookException {
        List<Contact> result = new ArrayList<>(this.contacts.values());
        Collections.sort(result, GET_ALL_COMPARATOR);
        return result;
    }

    @Override
    public void store(Contact contact) throws AddressBookException {
        this.contacts.put(contact.getEmail(), notNull(contact, "Contact"));
    }

    @Override
    public void deleteByEmail(String email) throws AddressBookException {
        this.contacts.remove(notNull(email, "Email"));
    }

    @Override
    public void close() throws AddressBookException {

    }
}
