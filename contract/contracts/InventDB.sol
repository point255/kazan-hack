pragma solidity ^0.4.4;

import "./Ownable.sol";

contract InventDB is Ownable {
  // constants
  uint constant PROJECT_MANAGER = 1;
  uint constant CHIEF_DESIGNER = 2;
  uint constant DIRECTOR_OF_OPERATIONS = 3;

  uint constant DEVELOPMENT = 1;
  uint constant TZ = 2;
  uint constant DOCUMENTS = 3;
  uint constant COMPILE = 4;
  uint constant CLOSE = 5;

  uint constant DEFAULT_AMOUNT = 100;

  struct User {
    string name;
    uint createAt;
    uint balance;
    uint[] projects;
  }

  struct Project {
    uint status;
    string name;
    address owner;
    uint balance;
    uint createAt;
    address[] users;
    mapping (address => uint) usersWithRole;
  }

  mapping (address => User) public users;
  mapping (uint => address) public usersIds;
  mapping (uint => Project) public projects;

  mapping (uint => uint) tokensKof;

  uint projectId = 1;

  event onUserAdded(address user);
  event onProjectAdded(uint id);
  event onJoinUserToProject(address user, uint id);
  event onChangeProjectStatus(uint id, uint oldStatus, uint newStatus);

  function InventDB() {
    tokensKof[PROJECT_MANAGER] = 50;
    tokensKof[CHIEF_DESIGNER] = 25;
    tokensKof[DIRECTOR_OF_OPERATIONS] = 25;
  }

  function setUser(string name, uint userId) {
    User storage usr = users[msg.sender];

    if (usr.createAt == 0) {
      usr.createAt = now;
      onUserAdded(msg.sender);
    }

    usersIds[userId] = msg.sender;
    usr.name = name;
  }

  function getUsersForProject(uint id) constant returns (address[]) {
    return projects[id].users;
  }

  function getBalanceForUser(address adr) constant returns (uint) {
    return users[adr].balance;
  }

  function addProject(string name) {
    uint id = projectId++;
    Project storage p = projects[id];
    p.status = DEVELOPMENT;
    p.name = name;
    p.owner = msg.sender;
    p.balance = DEFAULT_AMOUNT;
    p.createAt = now;

    joinToProject(id, msg.sender, PROJECT_MANAGER);
    onProjectAdded(id);
  }

  function getProjectsForUser(address adr) constant returns (uint[]) {
    return users[adr].projects;
  }

  function joinToProject(uint id, address adr, uint role) {
    User storage user = users[adr];
    if (user.createAt > 0) {
      Project storage project = projects[id];
      if (project.createAt > 0) {
        user.projects.push(id);
        project.usersWithRole[adr] = role;
        project.users.push(adr);
        onJoinUserToProject(adr, id);
      }
    }
  }

  function setProjectStatus(uint id, uint status) {
    Project storage project = projects[id];
    if (project.createAt > 0 && project.status != status) {
      uint old = project.status;
      project.status = status;
      onChangeProjectStatus(id, old, project.status);

      if (status == CLOSE) {
        uint sum = 0;
        for (uint i = 0; i < project.users.length; i++) {
          uint role = project.usersWithRole[project.users[i]];
          uint b = (project.balance * tokensKof[role]) / 100;
          users[project.users[i]].balance += b;
          sum = sum + b;
        }
        project.balance = project.balance - sum;
        require(project.balance >= 0);
      }
    }
  }

  function getProjectStatus(uint id) constant returns (uint) {
    return projects[id].status;
  }

  function getBalanceForProject(uint id) constant returns (uint) {
    return projects[id].balance;
  }

}
