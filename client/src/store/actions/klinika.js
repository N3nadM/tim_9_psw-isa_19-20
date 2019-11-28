import { SET_ALL_KLINIKE, SET_ADDED_KLINIKA } from "../actionTypes";
import axios from "axios";

export const setKlinike = klinike => ({
  type: SET_ALL_KLINIKE,
  klinike
});

export const setNewKlinika = newKlinika => ({
  type: SET_ADDED_KLINIKA,
  newKlinika
});

export const getAllKlinike = (sum, rpp) => async (dispatch, getState) => {
  try {
    let klinike = getState().klinika.klinike;
    if (!klinike) {
      klinike = await axios.get(`/api/klinika`);
      dispatch(setKlinike(klinike.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const searchKlinike = searchData => async dispatch => {
  try {
    const klinike = await axios.post(`/api/klinika/search`, searchData);
    dispatch(setKlinike(klinike.data));
  } catch (err) {
    console.log(err);
  }
};

export const addNewKlinika = data => async dispatch => {
  try {
    const klinika = await axios.post("/api/klinika", data);
    dispatch(setNewKlinika(klinika.data));
  } catch (err) {
    console.log(err);
  }
};
