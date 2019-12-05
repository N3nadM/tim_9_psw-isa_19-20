import {
  SET_PACIJENT_PROFILE,
  SET_EDIT_PACIJENT,
  SET_PACIJENT_ZDR
} from "../actionTypes";
import axios from "axios";
import { setRealKorisnik } from "./auth";

export const setProfile = pacijent => ({
  type: SET_PACIJENT_PROFILE,
  pacijent
});

export const setNewPacijentKorisnik = korisnik => ({
  type: SET_EDIT_PACIJENT,
  korisnik
});

export const setPacijentZdr = zdrKarton => ({
  type: SET_PACIJENT_ZDR,
  zdrKarton
});

export const getPacijent = id => async (dispatch, getState) => {
  try {
    const pacijent = await axios.get(`/api/pacijent/${id}`);
    dispatch(setRealKorisnik(pacijent.data.korisnik));
    dispatch(setProfile(pacijent.data));
  } catch (err) {
    console.log(err);
  }
};

export const editPacijent = korisnik => async dispatch => {
  try {
    const k = await axios.put(`/api/users/${korisnik.id}`, korisnik);
    dispatch(setNewPacijentKorisnik(k.data));
    dispatch(setRealKorisnik(k.data));
  } catch (err) {
    console.log(err);
  }
};

export const searchPacijent = searchData => async dispatch => {
  try {
    const pacijent = await axios.post(`/api/pacijent/search`, searchData);
    dispatch(setProfile(pacijent.data));
  } catch (err) {
    console.log(err);
  }
};

export const getPacijentiKlinike = id => async (dispatch, getState) => {
  try {
    const pacijenti = await axios.get(`/api/pacijent/pacijentiKlinike/${id}`);
    dispatch(setProfile(pacijenti.data));
  } catch (err) {
    console.log(err);
  }
};

export const getZdrKarton = id => async (dispatch, getState) => {
  try {
    const zdrKarton = await axios.get(`/api/pacijent/getZdrKarton/${id}`);
    dispatch(setPacijentZdr(zdrKarton.data));
  } catch (err) {
    console.log(err);
  }
};

export const editZdrKarton = zdrKarton => async dispatch => {
  try {
    const zdr = await axios.put(`/api/karton/edit/${zdrKarton.id}`, zdrKarton);
    dispatch(setPacijentZdr(zdr.data));
  } catch (err) {
    console.log(err);
  }
};
