import {
  SET_ALL_ADMINK,
  SET_ADDED_ADMINK,
  SET_ADMINOVA_KLINIKA
} from "../actionTypes";

const DEFAULT_STATE = {
  admins: null,
  newAdmin: null,
  klinika: null
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
    default:
      return state;
  }
};
