package peer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface PeerInterface extends Remote
{
    void lookUpForPeerByName(String peerName) throws RemoteException, NotBoundException;

    void sendMessage(String message) throws RemoteException;

    void receiveMessage(String message, String name) throws RemoteException;

    String getName() throws RemoteException;

    void endChat() throws RemoteException;

    PeerInterface put(String key, PeerInterface value) throws RemoteException;

    void setPeerList(Map<String, PeerInterface> peerList) throws RemoteException;

    Map<String, PeerInterface> getPeerList() throws RemoteException;
}
