package com.katsha96.lab1.client;

import com.katsha96.lab1.server.RemoteServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        RemoteServerInterface lookup = (RemoteServerInterface) Naming.lookup(RemoteServerInterface.NAME_URL);
        boolean mustStop = false;
        while (!mustStop) {
            System.out.println("Type a number of a command to execute remote request: \n" +
                    "1 - view all foreigners \n" +
                    "2 - add a foreigner \n" +
                    "3 - exit.");
            switch (readCommand()) {
                case 1:
                    System.out.println(lookup.getForeigners());
                    break;
                case 2:
                    System.out.println("Type the foreigner's name");
                    String newForeigner = scanner.nextLine();
                    lookup.addForeigner(newForeigner);
                    break;
                case 3:
                    mustStop = true;
                    break;
                default:
                    System.out.println("Wrong command!");
            }
            System.out.println("-----------------------------------------------------");
        }
    }

    private static int readCommand() {
        String command = scanner.nextLine();
        System.out.println("-----------------------------------------------------");
        try {
            return Integer.parseInt(command);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
