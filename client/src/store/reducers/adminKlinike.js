import {
  SET_ALL_ADMINK,
  SET_ADDED_ADMINK,
  SET_ADMINOVA_KLINIKA,
  SET_GRAFIK,
  SET_ADMIN_KLINIKE,
  SET_EDIT_ADMIN_KLINIKE
} from "../actionTypes";

const DEFAULT_STATE = {
  admins: null,
  newAdmin: null,
  klinika: null,
  podaciGrafik: [],
  adminKlinike: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ALL_ADMINK:
      return {
        ...state,
        admins: action.admins
      };
    case SET_ADDED_ADMINK:
      return {
        ...state,
        newAdmin: action.newAdmin
      };
    case SET_ADMINOVA_KLINIKA:
      return {
        klinika: action.klinika
      };
    case SET_GRAFIK:
      return {
        ...state,
        podaciGrafik: action.podaciGrafik
      };
    case SET_ADMIN_KLINIKE:
      return {
        ...state,
        adminKlinike: action.adminKlinike
      };
    case SET_EDIT_ADMIN_KLINIKE:
      return {
        ...state,
        adminKlinike: { ...state.adminKlinike, korisnik: action.korisnik }
      };
    default:
      return state;
  }
};
