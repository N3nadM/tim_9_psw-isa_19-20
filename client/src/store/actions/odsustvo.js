import { SET_ODSUSTVO } from "../actionTypes";
import axios from "axios";

export const setOdsustvo = odsustvo => ({
  type: SET_ODSUSTVO,
  odsustvo
});

export const addNewOdsustvo = data => async (dispatch, getState) => {
  try {
    const odsustvo = await axios.post(`/api/odsustvo`, data);
    dispatch(setOdsustvo(odsustvo.data));
  } catch (err) {
    console.log(err);
  }
};
