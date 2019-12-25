import {
  SET_ZAHTEV_ODSUSTVO,
  SET_ZAHTEVI_ADMIN_ODSUSTVO,
  SET_ODBIJEN_ZAHTEV_ODSUSTVO,
  SET_PRIHVACEN_ZAHTEV_ODSUSTVO
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

export const setOdbijenZahtev = odbijenZahtev => ({
  type: SET_ODBIJEN_ZAHTEV_ODSUSTVO,
  odbijenZahtev
});

export const setPrihvacenZahtev = prihvacenZahtev => ({
  type: SET_PRIHVACEN_ZAHTEV_ODSUSTVO,
  prihvacenZahtev
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

export const denyZahtev = state => async (dispatch, getState) => {
  try {
    const zahtev = await axios.put(`/api/zahtevOdsustvo/deny`, state);
    dispatch(setOdbijenZahtev(zahtev.data));
  } catch (err) {
    console.log(err);
  }
};

export const acceptZahtev = id => async (dispatch, getState) => {
  try {
    const zahtev = await axios.put(`/api/zahtevOdsustvo/accept/${id}`);
    dispatch(setPrihvacenZahtev(zahtev.data));
  } catch (err) {
    console.log(err);
  }
};
