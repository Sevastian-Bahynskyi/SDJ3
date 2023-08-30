package peer;

import address_server.AddressServerInterface;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PeerImplementation extends UnicastRemoteObject implements PeerInterface
{
    private final String name;
    private final AddressServerInterface addressServer;
    private PeerInterface anotherPeer;


    public PeerImplementation(String name, AddressServerInterface addressServer) throws MalformedURLException, RemoteException
    {
        this.name = name;
        this.addressServer = addressServer;
        addressServer.registerPeer(this);
    }

    public void endChat()
    {
        anotherPeer = null;
    }

    @Override
    public void lookUpForPeerByName(String peerName) throws RemoteException, NullPointerException
    {
        anotherPeer = addressServer.lookUpPeer(peerName);
        if(anotherPeer == null)
            throw new NullPointerException();
    }

    @Override
    public void sendMessage(String message) throws RemoteException
    {
        if(anotherPeer != null && message != null && !message.trim().isEmpty())
            anotherPeer.receiveMessage(message, name);
    }

    @Override
    public void receiveMessage(String message, String name) throws RemoteException
    {
        System.out.println(String.format("%s : from %s", message, name));
    }

    @Override
    public String getName() throws RemoteException
    {
        return name;
    }

}
