package com.katsha96.lab1.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

class Server extends UnicastRemoteObject implements RemoteServerInterface {

    private final static String FILE_NAME = "Foreigners.txt";
    private static Logger log = Logger.getLogger(Server.class.getName());
    private static Path filePath = FileSystems.getDefault().getPath(FILE_NAME);

    private Server() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            Naming.rebind("//localhost/MyServer", new Server());
            log.info("Server started");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Error while running server: ", ex);
        }
    }

    @Override
    public String getForeigners() throws RemoteException {
        try {
            byte[] encoded = Files.readAllBytes(filePath);
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            log.severe("Error reading the file: " + ex.toString());
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void addForeigner(String line) throws RemoteException {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(FILE_NAME, true));
            fw.append(line);
            fw.newLine();
            fw.close();
        } catch (IOException ex) {
            log.severe("Error writing to the file: " + ex.toString());
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
}
