import {
  SET_ZAVRSEN_PREGLED_OPERACIJA,
  SET_ZAPOCET_PREGLED_OPERACIJA,
  SET_ISPRAVI_ZAPOCET_PREGLED
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

export const zavrsenPregledOperacija = data => async dispatch => {
  try {
    const pregledOperacija = await axios.post("/api/pregled_operacija", data);
    dispatch(setZavrsenPregledOperacija(pregledOperacija.data));

    dispatch(setIspravkaZapocetPregled(pregledOperacija.data));
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
    if (zapocetPregledOperacija.data.vrsta == 0) {
      console.log("uslo");
      dispatch(setIspravkaZapocetPregled(zapocetPregledOperacija.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const izmeniIzvestaj = data => async dispatch => {
  try {
    const res = await axios.put("/api/pregled_operacija/izvestaj", data);
  } catch (err) {
    console.log(err);
  }
};
