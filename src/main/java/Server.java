/**
 * Created by Alan on 5/8/17.
 */
import Infit.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class Server {
	private static Logger logger = Logger.getLogger("HRVLib_Test");

	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	private void start() {

		try {
			System.out.print("Being called ! 1");
			TServerSocket serverTransport = new TServerSocket(7911);
			Factory protFactory = new TBinaryProtocol.Factory(true, true);
			Data.Processor processor = new Data.Processor(new DataImpl());

			TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(
					serverTransport);
			serverArgs.processor(processor);
			// serverArgs.transportFactory(serverTransport);
			serverArgs.protocolFactory(protFactory);

			TServer server = new TThreadPoolServer(serverArgs);
			server.serve();
		} catch (TTransportException tte) {
			tte.printStackTrace();
		}
	}

	public static void main(String args[]) {
		 Server srv = new Server();
		 System.out.print("server start");
		 srv.start();
/*
		String fpath = "../TestData/sleep_data1.log";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fpath));
            String jsonRequest = br.readLine();

            DataImpl function = new DataImpl();

            function.Analysis(jsonRequest);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
		

	}
}