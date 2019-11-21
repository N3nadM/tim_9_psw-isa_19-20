import { SET_LEKAR_PROFILE, SET_EDIT_LEKAR } from "../actionTypes";

const DEFAULT_STATE = {
  lekar: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_LEKAR_PROFILE:
      return {
        lekar: action.lekar
      };
    case SET_EDIT_LEKAR:
      return {
        lekar: { ...state.lekar, korisnik: action.korisnik }
      };

    default:
      return state;
  }
};
