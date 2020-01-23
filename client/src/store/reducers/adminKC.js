import {
  SET_ALL_ADMINKC,
  SET_ADDED_ADMINKC,
  SET_ADMIN_KC,
  SET_EDITED_ADMIN_KC
} from "../actionTypes";

const DEFAULT_STATE = {
  admins: null,
  newAdmin: null,
  adminKC: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ALL_ADMINKC:
      return {
        ...state,
        admins: action.admins
      };
    case SET_ADDED_ADMINKC:
      return {
        ...state,
        newAdmin: action.newAdmin
      };
    case SET_ADMIN_KC:
      return {
        ...state,
        adminKC: action.adminKC
      };
    case SET_EDITED_ADMIN_KC:
      return {
        ...state,
        adminKC: { ...state.adminKC, korisnik: action.korisnik }
      };
    default:
      return state;
  }
};
