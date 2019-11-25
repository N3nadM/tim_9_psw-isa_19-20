import { SET_ALL_ADMINK, SET_ADDED_ADMINK } from "../actionTypes";
import axios from "axios";

export const setAdmins = admins => ({
  type: SET_ALL_ADMINK,
  admins
});

export const setNewAdmin = newAdmin => ({
  type: SET_ADDED_ADMINK,
  newAdmin
});

export const getAllAdmins = (sum, rpp) => async dispatch => {
  try {
    const admins = await axios.get(`/api/adminK`);
    dispatch(setAdmins(admins.data));
  } catch (err) {
    console.log(err);
  }
};

export const addNewAdmin = data => async dispatch => {
  try {
    const admin = await axios.post("/api/adminK", data);
    dispatch(setNewAdmin(admin.data));
  } catch (err) {
    console.log(err);
  }
};
