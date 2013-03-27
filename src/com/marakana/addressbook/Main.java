
package com.marakana.addressbook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class Main {
    private static final String USAGE = "Usage: Main <address-book-factory-class> <prop-name>=<prop-value>";

    private static final String PROMPT = "address-book> ";

    private static final String HELP = "Usage: quit|help|list|get <email>|delete <email>|store <first-name> <last-name> <email> [phone]";

    private static void error(String error, boolean usage) {
        System.err.println("ERROR: " + error);
        if (usage) {
            System.err.println(HELP);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println(USAGE);
            return;
        }

        Class<?> clazz = Class.forName(args[0]);
        AddressBookFactory addressBookFactory = (AddressBookFactory) clazz.newInstance();

        for (int i = 1; i < args.length; i++) {
            String[] prop = args[i].split("=");
            String methodName = "set" + Character.toUpperCase(prop[0].charAt(0))
                    + prop[0].substring(1);
            Method method = clazz.getMethod(methodName, String.class);
            method.invoke(addressBookFactory, prop[1]);
        }

        AddressBook addressBook = addressBookFactory.getAddressBook();

        System.out.print(PROMPT);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                if ("quit".equals(line)) {
                    addressBook.close();
                    break;
                } else if ("help".equals(line)) {
                    System.out.println("OK");
                    System.out.println(HELP);
                } else if ("list".equals(line)) {
                    for (Contact contact : addressBook.getAll()) {
                        System.out.println(contact);
                    }
                    System.out.println("OK");
                } else {
                    String[] request = line.split(" ");
                    if (request.length >= 2) {
                        if ("get".equals(request[0])) {
                            Contact contact = addressBook.getByEmail(request[1]);
                            if (contact == null) {
                                error("No such contact", false);
                            } else {
                                System.out.println(contact);
                                System.out.println("OK");
                            }
                        } else if ("delete".equals(request[0])) {
                            addressBook.deleteByEmail(request[1]);
                            System.out.println("OK");
                        } else if ("store".equals(request[0]) && request.length >= 4) {
                            Contact contact = new Contact(request[1], request[2], request[3]);
                            if (request.length >= 5) {
                                contact.setPhone(request[4]);
                            }
                            addressBook.store(contact);
                            System.out.println("OK");
                        } else {
                            error("Invalid request", true);
                        }
                    } else {
                        error("Invalid request", true);
                    }
                }
            } catch (AddressBookException e) {
                System.err.print(e.getMessage());
                e.printStackTrace();
            }
            System.out.print(PROMPT);
        }
    }
}
