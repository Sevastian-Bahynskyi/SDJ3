package peer;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class PeerImplementation extends UnicastRemoteObject implements PeerInterface
{
    private final String name;
    private PeerList peerList;
    private final RemotePropertyChangeSupport<PeerList> support;
    private PeerInterface anotherPeer;



    public PeerImplementation(String name) throws RemoteException
    {
        super();
        this.name = name;
        peerList = new PeerList();
        peerList.add(this);

        support = new RemotePropertyChangeSupport<>();
        support.firePropertyChange("new peer was created", null, peerList);
    }



    public void endChat()
    {
        anotherPeer = null;
    }

    @Override
    public void lookUpForPeerByName(String peerName) throws RemoteException, NotBoundException, MalformedURLException
    {
        anotherPeer = (PeerInterface) LocateRegistry.getRegistry(5050).lookup(peerName);
//        for (PeerInterface peerInterface:peerList)
//        {
//            if(peerInterface.equals(name))
//            {
//                anotherPeer = peerInterface;
//                break;
//            }
//        }
    }

    @Override
    public void sendMessage(String message) throws RemoteException
    {
        anotherPeer.receiveMessage(message);
    }

    @Override
    public void receiveMessage(String message) throws RemoteException
    {
        System.out.println(message);
    }

    @Override
    public String getName()
    {
        return name;
    }

    public void setPeerList(PeerList peerList)
    {
        this.peerList = peerList;
    }

    public PeerList getPeerList()
    {
        return peerList;
    }

    public RemotePropertyChangeSupport<PeerList> getPropertyChangeSupport()
    {
        return support;
    }
}
