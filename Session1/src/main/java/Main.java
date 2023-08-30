import address_server.AddressServerInterface;
import peer.PeerImplementation;
import peer.PeerInterface;
import utils.ConsoleSupport;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException
    {
        Registry registry = LocateRegistry.getRegistry(5050);
        AddressServerInterface addressServer = (AddressServerInterface) registry.lookup("address server");

        System.out.println("Name yourself: ");

        PeerInterface peer = new PeerImplementation(new Scanner(System.in).nextLine(), addressServer);

        ConsoleSupport consoleSupport = new ConsoleSupport(peer);
        consoleSupport.run();
    }
}
