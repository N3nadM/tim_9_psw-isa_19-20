import {
  SET_ALL_TIPOVI_PREGLEDA,
  SET_ADDED_TIP_PREGLEDA,
  SET_TIPOVI_KOJI_SE_MOGU_IZMENITI,
  SET_EDITED_TIP,
  SET_OBRISAN_TIP
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

export const setTipovKojiSeMoguIzmeniti = tipoviZaIzmenu => ({
  type: SET_TIPOVI_KOJI_SE_MOGU_IZMENITI,
  tipoviZaIzmenu
});

export const setEditedTip = editedTip => ({
  type: SET_EDITED_TIP,
  editedTip
});
export const setDeletedTip = obrisanTip => ({
  type: SET_OBRISAN_TIP,
  obrisanTip
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

export const getTipoviZaIzmenu = id => async (dispatch, getState) => {
  try {
    const tipoviPregleda = await axios.get(
      `/api/tipPregleda/tipoviZaIzmenu/${id}`
    );
    dispatch(setTipovKojiSeMoguIzmeniti(tipoviPregleda.data));
  } catch (err) {
    console.log(err);
  }
};

export const editTip = data => async dispatch => {
  try {
    const tip = await axios.post(`/api/tipPregleda/edit`, data);
    dispatch(setEditedTip(tip.data));
  } catch (err) {
    console.log(err);
  }
};

export const deleteTip = id => async dispatch => {
  try {
    const tip = await axios.delete(`/api/tipPregleda/delete/${id}`);
    dispatch(setDeletedTip(tip.data));
  } catch (err) {
    console.log(err);
  }
};
