import { TextField } from '@material-ui/core';
import React, { Component } from 'react'
import EnergyConsumptionRow from './EnergyConsumptionRow';

export default function DeviceEntityInTable({ device, deleteDevice, updateDeviceState, addEmptyConsumption }) {
    
    let handleNameTextFieldChange = (newTextField) => {
        device.name = newTextField;
        updateDeviceState(device);
    }

    let handleDescriptionTextFieldChange = (newTextField) => {
        device.description = newTextField;
        updateDeviceState(device);
    }

    let handleMHECCTextFieldhange = (newTextField) => {
        device.maximumHourlyEnergyConsumption = newTextField;
        updateDeviceState(device);
    }

    return (
        <React.Fragment>
            <tr>
                <td>
                    <button
                        className="btn btn-danger btn-sm m-2"
                        onClick={() => {
                            deleteDevice(device);
                        }}
                    >
                        Delete Device
                    </button>
                </td>
                <td>
                    <TextField
                        type="text"
                        value={device.id}
                        style={{ width: 350 }}
                        disabled
                    ></TextField>
                </td>
                <td>
                    <TextField
                        type="text"
                        value={device.name}
                        style={{ width: 350 }}
                        onChange={(event) => handleNameTextFieldChange(event.target.value)}
                    ></TextField>
                </td>
                <td>
                    <TextField
                        type="text"
                        value={device.description}
                        style={{ width: 350 }}
                        onChange={(event) => handleDescriptionTextFieldChange(event.target.value)}
                    ></TextField>
                </td>
                <td>
                    <TextField
                        type="text"
                        value={device.maximumHourlyEnergyConsumption}
                        style={{ width: 350 }}
                        onChange={(event) => handleMHECCTextFieldhange(event.target.value)}
                    ></TextField>
                </td>
            </tr>

            <tr>
                <th />
                <th scope="col">Date</th>
                <th scope="col">Consumption</th>
            </tr>

            {device.energyConsumption.length > 0
                && device.energyConsumption.map((energyConsumption) => (
                    <EnergyConsumptionRow
                        device={device}
                        energyConsumption={energyConsumption}
                        updateDeviceState={updateDeviceState}
                    ></EnergyConsumptionRow>
                ))}

            <button
                className="btn btn-primary btn-sm m-2"
                onClick={() => {
                    addEmptyConsumption(device)
                }}
            >
                Add Consumption
            </button>
        </React.Fragment>
    );
}

