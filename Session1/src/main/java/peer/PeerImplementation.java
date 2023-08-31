package peer;


import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class PeerImplementation extends UnicastRemoteObject implements PeerInterface
{
    private final String name;
    private PeerInterface anotherPeer;
    private Map<String, PeerInterface> peerList;


    public PeerImplementation(String name) throws RemoteException
    {
        this.name = name;
        peerList = new HashMap<>();
        peerList.put(name, this);
    }

    public Map<String, PeerInterface> getPeerList()
    {
        return peerList;
    }

    public PeerInterface put(String key, PeerInterface value)
    {
        return peerList.put(key, value);
    }

    public void setPeerList(Map<String, PeerInterface> peerList)
    {
        this.peerList = peerList;
    }

    public void endChat()
    {
        anotherPeer = null;
    }

    @Override
    public void lookUpForPeerByName(String peerName) throws RemoteException, NullPointerException
    {
        anotherPeer = peerList.get(peerName);
        if(anotherPeer == null)
            throw new NullPointerException();
    }

    @Override
    public void sendMessage(String message) throws RemoteException
    {
        if(anotherPeer != null && message != null && !message.trim().isEmpty())
            anotherPeer.receiveMessage(message, name);
    }

    @Override
    public void receiveMessage(String message, String name) throws RemoteException
    {
        System.out.println(String.format("%s : from %s", message, name));
    }

    @Override
    public String getName() throws RemoteException
    {
        return name;
    }

}
