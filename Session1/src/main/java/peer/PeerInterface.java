package peer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerInterface extends Remote
{
    void lookUpForPeerByName(String peerName) throws RemoteException;

    void sendMessage(String message) throws RemoteException;

    void receiveMessage(String message, String name) throws RemoteException;

    String getName() throws RemoteException;

    void endChat() throws RemoteException;
}
