package utils;


import peer.PeerInterface;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ConsoleSupport
{
    public enum COMMAND
    {
        END(
                String.format("%sTo quit current chat enter this command: ", ConsoleColors.RESET),
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
        while(!commandQuitWasEntered)
        {
                System.out.println("Enter peer name you want to message.");
                Scanner scanner = new Scanner(System.in);
                String peerName = scanner.next();
            try
            {
                peer.lookUpForPeerByName(peerName);
            } catch (Exception e)
            {
                System.err.println("Peer with such name doesn't exist.");
                continue;
            }

            isPeerChatting = true;
                System.out.println("You was successfully connected and now can chat with " + peerName);

                {
                    int count = 0;
                    for (COMMAND command : COMMAND.values())
                    {
                        System.out.println(String.format("%d. %s", ++count, command.getDescriptionValue()));
                    }
                }

                lld: while (isPeerChatting)
                {
                    String input = scanner.nextLine();

                    for (COMMAND command : COMMAND.values())
                    {
                        if (command.commandValue.equals(input))
                        {
                            command.executeCommand();
                            continue lld;
                        }
                    }

                    if (isPeerChatting) // to make sure the end chat command wasn't entered
                    {
                        try
                        {
                            peer.sendMessage(input);
                        } catch (RemoteException e)
                        {
                            System.out.println("Message hasn't been send");
                        }
                    }
                }
        }
    }
}