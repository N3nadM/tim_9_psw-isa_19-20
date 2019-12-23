import {
  SET_ZAHTEV_ODSUSTVO,
  SET_ZAHTEVI_ADMIN_ODSUSTVO
} from "../actionTypes";
import axios from "axios";

export const setZahtevOdsustvo = zahtevOdsustvo => ({
  type: SET_ZAHTEV_ODSUSTVO,
  zahtevOdsustvo
});

export const setListaZahtevaOdsustvo = listaZahtevaOdsustvo => ({
  type: SET_ZAHTEVI_ADMIN_ODSUSTVO,
  listaZahtevaOdsustvo
});

export const addNewZahtevOdsustvo = data => async (dispatch, getState) => {
  try {
    const zahtevOdsustvo = await axios.post(`/api/zahtevOdsustvo`, data);
    dispatch(setZahtevOdsustvo(zahtevOdsustvo.data));
  } catch (err) {
    console.log(err);
  }
};

export const getZahteviOdsustvo = id => async (dispatch, getState) => {
  try {
    const zahteviOdsustvo = await axios.get(
      `/api/zahtevOdsustvo/naKlinici/${id}`
    );
    dispatch(setListaZahtevaOdsustvo(zahteviOdsustvo.data));
  } catch (err) {
    console.log(err);
  }
};
