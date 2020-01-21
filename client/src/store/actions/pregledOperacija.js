import {
  SET_ZAVRSEN_PREGLED_OPERACIJA,
  SET_ZAPOCET_PREGLED_OPERACIJA,
  SET_ISPRAVI_ZAPOCET_PREGLED,
  SET_ISPRAVI_ZAPOCETA_OPERACIJA
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

export const setIspravkaZapocetPregled = ispravkaZapocetPregled => ({
  type: SET_ISPRAVI_ZAPOCET_PREGLED,
  ispravkaZapocetPregled
});

export const setIspravkaZapocetaOperacija = ispravkaZapocetaOperacija => ({
  type: SET_ISPRAVI_ZAPOCETA_OPERACIJA,
  ispravkaZapocetaOperacija
});

export const zavrsenPregledOperacija = data => async dispatch => {
  try {
    const pregledOperacija = await axios.post("/api/pregled_operacija", data);
    dispatch(setZavrsenPregledOperacija(pregledOperacija.data));

    if (pregledOperacija.data.vrsta === 0) {
      dispatch(setIspravkaZapocetPregled(pregledOperacija.data));
    } else if (pregledOperacija.data.vrsta === 1) {
      dispatch(setIspravkaZapocetaOperacija(pregledOperacija.data));
    }
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
    if (zapocetPregledOperacija.data.vrsta === 0) {
      dispatch(setIspravkaZapocetPregled(zapocetPregledOperacija.data));
    } else if (zapocetPregledOperacija.data.vrsta === 1) {
      dispatch(setIspravkaZapocetaOperacija(zapocetPregledOperacija.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const izmeniIzvestaj = data => async dispatch => {
  try {
    await axios.put("/api/pregled_operacija/izvestaj", data);
  } catch (err) {
    console.log(err);
  }
};
