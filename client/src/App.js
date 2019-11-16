import React from "react";
import { Provider } from "react-redux";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { configureStore } from "./store/index";

import SignIn from "./components/pages/Prijava";
import SignUp from "./components/pages/Registracija";
import AppBar from "./components/layout/AppBar/AppBar";
import jwtDecode from "jwt-decode";
import ScrollToTop from "./components/ScrollToTop/ScrollToTop";
import Profil from "./components/pages/ProfilAdminKlinike";
import AdminKC from "./components/pages/ProfilAKC";
import Drawer from "./components/layout/Drawer";
import { setAuthorizationToken, setCurrentUser } from "./store/actions/auth";

const store = configureStore();

if (localStorage.jwtToken) {
  setAuthorizationToken(localStorage.jwtToken);

  try {
    store.dispatch(setCurrentUser(jwtDecode(localStorage.jwtToken)));
  } catch (err) {
    store.dispatch(setCurrentUser({}));
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <ScrollToTop>
          <Drawer />
          <Switch>
            <Route exact path="/" component={SignIn} />
            <Route exact path="/signUp" component={SignUp} />
            <Route exact path="/adminKprofil" component={Profil} />
            <Route exact path="/adminKC" component={AdminKC} />
          </Switch>
        </ScrollToTop>
      </Router>
    </Provider>
  );
}

export default App;
