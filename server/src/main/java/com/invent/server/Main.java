package com.invent.server;

import com.invent.bot.Service;
import com.invent.contract.InventDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final BigInteger GAS_PRICE = BigInteger.valueOf(9_000L);
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(1_500_000L);

    private static final String CONTRACT_ADR = "0x2df39e12e8e4a5ff1e917af9fdbd01186fa72d24";

    public class User {
        String privateKey;
        String publicAdr;
        String name;

        User(String key, String adr, String name) {
            privateKey = key;
            publicAdr = adr;
            this.name = name;
        }
    }

    private final List<User> mUsers;
    private final Web3j mWeb3;
    private InventDB mInventDB;

    public static void main(String[] args) {
        /*
        runService();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        Main m = new Main();
//        m.setUser(0);
//        m.setUser(1);
//        m.setUser(2);
//        m.addProject(0, "Project 1");
        /*
        List<Uint256> projects = m.getProjectsForUser(0);
        if (projects != null && projects.size() > 0) {
            m.joinToProject(1, projects.get(0), 2);
            m.joinToProject(2, projects.get(0), 3);
        }
        */
//        m.showUsersForProject(0, new Uint256(1));
//        m.setStatusForProject(0, 1, 5);
        m.showProjects(0);
        System.out.println("finish...");
    }

    private static void runService() {
        try {
            Service service = new Service();
            service.start();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Main() {
        mUsers = new ArrayList<>();
        mUsers.add(new User("73dfc53a5ea6dc4ecf9a8a0f827c7063795264dfd626534aa9673594af4a578a",
                "0xf9614c573f8c8ffab42fbeb2cfc094a79245b7da", "user1"));
        mUsers.add(new User("b201dbf131556d7cb13dad38beef20a3afbee01bfb9f3f41af593b467f310a14",
                "0x9652efa7ac5e99d2541c29fae1e24479ed5ca8ee", "user2"));
        mUsers.add(new User("a4f877dd9bf1f79e92c0348fe6cdb8e7f0b1265f3e2472da36b7928450d376e1",
                "0xbedfd9e70e8c38e091ffe0e10550079329b51425", "user3"));

        mWeb3 = Web3j.build(new HttpService("http://localhost:8545"));
    }

    private InventDB getInvent(int userNum) {
        final Credentials credentials = Credentials.create(mUsers.get(userNum).privateKey);
        return InventDB.load(CONTRACT_ADR, mWeb3, credentials, GAS_PRICE, GAS_LIMIT);
    }

    private void setUser(int userNum) {
        User user = mUsers.get(userNum);
        InventDB inventDB = getInvent(userNum);
        inventDB.setUser(new Utf8String(user.name), new Uint256(user.name.hashCode()));
        /*
        try {
            Object obj = inventDB.users(new Address("0xab1c4672bdc3d35f1330fde2b1f02024a4be136d")).get();
            System.out.println("class:" + obj.getClass().getName());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        */
    }

    private void addProject(int userNum, String name) {
        InventDB inventDB = getInvent(userNum);
        inventDB.addProject(new Utf8String(name));
    }

    private void joinToProject(int userNum, Uint256 projectId, int roleId) {
        InventDB inventDB = getInvent(userNum);
        inventDB.joinToProject(projectId, new Address(mUsers.get(userNum).publicAdr), new Uint256(roleId));
    }

    private List<Uint256> getProjectsForUser(int userNum) {
        User user = mUsers.get(userNum);
        InventDB inventDB = getInvent(userNum);

        try {
            return inventDB.getProjectsForUser(new Address(user.publicAdr)).get().getValue();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void showProjects(int userNum) {
        List<Uint256> items = getProjectsForUser(userNum);
        if (items != null) {
            System.out.println("Projects count: " + items.size());
            for (Uint256 id : items) {
                showProjectInfo(userNum, id);
            }
        }
    }

    private void showProjectInfo(int userNum, Uint256 projectId) {
        InventDB inventDB = getInvent(userNum);
        try {
            List<Type> items = inventDB.projects(projectId).get();

            // status
            Uint256 status = inventDB.getProjectStatus(projectId).get();
            System.out.println("status: " + status.getValue());

            // balance
            Uint256 balance = inventDB.getBalanceForProject(projectId).get();
            System.out.println("balance: " + balance.getValue());

            // users
            List<Address> addresses = inventDB.getUsersForProject(projectId).get().getValue();
            for (Address address : addresses) {
                System.out.println(address);
                showUserInfo(userNum, address);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void showUsersForProject(int userNum, Uint256 projectId) {
        InventDB inventDB = getInvent(userNum);
        try {
            List<Address> addresses = inventDB.getUsersForProject(projectId).get().getValue();
            for (Address address : addresses) {
                System.out.println(address);
//                showUserInfo(userNum, address);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void showUserInfo(int userNum, Address address) {
        InventDB inventDB = getInvent(userNum);
        try {
            List<Type> items = inventDB.users(address).get();
            for (Type item : items) {
                System.out.println(item.getValue());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void setStatusForProject(int userNum, int projectId, int status) {
        InventDB inventDB = getInvent(userNum);
        inventDB.setProjectStatus(new Uint256(projectId), new Uint256(status));
    }
}
