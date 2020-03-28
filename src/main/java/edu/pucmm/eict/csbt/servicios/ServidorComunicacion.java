package edu.pucmm.eict.csbt.servicios;

import java.net.ServerSocket;

public class ServidorComunicacion implements Runnable {

    private static ServidorComunicacion instancia;
    private ServerSocket serverSocket;

    private ServidorComunicacion() {
    }

    /**
     *
     * @return
     */
    public static ServidorComunicacion getInstacia(){
        if(instancia==null){
            instancia = new ServidorComunicacion();
        }
        return instancia;
    }

    public void iniciarServidor(int puerto) throws Exception{
        serverSocket = new ServerSocket(puerto);
    }

    @Override
    public void run() {

    }
}
