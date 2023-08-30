import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class PeerImplementation extends UnicastRemoteObject implements PeerInterface, RemotePropertyChangeListener<PeerList>
{
    private final String name;
    private PeerList peerList;
    private final PropertyChangeSupport support;
    private PeerInterface anotherPeer;



    public PeerImplementation(String name) throws MalformedURLException, RemoteException
    {
        this.name = name;
        peerList = new PeerList();
        peerList.add(this);

        support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener("retrieve list", (PropertyChangeListener) this);
        support.firePropertyChange("new peer was created", null, peerList);
    }



    public void endChat()
    {
        anotherPeer = null;
    }

    @Override
    public void lookUpForPeerByName(String peerName) throws RemoteException, NotBoundException
    {
        anotherPeer = (PeerInterface) peerList.stream().filter(peerInterface ->
        {
            try
            {
                return peerInterface.getName().equals(peerName);
            } catch (RemoteException e)
            {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void sendMessage(String message) throws RemoteException
    {
        anotherPeer.receiveMessage(message);
    }

    @Override
    public void receiveMessage(String message) throws RemoteException
    {
        System.out.println(message);
    }

    @Override
    public String getName() throws RemoteException
    {
        return name;
    }

    @Override
    public void propertyChange(RemotePropertyChangeEvent<PeerList> remotePropertyChangeEvent) throws RemoteException
    {
        PeerList peerList = ((PeerList) remotePropertyChangeEvent.getNewValue());

        switch (remotePropertyChangeEvent.getPropertyName())
        {
            case "new peer was created" -> {
                peerList.clear();
                peerList.addAll(peerList);

                PeerInterface[] listenersOfNewPeers = ((PeerInterface[]) support.getPropertyChangeListeners("new peer created"));
                for (PeerInterface newPeer : listenersOfNewPeers)
                {
                    newPeer.firePropertyChange("retrieve list", null, peerList);
                }
            }

            case "retrieve list" -> {
                this.peerList = peerList;
                support.removePropertyChangeListener("retrieve list", (PropertyChangeListener) this);
                support.addPropertyChangeListener("new peer was created", (PropertyChangeListener) this);
            }
        }
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(propertyName, listener);
    }
}
