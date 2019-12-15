import {
  SET_MEDSESTRA_PROFILE,
  SET_EDIT_MEDSESTRA,
  SET_LISTA_DOSTUPNIH_SESTARA,
  SET_SESTRA_ZA_PREGLED
} from "../actionTypes";
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

export const setZakazivanje = sestraZaPregled => ({
  type: SET_SESTRA_ZA_PREGLED,
  sestraZaPregled
});

export const setListaDostupnihSestara = listaDostupnihSestara => ({
  type: SET_LISTA_DOSTUPNIH_SESTARA,
  listaDostupnihSestara
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

export const getListaDostupnihSestara = (
  id,
  termin,
  trajanje
) => async dispatch => {
  try {
    console.log(termin);
    const sestre = await axios.get(
      `/api/medsestra/dostupneSestre/${id}/${termin}/${trajanje}`
    );
    dispatch(setListaDostupnihSestara(sestre.data));
  } catch (err) {
    console.log(err);
  }
};

export const setSestraZakazivanje = sestra => async dispatch => {
  try {
    dispatch(setZakazivanje(sestra));
  } catch (err) {
    console.log(err);
  }
};
