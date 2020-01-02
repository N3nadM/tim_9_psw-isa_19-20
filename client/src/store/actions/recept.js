import { SET_RECEPTI, SET_OVEREN_RECEPT } from "../actionTypes";
import axios from "axios";

export const setRecepti = recepti => ({
  type: SET_RECEPTI,
  recepti
});

export const setRecept = overenRecept => ({
  type: SET_OVEREN_RECEPT,
  overenRecept
});

export const getAllReceptiZaOveru = id => async dispatch => {
  try {
    const recepti = await axios.get(`/api/recept/${id}`);
    dispatch(setRecepti(recepti.data));
  } catch (err) {
    console.log(err);
  }
};

export const overiRecept = id => async dispatch => {
  try {
    const overenRecept = await axios.put(`/api/recept/overi/${id}`);
    dispatch(setRecept(overenRecept.data));
  } catch (err) {
    console.log(err);
  }
};
