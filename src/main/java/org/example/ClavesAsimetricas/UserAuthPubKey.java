package org.example.ClavesAsimetricas;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.Properties;

public class UserAuthPubKey {
    public static void main(String[] arg) {
        try {
            JSch jsch = new JSch();
            String user = "root";
            String host = "192.168.1.100";
            int port = 22;
            String privateKey = "/home/jalonso/.ssh/id_rsa";
            jsch.addIdentity(privateKey);
            System.out.println("identity added ");
            Session session = jsch.getSession(user, host, port);

 //Si es necesario introducir el password para iniciar sesion
 session.setPassword("mipassword");
// Para permitir conectarse sin comprobar el host
// session.setConfig("StrictHostKeyChecking", "no");
// System.out.println("session created.");
// Conectamos session.connect();
// System.out.println("session connected.....");
// Channel channel = session.openChannel("shell");
// channel.setInputStream(System.in);

// channel.setOutputStream(System.out);
// channel.connect(3 * 1000);
 } catch (Exception e)
 { System.err.println(e);
 }
 }
 }