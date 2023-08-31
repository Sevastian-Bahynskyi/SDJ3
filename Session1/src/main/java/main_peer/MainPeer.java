package main_peer;

import peer.PeerImplementation;
import peer.PeerInterface;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainPeer
{
    public static void main(String[] args) throws RemoteException
    {
        Registry registry = LocateRegistry.createRegistry(5050);
        PeerInterface mainPeer = new PeerImplementation("main_peer");
        registry.rebind("main_peer", mainPeer);
    }
}
