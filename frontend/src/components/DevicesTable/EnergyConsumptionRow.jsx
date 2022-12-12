import { TextField } from '@material-ui/core';
import React, { Component } from 'react'

export default function EnergyConsumptionRow({ device, energyConsumption, updateDeviceState }) {

    let handleEnergyConsumptionDateChange = (textValue) => {
        device.energyConsumption.map((currentEnergyConsumption) => {
            if (currentEnergyConsumption.id === energyConsumption.id) {
                energyConsumption.date = textValue;
                return energyConsumption;
            }

            return currentEnergyConsumption;
        });
        updateDeviceState(device)
    }

    let handleEnergyConsumptionValueChange = (textValue) => {
        device.energyConsumption.map((currentEnergyConsumption) => {
            if (currentEnergyConsumption.id === energyConsumption.id) {
                energyConsumption.consumption = textValue;
                return energyConsumption;
            }

            return currentEnergyConsumption;
        });

        updateDeviceState(device)
    }

    return (
        <React.Fragment>
            <tr>
                <td />
                <td>
                    <TextField
                        type="text"
                        value={energyConsumption.date}
                        style={{ width: 300 }}
                        onChange={(event) => handleEnergyConsumptionDateChange(event.target.value)}
                    ></TextField>
                </td>

                <td>

                    <TextField
                        type="text"
                        value={energyConsumption.consumption}
                        style={{ width: 100 }}
                        onChange={(event) => handleEnergyConsumptionValueChange(event.target.value)}
                    ></TextField>
                </td>
            </tr>
        </React.Fragment>
    );
}