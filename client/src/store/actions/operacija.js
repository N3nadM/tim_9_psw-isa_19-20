import { SET_OPERACIJE } from "../actionTypes";
import axios from "axios";

export const setOperacije = operacije => ({
  type: SET_OPERACIJE,
  operacije
});

export const getAllOperacije = id => async (dispatch, getState) => {
  try {
    let operacije = getState().operacija.operacije;
    if (!operacije.length) {
      operacije = await axios.get(`/api/operacija/${id}`);
      dispatch(setOperacije(operacije.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const getAllOsobljeOperacije = id => async (dispatch, getState) => {
  try {
    let operacije = getState().operacija.operacije;
    if (!operacije.length) {
      operacije = await axios.get(`/api/operacija/osoblje/${id}`);
      dispatch(setOperacije(operacije.data));
    }
  } catch (err) {
    console.log(err);
  }
};
