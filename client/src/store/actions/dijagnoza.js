import { SET_DIJAGNOZA, SET_ALL_DIJAGNOZE } from "../actionTypes";
import axios from "axios";

export const setNewDijagnoza = dijagnoza => ({
  type: SET_DIJAGNOZA,
  dijagnoza
});

export const setListaDijagnoza = dijagnoze => ({
  type: SET_ALL_DIJAGNOZE,
  dijagnoze
});

export const addNewDijagnoza = data => async dispatch => {
  try {
    const dijagnoza = await axios.post("/api/dijagnoza", data);
    dispatch(setNewDijagnoza(dijagnoza.data));
  } catch (err) {
    console.log(err);
  }
};

export const getAllDijagnoze = () => async dispatch => {
  try {
    const dijagnoze = await axios.get("/api/dijagnoza");
    dispatch(setListaDijagnoza(dijagnoze.data));
  } catch (err) {
    console.log(err);
  }
};
