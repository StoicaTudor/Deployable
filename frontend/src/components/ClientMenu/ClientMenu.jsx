import React from "react";
// import { ListGroup, ListGroupItem } from "react-bootstrap";
import Device from "./Device";
import { useEffect, useState } from "react";
import axios from "axios";
import Graph from "../Graph/Graph";
import { ListGroup, ListGroupItem } from "reactstrap";
import GraphCalendar from "../GraphCalendar";
import NavigationBar from "../NavigationBar";

let Utils = require("../../Util.js").default;

export default function ClientMenu() {
  const [client, setClient] = useState();
  const [devicesSelected, setDevicesSelected] = useState([]);
  const [calendarSelectedDate, setCalendarSelectedDate] = useState(new Date());

  let deviceIsActive = (deviceToCheck) => {

    let foundDeviceInArray = devicesSelected.find(
      (device) => device.id === deviceToCheck.id
    );

    console.log(deviceToCheck.id + " is " + !Utils.isUndefined(foundDeviceInArray));

    return !Utils.isUndefined(foundDeviceInArray);
  };

  // let selectDevice = (device) => {

  //   // device.isActive = true

  //   const newDevicesList = devicesSelected;
  //   newDevicesList.push(device);
  //   console.log("selectDevice");
  //   console.log(newDevicesList);
  //   setDevicesSelected(newDevicesList);


  // };

  let updateClientDevice = (clickedDevice, clientDevice, statusToSet) => {

    // console.log(statusToSet)

    if (clickedDevice.id === clientDevice.id)
      clientDevice.isActive = statusToSet;

    return clientDevice;
  };

  // let selectDevice = useCallback((selectedDevice, selected) => {

  //   // const newDevicesList = devicesSelected;
  //   // newDevicesList.push(device);
  //   // setDevicesSelected(newDevicesList);
  //   // console.log("selectDevice")

  //   let newClientDevices = client.devices.map((clientDevice) => updateClientDevice(selectedDevice, clientDevice, selected))
  //   client.devices = newClientDevices;
  //   setClient(client);

  // }, [devicesSelected]);

  let clickDevice = (clickedDevice, statusToSet) => {
    let newClientDevices = client.devices.map((clientDevice) => updateClientDevice(clickedDevice, clientDevice, statusToSet))
    // console.log(newClientDevices)
    client.devices = newClientDevices;
    setClient(client);
  };

  // let unselectDevice = useCallback((device) => {

  //   setDevicesSelected(devicesSelected.filter((selectedDevice) => device.id !== selectedDevice.id));
  //   console.log("unselectDevice")

  // }, [devicesSelected]);


  // let unselectDevice = (device) => {
  //   setDevicesSelected(devicesSelected.filter((selectedDevice) => device.id !== selectedDevice.id));
  // };

  async function getClient(userId = "12b7f556-fb3f-490a-b019-4aa9d23fa647") {
    await axios
      .get("/administrator/getUser", { params: { userId: userId } })
      .then((res) => setClient(res.data))
      // .then((res) => console.log(res.data))
      .catch((err) => console.log(err));
  }

  useEffect(() => {
    getClient();
  }, []);

  return (
    <React.Fragment>

      <NavigationBar />

      <div class="d-flex justify-content-between">
        {/* <div className="p-2 col-example text-left">aaa</div>
        <div className="p-2 col-example text-left">bbb</div>
        <div className="p-2 col-example text-left">ccc</div> */}

        <div class="p-2 col-example text-left">
          <ListGroup className="mb-2">
            {client &&
              client.devices.map((device) => (
                <Device
                  key={device.id}
                  device={device}
                  clickDevice={clickDevice}
                // unselectDevice={unselectDevice}
                ></Device>
              ))}
          </ListGroup>
        </div>

        <div>
          <GraphCalendar
            setCalendarSelectedDate={setCalendarSelectedDate}
            calendarSelectedDate={calendarSelectedDate}
          />
        </div>

        {client && client.devices
          ? <div className="p-2 col-example">
            <Graph
              devices={client.devices}
              calendarSelectedDate={calendarSelectedDate}
            />
          </div>
          : <></>}

      </div>

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
