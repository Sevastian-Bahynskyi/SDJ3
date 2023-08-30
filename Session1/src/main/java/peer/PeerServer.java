package peer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PeerServer
{
    public static void main(String[] args) throws RemoteException
    {
        Registry registry = LocateRegistry.createRegistry(5050);
    }
}
