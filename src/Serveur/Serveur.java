package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Semaphore;

import DAO.Entrees;

public class Serveur implements Runnable
{
    Socket socket;
    ServerSocket server;
    Entrees en=new Entrees();
    Semaphore mutex;
    
    public Serveur(int port) {
//    	en.emptyBase();
    	mutex = new Semaphore(1);
        try {
        	en.disconnectAllCapteurs();
            this.server = new ServerSocket(port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            while (!this.server.isClosed()) {
                this.socket = this.server.accept();
                final Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final BufferedReader plec = new BufferedReader(new InputStreamReader(Serveur.this.socket.getInputStream()));
                            boolean socketOuvert = true;
                            while (socketOuvert) {
                                try {
                                    final String input = plec.readLine();
                                    if (input == null) {
                                        continue;
                                    }
                                    String recu[] = input.split(" ");
                                    //System.out.println(recu[1]);//nom
                                    //System.out.println(recu[2]);//données forme x:x:x:x
                                    mutex.acquire();
                                    if(recu[0].equals("Connexion"))
                                    	en.capteur(recu[1], recu[2]);
                                    else if(recu[0].equals("Donnee"))
                                    	en.valeur(recu[1], recu[2]);
                                    else if(recu[0].equals("Deconnexion"))
                                        en.disconnectCapteur(recu[1]);
                                    mutex.release();
                                    
                                }
                                
                                catch (SocketException se) {
                                    socketOuvert = false;
                                }
                                
                            }
                            Thread.sleep(500);
                            Serveur.this.socket.close();
                        }
                        catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        final Serveur c = new Serveur(8952);
        final Thread t = new Thread(c);
        t.start();
    }
}
