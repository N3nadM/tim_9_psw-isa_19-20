import {
  SET_OPERACIJE,
  SET_OPERACIJE_ZA_SALU,
  SET_OPERACIJE_LEKAR,
  SET_OPERACIJE_SESTRA
} from "../actionTypes";

const DEFAULT_STATE = {
  operacije: [],
  operacijeZaSalu: [],
  operacijeKodLekara: [],
  operacijeKodSestre: []
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
    case SET_OPERACIJE_SESTRA:
      return {
        operacijeKodSestre: action.operacijeKodSestre
      };
    case SET_OPERACIJE_LEKAR:
      return {
        operacijeKodLekara: action.operacijeKodLekara
      };
    default:
      return state;
  }
};
