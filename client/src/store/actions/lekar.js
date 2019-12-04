import {
  SET_LEKAR_PROFILE,
  SET_EDIT_LEKAR,
  SET_LEKAR_LIST,
  SET_ADDED_LEKAR
} from "../actionTypes";
import axios from "axios";
import { setRealKorisnik } from "./auth";

export const setProfile = lekar => ({
  type: SET_LEKAR_PROFILE,
  lekar
});

export const setNewLekarKorisnik = korisnik => ({
  type: SET_EDIT_LEKAR,
  korisnik
});
export const setAddedLekar = addedLekar => ({
  type: SET_ADDED_LEKAR,
  addedLekar
});

export const setLekariKlinike = lekarList => ({
  type: SET_LEKAR_LIST,
  lekarList
});

export const getLekar = id => async (dispatch, getState) => {
  try {
    const lekar = await axios.get(`/api/lekar/${id}`);
    dispatch(setRealKorisnik(lekar.data.korisnik));
    dispatch(setProfile(lekar.data));
  } catch (err) {
    console.log(err);
  }
};

export const editLekar = korisnik => async dispatch => {
  try {
    const k = await axios.put(`/api/users/${korisnik.id}`, korisnik);
    dispatch(setNewLekarKorisnik(k.data));
    dispatch(setRealKorisnik(k.data));
  } catch (err) {
    console.log(err);
  }
};

export const getLekariKlinike = (id, searchData) => async (
  dispatch,
  getState
) => {
  try {
    const lekari = await axios.post(`/api/klinika/getLekari/${id}`, searchData);
    dispatch(setLekariKlinike(lekari.data));
  } catch (err) {
    console.log(err);
  }
};

export const addNewLekar = data => async dispatch => {
  try {
    console.log(data);
    const lekar = await axios.post(`/api/lekar`, data);
    dispatch(setAddedLekar(lekar.data));
  } catch (err) {
    console.log(err);
  }
};
