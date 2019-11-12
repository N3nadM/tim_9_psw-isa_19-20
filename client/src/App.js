import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignIn from "./components/pages/Prijava";
import SignUp from "./components/pages/Registracija";
import AppBar from "./components/AppBar/AppBar";
import ScrollToTop from "./components/ScrollToTop/ScrollToTop";

function App() {
  return (
    <>
      <Router>
        <ScrollToTop>
          <AppBar />
          <Switch>
            <Route exact path="/" component={SignIn} />
            <Route exact path="/signUp" component={SignUp} />
          </Switch>
        </ScrollToTop>
      </Router>
    </>
  );
}

export default App;
