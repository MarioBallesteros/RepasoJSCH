package org.example.ClavesAsimetricas;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.KeyPair;

public class GenerarClavePublicaPrivada {
    public static void main(String[] args) {
        // EJECUTAS ESTO Y SE LO MANDAS A OTRO ORDENADOR CON EL COMANDO QUE HEMOS VISTO EN CLASE  --> ssh-copy-id -i ~/.ssh/id_rsa.pub 10.18.0.1
        String path = "/home/mballesterosv/.ssh/";
        String filename = "id_rsa";
        String file = path + filename;
        String comment = "mballesterosv@lmde5";
        JSch jsch = new JSch();
        try {
            KeyPair kpair = KeyPair.genKeyPair(jsch, KeyPair.RSA, 2048);
             //Solo para mostrar por consola
            kpair.writePrivateKey(System.out);
             kpair.writePublicKey(System.out,comment);
             //Creamos los ficheros de las claves privadas y publicas
             kpair.writePrivateKey(file);
             kpair.writePublicKey(file+".pub", comment);
            // CAMBIAR LOS PERMISOS A LECTURA Y ESCRITURA DE PROPIETARIO
            // CAMBIAR LOS PERMISOS A LECTURA Y ESCRITURA DE PROPIETARIO
            // CAMBIAR LOS PERMISOS A LECTURA Y ESCRITURA DE PROPIETARIO
            // CAMBIAR LOS PERMISOS A LECTURA Y ESCRITURA DE PROPIETARIO
            // CAMBIAR LOS PERMISOS A LECTURA Y ESCRITURA DE PROPIETARIO
            // de 644 a 600
            // chmod 600 id_rsa
             System.out.println("Finger print: "+kpair.getFingerPrint());
             kpair.dispose();
             } catch(Exception e)
        {
             e.printStackTrace();
        }
             System.exit(0);
    }
}