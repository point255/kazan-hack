package com.invent.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 */
public final class InventDB extends Contract {
    private static final String BINARY = "606060405260008054600160a060020a03191633600160a060020a03161790556001600555341561002f57600080fd5b5b5b5b600460205260327fabd6e7cb50984ff9c2f3e18a2660c3353dadf4e3291deeb275dae2cd1e44fe055560197f91da3fd0782e51c6b3986e9e672fd566868e71f3dbc2d6c2cd6fbb3e361af2a781905560036000527f2e174c10e159ea99b867ce3205125c24a42d128804e4070ed6fcc8cc98166aa0555b5b610dcc806100b96000396000f300606060405236156100cd5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630ed047d481146100d2578063107046bd14610125578063153467b0146101ea578063223153fa1461021b57806322ac6c161461028e5780633a517151146102b65780634aae24f7146102e857806350f535711461030f57806370eda513146103795780638da5cb5b146103945780639675c009146103c3578063a6f9dae1146103eb578063a87430ba1461040c578063d3bddc2b146104c3575b600080fd5b34156100dd57600080fd5b61012360046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061051895505050505050565b005b341561013057600080fd5b61013b600435610652565b604051858152600160a060020a0384166040820152606081018390526080810182905260a0602082018181528654600260001961010060018416150201909116049183018290529060c0830190879080156101d75780601f106101ac576101008083540402835291602001916101d7565b820191906000526020600020905b8154815290600101906020018083116101ba57829003601f168201915b5050965050505050505060405180910390f35b34156101f557600080fd5b610209600160a060020a0360043516610688565b60405190815260200160405180910390f35b341561022657600080fd5b61023a600160a060020a03600435166106aa565b60405160208082528190810183818151815260200191508051906020019060200280838360005b8381101561027a5780820151818401525b602001610261565b505050509050019250505060405180910390f35b341561029957600080fd5b610209600435610731565b60405190815260200160405180910390f35b34156102c157600080fd5b6102cc60043561074a565b604051600160a060020a03909116815260200160405180910390f35b34156102f357600080fd5b610123600435600160a060020a0360243516604435610765565b005b341561031a57600080fd5b61023a600435610855565b60405160208082528190810183818151815260200191508051906020019060200280838360005b8381101561027a5780820151818401525b602001610261565b505050509050019250505060405180910390f35b341561038457600080fd5b6101236004356024356108d4565b005b341561039f57600080fd5b6102cc610a08565b604051600160a060020a03909116815260200160405180910390f35b34156103ce57600080fd5b610209600435610a17565b60405190815260200160405180910390f35b34156103f657600080fd5b610123600160a060020a0360043516610a2c565b005b341561041757600080fd5b61042b600160a060020a0360043516610a89565b6040516020810183905260408101829052606080825284546002600019610100600184161502019091160490820181905281906080820190869080156104b25780601f10610487576101008083540402835291602001916104b2565b820191906000526020600020905b81548152906001019060200180831161049557829003601f168201915b505094505050505060405180910390f35b34156104ce57600080fd5b61012360046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496505093359350610aa692505050565b005b600580546001810190915560c0604051908101604052806001815260200183815260200133600160a060020a031681526020016064815260200142815260200160006040518059106105675750595b908082528060200260200182016040525b5090526000828152600360205260409020815181556020820151816001019080516105a7929160200190610b5c565b50604082015160028201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055606082015181600301556080820151816004015560a08201518160050190805161060a929160200190610bdb565b5090505061061a81336001610765565b7f3c8ca33d8e8fbe618893f557ab10a74aa2cedac95f3babf926beca1d22883c948160405190815260200160405180910390a15b5050565b6003602081905260009182526040909120805460028201549282015460048301549193600190930192600160a060020a03169185565b600160a060020a0381166000908152600160205260409020600201545b919050565b6106b2610c50565b6001600083600160a060020a0316600160a060020a0316815260200190815260200160002060030180548060200260200160405190810160405280929190818152602001828054801561072457602002820191906000526020600020905b815481526020019060010190808311610710575b505050505090505b919050565b600081815260036020819052604090912001545b919050565b600260205260009081526040902054600160a060020a031681565b600160a060020a03821660009081526001602081905260408220908101549091908190111561084c57506000848152600360205260409020600281015433600160a060020a0390811691161480156107c1575060008160040154115b1561084c57600382018054600181016107da8382610c62565b916000526020600020900160005b5086905550600160a060020a038416600090815260068201602052604090208390556005810180546001810161081e8382610c62565b916000526020600020900160005b8154600160a060020a038089166101009390930a92830292021916179055505b5b5b5050505050565b61085d610c50565b6003600083815260200190815260200160002060050180548060200260200160405190810160405280929190818152602001828054801561072457602002820191906000526020600020905b8154600160a060020a031681526001909101906020018083116108a9575b505050505090505b919050565b600082815260036020526040812060048101549091908190819081908190118015610900575084548614155b156109fd5785855560058614156109fd5784600301549350600092505b60058501548310156109f557846006016000866005018581548110151561094057fe5b906000526020600020900160005b9054600160a060020a036101009290920a90041681526020808201929092526040908101600090812054808252600490935220546003870154919350606491025b049050806001600087600501868154811015156109a857fe5b906000526020600020900160005b9054600160a060020a036101009290920a900416815260208101919091526040016000206002018054909101905592839003925b60019092019161091d565b600385018190555b5b5b50505050505050565b600054600160a060020a031681565b6000818152600360205260409020545b919050565b60005433600160a060020a03908116911614610a4757600080fd5b600160a060020a0381161515610a5c57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0383161790555b5b50565b600160208190526000918252604090912090810154600282015483565b600160a060020a0333166000908152600160208190526040909120908101541515610b0f574260018201557f155b6d113493a21307ef710fa8c0025cece1ce25a5a35561727361bbd0c8976b33604051600160a060020a03909116815260200160405180910390a15b6000828152600260205260409020805473ffffffffffffffffffffffffffffffffffffffff191633600160a060020a031617905580838051610b55929160200190610b5c565b505b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b9d57805160ff1916838001178555610bca565b82800160010185558215610bca579182015b82811115610bca578251825591602001919060010190610baf565b5b50610bd7929150610d47565b5090565b828054828255906000526020600020908101928215610c3f579160200282015b82811115610c3f578251825473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039190911617825560209290920191600190910190610bfb565b5b50610bd7929150610d68565b5090565b60206040519081016040526000815290565b815481835581811511610b5757600083815260209020610b57918101908301610d47565b5b505050565b815481835581811511610b5757600083815260209020610b57918101908301610d47565b5b505050565b60206040519081016040526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b9d57805160ff1916838001178555610bca565b82800160010185558215610bca579182015b82811115610bca578251825591602001919060010190610baf565b5b50610bd7929150610d47565b5090565b610d6591905b80821115610bd75760008155600101610d4d565b5090565b90565b610d6591905b80821115610bd757805473ffffffffffffffffffffffffffffffffffffffff19168155600101610d6e565b5090565b905600a165627a7a723058201a07810b36295d70e7784d64168e829f498d13daece2118ea839a7104afdc69b0029";

