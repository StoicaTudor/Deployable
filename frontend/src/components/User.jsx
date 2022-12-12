import React from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

let handleOnClick = (props, event) => {
  props.isActive
    ? props.unselectDevice(props.device)
    : props.selectDevice(props.device);
};

export default function User(props) {
  return (
    <React.Fragment>
      {props.isActive ? (
        <ListGroupItem
          aria-current="true"
          onClick={() => {
            handleOnClick(props);
          }}
          active
        >
          {props.user.id}
          <br />
          {props.user.name}
        </ListGroupItem>
      ) : (
        <ListGroupItem
          aria-current="true"
          onClick={(event) => {
            handleOnClick(props, event);
          }}
        >
          {props.user.id}
          <br />
          {props.user.name}
        </ListGroupItem>
      )}
    </React.Fragment>
  );
}
