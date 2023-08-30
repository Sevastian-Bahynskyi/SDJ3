package peer;

import utils.ConsoleSupport;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException, AlreadyBoundException
    {

        System.out.println("Name yourself: ");
        PeerInterface peer = new PeerImplementation(new Scanner(System.in).nextLine());
        PeerListener listener = new PeerListener(peer);

        peer.getPropertyChangeSupport().addPropertyChangeListener("retrieve list", listener);
        ConsoleSupport console = new ConsoleSupport(peer);
        console.run();
    }
}
