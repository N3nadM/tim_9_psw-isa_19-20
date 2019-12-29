import { SET_LEK, SET_LISTA_LEKOVA } from "../actionTypes";
import axios from "axios";

export const setNewLek = lek => ({
  type: SET_LEK,
  lek
});

export const setLekovi = lekovi => ({
  type: SET_LISTA_LEKOVA,
  lekovi
});

export const getAllLekovi = id => async dispatch => {
  try {
    const lekovi = await axios.get(`/api/lek`);
    dispatch(setLekovi(lekovi.data));
  } catch (err) {
    console.log(err);
  }
};

export const getLekoviByDijagnozaId = id => async dispatch => {
  try {
    const lekovi = await axios.get(`/api/lek/dijagnoza/${id}`);
    dispatch(setLekovi(lekovi.data));
  } catch (err) {
    console.log(err);
  }
};

export const addNewLek = data => async dispatch => {
  try {
    const lek = await axios.post("/api/lek", data);
    dispatch(setNewLek(lek.data));
  } catch (err) {
    console.log(err);
  }
};
