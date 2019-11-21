import { SET_ALL_KLINIKE } from "../actionTypes";
import axios from "axios";

export const setKlinike = klinike => ({
  type: SET_ALL_KLINIKE,
  klinike
});

export const getAllKlinike = (sum, rpp) => async dispatch => {
  try {
    const klinike = await axios.get(`/api/klinika`);
    dispatch(setKlinike(klinike.data));
  } catch (err) {
    console.log(err);
  }
};
