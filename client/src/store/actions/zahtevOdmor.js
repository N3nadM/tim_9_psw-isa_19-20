import {
  SET_ZAHTEV_ODMOR,
  SET_ZAHTEVI_ADMIN_ODMOR,
  SET_PRIHVACEN_ZAHTEV_ODMOR,
  SET_ODBIJEN_ZAHTEV_ODMOR
} from "../actionTypes";
import axios from "axios";

export const setZahtevOdmor = zahtevOdmor => ({
  type: SET_ZAHTEV_ODMOR,
  zahtevOdmor
});

export const setListaZahtevaOdmor = listaZahtevaOdmor => ({
  type: SET_ZAHTEVI_ADMIN_ODMOR,
  listaZahtevaOdmor
});

export const setOdbijenZahtevOdmor = odbijenZahtevOdmor => ({
  type: SET_ODBIJEN_ZAHTEV_ODMOR,
  odbijenZahtevOdmor
});

export const setPrihvacenZahtevOdmor = prihvacenZahtevOdmor => ({
  type: SET_PRIHVACEN_ZAHTEV_ODMOR,
  prihvacenZahtevOdmor
});

export const addNewZahtevOdmor = data => async (dispatch, getState) => {
  try {
    const zahtevOdmor = await axios.post(`/api/zahtevOdmor`, data);
    dispatch(setZahtevOdmor(zahtevOdmor.data));
  } catch (err) {
    console.log(err);
  }
};

export const getZahteviOdmor = id => async (dispatch, getState) => {
  try {
    const zahteviOdmor = await axios.get(`/api/zahtevOdmor/naKlinici/${id}`);
    dispatch(setListaZahtevaOdmor(zahteviOdmor.data));
  } catch (err) {
    console.log(err);
  }
};

export const denyZahtevOdmor = state => async (dispatch, getState) => {
  try {
    const zahtev = await axios.put(`/api/zahtevOdmor/deny`, state);
    dispatch(setOdbijenZahtevOdmor(zahtev.data));
  } catch (err) {
    console.log(err);
  }
};

export const acceptZahtevOdmor = id => async (dispatch, getState) => {
  try {
    const zahtev = await axios.put(`/api/zahtevOdmor/accept/${id}`);
    dispatch(setPrihvacenZahtevOdmor(zahtev.data));
  } catch (err) {
    console.log(err);
  }
};
