import {
  SET_ADDED_SALA,
  SET_LISTA_SALA,
  SET_LISTA_DOSTUPNIH_SALA,
  SET_SALA_ZA_PREGLED,
  SET_SALE_KOJE_SE_MOGU_OBRISATI,
  SET_OBRISANA_SALA,
  SET_EDITED_SALA
} from "../actionTypes";
import axios from "axios";

export const setNewSala = newSala => ({
  type: SET_ADDED_SALA,
  newSala
});

export const setListaSala = listaSala => ({
  type: SET_LISTA_SALA,
  listaSala
});

export const setSalaZaPregled = salaZaPregled => ({
  type: SET_SALA_ZA_PREGLED,
  salaZaPregled
});

export const setListaDostupnihSala = listaDostupnihSala => ({
  type: SET_LISTA_DOSTUPNIH_SALA,
  listaDostupnihSala
});

export const setSaleZaBrisanje = saleZaBrisanje => ({
  type: SET_SALE_KOJE_SE_MOGU_OBRISATI,
  saleZaBrisanje
});

export const setObrisanaSala = obrisanaSala => ({
  type: SET_OBRISANA_SALA,
  obrisanaSala
});
export const setEditedSala = editedSala => ({
  type: SET_EDITED_SALA,
  editedSala
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

export const getListaDostupnihSala = (
  id,
  termin,
  trajanje
) => async dispatch => {
  try {
    console.log(termin);
    const sala = await axios.get(
      `/api/sala/dostupneSale/${id}/${termin}/${trajanje}`
    );
    dispatch(setListaDostupnihSala(sala.data));
  } catch (err) {
    console.log(err);
  }
};
export const searchSalaNaKlinici = (id, searchData) => async (
  dispatch,
  getState
) => {
  try {
    const sale = await axios.post(`/api/sala/search/${id}`, searchData);
    dispatch(setListaSala(sale.data));
  } catch (err) {
    console.log(err);
  }
};
export const setSalaZakazivanje = sala => async dispatch => {
  try {
    dispatch(setSalaZaPregled(sala));
  } catch (err) {
    console.log(err);
  }
};

export const getSaleZaBrisanje = id => async dispatch => {
  try {
    const k = await axios.get(`/api/sala/saleZaBrisanje/${id}`, id);
    dispatch(setSaleZaBrisanje(k.data));
  } catch (err) {
    console.log(err);
  }
};

export const obrisiSalu = id => async dispatch => {
  try {
    const k = await axios.delete(`/api/sala/delete/${id}`, id);
    dispatch(setObrisanaSala(k.data));
  } catch (err) {
    console.log(err);
  }
};

export const editSala = state => async dispatch => {
  try {
    const k = await axios.put(`/api/sala/edit`, state);
    dispatch(setEditedSala(k.data));
  } catch (err) {
    console.log(err);
  }
};
