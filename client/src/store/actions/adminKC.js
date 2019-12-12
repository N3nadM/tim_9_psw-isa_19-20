import { SET_ALL_ADMINKC, SET_ADDED_ADMINKC } from "../actionTypes";
import axios from "axios";

export const setAdminsKC = admins => ({
  type: SET_ALL_ADMINKC,
  admins
});

export const setNewAdminKC = newAdmin => ({
  type: SET_ADDED_ADMINKC,
  newAdmin
});

export const addNewAdminKC = data => async dispatch => {
  try {
    console.log(data);
    const admin = await axios.post(`/api/adminkc`, data);
    dispatch(setNewAdminKC(admin.data));
  } catch (err) {
    console.log(err);
  }
};
