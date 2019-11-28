import {
  SET_ALL_ADMINK,
  SET_ADDED_ADMINK,
  SET_ADMINOVA_KLINIKA
} from "../actionTypes";
import axios from "axios";

export const setAdmins = admins => ({
  type: SET_ALL_ADMINK,
  admins
});

export const setNewAdmin = newAdmin => ({
  type: SET_ADDED_ADMINK,
  newAdmin
});

export const setAdminovaKlinika = klinika => ({
  type: SET_ADMINOVA_KLINIKA,
  klinika
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
    console.log(data);
    const admin = await axios.post(`/api/adminK`, data);
    dispatch(setNewAdmin(admin.data));
  } catch (err) {
    console.log(err);
  }
};

export const getKlinikaAdmin = id => async (dispatch, getState) => {
  try {
    const klinika = await axios.get(`/api/adminK/getKlinika/${id}`);
    dispatch(setAdminovaKlinika(klinika.data));
  } catch (err) {
    console.log(err);
  }
};
export const editKlinika = klinika => async dispatch => {
  try {
    const k = await axios.put(`/api/klinika/edit/${klinika.id}`, klinika);
    dispatch(setAdminovaKlinika(k.data));
  } catch (err) {
    console.log(err);
  }
};
