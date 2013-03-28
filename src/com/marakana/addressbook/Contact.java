
package com.marakana.addressbook;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact implements Serializable, Comparable<Contact> {

    private static final long serialVersionUID = 8972079108707981970L;

    private static final Pattern PARSE_PATTERN = Pattern
            .compile("(?:([^ ]+) )?(?:([^ ]+) )?<([^> ]+)>(?: \\(([^ ]+)\\))?");

    public static Contact parse(String in) {
        Matcher m = PARSE_PATTERN.matcher(in);
        if (m.matches()) {
            return new Contact(m.group(1), m.group(2), m.group(3), m.group(4));
        } else {
            throw new IllegalArgumentException("Invalid input string: " + in);
        }
    }

    public static final Comparator<Contact> FIRST_NAME_COMPARATOR = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            return Util.compare(c1.getFirstName(), c2.getFirstName());
        }
    };

    public static final Comparator<Contact> LAST_NAME_COMPARATOR = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            return Util.compare(c1.getLastName(), c2.getLastName());
        }
    };

    public static final Comparator<Contact> EMAIL_COMPARATOR = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            return c1.compareTo(c2);
        }
    };

    public static final Comparator<Contact> DATE_OF_BIRTH_COMPARATOR = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            return Util.compare(c1.getDateOfBirth(), c2.getDateOfBirth());
        }
    };

    private String firstName;

    private String lastName;

    private transient String name;

    private final String email;

    private String phone;

    private Date dateOfBirth;

    public Contact(String email) {
        if (email == null) {
            throw new NullPointerException("Email must not be null");
        } else if (email.isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        } else if (!email.matches("[a-zA-Z0-9\\.\\-\\_]+@[a-zA-Z0-9\\.\\-\\_]+")) {
            throw new IllegalArgumentException("Invalid email [" + email + "]");
        }
        this.email = email;
    }

    public Contact(String firstName, String lastName, String email) {
        this(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public Contact(String firstName, String lastName, String email, String phone) {
        this(firstName, lastName, email);
        this.setPhone(phone);
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
            out.append(" (").append(this.getPhone()).append(")");
        }
        if (this.getDateOfBirth() != null) {
            out.append(' ').append(this.getDateOfBirth()); // ignore I18N
        }
        return out.toString();
    }

    @Override
    public int compareTo(Contact that) {
        return this.getEmail().compareTo(that.getEmail());
    }

}
