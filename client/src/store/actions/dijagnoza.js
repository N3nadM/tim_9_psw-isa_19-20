import { SET_DIJAGNOZA } from "../actionTypes";
import axios from "axios";

export const setNewDijagnoza = dijagnoza => ({
  type: SET_DIJAGNOZA,
  dijagnoza
});

export const addNewDijagnoza = data => async dispatch => {
  try {
    const dijagnoza = await axios.post("/api/dijagnoza", data);
    dispatch(setNewDijagnoza(dijagnoza.data));
  } catch (err) {
    console.log(err);
  }
};
