package connections;

import encoders.PrivateKeyGenerator;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ConnectToNodes extends Thread {
    long startTime = System.currentTimeMillis();
    private final static int RRS = 10;

    public void run() {
        System.out.println("Connecting to Ethereum ...");

        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/cd601df89a60461f9d744777d13769a5"));
        System.out.println("Successfully connected to Ethereum");
        System.out.println("Load your MONEY: ");

        PrivateKeyGenerator pr = new PrivateKeyGenerator();
        Set<Object> set = new HashSet<>();

        for (int j = 0; j < RRS; j++) {
            for (int i = 0; i < RRS; i++) {
                set.add(pr.ethKeyGenerator(64));
            }
            for (Object o : set) {
                Credentials credentials = Credentials.create(o.toString());
                EthGetBalance balance = null;
                try {
                    balance = web3.ethGetBalance(credentials.getAddress().toLowerCase(Locale.ROOT), DefaultBlockParameterName.LATEST).send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert balance != null;
                BigDecimal balanceInEther = Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER);
                int sum = 0;
                BigDecimal u = BigDecimal.valueOf(0.0000000001);
                if (balanceInEther.compareTo(u) < 1) {

                    System.out.println("Balance: " + balanceInEther);
                    System.out.println("Public key: " + credentials.getAddress().toLowerCase(Locale.ROOT));
                    System.out.println("Private key: " + o);
                }
                sum++;
                System.out.println(sum);
            }
            System.out.println("NO MONEY YET");

            long endTime = System.currentTimeMillis();
            long result = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);

            System.out.print("Total execution time: " + result + " s");
            System.exit(0);
        }
    }
}
