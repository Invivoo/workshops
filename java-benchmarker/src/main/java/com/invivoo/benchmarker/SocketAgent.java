package com.invivoo.benchmarker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketAgent {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(6060);
			while (true) {
				System.out.println("SocketAgent.main() WAITING FOR CLIENT");
				Socket clientSocket = serverSocket.accept();
				System.out.println("SocketAgent.main() NEW CONNECTED CLIENT");
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println("SocketAgent.main() " + line);
				}
				System.out.println("SocketAgent.main() TERMINATED");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
