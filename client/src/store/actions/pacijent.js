import { SET_PACIJENT_PROFILE, SET_EDIT_PACIJENT } from "../actionTypes";
import axios from "axios";
import { setRealKorisnik } from "./auth";
import { getKlinika } from "./klinika";

export const setProfile = pacijent => ({
  type: SET_PACIJENT_PROFILE,
  pacijent
});

export const setNewPacijentKorisnik = korisnik => ({
  type: SET_EDIT_PACIJENT,
  korisnik
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

export const getPacijentiKlinike = id => async (dispatch, getState) => {
  try {
    const pacijenti = await axios.get(`/api/pacijent/pacijentiKlinike/${id}`);
    dispatch(setProfile(pacijenti.data));
  } catch (err) {
    console.log(err);
  }
};
