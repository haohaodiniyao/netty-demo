package com.demo.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
    public static void main(String[] args){
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        BufferedReader readerConsole = null;
        String responseStr = null;
        try {
            socket = new Socket("127.0.0.1",port);
            while(true){
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter = new PrintWriter(socket.getOutputStream(),true);
                readerConsole = new BufferedReader(new InputStreamReader(System.in));
                responseStr = readerConsole.readLine();
                printWriter.println(responseStr);
                String body = bufferedReader.readLine();
                System.out.println("client receive:"+body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader !=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    bufferedReader = null;
                }
            }
            if(printWriter != null){
                printWriter.close();
                printWriter = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    socket = null;
                }
            }
        }
    }
}
