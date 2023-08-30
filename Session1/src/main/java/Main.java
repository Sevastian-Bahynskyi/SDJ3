import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException
    {
        System.out.println("Name yourself: ");
        PeerInterface peer = new PeerImplementation(new Scanner(System.in).nextLine());
        peer.run();
    }
}
