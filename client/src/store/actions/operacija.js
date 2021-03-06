import {
  SET_OPERACIJA,
  SET_OPERACIJE,
  SET_OPERACIJE_ZA_SALU,
  SET_OPERACIJE_SESTRA,
  SET_OPERACIJE_LEKAR,
  SET_OPERACIJE_LEKAR_ODMOR,
  SET_OPERACIJE_SESTRA_ODMOR,
  SET_ZAVRSENE_OPERACIJE,
  SET_OPERACIJE_PRONALAZENJE_SALE
} from "../actionTypes";
import axios from "axios";

export const setOperacija = operacija => ({
  type: SET_OPERACIJA,
  operacija
});

export const setZavrseneOperacije = zavrseneOperacije => ({
  type: SET_ZAVRSENE_OPERACIJE,
  zavrseneOperacije
});

export const setOperacije = operacije => ({
  type: SET_OPERACIJE,
  operacije
});

export const setOperacijeZaSalu = operacijeZaSalu => ({
  type: SET_OPERACIJE_ZA_SALU,
  operacijeZaSalu
});
export const setOperacijeZaSestru = operacijeKodSestre => ({
  type: SET_OPERACIJE_SESTRA,
  operacijeKodSestre
});
export const setOperacijeZaLekara = operacijeKodLekara => ({
  type: SET_OPERACIJE_LEKAR,
  operacijeKodLekara
});

export const setOperacijeZaSestruOdmor = operacijeKodSestreOdmor => ({
  type: SET_OPERACIJE_SESTRA_ODMOR,
  operacijeKodSestreOdmor
});
export const setOperacijeZaLekaraOdmor = operacijeKodLekaraOdmor => ({
  type: SET_OPERACIJE_LEKAR_ODMOR,
  operacijeKodLekaraOdmor
});
export const setOperacijePronalazenjeSale = operacijePronalazenjeSale => ({
  type: SET_OPERACIJE_PRONALAZENJE_SALE,
  operacijePronalazenjeSale
});

export const getAllOperacije = id => async (dispatch, getState) => {
  try {
    let operacije = getState().operacija.operacije;
    if (!operacije.length) {
      operacije = await axios.get(`/api/operacija/${id}`);
      dispatch(setOperacije(operacije.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const getOperacijaById = id => async dispatch => {
  try {
    let operacija = await axios.get(`/api/operacija/zahtev/${id}`);
    dispatch(setOperacija(operacija.data));
  } catch (err) {
    console.log(err);
  }
};

export const getZavrseneOperacije = id => async (dispatch, getState) => {
  try {
    let zavrseneOperacije = await axios.get(
      `/api/operacija/osoblje/zavrseni/${id}`
    );
    dispatch(setZavrseneOperacije(zavrseneOperacije.data));
  } catch (err) {
    console.log(err);
  }
};

export const getAllOsobljeOperacije = id => async (dispatch, getState) => {
  try {
    let operacije = getState().operacija.operacije;
    if (!operacije.length) {
      operacije = await axios.get(`/api/operacija/osoblje/${id}`);
      dispatch(setOperacije(operacije.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const getAllSalaOperacije = id => async (dispatch, getState) => {
  try {
    let operacijeZaSalu = await axios.get(`/api/operacija/sala/${id}`);
    dispatch(setOperacijeZaSalu(operacijeZaSalu.data));
  } catch (err) {
    console.log(err);
  }
};

export const getOperacijeZaSestru = (id, datum) => async (
  dispatch,
  getState
) => {
  try {
    let operacijeZaSestru = await axios.get(
      `/api/operacija/zaSestru/${id}/${datum}`
    );
    dispatch(setOperacijeZaSestru(operacijeZaSestru.data));
  } catch (err) {
    console.log(err);
  }
};

export const getOperacijeZaLekara = (id, datum) => async (
  dispatch,
  getState
) => {
  try {
    let operacijeZaLekara = await axios.get(
      `/api/operacija/zaLekara/${id}/${datum}`
    );
    dispatch(setOperacijeZaLekara(operacijeZaLekara.data));
  } catch (err) {
    console.log(err);
  }
};

export const getOperacijeZaSestruOdmor = (id, datum1, datum2) => async (
  dispatch,
  getState
) => {
  try {
    let operacijeZaSestru = await axios.get(
      `/api/operacija/zaSestruOdmor/${id}/${datum1}/${datum2}`
    );
    dispatch(setOperacijeZaSestruOdmor(operacijeZaSestru.data));
  } catch (err) {
    console.log(err);
  }
};

export const getOperacijeZaLekaraOdmor = (id, datum1, datum2) => async (
  dispatch,
  getState
) => {
  try {
    let operacijeZaLekara = await axios.get(
      `/api/operacija/zaLekaraOdmor/${id}/${datum1}/${datum2}`
    );
    dispatch(setOperacijeZaLekaraOdmor(operacijeZaLekara.data));
  } catch (err) {
    console.log(err);
  }
};

export const getOperacijePronalazenjeSale = id => async dispatch => {
  try {
    let operacije = await axios.get(`/api/operacija/nemajuSalu/${id}`);
    dispatch(setOperacijePronalazenjeSale(operacije.data));
  } catch (err) {
    console.log(err);
  }
};
