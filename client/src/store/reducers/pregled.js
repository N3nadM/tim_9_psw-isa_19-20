import { SET_PREGLEDI, SET_PREGLEDI_ZA_SALU } from "../actionTypes";

const DEFAULT_STATE = {
  pregledi: [],
  preglediZaSalu: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_PREGLEDI:
      return {
        pregledi: action.pregledi.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_PREGLEDI_ZA_SALU:
      return {
        preglediZaSalu: action.preglediZaSalu.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    default:
      return state;
  }
};
