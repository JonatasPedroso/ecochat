package com.socket;

import java.io.Serializable;

public class Message implements Serializable{
    
    //Código serial apenas para não ocorrer avisos. 
    //1L normalmente usado quando o desenvolvedor sabe que não está finalizado ainda.
    private static final long serialVersionUID = 1L;
    public String type, sender, content, recipient;
    
    //Construtor da mensagem.
    public Message(String type, String sender, String content, String recipient){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient;
    }
    
    @Override
    public String toString(){
        return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
    }
}
