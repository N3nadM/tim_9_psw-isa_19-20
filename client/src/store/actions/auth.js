import { SET_CURRENT_USER, SET_ERROR } from "../actionTypes";
import axios from "axios";
import jwtDecode from "jwt-decode";

export const setCurrentUser = user => ({
  type: SET_CURRENT_USER,
  user
});

export const setError = error => ({
  type: SET_ERROR,
  error
});

export const setAuthorizationToken = token => {
  if (token) {
    axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
  } else {
    delete axios.defaults.headers.common["Authorization"];
  }
};

export function logout() {
  return dispatch => {
    localStorage.clear();
    setAuthorizationToken(false);
    dispatch(setCurrentUser({}));
  };
}

export const authUser = userData => async dispatch => {
  try {
    const jwt = await axios.post("/api/users/login", userData, {
      withCredentials: true
    });
    const pureJwt = jwt.data.slice(7);
    localStorage.setItem("jwtToken", pureJwt);
    setAuthorizationToken(pureJwt);
    const decodedToken = jwtDecode(pureJwt);
    dispatch(setCurrentUser(decodedToken));
  } catch (err) {
    dispatch(setError(err));
  }
};