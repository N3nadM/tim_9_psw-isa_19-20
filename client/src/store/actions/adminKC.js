import {
  SET_ALL_ADMINKC,
  SET_ADDED_ADMINKC,
  SET_ADMIN_KC,
  SET_EDITED_ADMIN_KC
} from "../actionTypes";
import axios from "axios";
import { setRealKorisnik } from "./auth";

export const setAdminsKC = admins => ({
  type: SET_ALL_ADMINKC,
  admins
});

export const setNewAdminKC = newAdmin => ({
  type: SET_ADDED_ADMINKC,
  newAdmin
});

export const setAdminKC = adminKC => ({
  type: SET_ADMIN_KC,
  adminKC
});

export const setNewAdminKCKorisnik = korisnik => ({
  type: SET_EDITED_ADMIN_KC,
  korisnik
});

export const addNewAdminKC = data => async dispatch => {
  try {
    const admin = await axios.post(`/api/adminkc`, data);
    dispatch(setNewAdminKC(admin.data));
  } catch (err) {
    console.log(err);
  }
};

export const getAdminKC = id => async dispatch => {
  try {
    const admin = await axios.get(`/api/adminkc/korisnik/${id}`);
    dispatch(setRealKorisnik(admin.data.korisnik));
    dispatch(setAdminKC(admin.data));
  } catch (err) {
    console.log(err);
  }
};

export const editAdminKC = korisnik => async dispatch => {
  try {
    const k = await axios.put(`/api/users/${korisnik.id}`, korisnik);
    dispatch(setRealKorisnik(k.data));
    dispatch(setNewAdminKCKorisnik(k.data));
  } catch (err) {
    console.log(err);
  }
};
