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

export const getAllTipoviPregleda = (sum, rpp) => async (
  dispatch,
  getState
) => {
  try {
    let tipoviPregleda = getState().tipoviPregleda.tipoviPregleda;
    if (!tipoviPregleda) {
      tipoviPregleda = await axios.get(`/api/tipPregleda`);
      dispatch(setTipoviPregleda(tipoviPregleda.data));
    }
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
