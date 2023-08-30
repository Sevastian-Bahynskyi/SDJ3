import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AddressServerMain
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        Registry registry = LocateRegistry.createRegistry(5050);
        AddressServerInterface addressServer = new AddressServerImpl();

        registry.bind("address server", addressServer);
        System.out.println("server is running");
    }
}