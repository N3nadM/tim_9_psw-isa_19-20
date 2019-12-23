import { SET_ZAHTEV_ODMOR, SET_ZAHTEVI_ADMIN_ODMOR } from "../actionTypes";
import axios from "axios";

export const setZahtevOdmor = zahtevOdmor => ({
  type: SET_ZAHTEV_ODMOR,
  zahtevOdmor
});

export const setListaZahtevaOdmor = listaZahtevaOdmor => ({
  type: SET_ZAHTEVI_ADMIN_ODMOR,
  listaZahtevaOdmor
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
