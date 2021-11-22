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

public class ConnectToNodes extends Thread {

    private final static int RRS = 1000;

    public void run() {
        long time = System.currentTimeMillis();
        System.out.println("Connecting to Ethereum ...");

        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/cd601df89a60461f9d744777d13769a5"));
        System.out.println("Successfully connected to Ethereum");
        System.out.println("Load your MONEY: ");

        PrivateKeyGenerator pr = new PrivateKeyGenerator();
        Set<Object> set = new HashSet<>();
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
            BigDecimal balanceInEther = Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER);

            BigDecimal u = BigDecimal.valueOf(0.00001);
            if (balanceInEther.compareTo(u) > 0) {
                System.out.println("Balance: ");
                System.out.println(balanceInEther);

                System.out.println("Array public keys: ");
                System.out.println(credentials.getAddress().toLowerCase(Locale.ROOT));
                System.out.println("Array private keys: ");
                System.out.println(o);
            }
        }
        System.out.println("NO MONEY YET");
        time = System.nanoTime() - time;
        System.out.printf("Elapsed %,9.3f s\n", time / 1_000_000_000_000_000.0);
        System.exit(0);
    }
}
