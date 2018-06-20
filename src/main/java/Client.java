import Infit.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.thrift.*;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.*;

public class Client {
    public static void main(String [] args) {
        try {
            TTransport transport = new TSocket("localhost", 7911);
            TProtocol protocol = new TBinaryProtocol(transport);
            Data.Client client =new Data.Client(protocol);
            transport.open();
            System.out.println("Client calls ping()");
            byte[] encoded = Files.readAllBytes(Paths.get("/Users/h2ero/h2ero/www/infit-rpc/files/test.json"));
            String str = new String(encoded, Charset.defaultCharset());
            System.out.println(client.Analysis(str));
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}