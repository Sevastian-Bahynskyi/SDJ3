import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class PeerImplementation extends UnicastRemoteObject implements PeerInterface
{
    public enum COMMAND
    {
        END(
                "To quit current chat enter this command: ",
                "/end"),
        RED_COLOR(
                "To change the color to red enter this command: ",
                "/red");

        private final String commandValue;
        private final String descriptionValue;
        private Runnable commandAction;

        COMMAND(String description, String command){
            descriptionValue = description;
            commandValue = command;
        }

        public void setCommandAction(Runnable function)
        {
            this.commandAction = function;
        }

        public void executeCommand()
        {
            commandAction.run();
        }

        public String getCommandValue(){
            return commandValue;
        }

        public String getDescriptionValue()
        {
            return String.format(descriptionValue + "\"%s\"", commandValue);
        }
    }
    private final String name;
    private final AddressServerInterface addressServer;
    private PeerInterface anotherPeer;
    boolean isPeerChatting = false;
    boolean commandQuitWasEntered = false;


    public PeerImplementation(String name, AddressServerInterface addressServer) throws MalformedURLException, RemoteException
    {
        this.name = name;
        this.addressServer = addressServer;
        addressServer.registerPeer(this);
        setUpCommands();
    }

    private void setUpCommands()
    {
        COMMAND.END.setCommandAction(this::endChat);
    }

    private void endChat()
    {
        anotherPeer = null;
        isPeerChatting = false;
    }

    @Override
    public void lookUpForPeerByName(String peerName) throws RemoteException
    {
        anotherPeer = addressServer.lookUpPeer(peerName);
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
    public void run()
    {
        try
        {
            while(!commandQuitWasEntered)
            {
                isPeerChatting = true;
                System.out.println("Enter peer name you want to message.");
                Scanner scanner = new Scanner(System.in);
                String peerName = scanner.next();
                lookUpForPeerByName(peerName);
                System.out.println("You was successfully connected and now can chat with " + peerName);

                {
                    int count = 0;
                    for (COMMAND command : COMMAND.values())
                    {
                        System.out.println(String.format("%d. %s", ++count, command.getDescriptionValue()));
                    }
                }

                while (isPeerChatting)
                {
                    String input = scanner.nextLine();

                    for (COMMAND command : COMMAND.values())
                    {
                        if (command.commandValue.equals(input))
                        {
                            command.executeCommand();
                        }
                    }

                    if (isPeerChatting) // to make sure the end chat command wasn't entered
                        sendMessage(input);
                }
            }
        } catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }
}

