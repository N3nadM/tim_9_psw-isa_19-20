import { SET_ZAHTEV_ODMOR } from "../actionTypes";
import axios from "axios";

export const setZahtevOdmor = zahtevOdmor => ({
  type: SET_ZAHTEV_ODMOR,
  zahtevOdmor
});

export const addNewZahtevOdmor = data => async (dispatch, getState) => {
  try {
    const zahtevOdmor = await axios.post(`/api/zahtevOdmor`, data);
    dispatch(setZahtevOdmor(zahtevOdmor.data));
  } catch (err) {
    console.log(err);
  }
};
