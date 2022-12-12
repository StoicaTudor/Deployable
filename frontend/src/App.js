import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ClientMenu from "./components/ClientMenu/ClientMenu";
import AdminMenu from "./components/AdminMenu";
import DevicesMenu from "./components/DevicesMenu";

function App() {

  return (
    // <React.Fragment>
    // <ClientMenu />
    // <Example />
    // </React.Fragment>

    <BrowserRouter>
      <main>
        <Routes>
          <Route path="/" element={<ClientMenu />} />
          <Route path="/clientMenu" element={<ClientMenu />} />
          <Route path="/adminMenu" element={<AdminMenu />} />
          <Route path="/devicesMenu" element={<DevicesMenu />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
