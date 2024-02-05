package org.example;

import com.jcraft.jsch.*;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.util.Objects;
import java.util.Scanner;

public class JSCHEjecucionRemotaComando {

    // Actividad 7
    //
    //Modifica el primer programa para que si recibe la instrucción “quiero salir” cierre la conexión con el servidor SSH.
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.print("Host: ");
        String host = "127.0.0.1";
        System.out.print("Usuario: ");
        String usuario = "mballesterosv";
        System.out.print("Contraseña: ");
        String contraseña = "10678713";
        boolean exit = false;
        String comando;
        try {


            JSch jsch = new JSch();

            Session session = jsch.getSession(usuario, host, 22);

            session.setPassword(contraseña);
            Channel channel = null;
            System.out.print("Comando: ");
            comando = s.nextLine();
                UserInfo ui = new MiUserInfo() {

                    public void showMessage(String message) {

                        System.out.println(message);

                    }

                    public boolean promptYesNo(String message) {

                        return true;

                    }

                };

                session.setUserInfo(ui);

                session.connect(30000); // making a connection with timeout.

            while (!exit) {
                if (Objects.equals(comando, "quiero salir")) {
                    exit = true;
                    System.out.println("salgo");
                    break;
                } else if (!comando.equals("ls")) {
                    System.out.println("comando no valido");
                    comando = "";
                }
                channel = session.openChannel("exec");

                ChannelExec channelExec = (ChannelExec) channel;

                System.out.print("Comando: ");


                channelExec.setCommand(comando);

                channelExec.setInputStream(null);

                channelExec.setErrStream(System.err);

                try (InputStream is = channel.getInputStream();

                     InputStreamReader isr = new InputStreamReader(is);

                     BufferedReader br = new BufferedReader(isr)) {

                    channelExec.connect();

                    String lineaSalida = null;

                    while ((lineaSalida = br.readLine()) != null) {

                        System.out.println(lineaSalida);

                    }

                    System.out.println("comando:");
                    comando = s.nextLine();
                    System.out.println(comando);

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }
            channel.disconnect();

            session.disconnect();

        } catch (JSchException e) {

            e.printStackTrace();

        }

    }

    public static abstract class MiUserInfo

            implements UserInfo, UIKeyboardInteractive {

        public String getPassword() {

            return null;

        }

        public boolean promptYesNo(String str) {

            return false;

        }

        public String getPassphrase() {

            return null;

        }

        public boolean promptPassphrase(String message) {

            return false;

        }

        public boolean promptPassword(String message) {

            return false;

        }

        public void showMessage(String message) {

        }

        public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt, boolean[] echo) {
            return null;

        }

    }

}