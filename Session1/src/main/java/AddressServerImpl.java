import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AddressServerImpl extends UnicastRemoteObject implements AddressServerInterface
{
    private final String PEER_ADDRESS_TEMPLATE = "Peer_";

    public AddressServerImpl() throws RemoteException
    {
        super();
    }
    @Override
    public void registerPeer(PeerInterface peer) throws MalformedURLException, RemoteException
    {
        String address = PEER_ADDRESS_TEMPLATE + peer.getName();
        LocateRegistry.getRegistry(5050).rebind(address, peer);
    }

    @Override
    public PeerInterface lookUpPeer(String peerName) throws RemoteException, NotBoundException
    {
        String address = PEER_ADDRESS_TEMPLATE + peerName;
        return (PeerInterface) LocateRegistry.getRegistry(5050).lookup(address);
    }
}