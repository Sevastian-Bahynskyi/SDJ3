import java.rmi.RemoteException;

public class PeerThread implements Runnable
{
    private final PeerInterface peer;

    public PeerThread(PeerInterface peer)
    {
        this.peer = peer;
    }
    @Override
    public void run()
    {
        try
        {
            peer.run();
        } catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }
}
