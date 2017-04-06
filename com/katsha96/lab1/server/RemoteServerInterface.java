package com.katsha96.lab1.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author a.alifanov
 * @since 06.04.2017
 */
public interface RemoteServerInterface extends Remote {

    String NAME_URL = "//localhost/MyServer";

    String getForeigners() throws RemoteException;
    void addForeigner(String line) throws RemoteException;
}
