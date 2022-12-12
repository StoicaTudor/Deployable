import React from "react";
// import { ListGroup, ListGroupItem } from "react-bootstrap";
import { useEffect, useState } from "react";
import axios from "axios";
import { ListGroup, ListGroupItem } from "reactstrap";
import User from "./User";
import NavigationBar from "./NavigationBar";
import UsersTable from "./ClientsTable/UsersTable";

let Utils = require("../Util.js").default;

export default function AdminMenu() {
  const [users, setUsers] = useState();
  const [devices, setDevices] = useState();
  const [devicesSelected, setDevicesSelected] = useState([]);

  let deviceIsSelected = (deviceToCheck) => {
    let foundDeviceInArray = devicesSelected.find(
      (device) => device.id === deviceToCheck.id
    );
    return !Utils.isUndefined(foundDeviceInArray);
  };

  let selectDevice = (device) => {
    const newDevicesList = devicesSelected;
    newDevicesList.push(device);
    setDevicesSelected(newDevicesList);
  };

  let unselectDevice = (device) => {
    const newDevicesList = devicesSelected;
    newDevicesList.filter((selectedDevice) => device.id !== selectedDevice.id);
    setDevicesSelected(newDevicesList);
  };

  async function getAllUsers() {
    await axios
      .get("/administrator/getAllUsers")
      .then((res) => setUsers(res.data))
      // .then((res) => console.log(res.data))
      .catch((err) => console.log(err));
  }

  async function getAllDevices() {
    await axios
      .get("/administrator/getAllDevices")
      .then((res) => setDevices(res.data))
      // .then((res) => console.log(res.data))
      .catch((err) => console.log(err));
  }

  async function deleteUser(user) {
    await axios
      .delete("/administrator/deleteUser", { params: { userId: user.id } })
      .then((res) => getAllUsers())
      .catch((err) => console.log(err));
  }

  async function saveUpdatedUsers() {
    // await axios
    //   .get("/administrator/deleteUser", { params: { userId: client.id} })
    //   .then((res) => getAllUsers())
    //   .catch((err) => console.log(err));
  }

  async function addUser() {
    await axios
      .post("/administrator/createEmptyUser", {})
      .then((res) => getAllUsers())
      .catch((err) => console.log(err));
  }

  async function addDeviceToUser(selectedDeviceId, user) {

    let foundDeviceWithId = devices.find(
      (device) => device.id === selectedDeviceId
    );

    user.devices.push(foundDeviceWithId);

    await axios
      .put("/administrator/updateUser?userId=" + user.id, user)
      .then((res) => getAllUsers())
      .catch((err) => {
        console.log(err)
        alert(err.response.data)
      });
  }

  async function deleteDeviceFromUser(device, user) {

    user.devices = user.devices.filter((userDevice) => userDevice.id !== device.id);

    await axios
      .put("/administrator/updateUser?userId=" + user.id, user)
      .then((res) => getAllUsers())
      .catch((err) => {
        console.log(err)
        alert(err.response.data)
      });
  }

  let updateUserState = (user) => {
    const newUsers = users.map((currentUser) => {
      if (currentUser.id === user.id) {
        return user;
      }

      return currentUser;
    });

    setUsers(newUsers);
  }

  useEffect(() => {
    getAllUsers();
    getAllDevices();
  }, []);

  return (
    <React.Fragment>

      <NavigationBar />

      {users
        && <UsersTable
          users={users}
          deleteUser={deleteUser}
          devices={devices}
          addDeviceToUser={addDeviceToUser}
          deleteDeviceFromUser={deleteDeviceFromUser}
          updateUserState={updateUserState}
        />}

      <button
        className="btn btn-primary btn-sm m-2"
        onClick={() => {
          saveUpdatedUsers();
        }}
      >
        Save
      </button>

      <button
        className="btn btn-primary btn-sm m-2"
        onClick={() => {
          addUser();
        }}
      >
        Add User
      </button>

      {/* <div class="d-flex justify-content-between"> */}
      {/* <ListGroup className="mb-2">
            {client &&
              client.devices.map((device) => (
                <Device
                  key={device.id}
                  device={device}
                  isActive={deviceIsSelected(device)}
                  selectDevice={selectDevice}
                  unselectDevice={unselectDevice}
                ></Device>
              ))}
          </ListGroup> */}

      {/* <ListGroup className="mb-2">
            {users &&
              users.map((user) => (
                <User key={user.id} user={user} isActive={false}></User>
              ))}
          </ListGroup> */}



      {/* </div> */}

      {/* <div class="d-flex justify-content-between">
        <ListGroup className="mb-2">
          {client &&
            client.devices.map((device) => (
              <Device key={device.id} device={device}></Device>
            ))}
        </ListGroup>
      </div> */}
    </React.Fragment>
  );
}
