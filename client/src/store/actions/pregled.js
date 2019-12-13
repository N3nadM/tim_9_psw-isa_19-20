import { SET_PREGLEDI, SET_PREGLEDI_ZA_SALU } from "../actionTypes";
import axios from "axios";

export const setPregledi = pregledi => ({
  type: SET_PREGLEDI,
  pregledi
});

export const setPreglediZaSalu = preglediZaSalu => ({
  type: SET_PREGLEDI_ZA_SALU,
  preglediZaSalu
});

export const getAllPregledi = id => async (dispatch, getState) => {
  try {
    let pregledi = getState().pregled.pregledi;
    if (!pregledi.length) {
      pregledi = await axios.get(`/api/pregled/${id}`);
      dispatch(setPregledi(pregledi.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const getAllOsobljePregledi = id => async (dispatch, getState) => {
  try {
    let pregledi = getState().pregled.pregledi;
    if (!pregledi.length) {
      pregledi = await axios.get(`/api/pregled/osoblje/${id}`);
      dispatch(setPregledi(pregledi.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const getAllSalaPregledi = id => async (dispatch, getState) => {
  try {
    let preglediZaSalu = await axios.get(`/api/pregled/sala/${id}`);
    dispatch(setPreglediZaSalu(preglediZaSalu.data));
  } catch (err) {
    console.log(err);
  }
};

export const setPregled = data => async (dispatch, getState) => {
  try {
    let pregled = await axios.post(`/api/pregled`, data);
  } catch (err) {
    console.log(err);
  }
};
