import {
  SET_ALL_KLINIKE,
  SET_ADDED_KLINIKA,
  SET_KLINIKA,
  SET_ALL_TIPOVI
} from "../actionTypes";
import { setLekariKlinike } from "./lekar";
import axios from "axios";

export const setKlinike = klinike => ({
  type: SET_ALL_KLINIKE,
  klinike
});

export const setKlinika = klinika => ({
  type: SET_KLINIKA,
  klinika
});

export const setNewKlinika = newKlinika => ({
  type: SET_ADDED_KLINIKA,
  newKlinika
});

export const setTipovi = tipoviPregleda => ({
  type: SET_ALL_TIPOVI,
  tipoviPregleda
});

export const getAllKlinike = (sum, rpp) => async (dispatch, getState) => {
  try {
    const klinike = await axios.get(`/api/klinika`);
    dispatch(setKlinike(klinike.data));
  } catch (err) {
    console.log(err);
  }
};

export const searchKlinike = searchData => async dispatch => {
  try {
    const klinike = await axios.post(`/api/klinika/search`, searchData);
    dispatch(setKlinike(klinike.data));
    dispatch(setLekariKlinike([]));
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

export const getKlinika = id => async (dispatch, getState) => {
  try {
    const klinike = getState().klinika.klinike;
    const k = klinike !== null && klinike.find(kl => kl.id === id);

    if (!k) {
      const klinika = await axios.get(`/api/klinika/${id}`);
      dispatch(setKlinika(klinika.data));
    } else {
      dispatch(setKlinika(k));
    }
  } catch (err) {
    console.log(err);
  }
};

export const getTipoviPrelgeda = () => async (dispatch, getState) => {
  try {
    let tipoviPregleda = getState().klinika.tipoviPregleda;
    if (!tipoviPregleda.length) {
      tipoviPregleda = await axios.get("/api/tipovi");
      dispatch(setTipovi(tipoviPregleda.data));
    }
  } catch (err) {
    console.log(err);
  }
};
