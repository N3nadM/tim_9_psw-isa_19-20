import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignIn from "./components/pages/prijava";
import SignUp from "./components/pages/registracija";
import AppBar from "./components/AppBar/AppBar";
import ScrollToTop from "./components/ScrollToTop/ScrollToTop";
import Profil from "./components/pages/profilAdminKlinike";

function App() {
  return (
    <>
      <Router>
        <ScrollToTop>
          <AppBar />
          <Switch>
            <Route exact path="/" component={SignIn} />
            <Route exact path="/signUp" component={SignUp} />
            {/*<Route exact path="/" component={Profil} />*/}
          </Switch>
        </ScrollToTop>
      </Router>
    </>
  );
}

export default App;
