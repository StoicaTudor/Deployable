import React from "react";

import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";

let Utils = require("../../Util.js").default


// device.energyConsumption.map((ec) => {
//   //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//   // LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

//   var date = new Date(ec.date);
//   console.log(Utils.dateToHourMin(date));
//   console.log(date.toLocaleDateString("en-US"));

//   data.push({
//     date: Utils.dateToHourMin(date),
//     consumption: ec.consumption,
//   });
// });


export default function Graph({ devices, calendarSelectedDate }) {

  let deviceEnergyConsumptionsToGraphData = () => {
    const data = [];

    devices.forEach((device) => {

      if (!device.isActive)
        return;

      device.energyConsumption.map((ec) => {

        var date = new Date(ec.date);
        var calendarSelectedDateObject = new Date(calendarSelectedDate)

        if (
          date.getFullYear() === calendarSelectedDateObject.getFullYear()
          && date.getMonth() === calendarSelectedDateObject.getMonth()
          && date.getDay() === calendarSelectedDateObject.getDay()
        )
          data.push({
            date: Utils.dateToHourMin(date),
            consumption: ec.consumption,
          });

      });
    });
    return data;
  };


  console.log(devices)

  return (
    <div>
      {devices && <LineChart
        width={500}
        height={300}
        data={deviceEnergyConsumptionsToGraphData()}
        margin={{
          top: 5,
          right: 30,
          left: 20,
          bottom: 5,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" />
        <YAxis />
        <Tooltip />
        <Legend />
        {/* <Line
        type="monotone"
        dataKey="pv"
        stroke="#8884d8"
        activeDot={{ r: 8 }}
      />
      <Line type="monotone" dataKey="date" stroke="#82ca9d" /> */}
        <Line type="monotone" dataKey="consumption" stroke="#82ca9d" />
      </LineChart>}
    </div>
  );
}
