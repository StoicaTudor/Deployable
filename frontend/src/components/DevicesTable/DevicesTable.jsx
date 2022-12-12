import React, { Component } from 'react';
import DeviceEntityInTable from './DeviceEntityInTable';

export default function DevicesTable({ devices, deleteDevice, saveState, addEmptyDevice, updateDeviceState, addEmptyConsumption }) {

    return (
        <React.Fragment>
            <table>
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Max Hourly Energy Consumption</th>
                    </tr>
                </thead>

                <tbody>
                    {devices
                        && devices.map((device) => (
                            <DeviceEntityInTable
                                device={device}
                                deleteDevice={deleteDevice}
                                updateDeviceState={updateDeviceState}
                                addEmptyConsumption={addEmptyConsumption}
                            />
                        ))}
                </tbody>
            </table>

            <button
                className="btn btn-primary btn-sm m-2"
                onClick={saveState}
            >
                Save
            </button>

            <button
                className="btn btn-primary btn-sm m-2"
                onClick={addEmptyDevice}
            >
                Add Device
            </button>
        </React.Fragment>
    );
}