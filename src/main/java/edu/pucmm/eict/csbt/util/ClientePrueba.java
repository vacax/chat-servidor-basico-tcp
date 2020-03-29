
package edu.pucmm.eict.csbt.util;

import com.google.gson.Gson;
import edu.pucmm.eict.csbt.encapsulaciones.Mensaje;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vacax
 */
public class ClientePrueba {

    private static String contacto = null;

    public static void main(String[] args) throws IOException {
        //
        Gson gson = new Gson();

        //Parama        
        String menu;
        //capturando la información
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite dirección Servidor: ");
        String servidor = scanner.nextLine();
        System.out.print("Digite el puerto [1025 al 65000]:");
        int puerto = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite su nickname: ");
        contacto = scanner.nextLine();

        //abriendo el sockect
        Socket s = new Socket(servidor, puerto);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream din = new DataInputStream(s.getInputStream());
        
        //anuciando el emisor.
        dout.writeUTF(new Gson().toJson(new Mensaje(contacto, "" ,new Date())));
        dout.flush();

        //abriendo el flujo de chat.
        Thread hilo = new Thread(() -> {
            while (true) {
                try {
                    //
                    String tmp = din.readUTF();
                    //
                    Mensaje tm = gson.fromJson(tmp, Mensaje.class);
                    System.out.println("Mensaje Recibido: " + tmp);
                } catch (IOException ex) {
                    Logger.getLogger(ClientePrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        hilo.start();

        imprimirMenu();
        while (!(menu = scanner.nextLine()).equalsIgnoreCase("2")) {
            if (!menu.equalsIgnoreCase("1")) {
                imprimirMenu();
                continue;
            }
            //
            System.out.print("Digite el mensaje: ");
            String m = scanner.nextLine();
            Mensaje mensaje = new Mensaje(contacto, m, new Date());
            dout.writeUTF(new Gson().toJson(mensaje));
            dout.flush();
            //
            imprimirMenu();
        }

        dout.close();
        System.out.println("terminando programa.");
        System.exit(0);
    }

    public static void imprimirMenu() {
        System.out.println(String.format("Chat de [%s] para el grupo: ", contacto));
        System.out.println("Menú, 1 enviar mensaje, 2, cerrar.:");
    }

}
