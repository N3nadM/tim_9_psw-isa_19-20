import { SET_ADDED_SALA, SET_LISTA_SALA } from "../actionTypes";
import axios from "axios";

export const setNewSala = newSala => ({
  type: SET_ADDED_SALA,
  newSala
});

export const setListaSala = listaSala => ({
  type: SET_LISTA_SALA,
  listaSala
});

export const addNewSala = data => async dispatch => {
  try {
    const sala = await axios.post("/api/sala", data);
    dispatch(setNewSala(sala.data));
  } catch (err) {
    console.log(err);
  }
};

export const getListaSala = id => async dispatch => {
  try {
    const sala = await axios.get(`/api/sala/saleNaKlinici/${id}`);
    dispatch(setListaSala(sala.data));
  } catch (err) {
    console.log(err);
  }
};
