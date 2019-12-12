import { SET_PREGLEDI } from "../actionTypes";
import axios from "axios";

export const setPregledi = pregledi => ({
  type: SET_PREGLEDI,
  pregledi
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
