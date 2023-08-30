module Session1 {
    requires java.desktop;
    requires java.rmi;
    requires remoteobserver;

    // Export peer package to necessary modules
    exports peer to java.rmi;

    // Open peer package for potential reflection needs
    opens peer to java.rmi;
}
