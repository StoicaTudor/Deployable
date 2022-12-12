import React, { Component } from 'react'
import UserEntityInTable from './UserEntityInTable';


export default function UsersTable({ users, deleteUser, devices, addDeviceToUser, deleteDeviceFromUser, updateUserState }) {

    return (
        <React.Fragment>
            <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                </tr>
            </thead>

            <tbody>
                {users.map((user) => (
                    <UserEntityInTable
                        user={user}
                        deleteUser={deleteUser}
                        addDeviceToUser={addDeviceToUser}
                        devices={devices}
                        deleteDeviceFromUser={deleteDeviceFromUser}
                        updateUserState={updateUserState}
                    />
                ))}
            </tbody>
        </React.Fragment>
    );
}