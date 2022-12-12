import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';

export default function NavigationBar() {

  const navigate = useNavigate();

  function to(route) {
    navigate(route);
  }

  return (


    <Navbar bg="primary" variant="dark">
      <Container>
        <Navbar.Brand>Energy Whatever</Navbar.Brand>

        <Nav className="me-auto">
          <Nav.Link
            // onClick={() => {
            //   routeChange("/adminMenu")
            // }}
            onClick={() => to("/adminMenu")}
          >
            {/* <a href="/adminMenu">Admin Menu</a> */}
            Admins
          </Nav.Link>

          <Nav.Link
            onClick={() => to("/clientMenu")}
          >
            Clients
            {/* <a href="/clientMenu">Client Menu</a> */}
          </Nav.Link>

          <Nav.Link
            onClick={() => to("/devicesMenu")}
          >
            Devices
          </Nav.Link>

          <Nav.Link>
            <a href="/signOut">Sign Out</a>
          </Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}
