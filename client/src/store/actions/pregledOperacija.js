import {
  SET_ZAVRSEN_PREGLED_OPERACIJA,
  SET_ZAPOCET_PREGLED_OPERACIJA
} from "../actionTypes";
import axios from "axios";

export const setZavrsenPregledOperacija = pregledOperacija => ({
  type: SET_ZAVRSEN_PREGLED_OPERACIJA,
  pregledOperacija
});

export const setZapocetPregledOperacija = zapocetPregledOperacija => ({
  type: SET_ZAPOCET_PREGLED_OPERACIJA,
  zapocetPregledOperacija
});

export const zavrsenPregledOperacija = data => async dispatch => {
  try {
    const pregledOperacija = await axios.post("/api/pregled_operacija", data);
    dispatch(setZavrsenPregledOperacija(pregledOperacija.data));
  } catch (err) {
    console.log(err);
  }
};

export const zapocniPregledOperaciju = data => async dispatch => {
  try {
    const zapocetPregledOperacija = await axios.put(
      "/api/pregled_operacija",
      data
    );
    dispatch(setZapocetPregledOperacija(zapocetPregledOperacija.data));
  } catch (err) {
    console.log(err);
  }
};
