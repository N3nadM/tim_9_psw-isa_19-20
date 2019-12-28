import { SET_ZAVRSEN_PREGLED_OPERACIJA } from "../actionTypes";
import axios from "axios";

export const setZavrsenPregledOperacija = pregledOperacija => ({
  type: SET_ZAVRSEN_PREGLED_OPERACIJA,
  pregledOperacija
});

export const zavrsenPregledOperacija = data => async dispatch => {
  try {
    const pregledOperacija = await axios.post("/api/pregled_operacija", data);
    dispatch(setZavrsenPregledOperacija(pregledOperacija.data));
  } catch (err) {
    console.log(err);
  }
};
