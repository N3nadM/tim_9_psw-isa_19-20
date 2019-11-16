import React from "react";
import { Route, Redirect } from "react-router-dom";

import { connect } from "react-redux";

const PrivateRoute = ({ isAuthenticated, component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      !isAuthenticated ? <Redirect to="/login" /> : <Component {...props} />
    }
  />
);

const mapStateToProps = state => ({
  isAuthenticated: state.currentUser.isAuthenticated
});

export default connect(mapStateToProps)(PrivateRoute);
