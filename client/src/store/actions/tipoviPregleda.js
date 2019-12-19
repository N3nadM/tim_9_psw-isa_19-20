import {
  SET_ALL_TIPOVI_PREGLEDA,
  SET_ADDED_TIP_PREGLEDA
} from "../actionTypes";
import axios from "axios";

export const setTipoviPregleda = tipoviPregleda => ({
  type: SET_ALL_TIPOVI_PREGLEDA,
  tipoviPregleda
});

export const setNewTipPregleda = newTipPregleda => ({
  type: SET_ADDED_TIP_PREGLEDA,
  newTipPregleda
});

export const getAllTipoviPregleda = id => async (dispatch, getState) => {
  try {
    const tipoviPregleda = await axios.get(
      `/api/tipPregleda/getTipoviNaKlinici/${id}`
    );
    dispatch(setTipoviPregleda(tipoviPregleda.data));
  } catch (err) {
    console.log(err);
  }
};

export const addNewTipPregleda = data => async dispatch => {
  try {
    console.log(data);
    const tip = await axios.post(`/api/tipovi`, data);
    dispatch(setNewTipPregleda(tip.data));
  } catch (err) {
    console.log(err);
  }
};

export const searchTipovi = (id, data) => async dispatch => {
  try {
    const tipovi = await axios.post(`/api/tipPregleda/search/${id}`, data);
    dispatch(setTipoviPregleda(tipovi.data));
  } catch (err) {
    console.log(err);
  }
};
