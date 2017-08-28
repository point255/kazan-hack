pragma solidity ^0.4.4;

contract Ownable {

  function Ownable() {
  }

  address public owner = msg.sender;

  modifier onlyOwner {
    require(msg.sender == owner);
    _;
  }

  function changeOwner(address _newOwner)
  onlyOwner
  {
    require(_newOwner != 0x0);
    owner = _newOwner;
  }
}
