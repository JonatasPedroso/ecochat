package com.socket;

import com.ui.ChatFrame;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Download implements Runnable{
    
    public ServerSocket server;
    public Socket socket;
    public int port;
    public String saveTo = "";
    public InputStream In;
    public FileOutputStream Out;
    public ChatFrame ui;
    
    public Download(String saveTo, ChatFrame ui){
        try {
            // Cria um soquete do servidor, ligado à porta especificada.
            server = new ServerSocket(0);
            // Retorna o número da porta em que este soquete está escutando.
            port = server.getLocalPort();
            // Atribui a String Passada como paramentro a variavel Global
            this.saveTo = saveTo;
            this.ui = ui;
        } 
        catch (IOException ex) {
            System.out.println("Exception [Download : Download(...)]");
        }
    }

    @Override
    public void run() {
        try {
            // Escuta uma conexão a ser feita neste soquete e a aceita.
            socket = server.accept();
            // Retorna o endereço do nó de extremidade ao qual este soquete está vinculado ou nullse ainda não está vinculado.
            System.out.println("Download : " + socket.getRemoteSocketAddress());
            
            // obtém o fluxo de entrada do subprocesso
            In = socket.getInputStream();
            // Cria um fluxo de saída de arquivo para gravar no arquivo com o nome especificado.
            Out = new FileOutputStream(saveTo);
            
            byte[] buffer = new byte[1024];
            int count;
            
            while((count = In.read(buffer)) >= 0){
                // Grava lenbytes da matriz de bytes especificada, iniciando no deslocamento offpara esse fluxo de saída do arquivo.
                Out.write(buffer, 0, count);
            }
            // Libera esse fluxo de saída e força a gravação de todos os bytes 
            // de saída armazenados em buffer. 
            Out.flush();
            // aqui ele esta acrecentado ao ChatFrame essa frase  
            ui.jTextArea1.append("[EcoServer > Mim] : Download Completo!\n");
            // aqui esta fechando todas as conexões
            if(Out != null){ Out.close(); }
            if(In != null){ In.close(); }
            if(socket != null){ socket.close(); }
        } 
        catch (Exception ex) {
            System.out.println("Exception [Download : run(...)]");
        }
    }
}