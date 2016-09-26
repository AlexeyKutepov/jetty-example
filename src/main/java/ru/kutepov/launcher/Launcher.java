package ru.kutepov.launcher;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

/**
 * Starts jetty-server on the specified port
 */
public class Launcher {

  public static void main(String[] args) throws Exception {
    int port = 12135;
    try {
      if (args.length > 0) {
        port = Integer.parseInt(args[0]);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    Server server = new Server(port);

    ProtectionDomain domain = Launcher.class.getProtectionDomain();
    URL location = domain.getCodeSource().getLocation();

    WebAppContext webapp = new WebAppContext();
    webapp.setContextPath("/");
    webapp.setWar(location.toExternalForm());

    server.setHandler(webapp);
    server.start();
    server.join();
  }
}