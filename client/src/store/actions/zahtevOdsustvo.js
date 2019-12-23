import { SET_ZAHTEV_ODSUSTVO } from "../actionTypes";
import axios from "axios";

export const setZahtevOdsustvo = zahtevOdsustvo => ({
  type: SET_ZAHTEV_ODSUSTVO,
  zahtevOdsustvo
});

export const addNewZahtevOdsustvo = data => async (dispatch, getState) => {
  try {
    const zahtevOdsustvo = await axios.post(`/api/zahtevOdsustvo`, data);
    dispatch(setZahtevOdsustvo(zahtevOdsustvo.data));
  } catch (err) {
    console.log(err);
  }
};
