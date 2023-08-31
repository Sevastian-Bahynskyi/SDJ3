
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
        PeerInterface mainPeer = (PeerInterface) registry.lookup("main_peer");

        System.out.println("Name yourself: ");
        String peerName = new Scanner(System.in).nextLine();
        //TODO check of name
        PeerInterface peer = new PeerImplementation(peerName);
        peer.getPeerList().clear();
        mainPeer.put(peerName, peer);
        for (PeerInterface peerInterface:mainPeer.getPeerList().values())
        {
            peerInterface.setPeerList(mainPeer.getPeerList());
        }

        ConsoleSupport consoleSupport = new ConsoleSupport(peer);
        consoleSupport.run();
    }
}
