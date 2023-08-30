import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerInterface extends Remote, RemotePropertyChangeListener<PeerList>
{
    void lookUpForPeerByName(String peerName) throws RemoteException, NotBoundException;

    void sendMessage(String message) throws RemoteException;

    void receiveMessage(String message) throws RemoteException;

    String getName() throws RemoteException;

    void endChat() throws RemoteException;

    void firePropertyChange(String propertyName, Object oldValue, Object newValue) throws RemoteException;
    void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) throws RemoteException;
    void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) throws RemoteException;
}