    private InventDB(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private InventDB(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<OnUserAddedEventResponse> getOnUserAddedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("onUserAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OnUserAddedEventResponse> responses = new ArrayList<OnUserAddedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OnUserAddedEventResponse typedResponse = new OnUserAddedEventResponse();
            typedResponse.user = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OnUserAddedEventResponse> onUserAddedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("onUserAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OnUserAddedEventResponse>() {
            @Override
            public OnUserAddedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OnUserAddedEventResponse typedResponse = new OnUserAddedEventResponse();
                typedResponse.user = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<OnProjectAddedEventResponse> getOnProjectAddedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("onProjectAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OnProjectAddedEventResponse> responses = new ArrayList<OnProjectAddedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OnProjectAddedEventResponse typedResponse = new OnProjectAddedEventResponse();
            typedResponse.id = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OnProjectAddedEventResponse> onProjectAddedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("onProjectAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OnProjectAddedEventResponse>() {
            @Override
            public OnProjectAddedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OnProjectAddedEventResponse typedResponse = new OnProjectAddedEventResponse();
                typedResponse.id = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<JoinUserToProjectEventResponse> getJoinUserToProjectEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("joinUserToProject", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<JoinUserToProjectEventResponse> responses = new ArrayList<JoinUserToProjectEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            JoinUserToProjectEventResponse typedResponse = new JoinUserToProjectEventResponse();
            typedResponse.user = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.id = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<JoinUserToProjectEventResponse> joinUserToProjectEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("joinUserToProject", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, JoinUserToProjectEventResponse>() {
            @Override
            public JoinUserToProjectEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                JoinUserToProjectEventResponse typedResponse = new JoinUserToProjectEventResponse();
                typedResponse.user = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.id = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<ChangeProjectStatusEventResponse> getChangeProjectStatusEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("changeProjectStatus", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ChangeProjectStatusEventResponse> responses = new ArrayList<ChangeProjectStatusEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ChangeProjectStatusEventResponse typedResponse = new ChangeProjectStatusEventResponse();
            typedResponse.id = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.oldStatus = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.newStatus = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ChangeProjectStatusEventResponse> changeProjectStatusEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("changeProjectStatus", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ChangeProjectStatusEventResponse>() {
            @Override
            public ChangeProjectStatusEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ChangeProjectStatusEventResponse typedResponse = new ChangeProjectStatusEventResponse();
                typedResponse.id = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.oldStatus = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.newStatus = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> addProject(Utf8String name) {
        Function function = new Function("addProject", Arrays.<Type>asList(name), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> projects(Uint256 param0) {
        Function function = new Function("projects", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<Uint256> getBalanceForUser(Address adr) {
        Function function = new Function("getBalanceForUser", 
                Arrays.<Type>asList(adr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<DynamicArray<Uint256>> getProjectsForUser(Address adr) {
        Function function = new Function("getProjectsForUser", 
                Arrays.<Type>asList(adr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getBalanceForProject(Uint256 id) {
        Function function = new Function("getBalanceForProject", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> usersIds(Uint256 param0) {
        Function function = new Function("usersIds", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> joinToProject(Uint256 id, Address adr, Uint256 role) {
        Function function = new Function("joinToProject", Arrays.<Type>asList(id, adr, role), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<DynamicArray<Address>> getUsersForProject(Uint256 id) {
        Function function = new Function("getUsersForProject", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setProjectStatus(Uint256 id, Uint256 status) {
        Function function = new Function("setProjectStatus", Arrays.<Type>asList(id, status), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getProjectStatus(Uint256 id) {
        Function function = new Function("getProjectStatus", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> changeOwner(Address _newOwner) {
        Function function = new Function("changeOwner", Arrays.<Type>asList(_newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> users(Address param0) {
        Function function = new Function("users", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setUser(Utf8String name, Uint256 userId) {
        Function function = new Function("setUser", Arrays.<Type>asList(name, userId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<InventDB> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(InventDB.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<InventDB> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(InventDB.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static InventDB load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new InventDB(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static InventDB load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new InventDB(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class OnUserAddedEventResponse {
        public Address user;
    }

    public static class OnProjectAddedEventResponse {
        public Uint256 id;
    }

    public static class JoinUserToProjectEventResponse {
        public Address user;

        public Uint256 id;
    }

    public static class ChangeProjectStatusEventResponse {
        public Uint256 id;

        public Uint256 oldStatus;

        public Uint256 newStatus;
    }
}
