import encoders.PrivateKeyGenerator;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class EthGenerator {

    private final static int RRS = 10;

    public static Set<Object> amountKeys(int amount) {
        PrivateKeyGenerator pr = new PrivateKeyGenerator();
        Set<Object> set = new HashSet<>();
        for (int i = 0; i < RRS; i++) {

            set.add(pr.ethKeyGenerator(64));
        }
        return set;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Connecting to Ethereum ...");

        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/cd601df89a60461f9d744777d13769a5"));
        System.out.println("Successfully connected to Ethereum");
        System.out.println("Collection HashSet: ");
        System.out.println(amountKeys(RRS));





//            while (amountKeys(1).iterator().hasNext()) {
//                Credentials credentials = Credentials.create(String.valueOf(String.valueOf(amountKeys(1).iterator().next())));
//                String accAddress = credentials.getAddress();
//                EthGetBalance ethGetBalance1 = web3.ethGetBalance(accAddress, DefaultBlockParameterName.LATEST).send();
//                if (ethGetBalance1 != null) {
//                    System.out.println("ETH balance: " + ethGetBalance1.getBalance().toString());
//                }
//            }
    }
}