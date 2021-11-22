import connections.ConnectToNodes;
import encoders.PrivateKeyGenerator;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class EthGenerator {
    public static void main(String[] args) throws IOException {
        System.out.println("Main thread started...");
        ConnectToNodes connect = new ConnectToNodes();
        connect.start();

    }
}