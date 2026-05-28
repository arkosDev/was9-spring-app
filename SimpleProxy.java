import java.io.*;
import java.net.*;

/**
 * Proxy HTTP simple para probar localmente.
 * Uso:
 *   javac SimpleProxy.java
 *   java SimpleProxy 8888
 *
 * Luego en application.properties:
 *   proxy.host=localhost
 *   proxy.port=8888
 */
public class SimpleProxy {

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8888;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Proxy escuchando en puerto " + port);

        while (true) {
            Socket client = server.accept();
            new Thread(new Handler(client)).start();
        }
    }

    static class Handler implements Runnable {
        private final Socket client;

        Handler(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));

                // Leer primera linea del request
                String requestLine = br.readLine();
                if (requestLine == null) return;

                System.out.println("[PROXY] " + requestLine);

                // Leer headers
                StringBuilder headers = new StringBuilder();
                String line;
                String host = null;
                int port = 80;
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    headers.append(line).append("\r\n");
                    if (line.toLowerCase().startsWith("host:")) {
                        String hostHeader = line.substring(5).trim();
                        if (hostHeader.contains(":")) {
                            host = hostHeader.split(":")[0];
                            port = Integer.parseInt(hostHeader.split(":")[1]);
                        } else {
                            host = hostHeader;
                        }
                    }
                }

                // CONNECT (HTTPS tunnel)
                if (requestLine.startsWith("CONNECT")) {
                    String[] parts = requestLine.split(" ");
                    String[] hostPort = parts[1].split(":");
                    host = hostPort[0];
                    port = Integer.parseInt(hostPort[1]);

                    System.out.println("[PROXY] CONNECT a " + host + ":" + port);

                    Socket remote = new Socket(host, port);

                    // Responder 200 al cliente
                    OutputStream clientOut = client.getOutputStream();
                    clientOut.write("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes());
                    clientOut.flush();

                    // Tunnel bidireccional
                    Thread t1 = new Thread(new Tunnel(client.getInputStream(), remote.getOutputStream()));
                    Thread t2 = new Thread(new Tunnel(remote.getInputStream(), client.getOutputStream()));
                    t1.start();
                    t2.start();
                    t1.join();
                    t2.join();
                    remote.close();

                } else {
                    // HTTP normal
                    if (host == null) {
                        client.close();
                        return;
                    }
                    Socket remote = new Socket(host, port);
                    OutputStream remoteOut = remote.getOutputStream();
                    remoteOut.write((requestLine + "\r\n").getBytes());
                    remoteOut.write((headers.toString() + "\r\n").getBytes());
                    remoteOut.flush();

                    Thread t1 = new Thread(new Tunnel(client.getInputStream(), remoteOut));
                    Thread t2 = new Thread(new Tunnel(remote.getInputStream(), client.getOutputStream()));
                    t1.start();
                    t2.start();
                    t1.join();
                    t2.join();
                    remote.close();
                }

            } catch (Exception e) {
                System.out.println("[PROXY] Error: " + e.getMessage());
            } finally {
                try { client.close(); } catch (Exception ignored) {}
            }
        }
    }

    static class Tunnel implements Runnable {
        private final InputStream in;
        private final OutputStream out;

        Tunnel(InputStream in, OutputStream out) {
            this.in = in;
            this.out = out;
        }

        public void run() {
            try {
                byte[] buf = new byte[4096];
                int n;
                while ((n = in.read(buf)) != -1) {
                    out.write(buf, 0, n);
                    out.flush();
                }
            } catch (Exception ignored) {}
        }
    }
}
