import utils.ConsoleSupport;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException
    {

        System.out.println("Name yourself: ");

        PeerInterface peer = new PeerImplementation(new Scanner(System.in).nextLine());
        ConsoleSupport console = new ConsoleSupport(peer);
        console.run();
    }
}
