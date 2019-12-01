import {
  SET_LEKAR_PROFILE,
  SET_EDIT_LEKAR,
  SET_ADDED_LEKAR
} from "../actionTypes";

const DEFAULT_STATE = {
  lekar: null,
  addedLekar: null
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
    case SET_ADDED_LEKAR:
      return {
        addedLekar: action.addedLekar
      };

    default:
      return state;
  }
};
