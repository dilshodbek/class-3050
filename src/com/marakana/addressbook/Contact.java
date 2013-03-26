
package com.marakana.addressbook;

import java.io.Serializable;
import java.util.Date;

public class Contact implements Serializable {

    private static final long serialVersionUID = 8972079108707981970L;

    private String firstName;

    private String lastName;

    private transient String name;

    private final String email;

    private String phone;

    private Date dateOfBirth;

    public Contact(String email) {
        if (email == null) {
            throw new NullPointerException("Email must not be null");
        }
        this.email = email;
    }

    public Contact(String firstName, String lastName, String email) {
        this(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.name = null;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.name = null;
    }

    public String getName() {
        if (this.name == null) {
            this.name = this.getFirstName() + " " + this.getLastName();
        }
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contact other = (Contact) obj;
        if (this.email == null) {
            if (other.email != null)
                return false;
        } else if (!this.email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(35);
        if (this.getFirstName() != null) {
            out.append(this.getFirstName()).append(' ');
        }
        if (this.getLastName() != null) {
            out.append(this.getLastName()).append(' ');
        }
        out.append('<').append(this.getEmail()).append('>');
        if (this.getPhone() != null) {
            out.append(' ').append(this.getPhone());
        }
        if (this.getDateOfBirth() != null) {
            out.append(' ').append(this.getDateOfBirth()); // ignore I18N
        }
        return out.toString();
    }
}
