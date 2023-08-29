import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

public class PeerHandler
{
    private Map<String, PeerInterface> peers;
    private final String PEER_ADDRESS_TEMPLATE = "Peer_";
    private static PeerHandler instance;

    public PeerHandler()
    {
        peers = new HashMap<>();
    }

    public static PeerHandler getInstance()
    {
        return instance == null? new PeerHandler() : instance;
    }

    public void registerPeer(PeerInterface peer) throws MalformedURLException, RemoteException
    {
        String address = PEER_ADDRESS_TEMPLATE + peer.getName();
        peers.put(PEER_ADDRESS_TEMPLATE + peer.getName(), peer);
        try
        {
            LocateRegistry.getRegistry(5050).rebind(address, peer);
        }
        catch (Exception e)
        {
            LocateRegistry.createRegistry(5050).rebind(address, peer);
        }
    }


    public PeerInterface lookUpPeer(String peerName)
    {
        String address = PEER_ADDRESS_TEMPLATE + peerName;
        return peers.get(address);
    }
}
