import {
  SET_ZAHTEV_LOADING,
  SET_ALL_ZAHTEV,
  DELETE_ZAHTEV
} from "../actionTypes";
import axios from "axios";

export const setLoading = zahtev => ({
  type: SET_ZAHTEV_LOADING
});

export const setAllZahtevi = zahtevi => ({
  type: SET_ALL_ZAHTEV,
  zahtevi
});

export const deleteZahtev = id => ({
  type: DELETE_ZAHTEV,
  id
});

export const getZahtevi = id => async dispatch => {
  try {
    dispatch(setLoading());
    const zahtevi = await axios.get("/api/adminkc/zahtevi");
    dispatch(setAllZahtevi(zahtevi.data));
  } catch (err) {
    console.log(err);
  }
};

export const confirmZahtev = (id, email) => async dispatch => {
  try {
    await axios.post(`/api/users/register/${email}`);
    dispatch(deleteZahtev(id));
  } catch (err) {
    console.log(err);
  }
};

export const denieZahtev = (id, email, message) => async dispatch => {
  try {
    await axios.post(`/api/users/denie/${email}`, message);
    dispatch(deleteZahtev(id));
  } catch (err) {
    console.log(err);
  }
};
