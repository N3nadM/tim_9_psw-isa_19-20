import {
  SET_LEKAR_PROFILE,
  SET_EDIT_LEKAR,
  SET_LEKAR_LIST
} from "../actionTypes";

const DEFAULT_STATE = {
  lekar: null,
  lekarList: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_LEKAR_PROFILE:
      return {
        ...state,
        lekar: action.lekar
      };
    case SET_EDIT_LEKAR:
      return {
        ...state,
        lekar: { ...state.lekar, korisnik: action.korisnik }
      };
    case SET_LEKAR_LIST:
      return {
        ...state,
        lekarList: action.lekarList
      };
    default:
      return state;
  }
};
