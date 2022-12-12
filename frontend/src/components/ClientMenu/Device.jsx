import React from "react";
import { useState } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import Util from "../../Util";
import Graph from "../Graph/Graph";

export default function Device(props) {

  let handleOnClick = (props) => {

    let statusToSet = !undefinedToFalseOrValue(props.device.isActive);
    console.log(statusToSet)
    // console.log(undefinedToFalseOrValue(props.device.isActive))
    setActive(statusToSet)

    props.clickDevice(props.device, statusToSet);
  };

  let undefinedToFalseOrValue = (value) => {
    return Util.isUndefined(value) ? false : value;
  };

  const [active, setActive] = useState(undefinedToFalseOrValue(props.device.isActive));

  return (
    <React.Fragment>
      {active ? (
        <ListGroupItem
          aria-current="true"
          onClick={() => {
            handleOnClick(props);
          }}
          active
        >
          {props.device.name}
          <br />
          {props.device.description}
        </ListGroupItem>
      ) : (
        <ListGroupItem
          aria-current="true"
          onClick={(event) => {
            handleOnClick(props, event);
          }}
        >
          {props.device.name}
          <br />
          {props.device.description}

        </ListGroupItem>
      )}
    </React.Fragment>
  );
}
