import React, { Component } from 'react'
import NavigationBar from './NavigationBar';
import DevicesTable from './DevicesTable/DevicesTable'
import { useEffect, useState } from "react";
import axios from "axios";

export default function DevicesMenu() {

    const [
        devices, setDevices] = useState();

    async function getAllDevices() {
        await axios
            .get("/administrator/getAllDevices")
            .then((res) => setDevices(res.data))
            // .then((res) => console.log(res.data))
            .catch((err) => console.log(err));
    }

    async function addEmptyDevice() {
        await axios
            .post("/administrator/createEmptyDevice", {})
            .then((res) => getAllDevices())
            .catch((err) => console.log(err));
    }

    async function deleteDevice(device) {
        await axios
            .delete("/administrator/deleteDevice", { params: { deviceId: device.id } })
            .then((res) => getAllDevices())
            .catch((err) => console.log(err));
    }

    async function saveState() {
        devices.forEach(async (currentDevice) => {
            await axios
                .put("/administrator/updateDevice?deviceId=" + currentDevice.id, currentDevice)
                .then((res) => getAllDevices())
                .catch((err) => {
                    console.log(err)
                    alert(err.response.data)
                });
        });
    }

    let updateDeviceState = (device) => {
        const newDevices = devices.map((currentDevice) => {
            if (currentDevice.id === device.id) {
                return device;
            }

            return currentDevice;
        });

        setDevices(newDevices);
    }

    let addEmptyConsumption = (device) => {

        device.energyConsumption.push({ date: "", consumption: 0 });

        const newDevices = devices.map((currentDevice) => {
            if (currentDevice.id === device.id) {
                return device;
            }

            return currentDevice;
        });

        setDevices(newDevices);
    }

    useEffect(() => {
        getAllDevices();
    }, []);

    return (
        <React.Fragment>
            <NavigationBar />
            <DevicesTable
                devices={devices}
                deleteDevice={deleteDevice}
                saveState={saveState}
                addEmptyDevice={addEmptyDevice}
                updateDeviceState={updateDeviceState}
                addEmptyConsumption={addEmptyConsumption}
            />
        </React.Fragment>
    );
}