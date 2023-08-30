package peer;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PeerListener extends UnicastRemoteObject implements PeerInterface, RemotePropertyChangeListener<PeerList>
{
    private PeerInterface peer;


    public PeerListener(PeerInterface peer) throws RemoteException
    {
        this.peer = peer;
    }

    @Override
    public void setPeerList(PeerList peerList) throws RemoteException
    {
        peer.setPeerList(peerList);
    }

    @Override
    public PeerList getPeerList() throws RemoteException
    {
        return peer.getPeerList();
    }

    @Override
    public void lookUpForPeerByName(String peerName) throws RemoteException, NotBoundException, MalformedURLException
    {
        peer.lookUpForPeerByName(peerName);
    }

    @Override
    public void sendMessage(String message) throws RemoteException
    {
        peer.sendMessage(message);
    }

    @Override
    public void receiveMessage(String message) throws RemoteException
    {
        peer.receiveMessage(message);
    }

    @Override
    public String getName() throws RemoteException
    {
        return peer.getName();
    }

    @Override
    public void endChat() throws RemoteException
    {
        peer.endChat();
    }

    @Override
    public RemotePropertyChangeSupport<PeerList> getPropertyChangeSupport() throws RemoteException
    {
        return peer.getPropertyChangeSupport();
    }

    @Override
    public void propertyChange(RemotePropertyChangeEvent<PeerList> remotePropertyChangeEvent) throws RemoteException
    {
        PeerList peerList = remotePropertyChangeEvent.getNewValue();

        switch (remotePropertyChangeEvent.getPropertyName())
        {
            case "new peer was created" -> {
                peerList.clear();
                peerList.addAll(peerList);

                PeerInterface[] listenersOfNewPeers = ((PeerInterface[]) getPropertyChangeSupport().getPropertyChangeListeners("new peer was created"));
                for (PeerInterface newPeer : listenersOfNewPeers)
                {
                    newPeer.getPropertyChangeSupport().firePropertyChange("retrieve list", null, peerList);
                }
            }

            case "retrieve list" -> {
                setPeerList(peerList);
                getPropertyChangeSupport().removePropertyChangeListener("retrieve list", this);
                getPropertyChangeSupport().addPropertyChangeListener("new peer was created", this);
            }
        }
    }
}
