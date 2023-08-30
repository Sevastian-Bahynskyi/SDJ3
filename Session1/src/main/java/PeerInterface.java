import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerInterface extends Remote
{
    void lookUpForPeerByName(String peerName) throws RemoteException, NotBoundException, MalformedURLException;

    void sendMessage(String message) throws RemoteException;

    void receiveMessage(String message) throws RemoteException;

    String getName() throws RemoteException;

    void run() throws RemoteException;

    void endChat() throws RemoteException;
}
