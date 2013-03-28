
package com.marakana.addressbook;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RemoteAddressBookTest extends AbstractAddressBookTest {

    private RemoteAddressBookServer remoteAddressBookServer;

    private AddressBook remoteAddressBook;

    private ExecutorService executorService;

    @Override
    protected AddressBook buildAddressBook() throws AddressBookException {
        try (ServerSocket serverSocket = new ServerSocket()) {
            InetSocketAddress inetSocketAddress;
            for (int port = 8000; port < 8100; port++) {
                try {
                    inetSocketAddress = new InetSocketAddress("localhost", port);
                    serverSocket.bind(inetSocketAddress);
                    this.executorService = Executors.newFixedThreadPool(10);
                    this.remoteAddressBook = new InMemoryAddressBook();
                    this.remoteAddressBookServer = new RemoteAddressBookServer(
                            this.remoteAddressBook, serverSocket, this.executorService);
                    Thread thread = new Thread(this.remoteAddressBookServer,
                            "RemoteAddressBook Thread");
                    thread.setDaemon(true);
                    thread.start();
                    return new RemoteAddressBook(inetSocketAddress);
                } catch (IOException e) {
                    // try the next one
                }
            }
            throw new AddressBookException("Failed to start remote address book service");
        } catch (IOException e) {
            throw new AddressBookException("Failed to create socket", e);
        }
    }

    @Override
    protected void tearDown(AddressBook addressBook) throws Exception {
        this.executorService.shutdownNow();
        this.remoteAddressBook.close();
        this.remoteAddressBookServer.shutdown();
    }
}
