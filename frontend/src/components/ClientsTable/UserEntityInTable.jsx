import { TextField } from '@material-ui/core';
import React, { Component } from 'react';
import DeviceRow from './DeviceRow';
import { useState } from "react";

export default function UserEntityInTable({ user, deleteUser, addDeviceToUser, devices, deleteDeviceFromUser, updateUserState }) {

    const [selectedDeviceId, setSelectedDeviceId] = useState();

    let handleSelectChange = (event) => {
        setSelectedDeviceId(event.target.value);
    }

    let handleUserNameTextFieldChange = (newTextField) => {
        user.name = newTextField;
        updateUserState(user);
    };

    return (
        <React.Fragment>
            <tr>
                <td>
                    <button
                        className="btn btn-danger btn-sm m-2"
                        onClick={() => {
                            deleteUser(user);
                        }}
                    >
                        Delete Client
                    </button>
                </td>

                <td>
                    <TextField
                        type="text"
                        value={user.id}
                        style={{ width: 350 }}
                        disabled
                    ></TextField>
                </td>

                <td>
                    <TextField
                        type="text"
                        value={user.name}
                        style={{ width: 350 }}
                        onChange={(e) => {
                            handleUserNameTextFieldChange(e.target.value)
                        }}
                    ></TextField>
                </td>

            </tr>

            <tr>
                <th scope="col"><button
                    className="btn btn-primary btn-sm m-2"
                    onClick={() => {
                        if (selectedDeviceId === undefined) {

                            if (devices.length > 0) {
                                addDeviceToUser(devices[0].id, user);
                            } else {
                                alert("Cannot add device mapping, there are no devices")
                            }

                        } else {
                            addDeviceToUser(selectedDeviceId, user);
                        }
                    }}
                >
                    Create Device Mapping
                </button>
                </th>

                <th scope="col">
                    <select
                        name="selectList"
                        id="selectList"
                        onChange={handleSelectChange}
                        defaultValue={{ label: "Select Dept", value: 0 }}
                    >
                        {devices
                            && devices.length > 0
                            && devices.map(device =>
                                <option value={device.id}>{device.name}</option>)
                        }
                    </select>
                </th>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
            </tr>

            {user.devices.length > 0
                && user.devices.map((device) => (
                    <DeviceRow
                        device={device}
                        deleteDeviceFromUser={deleteDeviceFromUser}
                        user={user}
                    ></DeviceRow>
                ))}

        </React.Fragment>
    );
}