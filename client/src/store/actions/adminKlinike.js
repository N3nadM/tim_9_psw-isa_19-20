import {
  SET_ALL_ADMINK,
  SET_ADDED_ADMINK,
  SET_ADMINOVA_KLINIKA,
  SET_GRAFIK,
  SET_ADMIN_KLINIKE,
  SET_EDIT_ADMIN_KLINIKE
} from "../actionTypes";
import axios from "axios";
import { setRealKorisnik } from "./auth";

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
export const setPodaciGrafik = podaciGrafik => ({
  type: SET_GRAFIK,
  podaciGrafik
});

export const setAdminKlinike = adminKlinike => ({
  type: SET_ADMIN_KLINIKE,
  adminKlinike
});

export const setNewAdminKorisnik = korisnik => ({
  type: SET_EDIT_ADMIN_KLINIKE,
  korisnik
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

export const getPodaciGrafik = (period, id) => async dispatch => {
  try {
    console.log("pozvao");
    const resp = await axios.get(`/api/klinika/grafik/${period}/${id}`);
    const data = [];
    for (const entry of Object.entries(resp.data)) {
      if (period === "Dan") {
        data.push(createData(entry[0] + ":00", entry[1]));
      } else {
        data.push(createData(entry[0], entry[1]));
      }
    }
    dispatch(setPodaciGrafik(data));
  } catch (err) {
    console.log(err);
  }
};
function createData(time, amount) {
  return { time, amount };
}

export const getAdminKlinike = id => async dispatch => {
  try {
    const k = await axios.get(`/api/adminK/korisnik/${id}`);
    dispatch(setRealKorisnik(k.data.korisnik));
    dispatch(setAdminKlinike(k.data));
  } catch (err) {
    console.log(err);
  }
};

export const editAdminKlinike = korisnik => async dispatch => {
  try {
    const k = await axios.put(`/api/users/${korisnik.id}`, korisnik);
    dispatch(setRealKorisnik(k.data));
    dispatch(setNewAdminKorisnik(k.data));
  } catch (err) {
    console.log(err);
  }
};
