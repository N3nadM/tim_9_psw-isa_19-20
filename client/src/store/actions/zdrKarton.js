import { SET_ZDRKARTON } from "../actionTypes";
import axios from "axios";

export const setZdrKarton = zdrKarton => ({
  type: SET_ZDRKARTON,
  zdrKarton
});

export const getZdrKarton = id => async (dispatch, getState) => {
  try {
    const state = getState();
    if (state.zdrKarton.zdrKarton === null) {
      const zdrKarton = await axios.get(`/api/karton/${id}`);
      dispatch(setZdrKarton(zdrKarton.data));
    }
  } catch (err) {
    console.log(err);
  }
};
