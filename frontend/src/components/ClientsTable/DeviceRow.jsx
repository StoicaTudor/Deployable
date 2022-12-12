import { TextField } from '@material-ui/core';
import React, { Component } from 'react';

export default function DeviceRow({ device, deleteDeviceFromUser, user }) {

    return (
        <React.Fragment>
            <tr>
                <td />
                <td>
                    <button
                        className="btn btn-danger btn-sm m-2"
                        onClick={() => {
                            deleteDeviceFromUser(device, user)
                        }}
                    >
                        Delete Device Mapping
                    </button>
                </td>

                <td>
                    <TextField
                        type="text"
                        value={device.id}
                        style={{ width: 350 }}
                        onChange={(e) => {
                            // this.props.onGenreChange(this.props.book, e.target.value);
                        }}
                        disabled
                    ></TextField>
                </td>

                <td>
                    <TextField
                        type="text"
                        value={device.name}
                        style={{ width: 350 }}
                        onChange={(e) => {
                            // handleDeviceNameTextFieldChange(e.target.value)
                        }}
                        disabled
                    ></TextField>
                </td>
            </tr>
        </React.Fragment>
    );
}