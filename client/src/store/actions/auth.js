import { SET_CURRENT_USER, SET_ERROR, SET_ZAHTEV } from "../actionTypes";
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

export const setZahtev = zahtev => ({
  type: SET_ZAHTEV,
  zahtev
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
    const jwt = await axios.post("/api/users/login", userData);
    const pureJwt = jwt.data.slice(7);
    localStorage.setItem("jwtToken", pureJwt);
    setAuthorizationToken(pureJwt);
    const decodedToken = jwtDecode(pureJwt);
    dispatch(setCurrentUser(decodedToken));
  } catch (err) {
    dispatch(setError(err));
  }
};

export const registerUser = userData => async dispatch => {
  try {
    const zahtev = await axios.post("/api/users/createRequest", userData);
    console.log(zahtev);
    dispatch(setZahtev(zahtev));
  } catch (err) {
    dispatch(setError(err));
  }
};
