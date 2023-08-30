package peer;

import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerInterface extends Remote, Serializable
{
    void lookUpForPeerByName(String peerName) throws RemoteException, NotBoundException, MalformedURLException;

    void sendMessage(String message) throws RemoteException;

    void receiveMessage(String message) throws RemoteException;

    String getName() throws RemoteException;

    void endChat() throws RemoteException;

    RemotePropertyChangeSupport<PeerList> getPropertyChangeSupport() throws RemoteException;

    void setPeerList(PeerList peerList) throws RemoteException;
    PeerList getPeerList() throws RemoteException;
}
