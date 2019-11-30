import { SET_ALL_TIPOVI_PREGLEDA } from "../actionTypes";
import axios from "axios";

export const setTipoviPregleda = tipoviPregleda => ({
  type: SET_ALL_TIPOVI_PREGLEDA,
  tipoviPregleda
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
