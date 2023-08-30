package address_server;

import peer.PeerInterface;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AddressServerImpl extends UnicastRemoteObject implements AddressServerInterface
{
    private Map<String, PeerInterface> peers;
    private final String PEER_ADDRESS_TEMPLATE = "Peer_";

    public AddressServerImpl() throws RemoteException
    {
        super();
        peers = new HashMap<>();
    }
    @Override
    public void registerPeer(PeerInterface peer) throws MalformedURLException, RemoteException
    {
        String address = PEER_ADDRESS_TEMPLATE + peer.getName();
        peers.put(PEER_ADDRESS_TEMPLATE + peer.getName(), peer);
        LocateRegistry.getRegistry(5050).rebind(address, peer);
    }

    @Override
    public PeerInterface lookUpPeer(String peerName)
    {
        String address = PEER_ADDRESS_TEMPLATE + peerName;
        return peers.get(address);
    }
}