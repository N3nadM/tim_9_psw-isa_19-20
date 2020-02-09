import {
  SET_ZAVRSEN_PREGLED_OPERACIJA,
  SET_ZAPOCET_PREGLED_OPERACIJA
} from "../actionTypes";

const DEFAULT_STATE = {
  pregledOperacija: null,
  zapocetPregledOperacija: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZAVRSEN_PREGLED_OPERACIJA:
      return {
        ...state,
        pregledOperacija: action.pregledOperacija
      };
    case SET_ZAPOCET_PREGLED_OPERACIJA:
      return {
        ...state,
        zapocetPregledOperacija: action.zapocetPregledOperacija
      };

    default:
      return state;
  }
};
