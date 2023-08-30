package utils;

import peer.PeerInterface;
import utils.ConsoleColors;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ConsoleSupport
{
    public enum COMMAND
    {
        END(
                "To quit current chat enter this command: ",
                "/end"),
        RED_COLOR(
                String.format("To change the color to %s%s%s enter this command: ", ConsoleColors.RED, "red", ConsoleColors.RESET),
                "/red"),

        YELLOW_COLOR(
                String.format("To change the color to %s%s%s enter this command: ", ConsoleColors.YELLOW, "yellow", ConsoleColors.RESET),
                "/yellow"),

        RESET_COLOR(
                "If you want to reset color enter this command: ",
                "/reset_color");

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





    private boolean isPeerChatting = false;
    private boolean commandQuitWasEntered = false;
    private PeerInterface peer;


    public ConsoleSupport(PeerInterface peer)
    {
        this.peer = peer;
        setUpCommands();
    }

    private void setUpCommands()
    {
        COMMAND.END.setCommandAction(() -> {
            try
            {
                peer.endChat();
            } catch (RemoteException e)
            {
                throw new RuntimeException(e);
            }
            isPeerChatting = false;
        });
        COMMAND.RED_COLOR.setCommandAction(() -> System.out.println(ConsoleColors.RED));
        COMMAND.YELLOW_COLOR.setCommandAction(() -> System.out.println(ConsoleColors.YELLOW));
        COMMAND.RESET_COLOR.setCommandAction(() -> System.out.println(ConsoleColors.RESET));
    }

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
                peer.lookUpForPeerByName(peerName);
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
                        peer.sendMessage(input);
                }
            }
        } catch (RemoteException e)
        {
            throw new RuntimeException(e);
        } catch (NotBoundException e)
        {
            throw new RuntimeException(e);
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
