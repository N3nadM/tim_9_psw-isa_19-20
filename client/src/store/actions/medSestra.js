import { SET_MEDSESTRA_PROFILE, SET_EDIT_MEDSESTRA } from "../actionTypes";
import axios from "axios";
import { setRealKorisnik } from "./auth";

export const setProfile = medsestra => ({
  type: SET_MEDSESTRA_PROFILE,
  medsestra
});

export const setNewMedSestraKorisnik = korisnik => ({
  type: SET_EDIT_MEDSESTRA,
  korisnik
});

export const getMedSestra = id => async (dispatch, getState) => {
  try {
    const medsestra = await axios.get(`/api/medsestra/${id}`);
    dispatch(setRealKorisnik(medsestra.data.korisnik));
    dispatch(setProfile(medsestra.data));
  } catch (err) {
    console.log(err);
  }
};

export const editMedSestra = korisnik => async dispatch => {
  try {
    const k = await axios.put(`/api/users/${korisnik.id}`, korisnik);
    dispatch(setNewMedSestraKorisnik(k.data));
    dispatch(setRealKorisnik(k.data));
  } catch (err) {
    console.log(err);
  }
};
