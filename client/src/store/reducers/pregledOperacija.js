import { SET_ZAVRSEN_PREGLED_OPERACIJA } from "../actionTypes";

const DEFAULT_STATE = {
  pregledOperacija: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZAVRSEN_PREGLED_OPERACIJA:
      return {
        ...state,
        pregledOperacija: action.pregledOperacija
      };
    default:
      return state;
  }
};
