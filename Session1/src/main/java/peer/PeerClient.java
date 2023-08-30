package peer;

import utils.ConsoleSupport;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Locale;
import java.util.Scanner;

public class PeerClient
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException
    {
        System.out.println("Name yourself: ");
        PeerInterface peer = new PeerImplementation(new Scanner(System.in).nextLine());
        PeerListener listener = new PeerListener(peer);

        LocateRegistry.createRegistry(5050).rebind("peer1", peer);

        peer.getPropertyChangeSupport().addPropertyChangeListener("new peer was created", listener);
        ConsoleSupport console = new ConsoleSupport(peer);
        console.run();
    }
}
