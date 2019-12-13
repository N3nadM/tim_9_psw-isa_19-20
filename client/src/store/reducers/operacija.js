import { SET_OPERACIJE, SET_OPERACIJE_ZA_SALU } from "../actionTypes";

const DEFAULT_STATE = {
  operacije: [],
  operacijeZaSalu: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_OPERACIJE:
      return {
        operacije: action.operacije.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_OPERACIJE_ZA_SALU:
      return {
        operacijeZaSalu: action.operacijeZaSalu.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    default:
      return state;
  }
};
