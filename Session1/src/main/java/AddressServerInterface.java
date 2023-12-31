import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AddressServerInterface extends Remote
{
    void registerPeer(PeerInterface peer) throws MalformedURLException, RemoteException;

    PeerInterface lookUpPeer(String peerName) throws RemoteException, NotBoundException;
}