import React from "react";
import { Provider } from "react-redux";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { configureStore } from "./store/index";

import SignIn from "./components/pages/prijava";
import SignUp from "./components/pages/registracija";
import AppBar from "./components/layout/AppBar";
import jwtDecode from "jwt-decode";
import ScrollToTop from "./components/ScrollToTop/ScrollToTop";

import Drawer from "./components/layout/Drawer";
import { setAuthorizationToken, setCurrentUser } from "./store/actions/auth";
import PrivateRoute from "./routing/PrivateRoute";

import Home from "./components/pages/Home";

const store = configureStore();

if (localStorage.jwtToken) {
  const decodedToken = jwtDecode(localStorage.jwtToken);
  const now = new Date();
  if (now.getMilliseconds() > decodedToken.exp) {
    try {
      setAuthorizationToken(false);
      store.dispatch(setCurrentUser({}));
    } catch (err) {
      console.log(err);
    }
  } else {
    try {
      setAuthorizationToken(localStorage.jwtToken);
      store.dispatch(setCurrentUser(decodedToken));
    } catch (err) {
      store.dispatch(setCurrentUser({}));
    }
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <ScrollToTop>
          <Switch>
            <Route
              exact
              path="/login"
              render={props => <SignIn {...props} />}
            />
            <Route exact path="/signUp" component={SignUp} />
            <PrivateRoute exact path="/" component={Home} />
          </Switch>
        </ScrollToTop>
      </Router>
    </Provider>
  );
}

export default App;
