package edu.pucmm.eict.csbt;

import edu.pucmm.eict.csbt.gui.JFApp;
import edu.pucmm.eict.csbt.servicios.ServidorComunicacion;
import edu.pucmm.eict.csbt.util.ClientePrueba;

public class Main {

    public static void main(String[] args) throws Exception {
        switch (args.length){
            case 0:
                JFApp.main(null);
                break;
            case 1:
                System.out.println("Iniciando el cliente");
                ClientePrueba.main(args);
                break;
            case 2:
                int puerto = Integer.parseInt(args[1]);
                System.out.println("Servidor de Comunicación - TCP - Puerto: "+puerto);
                ServidorComunicacion.getInstacia().iniciarServidor(puerto);
                break;
            default:
                System.out.println("Debe ejecutar sin parametro, 1 cliente puerto, 2 -> cliente");
        }
    }
}
