import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AddressServerMain
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        Registry registry = LocateRegistry.createRegistry(5050); // how can I get rid of this, If I create a general registry, does it count as main server?
        // should each node create a new registry, and how then other node can know the registry of first node and vice-versa?
        AddressServerInterface addressServer = new AddressServerImpl();

        registry.bind("address server", addressServer);
        System.out.println("server is running");
    }
}