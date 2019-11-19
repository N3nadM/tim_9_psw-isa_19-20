import { SET_LEKAR_PROFILE, SET_EDIT_LEKAR } from "../actionTypes";
import axios from "axios";
import { setRealKorisnik } from "./auth";

export const setProfile = lekar => ({
  type: SET_LEKAR_PROFILE,
  lekar
});

export const setNewPacijentKorisnik = korisnik => ({
  type: SET_EDIT_LEKAR,
  korisnik
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
    dispatch(setNewPacijentKorisnik(k.data));
    dispatch(setRealKorisnik(k.data));
  } catch (err) {
    console.log(err);
  }
};
