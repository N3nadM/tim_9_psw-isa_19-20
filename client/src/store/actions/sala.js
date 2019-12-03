import { SET_ADDED_SALA } from "../actionTypes";
import axios from "axios";

export const setNewSala = newSala => ({
  type: SET_ADDED_SALA,
  newSala
});

export const addNewSala = data => async dispatch => {
  try {
    const sala = await axios.post("/api/sala", data);
    dispatch(setNewSala(sala.data));
  } catch (err) {
    console.log(err);
  }
};
